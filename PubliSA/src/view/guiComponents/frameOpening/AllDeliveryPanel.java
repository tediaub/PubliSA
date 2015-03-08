package view.guiComponents.frameOpening;

import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import controller.openFrame.OpeningController;

import java.awt.Color;
import java.awt.Dimension;

import view.guiComponents.frame.PanButtonFrame;
import controller.IFrameController;
import view.guiComponents.ButtonFlat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class AllDeliveryPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener{

	private OpeningController control;
	private Point pointMouse;
	private JTable table;
	private ButtonFlat btnBack;
	private JScrollPane scrollPane;
	
	public AllDeliveryPanel(OpeningController control){
		setSize(new Dimension(700, 400));
		this.control = control;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setBackground(new Color(0, 119, 175));
		setLayout(null);
		
		PanButtonFrame panButtonFrame = new PanButtonFrame(control);
		panButtonFrame.setMaximizedVisible(false);
		panButtonFrame.setBounds(640, 0, 60, 20);
		
		
		add(panButtonFrame);
		
		btnBack = new ButtonFlat("Liste des livraisons");
		btnBack.addActionListener(this);
		btnBack.setRolloverBackground(new Color(0, 63, 113));
		btnBack.setBackground(new Color(0, 119, 175));
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setIconTextGap(15);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 27));
		btnBack.setForeground(Color.WHITE);
		btnBack.setIcon(new ImageIcon(AllDeliveryPanel.class.getResource("/iconeSideBarBlue/back.png")));
		btnBack.setBounds(20, 23, 294, 40);
		add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 74, 642, 301);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Nom", "DCR", "Etape"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pointMouse = control.getMouseOnFrame(e.getXOnScreen(), e.getYOnScreen());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getXOnScreen() - pointMouse.x;
		int y = e.getYOnScreen() - pointMouse.y;
		control.setFrameLocation(x, y);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack){
			control.setViewPanel(OpeningFrame.PANEL_MAIN);
		}
	}
}
