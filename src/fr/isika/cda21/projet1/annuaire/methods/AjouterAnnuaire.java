package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

// auteurs : groupe2
public class AjouterAnnuaire {

	// attributs
	private FichierBinaire fichierBin;
	private RandomAccessFile raf;
	private Noeud premierNoeud;
	private int indexCompteur;

	// constructeurs
	public AjouterAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf, Noeud premierNoeud) throws IOException {
		this.fichierBin = fichierBin;
		this.raf = raf;
		this.premierNoeud = premierNoeud;
		this.indexCompteur = (int) raf.length() / FichierBinaire.TAILLE_NOEUD;
	}

	// getters & setters
	public int getIndexCompteur() {
		return indexCompteur;
	}

	public void setIndexCompteur(int indexCompteur) {
		this.indexCompteur = indexCompteur;
	}

	public Noeud getPremierNoeud() {
		return premierNoeud;
	}

	public void setPremierNoeud(Noeud premierNoeud) {
		this.premierNoeud = premierNoeud;
	}

	// ajout d'un stagiaire dans l'annuaire
	public void ajouterStagiaire(Stagiaire stagiaireAAjouter) throws IOException {
		/*
		 * si le fichier est vide on crée le premierNoeud avec un stagiaire et les index
		 * des fils à -1 on se positionne au début du fichier on sauvegarde ce noeud
		 * dans le fichier binaire on augmente l'indexCompteur de 1
		 */
		if (raf.length() == 0) {
			premierNoeud = new Noeud(stagiaireAAjouter, -1, -1);
			raf.seek(0);
			fichierBin.sauvegardeFichierBin(premierNoeud, raf);
			setIndexCompteur(getIndexCompteur() + 1);
		} else {
			/*
			 * sinon on se positionne au début du fichier on lit le fichier pour récupérer
			 * le premierNoeud on utilise la méthode récursive ajouterNoeud *
			 */
			raf.seek(0);
			Noeud noeud = fichierBin.lectureFichierBin(raf);
			ajouterNoeud(stagiaireAAjouter, noeud);
		}
	}

	/*
	 * Méthode récursive pour ajouter un stagiaire dans l'annuaire Le noeud parent
	 * aura une référence du stagiaire par la mise à jour des indices fils gauche et
	 * fils droit *
	 */

	private void ajouterNoeud(Stagiaire stagiaireAAjouter, Noeud courant) throws IOException {

		Noeud noeudAjouter = new Noeud(stagiaireAAjouter, -1, -1);
		int test = courant.getCle().compareTo(stagiaireAAjouter);

		// cas d'un doublon de nom
		if (test == 0) {
			if (courant.getDoublon() == -1) {
				raf.seek(raf.getFilePointer() - 12);
				courant.setDoublon((int) raf.length() / FichierBinaire.TAILLE_NOEUD);
				raf.writeInt(courant.getDoublon());
				raf.seek(raf.length());
				fichierBin.sauvegardeFichierBin(noeudAjouter, raf);
				setIndexCompteur(getIndexCompteur() + 1);
			} else {
				raf.seek(courant.getDoublon() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudDoublon = fichierBin.lectureFichierBin(raf);
				ajouterNoeud(stagiaireAAjouter, noeudDoublon);
			}
		}

		// Parcours du sous-annuaire gauche
		else if (test < 0) {

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
				// On va dans le sous-annuaire gauche
				raf.seek(courant.getFilsGauche() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudGauche = fichierBin.lectureFichierBin(raf);
				ajouterNoeud(stagiaireAAjouter, noeudGauche);
			}
		}
		// Parcours du sous-annuaire droit
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
				// on va dans le sous-annuaire droit
				raf.seek(courant.getFilsDroit() * FichierBinaire.TAILLE_NOEUD);
				Noeud noeudDroit = fichierBin.lectureFichierBin(raf);
				ajouterNoeud(stagiaireAAjouter, noeudDroit);
			}
		}
	}
}
