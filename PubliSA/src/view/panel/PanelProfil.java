package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.Delivery;
import model.Model;

import view.guiComponents.SeparatorFlat;
import view.guiComponents.scrollBar.ScrollBarFlatUI;
import view.guiComponents.table.TableAllDelivery;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelProfil extends PanelObserver<ControllerFrame> implements ActionListener {

	String name = LanguageSelector.getLocalizedText(ELabelUI.REGLAGES.getLabel());
	private TableAllDelivery table;
	
	public PanelProfil(ControllerFrame control){
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
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("10px"),}));
		
		JLabel lblTitle = new JLabel("Profil");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(new Color(0, 119, 175));
		add(lblTitle, "6, 1, right, default");
		
		JLabel lblNewLabel = new JLabel("Livraisons");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 3, 3, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 6, 1");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, new JPanel());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarFlatUI());
		
		JLabel lblListeDesLivraisons = new JLabel("Liste des livraisons :");
		add(lblListeDesLivraisons, "4, 6");
		scrollPane.setBounds(30, 74, 642, 301);
		
		table = new TableAllDelivery(new DefaultTableModel(new Object[]{"Nom", "DCR", "Destinataire", "Etape"}, 0), control);
		scrollPane.setViewportView(table);
		add(scrollPane, "4, 8, 3, 1, fill, fill");
		
		update(control.getModel());
	}
	
	@Override
	protected void update(Model model) {
		table.clearTable();
		for(int i = 0; i < control.getModel().getDeliveries().size(); i++) {
			String[] s = new String[4];
			s[0] = control.getModel().getDeliveries().get(i).getName();
			s[1] = control.getModel().getDeliveries().get(i).getDCR();
			
			if(control.getModel().getDeliveries().get(i).getTarget() == Delivery.UBIK){
				s[2] = "UBIK";
			}else{
				s[2] = "THALES";
			}
			
			switch (control.getModel().getDeliveries().get(i).getHighestStep()) {
			case Delivery.STEP1:
				s[3] = "Etape1";
				break;
			case Delivery.STEP2:
				s[3] = "Etape2";
				break;
			case Delivery.STEP3:
				s[3] = "Etape3";
				break;
			case Delivery.STEP4:
				s[3] = "Etape4";
				break;

			default:
				break;
			}
			
			table.addRow(s);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}