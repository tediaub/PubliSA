package outils;

import java.io.*;

import javax.swing.JOptionPane;

import model.Delivery;


public class SupprEntete {

	private File[] fList;
	
	public SupprEntete(String OGC){
		File fOGC = new File(OGC);
		fList = fOGC.getParentFile().listFiles();
		
		for(int i = 0; i< fList.length; i++){
			String nomFichier = fList[i].getName();
			int j = nomFichier.lastIndexOf('.');
		
			if (j > 0 && j < nomFichier.length() - 1) {
				String extension = nomFichier.substring(j+1).toLowerCase();
				extension = extension.substring(0, 3);
				
				if(extension.equals("ogc")){
					supprEntete(fList[i], "!---------");
				}
				
				if(extension.equals("est") || extension.equals("ext")){
					supprEntete(fList[i], "---------");
				}
			}
		}
		File f = new File(OGC);
		JOptionPane.showMessageDialog(null, "Suppression des entêtes terminée.\nLes fichiers modifiés ont été placés dans le dossier :\n" 
				+ f.getParentFile().getPath() + File.separator + Delivery.getNom() + "."  , "PubliSA", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void supprEntete(File fichier, String recherche){
		try {
			InputStream ips = new FileInputStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			File repertoire = new File(fichier.getParentFile().getPath() + File.separator + Delivery.getNom());
			
			if(!repertoire.exists()){repertoire.mkdir();}
			
			// Supprime les numéros qui se trouve à la fin du fichier (ex: ;1 ;2 ;3 ;4 ;5 ...)
			String nomFichier = fichier.getName();
			int j = nomFichier.lastIndexOf('.');
			nomFichier = nomFichier.substring(0, j + 4);

			OutputStream ops = new FileOutputStream(new File(fichier.getParentFile().getPath() + File.separator + Delivery.getNom() + File.separator + nomFichier+ ".txt")); 
			OutputStreamWriter opsr = new OutputStreamWriter(ops);
			BufferedWriter bw = new BufferedWriter(opsr);
			
			String ligne;
			int indiceLigne = 0;
			
			while ((ligne = br.readLine())!= null){
				if(ligne.contains(recherche)){
					indiceLigne++;
				}
				if(indiceLigne == 3){
					while(indiceLigne != 0){
						ligne = br.readLine();
						ligne = ligne.trim();
						if(ligne.contains(recherche)){
							indiceLigne = 0;
						}
						else{
							bw.write(ligne + System.getProperty("line.separator"));	
						}	
					}
				}
			}
			br.close();
			bw.close();
			}		
		catch (Exception e){
			System.err.println(e.toString());
		}
	}
}