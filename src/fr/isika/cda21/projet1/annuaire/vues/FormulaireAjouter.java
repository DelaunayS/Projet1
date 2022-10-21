package fr.isika.cda21.projet1.annuaire.vues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Couleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

// Création classe FormulaireAjouter rattachée à la classe mère (super-classe) Scene
public class FormulaireAjouter extends Scene {

	// Déclaration des attributs
	private GridPane panneauRacine;
	private Label etiquetteNom;
	private TextField champTexteNom;
	private static final int LARGEUR_CHAMP_TEXTE = 200;
	private Label etiquettePrenom;
	private TextField champTextePrenom;
	private Label etiquetteDepartement;
	private ComboBox<String> choixDepartement;
	private Label etiquetteLibelleFormation;
	private ComboBox<String> choixCursus;
	private ComboBox<String> choixNumero;
	private CheckBox choixContrat;
	private static final int LARGEUR_BOUTON = (LARGEUR_CHAMP_TEXTE + 2 * 20) / 3;
	private Label etiquetteAnnee;
	private ComboBox<String> choixAnnee;
	private Button boutonValider;
	private Label etiquetteMessageErreur;
	private String nom;
	private String prenom;
	private Tooltip infoBulleNom;
	private Tooltip infoBullePrenom;
	private Stage nouvelleFenetre;
	private Annuaire annuaire;

	// Constructeur Formulaire rattachée à la classe mère
	// avec une GridPane, dont on définit la distance entre noeuds enfants et la
	// taille
	public FormulaireAjouter(Annuaire annuaire, TableView<Stagiaire> listeStagiaires,
			ObservableList<Stagiaire> listeObservableStagiaires) {
		super(new GridPane(), 580, 400);
		nouvelleFenetre = new Stage();
		nouvelleFenetre.setTitle("Ajouter un stagiaire");
		this.annuaire = annuaire;

		// Affectation à la racine du panneauRacine
		panneauRacine = ((GridPane) this.getRoot());
		panneauRacine.setStyle("-fx-background-color : " + Couleur.FLORAL);

		// ********************************************************
		// INSTANCIATION DES ETIQUETTES, CHAMPS DE TEXTE ET BOUTONS
		// ********************************************************

		// Création étiquette et champ de texte à saisir du Nom avec fixation largeur du
		// champ
		etiquetteNom = new Label("NOM");
		etiquetteNom.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		champTexteNom = new TextField();
		champTexteNom.setMinWidth(LARGEUR_CHAMP_TEXTE);

		// Création info-bulle pour le champ de texte Nom
		infoBulleNom = new Tooltip(
				"Nom ne comportant aucun caractère spécial, ni chiffre,\n ni accent/cédille et ayant au moins 2 lettres");
		infoBulleNom.setStyle("-fx-background-color : " + Couleur.SECONDARY);
		infoBulleNom.setFont(Font.font("Regular", FontPosture.ITALIC, 12));
		champTexteNom.setTooltip(infoBulleNom);
		infoBulleNom.setTextAlignment(TextAlignment.CENTER);

		// Création étiquette et champ de texte à saisir du Prénom
		etiquettePrenom = new Label("Prénom");
		etiquettePrenom.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		champTextePrenom = new TextField();

		// Création info-bulle pour le champ de texte Prénom
		infoBullePrenom = new Tooltip(
				"Prénom ne comportant aucun caractère spécial, \n ni chiffre et ayant au moins 2 lettres");
		infoBullePrenom.setStyle("-fx-background-color : " + Couleur.SECONDARY);
		infoBullePrenom.setFont(Font.font("Regular", FontPosture.ITALIC, 12));
		champTextePrenom.setTooltip(infoBullePrenom);
		infoBullePrenom.setTextAlignment(TextAlignment.CENTER);

		// Création étiquette et bouton choix de département + fixation largeur
		etiquetteDepartement = new Label("Département");
		choixDepartement = new ComboBox<String>();
		choixDepartement.setMinWidth(LARGEUR_BOUTON);

		// Ajout des départements
		choixDepartement.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "2A", "2B", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
				"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64",
				"65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81",
				"82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "971", "972", "973",
				"974", "975", "976", "977", "978", "984", "986", "987", "988", "989");

		// **********************************************************
		// LIBELLE FORMATION : Cursus + Numéro promotion + Contrat
		// **********************************************************

		// Création étiquette du libellé de la formation
		etiquetteLibelleFormation = new Label("Cursus  /  Numéro  /  Contrat Pro");

