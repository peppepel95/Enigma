/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_main;

import enigma_component.*;
import enigma_utilities.StartUpEnigma;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author peppepel95
 */
public class Enigma {
    private static final int NUMERO_ROTORI = 3;
    public static final int LUNG_ALF = 26;
    private static final int MIN_PLUG = 7;
    private static final int MAX_PLUG = 10;
    
    private Rotore rotore1;
    private Rotore rotore2;
    private Rotore rotore3;
    private Scambiatore scambiatore;
    private Riflettore riflettore;
    private Configuration configuration;
    private int[] initial_position;
    private ArrayList<String> arrRot;

    public Enigma() {
        StartUpEnigma rif = new StartUpEnigma("rotore", "configurazione");
        try {
            this.configuration = rif.getConfiguration();
            this.scambiatore = this.configuration.getPlugboard();
            System.out.println(this.scambiatore.toString());
            this.riflettore = this.configuration.getReflector();
            System.out.println(this.riflettore.toString());
            this.initial_position = this.configuration.getStartPos();
        } catch (IllegalArgumentException | IOException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Impossibile caricare configurazione!");
        }
        try {
            this.arrRot = rif.getRotori();
            int[] index = this.configuration.getRot();
            this.getRotori(arrRot, index);
            this.riallinea();
        } catch (IOException | IllegalArgumentException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Impossibile caricare rotori!");
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
    
    private void getRotori(ArrayList<String> arr,int[] index) {
        try{
            this.rotore1 = new Rotore(arr.get(index[0]), this.configuration.getStartPos()[0]);
            System.out.println(this.rotore1.toString());
            this.rotore2 = new Rotore(arr.get(index[1]), this.configuration.getStartPos()[1]);
            System.out.println(this.rotore2.toString());
            this.rotore3 = new Rotore(arr.get(index[2]), this.configuration.getStartPos()[2]);
            System.out.println(this.rotore3.toString());
        } catch (Exception e){
            System.err.println(Arrays.toString(e.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Impossibile caricare rotori!");
        }
    }
    
    public final void riallinea(){
        this.rotore1.setOffset(0);
        this.rotore2.setOffset(0);
        this.rotore3.setOffset(0);
    }

    public char codifica(char input) {
        if (input == ' '){
            return ' ';
        }
        this.rotore1.rotate();
        if (this.rotore1.getOffset() == 0) {
            this.rotore2.rotate();
            if (this.rotore2.getOffset() == 0) {
                this.rotore3.rotate();
            }
        }
        int c = this.scambiatore.Swap((int)(input - 65));
        c = this.rotore1.translate(c, true);
        c = this.rotore2.translate(c, true);
        c = this.rotore3.translate(c, true);
        c = this.riflettore.reflect(c);
        c = this.rotore3.translate(c, false);
        c = this.rotore2.translate(c, false);
        c = this.rotore1.translate(c, false);
        c = this.scambiatore.Swap(c);
        return (char)(c + 65);
    }

    public void setConfiguration(int[] rotori, int[] posizioni, String scambiatore, String riflettore) {
        if (rotori.length == NUMERO_ROTORI)         //cambio rotori
            this.getRotori(this.arrRot, rotori);
        
        if (posizioni.length == NUMERO_ROTORI) {    //cambio posizioni
            this.initial_position = posizioni;
            this.riallinea();
        }
        
        if (!scambiatore.isEmpty() && scambiatore.length() >= 3*MIN_PLUG-1 && scambiatore.length() <= 3*MAX_PLUG-1) {
            this.scambiatore = new Scambiatore(scambiatore.split(" "));
        }
        
        if (!riflettore.isEmpty()) {
            if (riflettore.length() == (LUNG_ALF+LUNG_ALF/2-1)) //26+12 = 38
                this.riflettore = new Riflettore(riflettore.split(" "));
            else if (riflettore.length() == (LUNG_ALF-2+(LUNG_ALF-2)/2-1)) //24+11 = 35
                this.riflettore = new Riflettore(StartUpEnigma.findLast(riflettore.split(" ")));
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Enigma temp = new Enigma();
        System.out.println(temp.codifica('P'));
        System.out.println(temp.codifica('A'));
    }

}
