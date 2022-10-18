package fr.isika.cda21.projet1.annuaire.modeles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.methods.AjouterAnnuaire;
import fr.isika.cda21.projet1.annuaire.methods.ParcourirAnnuaire;
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
	private ParcourirAnnuaire parcourirAnnuaire;

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

			this.ajouterAnnuaire = new AjouterAnnuaire(fichierBin, raf, premierNoeud, indexCompteur);
			// remplirAnnuaire();
			this.rechercherAnnuaire = new RechercherAnnuaire(fichierBin, raf);
			this.parcourirAnnuaire = new ParcourirAnnuaire(fichierBin, raf, listeDeStagiaires);

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

	// pour remplir l'annuaire avec le fichier texte
	private void remplirAnnuaire() throws IOException {
		Fichier fichierTxt = new Fichier("Fichier texte");
		fichierTxt.lectureFichier();
		for (Stagiaire stagiaire : fichierTxt.getListeStagiaires()) {

			ajouterAnnuaire.ajouterStagiaire(stagiaire);
		}
	}

	// afficher la recherche par Nom en console
	public void afficherRechercheParNom(String nom) throws IOException {
		rechercherAnnuaire.rechercheParNom(nom);
	}

	// affichage de l'annuaire dans l'ordre alphabétique
	public void ordreAlpha() throws IOException {
		raf.seek(0);
		parcourirAnnuaire.parcoursGND(fichierBin.lectureFichierBin(raf), listeDeStagiaires);
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
				listeDeStagiaires.add(noeudDoublon.getCle());
			} while (noeudDoublon.getDoublon() != -1);
		}

		listeDeStagiaires.add(noeudCourant.getCle());

		if (noeudCourant.getFilsDroit() != -1) {
			raf.seek(noeudCourant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			parcoursGND(noeudDroit, listeDeStagiaires);
		}
	}

	// affiche l'annuaire dans l'ordre du fichier binaire
	public void visualiserAnnuaire() throws IOException {
		raf.seek(0);
		int nombreNoeud = (int) raf.length() / FichierBinaire.TAILLE_NOEUD;
		for (int i = 0; i < nombreNoeud; i++) {
			System.out.println(fichierBin.lectureFichierBin(raf));
		}
		System.out.println("Taille : " + raf.length());
	}

	// Supprimer

	public void supprimerRacine(Stagiaire stagiaire) throws IOException {
		rechercherAnnuaire.rechercher(stagiaire);
		raf.seek(raf.getFilePointer() - FichierBinaire.TAILLE_NOEUD);
		supprimerRacine(fichierBin.lectureFichierBin(raf));
	}

	private void supprimerRacine(Noeud noeudCourant) throws IOException {

		// cas de la feuille
		if ((noeudCourant.getFilsGauche() == -1) && (noeudCourant.getFilsDroit() == -1)) {

			// on récupère la position du noeud courant dans le fichier
			int indexARemplacer = (int) (raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1);
			parent(noeudCourant.getCle());

			// on se positionne sur l'index du parent pour le remplacer par index (valeur
			// stockée plus haut)
			raf.seek(raf.getFilePointer() - 8);
			if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(-1);
			} else {
				raf.writeInt(-1);
			}

			// fils droit uniquement
		} else if ((noeudCourant.getFilsGauche() == -1)) {
			// on stocke la position du fils droit
			int index = noeudCourant.getFilsDroit();
			// on se positionne sur l'index du fils droit pour le remplacer par -1
			raf.seek(raf.getFilePointer() - 4);
			raf.writeInt(-1);

			// on récupère la position du noeud courant dans le fichier
			int indexARemplacer = (int) (raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1);

			// on recherche le parent du noeud courant et le pointeur se trouve à la fin du
			// parent
			parent(noeudCourant.getCle());

			// on se positionne sur l'index du parent pour le remplacer par index (valeur
			// stockée plus haut)
			raf.seek(raf.getFilePointer() - 8);
			if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(index);
			} else {
				raf.writeInt(index);
			}

			// fils gauche uniquement
		} else if ((noeudCourant.getFilsDroit() == -1)) {
			// on stocke la position du fils gauche
			int index = noeudCourant.getFilsGauche();
			// on se positionne sur l'index du fils gauche pour le remplacer par -1
			raf.seek(raf.getFilePointer() - 8);
			raf.writeInt(-1);
			raf.readInt();

			// on récupère la position du noeud courant dans le fichier
			int indexARemplacer = (int) (raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1);
			// on recherche le parent du noeud courant et le pointeur se trouve à la fin du
			// parent
			parent(noeudCourant.getCle());

			// on se positionne sur l'index du parent pour le remplacer par index (valeur
			// stockée plus haut)
			raf.seek(raf.getFilePointer() - 8);
			if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(index);
			} else {
				raf.writeInt(index);
			}
		}

		// 2 enfants
		else {

			if (noeudCourant.getDoublon() == -1) {

				// pointeur temporaire pour garder la position de la racine
				int pointeurTemporaire = (int) (raf.getFilePointer());

				// instancie le noeudSuccesseur de la racine
				Noeud noeudSuccesseur = successeur(noeudCourant);

				// récupère la position du successeur dans le fichier binaire
				int indexARemplacer = (int) raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1;

				// instancie le noeudParent du successeur
				Noeud noeudParent = parent(noeudCourant, noeudSuccesseur.getCle());

				/*
				 * on se positionne sur l'index du parent pour le remplacer par -1 ainsi le
				 * parent du successeur ne pointe plus vers le successeur
				 */

				raf.seek(raf.getFilePointer() - 8);
				if (raf.readInt() == indexARemplacer) {
					raf.seek(raf.getFilePointer() - 4);
					raf.writeInt(-1);
				} else {
					raf.writeInt(-1);
				}

				// on se positionne à la racine des deux enfants
				raf.seek(pointeurTemporaire - FichierBinaire.TAILLE_NOEUD);

				// on écrit les valeurs du successeur à la place de la racine
				raf.writeChars(noeudSuccesseur.getCle().nomLongBin());
				raf.writeChars(noeudSuccesseur.getCle().prenomLongBin());
				raf.writeChars(noeudSuccesseur.getCle().departementLongBin());
				raf.writeChars(noeudSuccesseur.getCle().formationLongBin());
				raf.writeInt(noeudSuccesseur.getCle().getAnnee());

			}

		}
	}

	//
	public void parent(Stagiaire stagiaire) throws IOException {
		raf.seek(0);
		parent(fichierBin.lectureFichierBin(raf), stagiaire);
	}

	private Noeud parent(Noeud noeudCourant, Stagiaire stagiaire) throws IOException {

		int test = noeudCourant.getCle().compareTo(stagiaire);
		int pointeurTemporaire = (int) raf.getFilePointer();
		Noeud parent = new Noeud();
		if (test == 0) {
			return null;
		} else {
			while (test != 0) {
				pointeurTemporaire = (int) raf.getFilePointer();

				// on stocke noeud Courant dans parent
				parent = noeudCourant;
				if (test < 0) {
					raf.seek(noeudCourant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
					// noeudCourant devient le fils gauche
					noeudCourant = fichierBin.lectureFichierBin(raf);
					// on teste avec la nouvelle valeur
					test = noeudCourant.getCle().compareTo(stagiaire);
				} else if (test > 0) {

					raf.seek(noeudCourant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
					// noeudCourant devient le fils droit
					noeudCourant = fichierBin.lectureFichierBin(raf);
					// on teste avec la nouvelle valeur
					test = noeudCourant.getCle().compareTo(stagiaire);
				}
			}
			// afin que le pointeur soit à la fin du parent
			raf.seek(pointeurTemporaire);
			System.out.println("pointeur à la sortie :" + raf.getFilePointer());
			System.out.println(parent);
			return parent;
		}
	}

	// afficher le successeur du Stagiaire
	public void successeur(Stagiaire stagiaireARechercher) throws IOException {
		rechercherAnnuaire.rechercher(stagiaireARechercher);
		raf.seek(raf.getFilePointer() - FichierBinaire.TAILLE_NOEUD);
		successeur(fichierBin.lectureFichierBin(raf));
	}

	// renvoie le successeur du Noeud courant
	private Noeud successeur(Noeud noeudCourant) throws IOException {

		raf.seek(raf.getFilePointer());

		if (noeudCourant == null) {
			throw new NullPointerException();
		}

		raf.seek(noeudCourant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
		Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
		while (noeudDroit.getFilsGauche() != -1) {
			raf.seek(noeudDroit.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
			noeudDroit = fichierBin.lectureFichierBin(raf);
		}
		return noeudDroit;
	}

}
