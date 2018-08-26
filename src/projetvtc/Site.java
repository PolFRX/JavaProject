package projetvtc;

import java.io.BufferedReader; //Pour parcourir un fichier
import java.io.FileReader; //Pour lire un fichier
import java.io.FileWriter; //Bibliothèque pour écrire dans un fichier
import java.io.IOException; //Pour gérer les exceptions des fichiers
import java.time.LocalDateTime; //pour utiliser des dates et des heures
import java.util.Iterator; //Pour utiliser des itérateurs
import java.util.LinkedList; //Pour utiliser des LinkedList
import java.util.Scanner; //Pour que l'utilisateur du programme rentre des données
import javax.swing.JOptionPane;

public class Site {

    //Constantes contenants les noms des fichiers de sauvegarde
    private static final String fichierPassagers = "Passagers.txt"; //Pour sauvegarder les passagers
    private static final String fichierConducteurs = "Conducteurs.txt"; //Pour sauvegarder les conducteurs
    private static final String fichierLieux = "Lieux.txt"; //Pour sauvegarder les lieux
    private LinkedList<Passager> listePassagers; //Liste contenant les passagers du site
    private LinkedList<Conducteur> listeConducteurs; //Liste contenant les conducteurs du site
    private LinkedList<Disponibilite> listeDispo; //Liste contenant les disponibilités des conducteurs du site
    private LinkedList<String> listeLieux; //Liste contenant les différents lieux de commande du site

    //Constructeur où on initialise toutes les listes
    public Site() {
        listePassagers = new LinkedList<>();
        listeConducteurs = new LinkedList<>();
        listeDispo = new LinkedList<>();
        listeLieux = new LinkedList<>();
    }

    @Override //On redéfinit la méthode toString
    public String toString() {
        return "Site{" + "listePassagers=" + listePassagers + ", listeConducteurs=" + listeConducteurs + ", listeDispo=" + listeDispo + ", listeLieux=" + listeLieux + '}';
    }

    //Pour inscrire un passager sur le site
    public Passager inscrirePassager(boolean CB, String nom, String mail, String mdp, String nbtel) throws ExErreur {
        Passager x = new Passager(CB, nom, mail, mdp, nbtel); //On contruit un nouveau passager
        Passager P;
        Iterator<Passager> it = listePassagers.iterator();
        while (it.hasNext()) {
            P = it.next();
            if ((P.getMail()).compareTo(mail) == 0) {
                throw new ExErreur("Ce mail est déjà utilisé.");
            }
        }
        this.ajouterPassager(x); //On ajoute la passager à la liste
        return x;
    }

    //Pour inscrire un passager sur le site
    public Conducteur inscrireConducteur(String permis, Vehicule typeVehic, int cartegrise, boolean attassurance, String nom, String mail, String mdp, String nbtel) throws ExErreur {
        Conducteur x = new Conducteur(permis, typeVehic, cartegrise, attassurance, nom, mail, mdp, nbtel);
        Conducteur C;
        Iterator<Conducteur> it = listeConducteurs.iterator();
        while (it.hasNext()) {
            C = it.next();
            if ((C.getMail()).compareTo(mail) == 0) {
                throw new ExErreur("Erreur: incription Conducteur");
            }
        }
        this.ajouterConducteur(x);
        return x;
    }

    //Pour ajouter une disponibilité au site avec toutes les données
    public void ajouterDispo(int jour, int mois, int annee, int heure1, int minutes1, int heure2, int minutes2, String position) {
        Disponibilite x = new Disponibilite(jour, mois, annee, heure1, minutes1, heure2, minutes2, position);
        listeDispo.add(x);
    }

    //Pour ajouter une disponibilité au site avec les dates déjà faite (utilisée surtout pour le chargement)
    public void ajouterDispo(LocalDateTime dt1, LocalDateTime dt2, String position) {
        Disponibilite x = new Disponibilite(dt1, dt2, position);
        listeDispo.add(x);
    }

