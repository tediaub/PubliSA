package view.guiComponents.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Delivery;
import model.Model;
import view.guiComponents.sideBar.SideBar;
import view.step.PanelStep1;
import view.step.PanelStep2;
import controller.ControllerFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer{
	
	private JPanel cards;
	
	public MainFrame(ControllerFrame control) {
		setSize(1005, 650);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		setTitle("PubliSA");
		getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),1));
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControllerFrame.class.getResource("/logo/logoPubliSA4.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1005, 150));
		
		SideBar sideBarWhite = control.createSideBarWhite(getContentPane());
		SideBar sideBarBlue = control.createSideBarBlue(sideBarWhite.getContainerPane());
		sideBarBlue.getContainerPane().setLayout(new BorderLayout(0, 0));
		sideBarBlue.getContainerPane().setBackground(Color.WHITE);
		control.createHighFrame(sideBarBlue.getContainerPane());
		control.createTree(sideBarBlue.getContainerPane());
		
		cards = new JPanel(new CardLayout());
		cards.setOpaque(false);
		PanelStep1 panStep1 = new PanelStep1();
		cards.add(panStep1, Integer.toString(Delivery.STEP1));
		
		PanelStep2 panStep2 = new PanelStep2();
		cards.add(panStep2, Integer.toString(Delivery.STEP2));
		
		sideBarBlue.getContainerPane().add(cards, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Model model = (Model) arg0;
		if(model.getMainDelivery() != null){
			CardLayout cl = (CardLayout)(cards.getLayout());
		    cl.show(cards, Integer.toString(model.getMainDelivery().getActualStep()));
		}
	}

}
