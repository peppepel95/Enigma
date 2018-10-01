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
    private final Scambiatore scambiatore;
    private final Riflettore riflettore;
    private final String rot;
    private final String config;
    private Configuration config_obj;

    public Read_input_file(String rot, String config) {
        this.rot = rot;
        this.config = config;
        this.rotori = new ArrayList<>();
        this.scambiatore = null;
        this.riflettore = null;
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

    public Scambiatore getScambiatore() throws IOException, Exception {
        if (this.scambiatore == null)
            this.setConfiguration();
        return scambiatore;
    }

    public Riflettore getRiflettore() throws IOException, Exception {
        if (this.riflettore == null)
            this.setConfiguration();
        return riflettore;
    }

    private void hook(String type, String result) throws Exception {
        String pattern;
        String[] str;
        
        if (type.equals("setRotori")) {
            if (!result.contains("Rotore")) {
                rotori.add(new Rotore());
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
            this.config_obj = new Configuration();
            switch (str[0]) {
                case "Rotori":
                    config_obj.setRot(items);
                    break;
                case "Posizione iniziale":
                    config_obj.setStartPos(items);
                    break;
                case "Scambiatore":
                    config_obj.setPlugboard(items);
                    break;
                case "Riflettore":
                    config_obj.setReflector(items);
                    break;
                default:
                    throw new Exception();
            }
        }
    }
    
    
    public static void main(String[] args) throws IOException, Exception {
        Read_input_file rif = new Read_input_file("rotore", "configurazione");
        ArrayList<Rotore> rotori = rif.getRotori();
    }
    
}
