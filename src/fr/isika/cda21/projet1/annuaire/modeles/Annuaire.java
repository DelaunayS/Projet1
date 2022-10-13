package fr.isika.cda21.projet1.annuaire.modeles;

import java.util.ArrayList;

public class Annuaire {

	// attributs
	private Noeud premierNoeud;
	private ArrayList<Stagiaire> listeAlpha; 

	// constructeur
	public Annuaire() {
		super();
		this.premierNoeud = null;
		this.listeAlpha= new ArrayList<Stagiaire>();
	}

	// getters
	public Noeud getPremierNoeud() {
		return premierNoeud;
	}
	
	public ArrayList<Stagiaire> getListeAlpha() {
		return listeAlpha;
	}

	// ajout un stagiaire dans l'annuaire
	public void ajouterAnnuaire(Stagiaire stagiaireAAjouter) {

		if (getPremierNoeud() == null) {
			premierNoeud = new Noeud(stagiaireAAjouter, null, null);
		} else {
			if (!(premierNoeud.rechercherNoeud(stagiaireAAjouter))) {
				premierNoeud.ajouterStagiaire(stagiaireAAjouter);
			}

		}
	}
	
	public void alphaAnnuaire() {
		alphaAnnuaire(premierNoeud, listeAlpha);
	}
	
	private void alphaAnnuaire(Noeud noeud, ArrayList<Stagiaire>  listeAlpha) {
		
		if (noeud != null) {
			
			alphaAnnuaire(noeud.getFilsGauche(),listeAlpha);			
			listeAlpha.add(noeud.getCle());			
			alphaAnnuaire(noeud.getFilsDroit(),listeAlpha);
		}
	}
	

	
	// affiche l'annuaire
	public void visualiserAnnuaire() {
		System.out.println(premierNoeud.toString());
		
		
	}

	// rechercher dans l'arbre
	public void rechercherAnnuaire(Stagiaire stagiaireARechercher) {
		if (premierNoeud.rechercherNoeud(stagiaireARechercher)) {
			System.out.println(stagiaireARechercher+" est présent");
		}
		else {
			System.out.println(stagiaireARechercher+" n'est pas  présent");
		}
		

	}
}
