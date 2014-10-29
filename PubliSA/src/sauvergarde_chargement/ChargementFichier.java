package sauvergarde_chargement;

import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 */
public class ChargementFichier {

	public String cheminOGC;
	private JFileChooser fc;
	private String dialog;
	
	public ChargementFichier(String dialog){
		this.dialog = dialog;
	}

	/**
	 * Method ChargementFich.
	 * @return String
	 */
	public String ChargementFich(String AdresseOrg, FileFilter filtre){

		fc = new JFileChooser(AdresseOrg);
		
		/*fenêtre contenant le file chooser*/
		JFrame fenetre = new JFrame();
		fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage(ChargementFichier.class.getResource("/image/logo/logo_secondaire.png")));
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filtre);
		fc.setDialogTitle(dialog);
		fc.setFileView(new MonFileView());
		
		/* Valeur de la réponse de l'utilisateur au file chooser*/
		int returnVal = fc.showOpenDialog(fenetre);
		
		// l'utilisateur a choisit de sauvegarder ses données
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			//Chemin du fichier
			cheminOGC = fc.getSelectedFile().getPath();
			return cheminOGC;
		}

		// Utilisateur annule la sauvegarde
		else{
			//Quitte le constructeur
			return null;
		}
	}
}
