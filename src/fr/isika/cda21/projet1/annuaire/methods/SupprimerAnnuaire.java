package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class SupprimerAnnuaire {

	// attributs
	private FichierBinaire fichierBin;
	private RandomAccessFile raf;
	private RechercherAnnuaire rechercherAnnuaire;

	// constructeurs
	public SupprimerAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf, RechercherAnnuaire rechercherAnnuaire) {
		this.fichierBin = fichierBin;
		this.raf = raf;
		this.rechercherAnnuaire = rechercherAnnuaire;
	}

	// Lance la recherche du stagiaire à supprimer
	public void supprimerRacine(Stagiaire stagiaire) throws IOException {
		rechercherNoeudASupprimer(stagiaire);
	}

	/*
	 * Supprime un noeud de l'arbre en fonction des différents cas
	 */
	private void supprimerRacine(Noeud noeudCourant) throws IOException {

		// cas 1 : le noeud est une feuille
		if ((noeudCourant.getFilsGauche() == -1) && (noeudCourant.getFilsDroit() == -1)) {

			// on récupère la position du noeud courant dans le fichier
			int indexARemplacer = (int) (raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1);
			// le pointeur sera positionné après le parent du noeud courant
			parent(noeudCourant.getCle());

			/*
			 * on se positionne sur la position du fils gauche du parent pour remplacer
			 * l'index du parent par l'index de l'enfant (valeurstockée plus haut)
			 */
			raf.seek(raf.getFilePointer() - 8);
			if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(-1);
			} else if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(-1);
			}

			// cas 2: le noeud a uniquement un fils droit
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
			} else if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(index);
			}

			// cas 3 :le noeud a uniquement un fils gauche
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
			} else if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(index);
			}
		}

		// cas 4: le noeud a 2 enfants
		else {
			// pointeur temporaire pour garder la position de la racine
			int pointeurTemporaire = (int) (raf.getFilePointer());

			// instancie le noeudSuccesseur de la racine qui remplacera la racine
			Noeud noeudSuccesseur = successeur(noeudCourant);

			// récupère la position du successeur dans le fichier binaire
			int indexARemplacer = (int) raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1;

			parent(noeudSuccesseur.getCle());

			/*
			 * on se positionne sur l'index du parent pour le remplacer par -1 ainsi le
			 * parent du successeur ne pointe plus vers le successeur
			 */
			raf.seek(raf.getFilePointer() - 8);
			if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(-1);
			} else if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
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

	// pour chercher le parent d'un stagiaire depuis le début l'annuaire
	public void parent(Stagiaire stagiaire) throws IOException {
		raf.seek(0);
		parent(fichierBin.lectureFichierBin(raf), stagiaire);
	}

	// pour chercher le parent d'un noeud dans l'annuaire
	public Noeud parent(Noeud noeudCourant, Stagiaire stagiaire) throws IOException {

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
			// afin que le pointeur soit à la fin de la lecture du parent
			raf.seek(pointeurTemporaire);
			return parent;
		}
	}

	/*
	 * afficher le successeur du recherché c'est à dire celui qui le remplacera dans
	 * l'annuaire
	 */
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

	/*
	 * recherche du noeud contenant le stagiaire à supprimer à partir du début de
	 * l'annuaire en initialisant la position du noeud parent et en lancant la
	 * recherche récursive *
	 */
	public void rechercherNoeudASupprimer(Stagiaire stagiaire) throws IOException {
		raf.seek(0);
		int positionPere = -1;
		rechercherNoeudASupprimer(fichierBin.lectureFichierBin(raf), stagiaire, positionPere);

	}

	// Recherche récursive du noeud à supprimer
	public Noeud rechercherNoeudASupprimer(Noeud noeud, Stagiaire stagiaire, int positionPere) throws IOException {

		int test = noeud.getCle().compareTo(stagiaire);
		int position = (int) raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1;

		// on continue la recherche à gauche
		if (test < 0) {
			raf.seek(noeud.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
			return rechercherNoeudASupprimer(noeudGauche, stagiaire, position);
		}

		// on continue la recherche à droite
		if (test > 0) {
			raf.seek(noeud.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
			return rechercherNoeudASupprimer(noeudDroit, stagiaire, position);
		}

		// le nom du stagiaire est trouvé
		if (test == 0) {
			// s'il n'y pas de doublon, on supprime le noeud
			if (noeud.getDoublon() == -1) {
				supprimerRacine(noeud);
			}
			// sinon on utilise une méthode récursive pour supprimer les doublons
			else {
				supprimerDoublon(noeud, stagiaire, positionPere);
			}
		}
		return null;
	}

	// méthode récursive pour supprimer le bon doublon dans sa liste chainée
	private void supprimerDoublon(Noeud noeud, Stagiaire stagiaire, int positionPere) throws IOException {

		int position = (int) raf.getFilePointer() / FichierBinaire.TAILLE_NOEUD - 1;

		// si on a trouvé le bon doublon on se positionne à la position du parent
		if (noeud.getCle().getPrenom().equals(stagiaire.getPrenom())
				&& noeud.getCle().getLibelleFormation().equals(stagiaire.getLibelleFormation())) {

			raf.seek(positionPere * FichierBinaire.TAILLE_NOEUD);
			Noeud noeudPere = fichierBin.lectureFichierBin(raf);
			/* si le doublon à supprimer appartient à l'annuaire
			 * on change sa référence vers le doublon suivant dans la liste chainée
			 */
			if (noeudPere.getCle().getNom().equals(noeud.getCle().getNom())) {
				raf.seek(raf.getFilePointer() - 12);
				raf.writeInt(noeud.getDoublon());
			} else {
				/* on se positionne à l'indice doublon du noeud courant 
				 * afin d'intancier un noeud remplaçant avec les données du noeud doublon
				 */
				raf.seek(noeud.getDoublon() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudRemplacant = fichierBin.lectureFichierBin(raf);
				/*
				 * on se positionne afin d'écrire de nouvelles données au noeud à supprimer
				 */
				raf.seek(position * FichierBinaire.TAILLE_NOEUD);
				raf.writeChars(noeudRemplacant.getCle().nomLongBin());
				raf.writeChars(noeudRemplacant.getCle().prenomLongBin());
				raf.writeChars(noeudRemplacant.getCle().departementLongBin());
				raf.writeChars(noeudRemplacant.getCle().formationLongBin());
				raf.writeInt(noeudRemplacant.getCle().getAnnee());
				raf.writeInt(noeudRemplacant.getDoublon());
			}
		} else {
			/* s'il y a encore au moins un doublon on se positionne au doublon suivant pour
			 * savoir si c'est lui qu'il faut supprimer
			 */
			if (noeud.getDoublon() != -1) {
				raf.seek(noeud.getDoublon() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudDoublon = fichierBin.lectureFichierBin(raf);
				supprimerDoublon(noeudDoublon, stagiaire, position);
			}
		}
	}
}
