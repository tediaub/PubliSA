package myJTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import langue.GestLangue;
import langue.IHM;

@SuppressWarnings("serial")
public class DeliveryTree extends JTree implements MouseListener, TreeSelectionListener, ActionListener {

	private JPopupMenu popUp;
	private JMenuItem supprimer;
	private JMenuItem details;

	public DeliveryTree() {
		addMouseListener(this);
		addTreeSelectionListener(this);
		
		setOpaque(false);
		
		setCellRenderer(new TreeCellRenderer());
		treeModel = new DefaultTreeModel(new DefaultMutableTreeNode(GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel())));
		setModel(treeModel);
		
		popUp = new JPopupMenu();
		supprimer = new JMenuItem();
		details = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.DETAIL_LIV.getLabel()));
		popUp.add(supprimer);
		popUp.add(details);
		supprimer.addActionListener(this);
		details.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
