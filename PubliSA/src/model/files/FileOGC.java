package model.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 */
@SuppressWarnings("serial")
public class FileOGC extends File {
	
	private String DCR;
	
	/**
	 * Constructor for FichierOGC.
	 * @param adresse String
	 */
	public FileOGC(String path){
		super(path);
		changeExt(path);
	}
	
	/**
	 * Method extractPlancheOGC.
	 * @param DCR String
	 * @return ArrayList<String>
	 */
	public ArrayList<String> extractOgcPlank(String DCR, String cible){
		ArrayList<String> plankOGC = new ArrayList<String>();
		try {
			InputStream ips = new FileInputStream(this); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			
			while ((line = br.readLine())!= null){
				String numbers = new String();
				if(line.contains("! " + DCR)){
					numbers = getPlankName(line);
					if(cible.contentEquals("Ubik")){
						plankOGC.add(numbers + ".PDF");
					}
					if(cible.contentEquals("Thales")){
						plankOGC.add(numbers + ".ASC");
						plankOGC.add(numbers + ".SCH");
					}
				}
			}
			br.close();
			}		
		catch (Exception e){
			System.err.println(e.toString());
		}
		return plankOGC;
	}
	
	public ArrayList<String> extractAllOgcPlank(){
		ArrayList<String> plankOGC = new ArrayList<String>();
		try {
			InputStream ips = new FileInputStream(this); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			
			while ((line = br.readLine())!= null){
				String numbers = getPlankName(line);
				numbers = numbers + ".PDF";
				
				plankOGC.add(numbers);				
			}
			br.close();
			}		
		catch (Exception e){
			System.err.println(e.toString());
		}
		return plankOGC;
	}
	
	public String getPlankName(String line){
		String numbers = new String();
		line = line.trim();
		numbers = "L" + line.substring(2, 11);
		numbers = numbers.replace(".", "P");
		numbers = numbers.replaceFirst("P", "C");
        if(line.substring(49, 50).equalsIgnoreCase(" ")){
        	numbers = numbers + "V0" + line.substring(48, 49);
        }else{
        	numbers = numbers + "V" + line.substring(48, 50);
		}
		if(line.substring(52, 53).equalsIgnoreCase(" ")){
			numbers = numbers + "I0" + line.substring(51, 52);
        }else{                                                  
        	numbers = numbers + "I" + line.substring(51, 53);
		}
		return numbers;
	}
	
	/**
	 * Method changeExt.
	 * @return String
	 */
	public String changeExt(String path){		
	    if (path.substring(path.lastIndexOf(".")+1, path.length()).contains(";")){
	    	path = path.substring(0, path.lastIndexOf(";"));
	    	renameTo(new File(path));
	    }
	    return (path);
	}
	
	/**
	 * Method getDCR.
	 * @return String
	 */
	public String getDCR(){
		return this.DCR;
	}

}
