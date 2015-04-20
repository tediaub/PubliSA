package model.files;

import java.io.File;

import view.language.ELabelUI;
import view.language.LanguageSelector;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class FileToCheck extends File{

	protected String name;
	protected String extension;
	protected String path;
	protected String folder;
	
	protected String book;
	protected String chapter;
	protected String plank;
	protected String variant;
	protected String outcome;
	
	protected String theoreticalName;
	protected String errorMessage = "";
	protected String originalErrorMessage;
	protected String DCR;
	
	protected boolean isPresent;
	protected boolean exception;
	
	private ControllerFrame control;
	
	public FileToCheck(ControllerFrame control, String path) {
		super(path);
		changeExt();
		
		this.control = control;
		this.path = path;
		this.isPresent = false;
		this.name = super.getName();
		this.folder = super.getParentFile().getPath();
		this.extension = name.substring(name.indexOf("."), name.length());
		
		changePath();
		
		initialize();
	}
	
	public void initialize(){
		book = new String(name.substring(1,3));
		chapter = new String(name.substring(4,6));
		plank = new String(name.substring(7,10));
		
		String nameFin;
		nameFin = new String(name.substring(11, name.length()));
		variant = new String(name.substring(11, nameFin.indexOf("I")+11));
		outcome = new String(name.substring(nameFin.indexOf("I")+12, name.indexOf(".")));

		
	
        String message = new String();
        try{
        	numberTest("Livre", book, "0", message);
        	numberTest("Chapitre", chapter, "0", message);
        	numberTest("Planche", plank, "00", message);				
        }catch(Exception e){

        }finally{
        	theoreticalName = "L" + book + "C" + chapter + "P" + plank + "V" + variant + "I" + outcome + extension;
    		originalErrorMessage = LanguageSelector.getLocalizedText(ELabelUI.MES_ERR_ORIGINAL1.getLabel()) 
    				+ message 
    				+ " " 
    				+ LanguageSelector.getLocalizedText(ELabelUI.MES_ERR_ORIGINAL2.getLabel()) 
    				+ " " 
    				+ theoreticalName;
        }
		
	}
	
	private void numberTest(String type, String number, String prefix, String message){
		char car = ' ';
		if (Integer.parseInt(number)> 9 && Integer.parseInt(number) < 36){
			if (!message.isEmpty()){
				message = message + ", ";
			}
        	car = (char)(55+Integer.parseInt(book));
        	book = prefix + car;
        	message = type;
        	exception = true;
		}
	}
	
	/**
	 * Method changePath.
	 * @return String
	 */
	private void changePath(){
		boolean haveChanged = false;
		
		if (extension.contains(";")){
	        name = name.substring(0, name.indexOf(";"));
	        path = folder + File.separator + name;
	        haveChanged = true;
	    }		
		outcome = name.substring(name.lastIndexOf("I"), name.lastIndexOf("."));
		if (outcome.length() == 2){
			outcome = "I0" + outcome.charAt(1);
			name = name.substring(0, name.lastIndexOf("I")) + outcome + extension;
			path = folder + File.separator + name;
			haveChanged = true;
		}
		if(haveChanged){
			renameTo(new File(path));
		}
	}
	
	/**
	 * Method getname.
	 * @return String
	 */
	public String getName(){
		return this.name;
	}
	
	public String getExtension(){
		return extension;
	}
	
	/**
	 * Method getFolder.
	 * @return String
	 */
	public String getFolder(){
		return this.folder;
	}
	
	/**
	 * Method getBook.
	 * @return String
	 */
	public String getBook(){
		return this.book;
	}
	
	/**
	 * Method getChapter.
	 * @return String
	 */
	public String getChapter(){
		return this.chapter;
	}
	
	/**
	 * Method getPlank.
	 * @return String
	 */
	public String getPlank(){
		return this.plank;
	}
	
	/**
	 * Method getVariant.
	 * @return String
	 */
	public String getVariant(){
		return this.variant;
	}
	
	/**
	 * Method getOutcome.
	 * @return String
	 */
	public String getOutcome(){
		return this.outcome;
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
		
		control.moveFile(this);
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
	
	/**
	 * Method changeExt.
	 * @return String
	 */
	public String changeExt(){
		String path = getPath();
	    if (path.substring(path.lastIndexOf(".")+1, path.length()).contains(";")){
	    	path = path.substring(0, path.lastIndexOf(";"));
	    	renameTo(new File(path));
	    }
	    
	    path = path.substring(0, path.lastIndexOf(".")+1)
	    	+ path.substring(path.lastIndexOf(".")+1, path.length()).toUpperCase();
	    
	    renameTo(new File(path));
	    return (path);
	}
}
