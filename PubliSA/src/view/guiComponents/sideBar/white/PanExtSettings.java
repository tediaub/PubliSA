package view.guiComponents.sideBar.white;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.guiComponents.TabbedPaneFlat;
import view.guiComponents.frame.PanButtonFrame;
import view.panel.PanelAbout;
import view.panel.PanelMail;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanExtSettings extends JPanel implements ActionListener, MouseMotionListener {
	
	private ControllerFrame control;
	
	private PanButtonFrame panel;
	private JButton btnBack;

	public PanExtSettings(ControllerFrame control) {
		this.control = control;
		
		addMouseMotionListener(this);
		
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		panel = new PanButtonFrame(control);
		add(panel, "4, 1, fill, fill");
		
		btnBack = new JButton("Options");
		btnBack.setIconTextGap(20);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 30));
		btnBack.setForeground(new Color(0, 119, 175));
		btnBack.setIcon(new ImageIcon(PanExtSettings.class.getResource("/iconeSideBarWhite/backBlue.png")));
		btnBack.setRolloverIcon(new ImageIcon(PanExtSettings.class.getResource("/iconeSideBarWhite/backBlueHover.png")));
		btnBack.setContentAreaFilled(false);
		btnBack.addActionListener(this);
		add(btnBack, "2, 2");
		
		TabbedPaneFlat tabbedPane = new TabbedPaneFlat();
		tabbedPane.addTab("Profil", null);
		for (int i = 0; i < control.getModel().getUser().getMails().size(); i++) {
			tabbedPane.addTab(control.getModel().getUser().getMails().get(i).getShortTitle(),
					new PanelMail(control, control.getModel().getUser().getMails().get(i)), false);
		}
		PanelAbout panDetail = new PanelAbout();
		tabbedPane.addTab(panDetail.getName(), panDetail);
		
		add(tabbedPane, "2, 4, 3, 1, fill, fill");
	}

	public void actionPerformed(ActionEvent arg0) {
		control.colSideBarWhite();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
