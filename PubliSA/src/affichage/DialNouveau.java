package affichage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.User;
import myJTree.AffichageTree;
import XML.XML;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import etape.Etape0;
import etape.Etape1;

/**
 * 
 * @author Teddy AUBERT
 *
 * @version $Revision: 1.0 $
 */

@SuppressWarnings("serial")
public class DialNouveau extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	
	private JButton okButton;
	private JButton cancelButton;

	private JTextField tfLivraison;

	private JRadioButton rbLivUbik;

	private JRadioButton rbLivThales;

	/**
	 * Crée la fenetre "Nouveau".
	 */
	public DialNouveau() {
		
		//Initialisation de la fenêtre
		this.setModal(true);
		this.setResizable(false);
		this.setTitle(GestLangue.getInstance().getLocalizedText(IHM.NEW_LIV.getLabel()));
		this.setSize(361, 250);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(FenAPropos.class.getResource("/image/logo/logo_secondaire.png")));
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		this.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//Création du panel "Nouveau"
		JPanel nouveau = new JPanel();
		nouveau.setBounds(6, 6, 341, 166);
		nouveau.setBackground(Color.WHITE);
		nouveau.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]),null, TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		tfLivraison = new JTextField();
		rbLivUbik = new JRadioButton(GestLangue.getInstance().getLocalizedText(IHM.LIV_UBIK.getLabel()));
		rbLivThales = new JRadioButton(GestLangue.getInstance().getLocalizedText(IHM.LIV_THALES.getLabel()));
		ButtonGroup buttonGroup_1 = new ButtonGroup();
		buttonGroup_1.add(rbLivThales);
		buttonGroup_1.add(rbLivUbik);
		
		
		JLabel lblTypeLivraison = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.SELECT_TYPE_LIV.getLabel()));
		JLabel lblNomLivraison = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.ENTRER_NOM_LIV.getLabel()));
		
		//Mise en forme du panel Nouveau
		nouveau.setLayout(new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("4mm"),
        		ColumnSpec.decode("4mm"),
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("4mm"),},
  			new RowSpec[] {
  				RowSpec.decode("4mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("1mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("1mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("1mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("4mm")}));
		
		nouveau.add(lblTypeLivraison, "2, 2, 2, 1, left, center");
		nouveau.add(rbLivUbik, "3, 4, left, center");
		nouveau.add(rbLivThales, "3, 6, left, center");
		nouveau.add(lblNomLivraison, "2, 8, 2, 1, left, center");
		nouveau.add(tfLivraison, "2, 10, 2, 1, fill, center");
		
		contentPanel.add(nouveau);
		
		//Création du panel qui contient les bouton ok et cancel"
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		this.getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Annuler");
		cancelButton.setActionCommand("Annuler");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
		//Rend le dialogue visible
		this.setVisible(true);
	}

	
	/**
	 * Définie les différentes actions lancées par les boutons
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		//bouton ok
		if (e.getSource() == okButton){
			String nomLiv = tfLivraison.getText();
			// messages d'erreurs
			if(nomLiv.isEmpty()){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.MES_NOM_LIV.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM.getLabel()),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!rbLivThales.isSelected() && !rbLivUbik.isSelected()){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.MES_TYPE_LIV.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.ERREUR_TYPE.getLabel()),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//Vérifie la présence du nom entrer.
			for(int i = 0; i < XML.getUTI().size();i++){
				String uti = XML.getUTI().get(i).getName();
				for(int j = 0; j < XML.getLivraison(uti).size(); j++){
					String liv = XML.getLivraison(uti).get(j).getName();
					if(XML.getNomLiv(uti, liv).contentEquals(nomLiv)){
						JOptionPane.showMessageDialog(null, 
								GestLangue.getInstance().getLocalizedText(IHM.MES_NOM_IDENTIQUE_LIV.getLabel()),
								GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM.getLabel()),
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			
			if(nomLiv.contains(" ")){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.MES_ESPACE.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM.getLabel()),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//Création d'une nouvelle livraison
			XML.newLivraison(User.getNom(), nomLiv);
			
			try {
				XML.enregistreFichier();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			XML.setEtapeLiv(User.getNom(), "L" + nomLiv, "1");
			XML.setNomLiv(User.getNom(), "L" + nomLiv, tfLivraison.getText());
			
			if(rbLivThales.isSelected()){
				XML.setCibleLiv(User.getNom(), "L" + nomLiv, "Thales");
			}
			else if(rbLivUbik.isSelected()){
				XML.setCibleLiv(User.getNom(), "L" + nomLiv, "Ubik");
			}
			
			new Delivery(User.getNom(), nomLiv);
			
			try {
				XML.enregistreFichier();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//Mise à jour de l'interface graphique
			FenetrePrincipale.lblNom.setText(User.getNom() + "  |  " + Delivery.getNom());
			
			CardLayout cl2 = (CardLayout) FenetrePrincipale.getCards().getLayout();
			cl2.show(FenetrePrincipale.getCards(), FenetrePrincipale.OTHERS);
			CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
	        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE1);

			Etape1.miseAjour();
			FenetrePrincipale.getInstance().repaint();
			AffichageTree.miseAjour();
			
			//Fermeture de la fenêtre
			this.hide();
		}
		
		//bouton annuler
		if (e.getSource() == cancelButton){
			//Fermeture de la fenêtre
			this.hide();
		}
	}
}