package PFiches;

import java.io.IOException; //Pour gérer les erreurs de fichier
import javax.swing.JOptionPane; //pour afficher des messages
import projetvtc.Conducteur; //pour gérer des conducteurs
import projetvtc.ExErreur; //pour gérer différents types d'erreurs
import projetvtc.ExVide;
import projetvtc.MotoEco; //Pour gérer tous les différents types de véhicule
import projetvtc.MotoLuxe;
import projetvtc.MotoMedium;
import projetvtc.VoitureEco;
import projetvtc.VoitureLuxe;
import projetvtc.VoitureMedium;

public class FVéhicule extends javax.swing.JDialog {

    private Conducteur C; //Pour stocker le conducteur qui utilise cette page
    private int choix = -1; //Choix qui déterminera quel type de véhicule a été choisi
    private int vitesse; //Variables qui contiendront les infos sur le véhicule
    private int NbPlace;
    private boolean GrandCoffre;
    private boolean Pmr;
    private String nom; //Variables qui contiendront les infos du conducteur
    private String mail;
    private String tel;
    private String Permis;
    private boolean assurance;
    private int cartegrise;
    private String mdp;
    private int mode = -1; //Pour déterminer dans quel mode on est sur la page. mode 1: inscription d'un conducteur, mode 0: modification du véhicule d'un conducteur existant déjà

    public FVéhicule(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lVitesse.setVisible(false); //on rend invisibles les composants tant que l'utilisateur n'a pas choisi un type de véhicule
        lNbPlace.setVisible(false);
        lGrandCoffre.setVisible(false);
        lPmr.setVisible(false);
        tfVitesse.setVisible(false);
        cbNbPlace.setVisible(false);
        cbGrandCoffre.setVisible(false);
        cbPmr.setVisible(false);
    }

    //Pour récupérer les infos d'un conducteur s'inscrivant et qui doit rentrer les infos de son véhicule
    public void inscription(String nom, String mail, String tel, String permis, boolean assurance, int cartegrise, String mdp) {
        bRetour.setVisible(false); //On rend invisible le bouton retour
        this.nom = nom; //on affecte les informations du conducteur aux variables correspondantes
        this.mail = mail;
        this.tel = tel;
        this.Permis = permis;
        this.assurance = assurance;
        this.cartegrise = cartegrise;
        this.mdp = mdp;
        cbTypeVehic.removeAllItems(); //On enlève tous les items de la combo box typeVehic
        if (Permis.equals("B")) { //Si il a un permis voiture on affiche les types de voitures
            cbTypeVehic.addItem("Voiture Economique");
            cbTypeVehic.addItem("Voiture Medium");
            cbTypeVehic.addItem("Voiture de Luxe");
        } else if (Permis.equals("moto")) { //Si il a un permis moto on affiche les types de motos
            cbTypeVehic.addItem("Moto Economique");
            cbTypeVehic.addItem("Moto Medium");
            cbTypeVehic.addItem("Moto de Luxe");
        }
        mode = 1; //on sélectionne le mode inscription
    }

    public void initCond(Conducteur C) { //Pour récupérer le conducteur voulant changer son véhicule
        bRetour.setVisible(true); //On affiche le bouton retour
        this.C = C;
        Permis = C.getPermis(); //on récupère le type de permis du conducteur
        cbTypeVehic.removeAllItems(); //On enlève tous les items de la combo box typeVehic
        if (Permis.equals("B")) { //Si il a un permis voiture on affiche les types de voitures
            cbTypeVehic.addItem("Voiture Economique");
            cbTypeVehic.addItem("Voiture Medium");
            cbTypeVehic.addItem("Voiture de Luxe");
        } else if (Permis.equals("moto")) { //Si il a un permis moto on affiche les types de motos
            cbTypeVehic.addItem("Moto Economique");
            cbTypeVehic.addItem("Moto Medium");
            cbTypeVehic.addItem("Moto de Luxe");
        }
        mode = 0; //On sélectionne le mode modification de véhicule
    }

