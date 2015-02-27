package view.guiComponents.tree;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import langue.GestLangue;
import langue.IHM;
import model.Model;
import myJTree.TreeCellRenderer;
import controller.ControllerFrame;

@SuppressWarnings("serial")
public class DeliveryTree extends JTree implements Observer{

	private JPopupMenu popUp;
	private JMenuItem supprimer;
	private JMenuItem details;

	public DeliveryTree(ControllerFrame control) {
		setOpaque(false);
		
		setData(control.getModel());
		setCellRenderer(new TreeCellRenderer());
		
		popUp = new JPopupMenu();
		supprimer = new JMenuItem();
		details = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.DETAIL_LIV.getLabel()));
		popUp.add(supprimer);
		popUp.add(details);
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
}
