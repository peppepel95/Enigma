/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_utilities;

import enigma_component.*;
import java.io.*;
import java.util.ArrayList;


/**
 * Legge i file rotore.txt e configurazione.txt, salva i rotori in una lista e 
 * in base alla configurazione seleziona i rotori e salva scambiatore e riflettore.
 * @author peppepel95
 */
public class Read_input_file {
    private final ArrayList<Rotore> rotori;
    private final String rot;
    private final String config;
    private Configuration config_obj;

    public Read_input_file(String rot, String config) {
        this.rot = rot;
        this.config = config;
        this.rotori = new ArrayList<>();
        this.config_obj = null;
    }
    
    private void readText(String file, String type) throws FileNotFoundException, IOException, Exception {
        String result;
        boolean eof = false;
        FileReader filein = new FileReader(file + ".txt");
        BufferedReader br = new BufferedReader(filein);
        while (!eof) {
            result = br.readLine();
            if (result == null) {
                eof = true;
            }
            else {
                this.hook(type,result);
            }
        }
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
    
    private void hook(String type, String result) throws Exception {
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
                    this.config_obj.setReflector(items);
                    break;
                default:
                    throw new Exception();
            }
        }
    }
    
    public static void main(String[] args) throws IOException, Exception {
        Read_input_file rif = new Read_input_file("rotore", "configurazione");
        ArrayList<Rotore> rotori = rif.getRotori();
        System.out.println(rotori);
        Configuration config = rif.getConfiguration();
        System.out.println(config);
    }
    
}
