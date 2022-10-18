package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class ParcourirAnnuaire {

	private FichierBinaire fichierBin;
	private RandomAccessFile raf;
	private ArrayList<Stagiaire> listeDeStagiaires;

	public ParcourirAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf, ArrayList<Stagiaire> listeDeStagiaires) {
		this.fichierBin = fichierBin;
		this.raf = raf;
		this.listeDeStagiaires = listeDeStagiaires;
	}

	public ArrayList<Stagiaire> getListeDeStagiaires() {
		return listeDeStagiaires;
	}

	// pour faire un parcours infixe
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
			do {
				// on récupère la position du doublon
				raf.seek(noeudCourant.getDoublon() * FichierBinaire.TAILLE_NOEUD);
				noeudDoublon = fichierBin.lectureFichierBin(raf);
				System.out.println(noeudDoublon);
				getListeDeStagiaires().add(noeudDoublon.getCle());
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

}
