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
import view.guiComponents.frameOpening.OpeningFrame;
import controller.ControllerFrame;
import controller.IFrameController;

public class OpeningController implements IFrameController {

	private Model model;
	private OpeningFrame frame;
	private ControllerFrame control;
	
	public OpeningController(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return model;
	}
	
	public void createOpeningFrame(){
		frame = new OpeningFrame(this);
	}
	
	public void createMainFrame(){
		control = new ControllerFrame(model);
		control.createFrame();
	}
	
	public void setMainFrameVisible(boolean b) {
		control.setFrameVisible(b);
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
		setMainFrameVisible(true);
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

	public Model getModel() {
		return model;
	}

	public void openDelivery() {
		setMainFrameVisible(true);
		closeFrame();
	}
	
	public void openDelivery(Delivery delivery) {
		model.setMainDelivery(delivery);
		setMainFrameVisible(true);
		closeFrame();
	}
}
