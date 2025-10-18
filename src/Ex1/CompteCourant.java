package Ex1;

public class CompteCourant extends Compte {
    private double decouvertAutorise;

    public CompteCourant(double soldeInitial, double decouvert) {
        super(soldeInitial);
        this.decouvertAutorise = decouvert;
    }

    @Override
    public void retirer(double montant) {
        if (montant <= solde + decouvertAutorise) solde -= montant;
    }

    @Override
    public void afficherDetails() {
        System.out.println("CompteCourant #" + getNumero() + " — solde = " + solde + ", découvert = " + decouvertAutorise);
    }


}
