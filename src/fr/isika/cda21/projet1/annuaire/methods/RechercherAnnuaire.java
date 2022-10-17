package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class RechercherAnnuaire {

	private FichierBinaire fichierBin;
	private RandomAccessFile raf;

	public RechercherAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf) {
		this.fichierBin = fichierBin;
		this.raf = raf;
	}

	public FichierBinaire getFichierBin() {
		return fichierBin;
	}

	public void setFichierBin(FichierBinaire fichierBin) {
		this.fichierBin = fichierBin;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	// recherche par nom
	public ArrayList<Noeud> rechercheParNom(String nomARechercher) throws IOException {
		nomARechercher = nomARechercher.toUpperCase();
		Stagiaire stagiaire = new Stagiaire(nomARechercher);
		System.out.println("Recherche par nom : " + nomARechercher);
		return rechercher(stagiaire);
	}

	// recherche par Stagiaire
	public ArrayList<Noeud> rechercher(Stagiaire stagiaireARechercher) throws IOException {
		raf.seek(0);

		return rechercher(fichierBin.lectureFichierBin(raf), stagiaireARechercher);
	}

	// recherche
	private ArrayList<Noeud> rechercher(Noeud noeudCourant, Stagiaire stagiaireARechercher) throws IOException {
		ArrayList<Noeud> noeuds = rechercherRecursif(noeudCourant, stagiaireARechercher);
		if (noeuds != null) {
			return noeuds;
		}
		return null;
	}

	private ArrayList<Noeud> rechercherRecursif(Noeud noeudCourant, Stagiaire stagiaireARechercher) throws IOException {

		ArrayList<Noeud> noeudsRecherches = new ArrayList<>();
		if (noeudCourant == null) {
			return null;
		}
		int test = noeudCourant.getCle().compareTo(stagiaireARechercher);

		if (test == 0) {
			if (noeudCourant.getDoublon() != -1) {
				Noeud noeudDoublon = new Noeud();
				do {
					// on récupère la position du doublon
					raf.seek(noeudCourant.getDoublon() * FichierBinaire.TAILLE_NOEUD);
					noeudDoublon = fichierBin.lectureFichierBin(raf);
					System.out.println(noeudDoublon);
					noeudsRecherches.add(noeudDoublon);
				} while (noeudDoublon.getDoublon() != -1);
			}
			noeudsRecherches.add(noeudCourant);
			System.out.println(noeudCourant);
			return noeudsRecherches;
		}

		raf.seek(raf.getFilePointer());

		if (noeudCourant.getFilsGauche() != -1 && test < 0) {
			raf.seek(noeudCourant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
			return rechercherRecursif(noeudGauche, stagiaireARechercher);

		}

		if (noeudCourant.getFilsDroit() != -1 && test > 0) {
			raf.seek(noeudCourant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			return rechercherRecursif(noeudDroit, stagiaireARechercher);
		}

		return null;
	}

}
