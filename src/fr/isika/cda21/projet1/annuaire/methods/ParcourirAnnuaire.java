package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class ParcourirAnnuaire {

	//attributs
	private FichierBinaire fichierBin;
	private RandomAccessFile raf;
	private ArrayList<Stagiaire> listeDeStagiaires;

	//constructeurs
	public ParcourirAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf, ArrayList<Stagiaire> listeDeStagiaires) {
		this.fichierBin = fichierBin;
		this.raf = raf;
		this.listeDeStagiaires = listeDeStagiaires;
	}

	//getter
	public ArrayList<Stagiaire> getListeDeStagiaires() {
		return listeDeStagiaires;
	}

	// Méthode pour faire un parcours infixe
	public void parcoursGND(Noeud noeudCourant, ArrayList<Stagiaire> listeDeStagiaires) throws IOException {

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
			raf.seek(noeudCourant.getDoublon() * FichierBinaire.TAILLE_NOEUD);
			noeudDoublon = fichierBin.lectureFichierBin(raf);
			lireDoublons(noeudDoublon,listeDeStagiaires);
		}
		
		//on ajoute le noeud à la liste des stagiaires
		listeDeStagiaires.add(noeudCourant.getCle());
		System.out.println(noeudCourant);

		// on vérifie s'il existe un noeud à droite
		if (noeudCourant.getFilsDroit() != -1) {
			raf.seek(noeudCourant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			parcoursGND(noeudDroit, listeDeStagiaires);
		}
	}

	/* Méthode récursive pour lire la liste chainée de doublons
	 * et les ajouter à la liste des stagiaires
	 * quand il n'y a plus de doublon à la liste chainée de doublons
	 */
	private void lireDoublons(Noeud noeudDoublon, ArrayList<Stagiaire> listeDeStagiaires2) throws IOException {
		if (noeudDoublon.getDoublon()!=-1) {
			Noeud doublonSuivant = new Noeud();
			raf.seek(noeudDoublon.getDoublon() * FichierBinaire.TAILLE_NOEUD);
			doublonSuivant = fichierBin.lectureFichierBin(raf);
			lireDoublons(doublonSuivant, listeDeStagiaires2);
		}
		listeDeStagiaires2.add(noeudDoublon.getCle());		
	}
}
