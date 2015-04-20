package view.frame.mainFrame;

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
import view.frame.mainFrame.panel.PanelSettings;
import view.frame.mainFrame.panel.step.PanelStep1;
import view.frame.mainFrame.panel.step.PanelStep2;
import view.frame.mainFrame.panel.step.PanelStep3;
import view.frame.mainFrame.panel.step.PanelStep4;
import view.guiComponents.LabelResize;
import view.guiComponents.sideBar.PanCollapse;
import view.guiComponents.sideBar.PanExtend;
import view.guiComponents.sideBar.PanTransparent;
import view.guiComponents.sideBar.SideBar;
import view.guiComponents.tree.DeliveryTree;
import view.guiComponents.tree.PanTree;
import controller.ControllerFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer{
	
	private JPanel cards;
	private SideBar sideBarBlue;
	private LabelResize lblResize;
	private PanelSettings panSettings;
	
	public MainFrame(ControllerFrame control) {
		setSize(control.getModel().getUser().getFrameWidth(), 
				control.getModel().getUser().getFrameHeight());
		setMinimumSize(new Dimension(905, 555));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		setTitle("PubliSA");
		getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),1));
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControllerFrame.class.getResource("/logo/logoPubliSA4.png")));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(905, 555));
		
		getContentPane().setLayout(new CardLayout());
		
		//SideBar Blue
		PanExtend pExtBlue = new PanExtend(control);
		PanCollapse pCollBlue = new PanCollapse(control);
		PanTransparent pTransBlue = new PanTransparent(control);
		sideBarBlue = new SideBar(SideBar.LEFTtoRIGHT, 50, 200, pExtBlue, pCollBlue, pTransBlue);
		sideBarBlue.getContainerPane().setLayout(new BorderLayout(0, 0));
		sideBarBlue.getContainerPane().setBackground(Color.WHITE);
		getContentPane().add(sideBarBlue, "main");
		
		//PanHighFrame
		PanFrame pFrame = new PanFrame(control);
		sideBarBlue.getContainerPane().add(pFrame, BorderLayout.NORTH);
		control.getModel().addObserver(pFrame);
		
		//PanTree
		DeliveryTree tree = new DeliveryTree(control);
		tree.setOpaque(false);
		lblResize = new LabelResize(control);
		PanTree pTree = new PanTree(tree, lblResize);
		sideBarBlue.getContainerPane().add(pTree, BorderLayout.EAST);
		control.getModel().addObserver(tree);
		
		//PanCenter
		cards = new JPanel(new CardLayout());
		cards.setOpaque(false);
		PanelStep1 panStep1 = new PanelStep1(control);
		cards.add(panStep1, Integer.toString(Delivery.STEP1));
		
		PanelStep2 panStep2 = new PanelStep2(control);
		cards.add(panStep2, Integer.toString(Delivery.STEP2));
		
		PanelStep3 panStep3 = new PanelStep3(control);
		cards.add(panStep3, Integer.toString(Delivery.STEP3));
		
		PanelStep4 panStep4 = new PanelStep4(control);
		cards.add(panStep4, Integer.toString(Delivery.STEP4));
		
		sideBarBlue.getContainerPane().add(cards, BorderLayout.CENTER);
		
		//PanSettings
		panSettings = new PanelSettings(control);
		getContentPane().add(panSettings, "settings");
		
		update(control.getModel());
	}
	
	private void update(Model model){
		if(model.getMainDelivery() != null){
			CardLayout cl = (CardLayout)(cards.getLayout());
		    cl.show(cards, Integer.toString(model.getMainDelivery().getActualStep()));
		}
	}

	@Override
	public void update(Observable o, Object arg1) {
		update((Model) o);
	}
	
	public void showSettings(int i){
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
	    cl.show(getContentPane(), "settings");
	    panSettings.changeView(i);
	}
	
	public void unShowSettings(){
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
	    cl.show(getContentPane(), "main");
	}
	
	public void extSideBarBlue(){
		sideBarBlue.extendSideBar();
	}
	
	public void colSideBarBlue(){
		sideBarBlue.collapseSideBar();
	}
	
	@Override
	public void setResizable(boolean resizable) {
		super.setResizable(resizable);
		lblResize.setVisible(resizable);
		panSettings.setResizable(resizable);
	}
}