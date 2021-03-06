package view.guiComponents.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import model.Delivery;
import model.language.ELabelUI;
import model.language.LanguageSelector;
import view.frame.dialog.DialogFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelPopUpNewDelivery extends JPanel implements ActionListener {

	private RadioButtonFlat rdbtnThales;
	private RadioButtonFlat rdbtnUbik;
	private ButtonFlat btnCreate;
	private TextFieldFlat tfDelivery;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private ControllerFrame control;
	private JPopupMenu popUp;

	/**
	 * Create the 
	 */
	public PanelPopUpNewDelivery(ControllerFrame control, JPopupMenu popUp) {
		this.control = control;
		this.popUp = popUp;
		
		setOpaque(false);
		
		setSize(450, 280);
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("15px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("15px"),},
			new RowSpec[] {
				RowSpec.decode("15px"),
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
				RowSpec.decode("30px:grow"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("15px"),}));
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 2, 2, 1");
		
		SeparatorFlat separatorFlat = new SeparatorFlat(new Color(0, 119, 175));
		add(separatorFlat, "2, 4, 3, 1");
		
		JLabel lblEntreLeNom = new JLabel("Entr\u00E9e le nom de la nouvelle livraison :");
		lblEntreLeNom.setForeground(Color.BLACK);
		lblEntreLeNom.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblEntreLeNom, "3, 6, 2, 1");
		
		tfDelivery = new TextFieldFlat();
		add(tfDelivery, "3, 8, 2, 1, fill, default");
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDestination.setForeground(new Color(0, 119, 175));
		add(lblDestination, "2, 10, 2, 1");
		
		SeparatorFlat separatorFlat_1 = new SeparatorFlat(new Color(0, 119, 175));
		add(separatorFlat_1, "2, 12, 3, 1");
		
		JLabel lblNewLabel_1 = new JLabel("Selectionner le destinataire de la livraison : ");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblNewLabel_1, "3, 14, 2, 1");
		
		rdbtnUbik = new RadioButtonFlat("UBIK");
		rdbtnUbik.setSelectedIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/icons/radioButton/radioButtonValidate.png")));
		rdbtnUbik.setIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/icons/radioButton/radioButtonNormal.png")));
		rdbtnUbik.setRolloverIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/icons/radioButton/radioButtonHoover.png")));
		rdbtnUbik.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnUbik.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnUbik.setForeground(Color.BLACK);
		buttonGroup.add(rdbtnUbik);
		add(rdbtnUbik, "3, 16");
		
		rdbtnThales = new RadioButtonFlat("THALES");
		rdbtnThales.setIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/icons/radioButton/radioButtonNormal.png")));
		rdbtnThales.setRolloverIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/icons/radioButton/radioButtonHoover.png")));
		rdbtnThales.setSelectedIcon(new ImageIcon(PanelPopUpNewDelivery.class.getResource("/icons/radioButton/radioButtonValidate.png")));
		rdbtnThales.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnThales.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnThales.setForeground(Color.BLACK);
		buttonGroup.add(rdbtnThales);
		add(rdbtnThales, "4, 16");
		
		btnCreate = new ButtonFlat();
		btnCreate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCreate.addActionListener(this);
		btnCreate.setRolloverBackground(new Color(0, 92, 136));
		btnCreate.setBackground(new Color(0, 119, 175));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setText("Cr\u00E9er");
		add(btnCreate, "4, 18, right, default");
	}

	
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
			control.colSideBarBlue();
			popUp.setVisible(false);
			buttonGroup.clearSelection();
			tfDelivery.setText("");
		}
	}
}
