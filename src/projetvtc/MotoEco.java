package projetvtc;

import java.io.FileWriter; //Bibliothèque ppur écrire dans un fichier
import java.io.IOException; //pour gérer les exceptions des fichiers

public class MotoEco extends Moto { //hérite de la classe Moto

    //Constructeur d'une MotoEco
    public MotoEco(int vitesse, int nbplaces) {
        super(vitesse, nbplaces); //On appelle le constructeur de la classe mère
    }

    //Méthode pour sauvegarder une MotoEco
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write("4" + System.lineSeparator()); //Pour signifier qu'il s'agit d'une voiture et donc pour utiliser le bon constructeur lors du chargement
        super.sauvegarder(fich);
    }
}
