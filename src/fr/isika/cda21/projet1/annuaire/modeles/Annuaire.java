package fr.isika.cda21.projet1.annuaire.modeles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.methods.AjouterAnnuaire;
import fr.isika.cda21.projet1.annuaire.methods.ParcourirAnnuaire;
import fr.isika.cda21.projet1.annuaire.methods.RechercherAnnuaire;
import fr.isika.cda21.projet1.annuaire.methods.SupprimerAnnuaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.Fichier;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

//auteurs : groupe2
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
	private SupprimerAnnuaire supprimerAnnuaire;

	// constructeur
	public Annuaire() {
		super();
		this.premierNoeud = null;
		this.listeDeStagiaires = new ArrayList<Stagiaire>();

		fichierBin = new FichierBinaire("Fichier Binaire");

		this.indexCompteur = 0;

		try {
			raf = new RandomAccessFile(FichierBinaire.cheminFichierBin, "rw");

			this.rechercherAnnuaire = new RechercherAnnuaire(fichierBin, raf);
			this.ajouterAnnuaire = new AjouterAnnuaire(fichierBin, raf, premierNoeud);
			if (raf.length() == 0) {
				remplirAnnuaire();
			}
			this.parcourirAnnuaire = new ParcourirAnnuaire(fichierBin, raf, listeDeStagiaires);
			this.supprimerAnnuaire = new SupprimerAnnuaire(fichierBin, raf, rechercherAnnuaire);

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

	public AjouterAnnuaire getAjouterAnnuaire() {
		return ajouterAnnuaire;
	}

	public void setAjouterAnnuaire(AjouterAnnuaire ajouterAnnuaire) {
		this.ajouterAnnuaire = ajouterAnnuaire;
	}

	public RechercherAnnuaire getRechercherAnnuaire() {
		return rechercherAnnuaire;
	}

	public void setRechercherAnnuaire(RechercherAnnuaire rechercherAnnuaire) {
		this.rechercherAnnuaire = rechercherAnnuaire;
	}

	public ParcourirAnnuaire getParcourirAnnuaire() {
		return parcourirAnnuaire;
	}

	public void setParcourirAnnuaire(ParcourirAnnuaire parcourirAnnuaire) {
		this.parcourirAnnuaire = parcourirAnnuaire;
	}

	public SupprimerAnnuaire getSupprimerAnnuaire() {
		return supprimerAnnuaire;
	}

	public void setSupprimerAnnuaire(SupprimerAnnuaire supprimerAnnuaire) {
		this.supprimerAnnuaire = supprimerAnnuaire;
	}

	/*
	 * Méthodes
	 */

	// pour remplir l'annuaire avec le fichier texte
	private void remplirAnnuaire() throws IOException {
		Fichier fichierTxt = new Fichier("Fichier texte");
		fichierTxt.lectureFichier();
		for (Stagiaire stagiaire : fichierTxt.getListeStagiaires()) {
			ajouterAnnuaire.ajouterStagiaire(stagiaire);
		}
	}

	// ajouter stagiaire
	public void ajouterStagiaire(Stagiaire stagiaire) throws IOException {
		ajouterAnnuaire.ajouterStagiaire(stagiaire);
	}

	// afficher la recherche par Nom en console
	public void afficherRechercheParNom(String nom) throws IOException {
		rechercherAnnuaire.rechercheParNom(nom);
	}

	/* L'annuaire est mis dans l'ordre alphabétique
	 * on se positionne au début du fichier et on vide la liste des stagiaires
	 * on fait un parcours infixe pour remplir la liste des stagiaires
	 */
	public void ordreAlpha() throws IOException {
		raf.seek(0);
		setListeDeStagiaires(new ArrayList<Stagiaire>());
		System.out.println();
		parcourirAnnuaire.parcoursGND(fichierBin.lectureFichierBin(raf), listeDeStagiaires);
	}

	// supprimer un stagiaire de l'annuaire
	public void supprimerStagiaire(Stagiaire stagiaire) throws IOException {
		supprimerAnnuaire.supprimerRacine(stagiaire);
	}

	// affiche l'annuaire dans l'ordre du fichier binaire
	public void visualiserAnnuaire() throws IOException {
		raf.seek(0);
		int nombreNoeud = (int) raf.length() / FichierBinaire.TAILLE_NOEUD;
		for (int i = 0; i < nombreNoeud; i++) {
			System.out.println("n° " + i + " : " + fichierBin.lectureFichierBin(raf));
		}
		System.out.println("Taille : " + raf.length());
	}

}
