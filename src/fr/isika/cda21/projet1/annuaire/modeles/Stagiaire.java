package fr.isika.cda21.projet1.annuaire.modeles;

// Déclaration de l'objet Stagiaire
public class Stagiaire {

	public static final int TAILLE_MAX = 30;
	public static final int TAILLE2_MAX = 15;
	public static final int TAILLE3_MAX = 3;
	public static final int TAILLE4_MAX = 4;

	// Déclaration des attributs
	private String nom;
	private String prenom;
	private String departement;
	private String libelleFormation;
	private int annee;

	// Constructeur Objet Stagiaire avec affectation des attributs
	public Stagiaire(String nom, String prenom, String departement, String libelleFormation, int annee) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.libelleFormation = libelleFormation;
		this.annee = annee;

	}

	public Stagiaire() {

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

	public String getLibelleFormation() {
		return libelleFormation;
	}

	public void setLibelleFormation(String libelleFormation) {
		this.libelleFormation = libelleFormation;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	// methodes spécifiques

	@Override
	public String toString() {
		return nom + "," + prenom + "," + departement + "," + libelleFormation + "," + annee;
	}

	// methode compareTo

	public int compareTo(Stagiaire myStagiaire) {
		if (myStagiaire.getNom().compareTo(this.nom) == 0) {
			if (myStagiaire.getPrenom().compareTo(this.prenom) == 0) {
				return myStagiaire.getLibelleFormation().compareTo(this.libelleFormation);
			} else {
				return myStagiaire.getPrenom().compareTo(this.prenom);
			}
		} else {
			return myStagiaire.getNom().compareTo(this.nom);
		}
	}

	// methodes pour créer des attributs longs pour le fichier binaire

	public String nomLongBin() {
		String valeur = getNom();
		for (int i = getNom().length(); i < TAILLE_MAX; i++) {
			valeur += " ";
		}
		return valeur;
	}

	public String prenomLongBin() {
		String valeur = getPrenom();
		for (int i = getPrenom().length(); i < TAILLE_MAX; i++) {
			valeur += " ";
		}
		return valeur;
	}

	public String departementLongBin() {
		String valeur = getDepartement();
		for (int i = getDepartement().length(); i < TAILLE3_MAX; i++) {
			valeur += " ";
		}
		return valeur;
	}

	public String formationLongBin() {
		String valeur = getLibelleFormation();
		for (int i = getLibelleFormation().length(); i < TAILLE2_MAX; i++) {
			valeur += " ";
		}
		return valeur;
	}

}
