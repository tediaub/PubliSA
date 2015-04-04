package view.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import langue.GestLangue;
import langue.IHM;
import model.Model;
import view.guiComponents.ButtonFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.table.TableCellRenderer;
import view.guiComponents.table.TableFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelCheckStep2 extends PanelObserver {
	
	private TableFlat table;
	private ButtonFlat btnOptions;
	
	private static String[] columns = {GestLangue.getLocalizedText(IHM.DCR.getLabel()),
		GestLangue.getLocalizedText(IHM.COL_NOM_PLANCHE.getLabel()),
		GestLangue.getLocalizedText(IHM.COL_NOM_SOMMAIRE.getLabel()),
		GestLangue.getLocalizedText(IHM.OK_KO.getLabel()),
		GestLangue.getLocalizedText(IHM.COMMENTAIRES.getLabel())};

	public PanelCheckStep2(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblName = new JLabel(GestLangue.getLocalizedText(IHM.RESULTAT_VERIF.getLabel()));
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(0, 119, 175));
		add(lblName, "2, 2, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 4, 1, fill, fill");
		
		btnOptions = new ButtonFlat();
		btnOptions.setBackground(new Color(255, 255, 255));
		btnOptions.setRolloverBackground(new Color(240, 240, 240));
		btnOptions.setForeground(Color.WHITE);
		btnOptions.setIcon(new ImageIcon(PanelCheckStep2.class.getResource("/iconeStep2/other.png")));
		add(btnOptions, "4, 6");
		
		JScrollPane scrollPane = new JScrollPane();
		table = new TableFlat(new DefaultTableModel(columns, 0));
		table.setDefaultRenderer(Object.class, new TableCellRenderer(control));
		scrollPane.setViewportView(table);
		add(scrollPane, "2, 8, 3, 1, fill, fill");
		
		update(control.getModel());
	}
	
	@Override
	protected void update(Model model) {
		table.clearTable();
		try{
			for (int i = 0; i < model.getMainDelivery().getDataStep2().size(); i++) {
				table.addRow(model.getMainDelivery().getDataStep2().get(i));
			}
		}catch(NullPointerException e){
			
		}
	}
}
