package view.guiComponents.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.FrameController;

@SuppressWarnings("serial")
public class PanButtonFrame extends JPanel implements ActionListener {

	private JButton btnIconified;
	private JButton btnMaximized;
	private JButton btnClose;
	
	private FrameController control;

	public PanButtonFrame(FrameController control) {
		this.control = control;
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		setOpaque(false);
		
		btnIconified = new JButton();
		btnIconified.setFocusable(false);
		btnIconified.setRolloverIcon(new ImageIcon(PanFrame.class.getResource("/iconeFrame/reduced/minusB.png")));
		btnIconified.setContentAreaFilled(false);
		btnIconified.addActionListener(this);
		btnIconified.setPreferredSize(new Dimension(20, 20));
		btnIconified.setIcon(new ImageIcon(PanFrame.class.getResource("/iconeFrame/reduced/minus.png")));
		add(btnIconified);
		
		btnMaximized = new JButton();
		btnMaximized.setFocusable(false);
		btnMaximized.setRolloverIcon(new ImageIcon(PanFrame.class.getResource("/iconeFrame/maximized/resizeB.png")));
		btnMaximized.setContentAreaFilled(false);
		btnMaximized.addActionListener(this);
		btnMaximized.setPreferredSize(new Dimension(20, 20));
		btnMaximized.setIcon(new ImageIcon(PanFrame.class.getResource("/iconeFrame/maximized/resize.png")));
		add(btnMaximized);
		
		btnClose = new JButton();
		btnClose.setFocusable(false);
		btnClose.setRolloverIcon(new ImageIcon(PanFrame.class.getResource("/iconeFrame/closed/CrossB.png")));
		btnClose.setContentAreaFilled(false);
		btnClose.addActionListener(this);
		btnClose.setPreferredSize(new Dimension(20, 20));
		btnClose.setIcon(new ImageIcon(PanFrame.class.getResource("/iconeFrame/closed/Cross.png")));
		add(btnClose);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnIconified){
			control.iconifiedFrame();
		}else if(e.getSource() == btnMaximized){
			control.maximizedFrame();
		}else if(e.getSource() == btnClose){
			control.closeFrame();
		}
	}
}
