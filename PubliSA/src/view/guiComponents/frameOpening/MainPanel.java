package view.guiComponents.frameOpening;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.guiComponents.ButtonFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.OpeningController;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements ActionListener{

	private JLabel lblWelcome;
	private ButtonFlat btnOpenDelivery;
	private ButtonFlat btnNewDelivery;
	private ButtonFlat btnOtherDelivery;
	private JLabel lblNewLabel;

	private OpeningController control;
	/**
	 * Create the application.
	 */
	public MainPanel(OpeningController control) {
		setBounds(new Rectangle(0, 0, 340, 340));
		setOpaque(false);
		this.control = control;		
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("20px"),
				RowSpec.decode("40px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("47px"),
				RowSpec.decode("7dlu:grow"),
				RowSpec.decode("47px"),
				RowSpec.decode("7dlu:grow"),
				RowSpec.decode("47px"),
				RowSpec.decode("40px"),}));
		lblNewLabel = new JLabel("Choissisez l'action \u00E0 r\u00E9aliser :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		add(lblNewLabel, "2, 3");
		
		btnNewDelivery = new ButtonFlat("Nouvelle livraison");
		btnNewDelivery.setRolloverBackground(new Color(220, 220, 220));
		btnNewDelivery.setBackground(new Color(255, 255, 255));
		btnNewDelivery.addActionListener(this);
		btnNewDelivery.setIcon(new ImageIcon(MainPanel.class.getResource("/iconeBegining/addDocument.png")));
		btnNewDelivery.setIconTextGap(20);
		btnNewDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewDelivery.setFont(new Font("Dialog", Font.PLAIN, 18));
		add(btnNewDelivery, "2, 5, fill, fill");
		
		btnOpenDelivery = new ButtonFlat("Ouvrir la livraison pr\u00E9cedente");
		btnOpenDelivery.setRolloverBackground(new Color(220, 220, 220));
		btnOpenDelivery.setBackground(new Color(255, 255, 255));
		btnOpenDelivery.addActionListener(this);
		btnOpenDelivery.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnOpenDelivery.setIconTextGap(20);
		btnOpenDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		btnOpenDelivery.setIcon(new ImageIcon(MainPanel.class.getResource("/iconeBegining/timer.png")));
		if(control.getModel().getMainDelivery() == null){
			btnOpenDelivery.setEnabled(false);
		}
		add(btnOpenDelivery, "2, 7, fill, fill");
		
		btnOtherDelivery = new ButtonFlat("Voir toutes les livraisons");
		btnOtherDelivery.setRolloverBackground(new Color(220, 220, 220));
		btnOtherDelivery.setBackground(new Color(255, 255, 255));
		btnOtherDelivery.addActionListener(this);
		btnOtherDelivery.setIcon(new ImageIcon(MainPanel.class.getResource("/iconeBegining/checking.png")));
		btnOtherDelivery.setIconTextGap(20);
		btnOtherDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		btnOtherDelivery.setFont(new Font("Dialog", Font.PLAIN, 18));
		if(control.getModel().getDeliveries().size() == 0){
			btnOtherDelivery.setEnabled(false);
		}
		add(btnOtherDelivery, "2, 9, fill, fill");
		
		lblWelcome = new JLabel("Bienvenue");
		lblWelcome.setFont(new Font("Arial", Font.PLAIN, 30));
		lblWelcome.setForeground(Color.white);
		add(lblWelcome, "2, 1, fill, fill");
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnNewDelivery){
			control.setViewPanel(OpeningFrame.PANEL_NEW_DELIVERY);
		}else if(ae.getSource() == btnOpenDelivery){
			control.openDelivery();
		}else if(ae.getSource() == btnOtherDelivery){
			control.setViewPanel(OpeningFrame.PANEL_ALL_DELIVERY);
		}
	}
}
