package view.frame.mainFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import javax.swing.table.DefaultTableModel;

import jxl.write.WriteException;
import model.Delivery;
import model.Model;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.buttons.ButtonFlat;
import view.guiComponents.scrollBar.ScrollBarFlatUI;
import view.guiComponents.table.TableCellRenderer;
import view.guiComponents.table.TableFlat;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;
import controller.checking.Report;

@SuppressWarnings("serial")
public class PanelCheckStep2 extends PanelObserver<ControllerFrame> implements ActionListener {
	
	private TableFlat table;
	private ButtonFlat btnOptions;
	private JPopupMenu popupMenu;
	private JMenuItem iReport;
	private JMenuItem iDeleteHeader;
	private JMenuItem iDocWord;
	private JLabel lblNbFileSummary;
	private JLabel lblNbFileFolder;
	private JMenuItem iDocExe;
	
	private static String[] columns = {LanguageSelector.getLocalizedText(ELabelUI.DCR.getLabel()),
		LanguageSelector.getLocalizedText(ELabelUI.COL_NOM_PLANCHE.getLabel()),
		LanguageSelector.getLocalizedText(ELabelUI.COL_NOM_SOMMAIRE.getLabel()),
		LanguageSelector.getLocalizedText(ELabelUI.OK_KO.getLabel()),
		LanguageSelector.getLocalizedText(ELabelUI.COMMENTAIRES.getLabel())};

	public PanelCheckStep2(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("25px"),
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("25px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("2px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblName = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.RESULTAT_VERIF.getLabel()));
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(0, 119, 175));
		add(lblName, "2, 2, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 4, 1, fill, fill");
		
		lblNbFileSummary = new JLabel();
		add(lblNbFileSummary, "3, 6");
		
		lblNbFileFolder = new JLabel();
		add(lblNbFileFolder, "3, 8");
		
		btnOptions = new ButtonFlat();
		btnOptions.addActionListener(this);
		btnOptions.setBackground(new Color(240, 240, 240));
		btnOptions.setRolloverBackground(new Color(220, 220, 220));
		btnOptions.setForeground(Color.WHITE);
		btnOptions.setIcon(new ImageIcon(PanelCheckStep2.class.getResource("/icons/step/other.png")));
		add(btnOptions, "4, 6, 1, 3");
		
		iReport = new JMenuItem(LanguageSelector.getLocalizedText(ELabelUI.BT_COMPTE_RENDU.getLabel()));
		iReport.addActionListener(this);
		iDeleteHeader = new JMenuItem("Supprimer les en-têtes");
		iDeleteHeader.addActionListener(this);
		iDocWord = new JMenuItem("Ouvrir le fichier EYDT");
		iDocWord.addActionListener(this);
		iDocExe = new JMenuItem("Ouvrir le logiciel FileCheck MD5");
		iDocExe.addActionListener(this);
		
		popupMenu = new JPopupMenu();
		popupMenu.setUI(new PopUpFlat());
		popupMenu.add(iReport);
		popupMenu.add(iDeleteHeader);
		popupMenu.add(iDocWord);
		popupMenu.add(iDocExe);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new TableFlat(new DefaultTableModel(columns, 0));
		table.setWidth(1, 270);
		table.setWidth(2, 270);
		table.setWidth(4, 500);
		table.setDefaultRenderer(Object.class, new TableCellRenderer(control));
		
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.setViewportView(table);
		add(scrollPane, "2, 10, 3, 1, fill, fill");
		
		update(control.getModel());
	}
	
	
	protected void update(Model model) {
		table.clearTable();
		try{
			for (int i = 0; i < model.getMainDelivery().getDataStep2().size(); i++) {
				table.addRow(model.getMainDelivery().getDataStep2().get(i));
			}
		}catch(NullPointerException e){
			
		}
		
		if(model.getMainDelivery().getTarget() == Delivery.UBIK){
			lblNbFileSummary.setText("Nombre de fichiers .PDF de la DCR dans le sommaire : " 
					+ model.getMainDelivery().getNbFileSummary());
			lblNbFileFolder.setText("Nombre de fichiers .PDF dans le dossier cible : "
					+ model.getMainDelivery().getNbFileFolder());
			
			iDocExe.setVisible(false);
			iDocWord.setVisible(true);
			iDeleteHeader.setVisible(true);
		}else if(model.getMainDelivery().getTarget() == Delivery.THALES){
			lblNbFileSummary.setText("Nombre de fichiers .ASC et .SCH de la DCR dans le sommaire : "
					+ model.getMainDelivery().getNbFileSummary());
			lblNbFileFolder.setText("Nombre de fichiers .ASC et .SCH dans le dossier cible : "
					+ model.getMainDelivery().getNbFileFolder());
			
			iDocExe.setVisible(true);
			iDocWord.setVisible(false);
			iDeleteHeader.setVisible(false);
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOptions){
			popupMenu.show(btnOptions, 0, btnOptions.getHeight());
		}
		else if(e.getSource() == iReport){
			try {
				new Report(control, columns);
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource() == iDeleteHeader){
			control.deleteHeader();
		}else if(e.getSource() == iDocWord){
			control.openWord();
		}else if(e.getSource() == iDocExe){
			control.openExe();
		}
	}
	
	class PopUpFlat extends BasicPopupMenuUI{
    	
    	
    	public void paint(Graphics g, JComponent c) {
    		//background
    		g.setColor(new Color(255,255,255));
    		g.fillRect(0, 0, c.getWidth(), c.getHeight());
    		
    		//border
    		g.setColor(new Color(220,220,220));
    		g.drawRect(0, 0, c.getWidth()-1, c.getHeight()-1);
    	}
    	
    	
    }
}
