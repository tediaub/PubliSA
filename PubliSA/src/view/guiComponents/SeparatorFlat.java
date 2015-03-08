package view.guiComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SeparatorFlat extends JSeparator {
	
	private Color c;
	private int lineWidth;
	
	public SeparatorFlat() {
		this(new Color(0, 119, 175), 2, SwingConstants.HORIZONTAL);
	}
	
	public SeparatorFlat(Color c) {
		this(c, 2, SwingConstants.HORIZONTAL);
	}
	
	public SeparatorFlat(Color c, int lineWidth) {
		this(c, lineWidth, SwingConstants.HORIZONTAL);
	}

	public SeparatorFlat(Color c, int lineWidth, int orientation) {
		super(orientation);
		this.c = c;
		this.lineWidth = lineWidth;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(c);
		
		if(getOrientation() == SwingConstants.HORIZONTAL){
			g.fillRect(0, 0, getWidth(), lineWidth);
		}else{
			g.fillRect(0, 0, lineWidth, getHeight());
		}
		
	}

}
