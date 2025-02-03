/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Controle;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import outils.connexion.Connection;

/**
 *
 * @author Administrateur
 */
public class JeuClient extends Jeu {
    
    private Connection connexion;
    
    public JeuClient(Controle controle) {
        ctrl = controle ;
    }

    @Override
    public void SetConnection(Connection connect) {
        connexion = connect;
       
    }

    @Override
    public void Reception(Connection connect, Object info) {
      if (info instanceof JPanel) ctrl.EvenementModele(this, "envoi panel murs", info);
      if (info instanceof Label) ctrl.EvenementModele(this, "ajout joueur", info);
      if (info instanceof String) ctrl.EvenementModele(this, "remplace chat", info);
    }

    @Override
    public void Deconnection(Connection connect) {
       System.exit(0);
    }

    public void envoi(Object info) {
        super.envoi(connexion, info); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
