package view.step;

import javax.swing.JPanel;

import view.panel.PanelCheckStep2;
import view.panel.PanelDcr;
import view.panel.PanelOgc;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelStep2 extends JPanel {

	public PanelStep2(ControllerFrame control) {		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(363dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		PanelDcr panel = new PanelDcr(control);
		add(panel, "1, 1, fill, fill");
		
		PanelOgc panel_1 = new PanelOgc(control);
		add(panel_1, "1, 3, fill, fill");
		
		PanelCheckStep2 panel_2 = new PanelCheckStep2(control);
		add(panel_2, "1, 5, fill, fill");
	}
}
