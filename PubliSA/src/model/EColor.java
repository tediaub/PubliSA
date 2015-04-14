package model;

import java.awt.Color;

public enum EColor {

	BLUE(new Color(0,119,175),new Color(0,63,113),new Color(0,0,0)),
	RED(new Color(0,0,0),new Color(0,0,0),new Color(0,0,0)),
	YELLOW(new Color(0,0,0),new Color(0,0,0),new Color(0,0,0)),
	;
	
	private Color main, second, third;
	
	EColor(Color main, Color second, Color third){
		this.main = main;
		this.second = second;
		this.third = third;
	}
	
	public Color getMainColor(){
		return main;
	}
	
	public Color getSecondColor(){
		return second;
	}
	
	public Color getThirdColor(){
		return third;
	}
}
