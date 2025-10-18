package Ex1;
public class Compte {
    private static int compteur = 0;  
    private final int numero;
    protected double solde;           

    public Compte(double soldeInitial) {
        this.numero = ++compteur;
        this.solde = soldeInitial;
    }
    public int getNumero() {
        return numero;
    }
	public void deposer(double montant) {
        if (montant > 0) solde += montant;
    }

    public void retirer(double montant) {
        if (montant > 0 && solde >= montant) solde -= montant;
    }

    public void afficherDetails() {
        System.out.println("Compte #" + numero + " — solde = " + solde);
    }
}
