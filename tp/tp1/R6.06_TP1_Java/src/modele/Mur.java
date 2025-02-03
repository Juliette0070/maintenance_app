/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Global;
import static controleur.Global.H_MUR;
import static controleur.Global.L_MUR;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 *
 * @author Administrateur
 */
public class Mur extends Objet implements Global {

    public Mur() {
        posX = (int) (Math.round(Math.random()*(L_ARENE - L_MUR)));
        posY = (int) (Math.round(Math.random()*(H_ARENE - H_MUR)));
        label = new Label(-1,new JLabel()) ;
        
        label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
        label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
        label.getjLabel().setBounds(posX,posY,L_MUR,H_MUR);
        label.getjLabel().setIcon(new ImageIcon(MUR));
        
    }
    
    
}
