package etape;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdom.Element;

import tutoriel.Tutoriel;
import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.User;
import myJTree.AffichageTree;
import XML.XML;
import affichage.FenetrePrincipale;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class Etape0 implements ActionListener {

	private static JPanel etape0;
	public static Color[] couleur;
	public static JPanel utilisateur;
	public static JComboBox<String> cbUtilisateur;
	public static JButton btnewUTI = new JButton(GestLangue.getInstance().getLocalizedText(IHM.NOUVEAU.getLabel()));
	
	public static JPanel Livraisons;
	
	public static JPanel continuer;
	public static JComboBox<String> cbLivraison = new JComboBox<String>();
	public static JButton btOKCont = new JButton(GestLangue.getInstance().getLocalizedText(IHM.CONTINUER.getLabel()));
	
	public static JLabel lblou = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.OU.getLabel()));
	
	public static JPanel nouveau;
	public static JRadioButton rbLivUbik;
	public static JRadioButton rbLivThales;
	public static ButtonGroup buttonGroup_1 = new ButtonGroup();
	public static JTextField tfLivraison;
	public static JButton btOKNew = new JButton(GestLangue.getInstance().getLocalizedText(IHM.CREER.getLabel()));
	
	/**
	 * 
	 * @author Teddy AUBERT
	 *
	 * @version $Revision: 1.0$
	 */
	public Etape0(){
		
		couleur = XML.getColor();
		
		etape0 = new JPanel();
		etape0.setOpaque(false);
		
		JLabel lblPrincipal = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.BIENVENUE.getLabel()));
		lblPrincipal.setForeground(couleur[1]);
		lblPrincipal.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		utilisateur = new JPanel();
		utilisateur.setOpaque(false);
		utilisateur.setBorder(new TitledBorder(new LineBorder(couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.UTI.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		cbUtilisateur = new JComboBox<String>();
		cbUtilisateur.setEditable(false);
		cbUtilisateur.addActionListener(this);
		
		refreshUtilisateur();	
		
		JLabel lblTextUti = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.CHOISIR_UN_UTI.getLabel()));
		JLabel lblTextNewUti = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.OU_CREER_NOUVEAU.getLabel()));

		btnewUTI.addActionListener(this);
		btnewUTI.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		utilisateur.setLayout(new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("10mm"),
        		ColumnSpec.decode("45mm"),
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("40mm")},
  			new RowSpec[] {
				RowSpec.decode("4mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("4mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("4mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("4mm")}));
		utilisateur.add(lblTextUti, "2, 2, 3, 1");
		utilisateur.add(cbUtilisateur, "2, 4, 3, 1");
		utilisateur.add(lblTextNewUti, "2,6");
		utilisateur.add(btnewUTI, "3, 6, left, center");
		
		////////////
		Livraisons = new JPanel();
		Livraisons.setOpaque(false);
		Livraisons.setBorder(new TitledBorder(new LineBorder(couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.LANCER_LIV.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		continuer = new JPanel();
		continuer.setOpaque(false);
		continuer.setBorder(new TitledBorder(new LineBorder(couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.CONTINUER_LIV.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));

		cbLivraison.setEditable(false);
		cbLivraison.removeAllItems();
		try{
			if(!XML.getModeUTI()){
				ArrayList<Element> livraison = XML.getLivraison(cbUtilisateur.getSelectedItem().toString());
				for(int i = 0; i<livraison.size();i++){
					cbLivraison.addItem(XML.getNomLiv(cbUtilisateur.getSelectedItem().toString(),livraison.get(i).getName()));
				}
			}
		}catch(Exception e){}
		
		JLabel lblpreContinuer = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.SELECT_LIV.getLabel()));		

		btOKCont.addActionListener(this);
		btOKCont.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		continuer.setLayout(new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("2mm"),
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("2mm")},
  			new RowSpec[] {
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm")}));
		
		continuer.add(lblpreContinuer, "2, 2, fill, center");
		continuer.add(cbLivraison, "2, 4, fill, center");
		continuer.add(btOKCont, "2, 6 , center, center");
		
		//////
		nouveau = new JPanel();
		nouveau.setOpaque(false);
		nouveau.setBorder(new TitledBorder(new LineBorder(couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.NEW_LIV.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		tfLivraison = new JTextField();
		rbLivUbik = new JRadioButton(GestLangue.getInstance().getLocalizedText(IHM.LIV_UBIK.getLabel()));
		rbLivThales = new JRadioButton(GestLangue.getInstance().getLocalizedText(IHM.LIV_THALES.getLabel()));
		buttonGroup_1.add(rbLivThales);
		buttonGroup_1.add(rbLivUbik);
		
		btOKNew.addActionListener(this);
		btOKNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblTypeLivraison = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.SELECT_TYPE_LIV.getLabel()));
		JLabel lblNomLivraison = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.ENTRER_NOM_LIV.getLabel()));
		
		nouveau.setLayout(new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("2mm"),
        		ColumnSpec.decode("4mm"),
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("2mm"),},
  			new RowSpec[] {
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("1mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("1mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("1mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("1mm")}));
		
		nouveau.add(lblTypeLivraison, "2, 2, 2, 1, left, center");
		nouveau.add(rbLivUbik, "3, 4, left, center");
		nouveau.add(rbLivThales, "3, 6, left, center");
		nouveau.add(lblNomLivraison, "2, 8, 2, 1, left, center");
		nouveau.add(tfLivraison, "2, 10, 2, 1, fill, center");
		nouveau.add(btOKNew, "2, 12, 2, 1, center, center");
		
		/////
		lblou.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		Livraisons.setLayout(new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("2mm"),
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("2mm"),
        		FormFactory.DEFAULT_COLSPEC,
        		ColumnSpec.decode("2mm"),
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("2mm")},
  			new RowSpec[] {
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm")}));
		
		Livraisons.add(continuer,"2,2, fill, fill");
		Livraisons.add(lblou,"4,2, center, center");
		Livraisons.add(nouveau,"6,2, fill, fill");
		
		
		JLabel lblLogoPubliSa = new JLabel();
		lblLogoPubliSa.setIcon(new ImageIcon(Etape0.class.getResource("/image/logo/logo2.png")));
		JLabel lblLogoAeroconseil = new JLabel();
		lblLogoAeroconseil.setIcon(new ImageIcon(Etape0.class.getResource("/image/logo/Nouvelle image (8).PNG")));
		
		etape0.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("4mm"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow(2)"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("4mm"),},
			new RowSpec[] {
				RowSpec.decode("8mm"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("4mm"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("2mm"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.GLUE_ROWSPEC,
				RowSpec.decode("4mm")}));
  		
		etape0.add(lblPrincipal,"3, 2, center, center");
		etape0.add(utilisateur,"3, 4, fill, fill");
		etape0.add(Livraisons,"3, 6, fill, fill");
		etape0.add(lblLogoAeroconseil,"2, 7, 2, 1, left, bottom");
		etape0.add(lblLogoPubliSa,"3, 7, 2, 1, right, bottom");
	}

	/**
	 * Récupère le panel qui contient l'etape 0
	 * @return JPanel
	 */
	public static JPanel getPanel(){
		return (etape0);
	}
	
	/**
	 * Rafraichit la comboBox qui liste toute les livraisons
	 */
	public static void refreshlivraison(){
		cbLivraison.removeAllItems();
		try{
			if(!cbUtilisateur.getSelectedItem().toString().isEmpty()){
				ArrayList<Element> livraison = XML.getLivraison(cbUtilisateur.getSelectedItem().toString());
				for(int i = 0; i<livraison.size();i++){
					cbLivraison.addItem(XML.getNomLiv(cbUtilisateur.getSelectedItem().toString(),livraison.get(i).getName()));
				}
			}
		}catch(Exception e){return;}
	}
	
	/**
	 * Rafraichit la comboBox qui liste tout les utilisateurs
	 */
	public static void refreshUtilisateur() {
		cbLivraison.addItem("");
		cbUtilisateur.removeAllItems();
		if(XML.getModeUTI()){
			cbUtilisateur.addItem("");
		}
		if(!XML.getFirstUti().isEmpty()){
			cbUtilisateur.addItem(XML.getFirstUti());
		}
		
		for(int i = 0; i<XML.getUTI().size();i++){
			if(!XML.getUTI().get(i).getName().contentEquals(XML.getFirstUti())){
				cbUtilisateur.addItem(XML.getUTI().get(i).getName());
			}
		}		
	}
	
	/**
	 * Mets à jour l'état des différents composant de l'étape 0
	 */
	public static void miseAjour(){
		refreshUtilisateur();
		refreshlivraison();
		
		buttonGroup_1.clearSelection();
		tfLivraison.setText(null);
		
		AffichageTree.getInstance().setEnabled(false);
		
		FenetrePrincipale.lblNom.setText(null);
		
		FenetrePrincipale.nouveau.setEnabled(false);		
		FenetrePrincipale.enregistrer.setEnabled(false);		
		
		FenetrePrincipale.reglage.setEnabled(false);
		FenetrePrincipale.outils.setEnabled(false);
	}
	
	/**
	 * Définie les différentes actions lancées par les boutons et les comboBox 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnewUTI){
			try{
				String nomNouveauUti = JOptionPane.showInputDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_IDENTITE.getLabel()), GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()), JOptionPane.QUESTION_MESSAGE);
				if(nomNouveauUti.contains(" ")){
					JOptionPane.showMessageDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_ESPACE.getLabel()), GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM_UTI.getLabel()),JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!nomNouveauUti.isEmpty()){
					XML.newUTI(nomNouveauUti);
					XML.setMesAkkaUbik(nomNouveauUti, 
							"corinne.cligny.external@airbus.com", 
							"Fichiers pour livraison UBIK",
							"Bonjour Corinne,\r\n\r\nPeux-tu, s'il te plait, me mettre \u00E0 disposition les fichiers suivants @ :\r\n      -Le fichier .OGC de la MOD#\r\n      -Les fichiers .EXT et .EST de la MOD#\r\n      -Les fichiers .POS *\r\n\r\nPar avance, merci.\r\n");
					XML.setMesAkkaThales(nomNouveauUti, 
							"corinne.cligny.external@airbus.com", 
							"Fichiers pour livraison THALES", 
							"Bonjour Corinne,\r\n\r\nPeux-tu, s'il te plait, me mettre \u00E0 disposition les fichiers suivants @ :\r\n      -Le fichier .OGC de la MOD#\r\n      -Les fichiers .ASC et .SCH *\r\n\r\nPar avance, merci.\r\n\r\n");
					XML.setMesSopra(nomNouveauUti, 
							"support.soprafs@sopragroup.com", 
							"UBIK Update -",
							"Hello,\r\n\r\nPlease find attached the request for software installation on UBIK of software @.\r\n\r\nBest regards\r\n\r\n\r\n");
					XML.setMesThales(nomNouveauUti, 
							"Exemple@thales.com", "THALES", "");
							
					XML.enregistreFichier();
					
					cbUtilisateur.addItem(nomNouveauUti);
					cbUtilisateur.setSelectedItem(nomNouveauUti);
				}else{
					JOptionPane.showMessageDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_NOM_UTI.getLabel()), GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM_UTI.getLabel()),JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			catch(Exception ex){
				
			}
		}
		if(e.getSource() == cbUtilisateur){
			refreshlivraison();
			if(FenetrePrincipale.boolTuto){
				//Tutoriel.next(1);
			}
		}
		
		if (e.getSource() == btOKCont){
			//Message d'erreur
			if(cbUtilisateur.getSelectedItem().toString().isEmpty()){
				JOptionPane.showMessageDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_SELECT_UTI.getLabel()),GestLangue.getInstance().getLocalizedText(IHM.ERREUR_UTI.getLabel()),JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(cbLivraison.getSelectedItem().toString().isEmpty()){
				JOptionPane.showMessageDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_SELECT_LIV.getLabel()),GestLangue.getInstance().getLocalizedText(IHM.ERREUR_LIV.getLabel()),JOptionPane.ERROR_MESSAGE);
				return;
			}
			//
			new User(cbUtilisateur.getSelectedItem().toString());
			new Delivery(User.getNom(), cbLivraison.getSelectedItem().toString());
			FenetrePrincipale.lblNom.setText(User.getNom() + "  |  " + Delivery.getNom());
			
			CardLayout cl2 = (CardLayout) FenetrePrincipale.getCards().getLayout();
			cl2.show(FenetrePrincipale.getCards(), FenetrePrincipale.OTHERS);
			CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			switch (Delivery.getEtape())
			{
			case 1:
				cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE1);
				Etape1.miseAjour();
				FenetrePrincipale.getInstance().repaint();
				break;
			case 2:
				cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE2);
				Etape2.miseAjour();
				FenetrePrincipale.getInstance().repaint();
				break;
			case 3:
				cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE3);
				Etape3.miseAjour();
				FenetrePrincipale.getInstance().repaint();
				break;
			case 4:
				cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE4);
				Etape4.miseAjour();
				FenetrePrincipale.getInstance().repaint();
				break;
			}
			
			AffichageTree.miseAjour();
			AffichageTree.getInstance().setEnabled(true);
			
			//Enable des menus		
			FenetrePrincipale.nouveau.setEnabled(true);		
			FenetrePrincipale.enregistrer.setEnabled(true);		
			
			FenetrePrincipale.reglage.setEnabled(true);
			FenetrePrincipale.outils.setEnabled(true);
			
			FenetrePrincipale.launchFichDOC.setEnabled(false);
			FenetrePrincipale.launchFichEXE.setEnabled(false);
			FenetrePrincipale.compteRendu.setEnabled(false);
			FenetrePrincipale.supprEntete.setEnabled(false);
		}
		
		if (e.getSource() == btOKNew){
			
			String nomLiv = tfLivraison.getText();
			// message d'erreur
			if(nomLiv.contains(" ")){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.MES_ESPACE.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM.getLabel()),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(cbUtilisateur.getSelectedItem().toString().isEmpty()){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.MES_SELECT_UTI.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.ERREUR_UTI.getLabel()),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
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
			
			//Gestions utilisateur, livraison et XML
			new User(cbUtilisateur.getSelectedItem().toString());
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
			FenetrePrincipale.lblNom.setText(User.getNom() + "  |  " + Delivery.getNom());
			
			CardLayout cl2 = (CardLayout) FenetrePrincipale.getCards().getLayout();
			cl2.show(FenetrePrincipale.getCards(), FenetrePrincipale.OTHERS);
			CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
	        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE1);
	        
			try {
				XML.enregistreFichier();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Etape1.miseAjour();
			Etape1.getPanel().repaint();
			AffichageTree.miseAjour();
			AffichageTree.getInstance().setEnabled(true);
			
			//Enable des menus		
			FenetrePrincipale.nouveau.setEnabled(true);		
			FenetrePrincipale.enregistrer.setEnabled(true);		
			
			FenetrePrincipale.reglage.setEnabled(true);
			FenetrePrincipale.outils.setEnabled(true);
			
			FenetrePrincipale.launchFichDOC.setEnabled(false);
			FenetrePrincipale.launchFichEXE.setEnabled(false);
			FenetrePrincipale.compteRendu.setEnabled(false);
			FenetrePrincipale.supprEntete.setEnabled(false);
			
			if(FenetrePrincipale.boolTuto){
				Tutoriel.next(3);
			}
		}
	}
	
	public static void setEnabled(boolean i){
		btnewUTI.setEnabled(i);
		btOKCont.setEnabled(i);
		btOKNew.setEnabled(i);
		cbLivraison.setEnabled(i);
		cbUtilisateur.setEnabled(i);
		rbLivThales.setEnabled(i);
		rbLivUbik.setEnabled(i);
		tfLivraison.setEnabled(i);
	}
}