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
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicTextPaneUI;

import loading.FilterPubliSaXml;
import loading.LoadFile;
import view.guiComponents.ButtonFlat;
import view.guiComponents.ComboBoxFlatUI;
import view.guiComponents.TextFieldFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.OpeningController;

public class FirstLaunchPanel extends JPanel implements ActionListener {
	
	private boolean isLoaded = false;
	
	private ButtonFlat btnLoad;
	private JPanel cards;
	private JComboBox<String> cbSelect;
	private ButtonFlat btnCreate;

	private TextFieldFlat tfNewUser;

	private OpeningController control;
	
	private static String COMBO = "combo";
	private static String TEXTFIELD = "textfield";
	
	private JLabel lblNewLabel;
	private JTextPane txtpnLaNouvelleVersion;

	public FirstLaunchPanel(OpeningController control) {
		this.control = control;
		
		setSize(340, 340);
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("30px"),
				RowSpec.decode("2px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("7dlu:grow"),
				RowSpec.decode("30px"),
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		JLabel lblNewUser = new JLabel("Nouvel Utilisateur");
		lblNewUser.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewUser.setForeground(Color.WHITE);
		
		lblNewLabel = new JLabel("Bienvenue");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.WHITE);
		add(lblNewLabel, "2, 1");
		
		txtpnLaNouvelleVersion = new JTextPane();
		txtpnLaNouvelleVersion.setSelectedTextColor(Color.BLACK);
		txtpnLaNouvelleVersion.setForeground(Color.WHITE);
		txtpnLaNouvelleVersion.setEditable(false);
		txtpnLaNouvelleVersion.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtpnLaNouvelleVersion.setText("La version 4.0 de PubliSA utilise un nouveau syst\u00E8me\r\nde sauvegarde. Si vous avez utilis\u00E9 une version\r\npr\u00E9cedente de PubliSA, chargez votre fichier \r\nde sauvegarde \"PubliSA.xml\" pour recup\u00E9rer vos \r\ndonn\u00E9es.\r\nSinon, entrez un nouveau nom d'utilisateur...");
		txtpnLaNouvelleVersion.setOpaque(false);
		txtpnLaNouvelleVersion.setUI(new BasicTextPaneUI());
		add(txtpnLaNouvelleVersion, "2, 3, 2, 1, fill, fill");
		
		JLabel lblLoad = new JLabel("Charger un fichier de sauvegarde :");
		lblLoad.setForeground(Color.WHITE);
		lblLoad.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblLoad, "2, 5, fill, fill");
		
		btnLoad = new ButtonFlat("Parcourir");
		btnLoad.addActionListener(this);
		btnLoad.setBackground(Color.WHITE);
		btnLoad.setRolloverBackground(new Color(200,200,200));
		add(btnLoad, "3, 5, left, fill");
		
		
		
		JPanel textField = new JPanel();
		textField.setOpaque(false);
		textField.setBackground(Color.WHITE);
		textField.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNew = new JLabel("Entrer un nom d'utilisateur :");
		lblNew.setForeground(Color.WHITE);
		lblNew.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.add(lblNew, "1, 2");
		
		tfNewUser = new TextFieldFlat();
		textField.add(tfNewUser, "1, 4, fill, default");
		
		
		JPanel combo = new JPanel();
		combo.setOpaque(false);
		combo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblSelect = new JLabel("Sélectionnez un utilisateur :");
		lblSelect.setForeground(Color.WHITE);
		lblSelect.setFont(new Font("Arial", Font.PLAIN, 15));
		combo.add(lblSelect, "1, 2");
		
		cbSelect = new JComboBox<String>();
		cbSelect.setBackground(Color.WHITE);
		cbSelect.setUI(new ComboBoxFlatUI());
		combo.add(cbSelect, "1, 4, fill, default");
		
		cards = new JPanel();
		cards.setOpaque(false);
		cards.setLayout(new CardLayout());
		cards.add(textField, TEXTFIELD);
		cards.add(combo, COMBO);
		add(cards, "2, 7, 2, 1, fill, fill");
		
		btnCreate = new ButtonFlat("Créer");
		btnCreate.addActionListener(this);
		btnCreate.setForeground(Color.BLACK);
		btnCreate.setBackground(Color.WHITE);
		btnCreate.setRolloverBackground(new Color(200,200,200));
		add(btnCreate, "3, 9, fill, fill");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnLoad){
			String path = LoadFile.loadFrame(null, "Ouvrir PubliSA.xml", new FilterPubliSaXml());

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
