package entities;

public class Roman extends Livre {
    private double prix;

    public Roman(String titre, String auteur, int nbrPages, double prix) {
        super(titre, auteur, nbrPages);
        this.prix = prix;
    }

    public double getPrix() { return prix; }

    @Override
    public String toString() {
        return super.toString() + " {Roman, prix=" + prix + "}";
    }
}
