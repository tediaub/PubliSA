package tutoriel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class TutoPanel extends JPanel implements MouseListener{

	final int opacite = 150; 
	int x, y, width, height;
	
	JPanel pHaut,pGauche,pDroit,pBas, pGBas ;//pDBas;
	
	public TutoPanel(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		pHaut = new JPanel(){
		    protected void paintComponent(final Graphics g) {
		        g.setColor(getBackground());
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		pHaut.setLayout(new BorderLayout(0, 0));
		
		pGauche = new JPanel(){
		    protected void paintComponent(final Graphics g) {
		        g.setColor(getBackground());
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		pGauche.setLayout(new BorderLayout(0, 0));
		
		pDroit = new JPanel(){
		    protected void paintComponent(final Graphics g) {
		        g.setColor(getBackground());
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		pDroit.setLayout(new BorderLayout(0, 0));
		
		pBas = new JPanel(){
		    protected void paintComponent(final Graphics g) {
		        g.setColor(getBackground());
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		pBas.setLayout(new BorderLayout(0, 0));
		
		pGBas = new JPanel(){
		    protected void paintComponent(final Graphics g) {
		        g.setColor(getBackground());
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		pGBas.setLayout(new BorderLayout(0, 0));
		
		/*pDBas = new JPanel(){
		    protected void paintComponent(final Graphics g) {
		        g.setColor(getBackground());
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		pDBas.setLayout(new BorderLayout(0, 0));*/
		
		this.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(x + "px"),
				ColumnSpec.decode(width + "px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode(y + "px"),
				RowSpec.decode(height + "px"),
				RowSpec.decode("default:grow"),}));
		
		this.add(pHaut, "2, 1, fill, fill");
		this.add(pGauche, "1, 1, 1, 2, fill, fill");
		this.add(pDroit, "3, 1, 1, 2, fill, fill");
		this.add(pBas, "2, 3, 2, 1, fill, fill");
		this.add(pGBas, "1, 3, fill, fill");
		//this.add(pDBas, "3, 3, fill, fill");
		
		this.setOpaque(false);
		pHaut.setOpaque(false);
		pGauche.setOpaque(false);
		pDroit.setOpaque(false);
		pBas.setOpaque(false);
		pGBas.setOpaque(false);
		//pDBas.setOpaque(false);
		
		pHaut.addMouseListener(this);
		pGauche.addMouseListener(this);
		pDroit.addMouseListener(this);
		pBas.addMouseListener(this);
		pGBas.addMouseListener(this);
		//pDBas.addMouseListener(this);
		
		pHaut.setBackground(new Color(0, 0, 0, opacite));
		pGauche.setBackground(new Color(0, 0, 0, opacite));
		pDroit.setBackground(new Color(0, 0, 0, opacite));
		pBas.setBackground(new Color(0, 0, 0, opacite));
		pGBas.setBackground(new Color(0, 0, 0, opacite));
		//pDBas.setBackground(new Color(0, 0, 0, opacite));
	}
	
	public void changeTaille(int x, int y, int width, int height){
		this.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(x + "px"),
				ColumnSpec.decode(width + "px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode(y + "px"),
				RowSpec.decode(height + "px"),
				RowSpec.decode("default:grow"),}));
		
		this.add(pHaut, "2, 1, fill, fill");
		this.add(pGauche, "1, 1, 1, 2, fill, fill");
		this.add(pDroit, "3, 1, 1, 2, fill, fill");
		this.add(pBas, "2, 3, 2, 1, fill, fill");
		this.add(pGBas, "1, 3, fill, fill");
		//this.add(pDBas, "3, 3, fill, fill");
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
	public void mousePressed(MouseEvent arg0) {
	    if (SwingUtilities.isLeftMouseButton(arg0)) {
	        /*if(Tutoriel.etape == 1)
	        	||Tutoriel.etape == 2
	        	||Tutoriel.etape == 3
	    		||Tutoriel.etape == 4
	    		||Tutoriel.etape == 5
	    		||Tutoriel.etape == 6
	        	||Tutoriel.etape == 7
	    		||Tutoriel.etape == 8
	    		||Tutoriel.etape == 9
	    		||Tutoriel.etape == 10
	    		||Tutoriel.etape == 11) */
	    	if(Tutoriel.etape !=17
	    			&&Tutoriel.etape != 3
	    			&&Tutoriel.etape != 7){
	    		Tutoriel.next(-1);
	    	}
	    	System.out.println(Tutoriel.etape);
	    }else if(SwingUtilities.isMiddleMouseButton(arg0)) {
	            /** Bouton du MILIEU*/ 
	    }else if(SwingUtilities.isRightMouseButton(arg0)) {
	            /** Bouton DROIT*/ 
	    }
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
