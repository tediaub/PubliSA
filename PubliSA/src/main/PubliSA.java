package main;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.guiComponents.firstLaunch.FirstLaunchDialog;
import model.Model;
import model.saveLoad.Serialization;
import model.saveLoad.XmlLoader;
import controller.openFrame.OpeningController;

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
				} catch (Exception e) {
					e.printStackTrace();
				}
								
				Model model = null;
				if(new File("data").exists()){
					model = Serialization.loadModel();
				}else if(new File("PubliSA.xml").exists()){
					model = XmlLoader.loadModel();
				}
				
				if(model == null){
					model = new Model();
				}
				
				OpeningController control = new OpeningController(model);
				control.createOpeningFrame();
				control.createMainFrame();
			}
		});
	}
}
