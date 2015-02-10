package sideBarMenu;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class SideBar extends JPanel implements ActionListener{

	private JButton btnExtand;
	private JButton btnCollapse;
	
	private JPanel pExtand;
	private JPanel pCollapse;
	private JPanel pContainer;
	
	public SideBar(int widthMin, int widthMax) {
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(widthMin+"px"),
				ColumnSpec.decode(widthMax+"px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		pExtand = new JPanel();
		pExtand.setVisible(false);
		pExtand.setBackground(new Color(0, 119, 175));
		add(pExtand, "1, 1, 2, 1, fill, fill");
		pExtand.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("50px"),},
			new RowSpec[] {
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		btnExtand = new JButton();
		btnExtand.addActionListener(this);
		btnExtand.setContentAreaFilled(false);
		btnExtand.setVerticalAlignment(SwingConstants.TOP);
		btnExtand.setIcon(new ImageIcon("C:\\Documents and Settings\\AUBERT_T.AKKA\\Bureau\\g4165.png"));
		pExtand.add(btnExtand, "2, 2");
		
		pCollapse = new JPanel();
		pCollapse.setBackground(new Color(0, 119, 175));
		add(pCollapse, "1, 1, fill, fill");
		pCollapse.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow")},
			new RowSpec[] {
				FormFactory.PARAGRAPH_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.PARAGRAPH_GAP_ROWSPEC,}));
		
		btnCollapse = new JButton();
		btnCollapse.addActionListener(this);
		btnCollapse.setContentAreaFilled(false);
		btnCollapse.setIcon(new ImageIcon("C:\\Documents and Settings\\AUBERT_T.AKKA\\Bureau\\g4164.png"));
		btnCollapse.setBounds(10, 11, 30, 23);
		pCollapse.add(btnCollapse, "1, 2");
		
		pContainer = new JPanel();
		pContainer.setBackground(Color.WHITE);
		add(pContainer, "2, 1, 2, 1, fill, fill");
		pContainer.setLayout(null);
	}
	
	public JPanel getContainer(){
		return pContainer;
	}
	
	public JPanel getExtand(){
		return pExtand;
	}
	
	public JPanel getCollapse(){
		return pCollapse;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCollapse){
			pExtand.setVisible(true);
			pCollapse.setVisible(false);
		}
		if(e.getSource() == btnExtand){
			pExtand.setVisible(false);
			pCollapse.setVisible(true);
		}
	}

}
