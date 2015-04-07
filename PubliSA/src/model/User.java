package model;

import java.io.Serializable;
import java.util.ArrayList;

import langue.GestLangue;
import langue.IHM;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String pathDocEXE = "";
	private String pathDocWord = "";
	
	private boolean deleteFinishDelivery = false;

	private Model model;
	
	private ArrayList<Mail> mails = new ArrayList<Mail>();
	
	public User(String name, Model model){
		this.name = name;
		this.model = model;
		
		createBasicMail();
	}
	
	public void createBasicMail() {
		addMail("AKKA pour liv. Ubik",
				GestLangue.getLocalizedText(IHM.AKKA_UBIK_RECIPIENT.getLabel()),
				GestLangue.getLocalizedText(IHM.AKKA_UBIK_OBJECT.getLabel()),
				GestLangue.getLocalizedText(IHM.AKKA_UBIK_MESSAGE.getLabel()));
		
		addMail("AKKA pour liv. Thales",
				GestLangue.getLocalizedText(IHM.AKKA_THALES_RECIPIENT.getLabel()),
				GestLangue.getLocalizedText(IHM.AKKA_THALES_OBJECT.getLabel()),
				GestLangue.getLocalizedText(IHM.AKKA_THALES_MESSAGE.getLabel()));
		
		addMail("SOPRA",
				GestLangue.getLocalizedText(IHM.SOPRA_RECIPIENT.getLabel()),
				GestLangue.getLocalizedText(IHM.SOPRA_OBJECT.getLabel()),
				GestLangue.getLocalizedText(IHM.SOPRA_MESSAGE.getLabel()));
		
		addMail("THALES",
				GestLangue.getLocalizedText(IHM.THALES_RECIPIENT.getLabel()),
				GestLangue.getLocalizedText(IHM.THALES_OBJECT.getLabel()),
				GestLangue.getLocalizedText(IHM.THALES_MESSAGE.getLabel()));
	}
	
	public void addMail(String name, String recipient, String object, String message){
		mails.add(new Mail(name, recipient, object, message));
	}
	
	public ArrayList<Mail> getMails(){
		return mails;
	}

	public String getName(){
		return name;
	}
	
	public String getPathExe(){
		return pathDocEXE;
	}
	
	public String getPathWord(){
		return pathDocWord;
	}
	
	public boolean getDeleteFinishDelivery(){
		return deleteFinishDelivery;
	}
	
	public void setName(String name){
		this.name = name;
		model.notice();
	}
	
	public void setPathExe(String pathString){
		pathDocEXE = pathString;
		model.notice();
	}
	
	public void setPathWord(String pathString){
		pathDocWord = pathString;
		model.notice();
	}
	
	public void setDeleteFinishDelivery(boolean deleteFinishDelivery){
		this.deleteFinishDelivery = deleteFinishDelivery;
		model.notice();
	}
}
