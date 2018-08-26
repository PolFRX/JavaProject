package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers

public class Vehicule {

    private int vitesse; //Vitesse du véhicule
    private int nbplaces; //Nombre de places du véhicule

    //Constructeur
    public Vehicule(int vitesse, int nbplaces) {
        this.vitesse = vitesse;
        this.nbplaces = nbplaces;
    }

    //Pour sauvegarder les informations du véhicule dans un fichier
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write(vitesse + System.lineSeparator());
        fich.write(nbplaces + System.lineSeparator());
    }

    @Override
    public String toString() { //On redéfinit la méthode toString
        return "vitesse=" + vitesse + ", nbplaces=" + nbplaces;
    }

    public int getVitesse() { //Retourne la vitesse
        return vitesse;
    }

    public int getNbplaces() { //Retourne le nombre de places
        return nbplaces;
    }

    public boolean equals(Vehicule V) { //On redéfinit la méthode equals pour voir si deux type de véhicule sont pareils
        if (this.vitesse == V.getVitesse() && this.nbplaces == V.getNbplaces()) {
            return true;
        } else {
            return false;
        }
    }
}
