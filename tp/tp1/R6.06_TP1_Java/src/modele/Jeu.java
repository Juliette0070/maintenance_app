/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 *
 * @author Administrateur
 */
public abstract class Jeu {
    
    protected Controle ctrl;
    
    public abstract void SetConnection(Connection connect);
    public abstract void Reception(Connection connect, Object info);

    public void envoi(Connection connect, Object info)
    {
        connect.envoi(info);
    }
    
     public abstract void Deconnection(Connection connect);   
    
}
