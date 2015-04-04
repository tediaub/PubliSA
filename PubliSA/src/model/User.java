package model;

import java.io.Serializable;
import java.util.ArrayList;

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
		
		//createBasicMail();
	}
	
//	private void createBasicMail() {
//		mails.add(new Mail(, object, message));
//		mails.add(new Mail(adressee, object, message));
//		mails.add(new Mail(adressee, object, message));
//		mails.add(new Mail(adressee, object, message));
//	}

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
