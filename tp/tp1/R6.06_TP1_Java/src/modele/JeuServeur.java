/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Controle;
import controleur.Global;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import javax.swing.JOptionPane;
import outils.connexion.Connection;

/**
 *
 * @author Administrateur
 */
public class JeuServeur extends Jeu implements Global {

    private ArrayList<Mur> lesmurs = new ArrayList<Mur>();
    private ArrayList<Joueur> lesjoueursdanslordre = new ArrayList<Joueur>();
    private Hashtable<Connection,Joueur> lesjoueurs = new Hashtable<Connection,Joueur>();
    
    public JeuServeur(Controle controle) {
        ctrl = controle ;
        Label.nbLabel = 0;
    }

    @Override
    public void SetConnection(Connection connect) {
        lesjoueurs.put(connect, new Joueur(this));
       
    }

    @Override
    public void Reception(Connection connect, Object info) {
       // System.out.println(info);
        String laphrase;
        String[] infos = ((String)info).split(SEPARE);
        switch (Integer.parseInt(infos[0]))
        {
            case PSEUDO :   ctrl.EvenementModele(this, "envoi panel murs",lesmurs);
                             for(Joueur unjoueur : lesjoueursdanslordre)
                             {
                                 super.envoi(connect,unjoueur.getLabel());
                                 super.envoi(connect,unjoueur.getMessage());
                                 super.envoi(connect,unjoueur.getBoulet().getLabel());
                             }
                            lesjoueurs.get(connect).initPerso(infos[1], Integer.parseInt(infos[2]), lesjoueurs, lesmurs);
                            lesjoueursdanslordre.add(lesjoueurs.get(connect));
                            laphrase = "*** " + infos[1] + " vient de se connecter ***";
                            ctrl.EvenementModele(this, "ajout phrase",laphrase);
                            break;
                
            case CHAT : laphrase = lesjoueurs.get(connect).getPseudo() + " > " + infos[1];
                        ctrl.EvenementModele(this, "ajout phrase",laphrase);
                        break;
            case ACTION : if (lesjoueurs.get(connect).EstMort()==false) lesjoueurs.get(connect).action(Integer.parseInt(infos[1]), lesjoueurs, lesmurs);
                        break ;
        }
    }

    @Override
    public void Deconnection(Connection connect) {
        lesjoueurs.get(connect).departJoueur();
        lesjoueurs.remove(connect);
    }
    
    public void constructionMurs()
    {
        for (int i=0 ; i <NBMURS ; i++)
        {
            lesmurs.add(new Mur());
                        
            ctrl.EvenementModele(this,"ajout mur",lesmurs.get(i).getLabel().getjLabel());
        }
    }
    
    public void nouveauLabelJeu (Label lbl)
    {
        ctrl.EvenementModele(this,"ajout joueur",lbl.getjLabel());
    }
    
    public void envoi (Object info)
    {
        for( Connection key : lesjoueurs.keySet())
        {
           super.envoi(key, info);
        }
    }
    
}
