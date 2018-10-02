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

    private int[] StartPos;
    private Scambiatore Plugboard;
    private Riflettore Reflector;

    public Configuration() {
    }

    private int[] Rot;

    public int[] getRot() {
        return Rot;
    }

    public int[] getStartPos() {
        return StartPos;
    }

    public Scambiatore getPlugboard() {
        return Plugboard;
    }

    public Riflettore getReflector() {
        return Reflector;
    }

    public void setRot(String[] Rot) {
        int temp[] = new int[Rot.length];
        for (int i = 0; i < Rot.length; i++) {
            temp[i] = Integer.parseInt(Rot[i]);
        }
        this.Rot = temp;
    }

    public void setStartPos(String[] StartPos) {
        int temp[] = new int[StartPos.length];
        for (int i = 0; i < StartPos.length; i++) {
            temp[i] = Integer.parseInt(StartPos[i]);
        }
        this.StartPos = temp;
    }

    public void setPlugboard(String[] Plugboard) {
        this.Plugboard = new Scambiatore(Plugboard);
    }

    public void setReflector(String[] Reflector) {
        this.Reflector = new Riflettore(Reflector);
    }
}
