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
public class Riflettore {
    String  reflector;

    public Riflettore(String  reflector) {
        this.reflector = reflector;
    }
    
    public void setReflector(String reflector) {
        this.reflector = reflector;
    }

    public String getReflector() {
        return reflector;
    }
    
    public char translate(char character) {
        int index = (((int) character) - 65) % 26;
        return reflector.charAt(index);
    }
}
