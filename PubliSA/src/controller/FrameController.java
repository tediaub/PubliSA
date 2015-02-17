package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import myJTree.DeliveryTree;
import view.guiComponents.frame.LabelResize;
import view.guiComponents.frame.PanFrame;
import view.guiComponents.sideBar.PanTransparent;
import view.guiComponents.sideBar.SideBar;
import view.guiComponents.sideBar.blue.PanCollapse;
import view.guiComponents.sideBar.blue.PanExtend;
import view.guiComponents.sideBar.white.PanExtSettings;
import view.guiComponents.tree.PanTree;
import view.step.PanelComputer;
import view.step.PanelDCR;

public class FrameController{

	private JFrame frame;
	
	private SideBar sideBarWhite;
	private PanTransparent pTransWhite;
	private PanExtSettings pExtWhite;
	
	private PanTree pTree;
	private LabelResize lblResize;
	
	private SideBar sideBarBlue;
	private PanExtend pExtBlue;
	private PanCollapse pCollBlue;
	private PanTransparent pTransBlue;

	private PanFrame pFrame;

	public void createFrame(){
		frame = new JFrame();
		frame.getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),2));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameController.class.getResource("/logo/logoPubliSA4.png")));
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 1005, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(1005, 150));
		
		createSideBarWhite(frame.getContentPane());
		createSideBarBlue(sideBarWhite.getContainerPane());
		sideBarBlue.getContainerPane().setLayout(new BorderLayout(0, 0));
		sideBarBlue.getContainerPane().setBackground(Color.WHITE);
		createHighFrame(sideBarBlue.getContainerPane());
		createTree(sideBarBlue.getContainerPane());
		
		createStep1(sideBarBlue.getContainerPane());
		
		frame.setVisible(true);
	}
	
	public void createTree(Container container){
		DeliveryTree tree = new DeliveryTree();
		tree.setOpaque(false);
		lblResize = new LabelResize(this);
		pTree = new PanTree(tree, lblResize);
		container.add(pTree, BorderLayout.EAST);
	}
	
	public void createHighFrame(Container container){
		pFrame = new PanFrame(this);
		container.add(pFrame, BorderLayout.NORTH);
	}
	
	public void createSideBarWhite(Container container){
		pExtWhite = new PanExtSettings(this);
		pTransWhite = new PanTransparent(this);
		sideBarWhite = new SideBar(SideBar.RIGHTtoLEFT, 900, pExtWhite, pTransWhite);
		container.add(sideBarWhite);
	}
	
	public void createSideBarBlue(Container container){
		pExtBlue = new PanExtend(this);
		pCollBlue = new PanCollapse(this);
		pTransBlue = new PanTransparent(this);
		sideBarBlue = new SideBar(SideBar.LEFTtoRIGHT, 50, 200, pExtBlue, pCollBlue, pTransBlue);
		container.add(sideBarBlue);
	}
	
	public void createStep1(Container container){
		JPanel panStep1 = new JPanel();
		panStep1.setOpaque(false);
		panStep1.setLayout(new GridLayout(0, 1, 0, 0));
		
		PanelDCR pDCR = new PanelDCR();
		PanelComputer pComputer= new PanelComputer();
		panStep1.add(pDCR);
		panStep1.add(pComputer);
		
		container.add(panStep1, BorderLayout.CENTER);
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
	
	public Point getMouseOnFrame(int xMouse, int yMouse){
		int xOrigin = xMouse- frame.getX();
		int yOrigin = yMouse- frame.getY();
		return(new Point(xOrigin, yOrigin));
	}
	
	public void setFrameLocation(int x, int y){
		frame.setLocation(x,y);
	}
	
	public void iconifiedFrame(){
		frame.setExtendedState(Frame.ICONIFIED);
	}
	
	public void maximizedFrame(){
		Rectangle usableBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frame.setMaximizedBounds(new Rectangle(0, 0, usableBounds.width, usableBounds.height));
		frame.setExtendedState((frame.getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
		if(frame.getExtendedState() == Frame.MAXIMIZED_BOTH){
			frame.getRootPane().setBorder(null);
			lblResize.setVisible(false);
		}else{
			frame.getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),2));
			lblResize.setVisible(true);
		}
	}
	
	public void closeFrame(){
		frame.dispose();
	}
	
	public void resizeFrame(int x, int y){
		int l = 0, xF = 0, h = 0, yF = 0;
		if(x <= frame.getX()){
			l = frame.getWidth() +(x-frame.getX());
			xF = x;
		}else if(x > frame.getX()){
			l = x - frame.getX();
			xF = frame.getX();
		}
		if(y <= frame.getY()){
			h = frame.getHeight() +(y-frame.getY());
			yF = y;
		}else if(y > frame.getY()){
			h = y - frame.getY();
			yF = frame.getY();
		}
		frame.setBounds(xF, yF, l, h);
	}
}