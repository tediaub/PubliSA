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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Delivery;
import view.frame.PanButtonFrame;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;
import view.guiComponents.buttons.ButtonFlat;
import view.guiComponents.buttons.PanelPopUpNewDelivery;
import view.guiComponents.buttons.RadioButtonFlat;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class DialogNew extends JDialog implements MouseListener, MouseMotionListener, ActionListener {

	private RadioButtonFlat rdbtnThales;
	private RadioButtonFlat rdbtnUbik;
	private ButtonFlat btnCreate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private TextFieldFlat tfDelivery;
	private Point pointMouse;
	private ControllerFrame control;

	public DialogNew(ControllerFrame control) {
		this.control = control;
		
		setModal(true);
		setUndecorated(true);
		setSize(450, 317);
		
		getRootPane().setBorder(new LineBorder(new Color(0,0,0,40),1));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogDocHeader.class.getResource("/logo/logoPubliSA4.png")));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				(screenSize.width-this.getWidth())/2,
				(screenSize.height-this.getHeight())/2);
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("15px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("15px"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("fill:2px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("15px"),}));
		
		JPanel panelHigh = new JPanel();
		panelHigh.addMouseListener(this);
		panelHigh.addMouseMotionListener(this);
		panelHigh.setBackground(new Color(0, 119, 175));
		getContentPane().add(panelHigh, "1, 1, 5, 1, fill, top");
		panelHigh.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("50px"),}));
		
		PanButtonFrame panButtonFrame = new PanButtonFrame(control);
		panButtonFrame.setIconifiedVisible(false);
		panButtonFrame.setMaximizedVisible(false);
		panelHigh.add(panButtonFrame, "3, 1, fill, fill");
		
		JLabel lblTitle = new JLabel("Nouveau");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(Color.WHITE);
		panelHigh.add(lblTitle, "2, 1, 2, 1, left, center");
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		getContentPane().add(lblNewLabel, "2, 3, 2, 1");
		
		SeparatorFlat separatorFlat = new SeparatorFlat(new Color(0, 119, 175));
		getContentPane().add(separatorFlat, "2, 5, 3, 1");
		
		JLabel lblEntreLeNom = new JLabel("Entr\u00E9e le nom de la nouvelle livraison :");
		lblEntreLeNom.setForeground(Color.BLACK);
		lblEntreLeNom.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblEntreLeNom, "3, 7, 2, 1");
		
		tfDelivery = new TextFieldFlat();
		getContentPane().add(tfDelivery, "3, 9, 2, 1, fill, default");
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDestination.setForeground(new Color(0, 119, 175));
		getContentPane().add(lblDestination, "2, 11, 2, 1");
		
		SeparatorFlat separatorFlat_1 = new SeparatorFlat(new Color(0, 119, 175));
		getContentPane().add(separatorFlat_1, "2, 13, 3, 1");
		
		JLabel lblNewLabel_1 = new JLabel("Selectionner le destinataire de la livraison : ");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel_1, "3, 15, 2, 1");
		
		rdbtnUbik = new RadioButtonFlat("UBIK");
		rdbtnUbik.setSelectedIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/iconeRadioButton/radioButtonValidate.png")));
		rdbtnUbik.setIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/iconeRadioButton/radioButtonNormal.png")));
		rdbtnUbik.setRolloverIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/iconeRadioButton/radioButtonHoover.png")));
		rdbtnUbik.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnUbik.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnUbik.setForeground(Color.BLACK);
		buttonGroup.add(rdbtnUbik);
		getContentPane().add(rdbtnUbik, "3, 17");
		
		rdbtnThales = new RadioButtonFlat("THALES");
		rdbtnThales.setIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/iconeRadioButton/radioButtonNormal.png")));
		rdbtnThales.setRolloverIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/iconeRadioButton/radioButtonHoover.png")));
		rdbtnThales.setSelectedIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/iconeRadioButton/radioButtonValidate.png")));
		rdbtnThales.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnThales.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnThales.setForeground(Color.BLACK);
		buttonGroup.add(rdbtnThales);
		getContentPane().add(rdbtnThales, "4, 17");
		
		btnCreate = new ButtonFlat();
		btnCreate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCreate.addActionListener(this);
		btnCreate.setRolloverBackground(new Color(0, 92, 136));
		btnCreate.setBackground(new Color(0, 119, 175));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setText("Cr\u00E9er");
		getContentPane().add(btnCreate, "4, 19, right, default");
		
		getContentPane().setBackground(Color.WHITE);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnCreate){
			if(!rdbtnThales.isSelected() && !rdbtnUbik.isSelected()){
				new DialogFlat().showDialog(LanguageSelector.getLocalizedText(ELabelUI.ERREUR_TYPE.getLabel()),
						LanguageSelector.getLocalizedText(ELabelUI.MES_TYPE_LIV.getLabel()),
						DialogFlat.ERROR_OPERATION,
						DialogFlat.ERROR_ICON);
				return;
			}
			
			int target = 0;
			if(rdbtnThales.isSelected()){
				target = Delivery.THALES;
			}
			else {
				target = Delivery.UBIK;
			}
			
			control.createDelivery(tfDelivery.getText(), target);
						
			dispose();
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen() - pointMouse.x;
		int y = e.getYOnScreen() - pointMouse.y;
		setLocation(x,y);
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
		int xOrigin = e.getXOnScreen()- getX();
		int yOrigin = e.getYOnScreen()- getY();
		pointMouse = new Point(xOrigin, yOrigin);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
