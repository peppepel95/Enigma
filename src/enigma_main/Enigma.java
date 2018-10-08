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
    private ArrayList<String> arrRot;

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

    public Configuration getConfiguration() {
        return configuration;
    }
    
    private void getRotori(ArrayList<String> arr,int[] index) {
        this.rotore1 = new Rotore(arr.get(index[0]), this.configuration.getStartPos()[0]);
        this.rotore2 = new Rotore(arr.get(index[1]), this.configuration.getStartPos()[1]);
        this.rotore3 = new Rotore(arr.get(index[2]), this.configuration.getStartPos()[2]);
    }
    
    public final void riallinea(){
        this.rotore1.setOffset(initial_position[0]);
        this.rotore2.setOffset(initial_position[1]);
        this.rotore3.setOffset(initial_position[2]);
    }

    public char codifica(char input) {
        if (input == ' '){
            return ' ';
        }
        this.rotore1.rotate();
        if (this.rotore1.getOffset() == this.initial_position[0]) {
            this.rotore2.rotate();
            if (this.rotore2.getOffset() == this.initial_position[1]) {
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
        System.out.println(temp.codifica('M'));
        System.out.println(temp.configuration.toString());
    }

}
