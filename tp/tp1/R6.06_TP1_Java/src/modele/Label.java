/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author Administrateur
 */
public class Label implements Serializable{
    
    public static int nbLabel;
    private int numLabel ;
    private JLabel jLabel;

    public Label(int numLabel, JLabel jLabel) {
        this.numLabel = numLabel;
        this.jLabel = jLabel;
    }

    public int getNumLabel() {
        return numLabel;
    }

    public JLabel getjLabel() {
        return jLabel;
    }
    
    
    
}
