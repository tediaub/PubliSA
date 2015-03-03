package view.guiComponents.firstLaunch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.guiComponents.ButtonFlat;
import view.guiComponents.TextFieldFlat;
import view.guiComponents.frame.PanButtonFrame;
import view.guiComponents.frameOpening.OpeningFrame;
import view.guiComponents.frameOpening.PanelTemplate;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.openFrame.OpeningController;
import view.guiComponents.SeparatorFlat;
import javax.swing.SwingConstants;

public class FirstLaunchDialog extends PanelTemplate {

	private OpeningController control;

	public FirstLaunchDialog(OpeningController control) {
		super(control);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("21px"),
				ColumnSpec.decode("104px"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("24px:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("33px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,}));
		setContainer(panel);
		
		JLabel lblNewLabel = new JLabel("Nouvel Utilisateur");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		setMenuComponent(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Charger un fichier de sauvegarde (Optionnel) :");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(lblNewLabel_2, "2, 2, fill, fill");
		
		ButtonFlat buttonFlat_1 = new ButtonFlat("Parcourir");
		buttonFlat_1.setBackground(new Color(240,240,240));
		buttonFlat_1.setRolloverBackground(new Color(200,200,200));
		panel.add(buttonFlat_1, "4, 2, left, fill");
		
		SeparatorFlat separatorFlat = new SeparatorFlat();
		panel.add(separatorFlat, "2, 4, 4, 1");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "2, 6, 3, 1, fill, fill");
		
		ButtonFlat buttonFlat_2 = new ButtonFlat("Créer");
		buttonFlat_2.setForeground(Color.WHITE);
		buttonFlat_2.setBackground(new Color(0, 119, 175));
		panel.add(buttonFlat_2, "4, 8, left, fill");
	}
}
