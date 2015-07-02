package view.frame.mainFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.LayerUI;

import model.Mail;
import model.Model;
import model.language.ELabelUI;
import model.language.LanguageSelector;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;
import view.guiComponents.buttons.ButtonFlat;
import view.guiComponents.layer.MailRecipientLayerUI;
import view.guiComponents.scrollBar.ScrollBarFlatUI;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelMail extends PanelObserver<ControllerFrame> implements ActionListener, KeyListener {
	private JEditorPane taMessage;
	private TextFieldFlat tfRecipient;
	private TextFieldFlat tfObject;
	
	private Mail mail;
	private ButtonFlat btnSave;
	
	public PanelMail(ControllerFrame control, Mail mail) {
		super(control);
		this.mail = mail;
		
		setOpaque(false);
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("15px"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("15px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblTitle = new JLabel(mail.getTitle());
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(new Color(0, 119, 175));
		add(lblTitle, "2, 1, 2, 1, right, fill");
		
		JLabel lblRecipient = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.CORRESPONDANT.getLabel()));
		lblRecipient.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRecipient.setForeground(new Color(0, 119, 175));
		add(lblRecipient, "2, 3, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 3, 1");
		
		LayerUI<JTextField> layerUI = new MailRecipientLayerUI();
		tfRecipient = new TextFieldFlat();
		tfRecipient.addKeyListener(this);
		tfRecipient.setColumns(10);
		add(new JLayer<JTextField>(tfRecipient, layerUI), "3, 6, fill, default");
		
		JLabel lblObject = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.OBJET.getLabel()));
		lblObject.setFont(new Font("Dialog", Font.BOLD, 12));
		lblObject.setForeground(new Color(0, 119, 175));
		add(lblObject, "2, 8, 2, 1");
		
		SeparatorFlat separator_1 = new SeparatorFlat();
		add(separator_1, "2, 9, 3, 1");
		
		tfObject = new TextFieldFlat();
		tfObject.addKeyListener(this);
		tfObject.setColumns(10);
		add(tfObject, "3, 11, fill, default");
		
		JLabel lblMessage = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.MESSAGE.getLabel()));
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMessage.setForeground(new Color(0, 119, 175));
		add(lblMessage, "2, 13, 2, 1");
		
		SeparatorFlat separator_2 = new SeparatorFlat();
		add(separator_2, "2, 14, 3, 1");
		
		JLabel affMarqueurs = new JLabel();
		add(affMarqueurs, "3, 15");
		affMarqueurs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		affMarqueurs.setText(LanguageSelector.getLocalizedText(ELabelUI.MARQUEURS.getLabel()));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		add(scrollPane, "3, 17, fill, fill");
		taMessage  = new JEditorPane();
		taMessage.addKeyListener(this);
		taMessage.setBorder(null);
		scrollPane.setViewportView(taMessage);
		
		btnSave = new ButtonFlat("Enregistrer");
		btnSave.addActionListener(this);
		btnSave.setRolloverBackground(new Color(0, 92, 136));
		btnSave.setBackground(new Color(0, 119, 175));
		btnSave.setForeground(Color.WHITE);
		btnSave.setMargin(new Insets(2, 4, 2, 4));
		btnSave.setEnabled(false);
		add(btnSave, "3, 19, right, fill");
		
		update(control.getModel());
	}

	
	protected void update(Model model) {
		tfRecipient.setText(mail.getRecipient());
		tfObject.setText(mail.getObject());
		taMessage.setText(mail.getMessage());
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSave){
			control.setMail(mail,
					tfRecipient.getText(),
					tfObject.getText(),
					taMessage.getText());
			
			btnSave.setEnabled(false);
		}
	}

	
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyReleased(KeyEvent arg0) {
		btnSave.setEnabled(true);
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}
