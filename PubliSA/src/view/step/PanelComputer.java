package view.step;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import langue.GestLangue;
import langue.IHM;
import view.guiComponents.RadioButtonFlat;
import view.guiComponents.TextFieldFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PanelComputer extends JPanel {
	private RadioButtonFlat ckbSec;
	private RadioButtonFlat ckbElac;
	private RadioButtonFlat ckbFcdc;
	private ButtonGroup buttonGroup_1;
	private TextFieldFlat textField;

	public PanelComputer() {
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("DCR");
		lblNewLabel.setFont(new Font("Sans", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 2, 2, 1");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 119, 175));
		add(panel, "2, 4, 5, 1, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(lblNewLabel_1, "3, 6");
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(lblNewLabel_2, "5, 6");
		
		RadioButtonFlat rdbtnNewRadioButton = new RadioButtonFlat("New radio button");
		add(rdbtnNewRadioButton, "3, 8");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "4, 6, 1, 7, center, default");
		
		RadioButtonFlat rdbtnNewRadioButton_1 = new RadioButtonFlat("New radio button");
		add(rdbtnNewRadioButton_1, "3, 10");
		
		
		
		RadioButtonFlat rdbtnNewRadioButton_2 = new RadioButtonFlat("New radio button");
		add(rdbtnNewRadioButton_2, "3, 12");
		
		
		ckbSec = new RadioButtonFlat(GestLangue.getInstance().getLocalizedText(IHM.SEC.getLabel()));
  		ckbSec.setOpaque(false);
  		ckbElac = new RadioButtonFlat(GestLangue.getInstance().getLocalizedText(IHM.ELAC.getLabel()));
  		ckbElac.setOpaque(false);
  		ckbFcdc = new RadioButtonFlat(GestLangue.getInstance().getLocalizedText(IHM.FCDC.getLabel()));
  		ckbFcdc.setOpaque(false);
  		buttonGroup_1 = new ButtonGroup();
		buttonGroup_1.add(ckbSec);
		buttonGroup_1.add(ckbElac);
		buttonGroup_1.add(ckbFcdc);
		
		textField = new TextFieldFlat();
		add(textField, "5, 8, 1, 3, fill, center");
	}
}
