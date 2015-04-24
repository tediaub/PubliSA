package view.guiComponents;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import view.guiComponents.ui.TabbedPaneUI;

@SuppressWarnings("serial")
public class TabbedPaneFlat extends JTabbedPane {

	ArrayList<Boolean> tabLevel = new ArrayList<Boolean>();
	
	public TabbedPaneFlat() {
		this(JTabbedPane.LEFT);
	}

	public TabbedPaneFlat(int arg0) {
		super(arg0);
		setBackground(Color.WHITE);
		setBorder(null);           
		setUI(new TabbedPaneUI());
	}
	
	public void addTab(String title, Component component, boolean isTitle) {
		tabLevel.add(isTitle);
		super.addTab(title, component);
	}
	
	@Override
	public void addTab(String title, Component component) {
		tabLevel.add(true);
		super.addTab(title, component);
	}

	public boolean getLevel(int tabIndex) {
		return tabLevel.get(tabIndex);
	}
}
