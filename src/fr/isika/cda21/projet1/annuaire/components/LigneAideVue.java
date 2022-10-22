package fr.isika.cda21.projet1.annuaire.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/*
 * GridPane qui permet l'ajout de ligne
 * dans la vue d'aide
 */
public class LigneAideVue extends GridPane {
	
	//attributs
	
	private VBox ligneCase1;
	private Button buttonLigne;
	private Label labelLigne;
	private VBox ligneCase2;
	private FileInputStream inputstream;
	private Image imageLigne;
	private ImageView imageView;
	
	public LigneAideVue(String texteButton, String messageLabel, int hauteurImage,
			int largeurImage, String cheminImage) throws FileNotFoundException {
		super();		
		ligneCase1 = new VBox();
		ligneCase1.setPrefWidth(300);
		ligneCase2 = new VBox();
		buttonLigne = new Button(texteButton);
		labelLigne = new Label(messageLabel);

		ligneCase1.getChildren().addAll(buttonLigne, labelLigne);
		this.addRow(0, ligneCase1, ligneCase2);
		
		this.setPadding(new Insets(5, 10, 10, 5));
		ligneCase1.setPadding(new Insets(5, 10, 10, 5));

		inputstream = new FileInputStream(cheminImage);
		imageLigne = new Image(inputstream);
		imageView = new ImageView(imageLigne);
		imageView.setFitHeight(hauteurImage);
		imageView.setFitWidth(largeurImage);
		ligneCase2.getChildren().add(imageView);		
	}

}
