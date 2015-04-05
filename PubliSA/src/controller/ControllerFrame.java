package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import langue.GestLangue;
import langue.IHM;
import loading.FilterOGC;
import loading.LoadFile;
import model.Delivery;
import model.Model;
import model.files.FichierPDF;
import model.files.FileOGC;
import model.files.FileToCheck;
import model.saveLoad.Serialization;
import verification.Checking;
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
			option = JOptionPane.showConfirmDialog(null, GestLangue.getLocalizedText(IHM.CHANGE_LIV.getLabel()),
					GestLangue.getLocalizedText(IHM.NOM_APPLI.getLabel()),
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			
			if(option != JOptionPane.NO_OPTION &&
					option != JOptionPane.CANCEL_OPTION &&
					option != JOptionPane.CLOSED_OPTION){
				model.changeMainDelivery(getDelivery(name));
			}
		}
	}
	
	public void setDCR(String dcr){
		model.getMainDelivery().setDCR(dcr);
	}
	
	public void checkingOnUbik(){
		model.getMainDelivery().setDataStep4(new Checking(this).checkingOnUbik());
	}	
	
	public void checkingFromAkka(){
		
		model.getMainDelivery().setDataStep2(new Checking(this).checkingFromAkka());
		
//		if(model.getMainDelivery().getDCR().isEmpty()){
//			JOptionPane.showMessageDialog(null, "Aucune DCR n'a été rentrée.", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		
//		String[] tabDCR = model.getMainDelivery().getDCR().split(",");
//		String DCRMax = "0";
//		try{
//			for (int i = 0; i<tabDCR.length;i++){
//				if(Integer.parseInt(DCRMax)<Integer.parseInt(tabDCR[i])){
//					DCRMax = tabDCR[i];
//				}
//				if(tabDCR[i].length() != 4){
//					@SuppressWarnings("unused")
//					int k = 1/0;
//				}
//			}
//		}catch(Exception e){
//			JOptionPane.showMessageDialog(null, "Vérifier que : \r\n- la/les DCR sont des nombres\r\n- elle(s) comporte(nt) quatre chiffres\r\n- il n'y a pas d'espace\r\n- le séparateur est bien une virgule(,)", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
//			return;
//		}	
//		
//		String path = LoadFile.loadFrame(model.getMainDelivery().getPathOGC(), "Ouvrir fichier OGC", new FilterOGC());
//		if(path == null){return;}
//		FileOGC OGC = new FileOGC(path);
//		model.getMainDelivery().setPathOGC(path);
//		
//		//Message d'erreur
//		if(!OGC.getName().endsWith(DCRMax + ".OGC")){
//			JOptionPane.showMessageDialog(null, "La DCR la plus grande ne correspond pas avec la DCR du fichier OGC.", "Erreur de fichier OGC", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		
//		ArrayList<String> listePlancheOGC = new ArrayList<String> ();
//		for(int i=0; i<tabDCR.length;i++){
//			listePlancheOGC.add(tabDCR[i]);
//			listePlancheOGC.addAll(OGC.extractOgcPlank(tabDCR[i], model.getMainDelivery().getTargetString()));
//		}
//		
//		String DCR = new String();
//		////Dossier du fichier OGC
//		File directory = OGC.getParentFile();
//		File[] subfiles = directory.listFiles();
//		FileToCheck PDF;
//		ArrayList<FileToCheck> listePlanchePDF = new ArrayList<FileToCheck>();
//		ArrayList<String> listePlancheErrOGC = new ArrayList<String> ();
//		
//		for(int i = 0 ; i < subfiles.length; i++){
//			if(subfiles[i].getName().substring(subfiles[i].getName().indexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("PDF")){
//				PDF = new FileToCheck(this, subfiles[i].getPath());
//				
//				listePlanchePDF.add(PDF);
//				boolean erreur = true;
//				
//				for(int j=0; j < listePlancheOGC.size();j++){
//					if(listePlancheOGC.get(j).length()==4){
//						do{
//							DCR = listePlancheOGC.get(j);
//							j++;
//						}while(listePlancheOGC.get(j).length()==4);
//					}
//					if(listePlancheOGC.get(j).substring(listePlancheOGC.get(j).indexOf(".")+1, listePlancheOGC.get(j).length()).toUpperCase().contains("PDF")){
//						if(listePlancheOGC.get(j).contentEquals(PDF.getName())){
//							PDF.isPresent();
//							PDF.setDCR(DCR);
//							j =listePlancheOGC.size();
//						}
//						else if(PDF.getException() && listePlancheOGC.get(j).contentEquals(PDF.getTheoreticalName())){
//							PDF.setDCR(DCR);
//							PDF.setMessage(1);
//							j =listePlancheOGC.size();
//						}
//						else if(PDF.getName().substring(0,10).compareTo(listePlancheOGC.get(j).substring(0,10)) == 0){
//							if(Integer.parseInt(PDF.getName().substring(14, 16)) != Integer.parseInt(listePlancheOGC.get(j).substring(14, 16))
//									||Integer.parseInt(PDF.getName().substring(11, 13)) != Integer.parseInt(listePlancheOGC.get(j).substring(11, 13))){
//								PDF.setMessage(2);
//							}
//							PDF.setDCR("ND");
//							erreur = false;
//						}
//						else if(erreur){
//							PDF.setDCR("ND");
//							PDF.setMessage(0);
//						}
//					}
//				}
//			}
//		}
//		
//		//Calcul des fichier présents dans l'OGC
//		for(int i=0 ; i < listePlancheOGC.size(); i++){
//			boolean exclusion = false;
//			for(int j=0 ; j < listePlanchePDF.size(); j++){
//				
//				if(listePlancheOGC.get(i).contentEquals(listePlanchePDF.get(j).getName())
//						||listePlancheOGC.get(i).contentEquals(listePlanchePDF.get(j).getTheoreticalName())){
//					exclusion = true;
//				}
//			}
//			if (!exclusion){
//				listePlancheErrOGC.add(listePlancheOGC.get(i));
//			}
//		}		
//		
//		DCR = new String();
//		ArrayList<String[]> tabArray = new ArrayList<String[]>();
//		for (int i = 0; i< listePlancheErrOGC .size(); i++){
//			if (listePlancheErrOGC.get(i).length()==4){
//				DCR = listePlancheErrOGC.get(i);
//			}
//			else{
//				String[] s = {DCR,	"",	listePlancheErrOGC.get(i),"KO",	"Planche non présente dans le dossier source"};
//				tabArray.add(s);
//			}
//		}
//		
//		for (int i = 0; i< listePlanchePDF .size(); i++){
//			if (listePlanchePDF.get(i).getPresent()){
//				String[] s = {listePlanchePDF.get(i).getDCR(), listePlanchePDF.get(i).getName(),	listePlanchePDF.get(i).getName(), "OK",""};
//				tabArray.add(s);
//			}
//			else {
//				String[] s = {listePlanchePDF.get(i).getDCR(), listePlanchePDF.get(i).getName(),	"", "KO", listePlanchePDF.get(i).getMessage()};
//				tabArray.add(s);
//			}
//		}
//		
//		model.getMainDelivery().setDataStep2(tabArray);
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
		JOptionPane.showMessageDialog(null,
				"Suppression des entêtes terminée.\nLes fichiers modifiés ont été placés dans le dossier :\n" 
				+ ogc.getParentFile().getPath() 
				+ File.separator 
				+ model.getMainDelivery().getName()
				+ "."  
				, "PubliSA", JOptionPane.INFORMATION_MESSAGE);
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
	
			OutputStream ops = new FileOutputStream(new File(file.getParentFile().getPath() + File.separator + model.getMainDelivery().getName() + File.separator + nomFichier+ ".txt")); 
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
}