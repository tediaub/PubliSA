package view.frame.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.guiComponents.buttons.ButtonFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class DialogFlat extends JDialog implements MouseListener, MouseMotionListener, ActionListener{

	//Icon
	public static String INFORMATION_ICON = "/icons/dialog/info.png";
	public static String ERROR_ICON = "/icons/dialog/error.png";
	public static String WARNING_ICON = "/icons/dialog/warning.png";
	
	//Button
	public static int CLOSE_OPERATION = 0;
	public static int ERROR_OPERATION = 1;
	public static int INFORMATION_OPERATION = 2;
	public static int ASK_OPERATION = 3;
	
	//Return
	public static final int VALIDATE = 1;
	public static final int CANCEL = 0;
	public static final int SAVE_AND_CLOSE = 2;
	
	private ButtonFlat btnOK = new ButtonFlat("OK");
	private ButtonFlat btnCancel = new ButtonFlat("Annuler");
	private ButtonFlat btnYes = new ButtonFlat("Oui");
	private ButtonFlat btnNo = new ButtonFlat("Non");
	private ButtonFlat btnSaveAndQuit = new ButtonFlat("Sauvegarder et Quitter");
	private ButtonFlat btnQuit = new ButtonFlat("Quitter");
	
	private int back;
	private Point pointMouse;
	
	public int showDialog(String title, String message, int type, String icon){
		setModal(true);
		setTitle(title);
		
		getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),1));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogFlat.class.getResource("/logo/logoPubliSA4.png")));
		setUndecorated(true);
		
		setSize(400, 190);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		JPanel panelHigh = new JPanel();
		panelHigh.addMouseListener(this);
		panelHigh.addMouseMotionListener(this);
		panelHigh.setBackground(new Color(0, 119, 175));
		panelHigh.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("60px:grow"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,}));
		getContentPane().add(panelHigh, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTitle.setForeground(Color.WHITE);
		panelHigh.add(lblTitle, "2, 2, left, center");
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(new Color(240, 240, 240));
		FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		if(type == ERROR_OPERATION){
			panelButton.add(btnOK);
		}
		if(type == INFORMATION_OPERATION){
			panelButton.add(btnOK);
			panelButton.add(btnCancel);
		}
		if(type == ASK_OPERATION){
			panelButton.add(btnYes);
			panelButton.add(btnNo);
		}
		if(type == CLOSE_OPERATION){
			panelButton.add(btnSaveAndQuit);
			panelButton.add(btnQuit);
			panelButton.add(btnCancel);
		}
		
		for (int i = 0; i < panelButton.getComponentCount(); i++) {
			if(panelButton.getComponent(i) instanceof ButtonFlat){
				ButtonFlat b = (ButtonFlat) panelButton.getComponent(i);
				b.setBackground(new Color(220,220,220));
				b.setRolloverBackground(new Color(200,200,200));
				b.addActionListener(this);
			}
		}
		
		btnYes.setBackground(new Color(0,119,175));
		btnYes.setRolloverBackground(new Color(0,63,113));
		btnYes.setForeground(Color.WHITE);
		
		btnSaveAndQuit.setBackground(new Color(0,119,175));
		btnSaveAndQuit.setRolloverBackground(new Color(0,63,113));
		btnSaveAndQuit.setForeground(Color.WHITE);
		
		btnOK.setBackground(new Color(0,119,175));
		btnOK.setRolloverBackground(new Color(0,63,113));
		btnOK.setForeground(Color.WHITE);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.WHITE);
		getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("15dlu"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_ROWSPEC,}));
		
		JLabel lblIcon = new JLabel();
		if(!icon.isEmpty())lblIcon.setIcon(new ImageIcon(DialogFlat.class.getResource(icon)));
		panelMain.add(lblIcon, "2, 2");
		
		JLabel lblMessage = new JLabel(message);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		panelMain.add(lblMessage, "4, 2");
		
		setVisible(true);
		return back;
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOK || e.getSource() == btnYes || e.getSource() == btnQuit){
			back = VALIDATE;
			dispose();
		}
		if(e.getSource() == btnCancel || e.getSource() == btnNo){
			back = CANCEL;
			dispose();
		}
		if(e.getSource() == btnSaveAndQuit){
			back = SAVE_AND_CLOSE;
			dispose();
		}
	}

	
	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen() - pointMouse.x;
		int y = e.getYOnScreen() - pointMouse.y;
		setLocation(x,y);
	}

	
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		int xOrigin = e.getXOnScreen()- getX();
		int yOrigin = e.getYOnScreen()- getY();
		pointMouse = new Point(xOrigin, yOrigin);
	}

	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
