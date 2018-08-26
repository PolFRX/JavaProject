package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fcihier
import java.io.IOException; //Pour gérer les exceptions des fichiers

public class MotoLuxe extends Moto { //hérite de la classe Moto

    //Constructeur
    public MotoLuxe(int vitesse, int nbplaces) {
        super(vitesse, nbplaces);
    }

    //Méthode pour sauvegarder une MotoLuxe
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write("6" + System.lineSeparator()); //Pour signifier qu'il s'agit d'une voiture et donc pour utiliser le bon constructeur lors du chargement
        super.sauvegarder(fich);
    }
}
