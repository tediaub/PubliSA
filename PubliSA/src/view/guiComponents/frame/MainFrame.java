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
import view.guiComponents.sideBar.PanTransparent;
import view.guiComponents.sideBar.SideBar;
import view.guiComponents.sideBar.blue.PanCollapse;
import view.guiComponents.sideBar.blue.PanExtend;
import view.guiComponents.sideBar.white.PanExtSettings;
import view.guiComponents.tree.DeliveryTree;
import view.guiComponents.tree.PanTree;
import view.step.PanelStep1;
import view.step.PanelStep2;
import view.step.PanelStep3;
import view.step.PanelStep4;
import controller.ControllerFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer{
	
	private JPanel cards;
	private SideBar sideBarBlue;
	private SideBar sideBarWhite;
	private LabelResize lblResize;
	
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
		setMinimumSize(new Dimension(905, 530));
		
		//SideBar White
		PanExtSettings pExtWhite = new PanExtSettings(control);
		PanTransparent pTransWhite = new PanTransparent(control);
		sideBarWhite = new SideBar(SideBar.RIGHTtoLEFT, 900, pExtWhite, pTransWhite);
		getContentPane().add(sideBarWhite);
		
		//SideBar Blue
		PanExtend pExtBlue = new PanExtend(control);
		PanCollapse pCollBlue = new PanCollapse(control);
		PanTransparent pTransBlue = new PanTransparent(control);
		sideBarBlue = new SideBar(SideBar.LEFTtoRIGHT, 50, 200, pExtBlue, pCollBlue, pTransBlue);
		sideBarWhite.getContainerPane().add(sideBarBlue);
		sideBarBlue.getContainerPane().setLayout(new BorderLayout(0, 0));
		sideBarBlue.getContainerPane().setBackground(Color.WHITE);
		
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
	
	public void extSideBarWhite(){
		sideBarWhite.extendSideBar();
	}
	
	public void colSideBarWhite(){
		sideBarWhite.collapseSideBar();
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
	}
}