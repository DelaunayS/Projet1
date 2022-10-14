package fr.isika.cda21.projet1.annuaire.modeles;

public class Noeud {

	private Stagiaire cle;
	private int filsGauche;
	private int filsDroit;

	public Noeud(Stagiaire cle, int filsGauche, int filsDroit) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}
	public Noeud() {
		
	}

	public Stagiaire getCle() {
		return cle;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	@Override
	public String toString() {
		return cle + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit;
	}

	// methodes spécifiques
	 

	/* ------------ AJOUTER UN NOEUD (STAGIAIRE) ------------ */
	// public void ajouterStagiaire(Stagiaire stagiaireAAjouter) {

	// if ((this.cle).compareTo(stagiaireAAjouter) == 0) {
	// System.out.println("Le stagiaire figure déjà dans la liste.");
	// } else if ((this.cle).compareTo(stagiaireAAjouter) > 0) {
	// if (this.filsDroit == -1) {
	// Stagiaire nouveauStagiaire = new Stagiaire(stagiaireAAjouter, -1, -1);
	// this.filsDroit = nouveauStagiaire;
	// } else {
	// this.filsDroit.ajouterStagiaire(stagiaireAAjouter);
	// }
	// } else if ((this.cle).compareTo(stagiaireAAjouter) < 0) {
	// if (this.filsGauche == -1) {
	// int nouveauStagiaire = new Noeud(stagiaireAAjouter, -1, -1);
	// this.filsGauche = nouveauStagiaire;
	// } else {
	// this.filsGauche.ajouterStagiaire(stagiaireAAjouter);
	// }
	// }

	// }
	/* ------------ NOMBRE TOTAL DE STAGIAIRES ------------ */

	// public int nombreTotalStagiaires() {
	// if ((this.filsDroit != null) && (this.filsGauche != null)) {
	// return this.filsGauche.nombreTotalStagiaires() + 1 +
	// this.filsDroit.nombreTotalStagiaires();
	// } else if (this.filsDroit != null) {
	// return 1 + this.filsDroit.nombreTotalStagiaires();
	// } else if (this.filsGauche != null) {
	// return 1 + this.filsGauche.nombreTotalStagiaires();
	// } else {
	// return 1;
	// }
	// }

	// rechercher un noeud
	// public boolean rechercherNoeud(Stagiaire stagiaireARechercher) {
//
	// if (this.cle.compareTo(stagiaireARechercher) == 0) {
	// return true;
	// }
//
	// if (this.cle.compareTo(stagiaireARechercher) > 0) {
	// if (this.getFilsDroit() == null) {
	// return false;
	// } else {
	// return this.getFilsDroit().rechercherNoeud(stagiaireARechercher);
	// }
	// } else {
	// if (this.getFilsGauche() == null) {
	// return false;
	// } else {
	// return this.getFilsGauche().rechercherNoeud(stagiaireARechercher);
	// }
	// }
	// }

}
