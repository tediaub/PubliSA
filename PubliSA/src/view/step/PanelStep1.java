package view.step;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;

import view.guiComponents.ButtonFlat;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class PanelStep1 extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelStep1() {
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(363dlu;default):grow"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		PanelDCR panel = new PanelDCR();
		add(panel, "1, 1, 2, 1, fill, fill");
		
		PanelComputer panel_1 = new PanelComputer();
		add(panel_1, "1, 3, 2, 1, fill, fill");
		
		ButtonFlat btnNewButton = new ButtonFlat("Cr�er", "/iconeStep1/mail.png");
		add(btnNewButton, "1, 5, right, center");

	}

}