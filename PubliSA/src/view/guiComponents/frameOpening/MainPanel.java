package view.guiComponents.frameOpening;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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

import controller.openFrame.OpeningController;

@SuppressWarnings("serial")
public class MainPanel extends PanelTemplate implements ActionListener{

	private JLabel lblWelcome;
	private ButtonFlat btnOpenDelivery;
	private ButtonFlat btnNewDelivery;
	private ButtonFlat btnOtherDelivery;
	private JLabel lblNewLabel;

	/**
	 * Create the application.
	 */
	public MainPanel(OpeningController control) {
		super(control);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		setContainer(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("378px:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("40px"),
				RowSpec.decode("47px"),
				RowSpec.decode("7dlu:grow"),
				RowSpec.decode("47px"),
				RowSpec.decode("7dlu:grow"),
				RowSpec.decode("47px"),
				RowSpec.decode("40px"),}));
		add(panel, BorderLayout.CENTER);
		
		btnNewDelivery = new ButtonFlat("Nouvelle livraison");
		btnNewDelivery.setRolloverBackground(new Color(200, 200, 200));
		btnNewDelivery.setBackground(new Color(240, 240, 240));
		btnNewDelivery.addActionListener(this);
		
		lblNewLabel = new JLabel("Choissisez l'action \u00E0 r\u00E9aliser :");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel.add(lblNewLabel, "2, 1");
		btnNewDelivery.setIcon(new ImageIcon(MainPanel.class.getResource("/iconeBegining/addDocument.png")));
		btnNewDelivery.setIconTextGap(20);
		btnNewDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewDelivery.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(btnNewDelivery, "2, 2, fill, fill");
		
		btnOpenDelivery = new ButtonFlat("Ouvrir la livraison pr\u00E9cedente");
		btnOpenDelivery.setRolloverBackground(new Color(200, 200, 200));
		btnOpenDelivery.setBackground(new Color(240, 240, 240));
		btnOpenDelivery.addActionListener(this);
		btnOpenDelivery.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnOpenDelivery.setIconTextGap(20);
		btnOpenDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		btnOpenDelivery.setIcon(new ImageIcon(MainPanel.class.getResource("/iconeBegining/timer.png")));
		panel.add(btnOpenDelivery, "2, 4, fill, fill");
		
		btnOtherDelivery = new ButtonFlat("Toutes les livraisons");
		btnOtherDelivery.setRolloverBackground(new Color(200, 200, 200));
		btnOtherDelivery.setBackground(new Color(240, 240, 240));
		btnOtherDelivery.addActionListener(this);
		btnOtherDelivery.setIcon(new ImageIcon(MainPanel.class.getResource("/iconeBegining/checking.png")));
		btnOtherDelivery.setIconTextGap(20);
		btnOtherDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		btnOtherDelivery.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(btnOtherDelivery, "2, 6, fill, fill");
		
		lblWelcome = new JLabel("Bienvenue");
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWelcome.setForeground(Color.white);
		setMenuComponent(lblWelcome);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnNewDelivery){
			control.setViewPanel(OpeningFrame.PANEL_NEW_DELIVERY);
		}else if(ae.getSource() == btnOpenDelivery){
			control.openDelivery();
		}
	}
}
