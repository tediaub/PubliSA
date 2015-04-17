package view.step;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.guiComponents.ButtonFlat;
import view.panel.PanelAttached;
import view.panel.PanelComputer;
import view.panel.PanelDcr;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelStep3 extends JPanel implements ActionListener{

	private ButtonFlat btnCreate;
	private ControllerFrame control;

	/**
	 * Create the panel.
	 * @param control 
	 */
	public PanelStep3(ControllerFrame control) {
		this.control = control;
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(363dlu;default):grow"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		PanelDcr panel = new PanelDcr(control);
		add(panel, "1, 1, 2, 1, fill, fill");
		
		PanelComputer panel_1 = new PanelComputer(control);
		add(panel_1, "1, 3, 2, 1, fill, fill");

		PanelAttached panel_2 = new PanelAttached(control);
		add(panel_2, "1, 5, 2, 1, fill, fill");
		
		btnCreate = new ButtonFlat("Cr�er");
		btnCreate.addActionListener(this);
		btnCreate.setRolloverBackground(new Color(0, 92, 136));
		btnCreate.setBackground(new Color(0, 119, 175));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setMargin(new Insets(2, 4, 2, 4));
		btnCreate.setIconTextGap(15);
		add(btnCreate, "1, 6, right, top");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCreate){
			try {
				control.createMail();
				control.changeStep();
			} catch (Exception e1) {
				return;
			}
		}
	}
}
