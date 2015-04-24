package view.guiComponents.scrollBar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBarFlatUI extends BasicScrollBarUI{
	
	@Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		if(thumbBounds.isEmpty() || !scrollbar.isEnabled()){
			return;
		}
		
		if(isDragging){
        	g.setColor(new Color(166, 166, 166));
        }else{
        	g.setColor(new Color(205, 205, 205));
        }
		
		JScrollBar sb = (JScrollBar) c;		
		if(sb.getOrientation() == JScrollBar.HORIZONTAL){			
			g.translate(thumbBounds.x + 8, thumbBounds.y);
			g.fillRect(0, 0, thumbBounds.width - 17, thumbBounds.height);
	        g.translate( -thumbBounds.x - 8, -thumbBounds.y);
		}else if(sb.getOrientation() == JScrollBar.VERTICAL){
			g.translate(thumbBounds.x + 1, thumbBounds.y + 8);
			g.fillRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 16);
	        g.translate( -thumbBounds.x - 1, -thumbBounds.y - 8);
		}
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
