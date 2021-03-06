package view.frame.frameOpening;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Delivery;
import model.language.ELabelUI;
import model.language.LanguageSelector;
import view.frame.dialog.DialogFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;
import view.guiComponents.buttons.ButtonFlat;
import view.guiComponents.buttons.RadioButtonFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.OpeningController;

@SuppressWarnings("serial")
public class NewDeliveryPanel extends JPanel implements ActionListener {

	private ButtonFlat btnBack;
	private RadioButtonFlat rdbtnThales;
	private RadioButtonFlat rdbtnUbik;
	private ButtonFlat btnCreate;
	private TextFieldFlat tfDelivery;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private OpeningController control;

	/**
	 * Create the 
	 */
	public NewDeliveryPanel(OpeningController control) {
		this.control = control;
		
		setOpaque(false);
		
		setSize(340,340);
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
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
				RowSpec.decode("9dlu:grow"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		add(lblNewLabel, "2, 3, 2, 1");
		
		SeparatorFlat separatorFlat = new SeparatorFlat(Color.WHITE);
		add(separatorFlat, "2, 5, 3, 1");
		
		JLabel lblEntreLeNom = new JLabel("Entr\u00E9e le nom de la nouvelle livraison :");
		lblEntreLeNom.setForeground(Color.WHITE);
		lblEntreLeNom.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblEntreLeNom, "3, 7, 2, 1");
		
		tfDelivery = new TextFieldFlat();
		add(tfDelivery, "3, 9, 2, 1, fill, default");
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDestination.setForeground(Color.WHITE);
		add(lblDestination, "2, 11, 2, 1");
		
		SeparatorFlat separatorFlat_1 = new SeparatorFlat(Color.WHITE);
		add(separatorFlat_1, "2, 13, 3, 1");
		
		JLabel lblNewLabel_1 = new JLabel("Selectionner le destinataire de la livraison");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblNewLabel_1, "3, 15, 2, 1");
		
		rdbtnUbik = new RadioButtonFlat("UBIK");
		rdbtnUbik.setSelectedIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/icons/radioButton/radioButtonValidateOpen.png")));
		rdbtnUbik.setIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/icons/radioButton/radioButtonNormalOpen.png")));
		rdbtnUbik.setRolloverIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/icons/radioButton/radioButtonNormal.png")));
		rdbtnUbik.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnUbik.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnUbik.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnUbik);
		add(rdbtnUbik, "3, 17");
		
		rdbtnThales = new RadioButtonFlat("THALES");
		rdbtnThales.setIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/icons/radioButton/radioButtonNormalOpen.png")));
		rdbtnThales.setRolloverIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/icons/radioButton/radioButtonNormal.png")));
		rdbtnThales.setSelectedIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/icons/radioButton/radioButtonValidateOpen.png")));
		rdbtnThales.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnThales.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnThales.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnThales);
		add(rdbtnThales, "4, 17");
		
		btnCreate = new ButtonFlat();
		btnCreate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCreate.addActionListener(this);
		btnCreate.setBackground(Color.WHITE);
		btnCreate.setRolloverBackground(new Color(200,200,200));
		btnCreate.setForeground(Color.BLACK);
		btnCreate.setText("Cr\u00E9er");
		add(btnCreate, "4, 19, right, default");
		
		btnBack = new ButtonFlat("Nouvelle livraison");
		btnBack.addActionListener(this);
		btnBack.setIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/icons/sideBarBlue/back.png")));
		btnBack.setRolloverBackground(new Color(0, 63, 113));
		btnBack.setOpaque(false);
		btnBack.setIconTextGap(10);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 27));
		btnBack.setForeground(Color.white);
		add(btnBack, "2, 1, 3, 1, left, fill");
	}

	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnBack){
			control.setViewPanel(OpeningFrame.PANEL_MAIN);
		}
		else if(ae.getSource() == btnCreate){
			
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
		}
		
	}

}
