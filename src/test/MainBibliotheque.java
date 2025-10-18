package test;

import entities.*;
import java.util.Scanner;

public class MainBibliotheque {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez la taille de la bibliothèque : ");
        int n = sc.nextInt();
        sc.nextLine();

        Bibliotheque biblio = new Bibliotheque(n);

        biblio.ajouter(new Roman("Le Petit Prince", "Saint-Exupéry", 120, 25.0));
        biblio.ajouter(new Manuel("Maths Terminale", "Dupont", 350, "Terminale"));

        boolean quitter = false;
        while (!quitter) {
        	System.out.println(
        		    "\nMenu :\n" +
        		    "1. Ajouter\n" +
        		    "2. Afficher\n" +
        		    "3. Supprimer\n" +
        		    "4. Document par ID\n" +
        		    "5. Auteurs\n" +
        		    "6. Quitter"
        		);
            System.out.print("Choix : ");
            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Type (Roman/Manuel/Revue/Dictionnaire) : ");
                    String type = sc.nextLine();
                    System.out.print("Titre : ");
                    String titre = sc.nextLine();

                    Document doc = null;
                    if (type.equalsIgnoreCase("Roman")) {
                        System.out.print("Auteur : "); 
                        String auteur = sc.nextLine();
                        System.out.print("Pages : "); 
                        int pages = sc.nextInt();
                        System.out.print("Prix : "); 
                        double prix = sc.nextDouble(); 
                        sc.nextLine();
                        doc = new Roman(titre, auteur, pages, prix);
                    } else if (type.equalsIgnoreCase("Manuel")) {
                        System.out.print("Auteur : "); 
                        String auteur = sc.nextLine();
                        System.out.print("Pages : "); 
                        int pages = sc.nextInt();
                        System.out.print("Niveau : "); 
                        String niveau = sc.next(); 
                        sc.nextLine();
                        doc = new Manuel(titre, auteur, pages, niveau);
                    } else if (type.equalsIgnoreCase("Revue")) {
                        System.out.print("Mois : "); 
                        String mois = sc.nextLine();
                        System.out.print("Année : "); 
                        int annee = sc.nextInt(); 
                        sc.nextLine();
                        doc = new Revue(titre, mois, annee);
                    } else if (type.equalsIgnoreCase("Dictionnaire")) {
                        System.out.print("Langue : "); 
                        String langue = sc.nextLine();
                        doc = new Dictionnaire(titre, langue);
                    }

                    if (doc != null) {
                        if (biblio.ajouter(doc)) 
                            System.out.println("Document ajouté !");
                        else 
                            System.out.println("Échec, bibliothèque pleine !");
                    }
                    break;

                case 2: 
                    biblio.afficherDocuments(); 
                    break;
                case 3:
                    System.out.print("Numéro d'enregistrement à supprimer : ");
                    int numSup = sc.nextInt(); 
                    sc.nextLine();
                    Document d = biblio.document(numSup);
                    if (d != null && biblio.supprimer(d)) 
                        System.out.println("Supprimé !");
                    else 
                        System.out.println("Document introuvable !");
                    break;
                case 4:
                    System.out.print("Numéro d'enregistrement : "); 
                    int numDoc = sc.nextInt(); 
                    sc.nextLine();
                    Document found = biblio.document(numDoc);
                    System.out.println(found != null ? found : "Document introuvable !");
                    break;
                case 5: 
                    biblio.afficherAuteurs(); 
                    break;
                case 6: 
                    quitter = true; 
                    break;
                default: 
                    System.out.println("Choix invalide."); 
                    break;
            }
        }
        sc.close();
    }
}