    //Pour ajouter un passager déjà créé au site
    public void ajouterPassager(Passager user) {
        listePassagers.add(user);
    }

    //Pour ajouter un conducteur déjà créé au site
    public void ajouterConducteur(Conducteur user) {
        listeConducteurs.add(user);
    }

    //Pour ajouter une disponibilité déjà créé au site
    public void ajouterDispo(Disponibilite dispo) {
        listeDispo.add(dispo);
    }

    //Pour ajouter un lieu de départ au site
    public void ajouterLieu(String lieu) {
        listeLieux.add(lieu);
    }

    //Pour sauvegarder l'intégralité des listes du site sur des fichiers
    public void sauvegarde() throws IOException {
        try { //On utilise "try" pour pouvoir gérer les erreurs de fichier notamment
            FileWriter fich = new FileWriter(fichierPassagers); //On ouvre en mode écriture le fichier qui contiendra les passagers
            int nbrePassagers = listePassagers.size(); //On attribut à "nbrePassagers" le nombre de passagers contenus dans la liste
            fich.write(nbrePassagers + System.lineSeparator()); //On enregistre dans le fichier le nombre de passagers de la liste
            Iterator<Passager> it = listePassagers.iterator(); //On créé un itérateur pour parcourir la liste
            while (it.hasNext()) { //tant que la liste n'est pas finie on continue
                Passager x = it.next(); //On attribut à x un passager de la liste
                x.sauvegarder(fich); //On utilise la méthode sauvegarder de Passager pour écrire dans le fichier
            }
            fich.close(); //On ferme le fichier Passagers
        } catch (IOException ex) { //Pour gérer les erreurs dues au fichier
            throw new IOException("Erreur. Un Passager n'a pas pu être enregistré");
        }
        try {
            FileWriter fich = new FileWriter(fichierConducteurs); //On ouvre en mode écriture le fichier qui contiendra les conducteurs
            int nbreConducteurs = listeConducteurs.size(); //On enregistre la taille de la liste dans le fichier
            fich.write(nbreConducteurs + System.lineSeparator()); //On enregistre dans le fichier le nombre de conducteurs de la liste
            Iterator<Conducteur> it = listeConducteurs.iterator(); //On créé un itérateur pour parcourir la liste
            while (it.hasNext()) {//Tant que la liste n'est pas finie on continue
                Conducteur x = it.next(); //On attribut à x le conducteur suivant de la liste
                x.sauvegarder(fich); //On sauvegarde le conducteur x en utilisant la méthode sauvegarder de Conducteur
            }
            fich.close(); //On ferme le fichier Conducteurs
        } catch (IOException ex) { //Pour gérer les erreurs dues au fichier
            throw new IOException("Erreur. Un conducteur n'a pas pu être enregistré");
        }
    }

