package view.guiComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonFlat extends JPanel implements MouseListener{

	private JButton button;

	/**
	 * Initialize the contents of the frame.
	 */
	public ButtonFlat(String test, String icon) {
		setLayout(new BorderLayout());
		setBackground(new Color(0,119,175));
		
		button = new JButton(test);
		button.setFont(new Font("Dialog", Font.PLAIN, 13));
		button.setIconTextGap(20);
		button.setForeground(Color.WHITE);
		button.setIcon(new ImageIcon(ButtonFlat.class.getResource(icon)));
		button.setContentAreaFilled(false);
		button.addMouseListener(this);
		
		add(button, BorderLayout.CENTER);
	}
	
	public JButton getButton(){
		return button;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		setBackground(new Color(0,63,113));
	}

	public void mouseExited(MouseEvent e) {
		setBackground(new Color(0,119,175));
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
