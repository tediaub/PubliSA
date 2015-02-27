package view.guiComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextFieldFlat extends JTextField {

	int borderWidth = 2;
	
	public TextFieldFlat() {
		
	}
	
	public TextFieldFlat(String text) {
		super(text);
	}
	
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		
		if(hasFocus()){
			g.setColor(new Color(0, 119, 175));
		}else{
			g.setColor(new Color(211, 211, 211));
		}
		
		int x = 0, y = 0, width = this.getWidth()-1, height = this.getHeight()-1;
		for (int i = 0; i < borderWidth; i++) {
			g.drawRect(x, y, width, height);
			x++;
			y++;
			width = width - 2;
			height = height - 2;
		}
	}
}
