/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_component;

import enigma_main.Enigma;

/**
 *
 * @author peppepel95
 */
public class Rotore {

    private final int[] DirectRotor;
    private final int[] InverseRotor;
    private int offset;
    private final String rotorStr;
    
    public Rotore(String rotor) {
        this(rotor, 0);
    }

    public Rotore(String rotor, int offset) {
        this.DirectRotor = new int[26];
        this.InverseRotor = new int[26];
        int index;
        char c;
        if (offset > 0)
            rotor = rotor.substring(Enigma.LUNG_ALF - offset) + rotor.substring(0, Enigma.LUNG_ALF - offset);
        this.rotorStr = rotor;
        
        for (int i = 0; i < rotor.length(); i++) {
            c = rotor.charAt(i);
            index = (int) c - 65;
            DirectRotor[index] = i;
            InverseRotor[i] = index;
        }
        this.offset = 0;
    }

    public int[] getDirectRotor() {
        return DirectRotor;
    }
    
    public int[] getInverseRotor() {
        return InverseRotor;
    }

    public int getOffset() {
        return offset;
    }

    public void rotate() {
        this.offset = (this.offset + 1) % 26;
    }

    public void rotate(int offset) {
        this.offset = (this.offset + offset) % 26;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        String dir = "";
        String inv = "";
        for(int i = 0;i<26;i++){
            dir += (this.DirectRotor[(i - offset + 26) % 26]) + " ";
            inv += (this.InverseRotor[(i - offset + 26) % 26]) + " ";
        }
        return "Rotore:\n" + this.rotorStr + "\nDirectRotor=" + dir + "\nInverseRotor=" + inv + '\n';
    }
    
    
    
    public int  translate(int value, boolean direction) {
        if (direction) {
            value = (DirectRotor[(value - offset + 26) % 26] + offset) % 26;
        } else {
            value = (InverseRotor[(value - offset + 26) % 26] + offset) % 26; 
        }
        return value;
    }
    
    /*
    Rotori: 3, 4, 1
    Posizione iniziale: 12, 5, 24
    Scambiatore: EJ OY IV AQ KW FX MT PS LU BD 
    Riflettore: IU AS DV GL FT OX EZ CH MR KN BQ PW JY 
    
    Rotore#1
    EKMFLGDQVZNTOWYHXUSPAIBRCJ
    Rotore#2
    AJDKSIRUXBLHWTMCQGZNPYFVOE
    Rotore#3
    BDFHJLCPRTXVZNYEIWGAKMUSQO
    Rotore#4
    ESOVPZJAYQUIRHXLNFTGKDCMWB
    Rotore#5
    VZBRGITYUPSDNHLXAWMJQOFECK
    Rotore#6
    JPGVOUMFYQBENHZRDKASXLICTW
    Rotore#7
    NZJHGRCXMYSWBOUFAIVLPEKQDT
    Rotore#8
    FKQHTLXOCBJSPDZRAMEWNIUYGV
    */
    
    public static void main(String[] args) {
        String s1 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
        String s2 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
        String s3 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        Rotore r1 = new Rotore(s1,12);//12
        Rotore r2 = new Rotore(s2,5);//5
        Rotore r3 = new Rotore(s3,24);//24
        Riflettore s = new Riflettore("IU AS DV GL FT OX EZ CH MR KN BQ PW JY".split(" "));
        System.out.println(r1.toString());
        System.out.println(r2.toString());
        System.out.println(r3.toString());
        
        int output;
        output = r1.translate(0, true);
        System.out.println(output);
        r1.rotate();
        output = r1.translate(0, true);
        System.out.println(output);
    }
}
