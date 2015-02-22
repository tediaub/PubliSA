package view.guiComponents.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class MyTableHeaderRenderer extends JLabel implements TableCellRenderer {
	  
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int rowIndex, int vColIndex) {
	    setText(value.toString());
	    setOpaque(true);
	    setHorizontalAlignment(CENTER);
	    
	    setBorder(new LineBorder(new Color(211, 211, 211)));
	    setBackground(Color.white);
	    setForeground(Color.BLACK);
	    
	    setPreferredSize(new Dimension(20, 20));
	    
	    setFont(new Font("Dialog", Font.PLAIN, 12));
	    
	    return this;
	  }
}