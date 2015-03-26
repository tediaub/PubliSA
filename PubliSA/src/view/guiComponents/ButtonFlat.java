package view.guiComponents;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.synth.SynthButtonUI;
import javax.swing.text.View;

@SuppressWarnings("serial")
public class ButtonFlat extends JButton {

	private static Rectangle viewRect = new Rectangle();
	private static Rectangle textRect = new Rectangle();
	private static Rectangle iconRect = new Rectangle();
	
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
	
	class FlatButtonUI extends SynthButtonUI{

		public void update(Graphics g, JComponent c) {
	        if (c.isOpaque()) {
	            g.setColor(c.getBackground());
	            g.fillRect(0, 0, c.getWidth(),c.getHeight());
	        }
	        paint(g, c);
	    }
		
		@Override
		public void paint(Graphics g, JComponent c) {
		    AbstractButton b = (AbstractButton) c;
		    ButtonModel model = b.getModel();
		    
		    String text = layout(b, g.getFontMetrics(getFont()), b.getWidth(), b.getHeight());
		    
		    clearTextShiftOffset();

		    if(model.isPressed() && pressedBackground != null){
		    	paintFocus(g, b, viewRect, textRect, iconRect);
		    }else if(model.isRollover() && rollOverBackground != null){
		    	paintRolloverButton(g,b);
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

		private void paintRolloverButton(Graphics g, AbstractButton b) {
			g.setColor(rollOverBackground);
			g.fillRect(0, 0, b.getSize().width, b.getSize().height);
		}
		
		private void paintButton(Graphics g, AbstractButton b) {
			g.setColor(getBackground());
			g.fillRect(0, 0, b.getSize().width, b.getSize().height);
		}

		@Override
		protected void paintFocus(Graphics g, AbstractButton b,
				Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
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
}