    //Pour charger toutes les informations du site à partir des fichiers 
    public void charger() throws IOException {
        FileReader fich; //Sert à ouvrir le fichier à lire
        BufferedReader br; //Pour récuperer les lignes du fichier
        int nbre; //Variable pour définir le nombre d'itérations pour lire un fichier
        int nbreListe; //Variable pour définir le nombre d'itérations pour enregistrer les listes contenues dans les fichiers
        String ligne; //Variable servant à enregistrer les lignes destinées à être cnverties en int, boolean, ...
        String nom, mail, mdp, nbtel; //Variables qui contiendront les variables pour les utilisateurs
        String lieuA, lieuD; //Variables qui contiendront les lieux de départ et d'arrivé d'une commande
        int vitesse, nbrePlaces, typeVehic; //Variables pour charger le type de véhicule d'une commande. la variable typeVehic sert à déterminer s'il s'agit d'une moto ou d'une voiture selon la valeur
        double cout, temps; //Variables pour le cout et le temps d'une commande
        boolean CB; //Variable pour charger le moyen de paiement d'un passager
        boolean pmr, grdcoffre; //Variables pour charger une voiture
        int cartegrise; //Variable pour le numéro de carte grise
        double moyenne; //Variable pour la moyenne d'un conducteur
        String permis; //Variable pour le type de permis d'un conducteur
        boolean attassurance; //Variable pour l'attestation d'assurance
        String commentaire; //Variable contenant le commentaire d'un avis
        int note; //Variable contenant la note d'un avis
        //String jdispo, double hdispo, String position
        String position; //Jour de disponibilite et position qui serviront à charger une disponibilité
        LocalDateTime dt1, dt2; //Heure de disponibilité pour en charge une
        //Variables pour contenir provisoirement chaque passager, conducteur, véhicule,... à charger
        Vehicule V = null;
        Passager P;
        Avis A;
        Conducteur C;
        Disponibilite D;
        String lieu;
        String mailC;
        String mailP;
        LocalDateTime dateAvis;

        try {
            fich = new FileReader(fichierPassagers); //On ouvre en mode lecture le fichier qui contient les passagers
            br = new BufferedReader(fich); //On créé un BufferedReader qui servira à parcourir le fichier
            ligne = br.readLine(); //On lit la ligne et on l'enregistre
            nbre = Integer.valueOf(ligne); //On convertit la ligne précedemment lue en un entier qui est le nombre de passagers que contient le fichier
            for (int i = 0; i < nbre; i++) { //On effectue la boucle autant de fois qu'il y a de passagers donc en fonction du nbre lu précedemment
                nom = br.readLine();
                mail = br.readLine();
                mdp = br.readLine();
                nbtel = br.readLine();
                ligne = br.readLine();
                CB = Boolean.valueOf(ligne); //On convertit la ligne précedemment lue en un booléen
                P = new Passager(CB, nom, mail, mdp, nbtel); //On créé un nouveau passager avec les informations lues dans le fichier
                this.ajouterPassager(P); //On ajoute le passager au site avec la méthode
                ligne = br.readLine();
                nbreListe = Integer.valueOf(ligne); //On convertit la ligne précedemment lue en un entier
                for (int j = 0; j < nbreListe; j++) {
                    //Type véhicule
                    ligne = br.readLine();
                    typeVehic = Integer.valueOf(ligne); //On convertit la ligne en entier, cet entier définit le type de véhicule (voiture ou moto)
                    if (typeVehic == 1 || typeVehic == 2 || typeVehic == 3) { //Si c'est une voiture
                        ligne = br.readLine();
                        vitesse = Integer.valueOf(ligne); //On convertit ligne en entier
                        ligne = br.readLine();
                        nbrePlaces = Integer.valueOf(ligne); //On convertit ligne en entier
                        ligne = br.readLine();
                        grdcoffre = Boolean.valueOf(ligne); //On convertit ligne en boolean
                        ligne = br.readLine();
                        pmr = Boolean.valueOf(ligne); //On convertit ligne en boolean
                        if (typeVehic == 1) { //Voiture eco
                            V = new VoitureEco(grdcoffre, pmr, vitesse, nbrePlaces); //On créé une nouvelle moto avec les info lues précedemment
                        } else if (typeVehic == 2) { //Voiture Medium
                            V = new VoitureMedium(grdcoffre, pmr, vitesse, nbrePlaces);
                        } else if (typeVehic == 3) { //Voiture de Luxe
                            V = new VoitureLuxe(grdcoffre, pmr, vitesse, nbrePlaces);
                        }
                    } else if (typeVehic == 4 || typeVehic == 5 || typeVehic == 6) { //Si c'est une moto
                        ligne = br.readLine();
                        vitesse = Integer.valueOf(ligne); //On convertit ligne en entier
                        ligne = br.readLine();
                        nbrePlaces = Integer.valueOf(ligne); //On convertit ligne en entier
                        if (typeVehic == 4) { //Moto eco
                            V = new MotoEco(vitesse, nbrePlaces); //On créé une nouvelle moto avec les info lues précedemment
                        } else if (typeVehic == 5) { //Moto Medium
                            V = new MotoMedium(vitesse, nbrePlaces);
                        } else if (typeVehic == 6) { //Moto de Luxe
                            V = new MotoLuxe(vitesse, nbrePlaces);
                        }
                    }
                    ligne = br.readLine();
                    dt1 = LocalDateTime.parse(ligne); //On convertit la ligne en une date
                    ligne = br.readLine();
                    cout = Double.valueOf(ligne); //On convertit la ligne en double
                    ligne = br.readLine();
                    temps = Double.valueOf(ligne); //On convertit la ligne en double
                    lieuD = br.readLine();
                    lieuA = br.readLine();
                    mailC = br.readLine();
                    P.ajouterCommande(V, cout, temps, lieuD, lieuA, dt1, mailC); //On ajoute la commande au passager 
                }
            }
            fich.close(); //On ferme le fichier Passagers
        } catch (IOException ex) { //Pour gérer les erreurs de fichier
            throw new IOException("Erreur. Un passager n'a pas pu être enregistré");
        }

        try {
            fich = new FileReader(fichierConducteurs); //On ouvre en mode lecture le fichier qui contient les conducteurs
            br = new BufferedReader(fich); //On créé un BufferedReader pour parcourir le fichier
            ligne = br.readLine();
            nbre = Integer.valueOf(ligne); //On convertit la ligne précedemment lue en un entier qui est le nombre de conducteurs contenus dans le fichier
            for (int i = 0; i < nbre; i++) { //On effectue la boucle autant de fois qu'il y a de conducteurs
                nom = br.readLine(); //On lit les informations du conducteurs
                mail = br.readLine();
                mdp = br.readLine();
                nbtel = br.readLine();
                permis = br.readLine();
                ligne = br.readLine();
                typeVehic = Integer.valueOf(ligne); //On convertit la ligne en entier qui détermine quel type de véhicule on veut enregistrer
                if (typeVehic == 1 || typeVehic == 2 || typeVehic == 3) { //Si c'est une voiture
                    ligne = br.readLine();
                    vitesse = Integer.valueOf(ligne); //On convertit ligne en entier
                    ligne = br.readLine();
                    nbrePlaces = Integer.valueOf(ligne); //On convertit ligne en entier
                    ligne = br.readLine();
                    grdcoffre = Boolean.valueOf(ligne); //On convertit ligne en boolean
                    ligne = br.readLine();
                    pmr = Boolean.valueOf(ligne); //On convertit ligne en boolean
                    if (typeVehic == 1) { //Voiture eco
                        V = new VoitureEco(grdcoffre, pmr, vitesse, nbrePlaces); //On créé une nouvelle moto avec les info lues précedemment
                    } else if (typeVehic == 2) { //Voiture Medium
                        V = new VoitureMedium(grdcoffre, pmr, vitesse, nbrePlaces);
                    } else if (typeVehic == 3) { //Voiture de Luxe
                        V = new VoitureLuxe(grdcoffre, pmr, vitesse, nbrePlaces);
                    }
                } else if (typeVehic == 4 || typeVehic == 5 || typeVehic == 6) { //Si c'est une moto
                    ligne = br.readLine();
                    vitesse = Integer.valueOf(ligne); //On convertit ligne en entier
                    ligne = br.readLine();
                    nbrePlaces = Integer.valueOf(ligne); //On convertit ligne en entier
                    if (typeVehic == 4) { //Moto eco
                        V = new MotoEco(vitesse, nbrePlaces); //On créé une nouvelle moto avec les info lues précedemment
                    } else if (typeVehic == 5) { //Moto Medium
                        V = new MotoMedium(vitesse, nbrePlaces);
                    } else if (typeVehic == 6) { //Moto de Luxe
                        V = new MotoLuxe(vitesse, nbrePlaces);
                    }
                }
                ligne = br.readLine();
                cartegrise = Integer.valueOf(ligne); //On convertit la ligne en entier
                ligne = br.readLine();
                attassurance = Boolean.valueOf(ligne); //On convertit la ligne en boolean
                C = new Conducteur(permis, V, cartegrise, attassurance, nom, mail, mdp, nbtel); //On créé un nouveau conducteur avec toutes les infos lues
                this.ajouterConducteur(C); //On ajoute ce conducteur au site
                //Charger les avis
                ligne = br.readLine();
                nbreListe = Integer.valueOf(ligne); //On convertit la ligne en entier qui sera le nombre d'avis à lire dans le fichier
                for (int j = 0; j < nbreListe; j++) {
                    commentaire = br.readLine();
                    ligne = br.readLine();
                    note = Integer.valueOf(ligne); //On convertit la ligne en entier
                    mailP = br.readLine();
                    ligne = br.readLine();
                    dateAvis = LocalDateTime.parse(ligne); //On convertit la ligne en une date
                    C.ajouterAvis(commentaire, note, mailP, dateAvis); //On ajoute l'avis au conducteur
                }
                //Charger les disponibilités
                ligne = br.readLine();
                nbreListe = Integer.valueOf(ligne); //On convertit la ligne en entier qui sera le nombre de disponibilités à lire dans le fichier
                for (int j = 0; j < nbreListe; j++) {
                    ligne = br.readLine();
                    dt1 = LocalDateTime.parse(ligne); //On convertit la ligne en une date
                    ligne = br.readLine();
                    dt2 = LocalDateTime.parse(ligne); //On convertit la ligne en une ligne
                    position = br.readLine();
                    C.ajouterDispo(dt1, dt2, position); //On ajoute une disponibilité au conducteur
                }
                C.calculerMoyenne();
            }
            fich.close(); //On ferme le fichier Conducteurs
        } catch (IOException ex) { //Pour gérer les erreurs de fichier
            throw new IOException("Erreur. Un conducteur n'a pas pu être enregistré");
        }

        try {
            fich = new FileReader(fichierLieux); //On ouvre en mode lecture le fichier contenant les lieux
            br = new BufferedReader(fich); //On créé un nouveau BufferedReader pour parcourir le fichier
            ligne = br.readLine();
            nbre = Integer.valueOf(ligne); //On convertit la ligne précedemment lue en un entier qui sera le nombre de lieux contenus dans le fichier
            for (int i = 0; i < nbre; i++) {
                ligne = br.readLine();
                this.ajouterLieu(ligne); //On ajoute le lieu au site
            }
            fich.close(); //On ferme le fichier Lieux
        } catch (IOException ex) { //Pour gérer les erreurs de fichier
            throw new IOException("Erreur. Un lieu n'a pas pu être enregistré");
        }
    }

