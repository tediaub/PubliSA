package view.guiComponents.table;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import view.guiComponents.ScrollBarFlatUI;

@SuppressWarnings("serial")
public class TableFlat extends JScrollPane {

	private JTable table;

	public TableFlat() {
		intialize(null);
	}

	public TableFlat(TableModel dm) {
		intialize(dm);
	}
	
	public void intialize(TableModel dm){
		if(dm != null){
			table = new JTable(dm);
		}else{
			table = new JTable();
		}
		
		getVerticalScrollBar().setUI(new ScrollBarFlatUI());
		getHorizontalScrollBar().setUI(new ScrollBarFlatUI());
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(211, 211, 211),2));
		setViewportView(table);
		
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		table.setFillsViewportHeight(true);
		
		for (int vColIndex = 0; vColIndex < table.getModel().getColumnCount(); vColIndex++) {
			TableColumn col = table.getColumnModel().getColumn(vColIndex);
			col.setHeaderRenderer(new MyTableHeaderRenderer());
		}
	}
	
	public void addRow(String[] data) {
	     ((DefaultTableModel) table.getModel()).addRow(data);
	}
	
	public void clearTable(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
		//table.setModel(model);
	}

}
