package fr.isika.cda21.projet1.annuaire;
	
import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Formation;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Fichier;
import javafx.application.Application;
import javafx.stage.Stage;


public class Lanceur extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Annuaire annuaire = new Annuaire();
			Formation formation = new Formation("Boby",5);
			Formation formation2 = new Formation("Atos",5);
			
			Stagiaire stagiaire1 = new Stagiaire("Bruno","Julien","75",formation,2005);
			Stagiaire stagiaire2 = new Stagiaire("Jean","Julien","45",formation2,2004);
			Stagiaire stagiaire3 = new Stagiaire("Ben","Julie","75",formation,2008);
			
			annuaire.ajouterAnnuaire(stagiaire1);
			annuaire.ajouterAnnuaire(stagiaire2);
			annuaire.ajouterAnnuaire(stagiaire3);
			
			
			
			annuaire.visualiserAnnuaire();
			annuaire.alphaAnnuaire();
			annuaire.rechercherAnnuaire(stagiaire2);
			System.out.println(annuaire.getListeAlpha());
			
			
			Fichier fichierTxt=new Fichier("Fichier texte");
			//FichierBinaire fichierBin = new FichierBinaire("Fichier binaire");
			
			fichierTxt.lectureSimple();
			System.out.println(fichierTxt.toString());
			
			
			//ArrayList<String> listes = new ArrayList<>(fichierTxt.lectureFichier());
			//fichierBin.sauvegardeFichierBin(listes);
			//fichierBin.lectureFichierBin();
			
			//System.out.println(fichierTxt.toString());
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,800,600);
			//primaryStage.setScene(scene);
			//primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
