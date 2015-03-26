package view.guiComponents.scrollBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;


@SuppressWarnings("serial")
public class ButtonScrollBar extends JButton {
	
	 /**
     * The direction of the arrow. One of
     * {@code SwingConstants.NORTH}, {@code SwingConstants.SOUTH},
     * {@code SwingConstants.EAST} or {@code SwingConstants.WEST}.
     */
    protected int direction;
	
	private Color selectedBackgroud;
    private Color rollOverBackground;
    
    public ButtonScrollBar(int direction, Color background, Color rollOverBackground,
            Color selectedBackgroud){
    	super();
    	setRequestFocusEnabled(false);
        setDirection(direction);
        setBackground(background);
        this.selectedBackgroud = selectedBackgroud;
        this.rollOverBackground = rollOverBackground;
    }
    
    public void paint(Graphics g) {
        Color origColor;
        boolean isPressed, isRollOver;
        int w, h, size;

        w = getSize().width;
        h = getSize().height;
        origColor = g.getColor();
        isPressed = getModel().isPressed();
        isRollOver = getModel().isRollover();
        
        Color c = null;
        if (isPressed) {
        	c = getBackground();
            g.setColor(selectedBackgroud);
        }else if(isRollOver){
        	c = getBackground();
        	g.setColor(rollOverBackground);
        }else{
        	c = selectedBackgroud;
        	g.setColor(getBackground());
        }
        g.fillRect(0, 0, w, h);

        // If there's no room to draw arrow, bail
        if(h < 5 || w < 5)      {
            g.setColor(origColor);
            return;
        }

        // Draw the arrow
        size = Math.min((h-4)/3, (w-4)/3);
        size = Math.max(size, 2);
        
        paintTriangle(g, (w - size) / 2, (h - size) / 2,
                            size, 3, direction, c);

        g.setColor(origColor);

    }
    
    /**
     * Returns the direction of the arrow.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the arrow.
     *
     * @param direction the direction of the arrow; one of
     *        of {@code SwingConstants.NORTH},
     *        {@code SwingConstants.SOUTH},
     *        {@code SwingConstants.EAST} or {@code SwingConstants.WEST}
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    /**
     * Returns the preferred size of the {@code BasicArrowButton}.
     *
     * @return the preferred size
     */
    public Dimension getPreferredSize() {
        return new Dimension(16, 16);
    }

    /**
     * Returns the minimum size of the {@code BasicArrowButton}.
     *
     * @return the minimum size
     */
    public Dimension getMinimumSize() {
        return new Dimension(5, 5);
    }

    /**
     * Returns the maximum size of the {@code BasicArrowButton}.
     *
     * @return the maximum size
     */
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Returns whether the arrow button should get the focus.
     * {@code BasicArrowButton}s are used as a child component of
     * composite components such as {@code JScrollBar} and
     * {@code JComboBox}. Since the composite component typically gets the
     * focus, this method is overriden to return {@code false}.
     *
     * @return {@code false}
     */
    public boolean isFocusTraversable() {
      return false;
    }
    
    /**
     * Paints a triangle.
     *
     * @param g the {@code Graphics} to draw to
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the triangle to draw
     * @param direction the direction in which to draw the arrow;
     *        one of {@code SwingConstants.NORTH},
     *        {@code SwingConstants.SOUTH}, {@code SwingConstants.EAST} or
     *        {@code SwingConstants.WEST}
     * @param isEnabled whether or not the arrow is drawn enabled
     */
    public void paintTriangle(Graphics g, int x, int y, int size, int lineWidth,
                                    int direction, Color color) {
        int mid, i, j;

        j = 0;
        size = Math.max(size, 2);
        mid = (size-lineWidth) / 2;

        g.translate(x, y);
        g.setColor(color);
        
        switch(direction)       {
        case NORTH:
            for(i = 0; i < size; i++){
            	g.fillOval(mid - i, i - lineWidth/2, lineWidth, lineWidth);
            	g.fillOval(mid + i, i - lineWidth/2, lineWidth, lineWidth);
            }
            break;
        case SOUTH:
            j = 0;
            for(i = size-1; i >= 0; i--){
            	g.fillOval(mid - i, j - lineWidth/2, lineWidth, lineWidth);
            	g.fillOval(mid + i, j - lineWidth/2, lineWidth, lineWidth);
                j++;
            }
            break;
        case WEST:
            for(i = 0; i < size; i++){
            	g.fillOval(i - lineWidth/2, mid - i, lineWidth, lineWidth);
            	g.fillOval(i - lineWidth/2, mid + i, lineWidth, lineWidth);
            }
            break;
        case EAST:
            j = 0;
            for(i = size-1; i >= 0; i--)   {
            	g.fillOval(j - lineWidth/2, mid - i, lineWidth, lineWidth);
            	g.fillOval(j - lineWidth/2, mid + i, lineWidth, lineWidth);
                j++;
            }
            break;
        }
        g.translate(-x, -y);
    }
}
