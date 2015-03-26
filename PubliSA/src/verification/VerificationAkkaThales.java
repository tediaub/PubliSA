package verification;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import loading.FilterOGC;
import loading.LoadFile;
import model.Delivery;
import model.files.FichierASC;
import model.files.FileOGC;
import model.files.FichierSCH;
import view.guiComponents.table.TableEtape2;
import etape.Etape2;

/**
 */
public class VerificationAkkaThales {
	
	private static boolean fini;
	private String nomOGC = new String();
	private String txDCR= new String();
	private String DCRMax= "0";
	private String[] tabDCR;
	public static FileOGC OGC;
	private ArrayList<String> listePlancheOGC = new ArrayList<String> ();
	private ArrayList<FichierASC> listePlancheASC = new ArrayList<FichierASC> ();
	private ArrayList<FichierSCH> listePlancheSCH = new ArrayList<FichierSCH> ();
	private ArrayList<String> listePlancheErrOGC = new ArrayList<String> ();
	public static String adresseOGC;

	public VerificationAkkaThales(){
		fini = false;
		//vérifie la présence de toute les bonnes planches pour une livraison à Thales
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
		OGC = new FileOGC(OGC.changeExt());
		 
		nomOGC = OGC.getNom();
			
		///Message d'erreur
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
		Etape2.lblNombreDeFichiersDos.setText(Etape2.lblNombreDeFichiersDos.getText() + (listePlancheASC.size() + listePlancheSCH.size()));
		
		transferFichier();
		
		fini = true;
	}
	
