package fr.isika.cda21.projet1.annuaire.modeles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.utilitaires.Fichier;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class Annuaire {

	// attributs
	private Noeud premierNoeud;
	private ArrayList<Stagiaire> listeDeStagiaires;
	private FichierBinaire fichierBin;
	private int indexCompteur;
	private RandomAccessFile raf;

	// constructeur
	public Annuaire() {
		super();
		this.premierNoeud = null;
		this.listeDeStagiaires = new ArrayList<Stagiaire>();

		// alphaAnnuaire(premierNoeud, listeAlpha);
		fichierBin = new FichierBinaire("Fichier Binaire");
		this.indexCompteur = 0;
		try {
			raf = new RandomAccessFile(FichierBinaire.cheminFichierBin, "rw");
			// remplirAnnuaire();;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// getters

	public Noeud getPremierNoeud() {
		return premierNoeud;
	}

	public void setPremierNoeud(Noeud premierNoeud) {
		this.premierNoeud = premierNoeud;
	}

	public ArrayList<Stagiaire> getListeDeStagiaires() {
		return listeDeStagiaires;
	}

	public void setListeDeStagiaires(ArrayList<Stagiaire> listeDeStagiaires) {
		this.listeDeStagiaires = listeDeStagiaires;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	public FichierBinaire getFichierBin() {
		return fichierBin;
	}

	public void setFichierBin(FichierBinaire fichierBin) {
		this.fichierBin = fichierBin;
	}

	public int getIndexCompteur() {
		return indexCompteur;
	}

	public void setIndexCompteur(int indexCompteur) {
		this.indexCompteur = indexCompteur;
	}

	// methodes

	// ajout d'un stagiaire dans l'annuaire
	public void ajouterStagiaire(Stagiaire stagiaireAAjouter) throws IOException {
		if (premierNoeud == null) {
			premierNoeud = new Noeud(stagiaireAAjouter, -1, -1);
			raf.seek(0);
			fichierBin.sauvegardeFichierBin(premierNoeud, raf);
			setIndexCompteur(getIndexCompteur() + 1);
		} else {
			raf.seek(0);
			Noeud noeud = fichierBin.lectureFichierBin(raf);
			ajouterNoeud(stagiaireAAjouter, noeud);
		}
	}

	// ajout d'un noeud dans l'annuaire
	private void ajouterNoeud(Stagiaire stagiaireAAjouter, Noeud courant) throws IOException {

		Noeud noeudAjouter = new Noeud(stagiaireAAjouter, -1, -1);
		int test = courant.getCle().compareTo(stagiaireAAjouter);

		// Parcours du sous-annuaire gauche
		if (test < 0) {

			// Le fils gauche est vide
			if (courant.getFilsGauche() == -1) {
				raf.seek(raf.getFilePointer() - 8);
				raf.writeInt(indexCompteur);
				raf.seek(raf.length());
				fichierBin.sauvegardeFichierBin(noeudAjouter, raf);
				setIndexCompteur(getIndexCompteur() + 1);
			}
			// Le fils gauche n'est pas vide
			else {
				// On va dans le sous-annuaire gauche
				raf.seek(courant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
				ajouterNoeud(stagiaireAAjouter, noeudGauche);
			}
		}
		// Parcours du sous-annuaire droit
		else if (test > 0) {
			if (courant.getFilsDroit() == -1) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(indexCompteur);
				raf.seek(raf.length());
				fichierBin.sauvegardeFichierBin(noeudAjouter, raf);
				setIndexCompteur(getIndexCompteur() + 1);
			}
			// Le fils droit n'est pas vide
			else {
				// on va dans le sous-annuaire droit
				raf.seek(courant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
				ajouterNoeud(stagiaireAAjouter, noeudDroit);
			}
		}
	}

	// pour remplir l'annuaire avec le fichier
	private void remplirAnnuaire() throws IOException {
		Fichier fichierTxt = new Fichier("Fichier texte");
		fichierTxt.lectureFichier();
		for (Stagiaire stagiaire : fichierTxt.getListeStagiaires()) {
			ajouterStagiaire(stagiaire);
		}
	}

	// affichage de l'annuaire dans l'ordre alphabétique
	public void ordreAlpha() throws IOException {
		raf.seek(0);
		parcoursGND(fichierBin.lectureFichierBin(raf), listeDeStagiaires);
	}

	// pour faire un parcours infixe
	private void parcoursGND(Noeud noeudCourant, ArrayList<Stagiaire> listeDeStagiaires) throws IOException {

		// on positionne le pointeur pour pouvoir lire l'index gauche puis le droit
		raf.seek(raf.getFilePointer() - 8);
		int indexNoeudGauche = raf.readInt();
		int indexNoeudDroit = raf.readInt();

		// on vérifie s'il existe un noeud à gauche
		if (indexNoeudGauche != -1) {

			raf.seek(indexNoeudGauche * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
			parcoursGND(noeudGauche, listeDeStagiaires);
		}

		System.out.println(noeudCourant);
		listeDeStagiaires.add(noeudCourant.getCle());

		if (indexNoeudDroit != -1) {
			raf.seek(indexNoeudDroit * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			parcoursGND(noeudDroit, listeDeStagiaires);
		}
	}

	// pour créer un liste de Stagiaire classée par ordre alphabétique
//	public void alphaAnnuaire() {
//		alphaAnnuaire(premierNoeud, listeAlpha);
//	}

//	private void alphaAnnuaire(Noeud noeud, ArrayList<Stagiaire> listeAlpha) {
//
//		if (noeud != null) {
//
//			alphaAnnuaire(noeud.getFilsGauche(), listeAlpha);
//			listeAlpha.add(noeud.getCle());
//			alphaAnnuaire(noeud.getFilsDroit(), listeAlpha);
//		}
//	}

	// affiche l'annuaire
	public void visualiserAnnuaire() throws IOException {
		raf.seek(0);
		int nombreNoeud = (int) raf.length() / FichierBinaire.TAILLE_NOEUD;
		for (int i = 0; i < nombreNoeud; i++) {
			System.out.println(fichierBin.lectureFichierBin(raf));
		}
		System.out.println("Taille : " + raf.length() / 168);

	}

	// rechercher dans l'annuaire
	public Stagiaire rechercher(Stagiaire stagiaireARechercher) throws IOException {
		raf.seek(0);
		return rechercher(fichierBin.lectureFichierBin(raf), stagiaireARechercher);
	}

	private Stagiaire rechercher(Noeud noeudCourant, Stagiaire stagiaireARechercher) throws IOException {
		Noeud noeud = rechercherRecursif(noeudCourant, stagiaireARechercher);
		if (noeud != null) {
			return noeud.getCle();
		}
		return null;
	}

	private Noeud rechercherRecursif(Noeud noeudCourant, Stagiaire stagiaireARechercher) throws IOException {

		if (noeudCourant == null) {
			return null;
		}
		int test = noeudCourant.getCle().compareTo(stagiaireARechercher);

		if (test == 0) {
			System.out.println(test);
			System.out.println("recherche aboutie :");
			return noeudCourant;
		}

		raf.seek(raf.getFilePointer() - 8);
		int indexNoeudGauche = raf.readInt();
		int indexNoeudDroit = raf.readInt();
	
		
		System.out.println("indexNoeudGauche : " + indexNoeudGauche);
		System.out.println("indexNoeudDroit : " + indexNoeudDroit);
		System.out.println("noueud courant : "+noeudCourant);
		

		if (indexNoeudGauche != -1 && test < 0) {
			raf.seek(indexNoeudGauche * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
			return rechercherRecursif(noeudGauche, stagiaireARechercher);

		}

		if (indexNoeudDroit != -1 && test > 0) {
			raf.seek(indexNoeudDroit * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			return rechercherRecursif(noeudDroit, stagiaireARechercher);

		}

		return null;

	}

}
