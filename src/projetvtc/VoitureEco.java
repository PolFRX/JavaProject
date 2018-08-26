package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers

public class VoitureEco extends Voiture { //hérite de la classe Voiture

    //Constructeur
    public VoitureEco(boolean gdcoffre, boolean pmr, int vitesse, int nbplaces) {
        super(gdcoffre, pmr, vitesse, nbplaces);
    }

    //on saucegarde tous les attributs
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write("1" + System.lineSeparator()); //Pour signifier qu'il s'agit d'une voiture et donc pour utiliser le bon constructeur lors du chargement
        super.sauvegarder(fich);
    }
}
