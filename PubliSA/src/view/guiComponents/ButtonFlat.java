package view.guiComponents;

import java.awt.Color;

import javax.swing.JButton;

import view.guiComponents.ui.FlatButtonUI;

@SuppressWarnings("serial")
public class ButtonFlat extends JButton {
	
	private Color rollOverBackground = null;
	private Color pressedBackground = null;
	
	public ButtonFlat(){
		setUI(new FlatButtonUI());
		setOpaque(true);
	}
	
	public ButtonFlat(String text){
		super(text);
		setUI(new FlatButtonUI());
		setOpaque(true);
	}
	
	public void setRolloverBackground(Color color){
		rollOverBackground = color;
	}
	
	public Color getRolloverBackground(){
		return rollOverBackground;
	}
	
	public void setPressedBackground(Color color){
		pressedBackground = color;
	}
	
	public Color getPressedBackground(){
		return pressedBackground;
	}
}
