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
		  //  annuaire.visualiserAnnuaire();
		    
		    Stagiaire stagiaire = new Stagiaire("AKHIAD","Brahim","92","AI 60",2003);
		    
		  
		 //   annuaire.rechercher(stagiaire);	
		   annuaire.afficherRechercheParNom("ROIGNANT");
			
		    System.out.println();
			Scene scene = new VuePrincipale(annuaire);
			primaryStage.setTitle("Annuaire");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
