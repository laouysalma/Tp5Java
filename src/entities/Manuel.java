package entities;

public class Manuel extends Livre {
    private String niveau;

    public Manuel(String titre, String auteur, int nbrPages, String niveau) {
        super(titre, auteur, nbrPages);
        this.niveau = niveau;
    }

    public String getNiveau() { return niveau; }

    @Override
    public String toString() {
        return super.toString() + " {Manuel, niveau=" + niveau + "}";
    }
}
