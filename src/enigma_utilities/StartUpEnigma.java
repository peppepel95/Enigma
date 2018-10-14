/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_utilities;

import enigma_component.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Legge i file rotore.txt e configurazione.txt, salva i rotori in una lista e
 * in base alla configurazione seleziona i rotori e salva scambiatore e
 * riflettore.
 *
 * @author peppepel95
 */
public class StartUpEnigma extends ReadFile {

    private final ArrayList<String> rotori;
    private final String rot;
    private final String config;
    private Configuration config_obj;

    public StartUpEnigma(String rot, String config) {
        this.rot = rot;
        this.config = config;
        this.rotori = new ArrayList<>();
        this.config_obj = null;
    }

    private void setConfiguration() throws IOException, IllegalArgumentException {
        this.readText(this.config, "setConfiguration");
    }

    private void setRotori() throws IOException, IllegalArgumentException {
        this.readText(this.rot, "setRotori");
    }

    public ArrayList<String> getRotori() throws IOException, IllegalArgumentException {
        if (this.rotori.isEmpty()) {
            this.setRotori();
        }
        return rotori;
    }

    public Configuration getConfiguration() throws IllegalArgumentException, IOException {
        if (this.config_obj == null) {
            this.config_obj = new Configuration();
            this.setConfiguration();
        }
        return config_obj;
    }

    @Override
    public void hook(String type, String result) throws IllegalArgumentException {
        String pattern;
        String[] str;

        if (type.equals("setRotori")) {
            this.rotorHook(result);
        }
        if (type.equals("setConfiguration")) {
            this.configHook(result);
        }
    }

    private void rotorHook(String result) throws IllegalArgumentException {
        if (!result.contains("Rotore")) {
            if (result.length() != 26 || !this.validate(result))
                throw new IllegalArgumentException();
            rotori.add(result);
        }
    }

    private void configHook(String result) throws IllegalArgumentException {
        String pattern;
        String[] str;

        str = result.split(": ");
        if (str.length != 2) {
            throw new IllegalArgumentException();
        }
        if (str[1].contains(",")) {
            pattern = ", ";
        } else if (str[1].contains(" ")) {
            pattern = " ";
        } else {
            throw new IllegalArgumentException();
        }

        String[] items = str[1].split(pattern);
        switch (str[0]) {
            case "Rotori":
                if (items.length == 3) {
                    for (String item : items) {
                        if (item.length() != 1 || item.charAt(0) < '0' + 1 || item.charAt(0) > '0' + 8) {
                            throw new IllegalArgumentException();
                        }
                    }
                } else {
                    throw new IllegalArgumentException();
                }
                this.config_obj.setRot(items);
                break;
            case "Posizione iniziale":
                if (items.length == 3) {
                    for (String item : items) {
                        int num = Integer.parseInt(item);
                        if (num < 0 || num > 25) {
                            throw new IllegalArgumentException();
                        }
                    }
                } else {
                    throw new IllegalArgumentException();
                }
                this.config_obj.setStartPos(items);
                break;
            case "Scambiatore":
                if (items.length > 10 || items.length < 7 || !StartUpEnigma.validate(String.join("", items)))
                    throw new IllegalArgumentException();
                this.config_obj.setPlugboard(items);
                break;
            case "Riflettore":
                if ((items.length != 12 && items.length != 13) || !StartUpEnigma.validate(String.join("", items)))
                    throw new IllegalArgumentException();
                if (items.length == 12) {
                    items = StartUpEnigma.findLast(items);
                }
                this.config_obj.setReflector(items);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static boolean validate(String s) {
        System.out.println(s);
        if (!s.matches("[A-Z]+"))
            return false;
        int[] arr = new int[26];
        
        for (int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'A';
            arr[index] += 1;
            if (arr[index] > 1)
                return false;
        }
        return true;
    }

    public static String[] findLast(String[] items) {
        String concatenation = Arrays.toString(items);
        String alf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char temp[] = new char[2];
        int k = 0;
        for (int i = 0; i < alf.length(); i++) {
            if (concatenation.indexOf(alf.charAt(i)) < 0) {
                temp[k] = alf.charAt(i);
                k++;
            }
        }
        String[] new_items = new String[items.length + 1];
        System.arraycopy(items, 0, new_items, 0, new_items.length - 1);
        new_items[new_items.length - 1] = new String(temp);
        return new_items;
    }

    public static void main(String[] args) throws IOException, Exception {
        StartUpEnigma rif = new StartUpEnigma("rotore", "configurazione");
        ArrayList<String> rotori = rif.getRotori();
        System.out.println(rotori);
        Configuration config = rif.getConfiguration();
        System.out.println(config);
    }
}
