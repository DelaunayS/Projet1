package fr.isika.cda21.projet1.annuaire.vues;

import fr.isika.cda21.projet1.annuaire.utilitaires.Couleur;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Impression extends Scene {

	private BorderPane root;
	public Stage newStage;
	private Label succes;

	public Impression() {
		super(new BorderPane(), 250, 100);
		root = (BorderPane) this.getRoot();
		root.setStyle("-fx-background-color :" + Couleur.FLORAL);
		succes = new Label("Impression lancée avec succès");

		root.setCenter(succes);

	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public Label getSucces() {
		return succes;
	}

	public void setSucces(Label succes) {
		this.succes = succes;
	}

	public Stage getNewStage() {
		return newStage;
	}

	public void setNewStage(Stage newStage) {
		this.newStage = newStage;
	}

}