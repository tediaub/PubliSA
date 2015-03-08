package view.guiComponents.sideBar.blue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.guiComponents.ButtonFlat;
import view.guiComponents.svg.Ariane;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanCollapse extends JPanel implements ActionListener {

	private JButton btnCollapse;

	private ControllerFrame controller;

	private ButtonFlat btnSettings;

	private Ariane pAriane;
	/**
	 * Create the panel.
	 */
	public PanCollapse(ControllerFrame control) {
		controller = control;
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("50px"),},
			new RowSpec[] {
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("25px"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("35px"),}));
		setBackground(new Color(0, 119, 175));
		
		pAriane = new Ariane(control);
		control.getModel().addObserver(pAriane);
		add(pAriane, "1, 4, fill, fill");
		
		btnCollapse = new JButton();
		btnCollapse.setContentAreaFilled(false);
		btnCollapse.setIcon(new ImageIcon(PanCollapse.class.getResource("/iconeSideBarBlue/menu.png")));
		btnCollapse.setFocusPainted(false);
		btnCollapse.addActionListener(this);
		add(btnCollapse, "1, 2");
		
		btnSettings = new ButtonFlat();
		btnSettings.setPreferredSize(new Dimension(90, 60));
		btnSettings.setIcon(new ImageIcon(PanCollapse.class.getResource("/iconeSideBarBlue/gear.png")));
		btnSettings.setFocusPainted(false);
		btnSettings.setRolloverBackground(new Color(0, 63, 113));
		btnSettings.setOpaque(false);
		btnSettings.addActionListener(this);
		add(btnSettings, "1, 6");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCollapse){
			controller.extSideBarBlue();
		}else if(e.getSource() == btnSettings){
			controller.extSideBarWhite();
		}
	}
}
