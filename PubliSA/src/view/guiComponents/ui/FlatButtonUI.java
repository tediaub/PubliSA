package view.guiComponents.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.synth.SynthButtonUI;
import javax.swing.text.View;

import view.guiComponents.ButtonFlat;

public class FlatButtonUI extends SynthButtonUI{

	private static Rectangle viewRect = new Rectangle();
	private static Rectangle textRect = new Rectangle();
	private static Rectangle iconRect = new Rectangle();
	
	public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            g.setColor(c.getBackground());
            g.fillRect(0, 0, c.getWidth(),c.getHeight());
        }
        paint(g, c);
    }
	
	@Override
	public void paint(Graphics g, JComponent c) {
		if(c instanceof ButtonFlat){
			ButtonFlat b = (ButtonFlat) c;
		    ButtonModel model = b.getModel();
		    
		    Color pressedBackground = b.getPressedBackground();
		    Color rollOverBackground = b.getRolloverBackground();
		    
		    String text = layout(b, g.getFontMetrics(b.getFont()), b.getWidth(), b.getHeight());
		    
		    clearTextShiftOffset();
		    
		    if(model.isPressed() && pressedBackground != null){
		    	paintFocus(g, b, pressedBackground);
		    }else if(model.isRollover() && rollOverBackground != null){
		    	paintRolloverButton(g, b, rollOverBackground);
		    }else if(c.isOpaque()){
		    	paintButton(g,b);
		    }
	
		    if(b.getIcon() != null) {
		        paintIcon(g,c,iconRect);
		    }
		    
		    if (text != null && !text.equals("")){
		        View v = (View) c.getClientProperty(BasicHTML.propertyKey);
		        if (v != null) {
		            v.paint(g, textRect);
		        } else {
		        	
		            paintText(g, b, textRect, text);
		        }
		    }
		}
	    
	}
	
	private void paintRolloverButton(Graphics g, AbstractButton b, Color rollOverBackground) {
		g.setColor(rollOverBackground);
		g.fillRect(0, 0, b.getSize().width, b.getSize().height);
	}
	
	private void paintButton(Graphics g, AbstractButton b) {
		g.setColor(b.getBackground());
		g.fillRect(0, 0, b.getSize().width, b.getSize().height);
	}

	protected void paintFocus(Graphics g, AbstractButton b, Color pressedBackground) {
		g.setColor(pressedBackground);
		g.fillRect(0, 0, b.getSize().width, b.getSize().height);
	}
	
	private String layout(AbstractButton b, FontMetrics fm, int width, int height) {
		Insets i = b.getInsets();
		viewRect.x = i.left;
		viewRect.y = i.top;
		viewRect.width = width - (i.right + viewRect.x);
		viewRect.height = height - (i.bottom + viewRect.y);
		
		textRect.x = textRect.y = textRect.width = textRect.height = 0;
		iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
		
		// layout the text and icon
		return SwingUtilities.layoutCompoundLabel(
		  b, fm, b.getText(), b.getIcon(),
		  b.getVerticalAlignment(), b.getHorizontalAlignment(),
		  b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
		  viewRect, iconRect, textRect,
		  b.getText() == null ? 0 : b.getIconTextGap());
	}
}
