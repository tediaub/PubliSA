package view.step;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JPanel;

import view.guiComponents.ButtonFlat;
import view.panel.PanelComputer;
import view.panel.PanelDCR;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelStep1And3 extends JPanel{

	private ButtonFlat btnCreate;
	private ControllerFrame control;

	/**
	 * Create the panel.
	 * @param control 
	 */
	public PanelStep1And3(ControllerFrame control) {
		this.control = control;
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(363dlu;default):grow"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("30px"),
				FormFactory.DEFAULT_ROWSPEC,}));
		
		PanelDCR panel = new PanelDCR(control);
		add(panel, "1, 1, 2, 1, fill, fill");
		
		PanelComputer panel_1 = new PanelComputer(control);
		add(panel_1, "1, 3, 2, 1, fill, fill");
		
		btnCreate = new ButtonFlat("Créer");
		btnCreate.setRolloverBackground(new Color(0, 92, 136));
		btnCreate.setBackground(new Color(0, 119, 175));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setMargin(new Insets(2, 4, 2, 4));
		btnCreate.setIconTextGap(15);
		add(btnCreate, "1, 5, right, center");
	}
}
