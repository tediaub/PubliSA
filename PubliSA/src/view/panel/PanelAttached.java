package view.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Delivery;
import model.Model;
import view.guiComponents.SeparatorFlat;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelAttached extends PanelObserver {

	private JLabel textInfo;

	public PanelAttached(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				FormFactory.GLUE_COLSPEC,
				ColumnSpec.decode("20mm"),
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("5mm"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("30dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("7mm"),}));
		
		JLabel lblName = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.A_JOINDRE.getLabel()));
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(0, 119, 175));
		add(lblName, "2, 2, 4, 1");
		
		JLabel lblAprsLaCration = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.APRES_MAIL.getLabel()));
		lblAprsLaCration.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblAprsLaCration, "5, 5");
		
		textInfo = new JLabel();
		textInfo.setForeground(Color.GRAY);
		add(textInfo, "5, 7");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 3, 4, 1, fill, fill");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(PanelAttached.class.getResource("/iconeAttachment/attachment.png")));
		add(lblNewLabel, "3, 5, 1, 3");
		
		update(control.getModel());
	}

	@Override
	protected void update(Model model) {
		if(model.getMainDelivery().getTarget() == Delivery.UBIK){
			textInfo.setText(LanguageSelector.getLocalizedText(ELabelUI.A_JOINDRE_UBIK.getLabel()));
		}
		else if(model.getMainDelivery().getTarget() == Delivery.THALES){
			textInfo.setText(LanguageSelector.getLocalizedText(ELabelUI.A_JOINDRE_THALES.getLabel()));
		}
	}

}
