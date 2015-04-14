package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import jxl.write.WriteException;

import model.Model;
import view.guiComponents.ButtonFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.table.TableCellRenderer;
import view.guiComponents.table.TableFlat;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;
import controller.checking.Report;

@SuppressWarnings("serial")
public class PanelCheckStep4 extends PanelObserver implements ActionListener {

	private TableFlat table;

	private ButtonFlat btnReport;

	private static String[] columns = {LanguageSelector.getLocalizedText(ELabelUI.COL_FICHIER_SOMMAIRE.getLabel()), 
		LanguageSelector.getLocalizedText(ELabelUI.COL_FICHIER_CSV.getLabel()),
		LanguageSelector.getLocalizedText(ELabelUI.COMMENTAIRES.getLabel())};
	/**
	 * Create the panel.
	 */
	public PanelCheckStep4(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblName = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.RESULTAT_VERIF.getLabel()));
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(0, 119, 175));
		add(lblName, "2, 2, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 3, 1, fill, fill");
		
		btnReport = new ButtonFlat(LanguageSelector.getLocalizedText(ELabelUI.BT_COMPTE_RENDU.getLabel()));
		btnReport.addActionListener(this);
		btnReport.setRolloverBackground(new Color(220, 220, 220));
		btnReport.setBackground(new Color(240, 240, 240));
		add(btnReport, "3, 6, default, center");
		
		JScrollPane scrollPane = new JScrollPane();
		table = new TableFlat(new DefaultTableModel(columns, 0));
		table.setDefaultRenderer(Object.class, new TableCellRenderer((ControllerFrame)null));
		scrollPane.setViewportView(table);
		add(scrollPane, "2, 8, 2, 1, fill, fill");		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReport){
			try {
				new Report(control, columns);
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
		}
	}
}
