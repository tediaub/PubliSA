package loading;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 */
public class SauvegardeRapport {

	public String chemin;
	private JFileChooser fc = new JFileChooser();
	
	/**
	 * Method ChargementFich.
	 * @return String
	 */
	public File SauvegardeRap(FileFilter filtre){

		/*fenêtre contenant le file chooser*/
		JFrame fenetre = new JFrame();
		fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage(SauvegardeRapport.class.getResource("/image/logo/logo_secondaire.png")));
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filtre);
		
		/* Valeur de la réponse de l'utilisateur au file chooser*/
		int returnVal = fc.showSaveDialog(fenetre);
		
		// l'utilisateur a choisit de sauvegarder ses données
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			//Chemin du fichier
			chemin = fc.getSelectedFile().getPath();
			int i = chemin.lastIndexOf('.');
			
			if (i > 0 && i < chemin.length() - 1) {
				String extension = chemin.substring(i+1).toLowerCase();
				if(!extension.equals("xls")){
					chemin = chemin + ".xls";
				}
			}
			else{
				chemin = chemin + ".xls";
			}
			
			File f = new File(chemin);
			if(f.exists()){
				int option = JOptionPane.showConfirmDialog(null,
						"Ce fichier existe déjà. Voulez-vous le remplacer ?",
						"Sauvegarde rapport",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION){
					return f;
				}
				else{
					return null;
				}
			}
			else{
				return f;
			}
		}
		
		// Utilisateur annule la sauvegarde
		else{
			//Quitte le constructeur
			return null;
		}
	}
}
