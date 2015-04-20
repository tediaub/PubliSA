package view.frame.dialog;

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

import model.Delivery;
import view.guiComponents.buttons.ButtonFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class DialogDocHeader extends JDialog implements MouseListener, MouseMotionListener, ActionListener {

	private Point pointMouse;
	private ButtonFlat btnDeleteHeader;
	private ButtonFlat btnOpenForm;
	private ControllerFrame control;
	private ButtonFlat btnSkip;
	private ButtonFlat btnFileCheck;
	/**
	 * Create the application.
	 */
	public DialogDocHeader(ControllerFrame control) {
		this.control = control;
			
		if(check())return;

		setModal(true);
		
		getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),1));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogDocHeader.class.getResource("/logo/logoPubliSA4.png")));
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
		
		JLabel lblTitle = new JLabel("Avant de passer \u00E0 l'\u00E9tape suivante");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(Color.WHITE);
		panelHigh.add(lblTitle, "2, 1, 2, 1, left, center");
		
		JLabel lblNewLabel = new JLabel("Veuillez executer ces t\u00E2ches :");
		getContentPane().add(lblNewLabel, "2, 3");
		
		btnDeleteHeader = new ButtonFlat("Supprimer les Ent\u00EAtes");
		btnDeleteHeader.addActionListener(this);
		btnDeleteHeader.setForeground(Color.WHITE);
		btnDeleteHeader.setRolloverBackground(new Color(0, 92, 136));
		btnDeleteHeader.setBackground(new Color(0, 119, 175));
		getContentPane().add(btnDeleteHeader, "2, 5");
		
		btnFileCheck = new ButtonFlat("Ouvrir le logiciel FileCheck MD5");
		btnFileCheck.addActionListener(this);
		btnFileCheck.setForeground(Color.WHITE);
		btnFileCheck.setRolloverBackground(new Color(0, 92, 136));
		btnFileCheck.setBackground(new Color(0, 119, 175));
		getContentPane().add(btnFileCheck, "2, 5");
		
		btnOpenForm = new ButtonFlat("Ouvrir le formulaire EYDT");
		btnOpenForm.addActionListener(this);
		btnOpenForm.setForeground(Color.WHITE);
		btnOpenForm.setRolloverBackground(new Color(0, 92, 136));
		btnOpenForm.setBackground(new Color(0, 119, 175));
		getContentPane().add(btnOpenForm, "2, 7");
		
		btnSkip = new ButtonFlat("Passer");
		btnSkip.addActionListener(this);
		btnSkip.setRolloverBackground(new Color(220, 220, 220));
		btnSkip.setBackground(new Color(240, 240, 240));
		getContentPane().add(btnSkip, "2, 9, right, bottom");
		
		if(control.getModel().getMainDelivery().getTarget() == Delivery.UBIK){
			btnFileCheck.setVisible(false);
			
			if(!control.getModel().getMainDelivery().isHasOpenDocWord())btnOpenForm.setVisible(true);
			if(!control.getModel().getMainDelivery().isHasDeleteHeader())btnDeleteHeader.setVisible(true);
		}else if(control.getModel().getMainDelivery().getTarget() == Delivery.THALES){
			btnFileCheck.setVisible(true);
			btnOpenForm.setVisible(false);
			btnDeleteHeader.setVisible(false);
		}
		
		if(!control.getModel().getMainDelivery().isHasOpenDocWord()){
			btnOpenForm.setVisible(true);
		}else{
			btnOpenForm.setVisible(false);
		}
		
		if(!control.getModel().getMainDelivery().isHasOpenDocExe()){
			btnFileCheck.setVisible(true);
		}else{
			btnFileCheck.setVisible(false);
		}
		
		if(!control.getModel().getMainDelivery().isHasOpenDocWord()){
			btnDeleteHeader.setVisible(true);
		}else{
			btnDeleteHeader.setVisible(false);
		}
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSkip){
			dispose();
		}else if(e.getSource() == btnOpenForm){
			if(!control.openWord())return;
			if(check())dispose();
			btnOpenForm.setRolloverBackground(new Color(220, 220, 220));
			btnOpenForm.setBackground(new Color(240, 240, 240));
			btnOpenForm.setForeground(Color.BLACK);
		}else if(e.getSource() == btnDeleteHeader){
			if(!control.deleteHeader())return;
			if(check())dispose();
			btnDeleteHeader.setRolloverBackground(new Color(220, 220, 220));
			btnDeleteHeader.setBackground(new Color(240, 240, 240));
			btnDeleteHeader.setForeground(Color.BLACK);
		}else if(e.getSource() == btnFileCheck){
			if(!control.openExe())return;
			if(check())dispose();
			btnFileCheck.setRolloverBackground(new Color(220, 220, 220));
			btnFileCheck.setBackground(new Color(240, 240, 240));
			btnFileCheck.setForeground(Color.BLACK);
		}
	}
	
	public boolean check(){
		if(control.getModel().getMainDelivery().getTarget() == Delivery.UBIK){
			if(control.getModel().getMainDelivery().isHasDeleteHeader()
					&& control.getModel().getMainDelivery().isHasOpenDocWord()){
				return true;
			}
		}else if(control.getModel().getMainDelivery().getTarget() == Delivery.THALES){
			if(control.getModel().getMainDelivery().isHasOpenDocExe()){
				return true;
			}
		}
		return false;
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
}
