package etape;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

import jxl.write.WriteException;

import outils.Lanceur;
import outils.SupprEntete;

import utilisateur.Livraison;
import utilisateur.Utilisateur;
import verification.CreationRapportEtape2;
import verification.VerificationAkkaUbik;
import verification.VerificationAkkaThales;

import langue.GestLangue;
import langue.IHM;
import myJTable.TableEtape2;


import affichage.FenetrePrincipale;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 *	
 * @author Teddy AUBERT
 *
 * @version $Revision: 1.0 $
 */
public class Etape2 implements ActionListener {

	private static JPanel etape2;

	private static JPanel pAffEtape2;
	public static JButton btEtape2;
	
	public static JPanel pDcr2;
	private static JLabel lblDCR2;
	public static JTextField tfDCR2;
	
	public static JPanel pParcourir;
	public static JButton btParcourir;
	private static JLabel lblLesFichiersDoivent;	
	private static JLabel lblOuvrirLeFichier;
	private static JLabel lblLesFichierspos;
	
	public static JPanel pVerification;
	public static JLabel lblNombreDeFichiersSom;

	public static JLabel lblNombreDeFichiersDos;
	private static JScrollPane spTable2;
	
	public static String adresseOGC = "";

	public static JButton btCompteRendu;
	public static JPanel panel;
	public static JButton btsupprEntete;
	public static JButton btDOC_EXE;
	
	private static boolean next1 = false;
	private static boolean next2 = false;
	
	public static JPanel pOutil;
	
