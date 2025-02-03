/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Global;
import static controleur.Global.BLESSE;
import static controleur.Global.LEPAS;
import static controleur.Global.MARCHE;
import static controleur.Global.NBETATSBLESSE;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import outils.connexion.Connection;

/**
 *
 * @author Administrateur
 */
public class Attaque extends Thread implements Global {
    
    private Joueur attaquant ;
    private JeuServeur jsrv ;
    private ArrayList<Mur> lesmurs = new ArrayList<Mur>();
    private Hashtable<Connection,Joueur> lesjoueurs = new Hashtable<Connection,Joueur>();

    public Attaque(Joueur attaquant, JeuServeur jsrv, ArrayList<Mur> lesbriques, Hashtable<Connection,Joueur> lesgamers) {
        this.attaquant = attaquant;
        this.jsrv = jsrv;
        lesmurs = lesbriques;
        lesjoueurs = lesgamers;
        start();
    }
    
    @Override
    public void run()
    {
        attaquant.affiche(MARCHE,1);
        Boule laboule = attaquant.getBoulet();
        int orientation = attaquant.getSens();
        laboule.getLabel().getjLabel().setVisible(true);
        Joueur victime = null;
        
        do
        {
            if (orientation==GAUCHE) laboule.setPosX(laboule.getPosX()-LEPAS);
            else laboule.setPosX(laboule.getPosX()+LEPAS);
            
            laboule.getLabel().getjLabel().setBounds(laboule.getPosX(), laboule.getPosY(), L_BOULE, H_BOULE);
            pause (10,0);
           
            jsrv.envoi(laboule.getLabel());
             
            victime = toucheJoueur();
        }
        while(laboule.getPosX()>0 && laboule.getPosX()< L_ARENE && !toucheMur() && victime==null );
        
        
        if (victime != null && victime.EstMort()==false)
        {
            victime.PerteVie();
            attaquant.GainVie();
            
            for (int i=1; i<=NBETATSBLESSE ; i++)
            {
                 victime.affiche(BLESSE,i);
                 jsrv.envoi(victime.getLabel());
                 pause(200,0);
            }
            
            if (victime.EstMort())
            {
                for (int i=1; i<=NBETATSMORT ; i++)
                {
                 victime.affiche(MORT,i);
                 jsrv.envoi(victime.getLabel());
                 pause(200,0);
                }
            }
            else
            {
                victime.affiche(MARCHE,1);
            }
            
            attaquant.affiche(MARCHE,1);
            jsrv.envoi(victime.getLabel());
        }
        
        laboule.getLabel().getjLabel().setVisible(false);
        jsrv.envoi(laboule.getLabel());
        
        
    }
    
    public void pause(long milli, int nano)
    {
        try {
            Thread.sleep(milli, nano);
        } catch (InterruptedException ex) {
            Logger.getLogger(Attaque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean toucheMur()
    {
         for(Mur unmur : lesmurs)
         {
           if (attaquant.getBoulet().toucheObjet(unmur)== true)
                   return true;
         }
        return false;
    }
    
    private Joueur toucheJoueur()
    {
        for(Joueur unjoueur : lesjoueurs.values())
        {
            if (attaquant.getBoulet().toucheObjet(unjoueur)== true)
               return unjoueur;
        }
        return null;
    }
    
    
}
