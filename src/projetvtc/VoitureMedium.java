package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers

public class VoitureMedium extends Voiture { //hérite de la classe Voiture

    //Constructeur
    public VoitureMedium(boolean gdcoffre, boolean pmr, int vitesse, int nbplaces) {
        super(gdcoffre, pmr, vitesse, nbplaces);
    }

    //Méthode pour sauvegarder tous ses attributs
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write("2" + System.lineSeparator()); //Pour signifier qu'il s'agit d'une voiture et donc pour utiliser le bon constructeur lors du chargement
        super.sauvegarder(fich);
    }
}
