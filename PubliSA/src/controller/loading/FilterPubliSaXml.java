package controller.loading;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FilterPubliSaXml extends FileFilter{
	
	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) {
			return true;
		}
	
		// on a récupéré l'extension du fichier et le tester 
		String fileName = f.getName();
		
		if(fileName.equals("PubliSA.xml")){
			return true;
		}
		
		return false;
	}

	@Override
	public String getDescription() {
		return "Fichier de sauvegarde PubliSA v3.0 (PubliSA.xml)";
	}

}
