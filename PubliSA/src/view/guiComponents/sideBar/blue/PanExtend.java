package view.guiComponents.sideBar.blue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.guiComponents.ButtonFlat;
import view.guiComponents.ButtonFlatPopUp;
import view.guiComponents.PopUpNewDeliveryPanel;
import view.guiComponents.SaveProgress;
import view.guiComponents.ariane.Ariane;
import view.guiComponents.list.ListSelecteable;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanExtend extends JPanel implements ActionListener, MouseMotionListener {

	private JButton btnExtend;

	private JPanel pButtonDown;
	private ButtonFlat btnSettings;
	private ButtonFlat btnUser;
	
	private JPanel pButtonUp;
	private ButtonFlatPopUp btnSave;
	private ButtonFlatPopUp btnNew;
	private ListSelecteable list;

	private ControllerFrame control;

	private Ariane pAriane;

	private SaveProgress panSave;
	
	/**
	 * Create the panel.
	 */
	public PanExtend(ControllerFrame control) {
		this.control = control;
		
		addMouseMotionListener(this);
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("50px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("25px"),
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
		
		btnSave = new ButtonFlatPopUp("Enregistrer");
		panSave = new SaveProgress();
		btnSave.setPopUpPanel(panSave);
		btnSave.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnSave.addActionListener(this);
		btnSave.setMargin(new Insets(2, 2, 2, 2));
		btnSave.setIconTextGap(1);
		btnSave.setIcon(new ImageIcon(PanExtend.class.getResource("/iconeSideBarBlue/save.png")));
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setForeground(Color.WHITE);
		btnSave.setRolloverBackground(new Color(0, 63, 113));
		btnSave.setOpaque(false);
		pButtonUp.add(btnSave);
		
		btnNew = new ButtonFlatPopUp("Nouveau");
		btnNew.setPopUpPanel(new PopUpNewDeliveryPanel(control, btnNew.getPopUp()));
		btnNew.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnNew.setMargin(new Insets(2, 2, 2, 2));
		btnNew.setIconTextGap(1);
		btnNew.setIcon(new ImageIcon(PanExtend.class.getResource("/iconeSideBarBlue/new.png")));
		btnNew.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNew.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNew.setForeground(Color.WHITE);
		btnNew.setRolloverBackground(new Color(0, 63, 113));
		btnNew.setOpaque(false);
		btnNew.addActionListener(this);
		pButtonUp.add(btnNew);
		add(btnExtend, "2, 2, default, center");
		
		list = new ListSelecteable(control);
		control.getModel().addObserver(list);
		add(list, "1, 4, fill, fill");
		
		pAriane = new Ariane(control);
		control.getModel().addObserver(pAriane);
		add(pAriane, "2, 4, fill, fill");
		
		pButtonDown = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pButtonDown.getLayout();
		flowLayout.setHgap(30);
		pButtonDown.setOpaque(false);
		add(pButtonDown, "1, 6, 2, 1, fill, fill");
		
		btnUser = new ButtonFlat("Profil");
		btnUser.setPreferredSize(new Dimension(90, 60));
		btnUser.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnUser.setForeground(Color.WHITE);
		btnUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUser.setIcon(new ImageIcon(PanExtend.class.getResource("/iconeSideBarBlue/user.png")));
		btnUser.setRolloverBackground(new Color(0, 63, 113));
		btnUser.setOpaque(false);
		btnUser.addActionListener(this);
		pButtonDown.add(btnUser);
		
		btnSettings = new ButtonFlat("Réglages");
		btnSettings.setPreferredSize(new Dimension(90, 60));
		btnSettings.setFont(new Font("Dotum", Font.PLAIN, 11));
		btnSettings.setForeground(Color.WHITE);
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSettings.setIcon(new ImageIcon(PanCollapse.class.getResource("/iconeSideBarBlue/gear.png")));
		btnSettings.setRolloverBackground(new Color(0, 63, 113));
		btnSettings.setOpaque(false);
		btnSettings.addActionListener(this);
		pButtonDown.add(btnSettings);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExtend){
			control.colSideBarBlue();
		}else if(e.getSource() == btnSettings){
			control.colSideBarBlue();
			control.extSideBarWhite();
		}else if(e.getSource() == btnSave){
			control.save();
			panSave.processEnd();
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
