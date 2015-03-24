package gugyj;

import java.awt.EventQueue;

import javafx.scene.control.CheckBox;

import javax.swing.JFrame;

import view.guiComponents.table.TableFlat;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class yughj {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yughj window = new yughj();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public yughj() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 239);
		frame.getContentPane().add(scrollPane);
		
		TableFlat tableFlat = new TableFlat(new DefaultTableModel(new Object[]{"Nom", "DCR", "Destinataire", "Etape"}, 0));
		String[] s = {null, null, null, null};
		tableFlat.addRow(s);
		tableFlat.addRow(s);
		tableFlat.addRow(s);
		tableFlat.addRow(s);
		tableFlat.addRow(s);
		tableFlat.addRow(s);
		scrollPane.setViewportView(tableFlat);
	}
}
