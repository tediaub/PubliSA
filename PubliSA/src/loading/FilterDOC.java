package loading;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Filtre les fichiers .txt
 * @author AUBERT_T
 *
 */

public class FilterDOC extends FileFilter {

	/** 
	 * indique les fichiers dont l'extension sera 
	 * acceptee 
	 * @param file le fichier teste
	 */
	public boolean accept(File fichier) {
		if(fichier.isDirectory()) {
			return true;
		}
	
		// on a récupéré l'extension du fichier et le tester 
		String nomFichier = fichier.getName();
		int i = nomFichier.lastIndexOf('.');
	
		if (i > 0 && i < nomFichier.length() - 1) {
			String extension = nomFichier.substring(i+1).toLowerCase();

			if(extension.equals("doc")){
				return true;
			}
			
			if(extension.equals("docx")){
				return true;
			}
		}
		return false;
	}

	
	/** 
	 * méthode servant a décrire le filtre de fichier 
	 */ 
	public String getDescription() {
	return "Document Word (*.DOC, *.DOCX)";
	}

	


}