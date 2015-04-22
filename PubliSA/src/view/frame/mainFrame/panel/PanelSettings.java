package view.frame.mainFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.frame.PanButtonFrame;
import view.guiComponents.LabelResize;
import view.guiComponents.TabbedPaneFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelSettings extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
	
	private ControllerFrame control;
	
	private PanButtonFrame panel;
	private JButton btnBack;
	private JLabel lblResize;

	private TabbedPaneFlat tabbedPane;

	private Point pointMouse;

	private boolean isMaximized;

	public PanelSettings(ControllerFrame control) {
		this.control = control;
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.DEFAULT_ROWSPEC,}));
		
		panel = new PanButtonFrame(control);
		add(panel, "4, 1, fill, fill");
		
		btnBack = new JButton("Options");
		btnBack.setIconTextGap(20);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 30));
		btnBack.setForeground(new Color(0, 119, 175));
		btnBack.setIcon(new ImageIcon(PanelSettings.class.getResource("/icons/settings/backBlue.png")));
		btnBack.setRolloverIcon(new ImageIcon(PanelSettings.class.getResource("/icons/settings/backBlueHover.png")));
		btnBack.setContentAreaFilled(false);
		btnBack.addActionListener(this);
		add(btnBack, "2, 2");
		
		tabbedPane = new TabbedPaneFlat();
		tabbedPane.addTab("Profil", new PanelProfil(control));
		for (int i = 0; i < control.getModel().getUser().getMails().size(); i++) {
			tabbedPane.addTab(control.getModel().getUser().getMails().get(i).getShortTitle(),
					new PanelMail(control, control.getModel().getUser().getMails().get(i)), false);
		}
		tabbedPane.addTab("Réglages", new PanelUserSetting(control));
		PanelAbout panDetail = new PanelAbout();
		tabbedPane.addTab(panDetail.getName(), panDetail);
		
		add(tabbedPane, "2, 4, 3, 1, fill, fill");
		
		lblResize = new LabelResize(control);
		lblResize.setIcon(new ImageIcon(PanelSettings.class.getResource("/icons/resize/SWicon.png")));
		add(lblResize, "4, 5, right, top");
	}

	public void actionPerformed(ActionEvent arg0) {
		control.closeSettings();
	}
	
	public void setResizable(boolean resizable) {
		lblResize.setVisible(resizable);
	}
	
	public void changeView(int index){
		tabbedPane.setSelectedIndex(index);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(isMaximized == true){
			isMaximized = control.maximizedFrame();
		}
		int x = e.getXOnScreen() - pointMouse.x;
		int y = e.getYOnScreen() - pointMouse.y;
		control.setFrameLocation(x, y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			isMaximized = control.maximizedFrame();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pointMouse = control.getMouseOnFrame(e.getXOnScreen(), e.getYOnScreen());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
