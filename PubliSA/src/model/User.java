package model;

public class User {
	
	private String name;
	
	private String pathDocEXE = "";
	private String pathDocWord = "";
	
	private boolean deleteFinishDelivery;
	
	public User(String name){
		this.name = name;
	}
	
	public String getname(){
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
	
	public void setname(String name){
		this.name = name;
	}
	
	public void setPathExe(String pathString){
		pathDocEXE = pathString;
	}
	
	public void setPathWord(String pathString){
		pathDocWord = pathString;
	}
	
	public void setDeleteFinishDelivery(boolean deleteFinishDelivery){
		this.deleteFinishDelivery = deleteFinishDelivery;
	}
}
