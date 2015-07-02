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
import model.language.ELabelUI;
import model.language.LanguageSelector;
import controller.ControllerFrame;


@SuppressWarnings("serial")
public class ListSelecteable extends JList<String> implements ListSelectionListener, Observer {

	ControllerFrame control;
	/**
	 * Create the application.
	 */
	public ListSelecteable(ControllerFrame control) {
		this.control = control;
		
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
		
		if(model.getMainDelivery() != null){
			if(model.getMainDelivery().getTarget() == Delivery.UBIK){
				listModel.addElement(LanguageSelector.getLocalizedText(ELabelUI.TEXT_FLECHE1_UBIK.getLabel()));
				listModel.addElement(LanguageSelector.getLocalizedText(ELabelUI.TEXT_FLECHE2_UBIK.getLabel()));
				listModel.addElement(LanguageSelector.getLocalizedText(ELabelUI.TEXT_FLECHE3_UBIK.getLabel()));
				listModel.addElement(LanguageSelector.getLocalizedText(ELabelUI.TEXT_FLECHE4_UBIK.getLabel()));
			}
			else{
				listModel.addElement(LanguageSelector.getLocalizedText(ELabelUI.TEXT_FLECHE1_UBIK.getLabel()));
				listModel.addElement(LanguageSelector.getLocalizedText(ELabelUI.TEXT_FLECHE2_UBIK.getLabel()));
				listModel.addElement(LanguageSelector.getLocalizedText(ELabelUI.TEXT_FLECHE3_THALES.getLabel()));
			}
		}
		
		setModel(listModel);
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			Delivery d = control.getModel().getMainDelivery();
			if(d != null){
				if(getSelectedIndex()>=0 && getSelectedIndex() <= d.getHighestStep()){
					d.setActualStep(getSelectedIndex());
				}
				else{
					return;
				}
			}
			control.colSideBarBlue();
	    }
	}

	
	public void update(Observable o, Object arg) {
		Model model = (Model)o;
		setData(model);	
	}
}
