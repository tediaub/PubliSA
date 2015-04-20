package view.guiComponents;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class LabelResize extends JLabel implements MouseMotionListener {
	
	private ControllerFrame control;
	
	public LabelResize(ControllerFrame control){
		super();
		this.control = control;
		setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
		setIcon(new ImageIcon(LabelResize.class.getResource("/iconeResize/SWicon.png")));
		addMouseMotionListener(this);
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		control.resizeFrame(x, y);
		}

	public void mouseMoved(MouseEvent arg0) {
		
	}
}