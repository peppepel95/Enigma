/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_component;

import java.util.HashMap;

/**
 *
 * @author peppepel95
 */
public class Riflettore {

    private final HashMap<Integer, Integer> reflectMap;
    String[] reflector;

    public Riflettore(String[] reflector) {
        this.reflectMap = new HashMap<>();
        this.reflector = reflector;
    }

    public int reflect(int pos) {
        if (reflectMap.isEmpty()) {
            this.getMap();
        }
        if (reflectMap.containsKey(pos)) {
            return reflectMap.get(pos);
        }
        return pos;
    }

    private void getMap() {
        for (String reflector1 : reflector) {
            this.reflectMap.put(reflector1.charAt(0) - 65, reflector1.charAt(1) - 65);
            this.reflectMap.put(reflector1.charAt(1) - 65, reflector1.charAt(0) - 65);
        }
    }

    public static void main(String[] args) {
        String[] s = new String[2];
        s[0] = "AB";
        s[1] = "CD";
        Riflettore r = new Riflettore(s);
        int c = r.reflect('A');
        System.out.println(c);
        //System.out.println(r.reflectMap.toString());
    }

    @Override
    public String toString() {
        String s = "";
        String s1;
        for (String temp : reflector) {
            s += temp + " ";
        }
        s = s.substring(0, s.length() - 1);
        if (this instanceof Scambiatore)
            s1 = "Scambiatore: ";
        else
            s1 = "Riflettore: ";
        return s1 + s;
    }
}
