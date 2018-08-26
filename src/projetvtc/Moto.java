package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers

public class Moto extends Vehicule { //hérite de la classe véhicule

    public Moto(int vitesse, int nbplaces) { //Constructeur pour une moto
        super(vitesse, nbplaces); //On appelle le constructeur de la classe mère
    }

    //Fonction pour sauvegarder une moto
    public void sauvegarder(FileWriter fich) throws IOException { //Gère les exceptions venant des fichiers
        super.sauvegarder(fich); //On appelle la fonction sauvegarder la classe mère
    }

    @Override
    public String toString() { //On redéfinit la méthode toString
        return "Moto: " + super.toString() + "\n";
    }
}
