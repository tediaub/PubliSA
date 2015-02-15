package affichage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import jxl.write.WriteException;
import outils.Lanceur;
import outils.SupprEntete;
import tutoriel.TutoPanel;
import tutoriel.Tutoriel;
import utilisateur.Livraison;
import utilisateur.Utilisateur;
import verification.CreationRapportEtape2;
import verification.CreationRapportEtape4;
import langue.GestLangue;
import langue.IHM;
import myJTree.AffichageTree;
import XML.XML;
import autre.Langue;

import com.jgoodies.forms.layout.*;

import etape.*;

/**
 * 
 * @author Teddy AUBERT
 *
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame implements ActionListener, WindowListener{
	
	public static JPanel cards;
	public static JPanel cards2;
	
	public static Color couleur;
	
	public static FenetrePrincipale instance = new FenetrePrincipale();
	
	public final static String ETAPE0 = "Etape 0";
	public final static String OTHERS = "Others";
	public final static String ETAPE1 = "Etape 1";
	public final static String ETAPE2 = "Etape 2";
	public final static String ETAPE3 = "Etape 3";
	public final static String ETAPE4 = "Etape 4";
    
    //Barre de Menu
	private Component horizontalStrut_2;
	
	private JMenu fichier;
	public static JMenuItem nouveau;
	public static JMenuItem enregistrer;
	private JSeparator separator;
	private JMenuItem quitter;
	
	public static JMenu reglage;
	private JMenuItem regEmail;
	private JSeparator separator_1;
	private JMenuItem gestUti;
	private JMenuItem fichExt;
	private JMenuItem perso;
	
	public static JMenu outils;
	public static JMenuItem launchFichDOC;
	public static JMenuItem launchFichEXE;
	private JSeparator separator_3;
	public static JMenuItem compteRendu;
	private JSeparator separator_4;
	public static JMenuItem supprEntete;
	
	private JMenu aide;
	public static JMenuItem tutoriel;
	public static JMenu langue;
	private JCheckBoxMenuItem langueFR;
	private JCheckBoxMenuItem langueEN;
	private final ButtonGroup buttonGroup_langue= new ButtonGroup();
	private JSeparator separator_2;
	private JMenuItem aPropos;
	
	//Affichage secondaire barre de menu
	private JPanel pMenu;
	public static JLabel lblNom;
	private Component horizontalStrut;
	public static JLabel lblImageUti;
	private Component horizontalStrut_1;

	//Affichage JTree
	public static JTree tree;
	public static JScrollPane scrollPane;
	
	//Affichage etape 0
	private JPanel etape0;
	
	//Affichage etape 1
	private JPanel etape1;

	//Affichage etape 2
	private JPanel etape2;
	
	//Affichage etape 3
	private JPanel etape3;
	
	//Affichage etape 4
	private JPanel etape4;

	public static TutoPanel pTuto;
	public static boolean boolTuto = false;
	
	private JPanel panel;
	private JButton btnNewButton;
	private JLabel lblTutoriel;
	
	/**
	 * Création de la fenetre d'acceuil de PubliSA
	 */
	
	public FenetrePrincipale() {
		
		langueFR = new JCheckBoxMenuItem(GestLangue.getInstance().getLocalizedText(IHM.L_FR.getLabel()));
		langueEN = new JCheckBoxMenuItem(GestLangue.getInstance().getLocalizedText(IHM.L_EN.getLabel()));
		
		if(XML.getLangue().contentEquals(Langue.FRANCAIS.toString())){
			GestLangue.getInstance().setLangue(Langue.FRANCAIS);
			langueFR.setState(true);
		}
		else{
			GestLangue.getInstance().setLangue(Langue.ANGLAIS);
			langueEN.setState(true);
		}		
		
		//Création de la fenetre
		this.setTitle(GestLangue.getInstance().getLocalizedText(IHM.ENTETE_FENETRE.getLabel()));
		addWindowListener(this);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FenAPropos.class.getResource("/image/logo/logo_secondaire.png")));
		getContentPane().setBackground(Color.WHITE);
		setSize(910, 700);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(
				(screenSize.width-getWidth())/2,
				(screenSize.height-getHeight())/2
		);
		
		//////Affichage principale
		//Création des objets du menu		
		horizontalStrut_2 = Box.createHorizontalStrut(5);
		
		fichier = new JMenu(GestLangue.getInstance().getLocalizedText(IHM.FICHIER.getLabel()));
		fichier.setFont(new Font("SansSerif", Font.BOLD, 12));
		nouveau = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.NOUVEAU.getLabel()));
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		enregistrer = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.ENREGISTRER.getLabel()));
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		separator = new JSeparator();
		quitter = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.QUITTER.getLabel()));

		reglage = new JMenu(GestLangue.getInstance().getLocalizedText(IHM.REGLAGES.getLabel()));
		reglage.setFont(new Font("SansSerif", Font.BOLD, 12));
		regEmail = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.REG_EMAIL.getLabel()));
		separator_1 = new JSeparator();
		gestUti = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.GEST_UTI.getLabel()));
		fichExt = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.FICH_EXT.getLabel()));
		perso = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.PERSO.getLabel()));

		outils = new JMenu(GestLangue.getInstance().getLocalizedText(IHM.OUTILS.getLabel()));
		outils.setFont(new Font("SansSerif", Font.BOLD, 12));
		launchFichDOC = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.EYDT.getLabel()));
		launchFichEXE= new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.EXE.getLabel()));
		separator_3 = new JSeparator();
		compteRendu = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.COMPTE_RENDU.getLabel()));
		separator_4 = new JSeparator();
		supprEntete = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.SUPPR_ENTETE.getLabel()));
		
		aide = new JMenu(GestLangue.getInstance().getLocalizedText(IHM.AIDE.getLabel()));
		aide.setFont(new Font("SansSerif", Font.BOLD, 12));
		tutoriel = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.TUTORIEL.getLabel()));
		langue = new JMenu(GestLangue.getInstance().getLocalizedText(IHM.LANGUE.getLabel()));
		separator_2 = new JSeparator();
		aPropos = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.ABOUT.getLabel()));
				
		//Ajout des objets au menu
		
		
		fichier.add(nouveau);		
		fichier.add(enregistrer);		
		fichier.add(separator);		
		fichier.add(quitter);
		
		reglage.add(regEmail);		
		reglage.add(separator_1);
		reglage.add(gestUti);
		reglage.add(fichExt);
		reglage.add(perso);
		
		outils.add(compteRendu);		
		outils.add(separator_3);
		outils.add(supprEntete);
		outils.add(separator_4);
		outils.add(launchFichDOC);
		outils.add(launchFichEXE);
		
		aide.add(tutoriel);
		aide.add(langue);
		langue.add(langueFR);
		langue.add(langueEN);	
		aide.add(separator_2);
		aide.add(aPropos);
		
		buttonGroup_langue.add(langueFR);
		buttonGroup_langue.add(langueEN);
		
		//Enable des menus		
		nouveau.setEnabled(false);		
		enregistrer.setEnabled(false);		
		//langue.setEnabled(false);
		tutoriel.setEnabled(false);
		reglage.setEnabled(false);
		outils.setEnabled(false);
		
		//tutoriel.setEnabled(false);
		
		//Ajout des icones
		regEmail.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359687791_email.png")));
		gestUti.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359688266_user_warning.png")));
		perso.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1389719551_Color_palette.png")));
		
		tutoriel.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359684753_Tutorial.png")));
		langue.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359684181_Network.png")));
		langueFR.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359682206_Flag_of_France.png")));
		langueEN.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359684936_Flag_of_United_States.png")));
		aPropos.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/info.png")));

		//Actions du menu
		nouveau.addActionListener(this);
		enregistrer.addActionListener(this);
		quitter.addActionListener(this);
		
		regEmail.addActionListener(this);		
		gestUti.addActionListener(this);
		fichExt.addActionListener(this);
		perso.addActionListener(this);
		
		compteRendu.addActionListener(this);		
		supprEntete.addActionListener(this);
		launchFichDOC.addActionListener(this);
		launchFichEXE.addActionListener(this);
		
		tutoriel.addActionListener(this);
		langueFR.addActionListener(this);
		langueEN.addActionListener(this);
		aPropos.addActionListener(this);
		
		//Tutoriel
		panel = new JPanel();
		panel.setOpaque(false);
		
		lblTutoriel = new JLabel("Tutoriel : ");
		lblTutoriel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTutoriel.setForeground(new Color(0, 0, 0));
		panel.add(lblTutoriel);
		
		btnNewButton = new JButton();
		btnNewButton.setPreferredSize(new Dimension(14, 14));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/com/sun/java/swing/plaf/motif/icons/Error.gif")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton.setOpaque(false);
		panel.add(btnNewButton);
		panel.setVisible(false);
		
		//Affichage secondaire de la barre menu
		pMenu = new JPanel();
		pMenu.setOpaque(false);
		
		FlowLayout flowLayout = (FlowLayout) pMenu.getLayout();
		flowLayout.setVgap(3);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		pMenu.setBackground(SystemColor.control);
		
		lblNom = new JLabel();
		lblNom.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNom.setBackground(SystemColor.control);
		pMenu.add(lblNom);
		
		horizontalStrut = Box.createHorizontalStrut(15);
		pMenu.add(horizontalStrut);
		
		lblImageUti = new JLabel();
		if(XML.getModeUTI()){
			lblImageUti.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359326968_user_manage.png")));
		}
		else{
			lblImageUti.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/image/icones/1359325630_user.png")));
		}
		pMenu.add(lblImageUti);
		
		horizontalStrut_1 = Box.createHorizontalStrut(8);
		pMenu.add(horizontalStrut_1);
        
		tree = AffichageTree.getInstance();
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new LineBorder((XML.getColor())[0], 1, true));
		scrollPane.setViewportView(tree);
		
		//Etape0
		new Etape0();
		etape0 = Etape0.getPanel();
		
		//Etape1
		new Etape1();
		etape1 = Etape1.getPanel();
		
		//Etape2
		new Etape2();
		etape2 = Etape2.getPanel();
		
		//Etape3
		new Etape3();
		etape3 = Etape3.getPanel();
		
		//Etape4
		new Etape4();
		etape4 = Etape4.getPanel();
		
		//création du panel qui contient les les etapes 1 à 4 et le Tree 
		JPanel others = new JPanel();
		others.setOpaque(false);
		
        cards2 = new JPanel(new CardLayout());
        cards2.setOpaque(false);
        cards2.add(etape1, ETAPE1);
        cards2.add(etape2, ETAPE2);
        cards2.add(etape3, ETAPE3);
        cards2.add(etape4, ETAPE4);
		
		//Mise en forme du panel others
		others.setLayout(new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("2mm"),
        		ColumnSpec.decode("default:grow"),
  				ColumnSpec.decode("4.5cm"),
  				ColumnSpec.decode("2mm"),},
  			new RowSpec[] {
        		RowSpec.decode("2mm"),
  				RowSpec.decode("default:grow"),
  				RowSpec.decode("0.5mm"),
  				RowSpec.decode("1.5mm"),}));
		
		others.add(cards2, "2, 2, 1, 2, fill, fill");
		others.add(scrollPane, "3, 2, fill, fill");
		
        //création du panel qui contient les l'etape0 et le panel des etapes 1 à 4
        cards = new JPanel(new CardLayout());
        cards.setOpaque(false);
        cards.add(etape0, ETAPE0);
        cards.add(others, OTHERS);
        
        
        //Mise en forme du panel principal
        this.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
				
  			new RowSpec[] {
  				RowSpec.decode("default:grow")}));
        
        //Ajout des panel "cards" et "tree" au panel principal
        pTuto = new TutoPanel(0,0,0,0);
        pTuto.setVisible(false);
        
        this.getContentPane().add(pTuto,"1, 1, fill, fill");
        this.getContentPane().add(cards, "1, 1, fill, fill");
		
		setVisible(true);
	}
	
	public static FenetrePrincipale getInstance(){
		return instance;
	}
	
	/**
	 * Récupère le panel qui contient les différentes étapes
	 * @return JPanel
	 */
	public static JPanel getCards(){
		return cards;
	}
	
	public static JPanel getCardEtape(){
		return cards2;
	}
	
	/**
	 * Définie les différentes actions lancées par les menus.
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		//Menu "Nouveau"
		if (e.getSource() == nouveau){
			new DialNouveau();
		}
		
		//Menu "Enregistrer"
		if (e.getSource() == enregistrer){
			try {
				XML.enregistreFichier();
				Utilisateur.refresh();
				Livraison.refresh();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		//Menu "Quitter"
		if (e.getSource() == quitter){
			int option = JOptionPane.showConfirmDialog(null, 
					GestLangue.getInstance().getLocalizedText(IHM.MES_QUITTER.getLabel()), 
					GestLangue.getInstance().getLocalizedText(IHM.QUITTER.getLabel()), 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		
		//Menu "Réglages Email"
		if (e.getSource() == regEmail){
			new FenReglage(0);
		} 
		
		//Menu "Gestion utilisateurs"
		if (e.getSource() == gestUti){
			new FenReglage(1);
		}
		
		//Menu "Fichiers externe"
		if (e.getSource() == fichExt){
			new FenReglage(2);
		}
		
		//Menu "Personnalisation"
		if (e.getSource() == perso){
			new FenReglage(3);
		}
		
		//Menu "Créer Compte rendu"
		if (e.getSource() == compteRendu){
			if(Livraison.getEtape() == 2){
				try {
					new CreationRapportEtape2();
				} catch (WriteException e1) {
					e1.printStackTrace();
				}
			}
			else if(Livraison.getEtape() == 4){
				try {
					new CreationRapportEtape4();
				} catch (WriteException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		//Menu "Supprimer les entêtes"
		if (e.getSource() == supprEntete){
			new SupprEntete(Etape2.adresseOGC);
		}
		
		//Menu "Formulaire EYDT"
		if (e.getSource() == launchFichDOC){
			new Lanceur(Utilisateur.getDoc());
		}
		
		//Menu "EXE FileCheck"
		if (e.getSource() == launchFichEXE){
			new Lanceur(Utilisateur.getExe());
		}
		
		//Menu "Tutoriel"
		if (e.getSource() == tutoriel){
			new Tutoriel();
		}
		
		if (e.getSource()==(langueFR)) {
			if (!GestLangue.getInstance().getCurrentLanguage().equals(Langue.FRANCAIS)) {
				XML.setLangue(Langue.FRANCAIS.toString());
				langueFR.setState(true);
				GestLangue.getInstance().setLangue(Langue.FRANCAIS);
				int option = JOptionPane.showConfirmDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.QUITTER_LANGUE.getLabel()), 
						GestLangue.getInstance().getLocalizedText(IHM.QUITTER.getLabel()), 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(option == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		} else if (e.getSource().equals(langueEN)) {
			if (!GestLangue.getInstance().getCurrentLanguage().equals(Langue.ANGLAIS)) {
				XML.setLangue(Langue.ANGLAIS.toString());
				langueEN.setState(true);
				GestLangue.getInstance().setLangue(Langue.ANGLAIS);
				int option = JOptionPane.showConfirmDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.QUITTER_LANGUE.getLabel()), 
						GestLangue.getInstance().getLocalizedText(IHM.QUITTER.getLabel()), 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(option == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		}
		
		//Menu "A propos de PubliSA"
		if (e.getSource() == aPropos){
			new FenAPropos();
		}
	}
	
	public void changementLangue() {
		
	}
	
	/**
	 * Se lance lors de la fermeture de la fenêtre. Demande à l'utilisateur si il veut vraiment quitter.
	 */
	public void windowClosing(WindowEvent arg0) {
	
		int option = JOptionPane.showConfirmDialog(null, 
				GestLangue.getInstance().getLocalizedText(IHM.MES_QUITTER.getLabel()), 
				GestLangue.getInstance().getLocalizedText(IHM.QUITTER.getLabel()), 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}

	/**
	 * Méthode d'interface. N'est pas pris en compte dans PubliSA
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {	
	}

	/**
	 * Méthode d'interface. N'est pas pris en compte dans PubliSA
	 */
	@Override
	public void windowClosed(WindowEvent arg0) {	
	}

	/**
	 * Méthode d'interface. N'est pas pris en compte dans PubliSA
	 */
	@Override
	public void windowDeactivated(WindowEvent arg0){
	}

	/**
	 * Méthode d'interface. N'est pas pris en compte dans PubliSA
	 */
	@Override
	public void windowDeiconified(WindowEvent arg0){	
	}

	/**
	 * Méthode d'interface. N'est pas pris en compte dans PubliSA
	 */
	@Override
	public void windowIconified(WindowEvent arg0){	
	}

	/**
	 * Méthode d'interface. N'est pas pris en compte dans PubliSA
	 */
	@Override
	public void windowOpened(WindowEvent arg0){
	}
}