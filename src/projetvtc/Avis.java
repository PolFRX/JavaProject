package projetvtc;

import java.io.FileWriter; //Bibliothèque pour pouvoir écrire dans un fichier
import java.io.IOException; //Pour pouvoir gérer les problèmes de fichiers
import java.time.LocalDateTime; //Pour pouvoir gérer des dates

public class Avis {

    private String commentaire; //Commentaire laissé par un passager
    private int note; //Note laissée par un passager
    private String mailPassager; //Mail du passager lié à l'avis
    private LocalDateTime date; //Pour associé l'avis avec la bonne commande

    public Avis(String commentaire, int note, String mailPassager, LocalDateTime date) { //Constructeur d'un avis
        this.commentaire = commentaire;
        this.note = note;
        this.mailPassager = mailPassager;
        this.date = date;
    }

    @Override
    public String toString() { //On redéfinit la méthode to String 
        return "Avis: " + "commentaire=" + commentaire + ", note=" + note + "\n";
    }

    public String getCommentaire() { //on retourne le commentaire
        return commentaire;
    }

    public void sauvegarder(FileWriter fich) throws IOException { //On enregistre chaque attributs d'un avis
        fich.write(commentaire + System.lineSeparator());
        fich.write(note + System.lineSeparator());
        fich.write(mailPassager + System.lineSeparator());
        fich.write(date + System.lineSeparator());
    }

    public int getNote() { //On retourne la note du conducteur
        return note;
    }

    public String getMailPassager() {
        return mailPassager;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void changerAvis(String commentaire, int note) { //on change les informations de l'avis
        this.commentaire = commentaire;
        this.note = note;
    }
}
