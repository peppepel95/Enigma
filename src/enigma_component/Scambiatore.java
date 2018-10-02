/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_component;

/**
 *
 * @author peppepel95
 */
public class Scambiatore extends Riflettore{
    public Scambiatore(String[] reflector) {
        super(reflector);
    }
    
    public char Swap(char character) {
        return super.reflect(character);
    } 
}
