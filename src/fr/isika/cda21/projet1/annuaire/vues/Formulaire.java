package fr.isika.cda21.projet1.annuaire.vues;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// Création classe Formulaire rattachée à la classe mère (super-classe) Scene
public class Formulaire extends Scene {

	// Déclaration des attributs
	private VBox panneauRacine;
	private Label etiquetteNom;
	private TextField champTexteNom;
	private Label etiquettePrenom;
	private TextField champTextePrenom;
	private Label etiquetteDepartement;
	private ChoiceBox<String> choixDepartement;
	private Label etiquetteLibelleFormation;
	private ChoiceBox<String> choixFormation;
	private ChoiceBox<Integer> choixNumero;
	private ChoiceBox<String> choixContrat;
	private HBox libelleFormationHBox;
	private Label etiquetteAnnee;
	private ChoiceBox<Integer> choixAnnee;
	private Button boutonValider;
	private String test;

	// Constructeur Formulaire rattachée à la classe mère
	// avec une VBox dont on définit la distance entre noeuds enfants et la taille
	public Formulaire(Annuaire annuaire) {
		super(new VBox(10), 250, 500);

		// Affectation à la racine du panneauRacine de type VBox
		panneauRacine = ((VBox) this.getRoot());

		// INSTANCIATION DES ETIQUETTES, CHAMPS DE TEXTE ET BOUTONS

		// Nom
		etiquetteNom = new Label("Nom");
		champTexteNom = new TextField();
		// Prénom
		etiquettePrenom = new Label("Prenom");
		champTextePrenom = new TextField();
		// Département
		etiquetteDepartement = new Label("Département");
		choixDepartement = new ChoiceBox<String>();

		// Ajustage taille du menu déroulant de la ChoiceBox
		// choixDepartement.set????(100);
		// **************A FAIRE***************

		// Ajout des départements (ObservableList à paramétrer ????)
		choixDepartement.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "2A", "2B", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
				"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64",
				"65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81",
				"82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "971", "972", "973",
				"974", "975", "976", "977", "978", "984", "986", "987", "988", "989");

		// LIBELLE FORMATION : Formation + Numéro promotion + Contrat
		// Professionnalisation
		etiquetteLibelleFormation = new Label("Formation  /  Numéro  /  Contrat Pro");

		// Bouton choix Formation
		choixFormation = new ChoiceBox<String>();
		// Ajout des formations
		choixFormation.getItems().addAll("AI", "ATOD", "BOBI");

		// Bouton choix des numéros de promotions
		choixNumero = new ChoiceBox<Integer>();
		// Ajout des numéros
		choixNumero.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24, 25, 26, 27, 28, 29, 30);

		// choixNumero.setMaxHeight???(70);
		// **********************************

		// Bouton choix contrat
		choixContrat = new ChoiceBox<String>();
		// Ajout des contrats
		choixContrat.getItems().addAll(" ", "CP");
		// Affichage par défaut du choix vide
		choixContrat.getSelectionModel().select(0);

		// Création HBox avec regroupement des 3 boutons de choix
		libelleFormationHBox = new HBox();
		libelleFormationHBox.getChildren().addAll(choixFormation, choixNumero, choixContrat);
		// Centrage de la HBox
		libelleFormationHBox.setAlignment(Pos.CENTER);

		// Récupération valeurs des 3 boutons et concaténation
		// ********************** A FAIRE ***********************

		// Année
		etiquetteAnnee = new Label("Année");
		choixAnnee = new ChoiceBox<Integer>();

		// Ajout des années
		choixAnnee.getItems().addAll(2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013,
				2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030);

		// Bouton Valider
		boutonValider = new Button("Valider");

		// Ajouts des étiquettes et boutons en tant que noeuds enfants du panneauRacine
		// de type HBox
		panneauRacine.getChildren().addAll(etiquetteNom, champTexteNom, etiquettePrenom, champTextePrenom,
				etiquetteDepartement, choixDepartement, etiquetteLibelleFormation, libelleFormationHBox, etiquetteAnnee,
				choixAnnee, boutonValider);

		// Centrage des noeuds du panneauRacine
		panneauRacine.setAlignment(Pos.CENTER);

