package view.guiComponents.list;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Delivery;
import model.Model;
import controller.ControllerFrame;


@SuppressWarnings("serial")
public class ListSelecteable extends JList<String> implements ListSelectionListener, Observer {

	/**
	 * Create the application.
	 */
	public ListSelecteable(ControllerFrame control) {
		
		setFocusable(false);
		setFixedCellHeight(50);
		setSelectionForeground(Color.WHITE);
		setSelectionBackground(new Color(0, 63, 113));
		setBackground(null);
		setForeground(Color.WHITE);
		setOpaque(false);
		setBorder(null);
		addListSelectionListener(this);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		setData(control.getModel());
		
	}
	
	public void setData(Model model){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement("Step1");
		listModel.addElement("Step2");
		listModel.addElement("Step3");
		
		if(model.getMainDelivery().getTarget() == Delivery.UBIK){
			listModel.addElement("Step4");
		}
		setModel(listModel);
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
	    }
	}

	@Override
	public void update(Observable o, Object arg) {
		Model model = (Model)o;
		setData(model);	
	}
}
