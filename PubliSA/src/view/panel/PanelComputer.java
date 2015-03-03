package view.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import langue.GestLangue;
import langue.IHM;
import view.guiComponents.RadioButtonFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class PanelComputer extends JPanel {
	
	private RadioButtonFlat ckbSec;
	private RadioButtonFlat ckbElac;
	private RadioButtonFlat ckbFcdc;
	
	private ButtonGroup groupComputer;
	private TextFieldFlat textField;

	public PanelComputer() {
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("4dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("4dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("7dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow(3)"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Calculateur");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 2, 3, 1");
		
		SeparatorFlat separatorB = new SeparatorFlat();
		add(separatorB, "2, 4, 11, 1, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(lblNewLabel_1, "3, 6");
		
		JLabel lblNewLabel_2 = new JLabel("Standard :");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(lblNewLabel_2, "11, 6");		
		
		ckbSec = new RadioButtonFlat(GestLangue.getInstance().getLocalizedText(IHM.SEC.getLabel()));
  		ckbSec.setOpaque(false);
  		ckbElac = new RadioButtonFlat(GestLangue.getInstance().getLocalizedText(IHM.ELAC.getLabel()));
  		ckbElac.setOpaque(false);
  		ckbFcdc = new RadioButtonFlat(GestLangue.getInstance().getLocalizedText(IHM.FCDC.getLabel()));
  		ckbFcdc.setOpaque(false);
  		
  		groupComputer = new ButtonGroup();
  		groupComputer.add(ckbSec);
  		groupComputer.add(ckbElac);
  		groupComputer.add(ckbFcdc);
		
		add(ckbSec, "3, 8");
		add(ckbElac, "5, 8");
		add(ckbFcdc, "7, 8");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "9, 6, 1, 3");
		
		textField = new TextFieldFlat();
		add(textField, "11, 8, fill, center");
	}
}
