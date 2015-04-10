package view.guiComponents.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class TableFlat extends JTable {
	
	private DefaultTableModel dm = new DefaultTableModel();
	
	public TableFlat(DefaultTableModel dm) {
		super(dm);
		
		this.dm = dm;
		
		setFillsViewportHeight(true);

		for (int i = 0; i < getColumnCount(); i++) {
			TableColumn tc = getColumnModel().getColumn(i);
			tc.setHeaderRenderer(new FlatTableHeaderRenderer());
		}
	}
	
	public void addRow(String[] data) {
	     dm.addRow(data);
	}
	
	public void clearTable(){
	    dm.setRowCount(0);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public void setWidth(int column, int width){
		TableColumn col;
		col = getColumnModel().getColumn(column);
		col.setPreferredWidth(width);
	}
}
