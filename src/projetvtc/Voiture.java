package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers

public class Voiture extends Vehicule { //hérite de la classe véhicule

    private boolean gdcoffre; //Si la voiture a un grand coffre ou non
    private boolean pmr; //Si la voiture a une prise en charge de personnes à mobilité réduite ou non

    //Constructeur
    public Voiture(boolean gdcoffre, boolean pmr, int vitesse, int nbplaces) {
        super(vitesse, nbplaces); //On appelle le constructeur de la fonction mère
        this.gdcoffre = gdcoffre;
        this.pmr = pmr;
    }

    //Pour sauvegarder les informations de la voiture dans un fichier
    public void sauvegarder(FileWriter fich) throws IOException {
        super.sauvegarder(fich);
        fich.write(gdcoffre + System.lineSeparator());
        fich.write(pmr + System.lineSeparator());
    }

    @Override //On redéfinit la méthode toString
    public String toString() {
        return "Voiture: " + super.toString() + "gdcoffre=" + gdcoffre + ", pmr=" + pmr + "\n";
    }

    @Override
    public boolean equals(Object obj) { //On redéfinit la méthode equals
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voiture other = (Voiture) obj;
        if (this.gdcoffre != other.gdcoffre) {
            return false;
        }
        if (this.pmr != other.pmr) {
            return false;
        }
        if (super.equals(obj)) {
            return true;
        }
        return false;
    }
}
