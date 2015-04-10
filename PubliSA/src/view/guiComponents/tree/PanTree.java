package view.guiComponents.tree;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import view.guiComponents.frame.LabelResize;
import view.guiComponents.scrollBar.ScrollBarFlatUI;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class PanTree extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanTree(JTree tree, LabelResize lblResize) {
		setBackground(new Color(240, 240, 240));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20px"),
				ColumnSpec.decode("150px:grow"),
				ColumnSpec.decode("20px"),},
			new RowSpec[] {
				RowSpec.decode("20px"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("20px"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.setViewportView(tree);
		
		add(scrollPane, "2, 2, fill, fill");
		add(lblResize, "3, 1, 1, 3, right, bottom");
	}
}
