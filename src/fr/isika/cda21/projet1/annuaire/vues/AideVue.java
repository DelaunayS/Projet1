package fr.isika.cda21.projet1.annuaire.vues;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

import fr.isika.cda21.projet1.annuaire.components.LigneAideVue;
import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Couleur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AideVue extends Scene {

	// attributs
	private Label titreLabel;
	private ScrollPane scrollPane;
	private BorderPane root;
	private HBox panneauHaut;
	private Button retourButton;
	@SuppressWarnings("unused")
	private Annuaire annuaire;
	@SuppressWarnings("unused")
	private Stage primaryStage;
	private VBox centrePage;

	private LigneAideVue ligne0;
	private LigneAideVue ligne1;
	private LigneAideVue ligne2;
	private LigneAideVue ligne3;
	private LigneAideVue ligne4;
	private LigneAideVue ligne5;
	private LigneAideVue ligne6;

	// constructeurs
	public AideVue(Annuaire annuaire, Stage primaryStage) throws FileNotFoundException {

		super(new BorderPane(), 650, 650);

		root = ((BorderPane) this.getRoot());
		scrollPane = new ScrollPane();

		centrePage = new VBox();

		// En haut
		panneauHaut = new HBox();
		titreLabel = new Label("Page d'aide");
		titreLabel.setStyle("-fx-font : 20 Verdana;");
		retourButton = new Button("Retour");
		retourButton.setTextFill(Color.web(Couleur.LIGHT));
		retourButton.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		retourButton.setStyle("-fx-background-color : " + Couleur.PRIMARY);
		panneauHaut.getChildren().addAll(titreLabel, retourButton);
		panneauHaut.setPadding(new Insets(10, 10, 10, 10));
		titreLabel.setPadding(new Insets(20, 20, 20, 20));
		retourButton.setPadding(new Insets(10, 10, 10, 10));
		panneauHaut.setAlignment(Pos.CENTER);

		centrePage.getChildren().add(panneauHaut);

		//explication mode administrateur
		String message0 = "Mode Administrateur :\n" 
				+ "1) saisir le mot passe \n"
				+ "2) si c'est bon : un message \n"
				+ "en vert apparait sous le bouton\n"
				+ "3) les boutons Modifier et\n"
				+ "Supprimer sont disponibles \n"
				+ "dans la zone des boutons en bas\n"
				+"\n"
				+"Visiteur est le mode par défaut";
				
		ligne0 = new LigneAideVue("Administrateur", message0, 120, 240,
				"src/fr/isika/cda21/projet1/annuaire/utilitaires/Admin.png");
		centrePage.getChildren().add(ligne0);
		
		
		// ligne 1
		String message1 = "Permet l'ajout de stagiaire :\n" 
				+ "1) apparition d'une fenêtre \n"
				+ "2) remplir les champs souhaités, \n" 
				+ "3) cliquer sur le bouton valider.\n"
				+ "4) la fenêtre se ferme \n" 
				+ "5) le stagiaire est dans la vue principale";
		ligne1 = new LigneAideVue("Ajouter", message1, 280, 320,
				"src/fr/isika/cda21/projet1/annuaire/utilitaires/AjouterStagiaire.png");
		centrePage.getChildren().add(ligne1);

		// ligne 2
		String message2 = "Permet la recherche par nom :\n"
				+ "1) apparition d'une fenêtre \n"
				+ "2) remplir avec le nom recherché \n" 
				+ "3) cliquer sur le bouton valider.\n"
				+ "4) la fenêtre se ferme \n" 
				+ "5) le stagiaire recherché \n" 
				+ "est dans la vue principale";
		ligne2 = new LigneAideVue("Rechercher", message2, 150, 250,
				"src/fr/isika/cda21/projet1/annuaire/utilitaires/RechercherStagiaire.png");
		centrePage.getChildren().add(ligne2);

		// ligne 3
		String message3 = "Modifier de stagiaire :\n" 
				+ "Uniquement en mode Administrateur \n"
				+ "1) apparition d'une fenêtre \n"
				+ "2) remplir les champs souhaités, \n" 
				+ "3) cliquer sur le bouton valider.\n"
				+ "4) la fenêtre se ferme \n" 
				+ "5) le stagiaire est dans la vue principale";
		ligne3 = new LigneAideVue("Modifier", message3, 280, 320,
				"src/fr/isika/cda21/projet1/annuaire/utilitaires/ModifierStagiaire.png");
		centrePage.getChildren().add(ligne3);

		// ligne 4 
		String message4 = "Supprimer un stagiaire :\n"
				+ "Uniquement en mode Administrateur \n"
				+ "1) apparition d'une fenêtre \n"
				+ "2) cliquer sur oui pour \n"
				+ "confirmer la suppression \n"
				+ "3) la fenêtre se ferme \n"
				+ "4) le stagiaire n'est plus\n" 
				+ "dans la vue principale";
		ligne4 = new LigneAideVue("Supprimer", message4, 150, 250,
				"src/fr/isika/cda21/projet1/annuaire/utilitaires/SupprimerStagiaire.png");
		centrePage.getChildren().add(ligne4);
				
		// ligne 5
		String message5 = "Impression des stagiaires :\n" 
				+ "1) apparition d'une fenêtre qui \n"
				+ "indique que l'impression a lieu \n" 
				+ "2) un fichier pdf est créé\n"
				+ "3) fermeture automatique de la fenêtre \n" 
				+ "4) le fichier se trouve dans :\n"
				+ "~/Projet1/src/fr/isika/cda21 \n"
				+ " /projet1/annuaire/utilitaires";
		ligne5 = new LigneAideVue("Impression", message5, 150, 220,
				"src/fr/isika/cda21/projet1/annuaire/utilitaires/Impression.png");
		centrePage.getChildren().add(ligne5);
	
		// ligne 6
		String message6 = "Recherche avancée :\n" 
				+ "Dans la barre au dessus \n"
				+ "du tableau de stagiaire \n" 
				+ "saisir les informations\n"
				+ "le tableau est filtré \n" 
				+ "en même temps que la saisie.";				
		ligne6 = new LigneAideVue("", message6, 200, 320,
				"src/fr/isika/cda21/projet1/annuaire/utilitaires/Avance.png");
		centrePage.getChildren().add(ligne6);
		
		// ajout de la centrePage au Pane qui permet de scroller
		scrollPane.setContent(centrePage);
		// on enlève le scroll en largeur
		scrollPane.setFitToWidth(true);
		// on place la scrollPane au centre de la BorderPane
		root.setCenter(scrollPane);

		/* clic sur le bouton retour */
		retourButton.setOnAction(eventAction -> {
			Scene scene;
			try {
				scene = new VuePrincipale(annuaire, primaryStage);
				Stage stage = (Stage) AideVue.this.getRoot().getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		});
	}

}
