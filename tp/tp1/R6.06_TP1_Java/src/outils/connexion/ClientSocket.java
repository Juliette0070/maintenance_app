/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outils.connexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrateur
 */
public class ClientSocket {

    private boolean connexionOK;
    public ClientSocket(String ip, int port, Object lerecepteur) {
        connexionOK = false;
        try {
            Socket socket ;
            socket = new Socket(ip,port);
            System.out.println("La connexion au serveur a reussi.");
            connexionOK = true ; 
            Connection connect = new Connection(socket,lerecepteur);
            
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog (null,"Serveur non disponible");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog (null,"Problème d'entree/sortie");
        }
        
    }

    public boolean isConnexionOK() {
        return connexionOK;
    }
    
    
    
}
