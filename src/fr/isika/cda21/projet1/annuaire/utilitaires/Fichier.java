package fr.isika.cda21.projet1.annuaire.utilitaires;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.isika.cda21.projet1.annuaire.modeles.Stagiaire;

public class Fichier {

	private static String cheminFichierTxt = "./src/fr/isika/cda21/projet1/annuaire/utilitaires/TEST.DON";

	// attributs
	private String nom;
	private String chemin;
	private ArrayList<String> lignesDuFichier = new ArrayList<String>();
	private ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();

	// getters
	public String getNom() {
		return nom;
	}

	public String getChemin() {
		return chemin;
	}

	public ArrayList<String> getLignesDuFichier() {
		return lignesDuFichier;
	}

	public ArrayList<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}

	// constructeurs
	public Fichier(String nom) {
		this.nom = nom;
		this.chemin = cheminFichierTxt;
	}

	public Fichier() {
		this.chemin = cheminFichierTxt;

	}

	// méthodes spécifiques
	@Override
	public String toString() {

		String message = "Fichier : " + nom + " dans " + chemin + "\n";
		String listeDeLignes = "";
		for (String ligne : lignesDuFichier) {
			listeDeLignes += ligne + "\n";
		}
		message += listeDeLignes;

		return message;
	}

	// lit le fichier texte et renvoie un ArrayList de Stagiaire
	public ArrayList<Stagiaire> lectureFichier() {

		try (BufferedReader bufferredReader = new BufferedReader(new FileReader(getChemin()));) {

			while (bufferredReader.ready()) {
				Stagiaire stagiaire = new Stagiaire();

				stagiaire.setNom(bufferredReader.readLine());
				stagiaire.setPrenom(bufferredReader.readLine());
				stagiaire.setDepartement(bufferredReader.readLine());
				stagiaire.setLibelleFormation(bufferredReader.readLine());
				stagiaire.setAnnee(Integer.parseInt(bufferredReader.readLine()));
				bufferredReader.readLine();
				listeStagiaires.add(stagiaire);
			}
			bufferredReader.close();

		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		return getListeStagiaires();
	}

}
