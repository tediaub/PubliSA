package controller.loading;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import view.frame.dialog.DialogFlat;

/**
 */
public class LoadSaveFile{
	private static JFileChooser fc;
	private static JFrame frame;
	
	private static LookAndFeel oldLaF;

	
	private static void init(String path, String title, FileFilter filter){
		oldLaF = UIManager.getLookAndFeel();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		fc = new JFileChooser(path);
		
		/*fenêtre contenant le file chooser*/
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoadSaveFile.class.getResource("/logo/logoPubliSA4.png")));
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filter);
		fc.setDialogTitle(title);
	}
	/**
	 * Method ChargementFich.
	 * @return String
	 */
	public static String loadFrame(String path, String title, FileFilter filter){
		init(path, title, filter);
		
		/* Valeur de la réponse de l'utilisateur au file chooser*/
		int returnVal = fc.showOpenDialog(frame);
		
		try {
			UIManager.setLookAndFeel(oldLaF);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
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
	
	public static String saveFrame(String path, String title, FileFilter filter, String extToAdd){
		init(path, title, filter);
	
		/* Valeur de la réponse de l'utilisateur au file chooser*/
		int returnVal = fc.showSaveDialog(frame);
		
		try {
			UIManager.setLookAndFeel(oldLaF);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// l'utilisateur a choisit de sauvegarder ses données
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			//Chemin du fichier
			String chemin = fc.getSelectedFile().getPath();
			
			if(!fc.getSelectedFile().getName().contains(".")){
				chemin = chemin + "." + extToAdd;
			}
			
			File f = new File(chemin);
			if(f.exists()){
				int option = new DialogFlat().showDialog("Sauvegarde",
						"Ce fichier existe déjà. Voulez-vous le remplacer ?",
						DialogFlat.ASK_OPERATION,
						DialogFlat.WARNING_ICON);
				
				if(option == DialogFlat.VALIDATE){
					return chemin;
				}
				else{
					return null;
				}
			}
			else{
				return chemin;
			}
		}else{
			return null;
		}
	}
}