    private void changerVehic() throws NumberFormatException { //Pour modifier son véhicule
        vitesse = Integer.valueOf(tfVitesse.getText()); //on récupère la vitesse et on la convertit en int
        NbPlace = Integer.valueOf("" + cbNbPlace.getSelectedItem()); //on récupère le nombre de place et on le convertit en int
        if (Permis.equals("B")) { //S'il a un permis voiture
            switch (choix) { //Selon quel type de voiture a été choisi
                case 0: //Voiture économique
                    GrandCoffre = cbGrandCoffre.isSelected();
                    Pmr = cbPmr.isSelected();
                    VoitureEco VEco = new VoitureEco(GrandCoffre, Pmr, vitesse, NbPlace); //On créé un nouveau véhicule
                    C.modifierVehic(VEco); //On affecte ce véhicule au conducteur
                    break;
                case 1: //voiture médium
                    GrandCoffre = cbGrandCoffre.isSelected();
                    Pmr = cbPmr.isSelected();
                    VoitureMedium VMedium = new VoitureMedium(GrandCoffre, Pmr, vitesse, NbPlace); //On créé un nouveau véhicule
                    C.modifierVehic(VMedium); //On affecte ce véhicule au conducteur
                    break;
                case 2: //voiture d eluxe
                    GrandCoffre = cbGrandCoffre.isSelected();
                    Pmr = cbPmr.isSelected();
                    VoitureLuxe VLuxe = new VoitureLuxe(GrandCoffre, Pmr, vitesse, NbPlace); //On créé un nouveau véhicule
                    C.modifierVehic(VLuxe); //On affecte ce véhicule au conducteur
                    break;
                default: //S'il ya une erreur d'index
                    JOptionPane.showMessageDialog(this, "Erreur.");
            }
        } else if (Permis.equals("moto")) { //S'il a un permis moto
            switch (choix) { //Selon quel type de moto a été choisi
                case 0: //Moto économique
                    MotoEco MEco = new MotoEco(vitesse, NbPlace); //On créé un nouveau véhicule
                    C.modifierVehic(MEco); //On affecte ce véhicule au conducteur
                    break;
                case 1:
                    MotoMedium MMed = new MotoMedium(vitesse, NbPlace); //On créé un nouveau véhicule
                    C.modifierVehic(MMed); //On affecte ce véhicule au conducteur
                    break;
                case 2:
                    MotoLuxe MLuxe = new MotoLuxe(vitesse, NbPlace); //On créé un nouveau véhicule
                    C.modifierVehic(MLuxe); //On affecte ce véhicule au conducteur
                    break;
                default: //S'il ya une erreur d'index
                    JOptionPane.showMessageDialog(this, "Erreur.");
            }
        }
    }

    private void inscrireVehic() throws ExErreur { //pour s'inscrire avec un véhicule
        vitesse = Integer.valueOf(tfVitesse.getText()); //on récupère la vitesse et on la convertit en int
        NbPlace = Integer.valueOf("" + cbNbPlace.getSelectedItem()); //on récupère le nombre de place et on le convertit en int
        if (Permis.equals("B")) { //S'il a un permis voiture
            switch (choix) { //Selon quel type de voiture a été choisi
                case 0: //voiture économique
                    GrandCoffre = cbGrandCoffre.isSelected();
                    Pmr = cbPmr.isSelected();
                    VoitureEco VEco = new VoitureEco(GrandCoffre, Pmr, vitesse, NbPlace); //On créé un nouveau véhicule
                    C = (((Accueil) this.getParent()).getSite()).inscrireConducteur(Permis, VEco, cartegrise, assurance, nom, mail, mdp, tel); //on créé un nouveau conducteur avec toutes les infos et on récupère le conducteur créé
                    break;
                case 1: //Voiture médium
                    GrandCoffre = cbGrandCoffre.isSelected();
                    Pmr = cbPmr.isSelected();
                    VoitureMedium VMedium = new VoitureMedium(GrandCoffre, Pmr, vitesse, NbPlace); //On créé un nouveau véhicule
                    C = (((Accueil) this.getParent()).getSite()).inscrireConducteur(Permis, VMedium, cartegrise, assurance, nom, mail, mdp, tel); //on créé un nouveau conducteur avec toutes les infos et on récupère le conducteur créé
                    break;
                case 2: //voiture de luxe
                    GrandCoffre = cbGrandCoffre.isSelected();
                    Pmr = cbPmr.isSelected();
                    VoitureLuxe VLuxe = new VoitureLuxe(GrandCoffre, Pmr, vitesse, NbPlace); //On créé un nouveau véhicule
                    C = (((Accueil) this.getParent()).getSite()).inscrireConducteur(Permis, VLuxe, cartegrise, assurance, nom, mail, mdp, tel); //on créé un nouveau conducteur avec toutes les infos et on récupère le conducteur créé
                    break;
                default: //S'il y a une erreur d'index
                    JOptionPane.showMessageDialog(this, "Erreur.");
            }
        } else if (Permis.equals("moto")) { //S'il a un permis moto
            switch (choix) { //Selon quel type de moto a été choisi
                case 0: //Moto économique
                    MotoEco MEco = new MotoEco(vitesse, NbPlace); //On créé un nouveau véhicule
                    C = (((Accueil) this.getParent()).getSite()).inscrireConducteur(Permis, MEco, cartegrise, assurance, nom, mail, mdp, tel); //on créé un nouveau conducteur avec toutes les infos et on récupère le conducteur créé
                    break;
                case 1: //moto médium
                    MotoMedium MMed = new MotoMedium(vitesse, NbPlace); //On créé un nouveau véhicule
                    C = (((Accueil) this.getParent()).getSite()).inscrireConducteur(Permis, MMed, cartegrise, assurance, nom, mail, mdp, tel); //on créé un nouveau conducteur avec toutes les infos et on récupère le conducteur créé
                    break;
                case 2: //moto de luxe
                    MotoLuxe MLuxe = new MotoLuxe(vitesse, NbPlace); //On créé un nouveau véhicule
                    C = (((Accueil) this.getParent()).getSite()).inscrireConducteur(Permis, MLuxe, cartegrise, assurance, nom, mail, mdp, tel); //on créé un nouveau conducteur avec toutes les infos et on récupère le conducteur créé
                    break;
                default: //S'il y a une erreur d'index
                    JOptionPane.showMessageDialog(this, "Erreur.");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bRetour = new javax.swing.JButton();
        cbTypeVehic = new javax.swing.JComboBox<>();
        lVitesse = new javax.swing.JLabel();
        lNbPlace = new javax.swing.JLabel();
        cbNbPlace = new javax.swing.JComboBox<>();
        tfVitesse = new javax.swing.JFormattedTextField();
        lGrandCoffre = new javax.swing.JLabel();
        cbGrandCoffre = new javax.swing.JCheckBox();
        lPmr = new javax.swing.JLabel();
        cbPmr = new javax.swing.JCheckBox();
        lType = new javax.swing.JLabel();
        bValider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        bRetour.setText("Retour");
        bRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRetourActionPerformed(evt);
            }
        });

