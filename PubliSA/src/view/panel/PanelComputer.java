package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.Model;
import view.guiComponents.RadioButtonFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelComputer extends PanelObserver implements KeyListener, ActionListener {
	
	private RadioButtonFlat ckbSec;
	private RadioButtonFlat ckbElac;
	private RadioButtonFlat ckbFcdc;
	
	private ButtonGroup groupComputer;
	private TextFieldFlat textField;

	public PanelComputer(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("4dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("4dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("7dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow(3)"),
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel(GestLangue.getLocalizedText(IHM.COMPUTER.getLabel()));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 2, 3, 1");
		
		SeparatorFlat separatorB = new SeparatorFlat();
		add(separatorB, "2, 4, 11, 1, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel(GestLangue.getLocalizedText(IHM.COMPUTER_TYPE.getLabel()));
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(lblNewLabel_1, "3, 6, 5, 1");
		
		JLabel lblNewLabel_2 = new JLabel(GestLangue.getLocalizedText(IHM.STANDARD.getLabel()));
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		add(lblNewLabel_2, "11, 6");		
		
		ckbSec = new RadioButtonFlat(GestLangue.getLocalizedText(IHM.SEC.getLabel()));
  		ckbSec.addActionListener(this);
		ckbSec.setOpaque(false);
  		ckbElac = new RadioButtonFlat(GestLangue.getLocalizedText(IHM.ELAC.getLabel()));
  		ckbElac.addActionListener(this);
  		ckbElac.setOpaque(false);
  		ckbFcdc = new RadioButtonFlat(GestLangue.getLocalizedText(IHM.FCDC.getLabel()));
  		ckbFcdc.addActionListener(this);
  		ckbFcdc.setOpaque(false);
  		
  		groupComputer = new ButtonGroup();
  		groupComputer.add(ckbSec);
  		groupComputer.add(ckbElac);
  		groupComputer.add(ckbFcdc);
		
		add(ckbSec, "3, 8");
		add(ckbElac, "5, 8");
		add(ckbFcdc, "7, 8");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "9, 6, 1, 3");
		
		textField = new TextFieldFlat();
		textField.addKeyListener(this);
		add(textField, "11, 8, fill, center");
		
		update(control.getModel());
	}
	
	protected void update(Model model){
		textField.setText(model.getMainDelivery().getStandard());
		
		if(model.getMainDelivery().getComputer().contentEquals(Delivery.SEC)){
			ckbSec.setSelected(true);
		}else if(model.getMainDelivery().getComputer().contentEquals(Delivery.ELAC)){
			ckbElac.setSelected(true);
		}else if(model.getMainDelivery().getComputer().contentEquals(Delivery.FCDC)){
			ckbFcdc.setSelected(true);
		}else{
			groupComputer.clearSelection();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ckbSec){
			control.getModel().getMainDelivery().setComputer(Delivery.SEC);
		}else if(e.getSource() == ckbElac){
			control.getModel().getMainDelivery().setComputer(Delivery.ELAC);
		}else if(e.getSource() == ckbFcdc){
			control.getModel().getMainDelivery().setComputer(Delivery.FCDC);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		control.getModel().getMainDelivery().setStandard(textField.getText());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
