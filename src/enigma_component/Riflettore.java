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
    private final HashMap<Integer,Integer> reflectMap;
    String[]  reflector;
    
    public Riflettore(String[]  reflector) {
        this.reflectMap = new HashMap<>();
        this.reflector = reflector;
    }
    
    public int reflect(int character) {
        if (reflectMap.isEmpty()) {
            this.getMap();
        }
        if (reflectMap.containsKey(character))
            return reflectMap.get(character);
        return character;
    }

    private void getMap() {
        for (String reflector1 : reflector) {
            this.reflectMap.put((int)(reflector1.charAt(0) - 65), (int)(reflector1.charAt(1) - 65));
            this.reflectMap.put((int)(reflector1.charAt(1) - 65), (int)(reflector1.charAt(0) - 65));
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
    public String toString(){
        String s = "";
        for (String temp: reflector){
            s += temp + " ";
        }
        return s;
    }
}
