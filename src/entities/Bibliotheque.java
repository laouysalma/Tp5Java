package entities;

public class Bibliotheque {
    private Document[] documents;
    private int nbDocuments;

    public Bibliotheque(int capacite) {
        this.documents = new Document[capacite];
        this.nbDocuments = 0;
    }

    public boolean ajouter(Document doc) {
        if (nbDocuments == documents.length) return false;
        documents[nbDocuments++] = doc;
        return true;
    }

    public boolean supprimer(Document doc) {
        for (int i = 0; i < nbDocuments; i++) {
            if (documents[i].getNumEnreg() == doc.getNumEnreg()) {
                for (int j = i; j < nbDocuments - 1; j++) {
                    documents[j] = documents[j + 1];
                }
                documents[--nbDocuments] = null;
                return true;
            }
        }
        return false;
    }

    public Document document(int numEnreg) {
        for (int i = 0; i < nbDocuments; i++) {
            if (documents[i].getNumEnreg() == numEnreg) return documents[i];
        }
        return null;
    }

    public void afficherDocuments() {
        System.out.println("Documents dans la bibliothèque :");
        for (int i = 0; i < nbDocuments; i++) {
            System.out.println(" - " + documents[i]);
        }
    }

    public void afficherAuteurs() {
        System.out.println("Liste des auteurs :");
        for (int i = 0; i < nbDocuments; i++) {
            if (documents[i] instanceof Livre) {
                System.out.println(" - " + ((Livre)documents[i]).getAuteur());
            }
        }
    }
}
