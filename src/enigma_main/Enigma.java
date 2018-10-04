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

    private Rotore rotore1;
    private Rotore rotore2;
    private Rotore rotore3;
    private Scambiatore scambiatore;
    private Riflettore riflettore;
    private Configuration configuration;
    private int[] initial_position;

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
            ArrayList<Rotore> arr = rif.getRotori();
            this.rotore1 = arr.get(this.configuration.getRot()[0]);
            this.rotore2 = arr.get(this.configuration.getRot()[1]);
            this.rotore3 = arr.get(this.configuration.getRot()[2]);
            this.riallinea();
        } catch (Exception ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Impossibile caricare rotori!");
        }
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Enigma temp = new Enigma();
        System.out.println(temp.codifica('I'));
        System.out.println(temp.codifica('B'));
    }

}
