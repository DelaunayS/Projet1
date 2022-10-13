package fr.isika.cda21.projet1.annuaire.modeles;

// Déclaration de l'objet Stagiaire
public class Stagiaire {

	// Déclaration des attributs
	private String nom;
	private String prenom;
	private String departement;
	private Formation libelleFormation;
	private int annee;

	// Constructeur Objet Stagiaire avec affectation des attributs
	public Stagiaire(String nom, String prenom, String departement, Formation libelleFormation, int annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.libelleFormation = libelleFormation;
		this.annee = annee;

	}

	// Getters et Setters : Affectation et récupération des valeurs des attributs
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
	// methodes spécifiques

	@Override
	public String toString() {
		return nom + ","+ prenom+","+departement+","+libelleFormation+","+annee;
	}
	
	
	public int compareTo(Stagiaire myStagiaire) {
		if (myStagiaire.getNom().compareTo(this.nom) == 0) {
			if (myStagiaire.getPrenom().compareTo(this.prenom) == 0) {
				return myStagiaire.getPrenom().compareTo(this.prenom);
			}
		}
		return myStagiaire.getNom().compareTo(this.nom);
	}	
}
