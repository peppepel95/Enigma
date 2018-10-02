/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_component;

import java.util.Arrays;


/**
 *
 * @author peppepel95
 */
public class Configuration {
    private int[] Rot;
    private int[] StartPos;
    private String[] Plugboard;
    private String[] Reflector;

    public Configuration() {
    }
    

    public void setRot(String[] Rot) {
        int temp[] = new int[Rot.length]; 
        for (int i = 0; i < Rot.length; i++) {
            temp[i] = Integer.parseInt(Rot[i]);
        }
        this.Rot = temp;
    }

    @Override
    public String toString() {
        return "Configuration{" + "Rot=" + Arrays.toString(Rot) + ", StartPos=" + Arrays.toString(StartPos) + ", Plugboard=" + Arrays.toString(Plugboard) + ", Reflector=" + Arrays.toString(Reflector) + '}';
    }

    public void setStartPos(String[] StartPos) {
        int temp[] = new int[StartPos.length];
        for (int i = 0; i < StartPos.length; i++) {
            temp[i] = Integer.parseInt(StartPos[i]);
        }
        this.StartPos = temp;
    }

    public void setPlugboard(String[] Plugboard) {
        this.Plugboard = Plugboard;
    }

    public void setReflector(String[] Reflector) {
        this.Reflector = Reflector;
    }
}
