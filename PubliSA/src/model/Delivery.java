package model;

public class Delivery {
	
	private String name;
	
	private int target;
	public static final int SOPRA = 0;
	public static final int THALES = 1;
	
	private int actualStep = 0;
	private int highestStep = 0;
	public static final int STEP1 = 0;
	public static final int STEP2 = 1;
	public static final int STEP3 = 2;
	public static final int STEP4 = 3;
	
	private String dcr;
	private String computer;
	private String standard;
	
	private String pathOGC;
	
	public Delivery(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getTarget(){
		return target;
	}
	
	public void setTarget(int target){
		this.target = target;
	}
	
	public int getActualStep(){
		return actualStep;
	}
	
	public void setActualStep(int actualStep){
		if(actualStep>highestStep){
			highestStep = actualStep;
		}
		this.actualStep = actualStep;
	}
	
	public int getHighestStep(){
		return highestStep;
	}
	
	public String getDCR(){
		return dcr;
	}
	
	public void setDCR(String DCR) {
		dcr = DCR;
	}
	
	public String getComputer(){
		return computer;
	}
	
	public void setComputer(String computer) {
		this.computer = computer;
	}
	
	public String getStandard(){
		return standard;
	}
	
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	public String getPathOGC(){
		return pathOGC;
	}
	
	public void setPathOGC(String OGC){
		pathOGC = OGC;
	}
}