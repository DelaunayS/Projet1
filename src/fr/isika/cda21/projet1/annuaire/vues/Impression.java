package fr.isika.cda21.projet1.annuaire.vues;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Impression extends Scene {

	private BorderPane root; 
	public Stage newStage;
	private Label succes;
	
	public Impression() {
		super(new BorderPane(), 250,100);
		root = (BorderPane)this.getRoot();
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