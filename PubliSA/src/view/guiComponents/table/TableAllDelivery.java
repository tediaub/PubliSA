package view.guiComponents.table;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableModel;

import controller.IFrameController;

@SuppressWarnings("serial")
public class TableAllDelivery extends TableFlat implements MouseListener{

	private IFrameController control;
	
	public TableAllDelivery(DefaultTableModel dm, IFrameController control) {
		super(dm);
		this.control = control;
		addMouseListener(this);
	}

	
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			Object obj = getModel().getValueAt(getSelectedRow(), 0);
			if(obj instanceof String){
				control.openDelivery((String) obj);
			}
		}
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
