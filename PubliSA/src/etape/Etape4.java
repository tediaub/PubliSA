package etape;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import jxl.write.WriteException;

import sauvergarde_chargement.*;
import utilisateur.*;
import verification.CreationRapportEtape4;
import verification.VerificationUbikUbik;

import langue.GestLangue;
import langue.IHM;
import myJTable.TableEtape4;

import XML.XML;

import affichage.DialNouveau;
import affichage.FenetrePrincipale;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 */
public class Etape4 implements ActionListener {

	private static JPanel etape4;

	private static JPanel pAffEtape4 = new JPanel();
	public static JButton btEtape4;
	public static JPanel pOutil;
	
	public static JPanel pParcourir;
	private JLabel lblOGC;
	private static JLabel lblPathOGC;
	private JLabel lblCSV;
	private static JLabel lblPathCSV;
	public static JButton btOGC;
	public static JButton btCSV;
	public static JButton btVerif;
	private JScrollPane spTable4;
	
	private static JButton btCompteRendu;
	public static JPanel panel;
	
	public Etape4(){
	//////Etape 4
        //TODO Création de l'etape 4
		
		
		etape4 = new JPanel();
		etape4.setOpaque(false);

  		//////////
		pAffEtape4 = new FilAriane(4);
		
  		//////////
		pParcourir = new JPanel();
  		pParcourir.setOpaque(false);
  		pParcourir.setBorder(new TitledBorder(new LineBorder(new Color(99, 130, 191)), GestLangue.getInstance().getLocalizedText(IHM.FICHIER_CIBLE.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
  		
  		lblOGC = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.OUVRIR_OGC_ETAPE4.getLabel()));
  		lblOGC.setFont(new Font("Dialog", Font.BOLD, 12));
  		lblPathOGC = new JLabel();
  		lblCSV = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.OUVRIR_CSV.getLabel()));
  		lblCSV.setFont(new Font("Dialog", Font.BOLD, 12));
  		lblPathCSV = new JLabel();
  		
  		btOGC = new JButton(GestLangue.getInstance().getLocalizedText(IHM.PARCOURIR.getLabel()));
  		btOGC.addActionListener(this);
  		btOGC.setIcon(new ImageIcon(Etape4.class.getResource("/image/icones/1354387681_file_search.png")));
  		btOGC.setIconTextGap(5);
  		btOGC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
  		btCSV = new JButton(GestLangue.getInstance().getLocalizedText(IHM.PARCOURIR.getLabel()));
  		btCSV.addActionListener(this);
  		btCSV.setIcon(new ImageIcon(Etape4.class.getResource("/image/icones/1354387681_file_search.png")));
  		btCSV.setIconTextGap(5);  	
  		btCSV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

  		btVerif = new JButton(GestLangue.getInstance().getLocalizedText(IHM.VERIFIER.getLabel()));
  		btVerif.addActionListener(this);
  		btVerif.setIcon(new ImageIcon(Etape4.class.getResource("/image/icones/Sans titre 4.png")));
  		btVerif.setIconTextGap(5);
  		btVerif.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  		
  		pParcourir.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("5mm"),
				ColumnSpec.decode("5mm"),
	     		ColumnSpec.decode("default:grow"),
	     		ColumnSpec.decode("default:grow"),
	     		ColumnSpec.decode("30mm"),
				ColumnSpec.decode("10mm"),},
			new RowSpec[] {
				RowSpec.decode("1mm"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("2mm")}));
  		
  		pParcourir.add(lblOGC, "3, 2, 2, 1, fill, fill");
  		pParcourir.add(lblPathOGC, "2, 3, 3, 1, fill, fill");
  		pParcourir.add(lblCSV, "3, 5, 2, 1, fill, fill");
  		pParcourir.add(lblPathCSV, "2, 6, 3, 1, fill, fill");
  		pParcourir.add(btOGC, "5, 3, default, default");
  		pParcourir.add(btCSV, "5, 6, default, default");
  		pParcourir.add(btVerif, "1, 8, 6, 1 , center, default");

  		//////////  
		new TableEtape4();
		spTable4 = TableEtape4.getPanel();
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.RESULTAT_VERIF.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
  		panel.setLayout(new FormLayout(new ColumnSpec[] {
  				ColumnSpec.decode("2mm"),
  				ColumnSpec.decode("default:grow"),
  				ColumnSpec.decode("30mm"),
				ColumnSpec.decode("8mm"),
  				ColumnSpec.decode("2mm"),},
  			new RowSpec[] {
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("2mm"),}));
  		
  		btCompteRendu = new JButton(GestLangue.getInstance().getLocalizedText(IHM.BT_COMPTE_RENDU.getLabel()));
  		btCompteRendu.addActionListener(this);
  		btCompteRendu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  		
  		panel.add(btCompteRendu, "3, 2");
  		panel.add(spTable4,"2,4,3,1");
  		
  		//////////
		pOutil = new JPanel();
		pOutil.setOpaque(false);
		
		pOutil.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				FormFactory.MIN_COLSPEC,},
			new RowSpec[] {
				FormFactory.GLUE_ROWSPEC,}));
		
		JLabel lbletape = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.FIN.getLabel()));
		lbletape.setFont(new Font("Dialog", Font.BOLD, 12));
		pOutil.add(lbletape, "2, 1, right, center");
		
		btEtape4 = new JButton();
		btEtape4.addActionListener(this);
		btEtape4.setIcon(new ImageIcon(Etape2.class.getResource("/image/icones/Sans titre 1.png")));
		btEtape4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pOutil.add(btEtape4, "3, 1, left, center");
		
  		/////////
  		etape4.setLayout(new FormLayout(new ColumnSpec[] {
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
  				RowSpec.decode("10mm"),}));
  		
  		etape4.add(pAffEtape4,"1, 1, fill, fill");
  		etape4.add(pParcourir,"1, 3, 2, 1, fill, fill");
  		etape4.add(panel, "1, 5, 2, 1 fill, fill");
  		etape4.add(pOutil,"1, 7, 2, 1 fill, fill");
  	}
	
	/**
	 * Method getPanel.
	 * @return JPanel
	 */
	public static JPanel getPanel(){
		return (etape4);
	}
	
	public static void miseAjour(){
		TableEtape4.removeJTable();
		btEtape4.setEnabled(false);
		btCompteRendu.setEnabled(false);
	}
	
	public static String getAdresseOGC(){
		return (lblPathOGC.getText());
	}
	
	public static String getAdresseCSV(){
		return (lblPathCSV.getText());
	}
	
	/**
	 * Method actionPerformed.
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btOGC){
			lblPathOGC.setText(new ChargementFichier("Ouvrir").ChargementFich(Livraison.getOGC(), new Filtre_OGCtxt()));
		}
		if (e.getSource() == btCSV){
			lblPathCSV.setText(new ChargementFichier("Ouvrir").ChargementFich("P:\\A320\\UBIK_SA", null));				 
		}
		if (e.getSource() == btVerif){
			new VerificationUbikUbik();
			if(VerificationUbikUbik.isFini()){
				btEtape4.setEnabled(true);
				btCompteRendu.setEnabled(true);
				FenetrePrincipale.compteRendu.setEnabled(true);
			}
			else return;
		}
		if (e.getSource() == btEtape4){ 
			if(XML.getGestLiv(Utilisateur.getNom())){
				 XML.supprLiv(Utilisateur.getNom(), "L" + Livraison.getNom());
			}
			try {
				XML.enregistreFichier();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			new DialNouveau();
		}
		if (e.getSource() == btCompteRendu){ 
			try {
				new CreationRapportEtape4();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
		}
	}
}