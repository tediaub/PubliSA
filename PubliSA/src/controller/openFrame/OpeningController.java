package controller.openFrame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.Model;
import model.saveLoad.XmlLoader;
import view.guiComponents.frameOpening.OpeningFrame;
import controller.ControllerFrame;
import controller.IFrameController;

public class OpeningController implements IFrameController {

	private Model model;
	private OpeningFrame frame;
	private ControllerFrame control;
	private XmlLoader xml;
	
	public OpeningController(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return model;
	}
	
	public void createOpeningFrame(String panel){
		frame = new OpeningFrame(this);
		setViewPanel(panel);
		frame.setVisible(true);
	}
	
	public void createMainFrame(){
		control = new ControllerFrame(model);
		control.createFrame();
	}
	
	public void setMainFrameVisible(boolean b) {
		control.setFrameVisible(b);
	}
	
	public void createUser(String name){
		if(name.isEmpty()){
			JOptionPane.showMessageDialog(null, 
					GestLangue.getLocalizedText(IHM.MES_NOM_LIV.getLabel()),
					GestLangue.getLocalizedText(IHM.ERREUR_NOM.getLabel()),
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		model.createUser(name);
		setViewPanel(OpeningFrame.PANEL_MAIN);
	}
	
	public void createDelivery(String name, int target){
		if(name.isEmpty()){
			JOptionPane.showMessageDialog(null, 
					GestLangue.getLocalizedText(IHM.MES_NOM_LIV.getLabel()),
					GestLangue.getLocalizedText(IHM.ERREUR_NOM.getLabel()),
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int i = 0; i < model.getDeliveries().size(); i++) {
			String deliveryName = model.getDeliveries().get(i).getName();
			if(deliveryName.equals(name)){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getLocalizedText(IHM.MES_NOM_IDENTIQUE_LIV.getLabel()),
						GestLangue.getLocalizedText(IHM.ERREUR_NOM.getLabel()),
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		
		model.createDelivery(name, target);
		openDelivery();
		closeFrame();
	}
	
	public void setViewPanel(String panelString){
		frame.setViewPanel(panelString);
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
		}else{
			frame.getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),2));
		}
	}
	
	public void closeFrame(){
		frame.dispose();
	}

	@Override
	public void resizeFrame(int x, int y) {
		
	}

	@Override
	public Point getMouseOnFrame(int xMouse, int yMouse){
		int xOrigin = xMouse- frame.getX();
		int yOrigin = yMouse- frame.getY();
		return(new Point(xOrigin, yOrigin));
	}
	
	public void setFrameLocation(int x, int y){
		frame.setLocation(x,y);
	}


	public void openDelivery() {
		createMainFrame();
		setMainFrameVisible(true);
		closeFrame();
	}
	
	public void openDelivery(String deliveryName) {
		Delivery delivery = getDelivery(deliveryName);
		model.setMainDelivery(delivery);
		openDelivery();
	}
	
	public Delivery getDelivery(String deliveryName){
		for (int i = 0; i < model.getDeliveries().size(); i++) {
			if(model.getDeliveries().get(i).getName().contentEquals(deliveryName)){
				return model.getDeliveries().get(i);
			}
		}
		return null;
	}
	
	public void createXml(String path){
		xml = new XmlLoader(path);
	}
	
	public XmlLoader getXml(){
		return xml;
	}
}
