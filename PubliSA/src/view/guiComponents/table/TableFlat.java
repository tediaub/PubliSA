package view.guiComponents.table;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class TableFlat extends JTable implements MouseListener {
	
	private DefaultTableModel dm = new DefaultTableModel();
	
	public TableFlat(DefaultTableModel dm) {
		super(dm);
		this.dm = dm;
		
		setFillsViewportHeight(true);

		for (int i = 0; i < getColumnCount(); i++) {
			TableColumn tc = getColumnModel().getColumn(i);
			tc.setHeaderRenderer(new MyTableHeaderRenderer());
		}
		addMouseListener(this);
	}
	
	public void addRow(String[] data) {
	     dm.addRow(data);
	}
	
	public void clearTable(){
	    dm.setRowCount(0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
