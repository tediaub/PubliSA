package view.guiComponents.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import view.language.ELabelUI;
import view.language.LanguageSelector;

import model.Delivery;
import controller.ControllerFrame;

	
@SuppressWarnings("serial")
public class TableCellRenderer extends DefaultTableCellRenderer {

	private int nbCol;
	private ControllerFrame control;
	
	public TableCellRenderer(ControllerFrame control){
		this.control = control;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column){
		
		if(control.getModel().getMainDelivery().getActualStep() == Delivery.STEP2){
			nbCol = 4;
		}
		else if(control.getModel().getMainDelivery().getActualStep() == Delivery.STEP4){
			nbCol = 2;
		}
		
		Component cell = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		
		TableModel model = table.getModel();
		if(model.getValueAt(row, nbCol).toString().contains(LanguageSelector.getLocalizedText(ELabelUI.INDICATEUR_ERR.getLabel()))){
			cell.setForeground(new Color(255, 153, 0));
			cell.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		else if(!model.getValueAt(row, nbCol).toString().isEmpty()){
			cell.setForeground(Color.RED);
			cell.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		else{
			cell.setForeground(new Color(34, 139, 34));
			cell.setFont(new Font("SansSerif", Font.PLAIN, 12));
		}
		return cell;
	}
}
