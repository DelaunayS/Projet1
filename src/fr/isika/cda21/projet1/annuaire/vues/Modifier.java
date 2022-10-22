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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Modifier extends Scene {

	/*----------------- DECLARATION DES ATTRIBUTS ----------------- */

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
	private static final int LARGEUR_BOUTON_CURSUS = 50;
	private static final int LARGEUR_BOUTON = LARGEUR_BOUTON_CURSUS + 27;
	private static final int ESPACE_ENTRE_BOUTONS = (LARGEUR_CHAMP_TEXTE - 3 * LARGEUR_BOUTON_CURSUS) / 2;
	private HBox libelleFormationHBox;
	private Label etiquetteAnnee;
	private ComboBox<String> choixAnnee;
	private Button boutonValider;
	private Label etiquetteMessageErreur;
	private String nom;
	private String prenom;
	private Tooltip infoBulleNom;
	private Tooltip infoBullePrenom;
	private DropShadow effetOmbrePortee;
	private Annuaire annuaire;
	private TableView<Stagiaire> listeStagiaires;
	private Stage fenetreModifier;
	ObservableList<Stagiaire> listeObservableStagiaires;
	@SuppressWarnings("unused")
	private Label modeAdmin;

	/*----------------- CONSTRUCTEUR SURCHARGE ----------------- */

	public Modifier(Annuaire annuaire, TableView<Stagiaire> listeStagiaires,
			ObservableList<Stagiaire> listeObservableStagiaires,Label modeAdmin) {
		super(new GridPane(), 580, 300);
		fenetreModifier = new Stage();
		fenetreModifier.setTitle("Modifier un stagiaire");
		panneauRacine = ((GridPane) this.getRoot());
		panneauRacine.setStyle("-fx-background-color :"+Couleur.FLORAL);
		etiquetteNom = new Label("NOM");
		champTexteNom = new TextField();
		champTexteNom.setMinWidth(250);
		etiquettePrenom = new Label("Prénom");
		champTextePrenom = new TextField();
		etiquetteDepartement = new Label("Département : ");
		choixDepartement = new ComboBox<String>();
		choixDepartement.setMinWidth(83);
		etiquetteLibelleFormation = new Label("Cursus / Numéro / Contrat Pro");
		choixCursus = new ComboBox<String>();
		choixNumero = new ComboBox<String>();
		choixContrat = new CheckBox();
		libelleFormationHBox = new HBox();
		etiquetteAnnee = new Label("Année de formation");
		choixAnnee = new ComboBox<String>();
		boutonValider = new Button("Valider");
		nom = champTexteNom.getText();
		nom = nom.trim();
		nom = nom.toUpperCase();
		prenom = champTextePrenom.getText();
		prenom = prenom.trim();
		this.listeObservableStagiaires = listeObservableStagiaires;
		this.modeAdmin=modeAdmin;

		/*----------------- PARAMETRAGE DES ATTRIBUTS ----------------- */

		etiquetteNom.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		etiquettePrenom.setFont(Font.font("Regular", FontWeight.BOLD, 13));

		/*----------------- CREATION INFO-BULLE POUR LES CHAMPS DE TEXTE ----------------- */

		// Création info-bulle pour le champ de texte Nom

		infoBulleNom = new Tooltip(
				"Nom ne comportant aucun caractère spécial, ni chiffre,\n ni accent/cédille et ayant au moins 2 lettres");
		infoBulleNom.setStyle("-fx-background-color : " + Couleur.SECONDARY);
		infoBulleNom.setFont(Font.font("Regular", FontPosture.ITALIC, 12));
		champTexteNom.setTooltip(infoBulleNom);
		infoBulleNom.setTextAlignment(TextAlignment.CENTER);

		// Création info-bulle pour le champ de texte Prénom

		infoBullePrenom = new Tooltip(
				"Prénom ne comportant aucun caractère spécial, \n ni chiffre et ayant au moins 2 lettres");
		infoBullePrenom.setStyle("-fx-background-color : " + Couleur.SECONDARY);
		infoBullePrenom.setFont(Font.font("Regular", FontPosture.ITALIC, 12));
		champTextePrenom.setTooltip(infoBullePrenom);
		infoBullePrenom.setTextAlignment(TextAlignment.CENTER);

		/*----------------- CREATION ETIQUETTE ET CHAMP DE TEXTE A SAISIR ----------------- */

		// Ajout des départements (ObservableList à paramétrer ????)
		choixDepartement.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "2A", "2B", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
				"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64",
				"65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81",
				"82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "971", "972", "973",
				"974", "975", "976", "977", "978", "984", "986", "987", "988", "989");

		choixCursus.setMinWidth(LARGEUR_BOUTON_CURSUS);
		choixCursus.setMinWidth(LARGEUR_BOUTON_CURSUS);
		choixCursus.getItems().addAll("AI", "AL", "ATOD", "BOBI", "CDA");
		choixNumero.setMinWidth(LARGEUR_BOUTON_CURSUS);

		choixNumero.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");

		// Création bouton choix du contrat avec fixation de sa largeur

		choixContrat = new CheckBox(" CP");
		choixContrat.setMinWidth(LARGEUR_BOUTON_CURSUS);

		// Création HBox avec regroupement des 3 boutons de choix

		libelleFormationHBox = new HBox();
		libelleFormationHBox.setMinWidth(LARGEUR_CHAMP_TEXTE);
		libelleFormationHBox.setSpacing(ESPACE_ENTRE_BOUTONS);
		libelleFormationHBox.setAlignment(Pos.CENTER);
		libelleFormationHBox.getChildren().addAll(choixCursus, choixNumero, choixContrat);

		// Création étiquette et bouton de choix de l'année de formation avec fixation
		// de sa largeur (250/3)

		choixAnnee.setMinWidth(LARGEUR_BOUTON);

		// Ajout des années
		choixAnnee.getItems().addAll("2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
				"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022",
				"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030");

		// Création étiquette pour affichage message d'erreur au dessus du bouton
		// Valider
		etiquetteMessageErreur = new Label();
		etiquetteMessageErreur.setTextFill(Color.web(Couleur.DANGER));
		etiquetteMessageErreur.setFont(Font.font("Regular", FontWeight.BOLD, 12));

		// Création bouton Valider avec fixation de sa largeur (250/3)

		boutonValider.setMinWidth(83);

		// Remplissage bouton Valider avec couleur texte et fond

		boutonValider.setTextFill(Color.web(Couleur.LIGHT));
		boutonValider.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		boutonValider.setStyle("-fx-background-color : " + Couleur.PRIMARY);

		// Ajouts des étiquettes et boutons en tant que noeuds enfants du panneauRacine
		panneauRacine.addRow(0, etiquetteNom, champTexteNom);
		panneauRacine.addRow(1, etiquettePrenom, champTextePrenom);
		panneauRacine.addRow(2, etiquetteDepartement, choixDepartement);
		panneauRacine.addRow(3, etiquetteLibelleFormation, libelleFormationHBox);
		panneauRacine.addRow(4, etiquetteAnnee, choixAnnee);
		panneauRacine.addRow(5, etiquetteMessageErreur);
		panneauRacine.addRow(6, boutonValider);

		panneauRacine.setPadding(new Insets(15, 15, 15, 15));

		panneauRacine.setVgap(20);
		panneauRacine.setHgap(30);

		/*----------------- JE RECUPERE LES INFOS DE LA LIGNE SUR LAQUELLE JE SUIS POSITIONNEE ----------------- */

		/* on crée un nouveau stagiaire sélectionné */

		/* on lui attribue tous les champs de la ligne sur laquelle on se trouve */

		Stagiaire stagiaireselectionne = new Stagiaire();
		String selectedFormation = listeStagiaires.getSelectionModel().getSelectedItem().getLibelleFormation();
		String[] attributsFormation;

		stagiaireselectionne.setNom(listeStagiaires.getSelectionModel().getSelectedItem().getNom());
		champTexteNom.setText(stagiaireselectionne.getNom());

		stagiaireselectionne.setPrenom(listeStagiaires.getSelectionModel().getSelectedItem().getPrenom());
		champTextePrenom.setText(stagiaireselectionne.getPrenom());

		if (listeStagiaires.getSelectionModel().getSelectedItem().getDepartement() == (null)) {
			stagiaireselectionne.setDepartement("");
		} else {
			stagiaireselectionne.setDepartement(listeStagiaires.getSelectionModel().getSelectedItem().getDepartement());
			choixDepartement.setValue(stagiaireselectionne.getDepartement());
		}

		String nomFormation = "";
		String promo = "";
		String contrat = "";

		if (listeStagiaires.getSelectionModel().getSelectedItem().getLibelleFormation().equals("")) {
			stagiaireselectionne.setLibelleFormation("");
		} else {
			attributsFormation = selectedFormation.split(" ");

			if (!attributsFormation[0].equals("")) {
				nomFormation = attributsFormation[0];
				choixCursus.setValue(nomFormation);
			}

			if (attributsFormation.length == 2) {
				if (!attributsFormation[1].equals("")) {
					promo = attributsFormation[1];
					choixNumero.setValue(promo);
				}
			} else if (attributsFormation.length == 3) {
				if (!attributsFormation[1].equals("")) {
					promo = attributsFormation[1];
					choixNumero.setValue(promo);
				}
				if (!attributsFormation[2].equals("")) {
					if (attributsFormation[2].equals("CP")) {
						choixContrat.setSelected(true);
					}
				}

			}
			stagiaireselectionne.setLibelleFormation(nomFormation + " " + promo + " " + contrat);

		}

		stagiaireselectionne.setAnnee(listeStagiaires.getSelectionModel().getSelectedItem().getAnnee());
		choixAnnee.setValue(String.valueOf(stagiaireselectionne.getAnnee()));

		/*---------------- PARAMETRAGE DU BOUTON VALIDER ----------------- */

		boutonValider.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				Stagiaire stagiaireModifie = new Stagiaire();

				stagiaireModifie.setNom(champTexteNom.getText());
				stagiaireModifie.setPrenom(champTextePrenom.getText());
				stagiaireModifie.setAnnee(Integer.parseInt(choixAnnee.getSelectionModel().getSelectedItem()));

				if (choixDepartement.getSelectionModel().getSelectedItem() == null) {
					stagiaireModifie.setDepartement("");
				} else {
					stagiaireModifie.setDepartement(choixDepartement.getSelectionModel().getSelectedItem());
				}

				if (choixCursus.getSelectionModel().getSelectedItem() == null) {
					stagiaireModifie.setLibelleFormation("");
				} else {
					stagiaireModifie.setLibelleFormation(choixCursus.getSelectionModel().getSelectedItem());
				}
				if (choixNumero.getSelectionModel().getSelectedItem() == null) {
					stagiaireModifie.setLibelleFormation(stagiaireModifie.getLibelleFormation());
				} else {
					stagiaireModifie.setLibelleFormation(stagiaireModifie.getLibelleFormation() + " "
							+ choixNumero.getSelectionModel().getSelectedItem());
				}
				if (choixContrat.isSelected()) {
					stagiaireModifie.setLibelleFormation(stagiaireModifie.getLibelleFormation() + " CP");
				}

				try {

					/* je supprime le stagiaire sélectionné */

					annuaire.supprimerStagiaire(stagiaireselectionne);

					/*
					 * j'ajoute un nouveau stagiaire dans l'annuaire avec les informations du
					 * stagiaire sélectionné je fais appel à la méthode ajoutStagiaireModifie qui
					 * reprend les cases mises à jour du stagiaire sélectionné
					 */

					annuaire.getAjouterAnnuaire().ajouterStagiaire(stagiaireModifie);

					/* je mets la liste dans l'ordre, cela me crée une nouvelle liste (ArrayList) */

					annuaire.ordreAlpha();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/* j'enlève l'ancienne liste observable */

				listeObservableStagiaires.clear();
				listeObservableStagiaires.setAll(annuaire.getListeDeStagiaires());
				modeAdmin.setText("Modification réussie");
				modeAdmin.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
				modeAdmin.setTextFill(Color.web(Couleur.SUCCESS));
				
				/* fermeture de la fenêtre */
				@SuppressWarnings("unused")
				Window window = boutonValider.getScene().getWindow();
				Stage nouvelleFenetre = (Stage) boutonValider.getScene().getWindow();
				nouvelleFenetre.close();

				// effacement de tous les champs
				champTexteNom.clear();
				champTextePrenom.clear();
				choixDepartement.getSelectionModel().clearSelection();
				choixCursus.getSelectionModel().clearSelection();
				choixNumero.getSelectionModel().clearSelection();

			}

		});
	}

	public GridPane getPanneauRacine() {
		return panneauRacine;
	}

	public void setPanneauRacine(GridPane panneauRacine) {
		this.panneauRacine = panneauRacine;
	}

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

	public ComboBox<String> getChoixDepartement() {
		return choixDepartement;
	}

	public void setChoixDepartement(ComboBox<String> choixDepartement) {
		this.choixDepartement = choixDepartement;
	}

	public Label getEtiquetteLibelleFormation() {
		return etiquetteLibelleFormation;
	}

	public void setEtiquetteLibelleFormation(Label etiquetteLibelleFormation) {
		this.etiquetteLibelleFormation = etiquetteLibelleFormation;
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

	public HBox getLibelleFormationHBox() {
		return libelleFormationHBox;
	}

	public void setLibelleFormationHBox(HBox libelleFormationHBox) {
		this.libelleFormationHBox = libelleFormationHBox;
	}

	public Label getEtiquetteAnnee() {
		return etiquetteAnnee;
	}

	public void setEtiquetteAnnee(Label etiquetteAnnee) {
		this.etiquetteAnnee = etiquetteAnnee;
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

	public Label getEtiquetteMessageErreur() {
		return etiquetteMessageErreur;
	}

	public void setEtiquetteMessageErreur(Label etiquetteMessageErreur) {
		this.etiquetteMessageErreur = etiquetteMessageErreur;
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

	public Tooltip getInfoBulleNom() {
		return infoBulleNom;
	}

	public void setInfoBulleNom(Tooltip infoBulleNom) {
		this.infoBulleNom = infoBulleNom;
	}

	public Tooltip getInfoBullePrenom() {
		return infoBullePrenom;
	}

	public void setInfoBullePrenom(Tooltip infoBullePrenom) {
		this.infoBullePrenom = infoBullePrenom;
	}

	public DropShadow getEffetOmbrePortee() {
		return effetOmbrePortee;
	}

	public void setEffetOmbrePortee(DropShadow effetOmbrePortee) {
		this.effetOmbrePortee = effetOmbrePortee;
	}

	public Annuaire getAnnuaire() {
		return annuaire;
	}

	public void setAnnuaire(Annuaire annuaire) {
		this.annuaire = annuaire;
	}

	public Stage getFenetreModifier() {
		return fenetreModifier;
	}

	public void setFenetreModifier(Stage fenetreModifier) {
		this.fenetreModifier = fenetreModifier;
	}

	public TableView<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}

	public void setListeStagiaires(TableView<Stagiaire> listeStagiaires) {
		this.listeStagiaires = listeStagiaires;
	}

}