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

    public Enigma(Configuration configuration) {
        this.configuration = configuration;
    }
       
    public char codifica (char input){
        char c = this.scambiatore.translate(input);
        c = this.rotore1.translate(c,true);
        this.rotore1.rotate();
        if (this.rotore1.getOffset() == this.rotore1.getConfiguration()){
            this.rotore2.rotate();
            if (this.rotore2.getOffset() == this.rotore2.getConfiguration()){
                this.rotore3.rotate();
            }
        }
        c = this.rotore2.translate(c,true);
        c = this.rotore3.translate(c,true);
        c = this.riflettore.translate(c);
        c = this.rotore3.translate(c,false);
        c = this.rotore2.translate(c,false);
        return this.rotore1.translate(c,false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
