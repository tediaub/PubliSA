package view.frame.mainFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JTextField;
import javax.swing.plaf.LayerUI;

import model.Model;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;
import view.guiComponents.layer.DcrLayerUI;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelDcr extends PanelObserver<ControllerFrame> implements KeyListener{
	
	private TextFieldFlat textField;

	public PanelDcr(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
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
		
		JLabel lblNewLabel = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.DCR.getLabel()));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 2, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 3, 1, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.TEXTE_DCR.getLabel()));
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(lblNewLabel_1, "3, 6");
		
		LayerUI<JTextField> layerUI = new DcrLayerUI();
		textField = new TextFieldFlat();
		textField.addKeyListener(this);
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(new JLayer<JTextField>(textField, layerUI), "3, 8, fill, default");
		
		update(control.getModel());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		control.setDCR(textField.getText());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	protected void update(Model model) {
		textField.setText(model.getMainDelivery().getDCR());
	}
}
