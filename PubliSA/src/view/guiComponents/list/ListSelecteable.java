package view.guiComponents.list;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


@SuppressWarnings("serial")
public class ListSelecteable extends JList<String> implements ListSelectionListener {

	/**
	 * Create the application.
	 */
	public ListSelecteable() {
		AbstractListModel<String> abs = new AbstractListModel<String>() {
			String[] values = new String[] {"Step 1", "Step 2", "Step3", "Step 4"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		};
		
		setFocusable(false);
		setFixedCellHeight(50);
		setSelectionForeground(Color.WHITE);
		setSelectionBackground(new Color(0, 63, 113));
		setBackground(null);
		setForeground(Color.WHITE);
		setOpaque(false);
		setBorder(null);
		addListSelectionListener(this);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(abs);
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
	    }
	}
}
