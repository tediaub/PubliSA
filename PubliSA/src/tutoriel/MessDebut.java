package tutoriel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MessDebut extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public MessDebut() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MessDebut.class.getResource("/image/logo/logo_secondaire.png")));
		setTitle("Tutoriel");
		setSize(411, 228);
		this.setModal(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2
		);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(MessDebut.class.getResource("/image/icones/1359684753_Tutorial2.png")));
		lblNewLabel.setBounds(23, 20, 52, 52);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Vous allez lancer le tutoriel de PubliSA.<br>Ce tutoriel va vous permettre d'apprendre \u00E0 utiliser PubliSA.<br>Pour cela, nous allons effectuer une nouvelle livraison de A \u00E0 Z.<br><br>C'est parti !!!</html>");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(96, 6, 299, 145);
		contentPanel.add(lblNewLabel_1);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(this);
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		this.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) {
		this.hide();
	}
}
