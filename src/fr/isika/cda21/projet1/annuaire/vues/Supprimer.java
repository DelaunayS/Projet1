package fr.isika.cda21.projet1.annuaire.vues;

import java.io.IOException;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Couleur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Supprimer extends Scene {

	// ----------------------- ATTRIBUTS -----------------------//

	private Stage fenetreSupprimer;
	private BorderPane racine;
	private Label demandeDeConfirmation;
	private Button oui;
	private Button non;
	private HBox conteneurQuestion;
	private HBox conteneurBoutons;
	private VBox conteneurInfos;
	private VBox structure;
	@SuppressWarnings("unused")
	private TableView<Stagiaire> listeStagiaires;
	ObservableList<Stagiaire> listeObservableStagiaires;
	private Label nom;
	private Label prenom;
	private Label departement;
	private Label libelleformation;
	private Label annee;

	// ----------------------- CONSTRUCTEUR SURCHARGE -----------------------//

	public Supprimer(Annuaire annuaire, TableView<Stagiaire> listeStagiaires,
			ObservableList<Stagiaire> listeObservableStagiaires) {
		super(new BorderPane(), 300, 200);
		fenetreSupprimer = new Stage();			
		racine = ((BorderPane) this.getRoot());
		racine.setStyle("-fx-background-color :"+Couleur.FLORAL);
		demandeDeConfirmation = new Label("Confirmer la suppression de");
		demandeDeConfirmation.setTextFill(Color.web(Couleur.DANGER));
		demandeDeConfirmation.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		oui = new Button("Oui");
		non = new Button("Non");
		conteneurInfos = new VBox();
		conteneurBoutons = new HBox();
		conteneurQuestion = new HBox();
		structure = new VBox();
		this.listeObservableStagiaires = listeObservableStagiaires;
		nom = new Label();
		prenom = new Label();
		departement = new Label();
		libelleformation = new Label();
		annee = new Label();
		

		// ----------------------- AJOUT DES COMPOSANTES A LA SCENE -----------------------//

		racine.setCenter(structure);
		structure.setAlignment(Pos.CENTER);
		conteneurQuestion.setAlignment(Pos.CENTER);
		conteneurInfos.setAlignment(Pos.CENTER);
		conteneurInfos.getChildren().addAll(nom, prenom, departement, libelleformation, annee);
		conteneurBoutons.setPadding(new Insets(10, 10, 0, 10));
		conteneurBoutons.setAlignment(Pos.CENTER);
		conteneurQuestion.setPadding(new Insets(0, 10, 10, 10));
		structure.getChildren().addAll(conteneurQuestion, conteneurInfos, conteneurBoutons);
		conteneurBoutons.getChildren().addAll(oui, non);
		conteneurQuestion.getChildren().add(demandeDeConfirmation);
		conteneurBoutons.setSpacing(20);

		
		
		
		// ----------------------- CONTENU DE LA FENETRE -----------------------//

		/*
		 * On reprend les informations du stagiaire selectionné depuis la tableView et
		 * on les affiche sur la fenêtre supprimer
		 */
		Stagiaire stagiaireSelectionne = listeStagiaires.getSelectionModel().getSelectedItem();
		nom.setText(stagiaireSelectionne.getNom());
		prenom.setText(stagiaireSelectionne.getPrenom());
		departement.setText("Département : " + stagiaireSelectionne.getDepartement());
		libelleformation.setText("Cursus : " + stagiaireSelectionne.getLibelleFormation());
		annee.setText(String.valueOf("Année : " + stagiaireSelectionne.getAnnee()));
		
		
		// ----------------------- CONFIGURATION DES BOUTONS -----------------------//

		/* Oui : supprimer le stagiaire */
		oui.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				// AJOUTER LA METHODE SUPPRIMER UN NOEUD //
				Stagiaire selection = listeStagiaires.getSelectionModel().getSelectedItem();
				
				try {
					annuaire.supprimerStagiaire(selection);
					annuaire.ordreAlpha();
				} catch (IOException e) {					
					e.printStackTrace();
				}
				
				listeObservableStagiaires.clear();
				listeObservableStagiaires.setAll(annuaire.getListeDeStagiaires());
				fermerFenetre(oui);
			}
		});
		
		non.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				fermerFenetre(non);
			}
		});
		
	}

	// ----------------------- GETTERS ET SETTERS -----------------------//

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

	public VBox getConteneurInfos() {
		return conteneurInfos;
	}

	public void setConteneurInfos(VBox conteneurInfos) {
		this.conteneurInfos = conteneurInfos;
	}
	
	
	// ----------------------- METHODES SPECIFIQUES -----------------------//

	/* méthode fermeture de la fenêtre */
	private void fermerFenetre(Button button) {
		@SuppressWarnings("unused")
		Window window = button.getScene().getWindow();
		Stage nouvelleFenetre = (Stage) button.getScene().getWindow();
		nouvelleFenetre.close();
	}
	
}