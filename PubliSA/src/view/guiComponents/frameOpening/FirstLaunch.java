package view.guiComponents.frameOpening;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import loading.LoadFile;
import model.saveLoad.XmlLoader;
import view.guiComponents.ButtonFlat;
import view.guiComponents.ComboBoxFlatUI;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.openFrame.OpeningController;

public class FirstLaunch extends PanelTemplate implements ActionListener {
	
	private boolean isLoaded = false;
	
	private ButtonFlat btnLoad;
	private JPanel cards;
	private JComboBox cbSelect;
	private ButtonFlat btnCreate;

	private TextFieldFlat tfNewUser;
	
	private static String COMBO = "combo";
	private static String TEXTFIELD = "textfield";

	public FirstLaunch(OpeningController control) {
		super(control);
		
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);
		container.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("21px"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("30px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("30px"),}));
		setContainer(container);
		
		JLabel lblNewUser = new JLabel("Nouvel Utilisateur");
		lblNewUser.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewUser.setForeground(Color.WHITE);
		setMenuComponent(lblNewUser);
		
		JLabel lblLoad = new JLabel("Charger un fichier de sauvegarde (Optionnel) :");
		lblLoad.setFont(new Font("Dialog", Font.PLAIN, 13));
		container.add(lblLoad, "2, 2, fill, fill");
		
		btnLoad = new ButtonFlat("Parcourir");
		btnLoad.addActionListener(this);
		btnLoad.setBackground(new Color(240,240,240));
		btnLoad.setRolloverBackground(new Color(200,200,200));
		container.add(btnLoad, "4, 2, left, fill");
		
		SeparatorFlat separatorFlat = new SeparatorFlat();
		container.add(separatorFlat, "2, 4, 4, 1");
		
		
		
		JPanel textField = new JPanel();
		textField.setBackground(Color.WHITE);
		textField.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNew = new JLabel("New label");
		textField.add(lblNew, "1, 2");
		
		tfNewUser = new TextFieldFlat();
		textField.add(tfNewUser, "1, 4, fill, default");
		
		
		JPanel combo = new JPanel();
		combo.setBackground(Color.WHITE);
		combo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblSelect = new JLabel("New label");
		combo.add(lblSelect, "1, 2");
		
		cbSelect = new JComboBox();
		cbSelect.setUI(new ComboBoxFlatUI());
		combo.add(cbSelect, "1, 4, fill, default");
		
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		cards.add(textField, TEXTFIELD);
		cards.add(combo, COMBO);
		container.add(cards, "2, 6, 3, 1, fill, fill");
		
		btnCreate = new ButtonFlat("Créer");
		btnCreate.addActionListener(this);
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setBackground(new Color(0, 119, 175));
		container.add(btnCreate, "4, 8, fill, fill");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnLoad){
			String path = LoadFile.loadFrame(null, "test", null);

			if(path != null){
				control.createXml(path);
				ArrayList<String> array = control.getXml().getUsers();
				
				for (int i = 0; i < array.size(); i++) {
					cbSelect.addItem(array.get(i));
				}
				
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, COMBO);
				
				isLoaded = true;
			}
		}
		if(arg0.getSource() == btnCreate){
			if(isLoaded){
				control.getXml().loadUser(cbSelect.getSelectedItem().toString(), control);
			}else{
				control.createUser(tfNewUser.getText());
			}
		}
		
	}
}
