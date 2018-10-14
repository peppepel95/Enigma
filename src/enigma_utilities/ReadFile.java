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

/**
 *
 * @author peppepel95
 */
public abstract class ReadFile {
    
    public void readText(String file, String type) throws FileNotFoundException, IOException, IllegalArgumentException {
        String result;
        boolean eof = false;
        FileReader filein = new FileReader(file + ".txt");
        BufferedReader br = new BufferedReader(filein);
        while (!eof) {
            result = br.readLine();
            if (result == null) {
                eof = true;
            }
            else {
                this.hook(type,result);
            }
        }
    }

    public abstract void hook(String type, String result) throws IllegalArgumentException;
}
