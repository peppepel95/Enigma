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
public class Scambiatore {
    private String  plugboard;
    
    public void setPlugboard(String plugboard) {
        this.plugboard = plugboard;
    }

    public String getPlugboard() {
        return plugboard;
    }
    
    public char translate(char character) {
        int index = (((int) character) - 65) % 26;
        return plugboard.charAt(index);
    } 
}
