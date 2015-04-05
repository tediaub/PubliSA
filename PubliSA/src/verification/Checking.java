package verification;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import loading.FilterOGC;
import loading.LoadFile;
import model.Delivery;
import model.files.FichierCSV;
import model.files.FileOGC;
import model.files.FileToCheck;
import controller.ControllerFrame;

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
		
		//Calcul des fichier pr�sents dans l'OGC
		for(int i=0 ; i < listePlancheOGC.size(); i++){
			boolean exclusion = false;
			String plOgc = listePlancheOGC.get(i);
			if(checkOgc(listePlancheCheck, plOgc) == true)exclusion = true;
			if (!exclusion){
				listePlancheErrOGC.add(listePlancheOGC.get(i));
			}
		}
		
		return getResultData();		
	}
	
	private void getOgc(){
		if(control.getModel().getMainDelivery().getDCR().isEmpty()){
			JOptionPane.showMessageDialog(null, "Aucune DCR n'a �t� rentr�e.", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		tabDCR = control.getModel().getMainDelivery().getDCR().split(",");
		DCRMax = "0";
		try{
			for (int i = 0; i<tabDCR.length;i++){
				if(Integer.parseInt(DCRMax)<Integer.parseInt(tabDCR[i])){
					DCRMax = tabDCR[i];
				}
				if(tabDCR[i].length() != 4){
					@SuppressWarnings("unused")
					int k = 1/0;
				}
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "V�rifier que : \r\n- la/les DCR sont des nombres\r\n- elle(s) comporte(nt) quatre chiffres\r\n- il n'y a pas d'espace\r\n- le s�parateur est bien une virgule(,)", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
			return;
		}	
		
		path = LoadFile.loadFrame(control.getModel().getMainDelivery().getPathOGC(), "Ouvrir fichier OGC", new FilterOGC());
		if(path == null){return;}

		control.getModel().getMainDelivery().setPathOGC(path);
	}
	
	private void getListOgc(FileOGC OGC){
		//Message d'erreur
		if(!OGC.getName().endsWith(DCRMax + ".OGC")){
			JOptionPane.showMessageDialog(null, "La DCR la plus grande ne correspond pas avec la DCR du fichier OGC.", "Erreur de fichier OGC", JOptionPane.ERROR_MESSAGE);
			return;
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
				String[] s = {DCR,	"",	listePlancheErrOGC.get(i),"KO",	"Planche non pr�sente dans le dossier source"};
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
			JOptionPane.showMessageDialog(null, "Veuillez entrer un fichier OGC et CSV", "Erreur chargement fichier", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(pathOGC.isEmpty() || pathCSV.isEmpty()){
			JOptionPane.showMessageDialog(null, "Veuillez entrer un fichier OGC et CSV", "Erreur chargement fichier", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Aucune erreur", "V�rification termin�e", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		ArrayList<String[]> tabArray = new ArrayList<String[]>();
		//test de la pr�sence d'un doublon
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
				String[] s = {hOGC.get(i), "", "Planche non pr�sente dans le fichier CSV"};
				tabArray.add(s);
			}
		}
		
		//test de la pr�sence d'un doublon
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
				String[] s = {"", hCSV.get(i), "Planche non pr�sente dans le fichier OGC"};
				tabArray.add(s);
			}
		}
		return tabArray;
	}
}
