package projetvtc;

import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers
import java.time.LocalDateTime; //Pour utiliser des dates et des heures
import java.util.Iterator; //Pour utiliser des itérateurs
import java.util.LinkedList; //Pour utiliser des LinkedList

public class Passager extends Utilisateur { //hérite de la classe Utilisateur

    private boolean CB; //Si le passager paye en carte de crédit ou sinon en espèce
    private LinkedList<Commande> listeCommande; //Liste contenant toutes les commandes que le passager a passé

    //Constructeur
    public Passager(boolean CB, String nom, String mail, String mdp, String nbtel) {
        super(nom, mail, mdp, nbtel); //On appelle le constructeur de la classe mère
        this.listeCommande = new LinkedList<Commande>(); //On initialise la liste
        this.CB = CB;
    }

    @Override
    public String toString() { //On redéfinit la méthode toString
        return "Passager: " + super.toString() + "CB=" + CB + ", listeCommande=" + listeCommande + "\n";
    }

    //Méthode pour sauvegarder les informations d'un passager
    public void sauvegarder(FileWriter fich) throws IOException {
        super.sauvegarder(fich); //On appelle la méthode mère
        fich.write(CB + System.lineSeparator());
        int nbreCommandes = listeCommande.size(); //On attribut à "nbreCommandes" le nombre de commande contenues dans la liste
        fich.write(nbreCommandes + System.lineSeparator()); //On enregistre dans le fichier le nombre de commandes dans la liste pour pouvoir charger toutes les commandes par la suite
        Iterator<Commande> it = listeCommande.iterator(); //On créé un itérateur pour parcourir la liste de commandes
        for (int i = 0; i < nbreCommandes; i++) //On parcourt la liste en sauvegardant chaque élément dans le fichier
        {
            Commande x = it.next();
            x.sauvegarder(fich);
        }
    }

    public void ajouterCommande(Commande C) { //pour ajouter une commande toute faite
        listeCommande.add(C); //On ajoute la commande à la liste
    }

    //Pour ajouter une commande à partir de toutes les informations
    public void ajouterCommande(Vehicule typeVehic, double cout, double temps, String lieuD, String lieuA, LocalDateTime dt, String mailC) {
        Commande x = new Commande(typeVehic, cout, temps, lieuD, lieuA, dt, mailC); //On construit un objet Commande
        listeCommande.add(x); //On l'ajoute à la liste
    }

    public Commande getLastCommande() { //Retourne la dernière commande du passager
        Commande A = listeCommande.get(listeCommande.size() - 1);
        return A;
    }

    public boolean isCB() {
        return CB;
    }

    public LinkedList<Commande> getListeCommande() {
        return listeCommande;
    }

    public void modifierInfos(boolean CB, String nom, String mail, String mdp, String nbtel) {
        this.CB = CB;
        super.modifierInfos(nom, mail, mdp, nbtel);
    }
}
