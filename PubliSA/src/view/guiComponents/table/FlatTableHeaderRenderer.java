package view.guiComponents.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JTable;
import javax.swing.border.LineBorder;

import sun.swing.table.DefaultTableCellHeaderRenderer;

@SuppressWarnings("serial")
public class FlatTableHeaderRenderer extends DefaultTableCellHeaderRenderer implements MouseListener {
	  
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int rowIndex, int vColIndex) {
		
		setText(value.toString());
	    setOpaque(true);
	    setHorizontalAlignment(CENTER);
	    
	    setBorder(new HeaderTableBorder(new Color(211, 211, 211)));
	    
	    setBackground(Color.WHITE);
	    setForeground(Color.BLACK);
	    
	    setPreferredSize(new Dimension(20, 20));
	    
	    setFont(new Font("Arial", Font.PLAIN, 12));
	    
	    return this;
	}
	
	class HeaderTableBorder extends LineBorder{

		public HeaderTableBorder(Color color, int thickness,
				boolean roundedCorners) {
			super(color, thickness, roundedCorners);
			// TODO Auto-generated constructor stub
		}

		public HeaderTableBorder(Color color, int thickness) {
			super(color, thickness);
			// TODO Auto-generated constructor stub
		}

		public HeaderTableBorder(Color color) {
			super(color);
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        if ((this.thickness > 0) && (g instanceof Graphics2D)) {
	            Graphics2D g2d = (Graphics2D) g;

	            Color oldColor = g2d.getColor();
	            g2d.setColor(this.lineColor);

	            Shape outer;
	            Shape inner;

	            int offs = this.thickness;
                outer = new Rectangle2D.Float(x, y, width, height);
                inner = new Rectangle2D.Float(x, y, width - offs, height - offs);

	            Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
	            path.append(outer, false);
	            path.append(inner, false);
	            g2d.fill(path);
	            g2d.setColor(oldColor);
	        }
	    }
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("test");		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}