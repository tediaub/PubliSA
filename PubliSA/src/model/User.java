package model;

public class User{
	
	private String name;
	
	private String pathDocEXE = "";
	private String pathDocWord = "";
	
	private boolean deleteFinishDelivery = false;

	private Model model;
	
	public User(String name, Model model){
		this.name = name;
		this.model = model;
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