		// Création bouton choix du cursus avec fixation de sa largeur
		choixCursus = new ComboBox<String>();
		choixCursus.setMinWidth(LARGEUR_BOUTON);

		// Ajout choix des cursus
		choixCursus.getItems().addAll("AI", "AL", "ATOD", "BOBI", "CDA");

		// Création bouton choix des numéros de promotions avec fixation de sa largeur
		choixNumero = new ComboBox<String>();
		choixNumero.setMinWidth(LARGEUR_BOUTON);

		// Ajout des numéros
		choixNumero.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");

		// Création bouton choix du contrat avec fixation de sa largeur
		choixContrat = new CheckBox(" CP");
		choixContrat.setMinWidth(LARGEUR_BOUTON);

		// Création étiquette et bouton de choix de l'année de formation avec fixation
		// de sa largeur
		etiquetteAnnee = new Label("Année de formation");
		choixAnnee = new ComboBox<String>();
		choixAnnee.setMinWidth(LARGEUR_BOUTON);

		// Ajout des années
		choixAnnee.getItems().addAll("2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
				"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022",
				"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030");

		// Création étiquette pour affichage message d'erreur à côté du bouton
		// Valider
		etiquetteMessageErreur = new Label();
		etiquetteMessageErreur.setTextFill(Color.web(Couleur.DANGER));
		etiquetteMessageErreur.setFont(Font.font("Regular", FontWeight.BOLD, 12));
		etiquetteMessageErreur.setAlignment(Pos.CENTER);

		// Création bouton Valider avec fixation de sa largeur
		boutonValider = new Button("Valider");
		boutonValider.setMinWidth(LARGEUR_BOUTON);

		// Remplissage bouton Valider avec couleur texte et fond
		boutonValider.setTextFill(Color.web(Couleur.LIGHT));
		boutonValider.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		boutonValider.setStyle("-fx-background-color : " + Couleur.PRIMARY);

		// Ajouts des étiquettes et boutons en tant que noeuds enfants du panneauRacine
		panneauRacine.add(etiquetteNom, 0, 0, 1, 1);
		panneauRacine.add(champTexteNom, 1, 0, 3, 1);
		panneauRacine.add(etiquettePrenom, 0, 1, 1, 1);
		panneauRacine.add(champTextePrenom, 1, 1, 3, 1);
		panneauRacine.add(etiquetteDepartement, 0, 2, 1, 1);
		panneauRacine.add(choixDepartement, 1, 2, 1, 1);
		panneauRacine.add(etiquetteLibelleFormation, 0, 3, 1, 1);
		panneauRacine.add(choixCursus, 1, 3, 1, 1);
		panneauRacine.add(choixNumero, 2, 3, 1, 1);
		panneauRacine.add(choixContrat, 3, 3, 1, 1);
		panneauRacine.add(etiquetteAnnee, 0, 4, 1, 1);
		panneauRacine.add(choixAnnee, 1, 4, 1, 1);
		panneauRacine.add(boutonValider, 0, 6, 1, 1);
		panneauRacine.add(etiquetteMessageErreur, 1, 6, 3, 1);
		// panneauRacine.setGridLinesVisible(true);

		// Ajouts de marges de bordure sur le panneauRacine (Haut,Droit,Bas,Gauche)
		// marges entre lignes et colonnes
		panneauRacine.setPadding(new Insets(15, 20, 10, 35));
		panneauRacine.setVgap(20);
		panneauRacine.setHgap(30);

		// ***************************
		// ACTIONS AVEC BOUTON VALIDER
		// ***************************

		// Déclenchement évènement à partir de l'action sur le bouton Valider
		boutonValider.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				// FORMATAGE DU NOM ET PRENOM

				// Suppression des espaces situés en début/fin du Nom
				nom = champTexteNom.getText().trim();

				// Conversion du mom en MAJUSCULES;
				nom = nom.toUpperCase();

				// Suppression des espaces situés au début/fin du Prénom + Majuscule sur 1ère
				// lettre et minuscules sur le reste
				prenom = champTextePrenom.getText().trim();
				
