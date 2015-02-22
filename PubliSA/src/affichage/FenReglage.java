package affichage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

import sauvergarde_chargement.*;
import tutoriel.TutoPanel;
import tutoriel.Tutoriel;

import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

import org.jdom.Element;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.User;
import myJTree.AffichageTree;
import etape.Etape0;
import XML.XML;

import java.awt.Font;
import java.util.ArrayList;

/**
 * 
 * @author Teddy AUBERT
 *
 * @version $Revision: 1.0 $
 */

@SuppressWarnings("serial")
public class FenReglage extends JDialog implements ItemListener, ActionListener{
	
	//Fichiers externe
	public JButton btParcourirDOC; 
	public JButton btParcourirEXE;
	private JLabel lblPathEXE;
	private JLabel lblPathDOC;
	
	public static JPanel pFileCheck;
	public static JPanel pEYDT;
	
	private static String adresseExe;
	private static String adresseWord;
	/////Mail	
	public static JComboBox<String> comboBox_1;
	public static JPanel pComboBox;
	private static JLabel lblSelectionMail;
	
	private JButton supprAllLiv;
	
	public static JPanel pMailAkkaU;
	public static JTextField tfCorrespondantAkkaU = new JTextField();
	public static JTextField tfObjetAkkaU = new JTextField();
	public static JTextArea taAkkaU = new JTextArea();
	
	private JPanel pMailAkkaT;
	private static JTextField tfCorrespondantAkkaT = new JTextField();
	private static JTextField tfObjetAkkaT = new JTextField();
	private static JTextArea taAkkaT = new JTextArea();
	
	private JPanel pMailSopra;
	private static JTextField tfCorrespondantSopra = new JTextField();
	private static JTextField tfObjetSopra = new JTextField();
	private static JTextArea taSopra = new JTextArea();
	
	private JPanel pMailThales;
	private static JTextField tfCorrespondantThales = new JTextField();
	private static JTextField tfObjetThales = new JTextField();
	private static JTextArea taThales = new JTextArea();
	
	final static String AkkaU = "AkkaU";
    final static String AkkaT = "AkkaT";
	final static String Sopra = "Sopra";
    final static String Thales = "Thales";
    
	/////Gestion Utilisateur
    public static JCheckBox ckbLivraison;
    public static JCheckBox ckbMode;
	
    public static JPanel pGestUti;
    public static JPanel pLiv;
    public static JPanel pUti;
	
    public static JComboBox<String> cbFirstUti;
	
    public JButton btCouleur1;
    public JButton btCouleur2;
    public JButton btCouleur3;
    public Color[] couleur = new Color[3];
    
    public JButton btFond;
    
	//Géneral
	
	public static JPanel cards;
	
	public static JTabbedPane tabbedPane;
	private JPanel pButton;
	public static JButton okButton;
	private JButton cancelButton;
	
	public static TutoPanel pTuto;
	
	public static JPanel pMessage;

