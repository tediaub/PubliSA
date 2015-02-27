package fichierPubliSA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 */
@SuppressWarnings("serial")
public class FichierOGC extends File {
	private String nom;
	private String adresse;
	private String répertoire;
	private String DCR;
	
	/**
	 * Constructor for FichierOGC.
	 * @param adresse String
	 */
	public FichierOGC(String adresse) {
		super(adresse);
		this.adresse = adresse;
		this.nom = super.getName();
		this.répertoire = super.getParentFile().getPath() + File.separator;
	}
	
	/**
	 * Method extractPlancheOGC.
	 * @param DCR String
	 * @return ArrayList<String>
	 */
	public ArrayList<String> extractPlancheOGC(String DCR, String cible){
		ArrayList<String> plancheOGC = new ArrayList<String>();
		try {
			InputStream ips = new FileInputStream(this); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			
			while ((ligne = br.readLine())!= null){
				String numeros = new String();
				if(ligne.contains("! " + DCR)){
					ligne = ligne.trim();
		            numeros = "L" + ligne.substring(2, 11);
		            numeros = numeros.replace(".", "P");
		            numeros = numeros.replaceFirst("P", "C");
		            if(ligne.substring(49, 50).equalsIgnoreCase(" ")){
		                numeros = numeros + "V0" + ligne.substring(48, 49);
		            }else{
		            	numeros = numeros + "V" + ligne.substring(48, 50);
					}
					if(ligne.substring(52, 53).equalsIgnoreCase(" ")){
						numeros = numeros + "I0" + ligne.substring(51, 52);
		            }else{                                                  
		            	numeros = numeros + "I" + ligne.substring(51, 53);
					}
					if(cible.contentEquals("Ubik")){
						plancheOGC.add(numeros + ".PDF");
					}
					if(cible.contentEquals("Thales")){
						plancheOGC.add(numeros + ".ASC");
						plancheOGC.add(numeros + ".SCH");
					}
				}
			}
			br.close();
			}		
		catch (Exception e){
			System.err.println(e.toString());
		}
		return plancheOGC;
	}
	
	public ArrayList<String> extractPlancheOGC(){
		ArrayList<String> plancheOGC = new ArrayList<String>();
		try {
			InputStream ips = new FileInputStream(this); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			
			while ((ligne = br.readLine())!= null){
				String numeros = new String();
				ligne = ligne.trim();
	            numeros = "L" + ligne.substring(2, 11);
	            numeros = numeros.replace(".", "P");
	            numeros = numeros.replaceFirst("P", "C");
	            
	            if(ligne.substring(49, 50).equalsIgnoreCase(" ")){
	                numeros = numeros + "V0" + ligne.substring(48, 49);
	            }else{
	            	numeros = numeros + "V" + ligne.substring(48, 50);
				}
				if(ligne.substring(52, 53).equalsIgnoreCase(" ")){
					numeros = numeros + "I0" + ligne.substring(51, 52);
	            }else{                                                  
	            	numeros = numeros + "I" + ligne.substring(51, 53);
				}
				numeros = numeros + ".PDF";
				
				plancheOGC.add(numeros);				
			}
			br.close();
			}		
		catch (Exception e){
			System.err.println(e.toString());
		}
		return plancheOGC;
	}
	
	/**
	 * Method changeExt.
	 * @return String
	 */
	public String changeExt(){		
	    if (nom.substring(nom.indexOf(".")+1, nom.length()).contains(";")){
	        nom = nom.substring(0, nom.indexOf(";"));
	        adresse = répertoire + nom;
	        this.renameTo(new File(adresse));
	    }
	    return (adresse);
	}
	
	/**
	 * Method getNom.
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Method getRepertoire.
	 * @return String
	 */
	public String getRepertoire(){
		return this.répertoire;
	}
	
	/**
	 * Method getAdresse.
	 * @return String
	 */
	public String getAdresse(){
		return this.adresse;
	}
	
	/**
	 * Method getDCR.
	 * @return String
	 */
	public String getDCR(){
		return this.DCR;
	}

}
