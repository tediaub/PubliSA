package enumeration;

import java.awt.Color;

public enum ColorApp {
	BLUE(0x0077AF,0x000000,0xFFFFFF);
   
	private Color mainColor;
	private Color secondColor;
	private Color thirdColor;
	   
	//Constructeur
	ColorApp(int c1, int c2, int c3){
		this.mainColor = new Color(c1);
	    this.secondColor = new Color(c2);
	    this.thirdColor = new Color(c3);
	}
	  
	public Color getMainColor(){
		return mainColor;
	}
	  
	public Color getSecondColor(){
		return secondColor;
	}
	  
	public Color getThirdColor(){
		return thirdColor;
	}
}