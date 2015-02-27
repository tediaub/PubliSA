package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import langue.GestLangue;
import langue.IHM;
import autre.ImagePanel;

/**
 * 
 * @author Teddy AUBERT
 *
 * @version $Revision: 1.0 $
 */

@SuppressWarnings("serial")
public class FenAPropos extends JFrame {

	private int largeur = 550;
	private int hauteur = 463;

	/**
	 * Création de la fenetre "A propos"
	 */
	public FenAPropos() {
		
		//Initialisation de la fenêtre
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FenAPropos.class.getResource("/image/logo/logo_secondaire.png")));
		this.setTitle(GestLangue.getInstance().getLocalizedText(IHM.ABOUT.getLabel()));
		this.setSize(largeur, hauteur);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
		this.setLocation(
				(screenSize.width-getWidth())/2,
				(screenSize.height-getHeight())/2
		);
	
		JLabel titre1 = new JLabel("AEROCONSEIL", JLabel.CENTER);
		titre1.setFont(titre1.getFont().deriveFont(Font.BOLD)); 
	
		JLabel titre2 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.VERSION.getLabel()), JLabel.CENTER);
		titre2.setFont(titre2.getFont().deriveFont(Font.BOLD)); 
	
		JLabel titre4 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.MISE_JOUR.getLabel()), JLabel.CENTER);
		
		JLabel titre5 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.CONTACT.getLabel()), JLabel.CENTER);
	
		JPanel ligne2 = new JPanel();
		ligne2.setBackground(Color.DARK_GRAY);
	
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(FenAPropos.class.getResource("/image/logo/logo2.png")));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		/////Création du panel
		ImagePanel panel = new ImagePanel(new ImageIcon(FenAPropos.class.getResource("/image/fonds/Sans titre 1.png")).getImage());
		tabbedPane.addTab(GestLangue.getInstance().getLocalizedText(IHM.DETAIL.getLabel()), null, panel, null);
		
		JPanel ligne1 = new JPanel();
		ligne1.setBackground(Color.DARK_GRAY);
		this.addComponent(panel, ligne1, 40, 190, largeur/2, 2);
		this.addComponent(panel, titre1, -100, 200, largeur, 30);
		this.addComponent(panel, titre2, -100, 230, largeur, 30);
		this.addComponent(panel, titre4, -100, 260, largeur, 30);
		this.addComponent(panel, titre5, -100, 290, largeur, 30);
		this.addComponent(panel, ligne2, 40, 330, largeur/2, 2);
		this.addComponent(panel, lblLogo, 325, 80, 150, 52);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		tabbedPane.addTab(GestLangue.getInstance().getLocalizedText(IHM.SUIVI_VERS.getLabel()), null, panel2, null);
		panel2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(UIManager.getColor("nimbusBase")));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 6, 532, 393);
		panel2.add(scrollPane);
		
		JEditorPane textPane = new JEditorPane();
		scrollPane.setViewportView(textPane);
		textPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textPane.setBorder(null);
		
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		textPane.setText(
			"<html>" + 
				"<style type=\"text/css\">"+
			    	"body {background-color:#FFFFFF;font-family:Arial; font-size:11pt; width:600px; }" +
	        		".tr1 {background-color:#EEEEEE}" +
	        		".tr2 {background-color:#FFFFFF}" +
	        		".tdChamp {text-align: right; width:325px; }" +
	        		".tdTitre {width:65px;}" +
	        	"</style>"+ "Description des versions :<br>" +
	        	"<body>" +
	        		"<form id='form' action='#'>" +
                    	"<table style='width:600px;'>" +
                        	"<tr>" +
		                        "<td>" +
		                        	"<table>" +
				                        "<tr class='tr1'>" +
					                        "<td class='tdTitre'>" + GestLangue.getInstance().getLocalizedText(IHM.V3_1.getLabel()) + "</td>" +
			                    			"<td class='tdChamp'>" + GestLangue.getInstance().getLocalizedText(IHM.TEXT_V3_1.getLabel()) + "</td>" +
		                        		"</tr>" +
		                        		"<tr class='tr2'>" +
			                        		"<td class='tdTitre'>" + GestLangue.getInstance().getLocalizedText(IHM.V3_0.getLabel()) + "</td>" +
			                    			"<td class='tdChamp'>" + GestLangue.getInstance().getLocalizedText(IHM.TEXT_V3_0.getLabel()) + "</td>" +
			                    		"</tr>" +
		                        		"<tr class='tr1'>" +
			                        		"<td class='tdTitre'>" + GestLangue.getInstance().getLocalizedText(IHM.V2_0.getLabel()) + "</td>" +
			                    			"<td class='tdChamp'> " + GestLangue.getInstance().getLocalizedText(IHM.TEXT_V2_0.getLabel()) + "</td>" +
		                    			"</tr>" +
		                    			"<tr class='tr2'>" +
			                    			"<td class='tdTitre'>" + GestLangue.getInstance().getLocalizedText(IHM.V1_0.getLabel()) + "</td>" +
		                        			"<td class='tdChamp'> " + GestLangue.getInstance().getLocalizedText(IHM.TEXT_V1_0.getLabel()) + "</td>" +
		                    			"</tr>" +
		                    		"</table>" +
		                    	"</td>"+
		                    "</tr>"+
		                "</table>"+
		            "</form>"+
		        "</body>"+
			"</html>");
		
		//Rend la fenêtre visible
		this.setVisible(true);
	}
	
	/**
	 * Ajoute les composants à un panel de type ImagePanel.
	 * @param image_panel ImagePanel
	 * @param c Component
	 * @param x int
	 * @param y int
	 * @param width int
	 * @param height int
	 */
	public void addComponent(ImagePanel image_panel,Component c,int x,int y,int width,int height) {		
		c.setBounds(x,y,width,height);		
		image_panel.add(c);	
	}
}
