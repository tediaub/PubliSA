package view.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.frame.mainFrame.PanFrame;
import controller.IFrameController;

@SuppressWarnings("serial")
public class PanButtonFrame extends JPanel implements ActionListener {

	private JButton btnIconified;
	private JButton btnMaximized;
	private JButton btnClose;
	
	private IFrameController control;

	public PanButtonFrame(IFrameController control) {
		this.control = control;
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		setOpaque(false);
		
		btnIconified = new JButton();
		btnIconified.setFocusable(false);
		btnIconified.setRolloverIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/reduced/minusB.png")));
		btnIconified.setSelectedIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/reduced/minusB.png")));
		btnIconified.setContentAreaFilled(false);
		btnIconified.addActionListener(this);
		btnIconified.setPreferredSize(new Dimension(20, 20));
		btnIconified.setIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/reduced/minus.png")));
		add(btnIconified);
		
		btnMaximized = new JButton();
		btnMaximized.setFocusable(false);
		btnMaximized.setRolloverIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/maximized/resizeB.png")));
		btnMaximized.setSelectedIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/maximized/resizeB.png")));
		btnMaximized.setContentAreaFilled(false);
		btnMaximized.addActionListener(this);
		btnMaximized.setPreferredSize(new Dimension(20, 20));
		btnMaximized.setIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/maximized/resize.png")));
		add(btnMaximized);
		
		btnClose = new JButton();
		btnClose.setFocusable(false);
		btnClose.setRolloverIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/closed/CrossB.png")));
		btnClose.setSelectedIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/closed/CrossB.png")));
		btnClose.setContentAreaFilled(false);
		btnClose.addActionListener(this);
		btnClose.setPreferredSize(new Dimension(20, 20));
		btnClose.setIcon(new ImageIcon(PanFrame.class.getResource("/icons/frame/closed/Cross.png")));
		add(btnClose);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnIconified){
			control.iconifiedFrame();
		}else if(e.getSource() == btnMaximized){
			control.maximizedFrame();
		}else if(e.getSource() == btnClose){
			control.closeAll();
		}
	}
	
	public void setMaximizedVisible(boolean b){
		btnMaximized.setVisible(false);
	}
	
	public void setCloseVisible(boolean b){
		btnClose.setVisible(false);
	}
	
	public void setIconifiedVisible(boolean b){
		btnIconified.setVisible(false);
	}
	
	public boolean getMaximizedVisible(){
		return btnMaximized.isVisible();
	}
	
	public boolean getCloseVisible(){
		return btnClose.isVisible();
	}
	
	public boolean getIconifiedVisible(){
		return btnIconified.isVisible();
	}
}
