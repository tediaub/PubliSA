package test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import langue.GestLangue;
import langue.IHM;
import model.Model;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.TextFieldFlat;
import view.panel.PanelObserver;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelMail extends PanelObserver {
	public static JTextArea taMessage = new JTextArea();
	private TextFieldFlat tfRecipient;
	private TextFieldFlat tfObject;
	
	private int mailNumber;
	
	public PanelMail(ControllerFrame control, int mailNumber) {
		super(control);
		this.mailNumber = mailNumber;
		
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("15px"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("15px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel, "2, 1, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 2, 3, 1");
		
		tfRecipient = new TextFieldFlat();
		tfRecipient.setColumns(10);
		add(tfRecipient, "3, 4, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1, "2, 6, 2, 1");
		
		SeparatorFlat separator_1 = new SeparatorFlat();
		add(separator_1, "2, 7, 3, 1");
		
		tfObject = new TextFieldFlat();
		tfObject.setColumns(10);
		add(tfObject, "3, 9, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		add(lblNewLabel_2, "2, 11, 2, 1");
		
		SeparatorFlat separator_2 = new SeparatorFlat();
		add(separator_2, "2, 12, 3, 1");
		
		JLabel affMarqueurs = new JLabel();
		add(affMarqueurs, "3, 13");
		affMarqueurs.setFont(new Font("SansSerif", Font.PLAIN, 12));
		affMarqueurs.setText(GestLangue.getLocalizedText(IHM.MARQUEURS.getLabel()));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		add(scrollPane, "3, 15, fill, fill");
		taMessage.setBorder(null);
		
		scrollPane.setViewportView(taMessage);
		taMessage.setLineWrap(true);
		
		update(control.getModel());
	}

	@Override
	protected void update(Model model) {
		tfRecipient.setText(model.getUser().getMails().get(mailNumber).getRecipient());
		tfObject.setText(model.getUser().getMails().get(mailNumber).getObject());
		taMessage.setText(model.getUser().getMails().get(mailNumber).getMessage());
	}

}