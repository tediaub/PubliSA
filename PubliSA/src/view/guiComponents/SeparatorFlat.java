package view.guiComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSeparator;

public class SeparatorFlat extends JSeparator {

	public SeparatorFlat() {
		// TODO Auto-generated constructor stub
	}

	public SeparatorFlat(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0, 119, 175));
		g.fillRect(0, 0, getWidth(), 2);
	}

}
