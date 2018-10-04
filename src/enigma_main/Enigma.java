/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_main;

import enigma_component.*;
import enigma_utilities.StartUpEnigma;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author peppepel95
 */
public class Enigma {
    private static final int NUMERO_ROTORI = 3;
    private static final int LUNG_ALF = 26;
    private static final int MIN_PLUG = 7;
    private static final int MAX_PLUG = 10;
    
    private Rotore rotore1;
    private Rotore rotore2;
    private Rotore rotore3;
    private Scambiatore scambiatore;
    private Riflettore riflettore;
    private Configuration configuration;
    private int[] initial_position;
    private ArrayList<Rotore> arrRot;

    public Enigma() {
        StartUpEnigma rif = new StartUpEnigma("rotore", "configurazione");
        try {
            this.configuration = rif.getConfiguration();
            this.scambiatore = this.configuration.getPlugboard();
            this.riflettore = this.configuration.getReflector();
            this.initial_position = this.configuration.getStartPos();
        } catch (Exception ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Impossibile caricare configurazione!");
        }
        try {
            this.arrRot = rif.getRotori();
            int[] index = this.configuration.getRot();
            this.getRotori(arrRot, index);
            this.riallinea();
        } catch (Exception ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Impossibile caricare rotori!");
        }
    }
    
    private void getRotori(ArrayList<Rotore> arr,int[] index) {
        this.rotore1 = arr.get(index[0]);
        this.rotore2 = arr.get(index[1]);
        this.rotore3 = arr.get(index[2]);
    }
    
    public final void riallinea(){
        this.rotore1.setOffset(initial_position[0]);
        this.rotore2.setOffset(initial_position[1]);
        this.rotore3.setOffset(initial_position[2]);
    }

    public char codifica(char input) {
        char c = this.scambiatore.Swap(input);
        c = this.rotore1.translate(c, true);
        
        c = this.rotore2.translate(c, true);
        c = this.rotore3.translate(c, true);
        c = this.riflettore.reflect(c);
        c = this.rotore3.translate(c, false);
        c = this.rotore2.translate(c, false);
        c = this.rotore1.translate(c, false);
        c = this.scambiatore.Swap(c);
        this.rotore1.rotate();
        if (this.rotore1.getOffset() == this.initial_position[0]) {
            this.rotore2.rotate();
            if (this.rotore2.getOffset() == this.initial_position[1]) {
                this.rotore3.rotate();
            }
        }
        return c;
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
        
        if (!riflettore.isEmpty() && riflettore.length() == (LUNG_ALF+LUNG_ALF/2-1)) {
            this.riflettore = new Riflettore(riflettore.split(" "));
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Enigma temp = new Enigma();
        System.out.println(temp.codifica('I'));
        System.out.println(temp.codifica('B'));
    }

}