        cbTypeVehic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTypeVehicActionPerformed(evt);
            }
        });

        lVitesse.setText("Vitesse:");

        lNbPlace.setText("Nombre de places:");

        tfVitesse.setEditable(false);
        tfVitesse.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        tfVitesse.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tfVitesse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVitesseActionPerformed(evt);
            }
        });

        lGrandCoffre.setText("Grand coffre:");

        cbGrandCoffre.setText("Oui");

        lPmr.setText("Personne à mobilité réduite:");

        cbPmr.setText("Oui");

        lType.setText("Type de véhicule:");

        bValider.setText("Valider");
        bValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bValiderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lType)
                        .addGap(30, 30, 30)
                        .addComponent(cbTypeVehic, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bRetour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lNbPlace)
                                    .addComponent(lVitesse)
                                    .addComponent(lGrandCoffre)
                                    .addComponent(lPmr))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfVitesse)
                                    .addComponent(cbNbPlace, 0, 88, Short.MAX_VALUE)
                                    .addComponent(cbGrandCoffre)
                                    .addComponent(cbPmr))))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(bValider, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTypeVehic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lType))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lVitesse)
                    .addComponent(tfVitesse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNbPlace)
                    .addComponent(cbNbPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lGrandCoffre)
                    .addComponent(cbGrandCoffre))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPmr)
                    .addComponent(cbPmr))
                .addGap(28, 28, 28)
                .addComponent(bValider)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(bRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRetourActionPerformed
        this.setVisible(false); //on rend invisible cette page puis on affiche la page précédente
        ((Accueil) getParent()).getFicheProfilCond().setVisible(true);
    }//GEN-LAST:event_bRetourActionPerformed

    private void tfVitesseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVitesseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfVitesseActionPerformed

    private void cbTypeVehicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTypeVehicActionPerformed
        //Quand on choisie un certain type de véhicule
        try {
            choix = cbTypeVehic.getSelectedIndex(); //on récupère le choix de véhicule
            if (Permis.equals("B")) { //s'il a un permis voiture
                cbNbPlace.removeAllItems(); //On enlève tous les items de la combo box qui contient les nombre de places
                cbNbPlace.addItem("1"); //on affiche les nombres de places qu'on peut avoir dans une voiture
                cbNbPlace.addItem("3");
                cbNbPlace.addItem("4");
                cbNbPlace.addItem("6");
                lVitesse.setVisible(true); //On affiche les composants spécifiques à une voiture
                lNbPlace.setVisible(true);
                lGrandCoffre.setVisible(true);
                lPmr.setVisible(true);
                tfVitesse.setVisible(true);
                cbNbPlace.setVisible(true);
                cbGrandCoffre.setVisible(true);
                cbPmr.setVisible(true);
                switch (choix) { //Selon le choix du type de voiture on change la vitesse
                    case 0: //Voiture économique
                        tfVitesse.setText("70");
                        break;
                    case 1: //Voiture médium
                        tfVitesse.setText("80");
                        break;
                    case 2: //Voiture de luxe
                        tfVitesse.setText("90");
                        break;
                    case -1: //Rien de sélectionné
                        break; //ne rien faire
                    default: //S'il y a une erreur d'index
                        throw new ExVide("Erreur switch type véhicule");
                }
            } else if (Permis.equals("moto")) { //S'il a un permis moto
                cbNbPlace.removeAllItems(); //On enlève tous les items de la combo box qui contient les nombre de places
                cbNbPlace.addItem("1"); //on affiche les nombres de places qu'on peut avoir sur une moto
                lVitesse.setVisible(true); //on affiche seulement les composants spécifiques à une moto
                lNbPlace.setVisible(true);
                lGrandCoffre.setVisible(false);
                lPmr.setVisible(false);
                tfVitesse.setVisible(true);
                cbNbPlace.setVisible(true);
                cbGrandCoffre.setVisible(false);
                cbPmr.setVisible(false);
                switch (choix) { //Selon le choix du type de voiture on change la vitesse
                    case 0: //Moto économique
                        tfVitesse.setText("90");
                        break;
                    case 1: //Moto médium
                        tfVitesse.setText("100");
                        break;
                    case 2: //Moto de luxe
                        tfVitesse.setText("110");
                        break;
                    case -1: //Rien de sélectionné
                        break; //ne rien faire
                    default: //S'il y a une erreur d'index
                        throw new ExVide("Erreur switch type véhicule");
                }
            }
        } catch (ExVide ex) { //S'il y a une erreur d'index
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_cbTypeVehicActionPerformed

    private void bValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bValiderActionPerformed
        try {
            if (mode == 0) { //Modification de profil
                changerVehic(); //On appelle la fonction qui va changer le véhicule du conducteur
                JOptionPane.showMessageDialog(this, "Vous avez bien enregistré votre véhicule.");
            } else if (mode == 1) { //Inscription d'un nouveau conducteur
                inscrireVehic(); //on appelle la fonction qui va inscrire le conducteur avec son véhciule
                JOptionPane.showMessageDialog(this, "Vous vous êtes bien inscrit.\nBienvenue " + nom + ".");
                (((Accueil) this.getParent()).getFicheCond()).initCond(C); //on envoie les infos du conducteur dans la page de l'espace conducteur
                (((Accueil) getParent()).getFicheProfilCond()).initCond(C); //on envoie les infos du conducteur dans la page de profil du conducteur
                (((Accueil) getParent()).getFicheAvisCond()).initCond(C); //on envoie les infos du conducteur dans la page des avis
                (((Accueil) this.getParent()).getFicheCond()).setVisible(true); //on affiche la page del'espace conducteur
                this.setVisible(false); //On rend invisible cette page
            }
        } catch (ExErreur ex) { //Si l'adresse entrée par le conducteur est déjà utilisée
            JOptionPane.showMessageDialog(this, "Cette adresse mail est déjà utilisée.\nVeuillez changer.");
            (((Accueil) this.getParent()).getFicheI()).setVisible(true); //on affiche la page d'inscription
            this.setVisible(false); //on rend invisible cette page
        }
    }//GEN-LAST:event_bValiderActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            (((Accueil) getParent()).getSite()).sauvegarde(); //on sauvegarde toutes les infos en gérant les possibles erreurs
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
            java.util.logging.Logger.getLogger(FVéhicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FVéhicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FVéhicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FVéhicule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FVéhicule dialog = new FVéhicule(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bRetour;
    private javax.swing.JButton bValider;
    private javax.swing.JCheckBox cbGrandCoffre;
    private javax.swing.JComboBox<String> cbNbPlace;
    private javax.swing.JCheckBox cbPmr;
    private javax.swing.JComboBox<String> cbTypeVehic;
    private javax.swing.JLabel lGrandCoffre;
    private javax.swing.JLabel lNbPlace;
    private javax.swing.JLabel lPmr;
    private javax.swing.JLabel lType;
    private javax.swing.JLabel lVitesse;
    private javax.swing.JFormattedTextField tfVitesse;
    // End of variables declaration//GEN-END:variables
}
