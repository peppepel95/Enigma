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
public class Scambiatore extends Riflettore{
    public Scambiatore(String[] reflector) {
        super(reflector);
    }
    
    public int Swap(int pos) {
        return super.reflect(pos);
    }
    
    public static void main(String[] args) {
        String[] str = new String[2];
        str[0] = "AB";
        str[1] = "CD";
        Scambiatore s = new Scambiatore(str);
        int c = s.Swap('A');
        System.out.println(c);
        //System.out.println(s.reflectMap.toString());
    }
}
