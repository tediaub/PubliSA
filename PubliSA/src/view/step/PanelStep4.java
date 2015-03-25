package view.step;

import javax.swing.JPanel;

import view.panel.PanelCheckStep4;
import view.panel.PanelOgcAndCsv;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelStep4 extends JPanel {

	public PanelStep4(ControllerFrame control) {
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		PanelOgcAndCsv panel = new PanelOgcAndCsv(control);
		add(panel, "1, 1, fill, fill");
		
		PanelCheckStep4 panel_1 = new PanelCheckStep4(control);
		add(panel_1, "1, 3, fill, fill");
	}

}
