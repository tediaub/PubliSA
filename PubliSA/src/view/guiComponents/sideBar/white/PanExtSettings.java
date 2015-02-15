package view.guiComponents.sideBar.white;

import javax.swing.JPanel;

import java.awt.Color;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import controller.FrameController;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import view.guiComponents.LabelResize;
import view.guiComponents.frame.PanButtonFrame;

import java.awt.Font;

@SuppressWarnings("serial")
public class PanExtSettings extends JPanel implements ActionListener, MouseMotionListener {
	
	private FrameController control;
	
	private PanButtonFrame panel;
	private JButton btnBack;
	private LabelResize lblResize;

	public PanExtSettings(FrameController control) {
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
				RowSpec.decode("default:grow"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		panel = new PanButtonFrame(control);
		add(panel, "4, 1, fill, fill");
		
		btnBack = new JButton("Options");
		btnBack.setIconTextGap(20);
		btnBack.setFont(new Font("Dotum", Font.PLAIN, 30));
		btnBack.setForeground(new Color(100, 100, 100));
		btnBack.setIcon(new ImageIcon(PanExtSettings.class.getResource("/iconeSideBarWhite/backBlue.png")));
		btnBack.setContentAreaFilled(false);
		btnBack.addActionListener(this);
		add(btnBack, "2, 2");
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, "3, 3, 2, 1, fill, fill");
		
		lblResize = new LabelResize(control);
		lblResize.setVerticalAlignment(SwingConstants.BOTTOM);
		lblResize.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblResize, "4, 5, fill, fill");
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
