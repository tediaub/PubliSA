package view.guiComponents.table;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableModel;

import controller.openFrame.OpeningController;

@SuppressWarnings("serial")
public class TableAllDelivery extends TableFlat implements MouseListener{

	private OpeningController control;
	
	public TableAllDelivery(DefaultTableModel dm, OpeningController control) {
		super(dm);
		this.control = control;
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			Object obj = getModel().getValueAt(getSelectedRow(), 0);
			if(obj instanceof String){
				control.openDelivery((String) obj);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
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