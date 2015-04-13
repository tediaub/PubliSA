package view.guiComponents.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import model.Model;
import controller.ControllerFrame;

@SuppressWarnings("serial")
public class DeliveryTree extends JTree implements Observer, MouseListener{
	
	private ControllerFrame control;

	public DeliveryTree(ControllerFrame control) {
		this.control = control;
		
		setOpaque(false);
		
		setData(control.getModel());
		setCellRenderer(new TreeCellRenderer());
		
		addMouseListener(this);
	}
	
	public void setData(Model model){
		DefaultMutableTreeNode user = new DefaultMutableTreeNode(model.getUser().getName());
		
		for (int i = 0; i < model.getDeliveries().size(); i++) {
			DefaultMutableTreeNode delivery = new DefaultMutableTreeNode(model.getDeliveries().get(i).getName());
			user.add(delivery);
		}
		
		setModel(new DefaultTreeModel(user));
	}

	@Override
	public void update(Observable o, Object arg) {
		Model model = (Model)o;
		setData(model);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent e) {
		int positionX= e.getX();
		int positionY= e.getY();
		
		TreePath tp = getPathForLocation(positionX,positionY);
		if(tp != null){
			String node = tp.getLastPathComponent().toString();
			
		
			if(e.getClickCount() == 2){
				control.openDelivery(node);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
