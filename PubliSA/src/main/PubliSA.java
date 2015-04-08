package main;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.guiComponents.frameOpening.OpeningFrame;
import model.Model;
import model.saveLoad.Serialization;
import controller.OpeningController;

/**
 * 
 * @author Teddy AUBERT
 *
 * @version $Revision: 1.0 $
 */
public class PubliSA {
	
	/**
	 * Lancement de l'application
	 * @param args String[]
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		        	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
		        	}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				
				String panelOpen;
				Model model = null;
				if(new File("data").exists()){
					model = Serialization.loadModel();
					panelOpen = OpeningFrame.PANEL_MAIN;
				}else{
					model = new Model();
					panelOpen = OpeningFrame.PANEL_FIRST_LAUNCH;
				}
				
				OpeningController control = new OpeningController(model);
				control.createOpeningFrame(panelOpen);
			}
		});
	}
}
