package controller.checking;

import java.io.File;
import java.util.ArrayList;

import model.Delivery;
import model.files.FichierCSV;
import model.files.FileOGC;
import model.files.FileToCheck;
import view.guiComponents.DialogFlat;
import controller.ControllerFrame;
import controller.loading.FilterOGC;
import controller.loading.LoadSaveFile;

public class Checking {

	private ControllerFrame control;
	////Step 2 
	private ArrayList<String> listePlancheOGC = new ArrayList<String> ();
	private ArrayList<FileToCheck> listePlancheCheck = new ArrayList<FileToCheck>();
	private ArrayList<String> listePlancheErrOGC = new ArrayList<String> ();
	private String DCRMax;
	private String[] tabDCR;
	private String path;
	
	////Step4
	private String pathOGC;
	private String pathCSV;
	private ArrayList<String> hCSVok = new ArrayList<String>();
	private ArrayList<String> hOGCok = new ArrayList<String>();
	private ArrayList<String> hCSV = new ArrayList<String>();
	private ArrayList<String> hOGC = new ArrayList<String>();

	public Checking(ControllerFrame control) {
		this.control = control;
	}
	
	public ArrayList<String[]> checkingFromAkka(){
		
		int target = control.getModel().getMainDelivery().getTarget();
		
		try {
			getOgc();
			FileOGC OGC = new FileOGC(path);
			getListOgc(OGC);
			
			String DCR = new String();
			////Dossier du fichier OGC
			File directory = OGC.getParentFile();
			File[] subfiles = directory.listFiles();
			
			
			for(int i = 0 ; i < subfiles.length; i++){
				if(target == Delivery.UBIK){
					if(subfiles[i].getName().substring(subfiles[i].getName().indexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("PDF")){
						FileToCheck file = new FileToCheck(control, subfiles[i].getPath());
						listePlancheCheck.add(file);
						checkFile(file, DCR);
					}
				}else if(target == Delivery.THALES){
					if(subfiles[i].getName().substring(subfiles[i].getName().indexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("ASC")){
						FileToCheck file = new FileToCheck(control, subfiles[i].getPath());
						listePlancheCheck.add(file);
						checkFile(file, DCR);
					}
					if(subfiles[i].getName().substring(subfiles[i].getName().indexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("SCH")){
						FileToCheck file = new FileToCheck(control, subfiles[i].getPath());
						listePlancheCheck.add(file);
						checkFile(file, DCR);
					}
				}
			}
			
			control.getModel().getMainDelivery().setNbFileFolder(listePlancheCheck.size());
			control.getModel().getMainDelivery().setNbFileSummary(listePlancheOGC.size());
			
			//Calcul des fichier présents dans l'OGC
			for(int i=0 ; i < listePlancheOGC.size(); i++){
				boolean exclusion = false;
				String plOgc = listePlancheOGC.get(i);
				if(checkOgc(listePlancheCheck, plOgc) == true)exclusion = true;
				if (!exclusion){
					listePlancheErrOGC.add(listePlancheOGC.get(i));
				}
			}
		} catch (Exception e) {
			return null;
		}
		
		return getResultData();		
	}
	
	private void getOgc()throws Exception{
		if(control.getModel().getMainDelivery().getDCR().isEmpty()){
			new DialogFlat().showDialog("Erreur DCR",
					"Aucune DCR n'a été rentrée.",
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			throw new Exception();
		}
		
		tabDCR = control.getModel().getMainDelivery().getDCR().split(",");
		DCRMax = control.getHighDcr(tabDCR);
		
		path = LoadSaveFile.loadFrame(control.getModel().getMainDelivery().getPathOGC(), "Ouvrir fichier OGC", new FilterOGC());
		if(path == null){throw new Exception();}

		control.getModel().getMainDelivery().setPathOGC(path);
	}
	
	private void getListOgc(FileOGC OGC) throws Exception{
		//Message d'erreur
		if(!OGC.getName().endsWith(DCRMax + ".OGC")){
			new DialogFlat().showDialog("Erreur de fichier OGC",
					"<html>La DCR la plus grande ne correspond pas avec<br>la DCR du fichier OGC.</html>",
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			throw new Exception();
		}
		
		for(int i=0; i<tabDCR.length;i++){
			listePlancheOGC.add(tabDCR[i]);
			listePlancheOGC.addAll(OGC.extractOgcPlank(tabDCR[i], control.getModel().getMainDelivery().getTargetString()));
		}
	}
	
	private void checkFile(FileToCheck file, String DCR){
		boolean erreur = true;
		
		for(int j=0; j < listePlancheOGC.size();j++){
			if(listePlancheOGC.get(j).length()==4){
				do{
					DCR = listePlancheOGC.get(j);
					j++;
				}while(listePlancheOGC.get(j).length()==4);
			}
			
			if(listePlancheOGC.get(j).contentEquals(file.getName())){
				file.isPresent();
				file.setDCR(DCR);
				j =listePlancheOGC.size();
			}
			else if(file.getException() && listePlancheOGC.get(j).contentEquals(file.getTheoreticalName())){
				file.setDCR(DCR);
				file.setMessage(1);
				j =listePlancheOGC.size();
			}
			else if(file.getName().substring(0,10).compareTo(listePlancheOGC.get(j).substring(0,10)) == 0){
				if(Integer.parseInt(file.getName().substring(14, 16)) != Integer.parseInt(listePlancheOGC.get(j).substring(14, 16))
						||Integer.parseInt(file.getName().substring(11, 13)) != Integer.parseInt(listePlancheOGC.get(j).substring(11, 13))){
					file.setMessage(2);
				}
				file.setDCR("ND");
				erreur = false;
			}
			else if(erreur){
				file.setDCR("ND");
				file.setMessage(0);
			}
		}
	}
	
	private boolean checkOgc(ArrayList<FileToCheck> array, String plOgc){
		boolean exclude = false;
		for(int j=0 ; j < array.size(); j++){
			if(plOgc.contentEquals(array.get(j).getName())
					||plOgc.contentEquals(array.get(j).getTheoreticalName())){
				exclude = true;
			}
		}
		return exclude;
	}
	
	private ArrayList<String[]> getResultData(){
		String DCR = new String();
		ArrayList<String[]> tabArray = new ArrayList<String[]>();
		for (int i = 0; i< listePlancheErrOGC .size(); i++){
			if (listePlancheErrOGC.get(i).length()==4){
				DCR = listePlancheErrOGC.get(i);
			}
			else{
				String[] s = {DCR,	"",	listePlancheErrOGC.get(i),"KO",	"Planche non présente dans le dossier source"};
				tabArray.add(s);
			}
		}
		
		for (int i = 0; i< listePlancheCheck .size(); i++){
			if (listePlancheCheck.get(i).getPresent()){
				String[] s = {listePlancheCheck.get(i).getDCR(), listePlancheCheck.get(i).getName(),	listePlancheCheck.get(i).getName(), "OK",""};
				tabArray.add(s);
			}
			else {
				String[] s = {listePlancheCheck.get(i).getDCR(), listePlancheCheck.get(i).getName(),	"", "KO", listePlancheCheck.get(i).getMessage()};
				tabArray.add(s);
			}
		}
		
		return tabArray;
	}
	
	public ArrayList<String[]> checkingOnUbik(){
		pathOGC = control.getModel().getMainDelivery().getPathOGC();
		pathCSV = control.getModel().getMainDelivery().getPathCSV();
		
		if(pathOGC==null || pathCSV == null){
			new DialogFlat().showDialog("Erreur chargement fichier",
					"Veuillez entrer un fichier OGC et CSV",
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			return null;
		}
		if(pathOGC.isEmpty() || pathCSV.isEmpty()){
			new DialogFlat().showDialog("Erreur chargement fichier",
					"Veuillez entrer un fichier OGC et CSV",
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
			return null;
		}
		
		FichierCSV CSV = new FichierCSV(pathCSV);
		hCSV = CSV.extractPlancheCSV();
		
		FileOGC OGC = new FileOGC(pathOGC);
		hOGC = OGC.extractAllOgcPlank();
		
		for(int i = 0 ; i<hCSV.size(); i++){
			for(int j = 0 ; j<hOGC.size(); j++){
				if (hOGC.get(j).equals(hCSV.get(i))){
					hCSVok.add(hCSV.get(i));
					hOGCok.add(hOGC.get(j));
					hCSV.remove(i);
					hOGC.remove(j);
					i--;
					j = hOGC.size();
				}
			}
		}

		
		if(hCSV.size() == 0 && hOGC.size() == 0){
			new DialogFlat().showDialog( "Vérification terminée",
					"Aucune erreur",
					DialogFlat.INFORMATION_OPERATION,
					DialogFlat.INFORMATION_ICON);
			return null;
		}
		
		ArrayList<String[]> tabArray = new ArrayList<String[]>();
		//test de la présence d'un doublon
		boolean exclusion = false;
		for(int i = 0; i<hOGC.size(); i++){
			for(int j = 0; j<hOGCok.size(); j++){
				if(hOGC.get(i).contentEquals(hOGCok.get(j))){
					String[] s = {hOGC.get(i), "", "Planche en doublon dans le fichier OGC"};
					tabArray.add(s);
					exclusion = true;
					break;
				}
			}
			if(!exclusion){
				String[] s = {hOGC.get(i), "", "Planche non présente dans le fichier CSV"};
				tabArray.add(s);
			}
		}
		
		//test de la présence d'un doublon
		exclusion = false;
		for(int i = 0; i<hCSV.size(); i++){
			for(int j = 0; j<hCSVok.size(); j++){
				if(hCSV.get(i).contentEquals(hCSVok.get(j))){
					String[] s = {hCSV.get(i), "", "Planche en doublon dans le fichier CSV"};
					tabArray.add(s);
					exclusion = true;
					break;
				}
			}
			if(!exclusion){
				String[] s = {"", hCSV.get(i), "Planche non présente dans le fichier OGC"};
				tabArray.add(s);
			}
		}
		return tabArray;
	}
}
