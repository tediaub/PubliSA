package view.guiComponents.buttons;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SaveProgress extends JPanel { 

	/**
	 * Create the panel.
	 * @param jPopupMenu 
	 */
	public SaveProgress() {
		setOpaque(false);
		setLayout(new CardLayout(5, 5));
		
		JLabel lblDone = new JLabel("Sauvegarde terminée");
		lblDone.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDone, "lblDone");
		
		JLabel lblError = new JLabel("Erreur lors de la sauvegarde");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblError, "lblError");

	}
	
	public void processEnd(){
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this, "lblDone");
	}
	
	public void processError(){
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this, "lblError");
	}
}
