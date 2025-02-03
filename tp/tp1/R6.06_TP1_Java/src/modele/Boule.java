/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import controleur.Global;
import static controleur.Global.PERSO;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import outils.connexion.Connection;

/**
 *
 * @author Administrateur
 */
public class Boule extends Objet implements Global {

    private JeuServeur jsrv;
    
    public Boule(JeuServeur j) {
        jsrv = j;
        
        label = new Label(Label.nbLabel,new JLabel());
        label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
        label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
        label.getjLabel().setBounds(0,0,L_BOULE,H_BOULE);
        label.getjLabel().setIcon(new ImageIcon(BOULE));
        label.getjLabel().setVisible(false);
                
        Label.nbLabel++;     
        
        jsrv.nouveauLabelJeu(label);
    }
    
    public void tireBoule(Joueur attaquant, ArrayList<Mur> lesmurs,Hashtable<Connection,Joueur> lesjoueurs)
    {
        if (attaquant.getSens() == GAUCHE) attaquant.getBoulet().setPosX(attaquant.getPosX() - L_BOULE - 1); 
        else attaquant.getBoulet().setPosX(attaquant.getPosX() + L_PERSO + 1);
        
        attaquant.getBoulet().setPosY(attaquant.getPosY() + (H_PERSO / 2));
        
        Attaque  tire = new Attaque(attaquant,jsrv,lesmurs, lesjoueurs);
        
    }
    
    
}
