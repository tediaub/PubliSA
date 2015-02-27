package main;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import model.Model;
import XML.XML;
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
				
				new XML();
				
				
				Model model = new Model();
				OpeningController control = new OpeningController(model);
				control.createOpeningFrame();
				control.createMainFrame();
			}
		});
	}
}
