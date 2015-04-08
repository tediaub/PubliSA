package view.guiComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;

public class TabbedPaneUI extends BasicTabbedPaneUI {

	private int space = 15;
	
	@Override
	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
	}
	
	@Override
	protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		g.setColor(new Color(220,220,220));
		Graphics2D g2 = (Graphics2D) g;

        g2.fill(new Rectangle2D.Double(x+1, y, 1, h));
	}
	
	@Override
	protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects,
			int tabIndex, Rectangle iconRect, Rectangle textRect) {
		Rectangle tabRect = rects[tabIndex];
		Font font = tabPane.getFont();
		FontMetrics metrics = g.getFontMetrics(font);
		String title = tabPane.getTitleAt(tabIndex);
		Icon icon = getIconForTab(tabIndex);
		
        int x = rects[tabIndex].x;
        int y = rects[tabIndex].y;
        int w = rects[tabIndex].width;
        int h = rects[tabIndex].height;
        
        int selectedIndex = tabPane.getSelectedIndex();
        boolean isSelected = selectedIndex == tabIndex;
        	
        paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);       
		
		layoutLabel(tabPlacement, metrics, tabIndex, title, icon,
                tabRect, iconRect, textRect, isSelected);
		
		Font f = new Font("Arial", Font.BOLD, 12);
		if(tabPane instanceof TabbedPaneFlat){
			if(!((TabbedPaneFlat) tabPane).getLevel(tabIndex)){
				textRect.setLocation(textRect.x + space, textRect.y);
				f = new Font("Arial", Font.PLAIN, 12); 
			}			
		}
		super.paintText(g, tabPlacement, f, getFontMetrics(), tabIndex, tabPane.getTitleAt(tabIndex), textRect,isSelected);
	}
	
	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		if(isSelected){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x, y, w-5, h);
		}
	}
	
	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex,
			FontMetrics metrics){
		Icon icon = getIconForTab(tabIndex);
		Insets insets = getTabInsets(tabPlacement, tabIndex);
		
		int width = insets.left + insets.right + 3;
		if (icon != null)
		  {
		    width += icon.getIconWidth() + textIconGap;
		  }
		
		View v = getTextViewForTab(tabIndex);
		if (v != null)
		  width += v.getPreferredSpan(View.X_AXIS);
		else{
		    String label = tabPane.getTitleAt(tabIndex);
		    width += metrics.stringWidth(label);
		}
		
		if(tabPane instanceof TabbedPaneFlat){
			if(!((TabbedPaneFlat) tabPane).getLevel(tabIndex)){
				width = width + space;
			}			
		}
		
		if(width < 100){
			width = 150;
		}
		return width;
	}
	
	protected void layoutLabel(int tabPlacement,
					            FontMetrics metrics, int tabIndex,
					            String title, Icon icon,
					            Rectangle tabRect, Rectangle iconRect,
					            Rectangle textRect, boolean isSelected ) {
		textRect.x = textRect.y = iconRect.x = iconRect.y = 0;
		
		View v = getTextViewForTab(tabIndex);
		if (v != null) {
		tabPane.putClientProperty("html", v);
		}
		
		SwingUtilities.layoutCompoundLabel(tabPane,
		                        metrics, title, icon,
		                        SwingUtilities.CENTER,
		                        SwingUtilities.LEFT,
		                        SwingUtilities.CENTER,
		                        SwingUtilities.CENTER,
		                        tabRect,
		                        iconRect,
		                        textRect,
		                        textIconGap);
		
		tabPane.putClientProperty("html", null);
		
		int xNudge = getTabLabelShiftX(tabPlacement, tabIndex, isSelected);
		int yNudge = getTabLabelShiftY(tabPlacement, tabIndex, isSelected);
		iconRect.x += xNudge;
		iconRect.y += yNudge;
		textRect.x += xNudge + getTabInsets(tabPlacement, tabIndex).left;
		textRect.y += yNudge;
	}
	
	@Override
	protected int calculateTabHeight(int tabPlacement, int tabIndex,
			int fontHeight) {
		return 25;
	}
}
