package verification;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import myJTable.TableEtape4;

import etape.Etape4;
import fichierPubliSA.FichierCSV;
import fichierPubliSA.FichierOGC;

/**
 */
public class VerificationUbikUbik {
	private ArrayList<String> hCSVok = new ArrayList<String>();
	private ArrayList<String> hOGCok = new ArrayList<String>();
	private ArrayList<String> hCSV = new ArrayList<String>();
	private ArrayList<String> hOGC = new ArrayList<String>();
	
	private String adresseCSV;
	private String adresseOGC;
	
	private static boolean fini;

	public VerificationUbikUbik(){
		fini = false;
		TableEtape4.removeJTable();
		
		adresseOGC = Etape4.getAdresseOGC();
		adresseCSV = Etape4.getAdresseCSV();
		
		if(adresseOGC==null || adresseCSV == null){
			JOptionPane.showMessageDialog(null, "Veuillez entrer un fichier OGC et CSV", "Erreur chargement fichier", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(adresseOGC.isEmpty() || adresseCSV.isEmpty()){
			JOptionPane.showMessageDialog(null, "Veuillez entrer un fichier OGC et CSV", "Erreur chargement fichier", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		FichierCSV CSV = new FichierCSV(adresseCSV);
		hCSV.addAll(CSV.extractPlancheCSV());
		
		FichierOGC OGC = new FichierOGC(adresseOGC);
		hOGC.addAll(OGC.extractPlancheOGC());
		
		System.out.println(hCSV);
		System.out.println(hOGC);
		
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
			JOptionPane.showMessageDialog(null, "Aucune erreur", "Vérification terminée", JOptionPane.INFORMATION_MESSAGE);
			fini = true;
			return;
		}
		
		//test de la présence d'un doublon
		boolean exclusion = false;
		for(int i = 0; i<hOGC.size(); i++){
			for(int j = 0; j<hOGCok.size(); j++){
				if(hOGC.get(i).contentEquals(hOGCok.get(j))){
					TableEtape4.construireTableau(hOGC.get(i), "", "Planche en doublon dans le fichier OGC");
					exclusion = true;
					break;
				}
			}
			if(!exclusion){
				TableEtape4.construireTableau(hOGC.get(i), "", "Planche non présente dans le fichier CSV");
			}
		}
		
		//test de la présence d'un doublon
		exclusion = false;
		for(int i = 0; i<hCSV.size(); i++){
			for(int j = 0; j<hCSVok.size(); j++){
				if(hCSV.get(i).contentEquals(hCSVok.get(j))){
					TableEtape4.construireTableau(hCSV.get(i), "", "Planche en doublon dans le fichier CSV");
					exclusion = true;
					break;
				}
			}
			if(!exclusion){
				TableEtape4.construireTableau("", hCSV.get(i), "Planche non présente dans le fichier OGC");
			}
		}

		fini = true;
	}
	
	public static boolean isFini() {
		return fini;
	}
}