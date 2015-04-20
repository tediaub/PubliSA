package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import model.Model;
import view.guiComponents.ButtonFlat;
import view.guiComponents.SeparatorFlat;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelOgc extends PanelObserver<ControllerFrame> implements ActionListener {

	private ButtonFlat btnFolder;

	public PanelOgc(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
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
		
		JLabel lblNewLabel = new JLabel("Fichier OGC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 2, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 4, 1, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.FICHIER_DEZIPPER.getLabel()));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblNewLabel_1, "3, 6");
		
		JLabel lblNewLabel_3 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.OUVRIR_OGC.getLabel()));
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblNewLabel_3, "3, 8");
		
		btnFolder = new ButtonFlat(LanguageSelector.getLocalizedText(ELabelUI.PARCOURIR_VERIFIER.getLabel()));
		btnFolder.addActionListener(this);
		btnFolder.setRolloverBackground(new Color(0, 92, 136));
		btnFolder.setBackground(new Color(0, 119, 175));
		btnFolder.setForeground(Color.WHITE);
		btnFolder.setMargin(new Insets(2, 4, 2, 4));
		add(btnFolder, "4, 8");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnFolder){
			control.checkingFromAkka();
		}
	}

	@Override
	protected void update(Model model) {
		
	}
}