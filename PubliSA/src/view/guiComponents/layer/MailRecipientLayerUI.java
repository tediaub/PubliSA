package view.guiComponents.layer;

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
public class MailRecipientLayerUI extends LayerUI<JTextField> {

	@Override
	public void paint (Graphics g, JComponent c) {
		super.paint (g, c);
 
		JLayer<?> jlayer = (JLayer<?>)c;
		JTextField ftf = (JTextField)jlayer.getView();
   
		String text = ftf.getText();
		
		if(text.isEmpty()){
			if(ftf.isFocusOwner()){
				return;
			}else{
				drawString(g,c);
				return;
			}
		}
	}
  
  	private void drawString(Graphics g, JComponent c) {
  		Graphics2D g2 = (Graphics2D)g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int h = c.getHeight();
		g2.setColor(new Color(140, 140, 140));
		g2.setFont(new Font("Arial", Font.PLAIN, 12));
		g2.drawString(LanguageSelector.getLocalizedText(ELabelUI.MAIL_CORRESPONDANT_INDICATION.getLabel()), 5, h - 10);
	}
}
