package view.guiComponents;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class RadioButtonFlat extends JRadioButton {

	public RadioButtonFlat(String text) {
		super(text);
		
		setSelectedIcon(new ImageIcon(RadioButtonFlat.class.getResource("/iconeRadioButton/radioButtonValidate.png")));
		setRolloverIcon(new ImageIcon(RadioButtonFlat.class.getResource("/iconeRadioButton/radioButtonHoover.png")));
		setIcon(new ImageIcon(RadioButtonFlat.class.getResource("/iconeRadioButton/radioButtonNormal.png")));
		setFont(new Font("Dialog", Font.PLAIN, 13));
		setOpaque(false);
	}
}