	public Etape2(){
	//////Etape 2  		
  		
  		etape2 = new JPanel();
  		etape2.setOpaque(false);
		
  		//////////
		pAffEtape2 = new FilAriane(2);
		
		//////////
		pDcr2 = new JPanel();
		pDcr2.setOpaque(false);
  		pDcr2.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.TEXTE_PANEL_DCR.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		lblDCR2 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.ENTRE_DCR.getLabel()));
		
		tfDCR2 = new JTextField();
		tfDCR2.setColumns(10);
		
		pDcr2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10mm"),
	     		ColumnSpec.decode("default:grow"),
	     		ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("10mm"),},
			new RowSpec[] {
				RowSpec.decode("1mm"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("2mm")}));
  		
		pDcr2.add(lblDCR2, "2, 2, fill, fill");
		pDcr2.add(tfDCR2, "2, 4, 2, 1");
		
		//////////
		pParcourir = new JPanel();
		pParcourir.setOpaque(false);
		pParcourir.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.FICHIER_OGC.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		lblLesFichiersDoivent = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.FICHIER_DEZIPPER.getLabel()));
		lblLesFichiersDoivent.setFont(new Font("Dialog", Font.BOLD, 12));

		lblOuvrirLeFichier = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.OUVRIR_OGC.getLabel()));
		
		lblLesFichierspos = new JLabel();
		
		btParcourir = new JButton(GestLangue.getInstance().getLocalizedText(IHM.PARCOURIR_VERIFIER.getLabel()));
		btParcourir.setIcon(new ImageIcon(Etape2.class.getResource("/image/icones/1354387681_file_search.png")));
		btParcourir.setIconTextGap(5);
		btParcourir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		pParcourir.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("5mm"),
				ColumnSpec.decode("5mm"),
				ColumnSpec.decode("default:grow"),
				FormFactory.MIN_COLSPEC,
				ColumnSpec.decode("5mm"),},
			new RowSpec[] {
				RowSpec.decode("1mm"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("2mm"),}));
  		
		pParcourir.add(lblLesFichiersDoivent, "2, 2, 3, 1, fill, fill");
		pParcourir.add(lblOuvrirLeFichier, "3, 4");
		pParcourir.add(lblLesFichierspos, "2, 6, 3, 1, fill, fill");
		pParcourir.add(btParcourir, "4, 4");
		
		//////////
		pVerification = new JPanel();
		pVerification.setOpaque(false);
		pVerification.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.RESULTAT_VERIF.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		lblNombreDeFichiersSom = new JLabel();
		
		lblNombreDeFichiersDos = new JLabel();
		
		new TableEtape2();
		spTable2 = TableEtape2.getPanel();
		
		btCompteRendu = new JButton(GestLangue.getInstance().getLocalizedText(IHM.BT_COMPTE_RENDU.getLabel()));
		btCompteRendu.setEnabled(false);
		btCompteRendu.addActionListener(this);
		btCompteRendu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		pVerification.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("3mm"),
				ColumnSpec.decode("8mm"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("2mm"),
				ColumnSpec.decode("3mm"),},
			new RowSpec[] {
				RowSpec.decode("1mm"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("2mm"),}));
  		
		pVerification.add(lblNombreDeFichiersSom, "3, 2, fill, fill");
		pVerification.add(lblNombreDeFichiersDos, "3, 6, fill, fill");
		pVerification.add(btCompteRendu, "4, 2, 1, 5, right, center");
		pVerification.add(spTable2, "2, 8, 4, 1");
		
		//////////
		
		
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.AVANT_ETAPE_SUIV.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		btDOC_EXE = new JButton();
		btDOC_EXE.setEnabled(false);
		btDOC_EXE.addActionListener(this);
		btDOC_EXE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btsupprEntete = new JButton(GestLangue.getInstance().getLocalizedText(IHM.BT_SUP_ENTETE.getLabel()));
		btsupprEntete.addActionListener(this);
		btsupprEntete.setEnabled(false);
		btsupprEntete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("5mm"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("5mm"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("5mm"),},
			new RowSpec[] {
				RowSpec.decode("2mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("2mm"),}));
		
		
		panel.add(btsupprEntete, "4, 2");
		panel.add(btDOC_EXE, "2, 2");
		
		pOutil = new JPanel();
		pOutil.setBounds(12, 12, 641, 55);
		pOutil.setOpaque(false);
		
		JLabel lbletape = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.ETAPE3.getLabel()));
		lbletape.setFont(new Font("Dialog", Font.BOLD, 12));
		
		btEtape2 = new JButton();
		btEtape2.addActionListener(this);
		btEtape2.setIcon(new ImageIcon(Etape2.class.getResource("/image/icones/Sans titre 1.png")));
		btEtape2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		pOutil.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				FormFactory.MIN_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));		
		
		pOutil.add(panel, "1, 1, fill, fill");
		pOutil.add(lbletape, "2, 1, right, center");
		pOutil.add(btEtape2, "3, 1, left, center");

		//////////
		etape2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("0.5mm"),
				ColumnSpec.decode("2mm"),},
			new RowSpec[] {
				RowSpec.decode("9mm"),
				RowSpec.decode("2mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("2mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("2mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("2mm"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
  		
		etape2.add(pAffEtape2,"1, 1, fill, fill");
		etape2.add(pDcr2,"1, 3, 2, 1, fill, fill");
		etape2.add(pDcr2,"1, 3, 2, 1, fill, fill");
		etape2.add(pParcourir,"1, 5, 2, 1 fill, fill");
		etape2.add(pVerification,"1, 7, 2, 1, fill, fill");
		etape2.add(pOutil,"1, 9, 2, 1, fill, fill");
		
		btParcourir.addActionListener(this);	
	}
	
	/**
	 * Récupère le panel qui contient l'etape 2
	 * @return JPanel
	 */
	public static JPanel getPanel(){
		return (etape2);
	}
	
	/**
	 * Retourne les numèros de DCR entrés par l'utilisateur
	 * @return String
	 */
	public static String getDCR(){
		return (tfDCR2.getText());
	}

	/**
	 * Définie les différentes actions lancées par le bouton
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btDOC_EXE){
			if(Utilisateur.getExe().isEmpty() && Livraison.getCible().contentEquals("Thales")){
				JOptionPane.showMessageDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_DOC.getLabel()), GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()), JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(Utilisateur.getDoc().isEmpty() && Livraison.getCible().contentEquals("Ubik")){
				JOptionPane.showMessageDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_DOC.getLabel()), GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()), JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if(Livraison.getCible().contentEquals("Ubik")){
				if(next1){
					btEtape2.setEnabled(true);
				}
				next2 = true;
			}
			else{
				btEtape2.setEnabled(true);
			}
			if(Livraison.getCible().contentEquals("Ubik")){
				new Lanceur(Utilisateur.getDoc());
			}
			else if(Livraison.getCible().contentEquals("Thales")){
				new Lanceur(Utilisateur.getExe());
			}
		}
		if (e.getSource() == btsupprEntete){
			if(Livraison.getCible().contentEquals("Ubik")){
				if(next2){
					btEtape2.setEnabled(true);
				}
				next1 = true;
			}
			new SupprEntete(adresseOGC);			
		}
		if (e.getSource() == btCompteRendu){
			try {
				new CreationRapportEtape2();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == btParcourir){
			enregistre();
			miseAjour();
			String path = null;
			
			if(Livraison.getCible().contentEquals("Ubik")){
				new VerificationAkkaUbik();
				if(VerificationAkkaUbik.isFini()){
					adresseOGC = VerificationAkkaUbik.adresseOGC;
					Livraison.enregistreLiv();
					
					FenetrePrincipale.launchFichDOC.setEnabled(true);
					btDOC_EXE.setEnabled(true);
					FenetrePrincipale.compteRendu.setEnabled(true);
					btCompteRendu.setEnabled(true);
					FenetrePrincipale.supprEntete.setEnabled(true);
					btsupprEntete.setEnabled(true);
					path = VerificationAkkaUbik.OGC.getParentFile().getPath();
				}
				else return;
			}
			else if(Livraison.getCible().contentEquals("Thales")){
				new VerificationAkkaThales();
				
				if(VerificationAkkaThales.isFini()){
					Livraison.enregistreLiv();
				
					FenetrePrincipale.launchFichEXE.setEnabled(true);
					btDOC_EXE.setEnabled(true);
					FenetrePrincipale.compteRendu.setEnabled(true);
					btCompteRendu.setEnabled(true);
					path = VerificationAkkaThales.OGC.getParentFile().getPath();
				}
				else return;
			}
			try {
				JOptionPane.showMessageDialog(null, GestLangue.getInstance().getLocalizedText(IHM.MES_VERIF_OK.getLabel()) 
						+ path + File.separator+ Livraison.getNom() + "."  , GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()), JOptionPane.INFORMATION_MESSAGE);
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			enregistre();
		}
		if (e.getSource() == btEtape2){
			CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
	        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE3);

	        Livraison.setEtape(3);
	        Etape2.enregistre();
	        Etape3.miseAjour();
	        FenetrePrincipale.getInstance().repaint();
		}
	}
	
	/**
	 * Mets à jour l'état des différents composant de l'étape 2
	 */
	public static void miseAjour(){
		
		tfDCR2.setText(Livraison.getDCR());
		TableEtape2.removeJTable();
		
		btEtape2.setEnabled(false);
		btCompteRendu.setEnabled(false);
		btsupprEntete.setEnabled(false);
		btDOC_EXE.setEnabled(false);
		next1 = false;
		next2 = false;
		
		if(Livraison.getCible().contentEquals("Thales")){
			btsupprEntete.setVisible(false);
			btDOC_EXE.setText("Exécutable FileCheck MD5");
			panel.remove(btDOC_EXE);
			panel.add(btDOC_EXE, "2, 2, 3, 1, fill, center");
			lblLesFichierspos.setText(GestLangue.getInstance().getLocalizedText(IHM.POSITION_ASC_SCH.getLabel()));
			lblNombreDeFichiersSom.setText(GestLangue.getInstance().getLocalizedText(IHM.NB_ASC_SCH.getLabel()));
			lblNombreDeFichiersDos.setText(GestLangue.getInstance().getLocalizedText(IHM.NB_ASC_SCH_SOURCE.getLabel()));
		}
		else if(Livraison.getCible().contentEquals("Ubik")){
			btsupprEntete.setVisible(true);
			btDOC_EXE.setText("Formulaire EYDT");
			panel.remove(btDOC_EXE);
			panel.add(btDOC_EXE, "2, 2, fill, center");
			lblLesFichierspos.setText(GestLangue.getInstance().getLocalizedText(IHM.POSITION_POS.getLabel()));
			lblNombreDeFichiersSom.setText(GestLangue.getInstance().getLocalizedText(IHM.NB_POS.getLabel()));
			lblNombreDeFichiersDos.setText(GestLangue.getInstance().getLocalizedText(IHM.NB_POS_SOURCE.getLabel()));
		}
	}
	
	/**
     * Enregistre les modifications apporté par l'utilisateur à l'etape 2
     */
	public static void enregistre(){
		Livraison.setDCR(tfDCR2.getText());
		Livraison.setOGC(adresseOGC);
		Livraison.enregistreLiv();
	}
	
	/**
	 * Retourne l'adresse du fichier OGC entrés par l'utilisateur
	 * @return String
	 */
	public static String getOGC(){
		return adresseOGC;
	}
}