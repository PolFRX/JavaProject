package projetvtc;

import java.io.FileWriter; //Bibliothèque pour pouvoir écrire dans un fichier
import java.io.IOException; //Pour pouvoir gérer les exceptions de fichiers
import java.time.LocalDateTime; //Pour utiliser des dates et des heures
import java.util.Iterator; //Pour pouvoir utiliser un itérateur
import java.util.LinkedList; //pour pouvoir utiliser des LinkedList

public class Conducteur extends Utilisateur {

    private String permis; //Le type de permis
    private Vehicule typeVehic; //Le type de véhicule du conducteur
    private int cartegrise; //Le numéro de carte grise du conducteur
    private boolean attassurance; //Si le conducteur a une attestation d'assurance ou non
    private LinkedList<Avis> listeAvis; //la liste contenant tous les avis sur le conducteur
    private double moyenne; //la moyenne de toutes les notes des avis
    private LinkedList<Disponibilite> listeDisponibilite; //La liste des disponibilités du chauffeur

    //Constructeur d'un Conducteur
    public Conducteur(String permis, Vehicule typeVehic, int cartegrise, boolean attassurance, String nom, String mail, String mdp, String nbtel) {
        super(nom, mail, mdp, nbtel);
        this.permis = permis;
        this.typeVehic = typeVehic;
        this.cartegrise = cartegrise;
        this.attassurance = attassurance;
        this.listeAvis = new LinkedList<>();
        this.listeDisponibilite = new LinkedList<>();
    }

    @Override
    public String toString() { //On redéfinit la méthode toString
        return "Conducteur: " + super.toString() + "permis=" + permis + ", typeVehic=" + typeVehic + ", cartegrise=" + cartegrise + ", attassurance=" + attassurance + ", listeAvis=" + listeAvis + ", moyenne=" + moyenne + ", listeDisponibilite=" + listeDisponibilite + "\n";
    }

    public LinkedList<Avis> getListeAvis() {
        return listeAvis;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public Vehicule getTypeVehic() { //On retourne le type de véhicule que possède le conducteur
        return typeVehic;
    }

    public String getPermis() {
        return permis;
    }

    public int getCartegrise() {
        return cartegrise;
    }

    public void sauvegarder(FileWriter fich) throws IOException { //On écrit dans le fichier tous les paramètres du conducteur
        super.sauvegarder(fich); //On appelle la fonction mère
        fich.write(permis + System.lineSeparator());
        typeVehic.sauvegarder(fich); //On appelle la fonction sauvegarder de Vehicule pour sauvegarder le type de véhicule du conducteur
        fich.write(cartegrise + System.lineSeparator());
        fich.write(attassurance + System.lineSeparator());
        int nbreAvis = listeAvis.size(); //On attribut à "nbreAvis" le nombre d'avis contenu dans listeAvis
        //Sauvegarder avis
        fich.write(nbreAvis + System.lineSeparator()); //On enregistre dans le fichier le nombre d'avis pour pouvoir les récupérer après
        Iterator<Avis> itAvis = listeAvis.iterator(); //On créé un itérateur pour récupérer chaque avis
        for (int i = 0; i < nbreAvis; i++) {
            Avis A = itAvis.next(); //On enregistre un avis
            A.sauvegarder(fich); //On sauvegarde l'avis dans le fichier
        }
        //sauvegarder liste dispo
        int nbreDispo = listeDisponibilite.size(); //On attribut à "nbreDispo" le nombre de disponibilités contenu dans listeDisponibilite
        fich.write(nbreDispo + System.lineSeparator()); //On enregistre dans le fichier le nombre de disponibilités pour pouvoir les récupérer après
        Iterator<Disponibilite> itDispo = listeDisponibilite.iterator(); //On créé un itérateur pour récupérer chaque disponibilités
        for (int i = 0; i < nbreDispo; i++) {
            Disponibilite B = itDispo.next(); //On enregistre une disponibilité
            B.sauvegarder(fich); //On la sauvegarde dans le fichier
        }
    }

    public LinkedList<Disponibilite> getDispo() { //On retourne la liste de disponibilités du conducteur
        return listeDisponibilite;
    }

    //Pour ajouter une disponibilité à la liste
    public void ajouterDispo(int jour, int mois, int annee, int heure1, int minutes1, int heure2, int minutes2, String position) {
        Disponibilite x = new Disponibilite(jour, mois, annee, heure1, minutes1, heure2, minutes2, position);
        listeDisponibilite.add(x);
    }

    //Pour ajouter une disponibilité à la liste avec le chargement
    public void ajouterDispo(LocalDateTime j1, LocalDateTime j2, String position) {
        Disponibilite x = new Disponibilite(j1, j2, position);
        listeDisponibilite.add(x);
    }

    //Pour ajouter un avis sur le conducteur
    public void ajouterAvis(String commentaire, int note, String mailP, LocalDateTime date) {
        Avis x = new Avis(commentaire, note, mailP, date);
        listeAvis.add(x);
        this.calculerMoyenne();
    }

    public void calculerMoyenne() { //Pour calculer la moyenne du conducteur à partir des avis
        Avis A;
        moyenne = 0;
        if (!listeAvis.isEmpty()) { //On effectue l'action si la liste n'est pas vide pour ne pas diviser par 0
            Iterator<Avis> it = listeAvis.iterator(); //On créé un itérateur pour parcourir la liste
            while (it.hasNext()) { //Le while s'effectue tant que la liste n'est pas parcourue entièrement
                A = it.next();
                moyenne = moyenne + A.getNote(); //On additionne toutes les notes des avis
            }
            moyenne = moyenne / listeAvis.size(); //On divise par le nombre de notes pour avoir une moyenne
        }
    }

    public void modifierInfos(String permis, int cartegrise, String nom, String mail, String mdp, String nbtel) {
        permis = this.permis;
        cartegrise = this.cartegrise;
        super.modifierInfos(nom, mail, mdp, nbtel);
    }

    public void modifierVehic(Vehicule V) {
        typeVehic = V;
    }

    public Avis chercherAvis(String mail, LocalDateTime date) throws ExVide {
        Avis A;
        int comp = 1;
        boolean comp1 = false;//Sert à contenir la variable qui compare 
        Iterator<Avis> it = listeAvis.iterator(); //On créé un nouvel itérateur pour parcourir la liste des avis
        while (it.hasNext()) { //tant que la liste n'est pas finie on continue
            A = it.next(); //On attribut à A l'avis suivant de la liste
            comp = mail.compareTo(A.getMailPassager()); //On compare le mail entré par l'utilisateur et celui du passager
            if (comp == 0) { //Si ce sont les mêmes
                comp1 = (date.isEqual(A.getDate())); //On compare les deux dates
                if (comp1) { //Si les dates sont les mêmes
                    return A; //on retourne l'avis
                }
            }
        }
        if (comp != 0 || !comp1) { //S'il n'a trouvé aucun avis
            throw new ExVide("Erreur.\nCette course n'existe pas pour ce conducteur.");
        }
        return null;
    }
}
