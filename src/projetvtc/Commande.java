package projetvtc;

import java.io.FileWriter; //Bilbiothèque pour écrire dans un fichier
import java.io.IOException; //Pour pouvoir gérer les exceptions dues aux fichiers
import java.time.LocalDateTime; //Pour manipuler des dates et des heures
import java.util.Random;

public class Commande {

    private static final int max = 60; //Constantes pour la fonction random
    private static final int min = 10;
    private Vehicule typeVehic; //Le type de véhicule voulu pour la commande
    private double cout; //Le coût de la course (déterminé à la fin de la course)
    private double temps; //Le temps mis par la course (déterminé à la fin de la course)
    private String lieuD; //lieu de départ
    private String lieuA; //lieu d'arrivé
    private LocalDateTime date; //Date et heure de départ
    private String mailConducteur; //Conducteur lié à la commande

    //Constructeur pour une commande que le passager réserve
    public Commande(Vehicule typeVehic, String lieuD, String lieuA, LocalDateTime date, String mailConducteur) {
        this.typeVehic = typeVehic;
        this.lieuD = lieuD;
        this.lieuA = lieuA;
        this.date = date; //Pour choisir la date de réservation
        this.mailConducteur = mailConducteur;
    }

    //Constructeur pour le chargement pour récupérer toutes les infos
    public Commande(Vehicule typeVehic, double cout, double temps, String lieuD, String lieuA, LocalDateTime date, String mailConducteur) { //Pour charger la commande depuis le fichier texte
        this.typeVehic = typeVehic;
        this.cout = cout;
        this.temps = temps;
        this.lieuD = lieuD;
        this.lieuA = lieuA;
        this.date = date;
        this.mailConducteur = mailConducteur;
    }

    @Override
    public String toString() { //On redéfinit la méthode toString
        return "Commande: " + "typeVehic=" + typeVehic + ", cout=" + cout + ", temps=" + temps + ", lieuD=" + lieuD + ", lieuA=" + lieuA + ", date=" + date + "\n";
    }

    public void sauvegarder(FileWriter fich) throws IOException { //On sauvegarde tous les attributs de la commande
        typeVehic.sauvegarder(fich); //On appelle la fonction mère
        fich.write(date + System.lineSeparator());
        fich.write(cout + System.lineSeparator());
        fich.write(temps + System.lineSeparator());
        fich.write(lieuD + System.lineSeparator());
        fich.write(lieuA + System.lineSeparator());
        fich.write(mailConducteur + System.lineSeparator());
    }

    public double calculerCout() { //Fonction qui calculera le coût de la course (sera fait avec le conversationnel)
        if ((typeVehic instanceof MotoEco) || (typeVehic instanceof VoitureEco)) {
            cout = temps * 1; //1€ par minute
        } else if ((typeVehic instanceof MotoMedium) || (typeVehic instanceof VoitureMedium)) {
            cout = temps * 2; //2€ par minute
        } else if ((typeVehic instanceof MotoLuxe) || (typeVehic instanceof VoitureLuxe)) {
            cout = temps * 3; //3€ par minute
        }
        return cout;
    }

    public double calculerTemps() { //Fonction qui calculera le temps de la course (sera fait avec le conversationnel)
        Random rand = new Random(); //Nouvel objet de la classe random
        temps = rand.nextInt(max - min) + min; //Renvoie un nombre entre min et max
        return temps;
    }

    public Vehicule getTypeVehic() { //Renvoit le type de véhicule de la commande
        return typeVehic;
    }

    public String getLieuD() { //renvoit le lieu de départ de la commande
        return lieuD;
    }

    public String getLieuA() {//renvoit le lieu d'arrivé de la commande
        return lieuA;
    }

    public String getMailConducteur() {
        return mailConducteur;
    }

    public LocalDateTime getDate() { //renvoit la date de départ de la commande
        return date;
    }

    public double getCout() { //renvoit le coût de la commande
        return cout;
    }

    public boolean equals(Commande C) { //On redéfinit la méthode equals pour ne renvoyer true que si les attributs principaux sont égaux
        if (this.date.equals(C.getDate()) && this.lieuD.equals(C.getLieuD()) && this.lieuA.equals(C.getLieuA()) && this.typeVehic.equals(C.getTypeVehic())) {
            return true;

        } else {
            return false;
        }
    }

}
