package fr.isika.cda21.projet1.annuaire.vues;

import java.io.IOException;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Couleur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

// Création classe FormulaireRechercher rattachée à la classe mère (super-classe) Scene
public class FormulaireRechercher extends Scene {

	// Déclaration des attributs
	private GridPane panneauRacine;
	private Label etiquetteNom;
	private TextField champTexteNom;
	private static final int LARGEUR_CHAMP_TEXTE = 250;
	static final int LARGEUR_BOUTON = 77;
	private Button boutonValider;
	private Label etiquetteMessageErreur;
	private static final String COULEUR_BG_WHITE = "#ffffff";
	private static final String COULEUR_BG_INFO = "#17a2b8";
	private static final String COULEUR_BG_SECONDARY = "#6c757d";
	private static final String COULEUR_BG_DANGER = "#dc3545";
	private static final String COULEUR_BG_SUCCESS = "#28a745";
	private String nom;	
	private Tooltip infoBulleNom;
	private Stage fenetreRechercher;
	private Annuaire annuaire;
	private TableView<Stagiaire> listeStagiaires;
	ObservableList<Stagiaire> listeObservableStagiaires;

	// Constructeur FormulaireRechercher
	// A partir de l'annuaire
	// avec une GridPane, dont on définit la distance entre noeuds enfants et la
	// taille
	public FormulaireRechercher(Annuaire annuaire, TableView<Stagiaire> listeStagiaires,
			ObservableList<Stagiaire> listObservableStagiaires) {
		super(new GridPane(), 400, 100);
		fenetreRechercher = new Stage();
		fenetreRechercher.setTitle(" Rechercher un stagiaire");
		fenetreRechercher.setX(710);
		fenetreRechercher.setY(290);

		this.annuaire = annuaire;
		this.listeObservableStagiaires = listObservableStagiaires;

		// Affectation à la racine du panneauRacine
		panneauRacine = ((GridPane) this.getRoot());
		panneauRacine.setStyle("-fx-background-color : floralwhite");

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
		infoBulleNom.setStyle("-fx-background-color : " + COULEUR_BG_SECONDARY);
		infoBulleNom.setFont(Font.font("Regular", FontPosture.ITALIC, 12));
		champTexteNom.setTooltip(infoBulleNom);
		infoBulleNom.setTextAlignment(TextAlignment.CENTER);

		// Création étiquette pour affichage message d'erreur à côté du bouton
		// Valider
		etiquetteMessageErreur = new Label();
		etiquetteMessageErreur.setTextFill(Color.web(COULEUR_BG_DANGER));
		etiquetteMessageErreur.setFont(Font.font("Regular", FontWeight.BOLD, 12));
		etiquetteMessageErreur.setTextAlignment(TextAlignment.CENTER);

		// Création bouton Valider avec fixation de sa largeur
		boutonValider = new Button("Valider");
		boutonValider.setMinWidth(LARGEUR_BOUTON);

		// Remplissage bouton Valider avec couleur texte et fond
		boutonValider.setTextFill(Color.web(COULEUR_BG_WHITE));
		boutonValider.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		boutonValider.setStyle("-fx-background-color : " + Color.web(Couleur.PRIMARY));

		// Ajouts des étiquettes et boutons en tant que noeuds enfants du panneauRacine
		panneauRacine.addRow(0, etiquetteNom, champTexteNom);
		panneauRacine.add(etiquetteMessageErreur, 1, 1);
		panneauRacine.add(boutonValider, 0, 1);

		// panneauRacine.setGridLinesVisible(true);

		// Ajouts de marges de bordure sur le panneauRacine
		// (marge haute / marge droite / marge basse / marge gauche)
		panneauRacine.setPadding(new Insets(15, 15, 15, 15));

		// Ajout de marges entre les étiquettes, les champs de texte et boutons du
		// panneauRacine
		panneauRacine.setVgap(20);
		panneauRacine.setHgap(30);

		// ***************************
		// ACTIONS AVEC BOUTON VALIDER
		// ***************************

		// Déclenchement évènement à partir de l'action sur le bouton Valider
		boutonValider.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				// FORMATAGE DU NOM

				// Suppression des espaces situés en début/fin du Nom
				nom = champTexteNom.getText().trim();

				// Conversion du mom en MAJUSCULES;
				nom = nom.toUpperCase();

				System.out.println("nom ="+nom);
				if (!(nom.equals(""))) {
					try {
						
						ArrayList<Noeud> listeNoeudSelectionne = new ArrayList<>();
						listeNoeudSelectionne = annuaire.getRechercherAnnuaire().rechercheParNom(nom);
						if (listeNoeudSelectionne==null) {
							etiquetteMessageErreur.setText("Aucun nom trouvé");	
						}
						else {
							ArrayList<Stagiaire> listeStagiaireSelectionne = new ArrayList<>();

							for (Noeud noeud : listeNoeudSelectionne) {
								listeStagiaireSelectionne.add(noeud.getCle());
							}
							listeObservableStagiaires.clear();
							listeObservableStagiaires.setAll(listeStagiaireSelectionne);		
							
							Window window = boutonValider.getScene().getWindow();
							Stage nouvelleFenetre = (Stage) boutonValider.getScene().getWindow();
							nouvelleFenetre.close();							
						}	

					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					etiquetteMessageErreur.setText("Saisir un nom");					
				}					
			}
		});
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

	public Button getBoutonValider() {
		return boutonValider;
	}

	public void setBoutonValider(Button boutonValider) {
		this.boutonValider = boutonValider;
	}

	public Stage getFenetreRechercher() {
		return fenetreRechercher;
	}

	public void setFenetreRechercher(Stage fenetreRechercher) {
		this.fenetreRechercher = fenetreRechercher;
	}

}