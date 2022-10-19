package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class SupprimerAnnuaire {

	private FichierBinaire fichierBin;
	private RandomAccessFile raf;
	private RechercherAnnuaire rechercherAnnuaire;

	public SupprimerAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf, RechercherAnnuaire rechercherAnnuaire) {
		this.fichierBin = fichierBin;
		this.raf = raf;
		this.rechercherAnnuaire = rechercherAnnuaire;
	}

	// Supprimer

	public void supprimerRacine(Stagiaire stagiaire) throws IOException {

		ArrayList<Noeud> noeuds = rechercherAnnuaire.rechercher(stagiaire);
		if (noeuds != null) {
			raf.seek(raf.getFilePointer() - FichierBinaire.TAILLE_NOEUD);
			supprimerRacine(fichierBin.lectureFichierBin(raf));
		} else {
			System.out.println("Personne à supprimer");
		}
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
			} else if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
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
			} else if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
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
			} else if (raf.readInt() == indexARemplacer) {
				raf.seek(raf.getFilePointer() - 4);
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
	}

	// pour chercher le parent d'un stagiaire dans l'annuaire
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