    public Conducteur identificationCond(String mail, String mdp) throws ExMailFaux, ExMdpFaux {
        Conducteur C;
        int comp = 1; //Sert à contenir la variable qui compare 
        Iterator<Conducteur> it = listeConducteurs.iterator(); //On créé un nouvel itérateur pour parcourir la liste des conducteurs
        while (it.hasNext()) { //tant que la liste n'est pas finie on continue
            C = it.next(); //On attribut à C le conducteur suivant de la liste
            comp = mail.compareTo(C.getMail()); //On compare le mail entré par l'utilisateur et celui de C
            if (comp == 0) { //Si ce sont les mêmes
                comp = mdp.compareTo(C.getMdp()); //On compare le mo de passe entré par l'utilisateur et celui de C
                if (comp == 0) { //Si ce sont les mêmes
                    return C;
                } else {
                    throw new ExMdpFaux(); //On propage l'exception du mot de passe erroné
                }
            }
        }
        if (comp != 0) { //Si ce ne sont pas les mêmes mails
            throw new ExMailFaux();  //On propage l'exception du mail erroné
        }
        return null;
    }

    //Pour s'identifier sur le site à l'aide de son mail et de son mot de passe
    public Passager identificationPass(String mail, String mdp) throws ExMailFaux, ExMdpFaux {
        Passager P; //Variable qui contiendra le passager
        int comp = 1; //Sert à contenir la variable qui compare
        Iterator<Passager> it = listePassagers.iterator(); //On créé un itérateur pour parcourir la liste des passagers
        while (it.hasNext()) { //Tant que la liste n'est pas finie on continue
            P = it.next(); //On attribut à P le passager suivant de la liste
            comp = mail.compareTo(P.getMail()); //On compare le mail entré par l'utilisateur et celui de P
            if (comp == 0) { //Si ce sont les mêmes
                String mpd = P.getMdp();
                comp = mdp.compareTo(P.getMdp()); //On compare le mot de passe entré par l'utilisateur et celui de P
                if (comp == 0) { //Si ce sont les mêmes
                    return P;
                } else {
                    throw new ExMdpFaux(); //On propage l'exception du mot de passe erroné
                }
            }
        }
        if (comp != 0) { //Si ce ne sont pas les mêmes mails
            throw new ExMailFaux(); //On propage l'exception du mail erroné
        }
        return null;
    }