//***********************************************************
		// ACTIONS AVEC BOUTON VALIDER
		boutonValider.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				test = champTexteNom.getText();

				System.out.println("j'ai validé");
				System.out.println("champTexteNom : " + champTexteNom);
				System.out.println("champTexteNom.getText() : " + champTexteNom.getText());
				System.out.println("champTexteNom.getText().trim : " + champTexteNom.getText().trim());

				if ((!(champTexteNom.getText().matches("[a-zA-Z]+"))) || (champTexteNom.getLength() < 2)) {
					System.out.println(" Veuillez entrer un nom valide \n sans caractère spécial, ni chiffre");
				}

				if ((!(champTextePrenom.getText().matches("[a-zA-Z]+"))) || (champTextePrenom.getLength() < 2)) {
					System.out.println(" Veuillez entrer un prénom valide \n sans caractère spécial, ni chiffre");
				}
				

//	 			primaryStage.setScene(sceneFormulaireValide);
//				primaryStage.show();

			}

		});

	}

	// GETTERS ET SETTERS

	public Label getEtiquetteNom() {
		return etiquetteNom;
	}

	public void setEtiquetteNom(Label etiquetteNom) {
		this.etiquetteNom = etiquetteNom;
	}

	public TextField getChampTexteNom() {
		return champTexteNom;
	}

	public void setChampTexteNom(TextField champTexteNom) {
		this.champTexteNom = champTexteNom;
	}

	public Label getEtiquettePrenom() {
		return etiquettePrenom;
	}

	public void setEtiquettePrenom(Label etiquettePrenom) {
		this.etiquettePrenom = etiquettePrenom;
	}

	public TextField getChampTextePrenom() {
		return champTextePrenom;
	}

	public void setChampTextePrenom(TextField champTextePrenom) {
		this.champTextePrenom = champTextePrenom;
	}

	public Label getEtiquetteDepartement() {
		return etiquetteDepartement;
	}

	public void setEtiquetteDepartement(Label etiquetteDepartement) {
		this.etiquetteDepartement = etiquetteDepartement;
	}

	public ChoiceBox<String> getChoixDepartement() {
		return choixDepartement;
	}

	public void setChoixDepartement(ChoiceBox<String> choixDepartement) {
		this.choixDepartement = choixDepartement;
	}

	public Label getEtiquetteInfosFormation() {
		return etiquetteLibelleFormation;
	}

	public void setEtiquetteInfosFormation(Label etiquetteInfosFormation) {
		this.etiquetteLibelleFormation = etiquetteInfosFormation;
	}

	public ChoiceBox<String> getChoixFormation() {
		return choixFormation;
	}

	public void setChoixFormation(ChoiceBox<String> choixFormation) {
		this.choixFormation = choixFormation;
	}

	public ChoiceBox<Integer> getChoixNumero() {
		return choixNumero;
	}

	public void setChoixNumero(ChoiceBox<Integer> choixNumero) {
		this.choixNumero = choixNumero;
	}

	public ChoiceBox<String> getChoixContrat() {
		return choixContrat;
	}

	public void setChoixContrat(ChoiceBox<String> choixContrat) {
		this.choixContrat = choixContrat;
	}

	public HBox getInfosFormationHBox() {
		return libelleFormationHBox;
	}

	public void setInfosFormationHBox(HBox infosFormationHBox) {
		this.libelleFormationHBox = infosFormationHBox;
	}

	public Label getEtiquetteAnnee() {
		return etiquetteAnnee;
	}

	public void setEtiquetteAnnee(Label etiquetteAnnee) {
		this.etiquetteAnnee = etiquetteAnnee;
	}

	public ChoiceBox<Integer> getChoixAnnee() {
		return choixAnnee;
	}

	public void setChoixAnnee(ChoiceBox<Integer> choixAnnee) {
		this.choixAnnee = choixAnnee;
	}

	public Button getBoutonValider() {
		return boutonValider;
	}

	public void setBoutonValider(Button boutonValider) {
		this.boutonValider = boutonValider;
	}

}