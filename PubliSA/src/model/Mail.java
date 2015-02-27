package model;

public class Mail {

	private String recipient;
	private String object;
	private String message;
	
	public Mail(String recipient, String object, String message) {
		this.recipient = recipient;
		this.object = object;
		this.message = message;
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
