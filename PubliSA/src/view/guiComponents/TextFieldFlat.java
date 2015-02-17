package view.guiComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class TextFieldFlat extends JPanel implements FocusListener {
	
	private JTextField textField;
	
	private boolean isSelected = false;

	public TextFieldFlat() {		
		SpringLayout springLayout = new SpringLayout();
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(211, 211, 211), 2));
		setPreferredSize(new Dimension(50, 25));
		setLayout(springLayout);
		
		textField = new JTextField();
		textField.addFocusListener(this);
		textField.setBorder(null);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 3, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 3, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -3, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, -3, SpringLayout.EAST, this);
		add(textField);
		textField.setColumns(10);
	}

	private void setBorder(boolean textFieldSelected) {
		if(textFieldSelected == true){
			setBorder(new LineBorder(new Color(0, 119, 175), 2));
		}else{
			setBorder(new LineBorder(new Color(211, 211, 211), 2));
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		isSelected = true;
		setBorder(isSelected);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		isSelected = false;
		setBorder(isSelected);
	}

}
