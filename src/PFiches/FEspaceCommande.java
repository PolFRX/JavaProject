package PFiches;

import java.io.IOException; //Pour gérer les erreurs de fichier
import java.time.LocalDateTime; //Pour utiliser des dates
import java.util.Iterator; //Pour parcourirles linked list
import java.util.LinkedList; //Pour utiliser des linked list
import javax.swing.DefaultListModel; //Pour pouvoir afficher des listes de models dans les JList
import javax.swing.JOptionPane; //Pour afficher des messages
import projetvtc.Avis; //pour gérer des avis
import projetvtc.Commande; //pour gérer des commandes
import projetvtc.Conducteur; //Pour gérer des conducteurs
import projetvtc.ExErreur; //Pour gérer des erreurs de différents types
import projetvtc.ExVide;
import projetvtc.Passager; //Pour gérer des passagers

public class FEspaceCommande extends javax.swing.JDialog {

    private Passager P; //Pour stocker le passager qui utilise la page
    private Conducteur C; //Pour stocker le conducteur à qui le passager a passé une commande en particulier
    private Commande Com; //Pour stocker la commande
    private Avis A; //Pour stocker l'avsi associé à la commande
    private LinkedList<Commande> listeCommande; //Pour stocker la liste de toutes les commandes passées du passager
    private boolean existe; //variable pour savoir si un avis existe déjà ou s'il faut le créer

    public FEspaceCommande(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listeCommande = new LinkedList<>(); //On initialise la linked list
    }

    public void initPass(Passager P) { //Pour récupérer le passager utilisant la page
        this.P = P;
        chargerCommande(); //On appelle la focntion pour charger les commandes du passager
    }

    public void chargerCommande() {
        Commande C; //Pour stocker temporairement chaque commande
        String lieuD, lieuA, cout; //pour stocker les informations sur une commande
        listeCommande = P.getListeCommande(); //On récupère la liste des commandes du passager
        DefaultListModel dlm = new DefaultListModel(); //On définit une nouvelle liste de models qui s'affichera dans la JList
        Iterator<Commande> it = listeCommande.iterator(); //Pour parcourir la liste des commandes
        while (it.hasNext()) { //Tant que la liste n'est pas vide
            C = it.next(); //On récupère chaque commande l'une après l'autre
            lieuD = C.getLieuD(); //On récupère les informations de cette commande
            lieuA = C.getLieuA();
            cout = String.valueOf(C.getCout());
            dlm.addElement(cout + "€ , de " + lieuD + " à " + lieuA); //On ajoute un texte décrivant la commande dans liste de models
        }
        jlCommande.setModel(dlm); //On attribut la liste de models à la JList des commandes
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jlCommande = new javax.swing.JList<>();
        lMessage = new javax.swing.JLabel();
        lCommande = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taCommentaire = new javax.swing.JTextArea();
        lNote = new javax.swing.JLabel();
        lCommentaire = new javax.swing.JLabel();
        bValider = new javax.swing.JButton();
        tfNote = new javax.swing.JFormattedTextField();

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

        jlCommande.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlCommandeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlCommande);

        lMessage.setText("Anciennes commandes");

        lCommande.setText("Commandes:");

        taCommentaire.setColumns(20);
        taCommentaire.setRows(5);
        jScrollPane2.setViewportView(taCommentaire);

        lNote.setText("Note:");

