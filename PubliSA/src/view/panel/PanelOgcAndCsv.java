package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import langue.GestLangue;
import langue.IHM;
import loading.FilterCSV;
import loading.FilterOGC;
import loading.LoadFile;
import model.Model;
import view.guiComponents.ButtonFlat;
import view.guiComponents.SeparatorFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelOgcAndCsv extends PanelObserver implements ActionListener{

	private JLabel lblPathOGC;
	private JLabel lblPathCSV;
	
	private ButtonFlat btnCSV;
	private ButtonFlat btnChecking;
	private ButtonFlat btnOGC;

	public PanelOgcAndCsv(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("15dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Chargement des fichiers \u00E0 v\u00E9rifier");
		lblNewLabel.setForeground(new Color(0, 119, 175));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		add(lblNewLabel, "2, 2, 2, 1");
		
		SeparatorFlat separatorFlat = new SeparatorFlat();
		add(separatorFlat, "2, 4, 5, 1");
		
		JLabel lblOGC = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.OUVRIR_OGC_ETAPE4.getLabel()));
  		lblOGC.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblOGC, "3, 6");
		
		lblPathOGC = new JLabel();
		lblPathOGC.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPathOGC.setForeground(Color.GRAY);
		add(lblPathOGC, "3, 8");
		
		btnOGC = new ButtonFlat("Parcourir");
		btnOGC.addActionListener(this);
		btnOGC.setRolloverBackground(new Color(220, 220, 220));
		btnOGC.setBackground(new Color(240, 240, 240));
		add(btnOGC, "5, 6, 1, 3, default, center");
		
		JLabel lblCSV = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.OUVRIR_CSV.getLabel()));
		lblCSV.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblCSV, "3, 10");
		
		lblPathCSV = new JLabel();
		lblPathCSV.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPathCSV.setForeground(Color.GRAY);
		add(lblPathCSV, "3, 12");
		
		btnCSV = new ButtonFlat("Parcourir");
		btnCSV.addActionListener(this);
		btnCSV.setRolloverBackground(new Color(220, 220, 220));
		btnCSV.setBackground(new Color(240, 240, 240));
		add(btnCSV, "5, 10, 1, 3, default, center");
				
		btnChecking = new ButtonFlat("Lancer la v\u00E9rification");
		btnChecking.addActionListener(this);
		btnChecking.setRolloverBackground(new Color(0, 92, 136));
		btnChecking.setBackground(new Color(0, 119, 175));
		btnChecking.setForeground(Color.WHITE);
		add(btnChecking, "3, 14, 3, 1, center, default");
		
		update(control.getModel());
	}

	@Override
	protected void update(Model model) {
		lblPathOGC.setText(model.getMainDelivery().getPathOGC());
		lblPathCSV.setText(model.getMainDelivery().getPathCSV());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCSV){
			String path = LoadFile.loadFrame(control.getModel().getMainDelivery().getPathCSV(), "Ouvrir fichier CSV", new FilterCSV());
			if(path == null){return;}
			control.getModel().getMainDelivery().setPathCSV(path);
		}else if(e.getSource() == btnOGC){
			String path = LoadFile.loadFrame(control.getModel().getMainDelivery().getPathOGC(), "Ouvrir fichier OGC", new FilterOGC());
			if(path == null){return;}
			control.getModel().getMainDelivery().setPathOGC(path);
		}else if(e.getSource() == btnChecking){
			
		}
	}
}