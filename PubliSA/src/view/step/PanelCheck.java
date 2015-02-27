package view.step;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import langue.GestLangue;
import langue.IHM;
import view.guiComponents.SeparatorFlat;
import view.guiComponents.table.TableFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class PanelCheck extends JPanel {
	
	private TableFlat table;
	private JButton btnOptions;

	public PanelCheck() {
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
		
		JLabel lblName = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.RESULTAT_VERIF.getLabel()));
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(0, 119, 175));
		add(lblName, "2, 2, 2, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 4, 1, fill, fill");
		
		JLabel lblNombreDeFichiersSom = new JLabel("New label");
		lblNombreDeFichiersSom.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblNombreDeFichiersSom, "3, 6");
		
		JLabel lblNombreDeFichiersDos = new JLabel("New label");
		lblNombreDeFichiersDos.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblNombreDeFichiersDos, "3, 8");
		
		btnOptions = new JButton();
		btnOptions.setIcon(new ImageIcon(PanelCheck.class.getResource("/iconeStep2/other.png")));
		btnOptions.setContentAreaFilled(false);
		add(btnOptions, "4, 6, 1, 3");
		
		table = new TableFlat();
		add(table, "2, 10, 3, 1, fill, fill");
	}
}
