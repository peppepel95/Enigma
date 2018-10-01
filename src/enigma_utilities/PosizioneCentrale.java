/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma_utilities;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * Utility to set Frames in the center of the screen.
 *
 * @author ivan
 */
public class PosizioneCentrale {

    /**
     * Return an instance of Point, which is the position of the center of the
     * screen.
     *
     * @return an instance of Point, which is the position of the center of the
     * screen
     */
    public static Point getPosizioneCentrale() {
        Point p = null;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return p = new Point((screenSize.width / 2) - (800 / 2), (screenSize.height / 2) - (600 / 2));
    }
    public static Point getLeftPosition(){
        return new Point(60,100);
    }
    public static Point getRightPosition(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((screenSize.width - (int) (screenSize.width*0.5)),(screenSize.height - (int) (screenSize.height*0.9)));
    }
}
