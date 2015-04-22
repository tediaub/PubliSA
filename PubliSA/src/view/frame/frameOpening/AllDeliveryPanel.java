package view.frame.frameOpening;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Delivery;
import model.Model;
import view.frame.PanButtonFrame;
import view.frame.mainFrame.panel.PanelObserver;
import view.guiComponents.buttons.ButtonFlat;
import view.guiComponents.scrollBar.ScrollBarFlatUI;
import view.guiComponents.table.TableAllDelivery;
import view.guiComponents.table.TableFlat;
import controller.OpeningController;

@SuppressWarnings("serial")
public class AllDeliveryPanel extends PanelObserver<OpeningController> implements MouseListener, MouseMotionListener, ActionListener{

	private OpeningController control;
	private Point pointMouse;
	private TableFlat table;
	private ButtonFlat btnBack;
	private JScrollPane scrollPane;
	
	public AllDeliveryPanel(OpeningController control){
		super(control);
		
		setSize(new Dimension(700, 400));
		this.control = control;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setBackground(new Color(0,119,175));
		setLayout(null);
		
		PanButtonFrame panButtonFrame = new PanButtonFrame(control);
		panButtonFrame.setMaximizedVisible(false);
		panButtonFrame.setBounds(640, 0, 60, 20);
		
		
		add(panButtonFrame);
		
		btnBack = new ButtonFlat("Liste des livraisons");
		btnBack.addActionListener(this);

		btnBack.setRolloverBackground(new Color(0,63,113));
		btnBack.setBackground(new Color(0,119,175));
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setIconTextGap(15);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 27));
		btnBack.setForeground(Color.WHITE);
		btnBack.setIcon(new ImageIcon(AllDeliveryPanel.class.getResource("/icons/sideBarBlue/back.png")));
		btnBack.setBounds(20, 23, 294, 40);
		add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, new JPanel());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarFlatUI());
		scrollPane.setBounds(30, 74, 642, 301);
		add(scrollPane);
		
		table = new TableAllDelivery(new DefaultTableModel(new Object[]{"Nom", "DCR", "Destinataire", "Etape"}, 0), control);
		scrollPane.setViewportView(table);
		
		update(control.getModel());
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


	@Override
	protected void update(Model model) {
		table.clearTable();
		for(int i = 0; i < control.getModel().getDeliveries().size(); i++) {
			String[] s = new String[4];
			s[0] = control.getModel().getDeliveries().get(i).getName();
			s[1] = control.getModel().getDeliveries().get(i).getDCR();
			
			if(control.getModel().getDeliveries().get(i).getTarget() == Delivery.UBIK){
				s[2] = "UBIK";
			}else{
				s[2] = "THALES";
			}
			
			switch (control.getModel().getDeliveries().get(i).getHighestStep()) {
			case Delivery.STEP1:
				s[3] = "Etape1";
				break;
			case Delivery.STEP2:
				s[3] = "Etape2";
				break;
			case Delivery.STEP3:
				s[3] = "Etape3";
				break;
			case Delivery.STEP4:
				s[3] = "Etape4";
				break;

			default:
				break;
			}
			
			table.addRow(s);
		}
	}
}
