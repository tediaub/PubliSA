package view.guiComponents.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.plaf.basic.BasicPopupMenuUI;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class ButtonFlatPopUp extends ButtonFlat implements ActionListener{

	private JPopupMenu popupMenu;
	private panPopUp panelPopUp;
	
	private int marginHigh = 10;
	private int midArrow = 20;
	
	public ButtonFlatPopUp(){
		this("");
	}
	
	public ButtonFlatPopUp(String text) {
		super(text);
		
		addActionListener(this);
		setBackground(Color.WHITE);

		popupMenu = new JPopupMenu();
		popupMenu.setUI(new BasicPopupMenuUI());
		popupMenu.setOpaque(false);
		popupMenu.setBorderPainted(false);
		
		panelPopUp = new panPopUp();
		panelPopUp.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode(marginHigh + "px"),
				RowSpec.decode("default:grow"),}));
		popupMenu.add(panelPopUp);
	}
	
	public JPopupMenu getPopUp(){
		return popupMenu;
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		midArrow = width/2;
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		midArrow = width/2;
	}
	
	public void setPopUpPanel(JPanel panel){
		panelPopUp.add(panel, "1, 2, fill, fill");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		popupMenu.show(this, 0, getHeight() - 5);
	}
	
	private class panPopUp extends JPanel {		
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(new Color(220,220,220));
			g.fillRect(0, marginHigh, getWidth(), getHeight() - marginHigh);
			
			int mid = midArrow;
			for(int i = 0; i < marginHigh; i++){
	            g.drawLine(mid-i, i, mid+i, i);
	        }
			
			g.setColor(Color.WHITE);
			g.fillRect(1, marginHigh + 1, getWidth() - 2, getHeight() - marginHigh - 2);
			
			mid = midArrow - 1;
			for(int i = 0; i < marginHigh; i++){
	            g.drawLine(mid-i, i + 1, mid+i, i + 1);
	        }
		}
	}
}
