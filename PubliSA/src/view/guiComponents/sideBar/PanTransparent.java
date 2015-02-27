package view.guiComponents.sideBar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanTransparent extends JPanel implements MouseListener{

	private ControllerFrame controller;

	public PanTransparent(ControllerFrame control) {
		controller = control;
		
		addMouseListener(this);
		setBackground(new Color(0, 0, 0, 60));
	}

	public void mouseClicked(MouseEvent arg0) {
		controller.colSideBarBlue();
		controller.colSideBarWhite();
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}