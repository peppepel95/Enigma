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
    private final HashMap<Character,Character> reflectMap;
    String[]  reflector;
    
    public Riflettore(String[]  reflector) {
        this.reflectMap = new HashMap<>();
        this.reflector = reflector;
    }
    
    public char reflect(char character) {
        if (reflectMap.isEmpty()) {
            this.getMap();
        }
        if (reflectMap.containsKey(character))
            return reflectMap.get(character);
        return character;
    }

    private void getMap() {
        for (String reflector1 : reflector) {
            this.reflectMap.put(reflector1.charAt(0), reflector1.charAt(1));
            this.reflectMap.put(reflector1.charAt(1), reflector1.charAt(0));
        }
    }
}
