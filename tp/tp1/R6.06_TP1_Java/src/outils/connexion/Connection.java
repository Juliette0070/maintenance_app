/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outils.connexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrateur
 */
public class Connection extends Thread{
    
    private Object lerecepteur;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public Connection(Socket socket, Object lerecepteur){
        this.lerecepteur=lerecepteur;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
                System.out.println("erreur creation canal de sortie : " + ex);
                System.exit(0);
        }
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
                System.out.println("erreur creation canal d'entree : " + ex);
                System.exit(0);
        }
        start();
        ((controleur.Controle)lerecepteur).SetConnection(this);
    }
    
    public void run(){
        boolean inOk = true;
        Object reception;
        
        while(inOk == true)
        {
            try {
                reception = in.readObject();
                ((controleur.Controle)lerecepteur).getLejeu().Reception(this, reception);
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog (null,"Ordinateur distant déconnecté");
                inOk = false; 
                ((controleur.Controle)lerecepteur).getLejeu().Deconnection(this);
                
                try {
                    in.close();
                } 
                catch (IOException ex1) {
                    System.out.println("erreur fermeture canal : " + ex);
                }
            } 
            catch (ClassNotFoundException ex) {
                System.out.println("erreur de classe : " + ex);
                System.exit(0);
            }
        }
        
    }
           
    public synchronized void envoi(Object unobj)
    {
        try {
            this.out.reset();
            out.writeObject(unobj);
            out.flush();
        } catch (IOException ex) {
            System.out.println("Erreur sur objet OUT.");
        }
    }
}
