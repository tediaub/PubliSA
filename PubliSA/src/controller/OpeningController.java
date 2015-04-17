package controller;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import model.Delivery;
import model.Mail;
import model.Model;
import model.saveLoad.XmlLoader;
import view.guiComponents.DialogFlat;
import view.guiComponents.frameOpening.OpeningFrame;
import view.language.ELabelUI;
import view.language.LanguageSelector;

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
			new DialogFlat().showDialog(LanguageSelector.getLocalizedText(ELabelUI.ERREUR_NOM.getLabel()),
					LanguageSelector.getLocalizedText(ELabelUI.MES_NOM_LIV.getLabel()),
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			return;
		}
		
		model.createUser(name);
		setViewPanel(OpeningFrame.PANEL_MAIN);
	}
	
	public void createDelivery(String name, int target){
		if(name.isEmpty()){
			new DialogFlat().showDialog(LanguageSelector.getLocalizedText(ELabelUI.ERREUR_NOM.getLabel()),
					LanguageSelector.getLocalizedText(ELabelUI.MES_NOM_LIV.getLabel()),
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			return;
		}

		for (int i = 0; i < model.getDeliveries().size(); i++) {
			String deliveryName = model.getDeliveries().get(i).getName();
			if(deliveryName.equals(name)){
				new DialogFlat().showDialog(LanguageSelector.getLocalizedText(ELabelUI.ERREUR_NOM.getLabel()),
						LanguageSelector.getLocalizedText(ELabelUI.MES_NOM_IDENTIQUE_LIV.getLabel()),
						DialogFlat.ERROR_OPERATION,
						DialogFlat.ERROR_ICON);
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
	
	public boolean maximizedFrame(){
		Rectangle usableBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		frame.setMaximizedBounds(new Rectangle(0, 0, usableBounds.width, usableBounds.height));
		frame.setExtendedState((frame.getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH ? JFrame.NORMAL : JFrame.MAXIMIZED_BOTH);
		if(frame.getExtendedState() == Frame.MAXIMIZED_BOTH){
			frame.getRootPane().setBorder(null);
			return true;
		}else{
			frame.getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),2));
			return false;
		}
	}
	
	public void closeFrame(){
		frame.dispose();
	}
	
	public void closeAll(){
		int option = new DialogFlat().showDialog(LanguageSelector.getLocalizedText(ELabelUI.QUITTER.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.MES_QUITTER.getLabel()),
				DialogFlat.ASK_OPERATION,
				DialogFlat.INFORMATION_ICON);
		
		if(option == DialogFlat.VALIDATE){
			closeFrame();
		}
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

	@Override
	public void save() {
		
	}
	
	public void setMail(Mail mail, String recipient, String object, String message) {		
		mail.setRecipient(recipient);
		mail.setObject(object);
		mail.setMessage(message);
	}
}
