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

    private final char[] directRotor;
    private final char[] inverseRotor;
    private int offset;

    public Rotore(String rotor) {
        this.directRotor = new char[26];
        this.inverseRotor = new char[26];
        int index;
        char c;

        for (int i = 0; i < rotor.length(); i++) {
            c = rotor.charAt(i);
            index = (int) c - 65;

            directRotor[i] = c;
            inverseRotor[index] = (char) (i + 65);
        }
        this.offset = 0;
    }

    public Rotore(String rotor, int offset) {
        this.directRotor = new char[26];
        this.inverseRotor = new char[26];
        int index;
        char c;

        for (int i = 0; i < rotor.length(); i++) {
            c = rotor.charAt(i);
            index = (int) c - 65;

            directRotor[i] = c;
            inverseRotor[index] = (char) (i + 65);
        }
        this.offset = offset;
    }

    public char[] getDirectRotor() {
        return directRotor;
    }

    public char[] getInverseRotor() {
        return inverseRotor;
    }

    public int getOffset() {
        return offset;
    }

    public void rotate() {
        offset++;
        if (offset == 26) {
            offset = 0;
        }
    }

    public void rotate(int offset) {
        this.offset += offset;
        if (this.offset == 26) {
            this.offset = 0;
        }
    }

    public char translate(char character, boolean direction) {
        if (character == ' '){
            return character;
        }
        if (direction) {
            int index = (((int) character) - 65 + offset) % 26;
            return directRotor[index];
        } else {
            int index = (((int) character) - 65) % 26;
            index = inverseRotor[index]-offset;
            if (index < 65){
                index += 26;
            }
            return (char) index;
        }
    }

    public static void main(String[] args) {
        String s = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        Rotore r = new Rotore(s);

        System.out.println(r.directRotor);

        int offset = r.getOffset();
        System.out.println(offset);

        char input = 'A';
        char output = r.translate(input, true);
        System.out.println(output);

        input = 'E';
        output = r.translate(input, false);
        System.out.println(output);

        r.rotate();

        output = r.translate(input, true);
        System.out.println(output);

        output = r.translate(input, true);
        System.out.println(output);
    }
}
