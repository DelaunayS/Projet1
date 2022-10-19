package fr.isika.cda21.projet1.annuaire.vues;

import java.io.IOException;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Supprimer extends Scene {

	private Stage fenetreSupprimer;
	private BorderPane racine;
	private Label demandeDeConfirmation;
	private Button oui;
	private Button non;
	private HBox conteneurQuestion;
	private HBox conteneurBoutons;
	private HBox conteneurInfos;
	private VBox structure;
	private Annuaire annuaire;
	private  TableView<Stagiaire> listeStagiaires;
	ObservableList<Stagiaire> listeObservableStagiaires;

	
	public Supprimer(Annuaire annuaire,TableView<Stagiaire> listeStagiaires, ObservableList<Stagiaire> listeObservableStagiaires) {
		super(new BorderPane(), 300, 200);
		fenetreSupprimer = new Stage();
		racine = ((BorderPane) this.getRoot());
		demandeDeConfirmation = new Label ("Etes-vous sûr de vouloir supprimer?");
		oui = new Button("Oui");
		non = new Button("Non"); 
		conteneurInfos = new HBox();
		conteneurBoutons = new HBox();
		conteneurQuestion = new HBox();
		structure = new VBox();
		this.listeObservableStagiaires = listeObservableStagiaires;

		
		racine.setCenter(structure);
		structure.setAlignment(Pos.CENTER);
		conteneurQuestion.setAlignment(Pos.CENTER);
		conteneurInfos.setAlignment(Pos.CENTER);
		conteneurBoutons.setAlignment(Pos.CENTER);
		conteneurQuestion.setPadding(new Insets (0, 10, 10, 10));
		structure.getChildren().addAll(conteneurQuestion, conteneurInfos, conteneurBoutons);
		conteneurBoutons.getChildren().addAll(oui,non);
		conteneurQuestion.getChildren().add(demandeDeConfirmation);
		conteneurBoutons.setSpacing(20);

	
		oui.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
								
				// AJOUTER LA METHODE SUPPRIMER UN NOEUD //
				
				
				
				
				Stagiaire selection = listeStagiaires.getSelectionModel().getSelectedItem();
				listeStagiaires.getItems().remove(selection);
				fenetreSupprimer.close();															 
				
				
				try {
					annuaire.getSupprimerAnnuaire().supprimerRacine(selection, -1);
					annuaire.ordreAlpha();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				ObservableList<Stagiaire> listeMAJstagiaires =
						FXCollections.observableArrayList(annuaire.getListeDeStagiaires());					
	
				listeStagiaires.getItems().removeAll(listeObservableStagiaires);
				listeStagiaires.getItems().clear();
				listeStagiaires.getItems().addAll(listeMAJstagiaires);
								
				
				
				
			}
		});
		

	
	}



	public Stage getNouvelleFenetre() {
		return fenetreSupprimer;
	}

	public void setNouvelleFenetre(Stage nouvelleFenetre) {
		this.fenetreSupprimer = nouvelleFenetre;
	}



	public BorderPane getRacine() {
		return racine;
	}



	public void setRacine(BorderPane racine) {
		this.racine = racine;
	}



	public Label getDemandeDeConfirmation() {
		return demandeDeConfirmation;
	}



	public void setDemandeDeConfirmation(Label demandeDeConfirmation) {
		this.demandeDeConfirmation = demandeDeConfirmation;
	}



	public Button getOui() {
		return oui;
	}



	public void setOui(Button oui) {
		this.oui = oui;
	}



	public Button getNon() {
		return non;
	}



	public void setNon(Button non) {
		this.non = non;
	}



	public HBox getConteneurInfos() {
		return conteneurInfos;
	}



	public void setConteneurInfos(HBox conteneurInfos) {
		this.conteneurInfos = conteneurInfos;
	}



	

}