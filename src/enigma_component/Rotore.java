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
public class Rotore {
    String rotor;
    int offset;
    
    public Rotore(String rotor) {
        this.rotor = rotor;
    }
    
    public Rotore(String rotor, int offset) {
        this.rotor = rotor;
        this.offset = offset;
    }
    
    public String getRotor() {
        return rotor;
    }
    
    public int getOffset() {
        return offset;
    }

    public void setRotor(String rotor) {
        this.rotor = rotor;
    }
    
    public void rotate() {
        offset ++;
        if (offset == 26)
                offset = 0;
    }
    
    public void rotate(int offset) {
        this.offset += offset;
        if (this.offset == 26)
                this.offset = 0;
    }
    
    public char translate(char character) {
        int index = (((int) character) - 65 + offset) % 26;
        return rotor.charAt(index);
    }
}