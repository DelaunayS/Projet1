package fr.isika.cda21.projet1.annuaire.modeles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.utilitaires.Fichier;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class Annuaire {

	// attributs
	private Noeud premierNoeud;
	private ArrayList<Noeud> listeDeNoeuds;
	private FichierBinaire fichierBin;
	private int indexCompteur;
	private RandomAccessFile raf;

	// constructeur
	public Annuaire() {
		super();
		this.premierNoeud = null;
		this.listeDeNoeuds = new ArrayList<Noeud>();
		
		// alphaAnnuaire(premierNoeud, listeAlpha);
		fichierBin = new FichierBinaire("Fichier Binaire");
		this.indexCompteur = 0;
		try {			
			raf = new RandomAccessFile(FichierBinaire.cheminFichierBin, "rw");
			remplirAnnuaire();;
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

	public ArrayList<Noeud> getListeDeNoeuds() {
		return listeDeNoeuds;
	}

	public void setListeDeNoeuds(ArrayList<Noeud> listeDeNoeuds) {
		this.listeDeNoeuds = listeDeNoeuds;
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

	public void ajouterNoeud(Stagiaire stagiaireAAjouter, Noeud courant) throws IOException {

		Noeud noeudAjouter = new Noeud(stagiaireAAjouter, -1, -1);
				
		int test = courant.getCle().compareTo(stagiaireAAjouter);
		
		// Parcours du sous-arbre gauche
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
				// On va dans le sous-arbre gauche
				raf.seek(courant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
				ajouterNoeud(stagiaireAAjouter, noeudGauche);
			}
		}
		// Parcours du sous-arbre droit
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
				// on va dans le sous-arbre droit
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
			System.out.println(stagiaire);
			ajouterStagiaire(stagiaire);
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
		System.out.println("Taille : "+raf.length()/168);

	}

	public void afficherListeNoeuds() {
		for (Noeud noeud : listeDeNoeuds) {
			System.out.println(noeud.getCle().getNom());
		}
	}

	// rechercher dans l'arbre
//	public void rechercherAnnuaire(Stagiaire stagiaireARechercher) {
//		if (premierNoeud.rechercherNoeud(stagiaireARechercher)) {
//			System.out.println(stagiaireARechercher + " est présent");
//		} else {
//			System.out.println(stagiaireARechercher + " n'est pas  présent");
//		}
//
//	}
	// sauvegarder l'annuaire
	public void sauvegarderAnnuaire() {

		// fichierBin.sauvegardeFichierBin(getListeAlpha());
	}

	// test lecture fichier binaire
	public void lectureFichierBinaire() {
		// fichierBin.lectureFichierBin();
	}

}
