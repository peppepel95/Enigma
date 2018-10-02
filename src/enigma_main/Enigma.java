/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_main;

import enigma_component.Configuration;
import enigma_component.Riflettore;
import enigma_component.Rotore;
import enigma_component.Scambiatore;
import enigma_utilities.Read_input_file;
import java.util.ArrayList;
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

    public Enigma(){
        Read_input_file rif = new Read_input_file("rotore", "configurazione");
        try {
            this.configuration = rif.getConfiguration();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Impossibile caricare configurazione!");
        }
        try {
            ArrayList<Rotore> arr = rif.getRotori();
            this.rotore1 = arr.get(0);
            this.rotore2 = arr.get(1);
            this.rotore3 = arr.get(2);
            this.initial_position[0] = this.rotore1.getOffset();
            this.initial_position[1] = this.rotore2.getOffset();
            this.initial_position[2] = this.rotore3.getOffset();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Impossibile caricare rotori!");
        }
    }
       
    public char codifica (char input){
        char c = this.scambiatore.Swap(input);
        c = this.rotore1.translate(c,true);
        this.rotore1.rotate();
        if (this.rotore1.getOffset() == this.initial_position[0]){
            this.rotore2.rotate();
            if (this.rotore2.getOffset() == this.initial_position[1]){
                this.rotore3.rotate();
            }
        }
        c = this.rotore2.translate(c,true);
        c = this.rotore3.translate(c,true);
        c = this.riflettore.reflect(c);
        c = this.rotore3.translate(c,false);
        c = this.rotore2.translate(c,false);
        return this.rotore1.translate(c,false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Enigma temp = new Enigma();
        System.out.println(temp.codifica('A'));
    }
    
}
