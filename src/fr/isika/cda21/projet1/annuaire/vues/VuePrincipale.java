package fr.isika.cda21.projet1.annuaire.vues;
import java.io.FileInputStream;
import java.io.IOException;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
public class VuePrincipale extends Scene {
	
	/* ------------ ATTRIBUTS ------------ */
	private BorderPane root;
	private GridPane panneauHaut;
	private VBox conteneurLogin;
	private VBox conteneurImage;
	private HBox ligneVisiteur;
	private HBox ligneAdmin;
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
	
	/* ------------ CONSTRUCTEUR ------------ */
	public VuePrincipale(Annuaire annuaire) throws IOException {
		
		super(new BorderPane(), 650, 650);
		root = ((BorderPane)this.getRoot());
		panneauHaut = new GridPane();
		conteneurLogin = new VBox();
		conteneurImage = new VBox();
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
		ligneVisiteur.getChildren().add(visiteur);
		ligneAdmin.getChildren().addAll(admin, codeAdmin);
		ligneAdmin.setSpacing(5);
		conteneurLogin.getChildren().addAll(ligneVisiteur, ligneAdmin, modeAdmin);
		conteneurLogin.setSpacing(5);
		conteneurLogin.setMargin(ligneVisiteur,new Insets(20,20,0,20));
		conteneurLogin.setMargin(ligneAdmin,new Insets(8,20,0,20));
		conteneurLogin.setMargin(modeAdmin,new Insets(2,20,0,20));
		conteneurLogin.setAlignment(Pos.CENTER_LEFT);
		root.getChildren().add(conteneurLogin);
		panneauBas.getChildren().addAll(ajouter, rechercher, modifier, supprimer, impression, aide, quitter);
		panneauBas.setMinSize(60, 60);
		panneauBas.setAlignment(Pos.CENTER);
		panneauBas.setSpacing(15);
		panneauHaut.add(conteneurLogin, 0, 0);
		panneauHaut.add(conteneurImage, 3, 0);
		panneauHaut.setVgap(30);
		panneauHaut.setHgap(40);
		root.setTop(panneauHaut);
		root.setAlignment(panneauHaut, Pos.CENTER);
		panneauHaut.setAlignment(Pos.CENTER);
		
		root.setBottom(panneauBas);
		root.setCenter(listeStagiaires);
		root.setLeft(margeGauche);
		root.setRight(margeDroite);
				
		//largeurTableau.setVgrow(listeStagiaires,Priority.ALWAYS);c

		
		
		margeGauche.setPadding(new Insets(10,10,10,10));
		margeDroite.setPadding(new Insets(10,10,10,10));

		
		
		
		colonneNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		colonneFormation.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("libelleFormation"));
		colonneAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("annee"));
		
		
		
		listeStagiaires.getColumns().addAll(colonneNom, colonnePrenom, colonneDepartement, colonneFormation,
				colonneAnnee);
		
		colonneNom.setSortType(TableColumn.SortType.ASCENDING);
		
		colonneNom.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.25));
		colonnePrenom.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.25));

		colonneDepartement.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.15));
		colonneFormation.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.2));
		colonneAnnee.prefWidthProperty().bind(listeStagiaires.widthProperty().multiply(0.15));


		
		//annuaire.ordreAlpha();
		
		
		ObservableList<Stagiaire> listeObservableStagiaires =
				FXCollections.observableArrayList(annuaire.getListeDeStagiaires());
		listeStagiaires.setItems(listeObservableStagiaires);
		
		

		
		//Passing FileInputStream object as a parameter 
		FileInputStream inputstream = new FileInputStream("src/fr/isika/cda21/projet1/annuaire/utilitaires/logo.png"); 
		Image logo = new Image(inputstream); 
		
		ImageView logoView = new ImageView(logo);
		logoView.setX(50);
		logoView.setY(25); 
	      
	    logoView.setFitHeight(150); 
	    logoView.setFitWidth(200); 
	      
	    conteneurImage.getChildren().add(logoView);
	      
	    		
		
		/* ------------ BOUTONS ------------ */
		
				/* ------------ CONNEXION EN MODE ADMINISTRATEUR ------------ */
		
		admin.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (codeAdmin.getText().equals(motDePasse)) {
					codeAdmin.setText(null);
					modifier.setVisible(true);
					supprimer.setVisible(true);
					modeAdmin.setText("Vous êtes en mode administrateur");
					modeAdmin.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
					modeAdmin.setTextFill(Color.RED);
				}
			}
		});
		
				/* ------------ DECONNEXION ------------ */
		visiteur.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				modifier.setVisible(false);
				supprimer.setVisible(false);
				modeAdmin.setText(null);
			}
		});
	}
	/* ------------ GETTERS && SETTERS ------------ */
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
	
	
	
	
}