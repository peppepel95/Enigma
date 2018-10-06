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

    private final int[] DirectRotor;
    private final int[] InverseRotor;
    private int offset;

    public Rotore(String rotor) {
        this.DirectRotor = new int[26];
        this.InverseRotor = new int[26];
        int index;
        char c;        
        
        for (int i = 0; i < rotor.length(); i++) {
            c = rotor.charAt(i);
            index = (int) c - 65;
            DirectRotor[index] = i;
            InverseRotor[i] = index;
        }
        this.offset = 0;
    }

    public Rotore(String rotor, int offset) {
        this.DirectRotor = new int[26];
        this.InverseRotor = new int[26];
        int index;
        char c;        
        
        for (int i = 0; i < rotor.length(); i++) {
            c = rotor.charAt(i);
            index = (int) c - 65;
            DirectRotor[index] = i;
            InverseRotor[i] = index;
        }
        this.offset = offset;
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
        return "Rotore{" + "DirectRotor=" + dir + ", InverseRotor=" + inv + '}';
    }
    
    
    
    public int  translate(int value, boolean direction) {
        if (direction) {
            value = DirectRotor[(value - offset + 26) % 26];
        } else {
            value = InverseRotor[(value - offset + 26) % 26]; 
        }
        return value;
    }

    public static void main(String[] args) {
        String s = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        Rotore r = new Rotore(s);

        System.out.println(r.toString());

        int input = 0;
        int output = r.translate(input, true);
        System.out.println(output);

        output = r.translate(input, false);
        System.out.println(output);

        r.rotate();
        System.out.println(r.toString());

        output = r.translate(input, true);
        System.out.println(output);

        output = r.translate(input, false);
        System.out.println(output);
        
        r.rotate();
        System.out.println(r.toString());

        output = r.translate(input, true);
        System.out.println(output);

        output = r.translate(input, false);
        System.out.println(output);
    }
}
