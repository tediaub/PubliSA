package verification;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import loading.FilterOGC;
import loading.LoadFile;
import model.Delivery;
import model.files.FileOGC;
import model.files.FichierPDF;
import view.guiComponents.table.TableEtape2;
import etape.Etape2;

/**
 */
public class AkkaToUbikChecking {
	
	private static boolean finish;
	private String nomOGC = new String();
	private String txDCR= new String();
	private String DCRMax = "0";
	private String[] tabDCR;
	private ArrayList<String> listePlancheOGC = new ArrayList<String> ();
	private ArrayList<FichierPDF> listePlanchePDF = new ArrayList<FichierPDF> ();
	private ArrayList<String> listePlancheErrOGC = new ArrayList<String> ();
	
	public static String adresseOGC;
	public static FileOGC OGC;
	
	public AkkaToUbikChecking(){
		finish = false;
		
		//vérifie la présence de toute les bonne planche pour une livraison à Thales
		TableEtape2.removeJTable();
		
		txDCR = Etape2.getDCR();
		tabDCR = txDCR.split(",");
		
		if(txDCR.isEmpty()){
			JOptionPane.showMessageDialog(null, "Aucune DCR n'a été rentrée.", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try{
			for (int i =0; i<tabDCR.length;i++){
				if(Integer.parseInt(DCRMax)<Integer.parseInt(tabDCR[i])){
					DCRMax = tabDCR[i];
				}
				if(tabDCR[i].length() != 4){
					@SuppressWarnings("unused")
					int k = 1/0;
				}
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Vérifier que : \r\n- la/les DCR sont des nombres\r\n- elle(s) comporte(nt) quatre chiffres\r\n- il n'y a pas d'espace\r\n- le séparateur est bien une virgule(,)", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
			return;
		}	
		
		adresseOGC = new LoadFile("Ouvrir").ChargementFich(Delivery.getOGC(), new FilterOGC());
		if(adresseOGC == null){return;}
		OGC = new FileOGC(adresseOGC);
		 
		nomOGC = OGC.getName();
		
		//Message d'erreur
		if(!nomOGC.endsWith(DCRMax + ".OGC")){
			JOptionPane.showMessageDialog(null, "La DCR la plus grande ne correspond pas avec la DCR du fichier OGC.", "Erreur de fichier OGC", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		for(int i=0; i<tabDCR.length;i++){
			listePlancheOGC.add(tabDCR[i]);
			listePlancheOGC.addAll(OGC.extractPlancheOGC(tabDCR[i], Delivery.getCible()));
		}
		
		this.verificationPrincipale();
		this.affichage();
		
		Etape2.lblNombreDeFichiersSom.setText(Etape2.lblNombreDeFichiersSom.getText() + (listePlancheOGC.size()- tabDCR.length));
		Etape2.lblNombreDeFichiersDos.setText(Etape2.lblNombreDeFichiersDos.getText() + (listePlanchePDF.size()));
		
		finish = true;
	}
	
	private void verificationPrincipale(){
		String DCR = new String();
		
		////Dossier du fichier OGC
		File directory = new File(OGC.getRepertoire());
		File[] subfiles = directory.listFiles();
		FichierPDF PDF;
		
		for(int i = 0 ; i < subfiles.length; i++){
			if(subfiles[i].getName().substring(subfiles[i].getName().indexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("PDF")){
				PDF = new FichierPDF(subfiles[i].getPath());
				PDF = new FichierPDF(PDF.changeAdresse());
				
				listePlanchePDF.add(PDF);
				boolean erreur = true;
				
				for(int j=0; j<listePlancheOGC.size();j++){
					if(listePlancheOGC.get(j).length()==4){
						do{
							DCR = listePlancheOGC.get(j);
							j++;
						}while(listePlancheOGC.get(j).length()==4);
					}
					if(listePlancheOGC.get(j).substring(listePlancheOGC.get(j).indexOf(".")+1, listePlancheOGC.get(j).length()).toUpperCase().contains("PDF")){
						if(listePlancheOGC.get(j).contentEquals(PDF.getNom())){
							PDF.isPresent();
							PDF.setDCR(DCR);
							j =listePlancheOGC.size();
						}
						else if(PDF.getException() && listePlancheOGC.get(j).contentEquals(PDF.getNomTheorique())){
							PDF.setDCR(DCR);
							PDF.setMessage(1);
							j =listePlancheOGC.size();
						}
						else if(PDF.getNom().substring(0,10).compareTo(listePlancheOGC.get(j).substring(0,10)) == 0){
							if(Integer.parseInt(PDF.getNom().substring(14, 16)) != Integer.parseInt(listePlancheOGC.get(j).substring(14, 16))
									||Integer.parseInt(PDF.getNom().substring(11, 13)) != Integer.parseInt(listePlancheOGC.get(j).substring(11, 13))){
								PDF.setMessage(2);
							}
							PDF.setDCR("ND");
							erreur = false;
						}
						else if(erreur){
							PDF.setDCR("ND");
							PDF.setMessage(0);
						}
					}
				}
			}
		}
		
		//Calcul des fichier présents dans l'OGC
		for(int i=0 ; i < listePlancheOGC.size(); i++){
			boolean exclusion = false;
			for(int j=0 ; j < listePlanchePDF.size(); j++){
				
				if(listePlancheOGC.get(i).contentEquals(listePlanchePDF.get(j).getNom())
						||listePlancheOGC.get(i).contentEquals(listePlanchePDF.get(j).getNomTheorique())){
					exclusion = true;
				}
			}
			if (!exclusion){
				listePlancheErrOGC.add(listePlancheOGC.get(i));
			}
		}
	}
	
	private void affichage(){
		String DCR = new String();

		for (int i = 0; i< listePlancheErrOGC .size(); i++){
			if (listePlancheErrOGC.get(i).length()==4){
				DCR = listePlancheErrOGC.get(i);
			}
			else{
				TableEtape2.construireTableau(
						DCR,
						"",
						listePlancheErrOGC.get(i),
						"KO",
						"Planche non présente dans le dossier source");
			}
		}
		
		for (int i = 0; i< listePlanchePDF .size(); i++){
			if (listePlanchePDF.get(i).getPresent()){
				TableEtape2.construireTableau(
						listePlanchePDF.get(i).getDCR(),
						listePlanchePDF.get(i).getNom(),
						listePlanchePDF.get(i).getNom(),
						"OK",
						"");
			}
			else {
				TableEtape2.construireTableau(
						listePlanchePDF.get(i).getDCR(),
						listePlanchePDF.get(i).getNom(), 
						"",
						"KO",
						listePlanchePDF.get(i).getMessage());
			}
		}
	}
	
	public static boolean isFinish(){
		return finish;
	}
}
