package fr.isika.cda21.projet1.annuaire.modeles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.methods.AjouterAnnuaire;
import fr.isika.cda21.projet1.annuaire.methods.RechercherAnnuaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Fichier;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class Annuaire {

	// attributs
	private Noeud premierNoeud;
	private ArrayList<Stagiaire> listeDeStagiaires;
	private FichierBinaire fichierBin;
	private int indexCompteur;
	private RandomAccessFile raf;	
	private AjouterAnnuaire ajouterAnnuaire;
	private RechercherAnnuaire rechercherAnnuaire;

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
			
			this.ajouterAnnuaire=new AjouterAnnuaire(fichierBin,raf,premierNoeud,indexCompteur);
			//remplirAnnuaire();
			this.rechercherAnnuaire= new RechercherAnnuaire(fichierBin, raf);
			;
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

	
//
//	// ajout d'un stagiaire dans l'annuaire
//	public void ajouterStagiaire(Stagiaire stagiaireAAjouter) throws IOException {
//		if (premierNoeud == null) {
//			premierNoeud = new Noeud(stagiaireAAjouter, -1, -1);
//			raf.seek(0);
//			fichierBin.sauvegardeFichierBin(premierNoeud, raf);
//			setIndexCompteur(getIndexCompteur() + 1);
//		} else {
//			raf.seek(0);
//			Noeud noeud = fichierBin.lectureFichierBin(raf);
//			ajouterNoeud(stagiaireAAjouter, noeud);
//		}
//	}
//
//	// ajout d'un noeud dans l'annuaire
//	private void ajouterNoeud(Stagiaire stagiaireAAjouter, Noeud courant) throws IOException {
//
//		Noeud noeudAjouter = new Noeud(stagiaireAAjouter, -1, -1);
//		int test = courant.getCle().compareTo(stagiaireAAjouter);
//
//		// cas d'un doublon de nom
//		if (test == 0) {
//			if (courant.getDoublon() == -1) {
//				raf.seek(raf.getFilePointer() - 12);
//				courant.setDoublon((int) raf.length() / FichierBinaire.TAILLE_NOEUD);
//				raf.writeInt(courant.getDoublon());
//				raf.seek(raf.length());
//				fichierBin.sauvegardeFichierBin(noeudAjouter, raf);
//				setIndexCompteur(getIndexCompteur() + 1);
//			} else {
//				raf.seek(courant.getDoublon() * FichierBinaire.TAILLE_NOEUD);
//				Noeud noeudDoublon = fichierBin.lectureFichierBin(raf);
//				ajouterNoeud(stagiaireAAjouter, noeudDoublon);
//			}
//
//		}
//
//		// Parcours du sous-annuaire gauche
//		else if (test < 0) {
//
//			// Le fils gauche est vide
//			if (courant.getFilsGauche() == -1) {
//				raf.seek(raf.getFilePointer() - 8);
//				raf.writeInt(indexCompteur);
//				raf.seek(raf.length());
//				fichierBin.sauvegardeFichierBin(noeudAjouter, raf);
//				setIndexCompteur(getIndexCompteur() + 1);
//			}
//			// Le fils gauche n'est pas vide
//			else {
//				// On va dans le sous-annuaire gauche
//				raf.seek(courant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
//				Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
//				ajouterNoeud(stagiaireAAjouter, noeudGauche);
//			}
//		}
//		// Parcours du sous-annuaire droit
//		else if (test > 0) {
//			if (courant.getFilsDroit() == -1) {
//				raf.seek(raf.getFilePointer() - 4);
//				raf.writeInt(indexCompteur);
//				raf.seek(raf.length());
//				fichierBin.sauvegardeFichierBin(noeudAjouter, raf);
//				setIndexCompteur(getIndexCompteur() + 1);
//			}
//			// Le fils droit n'est pas vide
//			else {
//				// on va dans le sous-annuaire droit
//				raf.seek(courant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
//				Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
//				ajouterNoeud(stagiaireAAjouter, noeudDroit);
//			}
//		}
//	}

	// pour remplir l'annuaire avec le fichier
	private void remplirAnnuaire() throws IOException {
		Fichier fichierTxt = new Fichier("Fichier texte");
		fichierTxt.lectureFichier();
		for (Stagiaire stagiaire : fichierTxt.getListeStagiaires()) {

			ajouterAnnuaire.ajouterStagiaire(stagiaire);
		}
	}
	
	public void afficherRechercheParNom(String nom) throws IOException {
		rechercherAnnuaire.rechercheParNom(nom);		
	}

	// affichage de l'annuaire dans l'ordre alphabétique
	public void ordreAlpha() throws IOException {
		raf.seek(0);
		parcoursGND(fichierBin.lectureFichierBin(raf), listeDeStagiaires);
	}

	// pour faire un parcours infixe
	private void parcoursGND(Noeud noeudCourant, ArrayList<Stagiaire> listeDeStagiaires) throws IOException {

		// on positionne le pointeur pour pouvoir lire l'index gauche puis le droit
		raf.seek(raf.getFilePointer());

		// on vérifie s'il existe un noeud à gauche
		if (noeudCourant.getFilsGauche() != -1) {

			raf.seek(noeudCourant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
			parcoursGND(noeudGauche, listeDeStagiaires);
		}

		// on vérifie les doublons
		if (noeudCourant.getDoublon() != -1) {
			Noeud noeudDoublon = new Noeud();
			do {
				// on récupère la position du doublon
				raf.seek(noeudCourant.getDoublon() * FichierBinaire.TAILLE_NOEUD);
				noeudDoublon = fichierBin.lectureFichierBin(raf);
				System.out.println(noeudDoublon);
				listeDeStagiaires.add(noeudDoublon.getCle());
			} while (noeudDoublon.getDoublon() != -1);
		}

		System.out.println(noeudCourant);
		listeDeStagiaires.add(noeudCourant.getCle());

		if (noeudCourant.getFilsDroit() != -1) {
			raf.seek(noeudCourant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			parcoursGND(noeudDroit, listeDeStagiaires);
		}
	}

	// affiche l'annuaire
	public void visualiserAnnuaire() throws IOException {
		raf.seek(0);
		int nombreNoeud = (int) raf.length() / FichierBinaire.TAILLE_NOEUD;
		for (int i = 0; i < nombreNoeud; i++) {
			System.out.println(fichierBin.lectureFichierBin(raf));
		}
		System.out.println("Taille : " + raf.length());
	}

	
	
	// Supprimer
//
//	public void supprimer(Stagiaire stagiaireASupprimer) throws IOException {
//
//		Noeud noeud = rechercher(stagiaireASupprimer);
//		if (noeud == null) {
//			System.out.println("Personne à supprimer");
//		} else {
//			raf.seek(raf.getFilePointer() - 168);
//			supprimer(fichierBin.lectureFichierBin(raf), stagiaireASupprimer);
//		}
//	}
//
//	private Noeud supprimer(Noeud noeudCourant, Stagiaire stagiaireASupprimer) throws IOException {
//		System.out.println("Suppression de : " + noeudCourant);
//
//		if (noeudCourant.getFilsGauche() == -1 & noeudCourant.getFilsDroit() == -1) {
//			System.out.println("c'est une feuille");
//		} else if (noeudCourant.getFilsDroit() == -1) {
//			System.out.println(noeudCourant + " sera remplacé par " + plusPetiteValeurSousAnnuaire(noeudCourant));
//		} else if (noeudCourant.getFilsGauche() == -1) {
//			System.out.println(noeudCourant + " sera remplacé par " + plusPetiteValeurSousAnnuaire(noeudCourant));
//		} else {
//			System.out.println(noeudCourant + " a deux enfants!");
//		}
//
//		return null;
//	}

//	public void plusPetiteValeurSousAnnuaire(Stagiaire stagiaireARechercher) throws IOException {
//		rechercher(stagiaireARechercher);
//		System.out.println(rechercher(stagiaireARechercher));
//		System.out.println();
//		raf.seek(raf.getFilePointer() - 168);
//		plusPetiteValeurSousAnnuaire(fichierBin.lectureFichierBin(raf));
//	}

	private Noeud plusPetiteValeurSousAnnuaire(Noeud noeudCourant) throws IOException {

		raf.seek(raf.getFilePointer());

		if (noeudCourant == null) {
			throw new NullPointerException();
		}
		if (noeudCourant.getFilsGauche() == -1) {
			return noeudCourant;
		}
		raf.seek(noeudCourant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
		Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
		return plusPetiteValeurSousAnnuaire(noeudGauche);
	}

	

}
