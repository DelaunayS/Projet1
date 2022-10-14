package fr.isika.cda21.projet1.annuaire;
	
import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Lanceur extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Annuaire annuaire = new Annuaire();						
			//annuaire.visualiserAnnuaire();	
			
//			Stagiaire stagiaire1= new Stagiaire("LACROIX","Pascale","91","BOBI 5",2008);
//			Stagiaire stagiaire2= new Stagiaire("CHAVENEAU","Kim Anh","92","ATOD 22",2014);
//			Stagiaire stagiaire3= new Stagiaire("GARIJO","Rosie","75","AI 79",2011);
//			Stagiaire stagiaire4= new Stagiaire("AUGEREAU","Kevin","76","AI 78",2010);
//			Stagiaire stagiaire5= new Stagiaire("UNG","Jet-Ming","75","ATOD 16 CP",2012);
//			Stagiaire stagiaire6= new Stagiaire("POTIN","Thomas","75","ATOD 21",2014);
//			annuaire.racineVide(stagiaire1);			
//			annuaire.racineVide(stagiaire2);			
//			annuaire.racineVide(stagiaire3);
//			annuaire.racineVide(stagiaire4);
//			annuaire.racineVide(stagiaire5);
//			annuaire.racineVide(stagiaire6);
			//Noeud noeud1 = new Noeud(stagiaire1, 1, 2);
			//Noeud noeud2 = new Noeud(stagiaire2, 0, 1);
			//Noeud noeud3 = new Noeud(stagiaire3, 1, 0);
			
			//ArrayList<Noeud> listeNoeuds = new ArrayList<Noeud>();
			//listeNoeuds.add(noeud1);
			//listeNoeuds.add(noeud2);
			//listeNoeuds.add(noeud3);
			annuaire.visualiserAnnuaire();
			
			
						
			
			
			
			
			//annuaire.visualiserAnnuaire();
		//	annuaire.sauvegarderAnnuaire();
			//annuaire.lectureFichierBinaire();
		//	FichierBinaire fichier= new FichierBinaire("test");
			//System.out.println(fichier.getChemin());
			//fichier.lectureFichierBin();
					
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
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
