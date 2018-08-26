package projetvtc;

public class ExMailFaux extends Exception {

    //Exception pour gérer le cas où le mail est faux
    public ExMailFaux() {
        super("Votre adresse mail ou votre mot de passe sont erronés."); //On affiche ce message
    }
    
    public ExMailFaux(String msg) {
        super(msg); //On affiche ce message
    }
}
