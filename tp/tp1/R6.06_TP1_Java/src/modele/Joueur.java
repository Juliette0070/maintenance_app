/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Global;
import static controleur.Global.BAS;
import static controleur.Global.DROITE;
import static controleur.Global.GAUCHE;
import static controleur.Global.HAUT;
import static controleur.Global.H_ARENE;
import static controleur.Global.H_MESSAGE;
import static controleur.Global.H_MUR;
import static controleur.Global.H_PERSO;
import static controleur.Global.LEPAS;
import static controleur.Global.L_ARENE;
import static controleur.Global.L_MUR;
import static controleur.Global.L_PERSO;
import static controleur.Global.NBETATSMARCHE;
import static controleur.Global.PERSO;
import static controleur.Global.TIRE;
import java.awt.Font;
import java.awt.event.KeyEvent;
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
public class Joueur extends Objet implements Global{

    private String pseudo;
    private int numPerso;
    private Label message;
    private JeuServeur jsrv;
    private int vie;
    private int sens;
    private int etape;
    private Boule boulet;
    private static final int maxvie = 10 ;
    private static final int gain = 1 ;
    private static final int perte = 2 ;
        
    public Joueur(JeuServeur j) {
        jsrv = j;
        vie = maxvie ;
        etape = 1;
        sens = 1;
    }
    
    public void GainVie()
    {
        vie = vie + gain ;
    }
    
    public void PerteVie()
    {
        vie = vie - perte ;
        if (vie < 0) vie =0;         
    }
    
    public boolean EstMort()
    {
        if (vie==0) return true;
        else return false;
    }
           
    public void departJoueur()
    {
        if (label != null)
        {
            label.getjLabel().setVisible(false);
            message.getjLabel().setVisible(false);
            boulet.getLabel().getjLabel().setVisible(false);
            jsrv.envoi(label);
            jsrv.envoi(message); 
            jsrv.envoi(boulet.getLabel());
        }
    }

    public int getEtape() {
        return etape;
    }

    public Boule getBoulet() {
        return boulet;
    }

    public int getNumPerso() {
        return numPerso;
    }

    public int getVie() {
        return vie;
    }

    public JeuServeur getJsrv() {
        return jsrv;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void initPerso(String pseudo, int numPerso, Hashtable<Connection,Joueur> lesjoueurs, ArrayList<Mur> lesmurs) {
        this.pseudo = pseudo;
        this.numPerso = numPerso;
            
        label = new Label(Label.nbLabel,new JLabel());
        label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
        label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
        jsrv.nouveauLabelJeu(label);
        
        Label.nbLabel++;
        
        message = new Label(Label.nbLabel,new JLabel());
        message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
        message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
        jsrv.nouveauLabelJeu(message);
        
        Label.nbLabel++;
         
        premierePosition(lesjoueurs,lesmurs);
        affiche(MARCHE, etape);
        
        jsrv.envoi(label);
        jsrv.envoi(message); 
        
        boulet = new Boule(jsrv);
        jsrv.envoi(boulet.getLabel());
    }

    public int getSens() {
        return sens;
    }

    public Label getMessage() {
        return message;
    }
    
    private boolean toucheJoueur(Hashtable<Connection,Joueur> lesjoueurs)
    {
        for(Joueur unjoueur : lesjoueurs.values())
        {
            if (unjoueur.equals(this)==false)
            {
                if (toucheObjet(unjoueur)== true)
                           return true;
            }
        }
        return false;
    }
    
     private boolean toucheMur(ArrayList<Mur> lesmurs)
     {
        for(Mur unmur : lesmurs)
        {
           if (toucheObjet(unmur)== true)
                   return true;
            
        }
        return false;
     }
     
     private void premierePosition (Hashtable<Connection,Joueur> lesjoueurs,ArrayList<Mur> lesmurs)
     {
         label.getjLabel().setBounds(0,0,L_PERSO,H_PERSO);
         do
         {
              posX = (int) (Math.round(Math.random()*(L_ARENE - L_PERSO)));
              posY = (int) (Math.round(Math.random()*(H_ARENE - H_PERSO - H_MESSAGE)));
              
         }while(toucheMur(lesmurs) || toucheJoueur(lesjoueurs));
         
     }
     
     public void affiche(String state, int step)
     {
         label.getjLabel().setBounds(posX, posY, L_PERSO, H_PERSO);
         label.getjLabel().setIcon(new ImageIcon(PERSO+numPerso+state+step+"d"+sens+EXTIMAGE));
         message.getjLabel().setBounds(posX-10, posY+H_PERSO, L_PERSO+10, H_MESSAGE);
         message.getjLabel().setText(pseudo + " : " + vie);
     }
    
     private int deplace(int act, int pos, int sens, int pas, int max, Hashtable<Connection,Joueur> lesjoueurs, ArrayList<Mur> lesmurs)
     {
         this.sens = sens;
         int ancpos = pos ;
         pos = pos + pas;
         
         if (pos < 0) pos = 0;
         if (pos > max) pos = max;
         
         if (act == GAUCHE || act == DROITE)
             posX = pos;
         else
             posY = pos;
         
         if (toucheMur(lesmurs) || toucheJoueur(lesjoueurs)) pos = ancpos;
         
         etape = etape + 1 ;
         if (etape > NBETATSMARCHE)  etape = 1 ;
         
         return pos;
     }
    
     public void action(int act, Hashtable<Connection,Joueur> lesjoueurs, ArrayList<Mur> lesmurs)
     {
         switch (act)
         {
             case TIRE: if (! boulet.getLabel().getjLabel().isVisible()) boulet.tireBoule(this, lesmurs, lesjoueurs);break;
             case GAUCHE: posX = deplace(act,posX,GAUCHE,-LEPAS,L_ARENE-L_PERSO,lesjoueurs, lesmurs) ;break;
             case DROITE: posX = deplace(act,posX,DROITE,LEPAS,L_ARENE-L_PERSO,lesjoueurs, lesmurs) ;break;
             case BAS: posY = deplace(act,posY,sens,LEPAS,H_ARENE-H_PERSO-H_MESSAGE,lesjoueurs, lesmurs) ;break;
             case HAUT: posY = deplace(act,posY,sens,-LEPAS,H_ARENE-H_PERSO-H_MESSAGE,lesjoueurs, lesmurs) ;break;
         }
         affiche(MARCHE,etape);
         jsrv.envoi(label);
         jsrv.envoi(message); 
     }
     
     
    
}
