package view.frame.mainFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import model.Model;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.buttons.ButtonFlat;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;
import controller.loading.FilterDOC;
import controller.loading.FilterEXE;
import controller.loading.LoadSaveFile;

@SuppressWarnings("serial")
public class PanelUserSetting extends PanelObserver<ControllerFrame> implements ActionListener {

	String name = LanguageSelector.getLocalizedText(ELabelUI.REGLAGES.getLabel());
	private JLabel lblFileCheck;
	private JLabel lblDocEYDT;
	private ButtonFlat btnEYDT;
	private ButtonFlat btnFileCheck;
	private JCheckBox chckbxDeleteDelivery;
	
	public PanelUserSetting(ControllerFrame control){
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("25px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("15px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTitle = new JLabel(name);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(new Color(0, 119, 175));
		add(lblTitle, "6, 1, right, default");
		
		JLabel lblNewLabel = new JLabel("Livraison");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 3, 3, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 6, 1");
		
		chckbxDeleteDelivery = new JCheckBox(LanguageSelector.getLocalizedText(ELabelUI.SUPPR_LIV_END.getLabel()));
		chckbxDeleteDelivery.addActionListener(this);
		chckbxDeleteDelivery.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxDeleteDelivery.setIcon(new ImageIcon(PanelUserSetting.class.getResource("/iconeCheckBox/ckbUnvalidate.png")));
		chckbxDeleteDelivery.setRolloverIcon(new ImageIcon(PanelUserSetting.class.getResource("/iconeCheckBox/ckbHover.png")));
		chckbxDeleteDelivery.setSelectedIcon(new ImageIcon(PanelUserSetting.class.getResource("/iconeCheckBox/ckbValidate.png")));
		chckbxDeleteDelivery.setIconTextGap(20);
		add(chckbxDeleteDelivery, "4, 6, 3, 1");
		
		JLabel label = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.REPERTOIRE_FILECHECK.getLabel()));
		label.setForeground(new Color(0, 119, 175));
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		add(label, "2, 8, 3, 1");
		
		SeparatorFlat separatorFlat = new SeparatorFlat();
		add(separatorFlat, "2, 9, 6, 1");
		
		JLabel lblNewLabel_1 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.INFO_FILECHECK.getLabel()));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblNewLabel_1, "4, 11");
		
		JLabel lblNewLabel_3 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.PATH_FILECHECK.getLabel()));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblNewLabel_3, "4, 13");
		
		lblFileCheck = new JLabel();
		lblFileCheck.setForeground(new Color(150, 150, 150));
		lblFileCheck.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblFileCheck, "4, 15, 3, 1");
		
		btnFileCheck = new ButtonFlat("Parcourir");
		btnFileCheck.addActionListener(this);
		btnFileCheck.setRolloverBackground(new Color(220, 220, 220));
		btnFileCheck.setBackground(new Color(240, 240, 240));
		add(btnFileCheck, "6, 13");
				
		JLabel label_1 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.REPERTOIRE_EYDT.getLabel()));
		label_1.setForeground(new Color(0, 119, 175));
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		add(label_1, "2, 17, 5, 1");
		
		SeparatorFlat separatorFlat_1 = new SeparatorFlat();
		add(separatorFlat_1, "2, 18, 6, 1");
		
		JLabel label_2 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.INFO_EYDT.getLabel()));
		label_2.setFont(new Font("Arial", Font.PLAIN, 11));
		add(label_2, "4, 20");
		
		JLabel label_3 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.PATH_EYDT.getLabel()));
		label_3.setFont(new Font("Arial", Font.PLAIN, 11));
		add(label_3, "4, 22");
		
		btnEYDT = new ButtonFlat("Parcourir");
		btnEYDT.addActionListener(this);
		btnEYDT.setRolloverBackground(new Color(220, 220, 220));
		btnEYDT.setBackground(new Color(240, 240, 240));
		add(btnEYDT, "6, 22");
		
		lblDocEYDT = new JLabel();
		lblDocEYDT.setForeground(new Color(150, 150, 150));
		lblDocEYDT.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblDocEYDT, "4, 24, 3, 1");
		
		update(control.getModel());
	}

	@Override
	protected void update(Model model) {
		lblFileCheck.setText(model.getUser().getPathExe());
		lblDocEYDT.setText(model.getUser().getPathWord());
		chckbxDeleteDelivery.setSelected(model.getUser().getDeleteFinishDelivery());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnFileCheck){
			String path = LoadSaveFile.loadFrame(control.getModel().getUser().getPathExe(), "Chemin FileCheck", new FilterEXE());
			if (path == null){
				return;
			}
			control.getModel().getUser().setPathExe(path);
		}
		else if(e.getSource() == btnEYDT){
			String path = LoadSaveFile.loadFrame(control.getModel().getUser().getPathWord(), "Chemin EYDT", new FilterDOC());
			if (path == null){
				return;
			}
			control.getModel().getUser().setPathWord(path);
		}
		else if(e.getSource() == chckbxDeleteDelivery){
			control.getModel().getUser().setDeleteFinishDelivery(chckbxDeleteDelivery.isSelected());
		}
	}
}