	/**
	 * Crée la fenêtre des réglages.
	 * @param page int
	 */
	public FenReglage(int page) {
		
		//Initialisation avec les données de l'utilisateur
		tfCorrespondantAkkaU.setText(User.getMesAkkaUbik().get(0));
		tfObjetAkkaU.setText(User.getMesAkkaUbik().get(1));
		taAkkaU.setText(User.getMesAkkaUbik().get(2));
		
		tfCorrespondantAkkaT.setText(User.getMesAkkaThales().get(0));
		tfObjetAkkaT.setText(User.getMesAkkaThales().get(1));
		taAkkaT.setText(User.getMesAkkaThales().get(2));
		
		tfCorrespondantSopra.setText(User.getMesSopra().get(0));
		tfObjetSopra.setText(User.getMesSopra().get(1));
		taSopra.setText(User.getMesSopra().get(2));
		
		tfCorrespondantThales.setText(User.getMesThales().get(0));
		tfObjetThales.setText(User.getMesThales().get(1));
		taThales.setText(User.getMesThales().get(2));
		
		adresseExe = User.getExe();
		adresseWord = User.getDoc();
		
		//Initialisation de la fenêtre
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FenAPropos.class.getResource("/image/logo/logo_secondaire.png")));
		setResizable(false);
		setTitle(GestLangue.getInstance().getLocalizedText(IHM.REGLAGES.getLabel()));
		setSize(570, 526);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2
		);
		
		getContentPane().setLayout(new BorderLayout());
		
		//Création du panel avec les bouton ok et annuler
		pButton = new JPanel();
		pButton.setBackground(SystemColor.control);
		pButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//getContentPane().add(pButton, BorderLayout.SOUTH);
		
		okButton = new JButton(GestLangue.getInstance().getLocalizedText(IHM.OK.getLabel()));
		okButton.addActionListener(this);
		pButton.add(okButton);
	
		cancelButton = new JButton(GestLangue.getInstance().getLocalizedText(IHM.ANNULER.getLabel()));
		cancelButton.addActionListener(this);
		pButton.add(cancelButton);
		
		/////Tutoriel
		pTuto = new TutoPanel(0,0,0,0);
		pTuto.setVisible(false);
		//this.getContentPane().add(pTuto, BorderLayout.CENTER);
		
		//Création du panel avec les onglets
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		/////////////Onglet EMAIL
		//Création du panel qui contient la comboBox pour le choix du message
		pComboBox = new JPanel();
		pComboBox.setBackground(Color.WHITE);
		tabbedPane.addTab(GestLangue.getInstance().getLocalizedText(IHM.EMAIL.getLabel()), null, pComboBox, null);
		pComboBox.setLayout(null);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(
				new String[] {
						" ",
						GestLangue.getInstance().getLocalizedText(IHM.MAIL_AKKA_U.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.MAIL_AKKA_T.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.MAIL_SOPRA.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.MAIL_THALES.getLabel())}));
		
		comboBox_1.addItemListener(this);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(269, 12, 270, 25);
		pComboBox.add(comboBox_1);
		
		lblSelectionMail = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.SELECT_PARAM_MAIL.getLabel()));
		lblSelectionMail.setBounds(12, 16, 259, 16);
		pComboBox.add(lblSelectionMail);
		
		//Crée les panel de chaque type de message
		akkaU();
		akkaT();
		sopra();
		thales();
		
		//création du panel qui contient les "cards".
        cards = new JPanel(new CardLayout());
        cards.add(pMailAkkaU, AkkaU);
        cards.add(pMailAkkaT, AkkaT);
        cards.add(pMailSopra, Sopra);
        cards.add(pMailThales, Thales);
        
        cards.setVisible(false);
        cards.setBounds(0, 37, 565, 389);
        pComboBox.add(cards);
		
		//////////Onglet Gestion des utilisateurs
        //Création panel de l'onglet
		pGestUti = new JPanel();
		pGestUti.setLayout(null);
		pGestUti.setBorder(new EmptyBorder(5, 5, 5, 5));
		pGestUti.setBackground(Color.WHITE);
		tabbedPane.addTab(GestLangue.getInstance().getLocalizedText(IHM.GEST_UTI.getLabel()), null, pGestUti, null);
		
		//Création panel Gestion des Utilisateurs
		pUti = new JPanel();
		pUti.setLayout(null);
		pUti.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.GEST_UTI.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pUti.setBackground(Color.WHITE);
		pUti.setBounds(12, 12, 539, 134);
		pGestUti.add(pUti);
		
		JLabel lblChoisirLutilisateurPrincipal = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.UTI_UNIQUE.getLabel()));
		lblChoisirLutilisateurPrincipal.setBounds(37, 77, 220, 16);
		pUti.add(lblChoisirLutilisateurPrincipal);
		
		//Initialisation du ComboBox qui liste tout les utilisateurs
		cbFirstUti = new JComboBox<String>();
		cbFirstUti.addItem(XML.getFirstUti());
		for(int i = 0; i<XML.getUTI().size();i++){
			if(!XML.getUTI().get(i).getName().contentEquals(XML.getFirstUti())){
				cbFirstUti.addItem(XML.getUTI().get(i).getName());
			}
		}
		cbFirstUti.setBounds(248, 73, 258, 25);
		pUti.add(cbFirstUti);
		
		ckbMode = new JCheckBox(GestLangue.getInstance().getLocalizedText(IHM.MODE_MULTI_UTI.getLabel()));
		ckbMode.setSelected(XML.getModeUTI());
		ckbMode.addActionListener(this);
		ckbMode.setBackground(Color.WHITE);
		ckbMode.setBounds(37, 39, 199, 24);
		pUti.add(ckbMode);
		
		JLabel lblNewLabel_2 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.MODE_MULTI_UTI_DESACTIV.getLabel()));
		lblNewLabel_2.setBounds(37, 96, 282, 16);
		pUti.add(lblNewLabel_2);
		
		//Création panel Gestion des Livraisons
		pLiv = new JPanel();
		pLiv.setLayout(null);
		pLiv.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.GEST_LIV.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pLiv.setBackground(Color.WHITE);
		pLiv.setBounds(12, 156, 539, 126);
		pGestUti.add(pLiv);
		
		ckbLivraison = new JCheckBox(GestLangue.getInstance().getLocalizedText(IHM.SUPPR_LIV_END.getLabel()));
		ckbLivraison.setSelected(User.getGestLiv());
		ckbLivraison.setBackground(Color.WHITE);
		ckbLivraison.setBounds(37, 32, 295, 24);
		pLiv.add(ckbLivraison);
		
		supprAllLiv = new JButton(GestLangue.getInstance().getLocalizedText(IHM.SUPPRIMER.getLabel()));
		supprAllLiv.setBounds(376, 79, 112, 26);
		supprAllLiv.addActionListener(this);
		pLiv.add(supprAllLiv);
		
		JLabel lblNewLabel = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.SUPPR_ALL_LIV.getLabel()));
		lblNewLabel.setBounds(37, 84, 327, 16);
		pLiv.add(lblNewLabel);
		
		//////////Onglet Fichiers externe
		//Création panel de l'onglet
		JPanel pFichExt = new JPanel();
		pFichExt.setBackground(Color.WHITE);
		tabbedPane.addTab(GestLangue.getInstance().getLocalizedText(IHM.FICH_EXT.getLabel()), null, pFichExt, null);
		pFichExt.setLayout(null);
		
		//Création panel où l'utilisateur détermine le chemin du fichier FileCheck MD5
		pFileCheck = new JPanel();
		pFileCheck.setBackground(Color.WHITE);
		pFileCheck.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.REPERTOIRE_FILECHECK.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pFileCheck.setBounds(12, 140, 541, 116);
		pFichExt.add(pFileCheck);
		pFileCheck.setLayout(null);
		
		btParcourirEXE = new JButton(GestLangue.getInstance().getLocalizedText(IHM.PARCOURIR.getLabel()));
		btParcourirEXE.setBounds(437, 68, 98, 26);
		btParcourirEXE.addActionListener(this);
		pFileCheck.add(btParcourirEXE);
		
		lblPathEXE = new JLabel(adresseExe);
		lblPathEXE.setBounds(25, 68, 409, 26);
		pFileCheck.add(lblPathEXE);
		
		JLabel lblVeuillezIndiquerLe_1 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.PATH_FILECHECK.getLabel()));
		lblVeuillezIndiquerLe_1.setBounds(25, 50, 311, 16);
		pFileCheck.add(lblVeuillezIndiquerLe_1);
		
		JLabel lblPourThalesUn = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.INFO_FILECHECK.getLabel()));
		lblPourThalesUn.setBounds(25, 22, 388, 16);
		pFileCheck.add(lblPourThalesUn);
		
		//Création panel où l'utilisateur détermine le chemin du fichier EYDT
		pEYDT = new JPanel();
		pEYDT.setBackground(Color.WHITE);
		pEYDT.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.REPERTOIRE_EYDT.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pEYDT.setBounds(12, 12, 541, 116);
		pFichExt.add(pEYDT);
		pEYDT.setLayout(null);
		
		btParcourirDOC = new JButton(GestLangue.getInstance().getLocalizedText(IHM.PARCOURIR.getLabel()));
		btParcourirDOC.setBounds(437, 68, 98, 26);
		btParcourirDOC.addActionListener(this);
		pEYDT.add(btParcourirDOC);
		
		lblPathDOC = new JLabel(adresseWord);
		lblPathDOC.setBounds(25, 68, 409, 26);
		pEYDT.add(lblPathDOC);
		
		JLabel lblPourUneLivraison = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.INFO_EYDT.getLabel()));
		lblPourUneLivraison.setBounds(25, 22, 388, 16);
		pEYDT.add(lblPourUneLivraison);
		
		JLabel lblVeuillezIndiquerLe = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.PATH_EYDT.getLabel()));
		lblVeuillezIndiquerLe.setBounds(25, 50, 311, 16);
		pEYDT.add(lblVeuillezIndiquerLe);		
		
		//////////Onglet Personnalisation
		//Création panel de l'onglet
		JPanel pPerso = new JPanel();
		pPerso.setBackground(Color.WHITE);
		tabbedPane.addTab(GestLangue.getInstance().getLocalizedText(IHM.PERSO.getLabel()), null, pPerso, null);
		pPerso.setLayout(null);
		
		//Création panel où l'utilisateur détermine le chemin du fichier FileCheck MD5
		JPanel pCouleur = new JPanel();
		pCouleur.setBackground(Color.WHITE);
		pCouleur.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.CHOIX_COULEUR.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pCouleur.setBounds(12, 12, 541, 158);
		pPerso.add(pCouleur);
		pCouleur.setLayout(null);
		
		btCouleur1 = new JButton("Palette");
		btCouleur1.setBounds(390, 20, 135, 26);
		btCouleur1.addActionListener(this);
		pCouleur.add(btCouleur1);
		
		JLabel lblCouleur1 = new JLabel("Couleur Principal");
		JLabel lblC1 = new JLabel("");
		lblC1.setOpaque(true);
		lblC1.setBackground(Etape0.couleur[0]);
		lblC1.setBounds(360, 25, 15, 15);
		lblCouleur1.setBounds(25, 20, 200, 26);
		pCouleur.add(lblCouleur1);
		pCouleur.add(lblC1);
		
		btCouleur2 = new JButton("Palette");
		btCouleur2.setBounds(390, 66, 135, 26);
		btCouleur2.addActionListener(this);
		pCouleur.add(btCouleur2);
		
		JLabel lblCouleur2 = new JLabel("Couleur Secondaire");
		JLabel lblC2 = new JLabel("");
		lblC2.setOpaque(true);
		lblC2.setBackground(Etape0.couleur[1]);
		lblC2.setBounds(360, 71, 15, 15);
		lblCouleur2.setBounds(25, 66, 200, 26);
		pCouleur.add(lblCouleur2);
		pCouleur.add(lblC2);
		
		btCouleur3 = new JButton("Palette");
		btCouleur3.setBounds(390, 112, 135, 26);
		btCouleur3.addActionListener(this);
		pCouleur.add(btCouleur3);
		
		JLabel lblCouleur3 = new JLabel("Couleur des caractères");
		lblCouleur3.setBounds(25, 112, 200, 26);
		pCouleur.add(lblCouleur3);
		
		////
		JPanel pImage = new JPanel();
		pImage.setBackground(Color.WHITE);
		pImage.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.CHOIX_FOND.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pImage.setBounds(12, 182, 541, 60);
		pPerso.add(pImage);
		pImage.setLayout(null);
		
		btFond = new JButton(GestLangue.getInstance().getLocalizedText(IHM.PARCOURIR.getLabel()));
		btFond.setBounds(390, 20, 135, 26);
		btFond.addActionListener(this);
		pImage.add(btFond);
		
		JLabel lblFond = new JLabel("Choisir une image à placer en fond");
		lblFond.setBounds(25, 20, 200, 26);
		pImage.add(lblFond);
		
		tabbedPane.setSelectedIndex(page);
		
		//Mise en forme du panel principal
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
	        		ColumnSpec.decode("default:grow")},
	  			new RowSpec[] {
	        		RowSpec.decode("default:grow"),
	        		FormFactory.DEFAULT_ROWSPEC}));
        
		getContentPane().add(pTuto,"1, 1, fill, fill");
		getContentPane().add(tabbedPane, "1, 1, fill, fill");
		getContentPane().add(pButton, "1, 2, fill, fill");
        
		if(FenetrePrincipale.boolTuto){
			Tutoriel.next(7);
		}
		
		setVisible(true);
	}
	
	/**
	 * Crée le panel qui contient les informations sur le mail Akka dans le cas d'une livraison Ubik.
	 */
	private void akkaU(){
		pMailAkkaU = new JPanel();
		pMailAkkaU.setBackground(Color.WHITE);
		pMailAkkaU.setLayout(null);
		
		JPanel pCorrespondant = new JPanel();
		pCorrespondant.setLayout(null);
		pCorrespondant.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.CORRESPONDANT.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pCorrespondant.setBackground(Color.WHITE);
		pCorrespondant.setBounds(12, 0, 541, 51);
		pMailAkkaU.add(pCorrespondant);
		
		tfCorrespondantAkkaU.setColumns(10);
		tfCorrespondantAkkaU.setBounds(12, 17, 517, 28);
		tfCorrespondantAkkaU.addActionListener(this);
		pCorrespondant.add(tfCorrespondantAkkaU);
		
		JPanel pObjet = new JPanel();
		pObjet.setLayout(null);
		pObjet.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.OBJET.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pObjet.setBackground(Color.WHITE);
		pObjet.setBounds(12, 54, 541, 51);
		pMailAkkaU.add(pObjet);
		
		tfObjetAkkaU.setColumns(10);
		tfObjetAkkaU.setBounds(12, 17, 517, 28);
		pObjet.add(tfObjetAkkaU);
		
		pMessage = new JPanel();
		pMessage.setLayout(null);
		pMessage.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.MESSAGE.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pMessage.setBackground(Color.WHITE);
		pMessage.setBounds(12, 104, 541, 270);
		pMailAkkaU.add(pMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 517, 168);
		pMessage.add(scrollPane);
			
		scrollPane.setViewportView(taAkkaU);
		taAkkaU.setLineWrap(true);
		
		JEditorPane affMarqueurs = new JEditorPane();
		affMarqueurs.setForeground(UIManager.getColor("textHighlight"));
		affMarqueurs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		affMarqueurs.setText(GestLangue.getInstance().getLocalizedText(IHM.MARQUEURS.getLabel()));
		affMarqueurs.setBounds(12, 17, 523, 72);
		pMessage.add(affMarqueurs);
	}
	
	/**
	 * Crée le panel qui contient les informations sur le mail Akka dans le cas d'une livraison Thalès.
	 */
	private void akkaT(){
		pMailAkkaT = new JPanel();
		pMailAkkaT.setBackground(Color.WHITE);
		pMailAkkaT.setLayout(null);
		
		JPanel pCorrespondant = new JPanel();
		pCorrespondant.setLayout(null);
		pCorrespondant.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.CORRESPONDANT.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pCorrespondant.setBackground(Color.WHITE);
		pCorrespondant.setBounds(12, 0, 541, 51);
		pMailAkkaT.add(pCorrespondant);
		
		tfCorrespondantAkkaT.setColumns(10);
		tfCorrespondantAkkaT.setBounds(12, 17, 517, 26);
		tfCorrespondantAkkaT.addActionListener(this);
		pCorrespondant.add(tfCorrespondantAkkaT);
		
		JPanel pObjet = new JPanel();
		pObjet.setLayout(null);
		pObjet.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.OBJET.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pObjet.setBackground(Color.WHITE);
		pObjet.setBounds(12, 54, 541, 51);
		pMailAkkaT.add(pObjet);
		
		tfObjetAkkaT.setColumns(10);
		tfObjetAkkaT.setBounds(12, 17, 517, 26);
		pObjet.add(tfObjetAkkaT);
		
		pMessage = new JPanel();
		pMessage.setLayout(null);
		pMessage.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.MESSAGE.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pMessage.setBackground(Color.WHITE);
		pMessage.setBounds(12, 104, 541, 270);
		pMailAkkaT.add(pMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 517, 168);
		pMessage.add(scrollPane);
			
		scrollPane.setViewportView(taAkkaT);
		taAkkaT.setLineWrap(true);
		
		JEditorPane affMarqueurs = new JEditorPane();
		affMarqueurs.setForeground(UIManager.getColor("textHighlight"));
		affMarqueurs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		affMarqueurs.setText(GestLangue.getInstance().getLocalizedText(IHM.MARQUEURS.getLabel()));
		affMarqueurs.setBounds(12, 17, 523, 72);
		pMessage.add(affMarqueurs);
	}
	
	/**
	 * Crée le panel qui contient les informations sur le mail Sopra dans le cas d'une livraison Ubik.
	 */
	private void sopra(){
		pMailSopra = new JPanel();
		pMailSopra.setBackground(Color.WHITE);
		pMailSopra.setLayout(null);
		
		JPanel pCorrespondant = new JPanel();
		pCorrespondant.setLayout(null);
		pCorrespondant.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.CORRESPONDANT.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pCorrespondant.setBackground(Color.WHITE);
		pCorrespondant.setBounds(12, 0, 541, 51);
		pMailSopra.add(pCorrespondant);
		
		tfCorrespondantSopra.setColumns(10);
		tfCorrespondantSopra.setBounds(12, 17, 517, 26);
		tfCorrespondantSopra.addActionListener(this);
		pCorrespondant.add(tfCorrespondantSopra);
		
		JPanel pObjet = new JPanel();
		pObjet.setLayout(null);
		pObjet.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.OBJET.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pObjet.setBackground(Color.WHITE);
		pObjet.setBounds(12, 54, 541, 51);
		pMailSopra.add(pObjet);
		
		tfObjetSopra.setColumns(10);
		tfObjetSopra.setBounds(12, 17, 517, 26);
		pObjet.add(tfObjetSopra);
		
		pMessage = new JPanel();
		pMessage.setLayout(null);
		pMessage.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.MESSAGE.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pMessage.setBackground(Color.WHITE);
		pMessage.setBounds(12, 104, 541, 270);
		pMailSopra.add(pMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 517, 168);
		pMessage.add(scrollPane);
			
		scrollPane.setViewportView(taSopra);
		taSopra.setLineWrap(true);
		
		JEditorPane affMarqueurs = new JEditorPane();
		affMarqueurs.setForeground(UIManager.getColor("textHighlight"));
		affMarqueurs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		affMarqueurs.setText(GestLangue.getInstance().getLocalizedText(IHM.MARQUEURS.getLabel()));
		affMarqueurs.setBounds(12, 17, 523, 72);
		pMessage.add(affMarqueurs);
	}
	
	/**
	 * Crée le panel qui contient les informations sur le mail Thalès dans le cas d'une livraison Thalès.
	 */
	private void thales(){
		pMailThales = new JPanel();
		pMailThales.setBackground(Color.WHITE);
		pMailThales.setLayout(null);
		
		JPanel pCorrespondant = new JPanel();
		pCorrespondant.setLayout(null);
		pCorrespondant.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.CORRESPONDANT.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pCorrespondant.setBackground(Color.WHITE);
		pCorrespondant.setBounds(12, 0, 541, 51);
		pMailThales.add(pCorrespondant);
		
		tfCorrespondantThales.setColumns(10);
		tfCorrespondantThales.setBounds(12, 17, 517, 26);
		tfCorrespondantThales.addActionListener(this);
		pCorrespondant.add(tfCorrespondantThales);
		
		JPanel pObjet = new JPanel();
		pObjet.setLayout(null);
		pObjet.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.OBJET.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pObjet.setBackground(Color.WHITE);
		pObjet.setBounds(12, 54, 541, 51);
		pMailThales.add(pObjet);
		
		tfObjetThales.setColumns(10);
		tfObjetThales.setBounds(12, 17, 517, 26);
		pObjet.add(tfObjetThales);
		
		pMessage = new JPanel();
		pMessage.setLayout(null);
		pMessage.setBorder(new TitledBorder(new LineBorder(Etape0.couleur[0]), GestLangue.getInstance().getLocalizedText(IHM.MESSAGE.getLabel()), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pMessage.setBackground(Color.WHITE);
		pMessage.setBounds(12, 104, 541, 270);
		pMailThales.add(pMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 517, 168);
		pMessage.add(scrollPane);
			
		scrollPane.setViewportView(taThales);
		taThales.setLineWrap(true);
		
		JEditorPane affMarqueurs = new JEditorPane();
		affMarqueurs.setForeground(UIManager.getColor("textHighlight"));
		affMarqueurs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		affMarqueurs.setText(GestLangue.getInstance().getLocalizedText(IHM.MARQUEURS.getLabel()));
		affMarqueurs.setBounds(12, 17, 523, 72);
		pMessage.add(affMarqueurs);
	}

	/**
	 * S'effectue lors du changement d'état des comboBox
	 * @param e ItemEvent
	 * @see java.awt.event.ItemListener#itemStateChanged(ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == comboBox_1){
			if(e.getItem() == GestLangue.getInstance().getLocalizedText(IHM.MAIL_AKKA_U.getLabel())){
				cards.setVisible(true);
				CardLayout cl = (CardLayout) cards.getLayout();
		        cl.show(cards, AkkaU);
			}
			if(e.getItem() == GestLangue.getInstance().getLocalizedText(IHM.MAIL_AKKA_T.getLabel())){
				cards.setVisible(true);
				CardLayout cl = (CardLayout) cards.getLayout();
		        cl.show(cards, AkkaT);	
			}
			if(e.getItem() == GestLangue.getInstance().getLocalizedText(IHM.MAIL_SOPRA.getLabel())){
				cards.setVisible(true);
				CardLayout cl = (CardLayout) cards.getLayout();
		        cl.show(cards, Sopra);
			}
			if(e.getItem() == GestLangue.getInstance().getLocalizedText(IHM.MAIL_THALES.getLabel())){
				cards.setVisible(true);
				CardLayout cl = (CardLayout) cards.getLayout();
		        cl.show(cards, Thales);
			}
			if(e.getItem() == " "){
				cards.setVisible(false);
			}
			if(FenetrePrincipale.boolTuto){
				Tutoriel.next(7);
			}
		}
	}

	/**
	 * Définie les différentes actions lancées par les boutons
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btParcourirEXE){
			try{
				adresseExe = new ChargementFichier("Adresse").ChargementFich(adresseExe, new Filtre_EXE());
				if(!adresseExe.equals(null)){lblPathEXE.setText(adresseExe);}
			}catch(Exception e1){}
		}
		if (e.getSource() == btParcourirDOC){
			try{
				adresseWord = new ChargementFichier("Adresse").ChargementFich(adresseWord, new Filtre_DOC());
				if(!adresseWord.equals(null)){lblPathDOC.setText(adresseWord);}
			}catch(Exception e1){}
		}
		
		if (e.getSource() == supprAllLiv){
			int option = JOptionPane.showConfirmDialog(null, 
					GestLangue.getInstance().getLocalizedText(IHM.MES_SUPPR_ALL_LIV.getLabel()),
					GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			
			if(option != JOptionPane.NO_OPTION &&
					option != JOptionPane.CANCEL_OPTION &&
					option != JOptionPane.CLOSED_OPTION){
				
				ArrayList<Element> livraison = XML.getLivraison(User.getNom());
				for(int i=0; i<livraison.size(); i++){
					XML.supprLiv(User.getNom(), livraison.get(i).getName());
				}
				
				CardLayout c2 = (CardLayout) FenetrePrincipale.getCards().getLayout();
				c2.show(FenetrePrincipale.getCards(), FenetrePrincipale.ETAPE0);
								
				Etape0.miseAjour();
				
				FenetrePrincipale.lblNom.setText("");				
				this.hide();
			}
		}
		
		if (e.getSource() == okButton){
			XML.setDOC(User.getNom(), lblPathDOC.getText());
			XML.setEXE(User.getNom(), lblPathEXE.getText());
			XML.setGestLiv(User.getNom(), ckbLivraison.isSelected());
			XML.setModeUTI(ckbMode.isSelected());
			XML.setFirtUti(cbFirstUti.getSelectedItem().toString());
			
			//Enregistrement données mail
			XML.setMesAkkaUbik(User.getNom(),
					tfCorrespondantAkkaU.getText(),
					tfObjetAkkaU.getText(), 
					taAkkaU.getText());
			
			XML.setMesAkkaThales(User.getNom(),
					tfCorrespondantAkkaT.getText(),
					tfObjetAkkaT.getText(), 
					taAkkaT.getText());
			
			XML.setMesSopra(User.getNom(),
					tfCorrespondantSopra.getText(),
					tfObjetSopra.getText(), 
					taSopra.getText());
			
			XML.setMesThales(User.getNom(),
					tfCorrespondantThales.getText(),
					tfObjetThales.getText(), 
					taThales.getText());
			
            XML.setColor(couleur);
			
			try {
				XML.enregistreFichier();
				User.refresh();
				Delivery.refresh();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(ckbMode.isSelected()){
				FenetrePrincipale.lblImageUti.setIcon(new ImageIcon(FenetrePrincipale
						.class.getResource("/image/icones/1359326968_user_manage.png")));
			}
			else{
				FenetrePrincipale.lblImageUti.setIcon(new ImageIcon(FenetrePrincipale
						.class.getResource("/image/icones/1359325630_user.png")));
			}
			AffichageTree.miseAjour();
			
			if(FenetrePrincipale.boolTuto){
				Tutoriel.next(17);
			}
			
			this.hide();
		}
		
		if (e.getSource() == cancelButton){
			this.hide();
		}
		
		if(e.getSource() == ckbMode){
			if(ckbMode.isSelected()){
				cbFirstUti.setEnabled(false);
			}
			else{
				cbFirstUti.setEnabled(true);
			}
		}
		
		if(e.getSource() == btCouleur1){
			couleur[0] = JColorChooser.showDialog(null,"Choix de la couleur", null);
		}
		if(e.getSource() == btCouleur2){
			couleur[1] = JColorChooser.showDialog(null,"Choix de la couleur", null);
		}
		if(e.getSource() == btCouleur3){
			couleur[2] = JColorChooser.showDialog(null,"Choix de la couleur", null);
		}
		
		if(e.getSource() == btFond){
			
		}
	}
}