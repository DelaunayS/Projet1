package fr.isika.cda21.projet1.annuaire.utilitaires;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FichierBinaire {

	private static String cheminFichierBin = "./src/fr/isika/cda21/projet1/annuaire/utilitaires/STAGIAIRES.BIN";

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
	public FichierBinaire(String nom) {
		this.nom = nom;
		this.chemin = cheminFichierBin;
	}

	public FichierBinaire() {

	}

	// sauvegarde dans le fichier
	public void sauvegardeFichierBin(ArrayList<String> listeDeLignes) {
		try {
			RandomAccessFile raf = new RandomAccessFile(getChemin(), "rw");

			for (String ligne : listeDeLignes) {
				System.out.println(ligne);
				raf.writeChars(ligne);
			}

			System.out.println("taille du fichier binaire : " + raf.length());

		
			raf.close();
		  
		 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// lecture du fichier
	public void lectureFichierBin() {
		try {
			RandomAccessFile raf = new RandomAccessFile(getChemin(), "r");
			System.out.println("taille du fichier binaire : " + raf.length());

			raf.seek(0);	
			//System.out.println(raf.readLine());

			raf.close();
		}catch (EOFException eof) {
	            // fin normale de la lecture
	            System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
