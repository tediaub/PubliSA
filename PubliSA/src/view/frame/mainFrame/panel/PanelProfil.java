package view.frame.mainFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.Delivery;
import model.Model;

import model.language.ELabelUI;
import model.language.LanguageSelector;

import view.frame.dialog.DialogFlat;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.buttons.ButtonFlat;
import view.guiComponents.scrollBar.ScrollBarFlatUI;
import view.guiComponents.table.TableAllDelivery;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelProfil extends PanelObserver<ControllerFrame> implements ActionListener {

	String name = LanguageSelector.getLocalizedText(ELabelUI.REGLAGES.getLabel());
	private TableAllDelivery table;
	
	private ButtonFlat btnDelete;
	private ButtonFlat btnDeleteAll;
	private ButtonFlat btnOpen;
	
	public PanelProfil(ControllerFrame control){
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("25px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("fill:25px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("fill:25px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("fill:25px"),
				ColumnSpec.decode("15px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:25px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("10px"),}));
		
		JLabel lblTitle = new JLabel("Profil");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(new Color(0, 119, 175));
		add(lblTitle, "4, 1, 7, 1, right, default");
		
		JLabel lblNewLabel = new JLabel("Livraisons");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 3, 3, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 10, 1");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, new JPanel());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarFlatUI());
		
		JLabel lblListeDesLivraisons = new JLabel("Liste des livraisons :");
		add(lblListeDesLivraisons, "4, 6, default, bottom");
		
		btnOpen = new ButtonFlat();
		btnOpen.setToolTipText("Ouvrir");
		btnOpen.addActionListener(this);
		btnOpen.setBackground(new Color(240, 240, 240));
		btnOpen.setRolloverBackground(new Color(220, 220, 220));
		btnOpen.setForeground(Color.WHITE);
		btnOpen.setIcon(new ImageIcon(PanelCheckStep2.class.getResource("/icons/profilIcons/open.png")));
		add(btnOpen, "6, 6");
		
		btnDelete = new ButtonFlat();
		btnDelete.setToolTipText("Supprimer");
		btnDelete.addActionListener(this);
		btnDelete.setBackground(new Color(240, 240, 240));
		btnDelete.setRolloverBackground(new Color(220, 220, 220));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setIcon(new ImageIcon(PanelCheckStep2.class.getResource("/icons/profilIcons/delete.png")));
		add(btnDelete, "8, 6");
		
		btnDeleteAll = new ButtonFlat();
		btnDeleteAll.setToolTipText("Tout supprimer");
		btnDeleteAll.addActionListener(this);
		btnDeleteAll.setBackground(new Color(240, 240, 240));
		btnDeleteAll.setRolloverBackground(new Color(220, 220, 220));
		btnDeleteAll.setForeground(Color.WHITE);
		btnDeleteAll.setIcon(new ImageIcon(PanelCheckStep2.class.getResource("/icons/profilIcons/deleteall.png")));
		add(btnDeleteAll, "10, 6");
		
		scrollPane.setBounds(30, 74, 642, 301);
		
		table = new TableAllDelivery(new DefaultTableModel(new Object[]{"Nom", "DCR", "Destinataire", "Etape"}, 0), control);
		scrollPane.setViewportView(table);
		add(scrollPane, "4, 8, 7, 1, fill, fill");
		
		update(control.getModel());
	}
	
	
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

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnOpen){
			if(table.getSelectedRowCount() == 1){
				Object obj = table.getModel().getValueAt(table.getSelectedRow(), 0);
				if(obj instanceof String){
					control.openDelivery((String) obj);
				}
			}
		}else if(e.getSource() == btnDelete){
			for (Integer row : table.getSelectedRows()) {
				Object obj = table.getModel().getValueAt(row, 0);
				if(obj instanceof String){
					int out = new DialogFlat().showDialog("PubliSA", "<html>Voulez vous supprimer cette livraison :<br>" + obj + ".</html>",
							DialogFlat.ASK_OPERATION,
							DialogFlat.INFORMATION_ICON);
					
					if(out == DialogFlat.VALIDATE)control.deleteDelivery(control.getDelivery((String) obj));
				}
			}
		}else if(e.getSource() == btnDeleteAll){
			int out = new DialogFlat().showDialog("PubliSA", "Voulez vous supprimer toutes les livraisons.",
					DialogFlat.ASK_OPERATION,
					DialogFlat.INFORMATION_ICON);
			if(out == DialogFlat.VALIDATE)control.deleteAllDeliveries();
		}
	}
}