package Ex1;

public class CompteEpargne extends Compte {
	private double tauxInteret;

	public CompteEpargne(double soldeInitial, double taux) {
		super(soldeInitial);
		this.tauxInteret = taux;
	}

	public void appliquerInterets() {
		solde += solde * tauxInteret / 100;
	}
@Override
	public void afficherDetails() {
		System.out.println("CompteEpargne #" + getNumero() + " — solde = " + solde + ", taux = " + tauxInteret + "%");
	}
}
