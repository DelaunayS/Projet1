package fr.isika.cda21.projet1.annuaire.utilitaires;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;

public class FichierBinaire {

	public static String cheminFichierBin = "./src/fr/isika/cda21/projet1/annuaire/utilitaires/STAGIAIRES.BIN";
	public final static int TAILLE_NOEUD = 172;

	// attributs
	private String nom;
	private String chemin;
	private ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();

	// getters
	public String getNom() {
		return nom;
	}

	public String getChemin() {
		return chemin;
	}

	public ArrayList<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}

	// constructeurs
	public FichierBinaire(String nom) {
		this.nom = nom;
		this.chemin = cheminFichierBin;
	}

	public FichierBinaire() {
		this.chemin = cheminFichierBin;

	}

	// sauvegarde dans le fichier
	public void sauvegardeFichierBin(Noeud noeud, RandomAccessFile raf) {

		/*
		 * un stagiaire : => 172
		 * nom 30 caractères => 30*2=60 
		 * prenom 30 caractères => 30*2=60 
		 * departement 3 caractères => 3*2 =6 
		 * formation 15 caractères => 15*2=30
		 * annee entier => 4
		 * doublon =>4
		 * indexNoeudGauche =>4
		 * indexNoeudDroit =>4 
		 * 
		 */

		try {
			raf.writeChars(noeud.getCle().nomLongBin());
			raf.writeChars(noeud.getCle().prenomLongBin());
			raf.writeChars(noeud.getCle().departementLongBin());
			raf.writeChars(noeud.getCle().formationLongBin());
			raf.writeInt(noeud.getCle().getAnnee());
			raf.writeInt(noeud.getDoublon());
			raf.writeInt(noeud.getFilsGauche());
			raf.writeInt(noeud.getFilsDroit());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// lecture du fichier
	public Noeud lectureFichierBin(RandomAccessFile raf) {
		
		try {		

			// variables nécessaire au stockage temporaire des données lues
			Noeud noeud = new Noeud();
			Stagiaire stagiaire = new Stagiaire();
			String nom = "";
			String prenom = "";
			String departement = "";
			String formation = "";
			int annee = 0;
			int doublon=0;
			int filsGauche = 0;
			int filsDroit = 0;

			// lecture du nom
			for (int i = 0; i < Stagiaire.TAILLE_MAX; i++) {
				nom += raf.readChar();
			}
			stagiaire.setNom(nom.trim());

			// lecture du prenom
			for (int i = 0; i < Stagiaire.TAILLE_MAX; i++) {
				prenom += raf.readChar();
			}
			stagiaire.setPrenom(prenom.trim());

			// lecture du departement
			for (int i = 0; i < Stagiaire.TAILLE3_MAX; i++) {
				departement += raf.readChar();
			}
			stagiaire.setDepartement(departement.trim());

			// lecture du formation
			for (int i = 0; i < Stagiaire.TAILLE2_MAX; i++) {
				formation += raf.readChar();
			}
			stagiaire.setLibelleFormation(formation.trim());
			
			// lecture de l'année
			annee += raf.readInt();
			stagiaire.setAnnee(annee);
			
			//lecture du doublon
			doublon=raf.readInt();
			noeud.setDoublon(doublon);

			// lecture fils gauche
			filsGauche = raf.readInt();
			noeud.setFilsGauche(filsGauche);

			// lecture fils droite
			filsDroit = raf.readInt();
			noeud.setFilsDroit(filsDroit);

			noeud.setCle(stagiaire);

			return noeud;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// récupérer Stagiaire
	public void recupererStagiaire() {

	}

}
