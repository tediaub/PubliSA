package view.guiComponents.table;


import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class tableFlate extends JFrame{
	private JTable table;
	private static String[] colonnes = {"Test", 
		"14587",
		"45896"};
	private DefaultTableModel model;
	private JScrollPane spTable4;
	private JPanel panel;

	public tableFlate() {
		super();
		setBounds(new Rectangle(300, 300, 400, 300));
		setUndecorated(true);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,}));		
	    
		table = new JTable(new DefaultTableModel(
			new Object[][] {
				{"116641354613", "1146fferfgerevvvvvefve", "veververvsvdvevv"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"1452", "14587", "45896"
			}
		));
		
		for (int vColIndex = 0; vColIndex < table.getModel().getColumnCount(); vColIndex++) {
			TableColumn col = table.getColumnModel().getColumn(vColIndex);
			//col.setHeaderRenderer(new MyTableHeaderRenderer());
		}
		
		
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		
		spTable4 = new JScrollPane(table);
		spTable4.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		spTable4.setBackground(Color.WHITE);
			
		spTable4.setViewportView(table);	
		
		getContentPane().add(spTable4, "2, 2, fill, fill");
		
		panel = new JPanel();
		spTable4.setColumnHeaderView(panel);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	        try {
	        	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	        	}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tableFlate fl = new tableFlate();
		fl.setVisible(true);
	}

}
