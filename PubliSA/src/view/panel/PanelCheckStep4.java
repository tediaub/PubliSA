package view.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import langue.GestLangue;
import langue.IHM;
import model.Model;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.table.TableCellRenderer;
import view.guiComponents.table.TableFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelCheckStep4 extends PanelObserver {

	private TableFlat table;

	private static String[] columns = {GestLangue.getLocalizedText(IHM.COL_FICHIER_SOMMAIRE.getLabel()), 
		GestLangue.getLocalizedText(IHM.COL_FICHIER_CSV.getLabel()),
		GestLangue.getLocalizedText(IHM.COMMENTAIRES.getLabel())};
	/**
	 * Create the panel.
	 */
	public PanelCheckStep4(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblName = new JLabel(GestLangue.getLocalizedText(IHM.RESULTAT_VERIF.getLabel()));
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(0, 119, 175));
		add(lblName, "2, 2, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 3, 1, fill, fill");
		
		JScrollPane scrollPane = new JScrollPane();
		table = new TableFlat(new DefaultTableModel(columns, 0));
		table.setDefaultRenderer(Object.class, new TableCellRenderer((ControllerFrame)null));
		scrollPane.setViewportView(table);
		add(scrollPane, "2, 6, 2, 1, fill, fill");
	}
	
	@Override
	protected void update(Model model) {
		table.clearTable();
		try{
			for (int i = 0; i < model.getMainDelivery().getDataStep4().size(); i++) {
				table.addRow(model.getMainDelivery().getDataStep4().get(i));
			}
		}catch(NullPointerException e){
			
		}
	}
}
