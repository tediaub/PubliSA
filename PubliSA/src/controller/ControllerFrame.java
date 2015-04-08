package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.Mail;
import model.Model;
import model.files.FileOGC;
import model.saveLoad.Serialization;
import verification.Checking;
import view.guiComponents.DialogFlat;
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
		PanTree pTree = new PanTree(tree, lblResize);
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
	
	@Override
	public void closeAll() {
		int option = new DialogFlat().showDialog(GestLangue.getLocalizedText(IHM.QUITTER.getLabel()),
				GestLangue.getLocalizedText(IHM.MES_QUITTER.getLabel()),
				DialogFlat.CLOSE_OPERATION,
				DialogFlat.INFORMATION_ICON);

		switch (option) {
		case DialogFlat.SAVE_AND_CLOSE:
			save();
		case DialogFlat.VALIDATE:
			closeFrame();
			break;
		default:
			break;
		}
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
		Serialization.saveModel(model);
	}
	
	public void changeStep(){
		int step = model.getMainDelivery().getActualStep() + 1;
		if((step == Delivery.STEP3 &&model.getMainDelivery().getTarget() == Delivery.THALES)
				||(step == Delivery.STEP4 && model.getMainDelivery().getTarget() == Delivery.UBIK)){
			
		}else{
			model.getMainDelivery().setActualStep(model.getMainDelivery().getActualStep() + 1);
		}
	}
	
	public void createDelivery(String name, int target){
		if(name.isEmpty()){
			new DialogFlat().showDialog(GestLangue.getLocalizedText(IHM.ERREUR_NOM.getLabel()),
					GestLangue.getLocalizedText(IHM.MES_NOM_LIV.getLabel()),
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			return;
		}

		for (int i = 0; i < model.getDeliveries().size(); i++) {
			String deliveryName = model.getDeliveries().get(i).getName();
			if(deliveryName.equals(name)){
				new DialogFlat().showDialog(GestLangue.getLocalizedText(IHM.ERREUR_NOM.getLabel()),
						GestLangue.getLocalizedText(IHM.MES_NOM_IDENTIQUE_LIV.getLabel()),
						DialogFlat.ERROR_OPERATION,
						DialogFlat.ERROR_ICON);
				return;
			}
			
		}
		
		model.createDelivery(name, target);
	}
	
	public Delivery getDelivery(String deliveryName){
		for (int i = 0; i < model.getDeliveries().size(); i++) {
			if(model.getDeliveries().get(i).getName().contentEquals(deliveryName)){
				return model.getDeliveries().get(i);
			}
		}
		return null;
	}
	
	public void openDelivery(String name){
		Delivery d = getDelivery(name);
		int option = JOptionPane.NO_OPTION;

		if(d != null && d!= model.getMainDelivery()){
			option = new DialogFlat().showDialog(GestLangue.getLocalizedText(IHM.NOM_APPLI.getLabel()),
					GestLangue.getLocalizedText(IHM.CHANGE_LIV.getLabel()),
					DialogFlat.ASK_OPERATION,
					DialogFlat.INFORMATION_ICON);
			
			if(option == DialogFlat.VALIDATE){
				model.changeMainDelivery(getDelivery(name));
			}
		}
	}
	
	public void setDCR(String dcr){
		model.getMainDelivery().setDCR(dcr);
	}
	
	public void checkingOnUbik(){
		ArrayList<String[]> array = new Checking(this).checkingOnUbik();
		if(array != null){
			model.getMainDelivery().setDataStep4(array);
		}
	}	
	
	public void checkingFromAkka(){
		ArrayList<String[]> array = new Checking(this).checkingFromAkka();
		if(array != null){
			model.getMainDelivery().setDataStep2(array);
		}
	}
	
	public void moveFile(File file) {
		File folder = new File(file.getParentFile().getPath() + File.separator + model.getMainDelivery().getName());
		if(!folder.exists()){folder.mkdir();}
		file.renameTo(new File(folder.getAbsolutePath() + File.separator + file.getName()));
	}
	
	public void deleteHeader(FileOGC ogc){
		File[] fileList = ogc.getParentFile().listFiles();
		
		for(int i = 0; i< fileList.length; i++){
			String fileName = fileList[i].getName();
			int j = fileName.lastIndexOf('.');
		
			if (j > 0 && j < fileName.length() - 1) {
				String extension = fileName.substring(j+1).toLowerCase();
				extension = extension.substring(0, 3);
				
				if(extension.equals("ogc")){
					deleteFileHeader(fileList[i], "!---------");
				}
				
				if(extension.equals("est") || extension.equals("ext")){
					deleteFileHeader(fileList[i], "---------");
				}
			}
		}
		new DialogFlat().showDialog(GestLangue.getLocalizedText(GestLangue.getLocalizedText(IHM.NOM_APPLI.getLabel())),
				"Suppression des entêtes terminée.\nLes fichiers modifiés ont été placés dans le dossier :\n" 
						+ ogc.getParentFile().getPath() 
						+ File.separator 
						+ model.getMainDelivery().getName()
						+ ".",
				DialogFlat.INFORMATION_OPERATION,
				DialogFlat.INFORMATION_ICON);
	}
	
	private void deleteFileHeader(File file, String stringToFind){
		try {
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			File repertoire = new File(file.getParentFile().getPath() + File.separator + model.getMainDelivery().getName());
			
			if(!repertoire.exists()){repertoire.mkdir();}
			
			// Supprime les numéros qui se trouve à la fin du fichier (ex: ;1 ;2 ;3 ;4 ;5 ...)
			String nomFichier = file.getName();
			int j = nomFichier.lastIndexOf('.');
			nomFichier = nomFichier.substring(0, j + 4);
	
			OutputStream ops = new FileOutputStream(new File(file.getParentFile().getPath() 
					+ File.separator + model.getMainDelivery().getName() 
					+ File.separator + nomFichier+ ".txt")); 
			OutputStreamWriter opsr = new OutputStreamWriter(ops);
			BufferedWriter bw = new BufferedWriter(opsr);
			
			String ligne;
			int indiceLigne = 0;
			
			while ((ligne = br.readLine())!= null){
				if(ligne.contains(stringToFind)){
					indiceLigne++;
				}
				if(indiceLigne == 3){
					while(indiceLigne != 0){
						ligne = br.readLine();
						ligne = ligne.trim();
						if(ligne.contains(stringToFind)){
							indiceLigne = 0;
						}
						else{
							bw.write(ligne + System.getProperty("line.separator"));	
						}	
					}
				}
			}
			br.close();
			bw.close();
			}		
		catch (Exception e){
			System.err.println(e.toString());
		}
	}

	public void setMail(Mail mail, String recipient, String object, String message) {		
		mail.setRecipient(recipient);
		mail.setObject(object);
		mail.setMessage(message);
	}
	
	public String getHighDcr(String[] tabDcr) throws Exception{
		String DCRMax = "0";
		try{
			for (int i = 0; i<tabDcr.length;i++){
				if(Integer.parseInt(DCRMax)<Integer.parseInt(tabDcr[i])){
					DCRMax = tabDcr[i];
				}
				if(tabDcr[i].length() != 4){
					@SuppressWarnings("unused")
					int k = 1/0;
				}
			}
		}catch(Exception e){
			new DialogFlat().showDialog("Erreur DCR",
					"<html>Vérifier que :<br>- la/les DCR sont des nombres<br>- elle(s) comporte(nt) quatre chiffres<br>"
					+ "- il n'y a pas d'espace<br>- le séparateur est bien une virgule(,)</html",
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			throw new Exception();
		}
		return DCRMax;
	}
	
	public void createMail() throws Exception{
		if(model.getMainDelivery().getTarget() == Delivery.UBIK){
			if(model.getMainDelivery().getActualStep() == Delivery.STEP1){
				createMail(model.getUser().getMails().get(0), this);
			}else if(model.getMainDelivery().getActualStep() == Delivery.STEP3){
				createMail(model.getUser().getMails().get(2), this);
			}
		}else if(model.getMainDelivery().getTarget() == Delivery.THALES){
			if(model.getMainDelivery().getActualStep() == Delivery.STEP1){
				createMail(model.getUser().getMails().get(1), this);
			}else if(model.getMainDelivery().getActualStep() == Delivery.STEP3){
				createMail(model.getUser().getMails().get(3), this);
			}
		}
	}
	
	public static void createMail(Mail mail, ControllerFrame control) throws Exception{
		
		String dcr = control.getModel().getMainDelivery().getDCR();
		String computer = control.getModel().getMainDelivery().getComputer()+ " " + control.getModel().getMainDelivery().getStandard();
		
		String[] tabDcr = dcr.split(",");
		if(dcr.isEmpty()){
			new DialogFlat().showDialog("Erreur DCR",
					"Aucune DCR n'a été rentrée.",
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			
			throw new Exception();
		}
		
		String DCRMax = control.getHighDcr(tabDcr);
		
		String recipient = mail.getRecipient();
		String object = mail.getObject();
		String message = mail.getMessage();
		
		String toAdd = new String(" " + DCRMax);
	    StringTokenizer s = new StringTokenizer(message,"#");
	    message = "";
	    while (s.hasMoreTokens()) {
	       if(s.countTokens()!= 1){
	    	   message = message + s.nextToken() + toAdd;
	       }
	       else{
	          message = message + s.nextToken();
	       }
	    }
	    
	    ///////////////////////////
	    for (int i =0; i<tabDcr.length;i++){
			if (i != 0){toAdd = toAdd + ", " + tabDcr[i];}
			else{toAdd = "MOD " + tabDcr[i];}
		}
	    
	    s = new StringTokenizer(message,"*");
	    message = "";
	    while (s.hasMoreTokens()) {
	       if(s.countTokens()!= 1){
	    	   message = message + s.nextToken() + toAdd;
	       }
	       else{
	    	   message = message + s.nextToken();
	       }
	    }
	    
	    ////////////////////////
	    for (int i =0; i<tabDcr.length;i++){
			if (i != 0){toAdd = toAdd + ", " + tabDcr[i];}
			else{toAdd = "MOD " + tabDcr[i];}
		}
	    toAdd = toAdd + " (standard : " + computer +")"; 
	    s = new StringTokenizer(message,"@");
	    message = "";
	    while (s.hasMoreTokens()) {
	       if(s.countTokens()!= 1){
	    	   message = message + s.nextToken() + toAdd;
	       }
	       else{
	    	   message = message + s.nextToken();
	       }
	    }
	    
	    for (int i =0; i<tabDcr.length;i++){
			if (i != 0){toAdd = toAdd + ", " + tabDcr[i];}
			else{toAdd = " MOD " + tabDcr[i];}
		}
	    
	    object = object + toAdd + " (standard : " + computer+")";
	    if(Desktop.isDesktopSupported()){		
	    	if(Desktop.getDesktop().isSupported(Desktop.Action.MAIL)){
	    		try {
					 Desktop.getDesktop().mail(new URI("mailto", recipient + "?subject=" + object +"&body=" + message, null));
				 } catch (IOException e1) {
					 e1.printStackTrace();
				 } catch (URISyntaxException e1) {
					 e1.printStackTrace();
				 }
	    	}
	    }
	}
}