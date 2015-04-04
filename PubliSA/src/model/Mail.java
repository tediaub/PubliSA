package model;

import java.io.Serializable;

public class Mail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String adressee;
	private String object;
	private String message;
	
	public Mail(String adressee, String object, String message) {
		this.adressee = adressee;
		this.object = object;
		this.message = message;
	}
	
	public String getRecipient(){
		return adressee;
	}
	
	public String getObject(){
		return object;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setRecipient(String adressee){
		this.adressee = adressee;
	}
	
	public void setObject(String object){
		this.object = object;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
}
