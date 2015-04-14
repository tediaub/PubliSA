package view.guiComponents.frameOpening;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.OpeningController;

@SuppressWarnings("serial")
public class OpeningFrame extends JFrame {

	private PanelOpen panelMainFirstNew;
	private AllDeliveryPanel panelAll;
	
	public static String PANEL_FIRST_LAUNCH = "pFirstLaunch";
	public static String PANEL_MAIN = "pMain";
	public static String PANEL_NEW_DELIVERY = "pNewDelivery";
	public static String PANEL_ALL_DELIVERY = "pAllDelivery";
	
	public OpeningFrame(OpeningController control){		
		setTitle("PubliSA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(OpeningFrame.class.getResource("/logo/logoPubliSA4.png")));
		setUndecorated(true);
		
		setSize(700, 400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		getContentPane().setLayout(new CardLayout(0, 0));
		
		panelMainFirstNew = new PanelOpen(control);
		getContentPane().add(panelMainFirstNew, PANEL_MAIN);
		
		panelAll = new AllDeliveryPanel(control);
		getContentPane().add(panelAll, PANEL_ALL_DELIVERY);
	}

	public void setViewPanel(String panelString) {
		if(panelString.equals(PANEL_ALL_DELIVERY)){
			CardLayout cl = (CardLayout)(getContentPane().getLayout());
		    cl.show(getContentPane(), PANEL_ALL_DELIVERY);
		}else{
			CardLayout cl = (CardLayout)(getContentPane().getLayout());
		    cl.show(getContentPane(), PANEL_MAIN);
		    
		    panelMainFirstNew.setViewPanel(panelString);
		}
	}
}
