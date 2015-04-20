package view.guiComponents.layer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JTextField;
import javax.swing.plaf.LayerUI;

import view.language.ELabelUI;
import view.language.LanguageSelector;


@SuppressWarnings("serial")
public class DcrLayerUI extends LayerUI<JTextField> {

	@SuppressWarnings("unchecked")
	@Override
	public void paint (Graphics g, JComponent c) {
		super.paint (g, c);
 
		JLayer<?> jlayer = (JLayer<?>)c;
		JTextField ftf = (JTextField)jlayer.getView();
   
		String text = ftf.getText();
		String[] tabDCR = text.split(",");
		
		if(text.isEmpty()){
			if(ftf.isFocusOwner()){
				return;
			}else{
				drawString(g,c);
				return;
			}
		}
    
		for (int i = 0; i < tabDCR.length; i++) {
			if(tabDCR[i].length() != 4){
				drawCross(g,c);
				return;
			}
			try{
				Integer.parseInt(tabDCR[i]);
			}catch(NumberFormatException e){
				drawCross(g,c);
				return;
			}
		}
		drawCheck(g, c);
	}
  
  	private void drawString(Graphics g, JComponent c) {
  		Graphics2D g2 = (Graphics2D)g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int h = c.getHeight();
		g2.setColor(new Color(140, 140, 140));
		g2.setFont(new Font("Dialog", Font.PLAIN, 12));
		g2.drawString(LanguageSelector.getLocalizedText(ELabelUI.ENTRE_DCR.getLabel()), 5, h - 10);
	}

	private void drawCheck(Graphics g, JComponent c) {
		Graphics2D g2 = (Graphics2D)g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = c.getWidth();
		int h = c.getHeight();
		int s = 10;
		int inset = (h - s) / 2;
		int x = w - inset - s;
		int y = inset;
		g2.setPaint(Color.GREEN.darker());
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(x, y + s/2, x + 3*s/8, y + s);
		g2.drawLine(x + 3*s/8, y + s, x + s, y);
 
		g2.dispose();
	}

	private void drawCross(Graphics g, JComponent c) {
		Graphics2D g2 = (Graphics2D)g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		          RenderingHints.VALUE_ANTIALIAS_ON);
		int w = c.getWidth();
		int h = c.getHeight();
		int s = 10;
		int inset = (h - s) / 2;
		int x = w - inset - s;
		int y = inset;
		g2.setColor(Color.RED.darker());
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(x, y, x + s, y + s);
		g2.drawLine(x, y + s, x + s, y);
		
		g2.setColor(new Color(255, 0, 0, 25));
		g2.fillRect(2, 2, c.getWidth()-4, c.getHeight()-4);
		
		g2.dispose();
	}
}
