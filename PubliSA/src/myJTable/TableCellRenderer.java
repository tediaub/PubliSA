package myJTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import langue.GestLangue;
import langue.IHM;

import utilisateur.Livraison;

	
@SuppressWarnings("serial")
public class TableCellRenderer extends DefaultTableCellRenderer {

	private int nbCol;
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column){
		
		if(Livraison.getEtape() == 2){
			nbCol = 4;
		}
		else if(Livraison.getEtape() == 4){
			nbCol = 2;
		}
		
		Component cell = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		TableModel model = table.getModel();
		if(model.getValueAt(row, nbCol).toString().contains(GestLangue.getInstance().getLocalizedText(IHM.INDICATEUR_ERR.getLabel()))){
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
