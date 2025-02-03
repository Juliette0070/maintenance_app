/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.Controle;
import controleur.Global;
import static controleur.Global.FONDCHOIX;
import java.awt.Cursor;
import static java.awt.Frame.DEFAULT_CURSOR;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrateur
 */
public class ChoixJoueur extends JFrame implements Global {
   
    private int numPerso ;
    private Controle controle;
    /**
     * Creates new form ChoixJoueur
     */
    public ChoixJoueur(Controle ctrl) {
        super();
        controle = ctrl;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        numPerso = 1;   
        affichePerso();
        txtPseudo.requestFocus();
    }

    
                       
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            lblSuivant =(javax.swing.JLabel)java.beans.Beans.instantiate(getClass().getClassLoader(), "vue.ChoixJoueur_lblSuivant");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        try {
            lblPrecedent =(javax.swing.JLabel)java.beans.Beans.instantiate(getClass().getClassLoader(), "vue.ChoixJoueur_lblPrecedent");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        try {
            txtPseudo =(javax.swing.JTextField)java.beans.Beans.instantiate(getClass().getClassLoader(), "vue.ChoixJoueur_txtPseudo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        try {
            lblGo =(javax.swing.JLabel)java.beans.Beans.instantiate(getClass().getClassLoader(), "vue.ChoixJoueur_lblGo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        try {
            lblPersonnage =(javax.swing.JLabel)java.beans.Beans.instantiate(getClass().getClassLoader(), "vue.ChoixJoueur_lblPersonnage");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Choix du joueur");
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(408, 302));
        setMinimumSize(new java.awt.Dimension(408, 302));
        setPreferredSize(new java.awt.Dimension(408, 302));
        getContentPane().setLayout(null);

        lblSuivant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSuivantMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSuivantMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSuivantMouseExited(evt);
            }
        });
        getContentPane().add(lblSuivant);
        lblSuivant.setBounds(280, 90, 70, 70);

        lblPrecedent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrecedentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPrecedentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPrecedentMouseExited(evt);
            }
        });
        getContentPane().add(lblPrecedent);
        lblPrecedent.setBounds(60, 90, 70, 70);

        txtPseudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPseudoActionPerformed(evt);
            }
        });
        getContentPane().add(txtPseudo);
        txtPseudo.setBounds(150, 225, 112, 20);

        lblGo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblGoMouseExited(evt);
            }
        });
        getContentPane().add(lblGo);
        lblGo.setBounds(330, 210, 60, 50);
        getContentPane().add(lblPersonnage);
        lblPersonnage.setBounds(152, 58, 109, 128);

        jLabel2.setIcon(new ImageIcon(FONDCHOIX));
        jLabel2.setText("jLabel2");
        jLabel2.setMaximumSize(new java.awt.Dimension(400, 275));
        jLabel2.setMinimumSize(new java.awt.Dimension(400, 275));
        jLabel2.setPreferredSize(new java.awt.Dimension(400, 275));
        jLabel2.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 400, 275);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblPrecedentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrecedentMouseClicked
        // TODO add your handling code here:
        lblPrecedent_Clic();
        affichePerso();
    }//GEN-LAST:event_lblPrecedentMouseClicked

    private void lblSuivantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSuivantMouseClicked
        // TODO add your handling code here:
        lblSuivant_Clic();
        affichePerso();
    }//GEN-LAST:event_lblSuivantMouseClicked

    private void txtPseudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPseudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPseudoActionPerformed

    private void lblGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoMouseClicked
        // TODO add your handling code here:
        lblGo_Clic();
    }//GEN-LAST:event_lblGoMouseClicked

    private void lblGoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoMouseEntered
        // TODO add your handling code here:
        souris_doigt();
    }//GEN-LAST:event_lblGoMouseEntered

    private void lblGoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoMouseExited
        // TODO add your handling code here:
        souris_normale();
    }//GEN-LAST:event_lblGoMouseExited

    private void lblSuivantMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSuivantMouseEntered
        // TODO add your handling code here:
         souris_doigt();
    }//GEN-LAST:event_lblSuivantMouseEntered

    private void lblSuivantMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSuivantMouseExited
        // TODO add your handling code here:
        souris_normale();
    }//GEN-LAST:event_lblSuivantMouseExited

    private void lblPrecedentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrecedentMouseEntered
        // TODO add your handling code here:
         souris_doigt();
    }//GEN-LAST:event_lblPrecedentMouseEntered

    private void lblPrecedentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrecedentMouseExited
        // TODO add your handling code here:
        souris_normale();
    }//GEN-LAST:event_lblPrecedentMouseExited

    private void lblPrecedent_Clic() {
        switch (numPerso)
        {
            case 1 : numPerso = 3; break ;
            case 2 : numPerso = 1; break ;
            case 3 : numPerso = 2; break ;
        }
    }
    
    private void lblGo_Clic() {
        if ("".equals(txtPseudo.getText()))
        {
            JOptionPane.showMessageDialog (null,"Le pseudo est obligatoire"); 
              txtPseudo.requestFocus();
        }
        else
        {
            controle.EvenementVue(this, PSEUDO+SEPARE+txtPseudo.getText()+SEPARE+numPerso);
        }        
    }
    
    private void lblSuivant_Clic() {
        switch (numPerso)
        {
            case 1 : numPerso = 2; break ;
            case 2 : numPerso = 3; break ;
            case 3 : numPerso = 1; break ;
        }
    }
    
    private void affichePerso() {
        lblPersonnage.setIcon(new ImageIcon(PERSO+numPerso+"stand.png"));
    }
    
    private void souris_normale(){
        Cursor souris = new Cursor(DEFAULT_CURSOR);
        setCursor(souris);
    }
    
    private void souris_doigt(){
        Cursor souris = new Cursor(HAND_CURSOR);
        setCursor(souris);
    }
    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblGo;
    private javax.swing.JLabel lblPersonnage;
    private javax.swing.JLabel lblPrecedent;
    private javax.swing.JLabel lblSuivant;
    private javax.swing.JTextField txtPseudo;
    // End of variables declaration//GEN-END:variables
}
