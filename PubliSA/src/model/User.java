package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.language.ELabelUI;
import model.language.LanguageSelector;



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
	
	private int frameWidth = 1000;
	private int frameHeight = 650;
	
	public User(String name, Model model){
		this.name = name;
		this.model = model;
		
		createBasicMail();
	}
	
	public void createBasicMail() {
		addMail("Email AKKA pour Ubik",
				LanguageSelector.getLocalizedText(ELabelUI.MAIL_AKKA_U.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.AKKA_UBIK_RECIPIENT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.AKKA_UBIK_OBJECT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.AKKA_UBIK_MESSAGE.getLabel()));
		
		addMail("Email AKKA pour Thales",
				LanguageSelector.getLocalizedText(ELabelUI.MAIL_AKKA_T.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.AKKA_THALES_RECIPIENT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.AKKA_THALES_OBJECT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.AKKA_THALES_MESSAGE.getLabel()));
		
		addMail("Email SOPRA",
				LanguageSelector.getLocalizedText(ELabelUI.MAIL_SOPRA.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.SOPRA_RECIPIENT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.SOPRA_OBJECT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.SOPRA_MESSAGE.getLabel()));
		
		addMail("Email THALES",
				LanguageSelector.getLocalizedText(ELabelUI.MAIL_THALES.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.THALES_RECIPIENT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.THALES_OBJECT.getLabel()),
				LanguageSelector.getLocalizedText(ELabelUI.THALES_MESSAGE.getLabel()));
	}
	
	public void addMail(String shortName, String name, String recipient, String object, String message){
		mails.add(new Mail(shortName, name, recipient, object, message));
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

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getFrameHeight() {
		return frameHeight;
	}
}
