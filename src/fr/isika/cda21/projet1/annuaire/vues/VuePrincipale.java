package fr.isika.cda21.projet1.annuaire.vues;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Couleur;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VuePrincipale extends Scene {

	// ----------------------- ATTRIBUTS -----------------------//

	private BorderPane root;
	private BorderPane panneauHaut;
	private VBox conteneurImage;
	private VBox conteneurLogin;
	private HBox ligneVisiteur;
	private HBox ligneAdmin;
	private HBox conteneurBarreDeRecherche;
	private HBox panneauBas;
	private Button ajouter;
	private Button rechercher;
	private Button impression;
	private Button aide;
	private Button modifier;
	private Button supprimer;
	private Button visiteur;
	private Button admin;
	private Button quitter;
	private PasswordField codeAdmin;
	private Label modeAdmin;
	private TableView<Stagiaire> listeStagiaires;
	private TableColumn<Stagiaire, String> colonneNom;
	private TableColumn<Stagiaire, String> colonnePrenom;
	private TableColumn<Stagiaire, String> colonneDepartement;
	private TableColumn<Stagiaire, String> colonneFormation;
	private TableColumn<Stagiaire, Integer> colonneAnnee;
	private String motDePasse;
	private VBox margeGauche;
	private VBox margeDroite;
	private Annuaire annuaire;
	private Stage primaryStage;
	private FormulaireAjouter nouveauFormulaire;
	private FormulaireRechercher nouvelleRecherche;
	private TextField barreDeRecherche;
	@SuppressWarnings("unused")
	private Label rechercheAvancee;
	private Label nombreTotalStagiaires;
	private Button rafraichir;

	// ----------------------- CONSTRUCTEUR -----------------------//

	@SuppressWarnings({ "static-access", "unchecked" })
	public VuePrincipale(Annuaire annuaire, Stage primaryStage) throws IOException, DocumentException {
		super(new BorderPane(), 650, 650);
		root = ((BorderPane) this.getRoot());
		panneauHaut = new BorderPane();
		conteneurLogin = new VBox();
		ligneVisiteur = new HBox();
		ligneAdmin = new HBox();
		panneauBas = new HBox();
		ajouter = new Button("Ajouter");
		rechercher = new Button("Rechercher");
		impression = new Button("Impression");
		aide = new Button("Aide");
		quitter = new Button("Quitter");
		modifier = new Button("Modifier");
		modifier.setVisible(false);
		supprimer = new Button("Supprimer");
		supprimer.setVisible(false);
		visiteur = new Button("Visiteur");
		admin = new Button("Administrateur");
		codeAdmin = new PasswordField();
		codeAdmin.setPromptText("Mot de passe requis");
		modeAdmin = new Label();
		listeStagiaires = new TableView<Stagiaire>();
		colonneNom = new TableColumn<Stagiaire, String>("Nom");
		colonnePrenom = new TableColumn<Stagiaire, String>("Prénom");
		colonneDepartement = new TableColumn<Stagiaire, String>("Département");
		colonneFormation = new TableColumn<Stagiaire, String>("Formation");
		colonneAnnee = new TableColumn<Stagiaire, Integer>("Année");
		margeGauche = new VBox();
		margeDroite = new VBox();
		motDePasse = "CDA21";
		barreDeRecherche = new TextField();
		conteneurBarreDeRecherche = new HBox();
		conteneurImage = new VBox();
		nombreTotalStagiaires = new Label();
		rafraichir = new Button("Rafraichir la page");

		// ----------------------- INTEGRATION DES COMPOSANTES AUX CONTENEURS
		// -----------------------//

		/* intégration des conteneurs à la racine */
		root.setBottom(panneauBas);
		root.setCenter(listeStagiaires);
		root.setLeft(margeGauche);
		root.setRight(margeDroite);
		root.setTop(panneauHaut);
		root.setAlignment(panneauHaut, Pos.TOP_CENTER);
		margeGauche.setPadding(new Insets(10, 10, 10, 10));
		margeDroite.setPadding(new Insets(10, 10, 10, 10));

		// ajout du logo ISIKA
		FileInputStream inputstream = new FileInputStream("src/fr/isika/cda21/projet1/annuaire/utilitaires/logo.png");
		Image logo = new Image(inputstream);
		ImageView logoView = new ImageView(logo);
		logoView.setFitHeight(200);
		logoView.setFitWidth(270);

		/*
		 * les VBox du panneau haut du BorderPane conteneur de la partie login conteneur
		 * du logo
		 */
		ligneVisiteur.getChildren().add(visiteur);
		ligneAdmin.getChildren().addAll(admin, codeAdmin);
		ligneAdmin.setSpacing(5);
		conteneurLogin.getChildren().addAll(ligneVisiteur, ligneAdmin, modeAdmin);
		conteneurLogin.setSpacing(5);
		conteneurLogin.setPadding(new Insets(20, 20, 20, 20));
		conteneurLogin.setAlignment(Pos.CENTER_LEFT);
		conteneurImage.getChildren().add(logoView);

		/* panneau bas du BorderPane */
		panneauBas.getChildren().addAll(ajouter, rechercher, modifier, supprimer, impression, aide, quitter);
		panneauBas.setMinSize(60, 60);
		panneauBas.setAlignment(Pos.CENTER);
		panneauBas.setSpacing(15);

		/* panneau haut du BorderPane */
		panneauHaut.setLeft(conteneurLogin);
		panneauHaut.setAlignment(conteneurLogin, Pos.CENTER_LEFT);
		panneauHaut.setRight(conteneurImage);
		panneauHaut.setAlignment(conteneurImage, Pos.CENTER_RIGHT);
		conteneurBarreDeRecherche.getChildren().addAll(barreDeRecherche);
		panneauHaut.setBottom(conteneurBarreDeRecherche);
		panneauHaut.setAlignment(conteneurBarreDeRecherche, Pos.BOTTOM_CENTER);

		/*
		 * conteneur HBox de la barre de recherche intégration de la barre de recherche
		 * dans le conteneur
		 */
		conteneurBarreDeRecherche.setAlignment(Pos.BOTTOM_CENTER);
		conteneurBarreDeRecherche.setHgrow(barreDeRecherche, Priority.ALWAYS);
		conteneurBarreDeRecherche.setPadding(new Insets(0, 20, 0, 20));

		// ----------------------- PARAMETRAGE DE LA TABLEVIEW -----------------------//

		// on remplit les colonnes du tableau par les paramètres de la classe Stagiaire
		colonneNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		colonneFormation.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("libelleFormation"));
		colonneAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("annee"));

		// on ajoute les colonnes au tableau
		listeStagiaires.getColumns().addAll(colonneNom, colonnePrenom, colonneDepartement, colonneFormation,
				colonneAnnee);

		// on définit la largeur des colonnes
		colonneNom.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.25));
		colonnePrenom.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.25));
		colonneDepartement.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.15));
		colonneFormation.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.2));
		colonneAnnee.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.15));

		/*
		 * On met l'annuaire dans l'ordre alphabétique et on crée une liste observable à
		 * laquelle on donne l'annuaire (sous forme de liste)
		 */
		annuaire.ordreAlpha();
		ObservableList<Stagiaire> listeObservableStagiaires = FXCollections
				.observableArrayList(annuaire.getListeDeStagiaires());

		/* On implémente la tableView avec la liste observable des stagiaires */
		listeStagiaires.getItems().addAll(listeObservableStagiaires);
		nombreTotalStagiaires = new Label("Nombre total de stagiaires : " + listeStagiaires.getItems().size());
		conteneurImage.getChildren().add(nombreTotalStagiaires);
		conteneurImage.setAlignment(Pos.BOTTOM_RIGHT);
		nombreTotalStagiaires.setPadding(new Insets(10, 20, 0, 10));

		// ----------------------- BOUTONS -----------------------//

		/* connexion en mode administrateur */
		admin.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {

				/* si le mot de passe est correct */

				if (codeAdmin.getText().equals(motDePasse)) {
					codeAdmin.setText(null);

					/* les boutons modifier et supprimer s'affichent */

					modifier.setVisible(true);
					supprimer.setVisible(true);
					modeAdmin.setText("Vous êtes en mode administrateur");
					modeAdmin.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
					modeAdmin.setTextFill(Color.web(Couleur.SUCCESS));

				} else { /* sinon message d'erreur et les boutons restent cachés */

					modeAdmin.setText("Mot de passe erroné. Veuillez réessayer.");
					modeAdmin.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
					modeAdmin.setTextFill(Color.web(Couleur.DANGER));
				}
			}
		});

		/*
		 * retour au mode visiteur permet de se déconnecter du mode administrateur
		 */
		visiteur.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				/* les boutons modifier et supprimer deviennent à nouveau invisibles */
				modifier.setVisible(false);
				supprimer.setVisible(false);
				modeAdmin.setText(null);
				;
			}
		});

		/* ajout d'un nouveau stagiaire */
		ajouter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				/* créatioon d'une nouvelle scène avec le formulaire */
				nouveauFormulaire = new FormulaireAjouter(annuaire, listeStagiaires, listeObservableStagiaires);
				/* ouverture d'une nouvelle fenêtre */
				nouveauFormulaire.getNouvelleFenetre().setScene(nouveauFormulaire);
				/* affichage de la fenêtre */
				nouveauFormulaire.getNouvelleFenetre().show();
			}
		});

		/* rechercher un stagiaire */
		rechercher.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				/* création d'une nouvelle scène avec le formulaire */
				nouvelleRecherche = new FormulaireRechercher(annuaire, listeStagiaires, listeObservableStagiaires);
				/* ouverture d'une nouvelle fenêtre */
				nouvelleRecherche.getFenetreRechercher().setScene(nouvelleRecherche);
				/* affichage de la fenêtre */
				nouvelleRecherche.getFenetreRechercher().show();
			}
		});

		/* modifier un stagiaire */
		modifier.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!(listeStagiaires.getSelectionModel().isEmpty())) {
					modeAdmin.setText("");
					/* création d'une nouvelle scène avec le formulaire */
					Scene modifier = new Modifier(annuaire, listeStagiaires, listeObservableStagiaires);
					/* création d'une nouvelle fenêtre */
					((Modifier) modifier).getFenetreModifier().setScene(modifier);
					((Modifier) modifier).getFenetreModifier().show();
				}
				modeAdmin.setText("Veuillez sélectionner la ligne à modifier.");
				modeAdmin.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
				modeAdmin.setTextFill(Color.web(Couleur.DANGER));
			}
		});

		/* suppression d'un stagiaire */
		supprimer.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (!(listeStagiaires.getSelectionModel().isEmpty())) {
					modeAdmin.setText("");
					/* créatioon d'une nouvelle scène avec le formulaire */
					Scene supprimer = new Supprimer(annuaire, listeStagiaires, listeObservableStagiaires);
					/* création d'une nouvelle fenêtre */
					Stage stage = new Stage();
					stage.setTitle("Supprimer un stagiaire");
					/* ouverture d'une nouvelle fenêtre */
					stage.setScene(supprimer);
					/* affichage de la fenêtre */
					stage.show();
				}
				modeAdmin.setText("Veuillez sélectionner la ligne à supprimer.");
				modeAdmin.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
				modeAdmin.setTextFill(Color.web(Couleur.SUCCESS));
			}
		});

		/* clic sur le bouton aide */
		aide.setOnAction(eventAction -> {
			Scene scene;
			try {
				scene = new AideVue(annuaire, primaryStage);
				Stage stage = (Stage) VuePrincipale.this.getRoot().getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		conteneurLogin.getChildren().add(rafraichir);
		rafraichir.setAlignment(Pos.BOTTOM_LEFT);

		rafraichir.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Platform.runLater(() -> {
					listeStagiaires.setItems(listeObservableStagiaires);
				});
			}

		});

		/* fermer l'application */
		quitter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				/* fermeture de la fenêtre */
				((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
			}
		});

		/* imprimer la liste */

		impression.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Document fichier = new Document();
				ArrayList<Stagiaire> listeAimprimer = new ArrayList<>();
				listeAimprimer.addAll(listeStagiaires.getItems());
				PdfPTable table = new PdfPTable(5);
				table.setWidthPercentage(100);
				

				@SuppressWarnings("unused")
				PdfPCell cell = null;

				table.addCell(getCell("NOM", PdfPCell.ALIGN_CENTER));
				table.addCell(getCell("PRENOM", PdfPCell.ALIGN_CENTER));
				table.addCell(getCell("DEPARTEMENT", PdfPCell.ALIGN_CENTER));
				table.addCell(getCell("FORMATION", PdfPCell.ALIGN_CENTER));
				table.addCell(getCell("ANNEE", PdfPCell.ALIGN_CENTER));

				try {
					PdfWriter.getInstance(fichier, new FileOutputStream(
							"./src/fr/isika/cda21/projet1/annuaire/utilitaires/Liste_Stagiaires.pdf"));
					fichier.open();
					
					Paragraph titre = new Paragraph("LISTE DES STAGIAIRES");
					titre.setAlignment(Element.ALIGN_CENTER);
					fichier.add(titre);					
					fichier.add(new Paragraph(" "));

					for (int j = 0; j < listeAimprimer.size(); j++) {

						table.addCell(listeAimprimer.get(j).getNom());
						table.addCell(listeAimprimer.get(j).getPrenom());
						table.addCell(listeAimprimer.get(j).getDepartement());
						table.addCell(listeAimprimer.get(j).getLibelleFormation());
						table.addCell(String.valueOf(listeAimprimer.get(j).getAnnee()));

					}
					fichier.add(table);

					fichier.close();

					Stage popupStage = new Stage();
					Scene imprimer = new Impression();
					popupStage.setScene(imprimer);
					popupStage.show();

					PauseTransition wait = new PauseTransition(Duration.seconds(2));
					wait.setOnFinished((e) -> {						
						popupStage.close();
					});
					wait.play();
					
//					try {
//						Process lancement = Runtime
//								   .getRuntime()
//								   .exec("rundll32 url.dll,FileProtocolHandler "
//								   		+ "C:\\Users\\annak\\JavaFXprojects\\Projet1\\Projet1\\src\\fr\\isika\\cda21\\projet1\\annuaire\\utilitaires\\Liste_Stagiaires.pdf");
//						lancement.waitFor();
//					} catch (InterruptedException e1) {						
//						e1.printStackTrace();
//					} catch (IOException e1) {						
//						e1.printStackTrace();
//					}

				} catch (FileNotFoundException | DocumentException e) {
					e.printStackTrace();
				}
			}

			private PdfPCell getCell(String text, int alignment) {
				PdfPCell cell = new PdfPCell(new Phrase(text));
				cell.setPadding(5);
				cell.setHorizontalAlignment(alignment);
				cell.setBorder(PdfPCell.NO_BORDER);				
				return cell;
			}
		});

		// ----------------------- RECHERCHE MULTICRITERE -----------------------//

		/* on englobe la liste observable dans une liste filtrée */

		FilteredList<Stagiaire> donneeFiltree = new FilteredList<>(listeObservableStagiaires, b -> true);

		barreDeRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			donneeFiltree.setPredicate(stagiaire -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (stagiaire.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (stagiaire.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (stagiaire.getDepartement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (stagiaire.getLibelleFormation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(stagiaire.getAnnee()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else {
					return false;
				}
			});

		});

		barreDeRecherche.setPromptText("Recherche avancée ");

		SortedList<Stagiaire> donneeTriee = new SortedList<>(donneeFiltree);
		donneeTriee.comparatorProperty().bind(listeStagiaires.comparatorProperty());
		listeStagiaires.setItems(donneeTriee);

	}

	// ----------------------- GETTERS ET SETTERS -----------------------//

	public Button getAjouter() {
		return ajouter;
	}

	public void setAjouter(Button ajouter) {
		this.ajouter = ajouter;
	}

	public Button getRechercher() {
		return rechercher;
	}

	public void setRechercher(Button rechercher) {
		this.rechercher = rechercher;
	}

	public Button getImpression() {
		return impression;
	}

	public void setImpression(Button impression) {
		this.impression = impression;
	}

	public Button getAide() {
		return aide;
	}

	public void setAide(Button aide) {
		this.aide = aide;
	}

	public Button getModifier() {
		return modifier;
	}

	public void setModifier(Button modifier) {
		this.modifier = modifier;
	}

	public Button getSupprimer() {
		return supprimer;
	}

	public void setSupprimer(Button supprimer) {
		this.supprimer = supprimer;
	}

	public Button getVisiteur() {
		return visiteur;
	}

	public void setVisiteur(Button visiteur) {
		this.visiteur = visiteur;
	}

	public Button getAdmin() {
		return admin;
	}

	public void setAdmin(Button admin) {
		this.admin = admin;
	}

	public PasswordField getCodeAdmin() {
		return codeAdmin;
	}

	public void setCodeAdmin(PasswordField codeAdmin) {
		this.codeAdmin = codeAdmin;
	}

	public Label getModeAdmin() {
		return modeAdmin;
	}

	public void setModeAdmin(Label modeAdmin) {
		this.modeAdmin = modeAdmin;
	}

	public TableView<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}

	public void setListeStagiaires(TableView<Stagiaire> listeStagiaires) {
		this.listeStagiaires = listeStagiaires;
	}

	public TableColumn<Stagiaire, String> getColonneNom() {
		return colonneNom;
	}

	public void setColonneNom(TableColumn<Stagiaire, String> colonneNom) {
		this.colonneNom = colonneNom;
	}

	public TableColumn<Stagiaire, String> getColonnePrenom() {
		return colonnePrenom;
	}

	public void setColonnePrenom(TableColumn<Stagiaire, String> colonnePrenom) {
		this.colonnePrenom = colonnePrenom;
	}

	public TableColumn<Stagiaire, String> getColonneDepartement() {
		return colonneDepartement;
	}

	public void setColonneDepartement(TableColumn<Stagiaire, String> colonneDepartement) {
		this.colonneDepartement = colonneDepartement;
	}

	public TableColumn<Stagiaire, String> getColonneFormation() {
		return colonneFormation;
	}

	public void setColonneFormation(TableColumn<Stagiaire, String> colonneFormation) {
		this.colonneFormation = colonneFormation;
	}

	public TableColumn<Stagiaire, Integer> getColonneAnnee() {
		return colonneAnnee;
	}

	public void setColonneAnnee(TableColumn<Stagiaire, Integer> colonneAnnee) {
		this.colonneAnnee = colonneAnnee;
	}

	public Annuaire getAnnuaire() {
		return annuaire;
	}

	public void setAnnuaire(Annuaire annuaire) {
		this.annuaire = annuaire;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}