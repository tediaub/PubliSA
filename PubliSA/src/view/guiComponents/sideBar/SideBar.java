package view.guiComponents.sideBar;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class SideBar extends JPanel{

	private JPanel pContainer;
	private JPanel pExtend;
	private JPanel pCollapse;
	private JPanel pTransparent;
	
	public static int LEFTtoRIGHT = 0;
	public static int RIGHTtoLEFT = 1;
	public static int HIGHtoDOWN = 2;
	public static int DOWNtoHIGH = 3;
	
	/**
	 * @wbp.parser.constructor
	 */
	public SideBar(int orientation, int lMin, int lMax, JPanel pExt, JPanel pColl, JPanel pTrans){
		pContainer = new JPanel();
		pContainer.setLayout(new BorderLayout(0, 0));
		pExtend = pExt;
		pCollapse = pColl;
		pTransparent = pTrans;
		
		setOrientation(orientation, lMin, lMax);
		
		pExtend.setVisible(false);
		pTransparent.setVisible(false);
	}
	
	public SideBar(int orientation, int lMax, JPanel pExt, JPanel pTrans){
		pContainer = new JPanel();
		pContainer.setLayout(new BorderLayout(0, 0));
		pExtend = pExt;
		pCollapse = null;
		pTransparent = pTrans;
		
		setOrientation(orientation, 0, lMax);
		
		pExtend.setVisible(false);
		pTransparent.setVisible(false);
	}
	
	private void setOrientation(int orientation, int lMin, int lMax){
		switch (orientation) {
		case 0:
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode(lMin+"px"),
					ColumnSpec.decode(lMax+"px"),
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					RowSpec.decode("default:grow"),}));
			add(pExtend, "1, 1, 2, 1, fill, fill");
			if(pCollapse!= null){add(pCollapse, "1, 1, fill, fill");}
			add(pTransparent, "3, 1, fill, fill");
			add(pContainer, "2, 1, 2, 1, fill, fill");
			break;
		case 1:
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					ColumnSpec.decode(lMax+"px"),
					ColumnSpec.decode(lMin+"px"),
					},
				new RowSpec[] {
					RowSpec.decode("default:grow"),}));
			add(pExtend, "2, 1, 2, 1, fill, fill");
			if(pCollapse!= null){add(pCollapse, "3, 1, fill, fill");}
			add(pTransparent, "1, 1, fill, fill");
			add(pContainer, "1, 1, 2, 1, fill, fill");
			break;
		case 2:
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					},
				new RowSpec[] {
					RowSpec.decode(lMin+"px"),
					RowSpec.decode(lMax+"px"),
					RowSpec.decode("default:grow"),}));
			add(pExtend, "1, 1, 1, 2, fill, fill");
			if(pCollapse!= null){add(pCollapse, "1, 1, fill, fill");}
			add(pTransparent, "1, 3, fill, fill");
			add(pContainer, "1, 2, 1, 2, fill, fill");
			break;
		case 3:
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					},
				new RowSpec[] {
					RowSpec.decode("default:grow"),
					RowSpec.decode(lMax+"px"),
					RowSpec.decode(lMin+"px"),}));
			add(pExtend, "1, 2, 1, 2, fill, fill");
			if(pCollapse!= null){add(pCollapse, "1, 3, fill, fill");}
			add(pTransparent, "1, 1, fill, fill");
			add(pContainer, "1, 1, 1, 2, fill, fill");
			break;
		default:
			setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode(lMin+"px"),
					ColumnSpec.decode(lMax+"px"),
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					RowSpec.decode("default:grow"),}));
			add(pExtend, "1, 1, 2, 1, fill, fill");
			if(pCollapse!= null){add(pCollapse, "1, 1, fill, fill");}
			add(pTransparent, "3, 1, fill, fill");
			add(pContainer, "2, 1, 2, 1, fill, fill");
			break;
		}
	}
	
	public JPanel getContainerPane(){
		return pContainer;
	}
	
	public JPanel getExtendPane(){
		return pExtend;
	}
	
	public JPanel getCollapsePane(){
		return pCollapse;
	}
	
	public void extendSideBar(){
		pExtend.setVisible(true);
		pTransparent.setVisible(true);
		if(pCollapse!= null){pCollapse.setVisible(false);}
	}

	public void collapseSideBar(){
		pExtend.setVisible(false);
		pTransparent.setVisible(false);
		if(pCollapse!= null){pCollapse.setVisible(true);}
	}
}
