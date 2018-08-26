package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers
import java.util.Iterator; //Pour utiliser un itérateur

public class Utilisateur {

    private String nom; //le nom de l'utilisateur
    private String mail; //son mail
    private String mdp; //son mot de passe
    private String nbtel; //Son numéro de téléphone

    //Constructeur
    public Utilisateur(String nom, String mail, String mdp, String nbtel) {
        this.nom = nom;
        this.mail = mail;
        this.mdp = mdp;
        this.nbtel = nbtel;
    }

    @Override
    public String toString() { //On redéfinit la méthode toString
        return "nom=" + nom + ", mail=" + mail + ", mdp=" + mdp + ", nbtel=" + nbtel;
    }

    public String getNbtel() {
        return nbtel;
    }

    //On sauvegarde toutes les informations de l'utilisateur
    public void sauvegarder(FileWriter fich) throws IOException {
        fich.write(nom + System.lineSeparator());
        fich.write(mail + System.lineSeparator());
        fich.write(mdp + System.lineSeparator());
        fich.write(nbtel + System.lineSeparator());
    }

    public String getMail() { //Retourne le mail
        return mail;
    }

    public String getMdp() { //Retourne le mot de passe
        return mdp;
    }

    public String getNom() { //Retourne le nom
        return nom;
    }

    public void modifierInfos(String nom, String mail, String mdp, String nbtel) {
        this.nom = nom;
        this.mail = mail;
        this.mdp = mdp;
        this.nbtel = nbtel;
    }
}
