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
public class FichierCSV extends File{
	
	/**
	 * Constructor for FichierCSV.
	 * @param adresse String
	 * @author AUBERT_T
	 */
	public FichierCSV(String adresse) {
		super(adresse);
	}
	
	public ArrayList<String> extractPlancheCSV(){
		ArrayList<String> PlancheCSV = new ArrayList<String>();
		
		try {
			InputStream ips = new FileInputStream(this); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;

			while ((ligne = br.readLine())!= null){
				String[] tab;
				String numeros = new String();
				tab = ligne.split(";");
				if(!tab[2].isEmpty()){
			        numeros = "L" + tab[2].trim();
			        numeros = numeros.replace(".", "P");
			        numeros = numeros.replaceFirst("P", "C");
				}

		        if(tab[3].trim().length() == 1){
	                numeros = numeros + "V0" + tab[3].trim();
	            }else{
	            	numeros = numeros + "V" + tab[3].trim();
				}
				if(tab[4].trim().length() == 1){
					numeros = numeros + "I0" + tab[4].trim();
	            }else{                                                  
	            	numeros = numeros + "I" + tab[4].trim();
				}
				numeros = numeros + ".PDF";
				
				PlancheCSV.add(numeros);
			}
			br.close();
			}
		catch (Exception e){
			System.err.println(e.toString());
		}
		
		return PlancheCSV;
	}
}
