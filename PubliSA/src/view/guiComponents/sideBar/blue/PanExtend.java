package view.guiComponents.sideBar.blue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.FrameController;

import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Font;

@SuppressWarnings("serial")
public class PanExtend extends JPanel implements ActionListener, MouseMotionListener {

	private JButton btnExtend;

	private FrameController controller;

	private JPanel pButtonDown;
	private JButton btnSettings;
	private JButton btnUser;
	
	private JPanel pButtonUp;
	private JButton btnSave;
	private JButton btnNew;
	
	/**
	 * Create the panel.
	 */
	public PanExtend(FrameController control) {
		controller = control;
		
		addMouseMotionListener(this);
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("50px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		setBackground(new Color(0, 119, 175));
		
		btnExtend = new JButton();
		btnExtend.setContentAreaFilled(false);
		btnExtend.setIcon(new ImageIcon(PanExtend.class.getResource("/iconeSideBarBlue/back.png")));
		btnExtend.addActionListener(this);
		
		pButtonUp = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pButtonUp.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pButtonUp.setOpaque(false);
		add(pButtonUp, "1, 2, fill, fill");
		
		btnSave = new JButton("Enregistrer");
		btnSave.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnSave.setMargin(new Insets(2, 2, 2, 2));
		btnSave.setIconTextGap(1);
		btnSave.setIcon(new ImageIcon(PanExtend.class.getResource("/iconeSideBarBlue/save.png")));
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setForeground(Color.WHITE);
		btnSave.setContentAreaFilled(false);
		pButtonUp.add(btnSave);
		
		btnNew = new JButton("Nouveau");
		btnNew.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnNew.setMargin(new Insets(2, 2, 2, 2));
		btnNew.setIconTextGap(1);
		btnNew.setIcon(new ImageIcon(PanExtend.class.getResource("/iconeSideBarBlue/new.png")));
		btnNew.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNew.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNew.setForeground(Color.WHITE);
		btnNew.setContentAreaFilled(false);
		pButtonUp.add(btnNew);
		add(btnExtend, "2, 2, default, center");
		
		pButtonDown = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pButtonDown.getLayout();
		flowLayout.setHgap(30);
		pButtonDown.setOpaque(false);
		add(pButtonDown, "1, 6, 2, 1, fill, fill");
		
		btnUser = new JButton("Profil");
		btnUser.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnUser.setForeground(Color.WHITE);
		btnUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUser.setContentAreaFilled(false);
		btnUser.setIcon(new ImageIcon(PanExtend.class.getResource("/iconeSideBarBlue/user.png")));
		btnUser.addActionListener(this);
		pButtonDown.add(btnUser);
		
		btnSettings = new JButton("Réglages");
		btnSettings.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnSettings.setForeground(Color.WHITE);
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSettings.setContentAreaFilled(false);
		btnSettings.setIcon(new ImageIcon(PanCollapse.class.getResource("/iconeSideBarBlue/gear.png")));
		btnSettings.addActionListener(this);
		pButtonDown.add(btnSettings);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExtend){
			controller.colSideBarBlue();
		}else if(e.getSource() == btnSettings){
			controller.colSideBarBlue();
			controller.extSideBarWhite();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
