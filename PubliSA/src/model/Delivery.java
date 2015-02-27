package model;

public class Delivery{
	
	private String name;
	
	private int target;
	public static final int UBIK = 0;
	public static final int THALES = 1;
	
	public boolean main = false;
	
	private int actualStep;
	private int highestStep;
	public static final int STEP1 = 0;
	public static final int STEP2 = 1;
	public static final int STEP3 = 2;
	public static final int STEP4 = 3;
	
	private String dcr = "";
	private String computer = "";
	private String standard = "";
	
	private String pathOGC;

	private Model model;
	
	public Delivery(String name, int target, Model model){
		this.name = name;
		this.target = target;
		this.model = model;
		actualStep = Delivery.STEP1;
		highestStep = Delivery.STEP1;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
		model.notice();
	}
	
	public boolean isMain(){
		return main;
	}
	
	public void setMain(boolean b){
		main = b;
		model.notice();
	}
	
	public int getTarget(){
		return target;
	}
	
	public void setTarget(int target){
		this.target = target;
		model.notice();
	}
	
	public int getActualStep(){
		return actualStep;
	}
	
	public void setActualStep(int actualStep){
		if(actualStep>highestStep){
			highestStep = actualStep;
		}
		this.actualStep = actualStep;
		model.notice();
	}
	
	public int getHighestStep(){
		return highestStep;
	}
	
	public String getDCR(){
		return dcr;
	}
	
	public void setDCR(String DCR) {
		dcr = DCR;
		model.notice();
	}
	
	public String getComputer(){
		return computer;
	}
	
	public void setComputer(String computer) {
		this.computer = computer;
		model.notice();
	}
	
	public String getStandard(){
		return standard;
	}
	
	public void setStandard(String standard) {
		this.standard = standard;
		model.notice();
	}
	
	public String getPathOGC(){
		return pathOGC;
	}
	
	public void setPathOGC(String OGC){
		pathOGC = OGC;
		model.notice();
	}
}