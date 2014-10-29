package etape;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import langue.GestLangue;
import langue.IHM;

import outils.CreationMail;
import utilisateur.Livraison;
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
public class Etape1 implements ActionListener {

	private static JPanel etape1;

	public static FilAriane pAffEtape1;
	
	public static JPanel pDcr1;
	private static JLabel lblDCR1;
	public static JTextField tfDCR1;
	
	public static JPanel pCalculateur;
	private static JRadioButton ckbSec;
	private static JRadioButton ckbElac;	
	private static JRadioButton ckbFcdc;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblStandard;
	public static JTextField tfStandard;

	public static JButton btCreation;
	
	public Etape1(){
	//////Etape 1
        //TODO Création de l'etape 1
		
		etape1 = new JPanel();
		etape1.setOpaque(false);
		
		//////////
		
		pAffEtape1 = new FilAriane(1);
		
  		//////////
  		pDcr1 = new JPanel();
  		pDcr1.setOpaque(false);
  		pDcr1.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.TEXTE_PANEL_DCR.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
  		
  		lblDCR1 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.ENTRE_DCR.getLabel()));
  		
  		tfDCR1 = new JTextField();
  		tfDCR1.setColumns(10);
  		
  		pDcr1.setLayout(new FormLayout(new ColumnSpec[] {
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
  		
  		pDcr1.add(lblDCR1, "2, 2, fill, fill");
  		pDcr1.add(tfDCR1, "2, 4, 2, 1");

  		//////////
  		pCalculateur = new JPanel();
  		pCalculateur.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.TEXTE_PANEL_CALCULATEUR.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
  		pCalculateur.setOpaque(false);
  		
  		ckbSec = new JRadioButton(GestLangue.getInstance().getLocalizedText(IHM.SEC.getLabel()));
  		ckbSec.setOpaque(false);
  		ckbElac = new JRadioButton(GestLangue.getInstance().getLocalizedText(IHM.ELAC.getLabel()));
  		ckbElac.setOpaque(false);
  		ckbFcdc = new JRadioButton(GestLangue.getInstance().getLocalizedText(IHM.FCDC.getLabel()));
  		ckbFcdc.setOpaque(false);
		buttonGroup_1.add(ckbSec);
		buttonGroup_1.add(ckbElac);
		buttonGroup_1.add(ckbFcdc);
		
  		lblStandard = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.STANDARD.getLabel()));
  		
  		tfStandard = new JTextField();
  		tfStandard.setColumns(10);
  		
		pCalculateur.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20mm"),
	     		ColumnSpec.decode("default:grow"),
	     		FormFactory.RELATED_GAP_COLSPEC,
	     		ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("20mm"),},
			new RowSpec[] {
				RowSpec.decode("1mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("1mm")}));
		
		pCalculateur.add(ckbSec, "2, 2");
		pCalculateur.add(ckbElac, "4, 2");
		pCalculateur.add(ckbFcdc, "6, 2");
		pCalculateur.add(lblStandard, "8, 2, right, fill");
		pCalculateur.add(tfStandard, "9, 2");
  		
  		//////////
  		btCreation = new JButton(GestLangue.getInstance().getLocalizedText(IHM.CREATION.getLabel()));
  		btCreation.setIcon(new ImageIcon(Etape1.class.getResource("/image/icones/1354447645_mail_write.png")));
  		btCreation.setIconTextGap(8);
  		btCreation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  		
  		etape1.setLayout(new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("default:grow"),
        		ColumnSpec.decode("0.5mm"),
        		ColumnSpec.decode("2mm"),},
  			new RowSpec[] {
  				RowSpec.decode("9mm"),
  				RowSpec.decode("2mm"),
				FormFactory.DEFAULT_ROWSPEC,
  				RowSpec.decode("2mm"),
				FormFactory.DEFAULT_ROWSPEC,
  				RowSpec.decode("2mm"),
  				FormFactory.DEFAULT_ROWSPEC,
  				FormFactory.GLUE_ROWSPEC,}));
  		
  		etape1.add(pAffEtape1,"1, 1, fill, fill");
  		etape1.add(pDcr1,"1, 3, 2, 1, fill, fill");
  		etape1.add(pCalculateur,"1, 5, 2, 1 fill, fill");
  		etape1.add(btCreation,"1, 7, right, center");
  		
  		
  		btCreation.addActionListener(this);
  	}
	
	/**
	 * Récupère le panel qui contient l'etape 1
	 * @return JPanel
	 */
	public static JPanel getPanel(){
		return (etape1);
	}
	
	/**
	 * Retourne les numèros de DCR entrés par l'utilisateur
	 * @return String
	 */
	public static String getDCR(){
		return tfDCR1.getText();
	}
	
	/**
	 * Retourne le calculateur et le standard entré par l'utilisateur
	 * Si les données de l'utilisateur sont vide, retourne null
	 * @return String
	 */
	public static String getCalculateur(){
		String standard;
		if (ckbSec.isSelected()){
			standard = "SEC " + tfStandard.getText();
			return (standard);
		}else if (ckbElac.isSelected()){
			standard = "ELAC " + tfStandard.getText();
			return (standard);
		}else if (ckbFcdc.isSelected()){
			standard = "FCDC " + tfStandard.getText();
			return (standard);
		}
		return null;
	}
	
	/**
	 * Mets à jour l'état des différents composant de l'étape 1
	 */
	public static void miseAjour(){
		tfDCR1.setText(Livraison.getDCR());
		tfStandard.setText(Livraison.getStandard());

		if(Livraison.getCalculateur().contentEquals(GestLangue.getInstance().getLocalizedText(IHM.ELAC.getLabel()))){
			ckbElac.setSelected(true);
		}
		else if(Livraison.getCalculateur().contentEquals(GestLangue.getInstance().getLocalizedText(IHM.SEC.getLabel()))){
			ckbSec.setSelected(true);
		}
		else if(Livraison.getCalculateur().contentEquals(GestLangue.getInstance().getLocalizedText(IHM.FCDC.getLabel()))){
			ckbFcdc.setSelected(true);
		}
		else{
			ckbElac.setSelected(false);
			ckbSec .setSelected(false);
			ckbFcdc.setSelected(false);
		}
	}
	
    /**
     * Enregistre les modifications apporté par l'utilisateur à l'etape 1
     */
	public static void enregistre(){
		
		Livraison.setDCR(tfDCR1.getText());
		
		if (ckbSec.isSelected()){
			Livraison.setCalculateur(GestLangue.getInstance().getLocalizedText(IHM.SEC.getLabel()));
		}
		else if (ckbElac.isSelected()){
			Livraison.setCalculateur(GestLangue.getInstance().getLocalizedText(IHM.ELAC.getLabel()));
		}
		else if (ckbFcdc.isSelected()){
			Livraison.setCalculateur(GestLangue.getInstance().getLocalizedText(IHM.FCDC.getLabel()));
		}
		
		Livraison.setStandard(tfStandard.getText());
		
	    Livraison.enregistreLiv();
	}

	/**
	 * Définie les différentes actions lancées par le bouton "Création"
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btCreation){
			CreationMail mail = new CreationMail();
			if(mail.getErreur()){
				return;
			}
			CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
	        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE2);
	        
	        Livraison.setEtape(2);
	        Etape1.enregistre();
	        Etape2.miseAjour();
	        FenetrePrincipale.getInstance().repaint();
		}		
	}
	
	public static void setEnabled(boolean i){
		tfDCR1.setEnabled(i);
		ckbSec.setEnabled(i);
		ckbElac.setEnabled(i);
		ckbFcdc.setEnabled(i);
		tfStandard.setEnabled(i);
		btCreation.setEnabled(i);
	}
}
