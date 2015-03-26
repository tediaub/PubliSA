package loading;

import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

/**
 */
public class LoadFile{
	private static JFileChooser fc;
	private static JFrame frame;

	/**
	 * Method ChargementFich.
	 * @return String
	 */
	public static String loadFrame(String path, String title, FileFilter filter){
		LookAndFeel oldLaF = UIManager.getLookAndFeel();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		fc = new JFileChooser(path);
		
		/*fenêtre contenant le file chooser*/
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoadFile.class.getResource("/logo/logoPubliSA4.png")));
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filter);
		fc.setDialogTitle(title);
		
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
}
