package etape;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import XML.XML;
import affichage.FenetrePrincipale;
import langue.GestLangue;
import langue.IHM;
import model.Delivery;
  
@SuppressWarnings("serial")
public class FilAriane extends JPanel implements MouseListener{
	
	int height;
	int width;
	int numerosSelect = 0;
	
	int test = 0;
	
	private JLabel lblFleche1 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.TEXT_FLECHE1_UBIK.getLabel()));
	private JLabel lblFleche2 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.TEXT_FLECHE2_UBIK.getLabel()));
	private JLabel lblFleche3 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.TEXT_FLECHE3_UBIK.getLabel()));
	private JLabel lblFleche4 = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.TEXT_FLECHE4_UBIK.getLabel()));
	
	public FilAriane(int nbSelect){
		numerosSelect = nbSelect;
		if(numerosSelect == 1){
			lblFleche1.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		else if(numerosSelect == 2){
			lblFleche2.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		else if(numerosSelect == 3){
			lblFleche3.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		else if(numerosSelect == 4){
			lblFleche4.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		
		lblFleche1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFleche2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFleche3.setHorizontalAlignment(SwingConstants.CENTER);
		lblFleche4.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblFleche1);
		add(lblFleche2);
		add(lblFleche3);
		add(lblFleche4);
		
		addMouseListener(this);
		//addMouseMotionListener(this);
		
		setOpaque(false);
		setBorder(new LineBorder((XML.getColor())[0], 1, true));
	}
	
	public void paintComponent(Graphics g){
		String cible = "";
		try{
			cible = Delivery.getCible();
		}catch(NullPointerException e){
			cible = "";
		}
		
		if(cible.contentEquals("Ubik")){
			lblFleche3.setText(GestLangue.getInstance().getLocalizedText(IHM.TEXT_FLECHE3_UBIK.getLabel()));
			lblFleche4.setText(GestLangue.getInstance().getLocalizedText(IHM.TEXT_FLECHE4_UBIK.getLabel()));
			
			height = this.getHeight();
			width = this.getWidth()/4;
			
			lblFleche1.setBounds(5,0, width-10, height);
			lblFleche2.setBounds(width+20,0, width-20, height);
			lblFleche3.setBounds(2*width+20,0, width-20, height);
			lblFleche4.setBounds(3*width+20,0, width-20, height);
			  
			int x[] = {0, width-10, width+15, width-10, 0};
		    int y[] = {0, 0, height/2, height, height};
		    if(numerosSelect == 1){
		    	g.setColor(Etape0.couleur[1]);
		    	g.fillPolygon(x, y, 5);
		    }
		    else{
		    	g.setColor(Color.white);
	    		g.fillPolygon(x, y, 5);
		    	g.setColor(Etape0.couleur[1]);
	    		g.drawPolygon(x, y, 5);	    	
		    }
		    
		    int x1[] = {width-10, 2*width-10, 2*width+15, 2*width-10, width-10, width+15};
		    int y1[] = {0, 0, height/2, height, height, height/2};
		    if(numerosSelect == 2){
		    	g.setColor(Etape0.couleur[1]);		    	
		    	g.fillPolygon(x1, y1, 6);
		    }
		    else{
		    	g.setColor(Color.white);
	    		g.fillPolygon(x1, y1, 6);
		    	g.setColor(Etape0.couleur[1]);
		    	g.drawPolygon(x1, y1, 6);
		    }
		    
		    int x2[] = {2*width-10, 3*width-10, 3*width+15, 3*width-10, 2*width-10, 2*width+15};
		    int y2[] = {0, 0, height/2, height, height, height/2};
		    if(numerosSelect == 3){
		    	g.setColor(Etape0.couleur[1]);
		    	g.fillPolygon(x2, y2, 6);
		    }
		    else{
		    	g.setColor(Color.white);
	    		g.fillPolygon(x2, y2, 6);
		    	g.setColor(Etape0.couleur[1]);
		    	g.drawPolygon(x2, y2, 6);
		    }
		    
		    int x3[] = {3*width-10, 4*width, 4*width, 3*width-10, 3*width+15};
		    int y3[] = {0, 0, height, height, height/2};
		    if(numerosSelect == 4){
		    	g.setColor(Etape0.couleur[1]);
		    	g.fillPolygon(x3, y3, 5);
		    }
		    else{
		    	g.setColor(Color.white);
	    		g.fillPolygon(x3, y3, 5);
		    	g.setColor(Etape0.couleur[1]);
		    	g.drawPolygon(x3, y3, 5);
		    }
		}
		else if(cible.contentEquals("Thales")){
			
			lblFleche3.setText(GestLangue.getInstance().getLocalizedText(IHM.TEXT_FLECHE3_THALES.getLabel()));
			lblFleche4.setText("");
			
			height = this.getHeight();
			width = this.getWidth()/3;
			
			lblFleche1.setBounds(5,0, width-10, height);
			lblFleche2.setBounds(width+20,0, width-20, height);
			lblFleche3.setBounds(2*width+20,0, width-20, height);
			  
			int x[] = {0, width-10, width+15, width-10, 0};
		    int y[] = {0, 0, height/2, height, height};
		    if(numerosSelect == 1){
		    	g.setColor(Etape0.couleur[1]);
		    	g.fillPolygon(x, y, 5);
		    }
		    else{
		    	g.setColor(Color.white);
	    		g.fillPolygon(x, y, 5);
		    	g.setColor(Etape0.couleur[1]);
	    		g.drawPolygon(x, y, 5);	    	
		    }
		    
		    int x1[] = {width-10, 2*width-10, 2*width+15, 2*width-10, width-10, width+15};
		    int y1[] = {0, 0, height/2, height, height, height/2};
		    if(numerosSelect == 2){
		    	g.setColor(Etape0.couleur[1]);
		    	g.fillPolygon(x1, y1, 6);
		    }
		    else{
		    	g.setColor(Color.white);
	    		g.fillPolygon(x1, y1, 6);
		    	g.setColor(Etape0.couleur[1]);
		    	g.drawPolygon(x1, y1, 6);
		    }
		    
		    int x2[] = {2*width-10, 3*width, 3*width, 2*width-10, 2*width+15};
		    int y2[] = {0, 0, height, height, height/2};
		    if(numerosSelect == 3){
		    	g.setColor(Etape0.couleur[1]);
		    	g.fillPolygon(x2, y2, 5);
		    }
		    else{
		    	g.setColor(Color.white);
	    		g.fillPolygon(x2, y2, 5);
		    	g.setColor(Etape0.couleur[1]);
		    	g.drawPolygon(x2, y2, 5);
		    }
		}
	}
	
	public void mousePressed(MouseEvent e) {
		String cible = "";
		try{
			cible = Delivery.getCible();
		}catch(NullPointerException n){
			cible = "";
		}
		
		if(cible.contentEquals("Ubik")){
			width = this.getWidth()/4;
			
			if(e.getX()> 0 && e.getX()<width-10){
				if(Delivery.getEtapeMAX() >= 1){
					CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE1);
			        Etape1.miseAjour();
					Etape1.getPanel().repaint();
					Delivery.setEtape(1);
				}
			}
			
			if(e.getX()> width+15 && e.getX()<2*width-10){
				if(Delivery.getEtapeMAX() >= 2){
					CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE2);
			        Etape2.miseAjour();
					Etape2.getPanel().repaint();
					Delivery.setEtape(2);
				}
			}
			
			if(e.getX()> 2*width+15 && e.getX()<3*width-10){
				if(Delivery.getEtapeMAX() >= 3){
					CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE3);
			        Etape3.miseAjour();
					Etape3.getPanel().repaint();
					Delivery.setEtape(3);
				}
			}
			
			if(e.getX()> 3*width+15 && e.getX()<4*width){
				if(Delivery.getEtapeMAX() >= 4){
					CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE4);
			        Etape4.miseAjour();
					Etape4.getPanel().repaint();
					Delivery.setEtape(4);
				}
			}
		}
		else if(cible.contentEquals("Thales")){
			width = this.getWidth()/3;
			
			if(e.getX()> 0 && e.getX()<width-10){
				if(Delivery.getEtapeMAX() >= 1){
					CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE1);
			        Etape1.miseAjour();
					Etape1.getPanel().repaint();
					Delivery.setEtape(1);
				}
			}
			
			if(e.getX()> width+15 && e.getX()<2*width-10){
				if(Delivery.getEtapeMAX() >= 2){
					CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE2);
			        Etape2.miseAjour();
					Etape2.getPanel().repaint();
					Delivery.setEtape(2);
				}
			}
			
			if(e.getX()> 2*width+15 && e.getX()<3*width){
				if(Delivery.getEtapeMAX() >= 3){
					CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
			        cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE3);
			        Etape3.miseAjour();
					Etape3.getPanel().repaint();
					Delivery.setEtape(3);
				}
			}
		}
		
	}
	
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		String cible = "";
//		try{
//			cible = Livraison.getCible();
//		}catch(NullPointerException n){
//			cible = "";
//		}
//		
//		if(cible.contentEquals("Ubik")){
//			height = this.getHeight();
//			width = this.getWidth()/4;
//			
//			if(e.getX()> 0 && e.getX()<width-10 && numerosSelect > 1){
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//			else if(e.getX()> width+15 && e.getX()<2*width-10 && numerosSelect > 2){
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//			else if(e.getX()> 2*width+15 && e.getX()<3*width-10 && numerosSelect > 3){
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//			else if(e.getX()> 3*width+15 && e.getX()<4*width && numerosSelect > 4){
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//			else{
//				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//			}
//		}
//		else if(cible.contentEquals("Thales")){
//			height = this.getHeight();
//			width = this.getWidth()/3;
//			
//			if(e.getX()> 0 && e.getX()<width-10 && numerosSelect > 1){
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//			else if(e.getX()> width+15 && e.getX()<2*width-10 && numerosSelect > 2){
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//			else if(e.getX()> 2*width+15 && e.getX()<3*width && numerosSelect > 3){
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}		
//			else{
//				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//			}
//		}
//		
//	}

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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

