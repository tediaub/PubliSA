package view.guiComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBarFlatUI extends BasicScrollBarUI{
	
	@Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.translate(thumbBounds.x, thumbBounds.y);
        if(isDragging){
        	g.setColor(new Color(166, 166, 166));
        }else{
        	g.setColor(new Color(205, 205, 205));
        }
        g.fillRect( 0, 0, thumbBounds.width, thumbBounds.height - 1 );
        g.translate( -thumbBounds.x, -thumbBounds.y );
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {   
        g.translate(trackBounds.x, trackBounds.y);
        g.setColor(new Color(240, 240, 240));
        g.fillRect( 0, 0, trackBounds.width, trackBounds.height - 1 );
        g.translate( -trackBounds.x, -trackBounds.y );
    }
    
    @Override
    protected JButton createIncreaseButton(int orientation) {
    	JButton button = new JButton();
 	   button.setPreferredSize(new Dimension(17, 15));
 	   button.setBorder(null);
 	   button.setIcon(new ImageIcon(ScrollBarFlatUI.class.getResource("/iconeScrollBar/scrollDown.png")));
 	   button.setPressedIcon(new ImageIcon(ScrollBarFlatUI.class.getResource("/iconeScrollBar/scrollDownB.png")));
 	   return button;
    }
    
   @Override
	protected JButton createDecreaseButton(int orientation) {
	   JButton button = new JButton();
	   button.setPreferredSize(new Dimension(17, 15));
	   button.setBorder(null);
	   button.setIcon(new ImageIcon(ScrollBarFlatUI.class.getResource("/iconeScrollBar/scrollUp.png")));
	   button.setPressedIcon(new ImageIcon(ScrollBarFlatUI.class.getResource("/iconeScrollBar/scrollUpB.png")));
	   return button;
	}
   
   
  }