    //pour que le passager passe commande
    public Conducteur passerCommande(Passager P, String lieuD, String lieuA, boolean pmr, boolean grdCoffre, LocalDateTime date, Vehicule Vehic) throws ExVide {
        LinkedList<Disponibilite> Dispo = new LinkedList<>(); //on initalise une LinkedList qui contiendra les disponibilités du conducteur
        Disponibilite D; //On initialise une varaible qui contiendra une disponibilité
        int e = 0; //variable pour voir s'il y a une erreur. Si il y en a une e=0
        Conducteur Conduc = null; //On initialise un conducteur

        //Vehicule m = new MotoLuxe(180, 2); //On initialise des variables pour vérifier si la méthode marche
        Iterator<Conducteur> it1 = listeConducteurs.iterator(); //On créé un itérateur pour parcourir la liste
        while (it1.hasNext()) { //Tant que la liste n'est pas vide on continue
            Conduc = it1.next(); //Conduc1 reçoit le conducteur suivant de la liste
            Boolean B = (Conduc.getTypeVehic()).equals(Vehic); //On regarde si les deux types de véhicules sont les mêmes
            if (B) { //Si ce sont les mêmes
                Dispo = Conduc.getDispo(); //on attribut à D la liste des disponibilités du conducteur C
                Iterator<Disponibilite> it2 = Dispo.iterator(); //On créé un nouvel itérateur pour parcourir la liste
                while (it2.hasNext()) { //Tant que la liste n'est pas finie on continue
                    D = it2.next(); //On attribut à D la disponibilité suivante de la liste
                    Commande C = new Commande(Vehic, lieuD, lieuA, date, Conduc.getMail());
                    Boolean B1 = D.equalCommande(C); //On regarde si la commande et la disponibilité sont compatibles au niveau des dates et du lieu de départ
                    if (B1) {//Si elles sont compatibles
                        P.ajouterCommande(C); //on ajoute la commande au passager P pour vérifier la méthode
                        e = 1; //Il n'y a pas d'erreur donc on change la variable e
                        return Conduc;
                    } else {
                        C = null;
                    }
                }
            }
        }
        if (e == 0) { //S'il y a erreur (on ne trouve pas de conducteur disponible)
            throw new ExVide("Nous n'avons pas trouvé de conducteurs.");
        }
        return Conduc;
    }

    public Conducteur chercherCond(String mail) throws ExErreur {

        Conducteur C = null;
        int comp = 1; //Sert à contenir la variable qui compare 
        Iterator<Conducteur> it = listeConducteurs.iterator(); //On créé un nouvel itérateur pour parcourir la liste des conducteurs
        while (it.hasNext()) { //tant que la liste n'est pas finie on continue
            C = it.next(); //On attribut à C le conducteur suivant de la liste
            comp = mail.compareTo(C.getMail()); //On compare le mail entré par l'utilisateur et celui de C
            if (comp == 0) { //Si ce sont les mêmes
                return C;
            }
        }
        throw new ExErreur("Aucun conducteur n'a cette adresse mail.");
    }

    public LinkedList<Conducteur> getListeConducteurs() { //On retourne la liste des conducteurs
        return listeConducteurs;
    }
}
