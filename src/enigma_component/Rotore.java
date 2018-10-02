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
    private String rotor;
    private int offset;
    
    public Rotore(String rotor) {
        this.rotor = rotor;
        this.offset = 0;
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
    
    public static void main(String[] args) {
//        String s = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
//        Rotore r = new Rotore(s);
//        
//        int offset = r.getOffset();
//        System.out.println(offset);
//        
//        String rot = r.getRotor();
//        System.out.println(rot);
//        
//        char input = 'A';
//        char output = r.translate(input);
//        System.out.println(output);
//        
//        r.rotate();
//        
//        output = r.translate(input);
//        System.out.println(output);
//        
//        output = r.translate(input);
//        System.out.println(output);
        
        System.out.println("______________________");
        
        String[] pairs = "IU, AS, DV, GL, FT, OX, EZ, CH, MR, KN, BQ, PW".split(",\\s");
        
//        for(String p:pairs) {  
//            System.out.println(p);  
//        }  
        
        
    }
    
}