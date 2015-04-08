package model;

import java.io.Serializable;

public class Mail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String shortTitle;
	
	private String recipient;
	private String object;
	private String message;
	
	public Mail(String shortTitle, String title, String recipient, String object, String message) {
		this.title = title;
		this.shortTitle = shortTitle;
		this.recipient = recipient;
		this.object = object;
		this.message = message;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getShortTitle(){
		return shortTitle;
	}
	
	public String getRecipient(){
		return recipient;
	}
	
	public String getObject(){
		return object;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setRecipient(String recipient){
		this.recipient = recipient;
	}
	
	public void setObject(String object){
		this.object = object;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
}
