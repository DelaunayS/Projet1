package fr.isika.cda21.projet1.annuaire.vues;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

import fr.isika.cda21.projet1.annuaire.modeles.Annuaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Couleur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	private Annuaire annuaire;
	private Stage primaryStage;
	private VBox centrePage;
	private GridPane ligne1;
	private VBox ligne1Case1;
	private Button buttonLigne1;
	private Label labelLigne1;
	private VBox ligne1Case2;
	
	private GridPane ligne2;
	private VBox ligne2Case1;
	private Button buttonLigne2;
	private Label labelLigne2;
	private VBox ligne2Case2;
	private Image imageLigne2;
	
	private GridPane ligne3;
	private VBox ligne3Case1;
	private Button buttonLigne3;
	private Label labelLigne3;
	private VBox ligne3Case2;
	private Image imageLigne3;
	
	

	// constructeurs
	public AideVue(Annuaire annuaire, Stage primaryStage) throws FileNotFoundException {
		
		super(new BorderPane(), 650, 650);
		
		root = ((BorderPane) this.getRoot());		
		scrollPane=new ScrollPane();	    
				
		// En haut
		panneauHaut=new HBox();
		
		titreLabel = new Label("Page d'aide");		
		titreLabel.setStyle("-fx-font : 25 arial;");
		retourButton = new Button("Retour");
		retourButton.setTextFill(Color.web(Couleur.LIGHT));
		retourButton.setFont(Font.font("Regular", FontWeight.BOLD, 13));
		retourButton.setStyle("-fx-background-color : " + Couleur.PRIMARY);
		panneauHaut.getChildren().addAll(titreLabel,retourButton);			
		panneauHaut.setPadding(new Insets(10, 10, 10, 10));
		titreLabel.setPadding(new Insets(20, 20, 20, 20));
		retourButton.setPadding(new Insets(10, 10, 10, 10));
		panneauHaut.setAlignment(Pos.CENTER);		

		// Au milieu
		centrePage = new VBox();	
		
		// ligne 1
		ligne1 = new GridPane();
		ligne1Case1 = new VBox();
		ligne1Case1.setPrefWidth(300);
		ligne1Case2 = new VBox();
		buttonLigne1 = new Button("Ajouter");
		
		labelLigne1 = new Label("Permet l'ajout de stagiaire :\n"
				+"1) apparition d'une fenêtre \n"
				+"2) remplir les champs souhaités, \n"
				+"3) cliquer sur le bouton valider.\n"
				+"4) la fenêtre se ferme \n"
				+"5) le stagiaire est dans la vue principale");
		ligne1Case1.getChildren().addAll(buttonLigne1, labelLigne1);
		ligne1.addRow(0, ligne1Case1, ligne1Case2);
		centrePage.getChildren().add(ligne1);
		ligne1.setPadding(new Insets(10, 20, 20, 10));
		ligne1Case1.setPadding(new Insets(10, 20, 20, 10));
		
		FileInputStream inputstream1 = new FileInputStream("src/fr/isika/cda21/projet1/annuaire/utilitaires/AjouterStagiaire.png");
		Image imageLigne1=new Image(inputstream1);
		ImageView image1View = new ImageView(imageLigne1);
		image1View.setFitHeight(300);
		image1View.setFitWidth(300);
		ligne1Case2.getChildren().add(image1View);

		// ligne 2
		ligne2 = new GridPane();
		ligne2Case1 = new VBox();
		ligne2Case1.setPrefWidth(300);
		ligne2Case2 = new VBox();
		buttonLigne2 = new Button("Rechercher");			
		labelLigne2 = new Label("Permet la recherche par nom :\n"
		+"1) apparition d'une fenêtre \n"
		+"2) remplir avec le nom recherché \n"
		+"3) cliquer sur le bouton valider.\n"
		+"4) la fenêtre se ferme \n"
		+"5) le stagiaire recherché \n"
		+"est dans la vue principale");

		ligne2Case1.getChildren().addAll(buttonLigne2, labelLigne2);
		ligne2.addRow(1, ligne2Case1, ligne2Case2);
		centrePage.getChildren().add(ligne2);
		ligne2.setPadding(new Insets(10, 20, 20, 10));
		ligne2Case1.setPadding(new Insets(10, 20, 20, 10));
				
		FileInputStream inputstream2 = new FileInputStream("src/fr/isika/cda21/projet1/annuaire/utilitaires/RechercherStagiaire.png");
		Image imageLigne2=new Image(inputstream2);
		ImageView image2View = new ImageView(imageLigne2);
		image2View.setFitHeight(150);
		image2View.setFitWidth(250);
		ligne2Case2.getChildren().add(image2View);
		
		// ligne 3
				ligne3 = new GridPane();
				ligne3Case1 = new VBox();
				ligne3Case2 = new VBox();
				buttonLigne3 = new Button("Modifier");
				
				labelLigne3 = new Label("Modifier de stagiaire :\n"
						+"1) apparition d'une fenêtre \n"
						+"2) remplir les champs souhaités, \n"
						+"3) cliquer sur le bouton valider.\n"
						+"4) la fenêtre se ferme \n"
						+"5) le stagiaire est dans la vue principale");
				ligne3Case1.getChildren().addAll(buttonLigne3, labelLigne3);
				ligne3.addRow(2, ligne3Case1, ligne3Case2);
				centrePage.getChildren().add(ligne3);
				ligne3.setPadding(new Insets(10, 20, 20, 10));
				ligne3Case1.setPadding(new Insets(10, 20, 20, 10));
//				
				FileInputStream inputstream3 = new FileInputStream("src/fr/isika/cda21/projet1/annuaire/utilitaires/ModifierStagiaire.png");
				Image imageLigne3=new Image(inputstream3);
				ImageView image3View = new ImageView(imageLigne3);
				image3View.setFitHeight(300);
				image3View.setFitWidth(300);
				ligne3Case2.getChildren().add(image3View);
		
				scrollPane.setContent(panneauHaut);
				scrollPane.setContent(centrePage);
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
