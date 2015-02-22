package view.step;

import javax.swing.JPanel;

import view.guiComponents.ButtonFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelStep2 extends JPanel {

	public PanelStep2() {
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(363dlu;default):grow"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		PanelDCR panel = new PanelDCR();
		add(panel, "1, 1, 2, 1, fill, fill");
		
		PanelOGC panel_1 = new PanelOGC();
		add(panel_1, "1, 3, 2, 1, fill, fill");
		
		PanelCheck panel_2 = new PanelCheck();
		add(panel_2, "1, 5, 2, 1, fill, fill");
		
	}
}