        lCommentaire.setText("Commentaire:");

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
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bRetour, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bValider)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfNote, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCommentaire, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lNote, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lMessage)
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNote)
                            .addComponent(tfNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(lCommentaire)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bValider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(bRetour))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lCommande)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRetourActionPerformed
        this.setVisible(false); //On rend invisible cette page puis on affiche la page précédente
        ((Accueil) getParent()).getFicheP().setVisible(true);
        jlCommande.setSelectedIndex(-1); //On réinitialise l'index de la List
    }//GEN-LAST:event_bRetourActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            (((Accueil) getParent()).getSite()).sauvegarde(); //On sauvegarde toutes les infos en gérant les possibles erreurs
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur inconnue de sauvegarde.");
        } finally {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosed

    private void jlCommandeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlCommandeValueChanged
        //Quand l'index la JList des commandes change
        try {
            A = null; //On efface l'avis précédent
            int choix = jlCommande.getSelectedIndex(); //On récupère l'index qui montre quelle commande a été choisi par le passager
            if (choix != -1) //S'il y a un avis de sélectionné
            {
                Com = listeCommande.get(choix); //On récupère la commande choisie
                String mail = Com.getMailConducteur(); //On récupère le mail du conducteur associé à la commande
                C = (((Accueil) getParent()).getSite()).chercherCond(mail); //On cherche quel conducteur a cette adresse mail et on récupère ses infos
                A = C.chercherAvis(P.getMail(), Com.getDate()); //On cherche l'avis correspondant au passager (à son mail) et correspondant bien à la date de la commande et on récupère ses infos. S'il n'y a aucun avis sur cette commande il y a une exception ExVide
                tfNote.setText(String.valueOf(A.getNote())); //On attribut la note de l'avis récupéré au text field correspondant
                taCommentaire.setText(A.getCommentaire()); //On attribut le commenatire de l'avis récupéré au text field correspondant

                existe = true;
            }
        } catch (ExVide ex) { //Si aucun avis n'existe encore pour cette commande
            tfNote.setText("");
            taCommentaire.setText("Veuillez entrer un avis.");
            existe = false; //On change la variable existe pour pouvoir créer un nouvel avis 
        } catch (ExErreur ex) { //Si aucun conducteur ne coorespond à l'adresse mail et que donc il y a une erreur
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jlCommandeValueChanged

    private void bValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bValiderActionPerformed
        try {
            LocalDateTime date; //Pour stocker la date de commande
            String mail; //Pour stocker le mail du passager
            int note; //Pour stocker la note donnée
            String commentaire; //Pour stocker le commentaire donné
            if ((tfNote.getText()).equals("") || (taCommentaire.getText()).equals("")) { //si un champ n'a pas été rempli
                throw new ExVide("Veuillez remplir les champs.");
            }
            note = Integer.valueOf(tfNote.getText()); //On récupère la note et on la transforme en int
            if (note > 20 || note < 0) { //Si la note rentrée n'est pas comprise entre 0 et 20
                throw new NumberFormatException(); //On appelle un des catch
            }
            commentaire = taCommentaire.getText(); //On récupère le commentaire
            mail = P.getMail(); //On récupère le mail du passager
            date = Com.getDate(); //On récupère la date de la commande
            if (!existe) { //Si l'avis n'existe pas et qu'on doit donc le créer
                C.ajouterAvis(commentaire, note, mail, date); //On ajoute un nouvel avis àla liste d'avis du conducteur
                JOptionPane.showMessageDialog(this, "Avis ajouté."); //Message de confirmation
            } else { //S'il y a déjà un avis sur cette commande mais qu'on veut le changer
                A.changerAvis(commentaire, note); //On change l'avis
                C.calculerMoyenne(); //on recalcule la moyenne du conducteur pour prendre en compte la nouvelle note
                JOptionPane.showMessageDialog(this, "Avis modifié."); //Message de confirmation
            }

        } catch (ExVide ex) { //Si les champs sont vides
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (NumberFormatException ex) { //Si le passager n'a pas rentré un nombre dans le champ ou que celui-ci n'est pas compris entre 0 et 20
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier compris entre 0 et 20 pour la note.");
        }
    }//GEN-LAST:event_bValiderActionPerformed

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
            java.util.logging.Logger.getLogger(FEspaceCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FEspaceCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FEspaceCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FEspaceCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FEspaceCommande dialog = new FEspaceCommande(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlCommande;
    private javax.swing.JLabel lCommande;
    private javax.swing.JLabel lCommentaire;
    private javax.swing.JLabel lMessage;
    private javax.swing.JLabel lNote;
    private javax.swing.JTextArea taCommentaire;
    private javax.swing.JFormattedTextField tfNote;
    // End of variables declaration//GEN-END:variables
}
