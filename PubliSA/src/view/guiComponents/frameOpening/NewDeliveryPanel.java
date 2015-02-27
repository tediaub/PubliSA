package view.guiComponents.frameOpening;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import view.guiComponents.ButtonFlat;
import view.guiComponents.RadioButtonFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.openFrame.OpeningController;

@SuppressWarnings("serial")
public class NewDeliveryPanel extends PanelTemplate implements ActionListener {

	private ButtonFlat btnBack;
	private RadioButtonFlat rdbtnThales;
	private RadioButtonFlat rdbtnUbik;
	private ButtonFlat btnCreate;
	private TextFieldFlat tfDelivery;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public NewDeliveryPanel(OpeningController control) {
		super(control);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		setContainer(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("fill:2px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		panel.add(lblNewLabel, "2, 2, 2, 1");
		
		SeparatorFlat separatorFlat = new SeparatorFlat();
		panel.add(separatorFlat, "2, 4, 5, 1");
		
		JLabel lblEntreLeNom = new JLabel("Entr\u00E9e le nom de la nouvelle livraison :");
		lblEntreLeNom.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(lblEntreLeNom, "3, 6, 2, 1");
		
		tfDelivery = new TextFieldFlat();
		panel.add(tfDelivery, "3, 8, 3, 1, fill, default");
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDestination.setForeground(new Color(0, 119, 175));
		panel.add(lblDestination, "2, 10, 2, 1");
		
		SeparatorFlat separatorFlat_1 = new SeparatorFlat();
		panel.add(separatorFlat_1, "2, 12, 5, 1");
		
		JLabel lblNewLabel_1 = new JLabel("Selectionner le destinataire de la livraison");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(lblNewLabel_1, "3, 14, 2, 1");
		
		rdbtnUbik = new RadioButtonFlat((String) null);
		buttonGroup.add(rdbtnUbik);
		rdbtnUbik.setText("UBIK");
		panel.add(rdbtnUbik, "3, 16");
		
		rdbtnThales = new RadioButtonFlat((String) null);
		buttonGroup.add(rdbtnThales);
		rdbtnThales.setText("THALES");
		panel.add(rdbtnThales, "4, 16, 2, 1");
		
		btnCreate = new ButtonFlat();
		btnCreate.addActionListener(this);
		btnCreate.setBackground(new Color(0, 119, 175));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setText("Cr\u00E9er");
		panel.add(btnCreate, "5, 18");
		
		btnBack = new ButtonFlat("Nouvelle livraison");
		btnBack.addActionListener(this);
		btnBack.setIcon(new ImageIcon(NewDeliveryPanel.class.getResource("/iconeSideBarBlue/back.png")));
		btnBack.setRolloverBackground(new Color(0, 63, 113));
		btnBack.setOpaque(false);
		btnBack.setIconTextGap(20);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 20));
		btnBack.setForeground(Color.white);
		setMenuComponent(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnBack){
			control.setViewPanel(OpeningFrame.PANEL_MAIN);
		}
		else if(ae.getSource() == btnCreate){
			
			if(!rdbtnThales.isSelected() && !rdbtnUbik.isSelected()){
				JOptionPane.showMessageDialog(null, 
						GestLangue.getInstance().getLocalizedText(IHM.MES_TYPE_LIV.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.ERREUR_TYPE.getLabel()),
						JOptionPane.ERROR_MESSAGE);
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
			control.createMainFrame();
			control.closeFrame();
		}
		
	}

}
