package view.guiComponents.frameOpening;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.guiComponents.SeparatorFlat;
import view.guiComponents.frame.PanButtonFrame;
import controller.OpeningController;

@SuppressWarnings("serial")
public class PanelOpen extends JPanel implements MouseListener, MouseMotionListener{
	
	private JPanel cards;
	private Point pointMouse;
	private OpeningController control;
	
	public PanelOpen(OpeningController control) {
		this.control = control;
		
		addMouseListener(this);
		addMouseMotionListener(this);

		setLayout(null);
		setBackground(new Color(0, 119, 175));
		
		JLabel lblIcon= new JLabel();
		lblIcon.setIcon(new ImageIcon(PanelOpen.class.getResource("/logo/logoPubliSA4Open.png")));
		lblIcon.setBounds(112, 99, 120, 120);
		add(lblIcon);
		
		JLabel lblTitle = new JLabel();
		lblTitle.setIcon(new ImageIcon(PanelOpen.class.getResource("/logo/logoTitreOpen.jpg.png")));
		lblTitle.setBounds(90, 241, 165, 30);
		add(lblTitle);
		
		JLabel lblAeroconseil = new JLabel("AEROCONSEIL - Version 4.0");
		lblAeroconseil.setFont(new Font("Arial", Font.PLAIN, 9));
		lblAeroconseil.setForeground(Color.WHITE);
		lblAeroconseil.setBounds(10, 375, 165, 14);
		add(lblAeroconseil);
		
		PanButtonFrame panButtonFrame = new PanButtonFrame(control);
		panButtonFrame.setMaximizedVisible(false);
		panButtonFrame.setBounds(640, 0, 60, 20);
		add(panButtonFrame);
		
		SeparatorFlat separatorFlat = new SeparatorFlat(Color.WHITE, 2, SwingConstants.VERTICAL);
		separatorFlat.setBounds(349, 30, 2, 340);
		add(separatorFlat);
		
		cards = new JPanel();
		cards.setOpaque(false);
		cards.setBounds(355, 30, 340, 340);
		cards.setLayout(new CardLayout(0, 0));
		add(cards);
		
		FirstLaunchPanel pFirstLaunch = new FirstLaunchPanel(control);
		cards.add(pFirstLaunch, OpeningFrame.PANEL_FIRST_LAUNCH);
		
		MainPanel pMain = new MainPanel(control);
		cards.add(pMain, OpeningFrame.PANEL_MAIN);
		
		NewDeliveryPanel pNewDelivery = new NewDeliveryPanel(control);
		cards.add(pNewDelivery, OpeningFrame.PANEL_NEW_DELIVERY);
		
		setVisible(true);
	}
	
	public void setViewPanel(String panelString){
		CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, panelString);
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

}
