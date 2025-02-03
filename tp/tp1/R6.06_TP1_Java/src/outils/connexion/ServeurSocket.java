/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class ServeurSocket extends Thread {

    private Object lerecepteur;
    private ServerSocket serversocket;
    
    public ServeurSocket(Object lerecepteur, int port) {
        this.lerecepteur=lerecepteur;
        try {
            serversocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("erreur création socket serveur : " + ex);
            System.exit(0);
        }
        start();
    }
    
    @Override
    public void run()
    {
        Socket socket;
        
        while (true){
            try {
                System.out.println("serveur en attente...");
                socket = serversocket.accept();
                System.out.println("un client s'est connecté");
                Connection connect = new Connection(socket,lerecepteur);
            } catch (IOException ex) {
                System.out.println("erreur écoute socket serveur : " + ex);
                System.exit(0);
            }
            
        }
        
    }
}
