package view.frame.mainFrame.panel.step;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.frame.dialog.DialogDocHeader;
import view.frame.mainFrame.panel.PanelCheckStep2;
import view.frame.mainFrame.panel.PanelDcr;
import view.frame.mainFrame.panel.PanelOgc;
import view.guiComponents.buttons.ButtonFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelStep2 extends JPanel implements ActionListener {

	private ButtonFlat btnValidate;
	private ControllerFrame control;

	public PanelStep2(ControllerFrame control) {		
		this.control = control;
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(363dlu;default):grow"),
				ColumnSpec.decode("25px")},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		PanelDcr panel = new PanelDcr(control);
		add(panel, "1, 1, 2, 1, fill, fill");
		
		PanelOgc panel_1 = new PanelOgc(control);
		add(panel_1, "1, 3, 2, 1, fill, fill");
		
		PanelCheckStep2 panel_2 = new PanelCheckStep2(control);
		add(panel_2, "1, 5, 2, 1, fill, fill");
		
		btnValidate = new ButtonFlat("Valider");
		btnValidate.setIcon(new ImageIcon(PanelStep2.class.getResource("/icons/step/validate.png")));
		btnValidate.addActionListener(this);
		btnValidate.setRolloverBackground(new Color(53, 117, 48));
		btnValidate.setBackground(new Color(78, 170, 70));
		btnValidate.setForeground(Color.WHITE);
		btnValidate.setMargin(new Insets(2, 4, 2, 4));
		btnValidate.setIconTextGap(15);
		add(btnValidate, "1, 7, right, center");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnValidate){
			new DialogDocHeader(control);
			control.changeStep();
		}
	}
}