package Ex1;

public class Main {
	public static void main(String[] args) {
		Compte c = new Compte(1000);
		CompteEpargne e = new CompteEpargne(500, 3);
		CompteCourant cc = new CompteCourant(200, 300);

		c.deposer(200);
		e.appliquerInterets();
		cc.retirer(400);

		c.afficherDetails();
		e.afficherDetails();
		cc.afficherDetails();
	}
}