	private void verificationPrincipale(){
		String DCR = new String();
		
		////Dossier du fichier OGC
		File directory = new File(OGC.getRepertoire());
		File[] subfiles = directory.listFiles();
		FichierASC ASC;
		FichierSCH SCH;

		for(int i=0 ; i < subfiles.length; i++){
			if(subfiles[i].getName().substring(subfiles[i].getName().indexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("ASC")){
				ASC = new FichierASC(subfiles[i].getPath());
				ASC = new FichierASC(ASC.changeAdresse());
				
				listePlancheASC.add(ASC);
				boolean erreur = true;
				
				for(int j=0; j<listePlancheOGC.size();j++){
					if(listePlancheOGC.get(j).length()==4){
						do{
							DCR = listePlancheOGC.get(j);
							j++;
						}while(listePlancheOGC.get(j).length()==4);
					}
					if(listePlancheOGC.get(j).substring(listePlancheOGC.get(j).indexOf(".")+1, listePlancheOGC.get(j).length()).toUpperCase().contains("ASC")){
						if(listePlancheOGC.get(j).contentEquals(ASC.getNom())){
							ASC.isPresent();
							ASC.setDCR(DCR);
							j =listePlancheOGC.size();
						}
						else if(ASC.getException() && listePlancheOGC.get(j).contentEquals(ASC.getNomTheorique())){
							ASC.setDCR(DCR);
							ASC.setMessage(1);
							j =listePlancheOGC.size();
						}
						else if(ASC.getNom().substring(0,10).compareTo(listePlancheOGC.get(j).substring(0,10)) == 0){
							if(Integer.parseInt(ASC.getNom().substring(14, 16)) != Integer.parseInt(listePlancheOGC.get(j).substring(14, 16))
									||Integer.parseInt(ASC.getNom().substring(11, 13)) != Integer.parseInt(listePlancheOGC.get(j).substring(11, 13))){
								ASC.setMessage(2);
							}
							ASC.setDCR("ND");
							erreur = false;
						}
						else if(erreur){
							ASC.setDCR("ND");
							ASC.setMessage(0);
						}
					}
				}
			}
			
			if(subfiles[i].getName().substring(subfiles[i].getName().indexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("SCH")){
				SCH = new FichierSCH(subfiles[i].getPath());
				SCH = new FichierSCH(SCH.changeAdresse());
				
				listePlancheSCH.add(SCH);
				boolean erreur = true;
				
				for(int j=0; j<listePlancheOGC.size();j++){
					if(listePlancheOGC.get(j).length()==4){
						do{
							DCR = listePlancheOGC.get(j);
							j++;
						}while(listePlancheOGC.get(j).length()==4);
					}
					if(listePlancheOGC.get(j).substring(listePlancheOGC.get(j).indexOf(".")+1, listePlancheOGC.get(j).length()).toUpperCase().contains("SCH")){
						if(listePlancheOGC.get(j).contentEquals(SCH.getNom())){
							SCH.isPresent();
							SCH.setDCR(DCR);
							j =listePlancheOGC.size();
						}
						else if(SCH.getException() && listePlancheOGC.get(j).contentEquals(SCH.getNomTheorique())){
							SCH.setDCR(DCR);
							SCH.setMessage(1);
							j =listePlancheOGC.size();
						}
						else if(SCH.getNom().substring(0,10).compareTo(listePlancheOGC.get(j).substring(0,10)) == 0){
							if(Integer.parseInt(SCH.getNom().substring(14, 16)) != Integer.parseInt(listePlancheOGC.get(j).substring(14, 16))
									||Integer.parseInt(SCH.getNom().substring(11, 13)) != Integer.parseInt(listePlancheOGC.get(j).substring(11, 13))){
								SCH.setMessage(2);
							}
							SCH.setDCR("ND");
							erreur = false;
						}
						else if(erreur){
							SCH.setDCR("ND");
							SCH.setMessage(0);
						}
					}
				}
			}
		}
		//Calcul des fichier présents dans l'OGC
		
		for(int i=0 ; i < listePlancheOGC.size(); i++){
			boolean exclusion = false;
			for(int j=0 ; j < listePlancheASC.size(); j++){
				if(listePlancheOGC.get(i).contentEquals(listePlancheASC.get(j).getNom())
						||listePlancheOGC.get(i).contentEquals(listePlancheASC.get(j).getNomTheorique())){
					exclusion = true;
				}
			}
			for(int j=0 ; j < listePlancheSCH.size(); j++){
				if(listePlancheOGC.get(i).contentEquals(listePlancheSCH.get(j).getNom())
						||listePlancheOGC.get(i).contentEquals(listePlancheSCH.get(j).getNomTheorique())){
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
		
		for (int i = 0; i< listePlancheASC .size(); i++){
			if (listePlancheASC.get(i).getPresent()){
				TableEtape2.construireTableau(
						listePlancheASC.get(i).getDCR(),
						listePlancheASC.get(i).getNom(),
						listePlancheASC.get(i).getNom(),
						"OK",
						"");
			}
			else if (!listePlancheASC.get(i).getPresent()){
				TableEtape2.construireTableau(
						listePlancheASC.get(i).getDCR(),
						listePlancheASC.get(i).getNom(), 
						"",
						"KO",
						listePlancheASC.get(i).getMessage());
			}
		}
		
		for (int j = 0; j< listePlancheSCH .size(); j++){
			if (listePlancheSCH.get(j).getPresent()){
				TableEtape2.construireTableau(
						listePlancheSCH.get(j).getDCR(),
						listePlancheSCH.get(j).getNom(),
						listePlancheSCH.get(j).getNom(),
						"OK",
						"");
			}
			else if (!listePlancheSCH.get(j).getPresent()){
				TableEtape2.construireTableau(
						listePlancheSCH.get(j).getDCR(),
						listePlancheSCH.get(j).getNom(), 
						"",
						"KO",
						listePlancheSCH.get(j).getMessage());
			}
		}
	}
	
	public void transferFichier(){
	////Dossier du fichier OGC
		File directory = new File(OGC.getRepertoire());
		File[] subfiles = directory.listFiles();
		
		for(int i=0 ; i < subfiles.length; i++){
			if(subfiles[i].getName().substring(subfiles[i].getName().lastIndexOf(".")+1, subfiles[i].getName().length()).toUpperCase().contains("OGC")){
				File OGC = subfiles[i];
				File repertoire = new File(OGC.getParentFile().getPath() + File.separator + Delivery.getNom());
				if(!repertoire.exists()){repertoire.mkdir();}
				OGC.renameTo(new File(OGC.getParentFile().getPath() + File.separator + Delivery.getNom() + File.separator + OGC.getName()));
			}
		}
	}

	public static boolean isFini() {
		return fini;
	}
}