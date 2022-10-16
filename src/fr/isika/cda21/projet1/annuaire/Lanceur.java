package fr.isika.cda21.projet1.annuaire;
	
import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
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
			
			Stagiaire stagiaire1= new Stagiaire("LACROIX","Pascale","91","BOBI 5",2008);
			Stagiaire stagiaire2= new Stagiaire("CHAVENEAU","Kim Anh","92","ATOD 22",2014);
			Stagiaire stagiaire3= new Stagiaire("GARIJO","Rosie","75","AI 79",2011);
			Stagiaire stagiaire4= new Stagiaire("POTIN","Thomas","75","ATOD 21",2014);
			Stagiaire stagiaire5= new Stagiaire("AUGEREAU","Kévin","76","AI 78",2010);
			Stagiaire stagiaire6= new Stagiaire("UNG","Jet-Ming","75","ATOD 16 CP",2012);
			Stagiaire stagiaire7= new Stagiaire("ROIGNANT","Pierre-Yves","77","ATOD 26 CP",2015);
			Stagiaire stagiaire8= new Stagiaire("CHONE","Martin","92","ATOD 24 CP",2015);
			Stagiaire stagiaire9= new Stagiaire("AKHIAD","Brahim","92","AI 60",2003);
			Stagiaire stagiaire10= new Stagiaire("NOUAR","Adel","94","ATOD 5",2009);
			Stagiaire stagiaire11= new Stagiaire("ANONYME","Toto","94","ATOD 5",2009);
			Stagiaire stagiaire12=new Stagiaire();
			stagiaire12.setNom("POTIN");
			stagiaire12.setPrenom("Thomas");
			stagiaire12.setLibelleFormation("ATOD 21");
			

			//Noeud noeud1 = new Noeud(stagiaire1, 1, 2);
			//Noeud noeud2 = new Noeud(stagiaire2, 0, 1);
			//Noeud noeud3 = new Noeud(stagiaire3, 1, 0);
			
			//ArrayList<Noeud> listeNoeuds = new ArrayList<Noeud>();
			//listeNoeuds.add(noeud1);
			//listeNoeuds.add(noeud2);
			//listeNoeuds.add(noeud3);
			//annuaire.visualiserAnnuaire();
			
			//annuaire.ordreAlpha();	
			System.out.println("Recherche de : "+stagiaire1);
			System.out.println(annuaire.rechercher(stagiaire1));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire2);
			System.out.println(annuaire.rechercher(stagiaire2));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire3);
			System.out.println(annuaire.rechercher(stagiaire3));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire4);
			System.out.println(annuaire.rechercher(stagiaire4));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire5);
			System.out.println(annuaire.rechercher(stagiaire5));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire6);
			System.out.println(annuaire.rechercher(stagiaire6));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire7);
			System.out.println(annuaire.rechercher(stagiaire7));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire8);
			System.out.println(annuaire.rechercher(stagiaire8));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire9);
			System.out.println(annuaire.rechercher(stagiaire9));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire10);
			System.out.println(annuaire.rechercher(stagiaire10));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire11);
			System.out.println(annuaire.rechercher(stagiaire11));
			System.out.println("==================");
			System.out.println("Recherche de : "+stagiaire12);
			System.out.println(annuaire.rechercher(stagiaire12));
			
			
			
			//System.out.println(annuaire.getListeDeStagiaires());
			
								
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
