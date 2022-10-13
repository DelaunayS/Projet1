package fr.isika.cda21.projet1.annuaire.modeles;

public class Noeud {

	private Stagiaire cle;
	private Noeud filsGauche;
	private Noeud filsDroit;

	public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	public Stagiaire getCle() {
		return cle;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	// méthodes spécifiques

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

	// methodes spécifiques
	@Override
	public String toString() {
		String res = "";
		if (this.filsGauche != null) {
			res = res + this.filsGauche.toString();// G
		}
		res = res + this.cle.toString() + "\n";// N
		if (this.filsDroit != null) {
			res = res + this.filsDroit.toString();// D
		}
		return res;
	}

	/* ------------ AJOUTER UN NOEUD (STAGIAIRE) ------------ */
	public void ajouterStagiaire(Stagiaire stagiaireAAjouter) {

		if ((this.cle).compareTo(stagiaireAAjouter) == 0) {
			System.out.println("Le stagiaire figure déjà dans la liste.");
		} else if ((this.cle).compareTo(stagiaireAAjouter) > 0) {
			if (this.filsDroit == null) {
				Noeud nouveauStagiaire = new Noeud(stagiaireAAjouter, null, null);
				this.filsDroit = nouveauStagiaire;
			} else {
				this.filsDroit.ajouterStagiaire(stagiaireAAjouter);
			}
		} else if ((this.cle).compareTo(stagiaireAAjouter) < 0) {
			if (this.filsGauche == null) {
				Noeud nouveauStagiaire = new Noeud(stagiaireAAjouter, null, null);
				this.filsGauche = nouveauStagiaire;
			} else {
				this.filsGauche.ajouterStagiaire(stagiaireAAjouter);
			}
		}

	}
	/* ------------ NOMBRE TOTAL DE STAGIAIRES ------------ */

	public int nombreTotalStagiaires() {
		if ((this.filsDroit != null) && (this.filsGauche != null)) {
			return this.filsGauche.nombreTotalStagiaires() + 1 + this.filsDroit.nombreTotalStagiaires();
		} else if (this.filsDroit != null) {
			return 1 + this.filsDroit.nombreTotalStagiaires();
		} else if (this.filsGauche != null) {
			return 1 + this.filsGauche.nombreTotalStagiaires();
		} else {
			return 1;
		}
	}

	// rechercher un noeud
	public boolean rechercherNoeud(Stagiaire stagiaireARechercher) {

		if (this.cle.compareTo(stagiaireARechercher) == 0) {
			return true;
		}

		if (this.cle.compareTo(stagiaireARechercher) > 0) {
			if (this.getFilsDroit() == null) {
				return false;
			} else {
				return this.getFilsDroit().rechercherNoeud(stagiaireARechercher);
			}
		} else {
			if (this.getFilsGauche() == null) {
				return false;
			} else {
				return this.getFilsGauche().rechercherNoeud(stagiaireARechercher);
			}
		}
	}

}
