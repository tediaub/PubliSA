package view.guiComponents.scrollBar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
        g.fillRect(0, 0, thumbBounds.width, thumbBounds.height);
        g.translate( -thumbBounds.x, -thumbBounds.y );
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {   
        g.translate(trackBounds.x, trackBounds.y);
        g.setColor(new Color(240, 240, 240));
        g.fillRect( 0, 0, trackBounds.width, trackBounds.height);
        g.translate( -trackBounds.x, -trackBounds.y );
    }
    
    @Override
    protected JButton createIncreaseButton(int orientation) {
    	return new ButtonScrollBar(orientation, new Color(240,240,240), new Color(166,166,166), new Color(96, 96, 96));
    }
    
   @Override
	protected JButton createDecreaseButton(int orientation) {
	   return new ButtonScrollBar(orientation, new Color(240,240,240), new Color(166,166,166), new Color(96, 96, 96));
	}
   
   
  }
