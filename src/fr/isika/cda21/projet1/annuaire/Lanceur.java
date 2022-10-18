package fr.isika.cda21.projet1.annuaire;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.vues.VuePrincipale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lanceur extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Annuaire annuaire = new Annuaire();
			//annuaire.visualiserAnnuaire();

			Stagiaire stagiaire = new Stagiaire("AKHIAD", "Brahim", "92", "AI 60", 2003);
			Stagiaire stagiaire1 = new Stagiaire("NOUAR", "Adel", "94", "ATOD 5", 2009);
			Stagiaire stagiaire2 = new Stagiaire("LACROIX", "Pascale", "91", "BOBY 5", 2008);
			Stagiaire stagiaire3= new Stagiaire("GARIJO","Rosie","75","AI 79",2011);
			Stagiaire stagiaire4 = new Stagiaire("BOUCHET","Laurent","91","ATOD 19 CP",2013);
			Stagiaire stagiaire5 = new Stagiaire("AUGEREAU","Kévin","76","AI78",2010);
			Stagiaire stagiaire6 = new Stagiaire("POTIN","Thomas","75","ATOD 21",2014);
			//annuaire.supprimer(stagiaire1);
			//annuaire.parent(stagiaire3);
			//annuaire.parent(stagiaire2);
			//annuaire.parent(stagiaire1);			
			//annuaire.parent(stagiaire);
			annuaire.supprimerRacine(stagiaire2);
			annuaire.visualiserAnnuaire();
			
			
			// annuaire.rechercher(stagiaire);
			//annuaire.afficherRechercheParNom("ROIGNANT");
			annuaire.ordreAlpha();
			System.out.println();
			Scene scene = new VuePrincipale(annuaire);
			primaryStage.setTitle("Annuaire");
			primaryStage.setX(50);
			primaryStage.setY(100);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
