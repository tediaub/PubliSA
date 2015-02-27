package view.guiComponents.table;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import langue.GestLangue;
import langue.IHM;

/**
 */
public class TableEtape2{

	private static JTable table2;
	private static JScrollPane spTable2;
	private static String[] colonnes = {GestLangue.getInstance().getLocalizedText(IHM.DCR.getLabel()),
										GestLangue.getInstance().getLocalizedText(IHM.COL_NOM_PLANCHE.getLabel()),
										GestLangue.getInstance().getLocalizedText(IHM.COL_NOM_SOMMAIRE.getLabel()),
										GestLangue.getInstance().getLocalizedText(IHM.OK_KO.getLabel()),
										GestLangue.getInstance().getLocalizedText(IHM.COMMENTAIRES.getLabel())};
	private static DefaultTableModel model;
	
	public TableEtape2(){
		
		model = new DefaultTableModel(new Object[][] {},colonnes);
		table2 = new JTable(model);
		table2.setDefaultRenderer(Object.class, new TableCellRenderer());
		spTable2 = new JScrollPane(table2);
		spTable2.setBackground(Color.WHITE);
		spTable2.setBorder(new LineBorder(new Color(211, 211, 211),2));
		table2.setFillsViewportHeight(true);

		changeSizeColum(table2, 1, 270);
		changeSizeColum(table2, 2, 270);
		changeSizeColum(table2, 4, 500);
		
		for (int vColIndex = 0; vColIndex < table2.getModel().getColumnCount(); vColIndex++) {
			TableColumn col = table2.getColumnModel().getColumn(vColIndex);
			col.setHeaderRenderer(new MyTableHeaderRenderer());
		}
	}
	
	/**
	 * Method changeSizeColum.
	 * @param tableau JTable
	 * @param colonne int
	 * @param width int
	 */
	public void changeSizeColum(JTable tableau, int colonne, int width){
		
		//Nous créons un objet TableColumn afin de travailler sur notre colonne
		TableColumn col;
		col = tableau.getColumnModel().getColumn(colonne);
		col.setPreferredWidth(width);
	}
	
	/**
	 * Method construireTableau.
	 * @param DCR String
	 * @param fichSom String
	 * @param fichPlan String
	 * @param erreur String
	 * @param comment String
	 */
	public static void construireTableau(String DCR, 
			String fichSom, 
			String fichPlan,
			String erreur,
			String comment) {

	     model.addRow(new String[]{DCR, fichSom, fichPlan, erreur, comment});
	}
	public static void removeJTable(){
		model.setRowCount(0);
		table2.setModel(model);
	}
	/**
	 * Method getPanel.
	 * @return JScrollPane
	 */
	public static JScrollPane getPanel(){
		return spTable2;
	}
	
	public static JTable getTable(){
		return table2;
	}
}
