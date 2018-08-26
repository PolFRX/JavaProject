package projetvtc;

import java.io.FileWriter;
import java.io.IOException;

public class MotoMedium extends Moto {

    //Constructeur
    public MotoMedium(int vitesse, int nbplaces) {
        super(vitesse, nbplaces);
    }

    //Méthode pour sauvegarder une MotoMedium
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write("5" + System.lineSeparator()); //Pour signifier qu'il s'agit d'une voiture et donc pour utiliser le bon constructeur lors du chargement
        super.sauvegarder(fich);
    }
}
