package projetvtc;

public class ExMdpFaux extends ExMailFaux {

    //Exception pour gérer le cas où le mot de passe est faux
    public ExMdpFaux() {
        super(); //On effectue le constructeur de "ExMailFaux" pour ne pas spécifier lequel des deux est faux pour plus de sécurité
    }
}
