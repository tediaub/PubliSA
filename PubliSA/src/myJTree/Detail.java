package myJTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import XML.XML;

@SuppressWarnings("serial")
public class Detail extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	/**
	 * Create the dialog.
	 */
	public Detail(String Uti, String Liv) {
		
		setModal(true);
		this.setResizable(false);
		setTitle("D\u00E9tails livraison");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Detail.class.getResource("/image/logo/logo_secondaire.png")));
		this.setSize(263, 277);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNom = new JLabel();
		lblNom.setText("Nom : " + Liv);
		lblNom.setBounds(6, 6, 243, 16);
		contentPanel.add(lblNom);
		
		JLabel lblCible = new JLabel();
		lblCible.setText("Cible : " + XML.getCibleLiv(Uti, "L" + Liv));
		lblCible.setBounds(6, 68, 243, 16);
		contentPanel.add(lblCible);
		
		JLabel lblDcr = new JLabel();
		lblDcr.setText("DCR : " + XML.getDCRLiv(Uti, "L" + Liv));
		lblDcr.setBounds(6, 124, 243, 16);
		contentPanel.add(lblDcr);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur :");
		lblUtilisateur.setText("Utilisateur : " + Uti);
		lblUtilisateur.setBounds(6, 26, 243, 16);
		contentPanel.add(lblUtilisateur);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 54, 243, 2);
		contentPanel.add(separator);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText("Etape : " + XML.getEtapeLiv(Uti, "L" + Liv));
		lblNewLabel.setBounds(6, 96, 243, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblCalculateur = new JLabel();
		lblCalculateur.setText("Calculateur : " + XML.getCalculateurLiv(Uti, "L" + Liv));
		lblCalculateur.setBounds(6, 152, 243, 16);
		contentPanel.add(lblCalculateur);
		
		JLabel lblStandard = new JLabel();
		lblStandard.setText("Standard : " + XML.getStandardCalLiv(Uti, "L" + Liv));
		lblStandard.setBounds(6, 180, 243, 16);
		contentPanel.add(lblStandard);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setVisible(true);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton){
			this.hide();
		}
	}
}
