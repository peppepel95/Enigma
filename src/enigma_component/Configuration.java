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
public class Configuration {
    private int[] Rot;
    private int[] StartPos;
    private String[] Plugboard;
    private String[] Reflector;

    public void setRot(String[] Rot) {
        int[] temp = null;
        for (int i = 0; i < Rot.length; i++) {
            temp[i] = Integer.parseInt(Rot[i]);
        }
        this.Rot = temp;
    }

    public void setStartPos(String[] StartPos) {
        int[] temp = null;
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
