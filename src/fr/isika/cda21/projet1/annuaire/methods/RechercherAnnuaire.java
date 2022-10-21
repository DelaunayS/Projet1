package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class RechercherAnnuaire {

	//attributs
	private FichierBinaire fichierBin;
	private RandomAccessFile raf;

	//constructeurs
	public RechercherAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf) {
		this.fichierBin = fichierBin;
		this.raf = raf;
	}

	//getters && setters
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

	
	/* recherche par nom dans l'annuaire
	 * renvoie la liste des noeuds avec ce nom
	 */
	public ArrayList<Noeud> rechercheParNom(String nomARechercher) throws IOException {
		nomARechercher = nomARechercher.toUpperCase();
		Stagiaire stagiaire = new Stagiaire(nomARechercher);		
		return rechercher(stagiaire);
	}

	/* recherche par Stagiaire en se positionnant au début du fichier
	 * afin de lire le premier noeud de l'annuaire et lancer la méthode rechercher
	 * avec le premier noeud et le stagiaire recherché en paramètres  
	 */
	public ArrayList<Noeud> rechercher(Stagiaire stagiaireARechercher) throws IOException {
		raf.seek(0);
		return rechercher(fichierBin.lectureFichierBin(raf), stagiaireARechercher);
	}

	// recherche par Noeud et stagiaire
	private ArrayList<Noeud> rechercher(Noeud noeudCourant, Stagiaire stagiaireARechercher) throws IOException {
		ArrayList<Noeud> noeuds = rechercherRecursif(noeudCourant, stagiaireARechercher);
		if (noeuds != null) {
			return noeuds;
		}
		return null;
	}

	/* recherche recursive en comparant le stagiaire recherché 
	 * avec le stagiaire contenu dans un noeud de l'annuaire
	 * (le compareTo ne compare que sur le nom)
	 * on renvoie une liste de Noeuds contenant le nom du stagiaire recherché	 * 
	 */
	public ArrayList<Noeud> rechercherRecursif(Noeud noeudCourant, Stagiaire stagiaireARechercher) throws IOException {

		ArrayList<Noeud> noeudsRecherches = new ArrayList<>();
		if (noeudCourant == null) {
			return null;
		}		
		int test = noeudCourant.getCle().compareTo(stagiaireARechercher);

		// c'est trouvé
		if (test == 0) {
			// on gère le cas où il y a des doublons
			if (noeudCourant.getDoublon() != -1) {
				Noeud noeudDoublon = new Noeud();
				do {
					// on récupère la position du doublon pour l'ajouter à la liste des noeuds
					raf.seek(noeudCourant.getDoublon() * FichierBinaire.TAILLE_NOEUD);
					noeudDoublon = fichierBin.lectureFichierBin(raf);
					noeudsRecherches.add(noeudDoublon);
				} while (noeudDoublon.getDoublon() != -1);
			}
			noeudsRecherches.add(noeudCourant);
			return noeudsRecherches;
		}

		raf.seek(raf.getFilePointer());

		//la recherche continue vers le noeud gauche
		if (noeudCourant.getFilsGauche() != -1 && test < 0) {
			raf.seek(noeudCourant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
			return rechercherRecursif(noeudGauche, stagiaireARechercher);
		}

		//la recherche continue vers le noeud droit
		if (noeudCourant.getFilsDroit() != -1 && test > 0) {
			raf.seek(noeudCourant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			return rechercherRecursif(noeudDroit, stagiaireARechercher);
		}
		return null;
	}
}
