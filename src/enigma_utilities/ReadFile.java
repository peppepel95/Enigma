/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author peppepel95
 */
public abstract class ReadFile {

    public void readText(String file, String type) {
        String result;
        boolean eof = false;
        FileReader filein;
        try {
            filein = new FileReader(file + ".txt");
            BufferedReader br = new BufferedReader(filein);
            while (!eof) {
                result = br.readLine();
                if (result == null) {
                    eof = true;
                } else {
                    this.hook(type, result);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "File non trovato!");
        } catch (IOException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Impossibile leggere dal file!");
        } catch (IllegalArgumentException ex) {
            System.err.println(Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Tipo di operazione non consentita!");
        }

    }

    public abstract void hook(String type, String result) throws IllegalArgumentException;
}
