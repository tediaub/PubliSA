package loading;

import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 */
public class LoadFile{

	

	/**
	 * Method ChargementFich.
	 * @return String
	 */
	public static String loadFrame(String path, String title, FileFilter filter){

		JFileChooser fc = new JFileChooser(path);
		
		/*fenêtre contenant le file chooser*/
		JFrame fenetre = new JFrame();
		fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage(LoadFile.class.getResource("/image/logo/logo_secondaire.png")));
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filter);
		fc.setDialogTitle(title);
		fc.setFileView(new MonFileView());
		
		/* Valeur de la réponse de l'utilisateur au file chooser*/
		int returnVal = fc.showOpenDialog(fenetre);
		
		// l'utilisateur a choisit de sauvegarder ses données
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			//Chemin du fichier
			return fc.getSelectedFile().getPath();
		}

		// Utilisateur annule la sauvegarde
		else{
			//Quitte le constructeur
			return null;
		}
	}
}
