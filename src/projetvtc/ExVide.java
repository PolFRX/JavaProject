package projetvtc;

public class ExVide extends Exception {

    public ExVide() { //Constructeur sans message
        super();
    }

    public ExVide(String msg) { //Constructeur avec message
        super(msg);
    }
}
