package sauvergarde_chargement;

import java.io.File;


import javax.swing.filechooser.FileFilter;

/**
 * Filtre les fichiers .txt
 * @author AUBERT_T
 *
 */

public class Filtre_OGCtxt extends FileFilter {

	/** 
	 * indique les fichiers dont l'extension sera 
	 * acceptee 
	 * @param file le fichier teste
	 */
	public boolean accept(File fichier) {
		if(fichier.isDirectory()) {
			return true;
		}
	
		// on a r�cup�r� l'extension du fichier et le tester 
		String nomFichier = fichier.getName();
		int i = nomFichier.lastIndexOf('.');
		
		if (i > 3 && i < nomFichier.length() - 1){
			String extension = nomFichier.substring(i-3).toLowerCase();
			nomFichier = nomFichier.substring(0,i);

			if(extension.equals("ogc.txt")){
				return true;
			}
		}
		return false;
	}

	
	/** 
	 * m�thode servant a d�crire le filtre de fichier 
	 */ 
	public String getDescription() {
		return "Fichier sommaire des planches (*.OGC.txt)";
	}
}