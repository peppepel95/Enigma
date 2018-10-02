/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_component;

import java.util.ArrayList;

/**
 *
 * @author peppepel95
 */
public class Rotore {
    private ArrayList<Character> directRotor;
    private ArrayList<Character> inverseRotor;
    private int offset;
    
    public Rotore(String rotor, int offset) {
        int index;
        char c;
        for (int i = 0; i < rotor.length(); i++) {
            c = rotor.charAt(i);
            index = (int) c - 65;
            
            directRotor.add(c);
            inverseRotor.add(index, (char)(i + 65));
        }
        this.offset = offset;
    }
    
    public ArrayList<Character> getDirectRotor() {
        return directRotor;
    }
    public ArrayList<Character> getinverseRotor() {
        return inverseRotor;
    }
    
    public int getOffset() {
        return offset;
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
    
    public char translate(char character, boolean direction) {
        int index = (((int) character) - 65 + offset) % 26;
        if (direction == false){
            return directRotor.get(index);
        } else {
            return inverseRotor.get(index);
        }
    }
    
    public static void main(String[] args) {
        String s = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        Rotore r = new Rotore(s,0);
        
        int offset = r.getOffset();
        System.out.println(offset);
        
        char input = 'A';
        char output = r.translate(input,true);
        System.out.println(output);
        
        r.rotate();
        
        output = r.translate(input,true);
        System.out.println(output);
        
        output = r.translate(input,true);
        System.out.println(output);
    }
}