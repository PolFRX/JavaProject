/*
    Lagarenne Laure
    de Font-Réaulx Pol
    Groupe Cb
    Projet VTC
 */
package PFiches;

import java.io.IOException; //Pour gérer les erreurs de fichier
import javax.swing.JOptionPane; //Pour afficher des messages
import projetvtc.Site; //Pour accéder aux fonctions de SIte

public class Accueil extends javax.swing.JFrame {

    private FInscription ficheI; //fiche d'inscription
    private FConnexion ficheC; //fiche de connexion
    private FPassagerEspace ficheP; //fiche de l'espace du passager
    private FConducteurEspace ficheCond; //fiche de l'espace du conducteur
    private FDispo ficheDispo; //fiche des disponibilités du conducteur
    private FProfilCond ficheProfilCond; //fiche du profil du conducteur
    private FVéhicule ficheVehic; //fiche pour inscription ou changement de véhicule
    private FAvisCond ficheAvisCond; //fiche des avis sur le conducteur
    private FProfilPass ficheProfilPass; //fiche du profil du passager
    private FCommande ficheCommande; //fiche pour commander un véhicule
    private FEspaceCommande ficheEspaceCommande; //fiche pour accéder aux anciennes commandes et donner des avis
    private Site site; //instance de site

    public Accueil() {
        try {
            initComponents();
            ficheI = new FInscription(this, false); //On initialise les fiches
            ficheC = new FConnexion(this, false);
            ficheP = new FPassagerEspace(this, false);
            ficheCond = new FConducteurEspace(this, false);
            ficheDispo = new FDispo(this, false);
            ficheProfilCond = new FProfilCond(this, false);
            ficheVehic = new FVéhicule(this, false);
            ficheAvisCond = new FAvisCond(this, false);
            ficheProfilPass = new FProfilPass(this, false);
            ficheCommande = new FCommande(this, false);
            ficheEspaceCommande = new FEspaceCommande(this, false);
            site = new Site(); //on créé une instance de Site qui va contenir notre site
            site.charger(); //On charge les informations contenues dans les fichiers
        } catch (IOException ex) { //s'il ya une erreur dans le chargement au niveau du fichier
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    //Pour récupérer les fiches
    public FProfilPass getFicheProfilPass() {
        return ficheProfilPass;
    }

    public FEspaceCommande getFicheEspaceCommande() {
        return ficheEspaceCommande;
    }

    public FCommande getFicheCommande() {
        return ficheCommande;
    }

    public FProfilCond getFicheProfilCond() {
        return ficheProfilCond;
    }

    public FAvisCond getFicheAvisCond() {
        return ficheAvisCond;
    }

    public FVéhicule getFicheVehic() {
        return ficheVehic;
    }

    public FDispo getFicheDispo() {
        return ficheDispo;
    }

    public FInscription getFicheI() {
        return ficheI;
    }

    public Site getSite() { //Pour récupérer le site
        return site;
    }

    public FConnexion getFicheC() {
        return ficheC;
    }

    public FPassagerEspace getFicheP() {
        return ficheP;
    }

    public FConducteurEspace getFicheCond() {
        return ficheCond;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lBienvenue = new javax.swing.JLabel();
        bConnexion = new javax.swing.JButton();
        bInscrire = new javax.swing.JButton();
        bQuitter = new javax.swing.JButton();
        lImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lBienvenue.setText("        Bienvenue sur Uber 2.0");

        bConnexion.setText("Connexion");
        bConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConnexionActionPerformed(evt);
            }
        });

        bInscrire.setText("S'Inscrire");
        bInscrire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInscrireActionPerformed(evt);
            }
        });

        bQuitter.setText("Quitter");
        bQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuitterActionPerformed(evt);
            }
        });

        lImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PImages/vtc.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(lBienvenue, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bInscrire, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 213, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lImage, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lBienvenue, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bInscrire, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConnexionActionPerformed
        this.setVisible(false); //On cache l'accueil et on affiche la page de connexion
        ficheC.setVisible(true);
    }//GEN-LAST:event_bConnexionActionPerformed

    private void bQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuitterActionPerformed
        try {
            site.sauvegarde(); //On sauvegarde le site
        } catch (IOException ex) { //S'il y a une erreur de fichier
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) { //S'il y a une erreur inconnue
            JOptionPane.showMessageDialog(this, "Erreur inconnue de sauvegarde.");
        } finally {
            System.exit(0); //On quitte le programme
        }

    }//GEN-LAST:event_bQuitterActionPerformed

    private void bInscrireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInscrireActionPerformed
        this.setVisible(false);
        ficheI.setVisible(true);
    }//GEN-LAST:event_bInscrireActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            site.sauvegarde(); //On sauvegarde en gérant les erreurs
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur inconnue de sauvegarde.");
        } finally {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Accueil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConnexion;
    private javax.swing.JButton bInscrire;
    private javax.swing.JButton bQuitter;
    private javax.swing.JLabel lBienvenue;
    private javax.swing.JLabel lImage;
    // End of variables declaration//GEN-END:variables
}
