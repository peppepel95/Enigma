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
    private final String cifrario;

    public Rotore(String result) {
        this.cifrario = result;
    }

    @Override
    public String toString() {
        return "Rotore{" + "cifrario=" + cifrario + '}';
    }
    
}
