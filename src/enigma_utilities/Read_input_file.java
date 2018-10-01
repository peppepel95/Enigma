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
    private ArrayList<Rotore> rotori;
    private Scambiatore scambiatore;
    private Riflettore riflettore;
    private final String rot;
    private final String config;

    public Read_input_file(String rot, String config) {
        this.rot = rot;
        this.config = config;
        this.rotori = new ArrayList<>();
        this.scambiatore = null;
        this.riflettore = null;
    }
    
    private void readText(String file, String type) throws FileNotFoundException, IOException {
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

    private void setConfiguration() throws IOException {
        this.readText(this.rot, "setConfiguration");
    }
    
    private void setRotori() throws IOException {
        this.readText(this.rot, "setRotori");
    }

    public ArrayList<Rotore> getRotori() throws IOException {
        if (this.rotori.isEmpty())
            this.setRotori();
        return rotori;
    }

    public Scambiatore getScambiatore() throws IOException {
        if (this.scambiatore == null)
            this.setConfiguration();
        return scambiatore;
    }

    public Riflettore getRiflettore() throws IOException {
        if (this.riflettore == null)
            this.setConfiguration();
        return riflettore;
    }

    private void hook(String type, String result) {
        if (type.equals("setRotori")) {
            if (!result.contains("Rotore")) {
                rotori.add(new Rotore());
            }
        }
        if (type.equals("setConfiguration")) {
            //setta scambiatore e riflettore
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        Read_input_file rif = new Read_input_file("rotore", "configurazione");
        ArrayList<Rotore> rotori = rif.getRotori();
    }
    
}
