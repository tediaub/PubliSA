package view.guiComponents.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.FrameController;

@SuppressWarnings("serial")
public class PanFrame extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

	private JButton btnIconified;
	private JButton btnMaximized;
	private JButton btnClose;
	
	private PanButtonFrame pBtnFrame;
	
	private FrameController control;
	
	private Point pointMouse;
	private JLabel lblDelivery;
	private JLabel lblLogo;

	/**
	 * Create the panel.
	 */
	public PanFrame(FrameController control) {
		setPreferredSize(new Dimension(723, 35));
		this.control = control;
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		setBackground(new Color(0, 119, 175));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("150px"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogo.setIcon(new ImageIcon(PanFrame.class.getResource("/logo/logoTitre.png")));
		add(lblLogo, "2, 1");
		
		pBtnFrame = new PanButtonFrame(control);
		add(pBtnFrame, "3, 1, fill, fill");
		
		lblDelivery = new JLabel("Nom de la livraison");
		lblDelivery.setFont(new Font("Dotum", Font.BOLD, 14));
		lblDelivery.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelivery.setForeground(Color.WHITE);
		add(lblDelivery, "1, 1");
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen() - pointMouse.x;
		int y = e.getYOnScreen() - pointMouse.y;
		control.setFrameLocation(x, y);
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		pointMouse = control.getMouseOnFrame(e.getXOnScreen(), e.getYOnScreen());
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
