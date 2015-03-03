package view.step;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.guiComponents.ButtonFlat;
import view.panel.PanelComputer;
import view.panel.PanelDCR;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

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
		
		ButtonFlat btnCreate = new ButtonFlat("Créer");
		btnCreate.setBackground(new Color(0, 119, 175));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setMargin(new Insets(2, 4, 2, 4));
		btnCreate.setIconTextGap(15);
		btnCreate.setIcon(new ImageIcon(getClass().getResource("/iconeStep1/mail.png")));
		add(btnCreate, "1, 5, right, center");
	}

}