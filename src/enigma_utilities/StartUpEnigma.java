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
 * in base alla configurazione seleziona i rotori e salva scambiatore e riflettore.
 * @author peppepel95
 */
public class StartUpEnigma extends ReadFile{
    private final ArrayList<Rotore> rotori;
    private final String rot;
    private final String config;
    private Configuration config_obj;

    public StartUpEnigma(String rot, String config) {
        this.rot = rot;
        this.config = config;
        this.rotori = new ArrayList<>();
        this.config_obj = null;
    }

    private void setConfiguration() throws IOException, Exception {
        this.readText(this.config, "setConfiguration");
    }
    
    private void setRotori() throws IOException, Exception {
        this.readText(this.rot, "setRotori");
    }

    public ArrayList<Rotore> getRotori() throws IOException, Exception {
        if (this.rotori.isEmpty())
            this.setRotori();
        return rotori;
    }
    
    public Configuration getConfiguration() throws Exception {
        if (this.config_obj == null) {
            this.config_obj = new Configuration();
            this.setConfiguration();
        }
        return config_obj;
    }
    
    @Override
    public void hook(String type, String result) throws Exception {
        String pattern;
        String[] str;
        
        if (type.equals("setRotori")) {
            if (!result.contains("Rotore")) {
                rotori.add(new Rotore(result));
            }
        }
        if (type.equals("setConfiguration")) {
            str = result.split(": ");
            if (str[1].contains(",")) {
                pattern = ", ";
            }
            else {
                pattern = " ";
            }
            String[] items = str[1].split(pattern);
            switch (str[0]) {
                case "Rotori":
                    this.config_obj.setRot(items);
                    break;
                case "Posizione iniziale":
                    this.config_obj.setStartPos(items);
                    break;
                case "Scambiatore":
                    this.config_obj.setPlugboard(items);
                    break;
                case "Riflettore":
                    String[] new_items = StartUpEnigma.findLast(items);
                    this.config_obj.setReflector(new_items);
                    break;
                default:
                    throw new Exception();
            }
        }
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
        ArrayList<Rotore> rotori = rif.getRotori();
        System.out.println(rotori);
        Configuration config = rif.getConfiguration();
        System.out.println(config);
    }
    
}