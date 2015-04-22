package view.guiComponents.buttons;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class RadioButtonFlat extends JRadioButton {

	public RadioButtonFlat(String text) {
		super(text);
		
		setSelectedIcon(new ImageIcon(RadioButtonFlat.class.getResource("/icons/radioButton/radioButtonValidate.png")));
		setRolloverIcon(new ImageIcon(RadioButtonFlat.class.getResource("/icons/radioButton/radioButtonHoover.png")));
		setIcon(new ImageIcon(RadioButtonFlat.class.getResource("/icons/radioButton/radioButtonNormal.png")));
		setFont(new Font("Dialog", Font.PLAIN, 13));
		setOpaque(false);
	}
}