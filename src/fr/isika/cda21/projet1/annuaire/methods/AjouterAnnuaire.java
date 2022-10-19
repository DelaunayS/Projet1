package fr.isika.cda21.projet1.annuaire.methods;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda21.projet1.annuaire.modeles.Noeud;
import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;
import fr.isika.cda21.projet1.annuaire.utilitaires.FichierBinaire;

public class AjouterAnnuaire {
	
	private FichierBinaire fichierBin;
	private RandomAccessFile raf;
	private Noeud premierNoeud;
	private int indexCompteur;

	public AjouterAnnuaire(FichierBinaire fichierBin, RandomAccessFile raf, Noeud premierNoeud) throws IOException {
		this.fichierBin = fichierBin;
		this.raf = raf;
		this.premierNoeud = premierNoeud;
		this.indexCompteur = (int)raf.length()/FichierBinaire.TAILLE_NOEUD;
	}

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
		if (raf.length()==0) {
			premierNoeud=new Noeud(stagiaireAAjouter, -1, -1);			
			raf.seek(0);
			fichierBin.sauvegardeFichierBin(premierNoeud, raf);
			setIndexCompteur(getIndexCompteur()+ 1);
			System.out.println("ajoutRacine / compteur : "+indexCompteur);
		} else {
			raf.seek(0);
			Noeud noeud = fichierBin.lectureFichierBin(raf);			
			ajouterNoeud(stagiaireAAjouter, noeud);
		}
	}

	// ajout d'un noeud dans l'annuaire
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
				System.out.println("gauche vide / compteur : "+indexCompteur);
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
				System.out.println("droite vide / compteur : "+indexCompteur);
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



