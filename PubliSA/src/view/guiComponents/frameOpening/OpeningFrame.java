package view.guiComponents.frameOpening;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import controller.openFrame.OpeningController;

@SuppressWarnings("serial")
public class OpeningFrame extends JFrame {

	public static String PANEL_MAIN = "pMain";
	public static String PANEL_NEW_DELIVERY = "pNewDelivery";
	
	public OpeningFrame(OpeningController control) {
		setSize(480, 330);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		setTitle("PubliSA");
		
		getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),2));
		setIconImage(Toolkit.getDefaultToolkit().getImage(OpeningFrame.class.getResource("/logo/logoPubliSA4.png")));
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		MainPanel pMain = new MainPanel(control);
		getContentPane().add(pMain, PANEL_MAIN);
		
		NewDeliveryPanel pNewDelivery = new NewDeliveryPanel(control);
		getContentPane().add(pNewDelivery, PANEL_NEW_DELIVERY);
		
		setVisible(true);
	}
	
	public void setViewPanel(String panelString){
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
	    cl.show(getContentPane(), panelString);
	}

}
