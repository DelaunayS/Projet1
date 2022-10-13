package fr.isika.cda21.projet1.annuaire.modeles;

public class Formation {

	// Attributs
	private String typeFormation;
	private int numeroPromotion;
	private boolean contratProfessionnalisation;

	// Constructeur avec initialisation contratProfessionnalisation à false par
	// défaut
	public Formation(String typeFormation, int numeroPromotion) {
		this.typeFormation = typeFormation;
		this.numeroPromotion = numeroPromotion;
		this.contratProfessionnalisation = false;
	}

	public String getTypeFormation() {
		return typeFormation;
	}

	public void setTypeFormation(String typeFormation) {
		this.typeFormation = typeFormation;
	}

	public int getNumeroPromotion() {
		return numeroPromotion;
	}

	public void setNumeroPromotion(int numeroPromotion) {
		this.numeroPromotion = numeroPromotion;
	}

	public boolean isContratProfessionnalisation() {
		return contratProfessionnalisation;
	}

	public void setContratProfessionnalisation(boolean contratProfessionnalisation) {
		this.contratProfessionnalisation = contratProfessionnalisation;
	}

	@Override
	public String toString() {
		if (contratProfessionnalisation) {
			return " "+typeFormation + ","+"Promotion :"+numeroPromotion+" CP";
		}
		else{
			return " "+typeFormation + ","+"Promotion :"+numeroPromotion;
		}
	}
	
	//
	

}