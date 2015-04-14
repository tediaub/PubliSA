package affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.guiComponents.ButtonFlat;
import view.guiComponents.frame.PanButtonFrame;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.IFrameController;

@SuppressWarnings("serial")
public class test extends JDialog implements MouseListener, MouseMotionListener, ActionListener, IFrameController {

	private Point pointMouse;
	/**
	 * Create the application.
	 */
	public test() {
		setModal(true);
		
		getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),1));
		setIconImage(Toolkit.getDefaultToolkit().getImage(test.class.getResource("/logo/logoPubliSA4.png")));
		setUndecorated(true);
		
		setSize(400, 240);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("10dlu"),},
			new RowSpec[] {
				RowSpec.decode("50px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_ROWSPEC,}));
		
		JPanel panelHigh = new JPanel();
		panelHigh.addMouseListener(this);
		panelHigh.addMouseMotionListener(this);
		panelHigh.setBackground(new Color(0, 119, 175));
		getContentPane().add(panelHigh, "1, 1, 3, 1, fill, top");
		panelHigh.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("50px"),}));
		
		PanButtonFrame panButtonFrame = new PanButtonFrame(this);
		panButtonFrame.setIconifiedVisible(false);
		panButtonFrame.setMaximizedVisible(false);
		panelHigh.add(panButtonFrame, "3, 1, fill, fill");
		
		JLabel lblTitle = new JLabel("Avant de passer \u00E0 l'\u00E9tape suivante");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(Color.WHITE);
		panelHigh.add(lblTitle, "2, 1, 2, 1, left, center");
		
		JLabel lblNewLabel = new JLabel("Veuillez exeuter ces t\u00E2ches :");
		getContentPane().add(lblNewLabel, "2, 3");
		
		ButtonFlat btnDeleteHeader = new ButtonFlat("Supprimer les Ent\u00EAtes");
		btnDeleteHeader.setForeground(Color.WHITE);
		btnDeleteHeader.setRolloverBackground(new Color(0, 92, 136));
		btnDeleteHeader.setBackground(new Color(0, 119, 175));
		getContentPane().add(btnDeleteHeader, "2, 5");
		
		ButtonFlat btnOpenForm = new ButtonFlat("Ouvrir le formulaire EYDT");
		btnOpenForm.setForeground(Color.WHITE);
		btnOpenForm.setRolloverBackground(new Color(0, 92, 136));
		btnOpenForm.setBackground(new Color(0, 119, 175));
		getContentPane().add(btnOpenForm, "2, 7");		
		
		ButtonFlat btnSkip = new ButtonFlat("Passer");
		btnSkip.setRolloverBackground(new Color(220, 220, 220));
		btnSkip.setBackground(new Color(240, 240, 240));
		getContentPane().add(btnSkip, "2, 9, right, bottom");
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int xOrigin = e.getXOnScreen()- getX();
		int yOrigin = e.getYOnScreen()- getY();
		pointMouse = new Point(xOrigin, yOrigin);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen() - pointMouse.x;
		int y = e.getYOnScreen() - pointMouse.y;
		setLocation(x,y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iconifiedFrame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maximizedFrame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeFrame() {
		System.out.println("test");
		this.dispose();
	}

	@Override
	public void closeAll() {
		this.dispose();
	}

	@Override
	public void resizeFrame(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFrameLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getMouseOnFrame(int xOnScreen, int yOnScreen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		
	}
}
