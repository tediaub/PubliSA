package view.guiComponents.frameOpening;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import view.guiComponents.frame.PanButtonFrame;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.openFrame.OpeningController;

@SuppressWarnings("serial")
public class PanelTemplate extends JPanel implements MouseMotionListener, MouseListener{

	protected OpeningController control;
	
	private JPanel panelHigh;

	private Point pointMouse;

	public PanelTemplate(OpeningController control) {
		setBackground(Color.WHITE);
		this.control = control;
		
		setLayout(new BorderLayout(0, 0));
		
		panelHigh = new JPanel();
		panelHigh.addMouseMotionListener(this);
		panelHigh.addMouseListener(this);
		panelHigh.setBackground(new Color(0, 119, 175));
		add(panelHigh, BorderLayout.NORTH);
		
		panelHigh.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("4dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("26px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		PanButtonFrame panelButton = new PanButtonFrame(control);
		panelButton.setMaximizedVisible(false);
		panelHigh.add(panelButton, "4, 1, fill, fill");
	}
	
	public void setMenuComponent(JComponent comp){
		panelHigh.add(comp, "2, 1, 1, 2, left, center");
	}
	
	public void setContainer(JComponent comp){
		add(comp, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pointMouse = control.getMouseOnFrame(e.getXOnScreen(), e.getYOnScreen());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen() - pointMouse.x;
		int y = e.getYOnScreen() - pointMouse.y;
		control.setFrameLocation(x, y);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
