package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import langue.GestLangue;
import langue.IHM;
import model.Model;
import model.saveLoad.Serialization;
import view.guiComponents.frame.LabelResize;
import view.guiComponents.frame.MainFrame;
import view.guiComponents.frame.PanFrame;
import view.guiComponents.sideBar.PanTransparent;
import view.guiComponents.sideBar.SideBar;
import view.guiComponents.sideBar.blue.PanCollapse;
import view.guiComponents.sideBar.blue.PanExtend;
import view.guiComponents.sideBar.white.PanExtSettings;
import view.guiComponents.tree.DeliveryTree;
import view.guiComponents.tree.PanTree;

public class ControllerFrame implements IFrameController{

	private MainFrame frame;
	
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

	private Model model;
	
	public ControllerFrame(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return model;
	}
	
	public void createFrame(){
		frame = new MainFrame(this);
		model.addObserver(frame);
	}
	
	public void setFrameVisible(boolean b){
		frame.setVisible(b);
	}
	
	public void createTree(Container container){
		DeliveryTree tree = new DeliveryTree(this);
		tree.setOpaque(false);
		lblResize = new LabelResize(this);
		pTree = new PanTree(tree, lblResize);
		container.add(pTree, BorderLayout.EAST);
		
		model.addObserver(tree);
	}
	
	public void createHighFrame(Container container){
		pFrame = new PanFrame(this);
		container.add(pFrame, BorderLayout.NORTH);
		
		model.addObserver(pFrame);
	}
	
	public SideBar createSideBarWhite(Container container){
		pExtWhite = new PanExtSettings(this);
		pTransWhite = new PanTransparent(this);
		sideBarWhite = new SideBar(SideBar.RIGHTtoLEFT, 900, pExtWhite, pTransWhite);
		container.add(sideBarWhite);
		return sideBarWhite;
	}
	
	public SideBar createSideBarBlue(Container container){
		pExtBlue = new PanExtend(this);
		pCollBlue = new PanCollapse(this);
		pTransBlue = new PanTransparent(this);
		sideBarBlue = new SideBar(SideBar.LEFTtoRIGHT, 50, 200, pExtBlue, pCollBlue, pTransBlue);
		container.add(sideBarBlue);

		return sideBarBlue;
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
			frame.getRootPane().setBorder(new LineBorder(new Color(0,0,0,40)));
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

	public void save(){
		new Serialization();
		Serialization.saveModel(model);
	}

	public void createDelivery(String name, int target){
		if(name.isEmpty()){
			JOptionPane.showMessageDialog(null, 
					GestLangue.getInstance().getLocalizedText(IHM.MES_NOM_LIV.getLabel()),
					GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM.getLabel()),
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int i = 0; i < model.getDeliveries().size(); i++) {
			String deliveryName = model.getDeliveries().get(i).getName();
			if(deliveryName.equals(name)){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.MES_NOM_IDENTIQUE_LIV.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.ERREUR_NOM.getLabel()),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		
		model.createDelivery(name, target);
	}
}