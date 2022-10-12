package fr.isika.cda21.projet1.annuaire.utilitaires;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Fichier {

	private static String cheminFichier = "./src/fr/isika/cda21/projet1/annuaire/utilitaires/STAGIAIRES.DON";

	// attributs
	private String nom;
	private String chemin;
	private ArrayList<String> lignesDuFichier = new ArrayList<String>();

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

	// constructeurs
	public Fichier(String nom) {
		this.nom = nom;
		this.chemin = cheminFichier;
	}

	public Fichier() {

	}

	// méthodes spécifiques
	@Override
	public String toString() {

		String message = "Fichier" + nom + " dans " + chemin + "\n";
		String listeDeLignes = "";
		for (String ligne : lignesDuFichier) {
			listeDeLignes += ligne + "\n";
		}
		message += listeDeLignes;

		return message;
	}
	
	// lit le fichier et ajoute chaque ligne dans un ArrayList lignesDuFichier
		public void lectureFichier() {

			try (BufferedReader bufferredReader = new BufferedReader(new FileReader(getChemin()));) {

				String line;
				while ((line = bufferredReader.readLine()) != null) {
					getLignesDuFichier().add(line);
				}

			} catch (FileNotFoundException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}

}