				if (prenom.length() > 2) {
					prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1).toLowerCase();
				}

				
				// Refus validation Nom/Prénom si caractères autres que :
				// lettres minuscules/Majuscules, espace, tiret, parenthèses et 16 lettres avec
				// signes
				// diacritiques (accents et cédille)

				if (((nom.matches("[A-Z, ,--]+"))) && (nom.length() > 1)
						&& ((prenom.matches("[a-zA-Z, ,--,(,),à,â,ä,é,è,ê,ë,î,ï,ô,ö,ù,û,ü,ÿ,ç]+")))) {

					try {
						annuaire.ajouterStagiaire(ajoutNouveauStagiaire());
						annuaire.ordreAlpha();
						listeObservableStagiaires.clear();
						listeObservableStagiaires.setAll(annuaire.getListeDeStagiaires());

						Window window = boutonValider.getScene().getWindow();
						Stage nouvelleFenetre = (Stage) boutonValider.getScene().getWindow();
						nouvelleFenetre.close();

						champTexteNom.clear();
						champTextePrenom.clear();
						choixDepartement.getSelectionModel().clearSelection();
						choixCursus.getSelectionModel().clearSelection();
						choixNumero.getSelectionModel().clearSelection();
						choixAnnee.getSelectionModel().clearSelection();

					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					etiquetteMessageErreur.setText(" Données non valides veuillez recommencer...");
				}

			}

		});
	}

	/*
	 * ajout d'un nouveau stagiaire avec au moins les champs noms et prénoms remplis
	 * et valides le nom sera tout en majuscule le prénom n'aura que la première
	 * lettre en majuscule
	 */
	public Stagiaire ajoutNouveauStagiaire() {
		String nom = champTexteNom.getText().trim().toUpperCase();
		String prenom = "";
		String contrat = "";
		String departement = "";
		String cursus = "";
		String numeroCursus = "";
		int annee = 2022;

		/*
		 * Vérification si les champs Departement/Cursus/Formation/Annee sont remplies
		 */

		if (champTextePrenom.getText().length() >= 2) {
			prenom = champTextePrenom.getText().substring(0, 1).toUpperCase()
					+ champTextePrenom.getText().substring(1).toLowerCase();
		}

		if (choixContrat.isSelected() == true) {
			contrat = "CP";
		}
		if (choixDepartement.getSelectionModel().getSelectedItem() != null) {
			departement = choixDepartement.getSelectionModel().getSelectedItem();
		}
		if (choixCursus.getSelectionModel().getSelectedItem() != null) {
			cursus = choixCursus.getSelectionModel().getSelectedItem();
		}
		if (choixNumero.getSelectionModel().getSelectedItem() != null) {
			numeroCursus = choixNumero.getSelectionModel().getSelectedItem();
		}
		if (choixAnnee.getSelectionModel().getSelectedItem() != null) {
			annee = (Integer.parseInt(choixAnnee.getSelectionModel().getSelectedItem()));
		}

		Stagiaire nouveauStagiaire = new Stagiaire(nom, prenom, departement,
				(cursus + " " + numeroCursus + " " + contrat), annee);

		return nouveauStagiaire;
	}

	// ******************
	// GETTERS ET SETTERS
	// ******************

	public TextField getChampTexteNom() {
		return champTexteNom;
	}

	public void setChampTexteNom(TextField champTexteNom) {
		this.champTexteNom = champTexteNom;
	}

	public TextField getChampTextePrenom() {
		return champTextePrenom;
	}

	public void setChampTextePrenom(TextField champTextePrenom) {
		this.champTextePrenom = champTextePrenom;
	}

	public ComboBox<String> getChoixDepartement() {
		return choixDepartement;
	}

	public void setChoixDepartement(ComboBox<String> choixDepartement) {
		this.choixDepartement = choixDepartement;
	}

	public ComboBox<String> getChoixCursus() {
		return choixCursus;
	}

	public void setChoixCursus(ComboBox<String> choixCursus) {
		this.choixCursus = choixCursus;
	}

	public ComboBox<String> getChoixNumero() {
		return choixNumero;
	}

	public void setChoixNumero(ComboBox<String> choixNumero) {
		this.choixNumero = choixNumero;
	}

	public CheckBox getChoixContrat() {
		return choixContrat;
	}

	public void setChoixContrat(CheckBox choixContrat) {
		this.choixContrat = choixContrat;
	}

	public ComboBox<String> getChoixAnnee() {
		return choixAnnee;
	}

	public void setChoixAnnee(ComboBox<String> choixAnnee) {
		this.choixAnnee = choixAnnee;
	}

	public Button getBoutonValider() {
		return boutonValider;
	}

	public void setBoutonValider(Button boutonValider) {
		this.boutonValider = boutonValider;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Stage getNouvelleFenetre() {
		return nouvelleFenetre;
	}

	public void setNouvelleFenetre(Stage nouvelleFenetre) {
		this.nouvelleFenetre = nouvelleFenetre;
	}

}