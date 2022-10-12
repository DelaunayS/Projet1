package fr.isika.cda21.projet1.annuaire;
	
import fr.isika.cda21.projet1.annuaire.utilitaires.Fichier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Lanceur extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Fichier fichierTxt=new Fichier("Fichier texte");
			fichierTxt.lectureFichier();
			System.out.println(fichierTxt.toString());
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600);
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
