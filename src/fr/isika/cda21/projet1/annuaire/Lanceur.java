package fr.isika.cda21.projet1.annuaire;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.vues.VuePrincipale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lanceur extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Annuaire annuaire = new Annuaire();
			
			Scene scene = new VuePrincipale(annuaire, primaryStage);
			primaryStage.setTitle("Annuaire");
			primaryStage.setX(20);
			primaryStage.setY(50);
			primaryStage.setResizable(false);
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
