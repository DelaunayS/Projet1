package fr.isika.cda21.projet1.annuaire.modeles;

public class Noeud {

	// attributs
	private Stagiaire cle;
	private int filsGauche;
	private int filsDroit;
	private int doublon;

	// constructeurs
	public Noeud(Stagiaire cle, int filsGauche, int filsDroit) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.doublon=-1;
	}

	public Noeud() {
		this.doublon=-1;

	}

	// getters & setters
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

	public int getDoublon() {
		return doublon;
	}

	public void setDoublon(int doublon) {
		this.doublon = doublon;
	}

	// méthodes spécifiques
	@Override
	public String toString() {
		return cle + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + ", doublon= " + doublon;
	}

}
