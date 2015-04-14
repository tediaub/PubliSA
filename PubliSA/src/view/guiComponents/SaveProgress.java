package view.guiComponents;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		add(progressBar, "name_4338683676047242");
		
		JLabel lblNewLabel = new JLabel("Sauvegarde terminée");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, "name_4338705196839778");

	}
	
	public void processEnd(){
		CardLayout cl = (CardLayout)(getLayout());
	    cl.show(this, "name_4338705196839778");
	}
}
