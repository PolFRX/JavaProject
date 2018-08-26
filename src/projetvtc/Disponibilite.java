package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fihier
import java.io.IOException; //Pour gérer les exceptions de fichier
import java.time.LocalDateTime; //Pour utiliser des dates et des heures

public class Disponibilite {

    private LocalDateTime jdispo1; //Date et heure de début de la disponibilite
    private LocalDateTime jdispo2; //Date et heure de fin de la disponibilite
    private String position; //Lieu de la disponibilité

    //Constructeur pour une Disponibilite
    public Disponibilite(int jour, int mois, int annee, int heure1, int minutes1, int heure2, int minutes2, String position) {
        this.jdispo1 = LocalDateTime.of(annee, mois, jour, heure1, minutes1);
        this.jdispo2 = LocalDateTime.of(annee, mois, jour, heure2, minutes2);
        this.position = position;
    }

    //Constructeur utilisé pour charger une disponibilité
    public Disponibilite(LocalDateTime jdispo1, LocalDateTime jdispo2, String position) {
        this.jdispo1 = jdispo1;
        this.jdispo2 = jdispo2;
        this.position = position;
    }

    @Override
    public String toString() { //On redéfinit la méthode toString
        return "Disponibilite: " + "Début de dispo=" + jdispo1 + ", Fin de dispo=" + jdispo2 + ", position=" + position + "\n";
    }

    //Méthode pour sauvegarder une disponibilité dans un fichier
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write(jdispo1 + System.lineSeparator());
        fich.write(jdispo2 + System.lineSeparator());
        fich.write(position + System.lineSeparator());
    }

    //Méthode pour comparer une disponibilité et une commande pour voir si la commande peut être associée à cette disponibilité
    public boolean equalCommande(Commande C) {
        int B1 = (C.getDate()).compareTo(jdispo1); //On compare la date de la commande à celle du début de la disponibilité
        int B2 = (C.getDate()).compareTo(jdispo2); //On compare la date de la commande à celle de fin de la disponibilité
        Boolean B3 = position.equals(C.getLieuD()); //On regarde si les lieux de départ de la commande et de la disponibilité sont les mêmes
        if ((B1 >= 0) && (B2 <= 0) && (B3)) { //Si la date de la commande est comprise entre les deux dates de disponibilité et que le lieu est le même
            return true;
        } else {
            return false;
        }
    }

    public LocalDateTime getJdispo1() {
        return jdispo1;
    }

    public LocalDateTime getJdispo2() {
        return jdispo2;
    }

    public String getPosition() {
        return position;
    }
}
