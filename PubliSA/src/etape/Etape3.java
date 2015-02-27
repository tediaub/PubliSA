package etape;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.User;
import outils.CreationMail;
import XML.XML;
import affichage.DialNouveau;
import affichage.FenetrePrincipale;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 */
public class Etape3 implements ActionListener {

	private static JPanel etape3;

	private static JPanel pAffEtape3 = new JPanel();
	
	private JPanel pDcr3;
	private JLabel lblDCR3;
	private static JTextField tfDCR3;
	
	public static JPanel pCalculateur;
	private static JRadioButton ckbSec;
	private static JRadioButton ckbElac;	
	private static JRadioButton ckbFcdc;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblStandard;
	private static JTextField tfStandard;

	public static JPanel pFichJoint;

	private static JLabel textInfo;

	public static JButton btCreation;
	
	public Etape3(){
	//////Etape 3
        //TODO Création de l'etape 3
		
		
		etape3 = new JPanel();
		etape3.setOpaque(false);
  		
		//////////
		pAffEtape3 = new FilAriane(3);
  		
		//////////
  		pDcr3 = new JPanel();
  		pDcr3.setOpaque(false);
  		pDcr3.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.TEXTE_PANEL_DCR.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
  		
  		lblDCR3 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.ENTRE_DCR.getLabel()));
  		
  		tfDCR3 = new JTextField();
  		tfDCR3.setColumns(10);
  		
  		pDcr3.setLayout(new FormLayout(new ColumnSpec[] {
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
  		
  		pDcr3.add(lblDCR3, "2, 2, fill, fill");
  		pDcr3.add(tfDCR3, "2, 4, 2, 1");

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
		pFichJoint = new JPanel();
  		pFichJoint.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.FICHIER_JOINT.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
  		pFichJoint.setOpaque(false);
		pFichJoint.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20mm"),
				ColumnSpec.decode("10mm"),
				ColumnSpec.decode("default:grow")},
			new RowSpec[] {
				RowSpec.decode("5mm"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("7mm")}));
		
		JLabel lblAprsLaCration = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.APRES_MAIL.getLabel()));
		textInfo = new JLabel();
		
		pFichJoint.add(lblAprsLaCration, "2, 4, 2, 1");
		lblAprsLaCration.setFont(new Font("Dialog", Font.BOLD, 12));
		pFichJoint.add(textInfo, "3, 6");
  		
  		//////////
  		btCreation = new JButton(GestLangue.getInstance().getLocalizedText(IHM.CREATION.getLabel()));
  		btCreation.setIcon(new ImageIcon(Etape3.class.getResource("/image/icones/1354447645_mail_write.png")));
  		btCreation.setIconTextGap(8);
  		btCreation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  		
  		etape3.setLayout(new FormLayout(new ColumnSpec[] {
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
  				RowSpec.decode("2mm"),
  				FormFactory.DEFAULT_ROWSPEC,
  				FormFactory.GLUE_ROWSPEC,}));
  		
  		etape3.add(pAffEtape3,"1, 1, fill, fill");
  		etape3.add(pDcr3,"1, 3, 2, 1, fill, fill");
  		etape3.add(pCalculateur,"1, 5, 2, 1 fill, fill");
  		etape3.add(pFichJoint,"1, 7, 2, 1, fill, fill");
  		etape3.add(btCreation,"1, 9, right, center");
  		
  		btCreation.addActionListener(this);
  	}
	
	/**
	 * Method getPanel.
	 * @return JPanel
	 */
	public static JPanel getPanel(){
		return (etape3);
	}
	
	public static String getDCR(){
		return tfDCR3.getText();
	}
	
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
	
	public static void miseAjour(){
		
		tfDCR3.setText(Delivery.getDCR());
		tfStandard.setText(Delivery.getStandard());

		if(Delivery.getCalculateur().contentEquals(GestLangue.getInstance().getLocalizedText(IHM.ELAC.getLabel()))){
			ckbElac.setSelected(true);
		}
		else if(Delivery.getCalculateur().contentEquals(GestLangue.getInstance().getLocalizedText(IHM.SEC.getLabel()))){
			ckbSec.setSelected(true);
		}
		else if(Delivery.getCalculateur().contentEquals(GestLangue.getInstance().getLocalizedText(IHM.FCDC.getLabel()))){
			ckbFcdc.setSelected(true);
		}
		
		if(Delivery.getCible().contentEquals("Ubik")){
			textInfo.setText(GestLangue.getInstance().getLocalizedText(IHM.A_JOINDRE_UBIK.getLabel()));
		}
		else if(Delivery.getCible().contentEquals("Thales")){
			textInfo.setText(GestLangue.getInstance().getLocalizedText(IHM.A_JOINDRE_THALES.getLabel()));
		}
		
		FenetrePrincipale.launchFichDOC.setEnabled(false);
		FenetrePrincipale.launchFichEXE.setEnabled(false);
		FenetrePrincipale.compteRendu.setEnabled(false);
		FenetrePrincipale.supprEntete.setEnabled(false);
	}
	
	public static void enregistre(){
		
		Delivery.setDCR(tfDCR3.getText());
		
		if (ckbSec.isSelected()){
			Delivery.setCalculateur(GestLangue.getInstance().getLocalizedText(IHM.SEC.getLabel()));
		}
		else if (ckbElac.isSelected()){
			Delivery.setCalculateur(GestLangue.getInstance().getLocalizedText(IHM.ELAC.getLabel()));
		}
		else if (ckbFcdc.isSelected()){
			Delivery.setCalculateur(GestLangue.getInstance().getLocalizedText(IHM.FCDC.getLabel()));
		}
		
		Delivery.setStandard(tfStandard.getText());
		
	    Delivery.enregistreLiv();
	}

	/**
	 * Method actionPerformed.
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btCreation){
			new CreationMail();
			if(Delivery.getCible().contentEquals("Ubik")){
				CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
		        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE4);
	
		        Delivery.setEtape(4);
		        Etape4.miseAjour();
		        FenetrePrincipale.getInstance().repaint();
		        Etape3.enregistre();
			}
			else if(Delivery.getCible().contentEquals("Thales")){
				
				Etape3.enregistre();
				
				if(User.getGestLiv()){
					XML.supprLiv(User.getNom(), "L" + Delivery.getNom());
				}
				new DialNouveau();
			}
		}			
	}
}

