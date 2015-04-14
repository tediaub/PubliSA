package model.files;

import java.io.File;

import view.language.ELabelUI;
import view.language.LanguageSelector;



/**
 */
@SuppressWarnings("serial")
public class FichierPDF extends File{
	
	private String name;
	private String path;
	private String folder;
	
	private String book;
	private String chapter;
	private String plank;
	private String variant;
	private String issue;
	
	private String theoreticalName;
	private String errorMessage = "";
	private String originalErrorMessage;
	private String DCR;

	
	private boolean isPresent;
	private boolean exception;

	
	/**
	 * Constructor for FichierPOS.
	 * @param path String
	 */
	public FichierPDF(String path) {
		super(path);
		isPresent = false;
		this.path = path;
		name = super.getName();
		folder = super.getParentFile().getPath();
		
		if(name.substring(name.indexOf(".")+1, name.length()).toUpperCase().contains("PDF")){

			book = new String(name.substring(1,3));
			chapter = new String(name.substring(4,6));
			plank = new String(name.substring(7,10));
			
			String nameFin;
			nameFin = new String(name.substring(11, name.length()));
			variant = new String(name.substring(11, nameFin.indexOf("I")+11));
			issue = new String(name.substring(nameFin.indexOf("I")+12, name.indexOf(".")));

			
		
	        String message = new String();
	        char car = ' ';
	        try{	        	
	        	if (Integer.parseInt(book)> 9 && Integer.parseInt(book) < 36){
		        	car = (char)(55+Integer.parseInt(book));
		        	book = "0" + car; 
		        	message = "book";
		        	exception = true;
				}
				
				if (Integer.parseInt(chapter) > 9 && Integer.parseInt(chapter) < 36){      
				    if (car != ' '){
				      message = message + ", ";
				    }
					car = (char)(55+Integer.parseInt(chapter));
					chapter = "0" + car;
					message = message + "chapter";
					exception = true;
				}
	
				if (Integer.parseInt(plank) > 9 && Integer.parseInt(plank) < 36){       
				    if (car != ' '){
				      message = message + ", ";
					}
					car = (char)(55+Integer.parseInt(plank));
					plank = "00" + car;
					message = message + "plank";
					exception = true;
				}
				
	        }catch(NumberFormatException e){
	        	theoreticalName = "L" + book + "C" + chapter + "P" + plank + "V" + variant + "I" + issue + ".PDF";
				originalErrorMessage = LanguageSelector.getLocalizedText(ELabelUI.MES_ERR_ORIGINAL1.getLabel()) + message + " " + LanguageSelector.getLocalizedText(ELabelUI.MES_ERR_ORIGINAL2.getLabel()) + " " + theoreticalName;
				return;
	        }
			theoreticalName = "L" + book + "C" + chapter + "P" + plank + "V" + variant + "I" + issue + ".PDF";
			originalErrorMessage = LanguageSelector.getLocalizedText(ELabelUI.MES_ERR_ORIGINAL1.getLabel()) + message + " " + LanguageSelector.getLocalizedText(ELabelUI.MES_ERR_ORIGINAL2.getLabel()) + " " + theoreticalName;
		
		}
	}
	
	/**
	 * Method changepath.
	 * @return String
	 */
	public String changepath(){
		boolean changement = false;
		
		if (name.substring(name.indexOf(".")+1, name.length()).contains(";")){
	        name = name.substring(0, name.indexOf(";"));
	        path = folder + File.separator + name;
	        changement = true;
	    }
		if(name.substring(name.indexOf(".")+1, name.length()).toUpperCase().contains("PDF")){
	        //Vérification des erreurs de name des planks
			if(name.substring(name.indexOf(".")+1, name.length()).contentEquals("pdf")){
				name = name.substring(0, name.lastIndexOf(".")) + ".PDF";
				path = folder + File.separator + name;
				changement = true;
			}
			
			issue = name.substring(name.lastIndexOf("I"), name.lastIndexOf("."));
			if (issue.length() == 2){
				issue = "I0" + issue.charAt(1);
				name = name.substring(0, name.lastIndexOf("I")) + issue + ".PDF";
				path = folder + File.separator + name;
		        changement = true;
			}
		}
		if(changement){
			this.renameTo(new File(path));
		}
		return path;
	}
	
	/**
	 * Method getname.
	 * @return String
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Method getfolder.
	 * @return String
	 */
	public String getFolder(){
		return this.folder;
	}
	
	/**
	 * Method getbook.
	 * @return String
	 */
	public String getBook(){
		return this.book;
	}
	
	/**
	 * Method getchapter.
	 * @return String
	 */
	public String getChapter(){
		return this.chapter;
	}
	
	/**
	 * Method getplank.
	 * @return String
	 */
	public String getPlank(){
		return this.plank;
	}
	
	/**
	 * Method getvariant.
	 * @return String
	 */
	public String getVariant(){
		return this.variant;
	}
	
	/**
	 * Method getIssue.
	 * @return String
	 */
	public String getIssue(){
		return this.issue;
	}
	
	/**
	 * Method getMessage.
	 * @return String
	 */
	public String getMessage(){
		return this.errorMessage;
	}
	
	/**
	 * Method setMessage.
	 * @param indice int
	 */
	public void setMessage(int indice){
		if (indice == 0){
			errorMessage = LanguageSelector.getLocalizedText(ELabelUI.MES_ERR0.getLabel());
		}
		if (indice == 1){
			errorMessage = LanguageSelector.getLocalizedText(ELabelUI.MES_ERR1.getLabel()) + originalErrorMessage;
		}
		if (indice == 2){
			errorMessage = LanguageSelector.getLocalizedText(ELabelUI.MES_ERR2.getLabel());
		}
	}
	
	/**
	 * Method gettheoreticalName.
	 * @return String
	 */
	public String getTheoreticalName(){
		return this.theoreticalName;
	}
	
	/**
	 * Method getException.
	 * @return boolean
	 */
	public boolean getException(){
		return this.exception;
	}
	
	/**
	 * Method getPresent
	 * @return boolean
	 */
	public boolean getPresent(){
		return isPresent;
	}
	
	public void isPresent(){
		isPresent = true;
//		
//		File folder = new File(this.getParentFile().getPath() + File.separator + Delivery.getname());
//		if(!folder.exists()){folder.mkdir();}
//		renameTo(new File(this.getParentFile().getPath() + File.separator + Delivery.getname() + File.separator + this.getName()));
	}
	
	/**
	 * Method getDCR.
	 * @return String
	 */
	public String getDCR(){
		return DCR;
	}

	/**
	 * Method setDCR.
	 * @param DCR String
	 */
	public void setDCR(String DCR){
		this.DCR = DCR;
	}
}