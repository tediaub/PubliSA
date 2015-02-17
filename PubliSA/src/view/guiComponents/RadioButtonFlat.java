package view.guiComponents;

import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class RadioButtonFlat extends JRadioButton {

	public RadioButtonFlat(String text) {
		setSelectedIcon(new ImageIcon(RadioButtonFlat.class.getResource("/iconeRadioButton/radioButtonValidate.png")));
		setRolloverIcon(new ImageIcon(RadioButtonFlat.class.getResource("/iconeRadioButton/radioButtonHoover.png")));
		setIcon(new ImageIcon(RadioButtonFlat.class.getResource("/iconeRadioButton/radioButtonNormal.png")));
		
		setText(text);
	}
}