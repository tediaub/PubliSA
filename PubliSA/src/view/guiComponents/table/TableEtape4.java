package view.guiComponents.table;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import langue.GestLangue;
import langue.IHM;

/**
 */
public class TableEtape4 {

	private static JTable table4;
	private static JScrollPane spTable4;
	private static String[] colonnes = {GestLangue.getInstance().getLocalizedText(IHM.COL_FICHIER_SOMMAIRE.getLabel()), 
										GestLangue.getInstance().getLocalizedText(IHM.COL_FICHIER_CSV.getLabel()),
										GestLangue.getInstance().getLocalizedText(IHM.COMMENTAIRES.getLabel())};
	private static DefaultTableModel model;
	
	public TableEtape4(){
		model = new DefaultTableModel(new Object[][] {},colonnes);
		table4 = new JTable(model);
		table4.setDefaultRenderer(Object.class, new TableCellRenderer());
		spTable4 = new JScrollPane(table4);
		spTable4.setBackground(Color.WHITE);
		
		table4.setFillsViewportHeight(true);
			
		spTable4.setViewportView(table4);	
	}	
	
	/**
	 * Method getPanel.
	 * @return JScrollPane
	 */
	public static JScrollPane getPanel(){
		return spTable4;
	}
	
	public static void removeJTable(){
		model.setRowCount(0);
		table4.setModel(model);
	}
	
	public static void construireTableau(String sommaire, String csv, String commentaire) {
	    model.addRow(new String[]{sommaire, csv, commentaire});
	}

	public static JTable getTable() {
		return table4;
	}
}
