/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Label;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 *
 * @author Administrateur
 */
public class Controle implements Global {

    private EntreeJeu frmEntreeJeu ;
    private Jeu lejeu;
    private Arene frmArene ;
    private ChoixJoueur frmChoixJoueur;
    private Connection connection;
        
    public Controle() {
        frmEntreeJeu = new EntreeJeu(this);
        frmEntreeJeu.setVisible(true);
    }
    
    public void EvenementVue(Object frm, Object info)
    {
        if (frm instanceof EntreeJeu)
            EvenementEntreeJeu(info);
        if (frm instanceof ChoixJoueur)
            EvenementChoixJoueur(info);
        if (frm instanceof Arene)
            EvenementArene(info);
    }
    
    public void EvenementEntreeJeu(Object info) {
        if ("serveur".equals((String)info))
        {
            ServeurSocket srv_sckt = new ServeurSocket(this,PORT);
            lejeu = new JeuServeur(this);
            frmEntreeJeu.dispose();
            frmArene = new Arene("serveur",this);
            ((JeuServeur)lejeu).constructionMurs();
            frmArene.setVisible(true);
            
        }
        else 
        {
            ClientSocket clnt_sckt = new ClientSocket((String) info, PORT, this);
            if (clnt_sckt.isConnexionOK())
            {
                 lejeu = new JeuClient(this);
                 lejeu.SetConnection(connection);
                 frmArene = new Arene("client",this);
                 frmArene.setVisible(false);
                 frmChoixJoueur = new ChoixJoueur(this);
                 frmChoixJoueur.setVisible(true);
                 frmEntreeJeu.dispose();                 
            }
        }
   }

    public Jeu getLejeu() {
        return lejeu;
    }
    
    public void EvenementChoixJoueur(Object info)
    {
        ((modele.JeuClient)lejeu).envoi(info);
        frmChoixJoueur.dispose();
        frmArene.setVisible(true);
    }
    
    public void SetConnection (Connection connection)
    {
        this.connection = connection;
        if (lejeu instanceof JeuServeur) lejeu.SetConnection(connection);
    }
    
    public void EvenementModele(Object unjeu, String ordre, Object info)
    {
       if (unjeu instanceof JeuServeur) EvenementJeuServeur(ordre,info);
       if (unjeu instanceof JeuClient) EvenementJeuClient(ordre,info);
       
    }
    
    public void EvenementJeuServeur(String ordre, Object info)
    {
       if ("ajout mur".equals(ordre)) frmArene.ajoutMur((JLabel)info);
       if ("envoi panel murs".equals(ordre)) ((JeuServeur)lejeu).envoi(connection, frmArene.getJpnMurs());
       if ("ajout joueur".equals(ordre)) frmArene.ajoutJoueur((JLabel)info);
       if ("ajout phrase".equals(ordre)) 
       {
           frmArene.ajoutChat((String)info);
           ((JeuServeur)lejeu).envoi(frmArene.getTxtChat().getText());
       }
       
    }
    
    public void EvenementJeuClient(String ordre, Object info)
    {
        if ("envoi panel murs".equals(ordre)) frmArene.ajoutPanelMurs((JPanel)info);
        if ("ajout joueur".equals(ordre)) frmArene.ajoutModifJoueur( ((Label)info).getNumLabel(), ((Label)info).getjLabel());
        if ("remplace chat".equals(ordre))  frmArene.remplaceChat((String)info);    
    }
    
    public void EvenementArene(Object info)
    {
        ((JeuClient)lejeu).envoi(info);       
    }
            
}
