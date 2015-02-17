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
			String[] values = new String[] {"jaune", "noir"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		};
		
		setSelectionBackground(new Color(0, 63, 113));
		setBounds(10, 6, 84, 254);
		setBackground(null);
		setForeground(Color.WHITE);
		setVerifyInputWhenFocusTarget(false);
		setFocusTraversalKeysEnabled(false);
		setFocusable(false);
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
