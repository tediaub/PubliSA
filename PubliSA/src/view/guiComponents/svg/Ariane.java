package view.guiComponents.svg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Delivery;
import model.Model;
import controller.ControllerFrame;

@SuppressWarnings("serial")
public class Ariane extends JPanel implements MouseListener, Observer {

	Marker markerStep1;
	Marker markerStep2;
	Marker markerStep3;
	Marker markerStep4;
	
	Point pointStep1 = new Point(25, 25);
	Point pointStep2 = new Point(25, 75);
	Point pointStep3 = new Point(25, 125);
	Point pointStep4 = new Point(25, 175);
	
	ControllerFrame control;
	
	/**
	 * Create the panel.
	 */
	public Ariane(ControllerFrame control) {
		this.control = control;
		
		markerStep1 = new Marker(pointStep1, null, false);
		markerStep2 = new Marker(pointStep2, markerStep1, false);
		markerStep3 = new Marker(pointStep3, markerStep2, false);
		markerStep4 = new Marker(pointStep4, markerStep3, false);
		
		setOpaque(false);
		addMouseListener(this);
		
		setData(control.getModel());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		markerStep1.draw(g2d);
		markerStep2.draw(g2d);
		markerStep3.draw(g2d);
		markerStep4.draw(g2d);
	}
	
	private class Marker{
		
		private Point pointCircle;
		
		private boolean visible = true;
		
		private Marker markerTop = null;
		
		private boolean selected = false;
		
		private int circleDiameter = 16;
		private int selectedUpDiameter = 6;
		private int selectedDownDiameter = 24;
		
		private Color c1 = new Color(0, 63, 113);
		private Color c2 = new Color(135, 181, 255);
		
		public Marker(Point pointCircle, Marker markerTop, boolean selected){
			this.pointCircle = pointCircle;
			this.markerTop = markerTop;
			this.selected = selected;
		}
		
		public void setSelected(boolean b){
			selected = b;
		}
		
		public void setVisible(boolean b){
			visible = b;
		}
		
		public Point getPoint(){
			return pointCircle;
		}
		
		public Rectangle getRectangle(){
			Rectangle r = new Rectangle(pointCircle.x - selectedDownDiameter/2, pointCircle.y - selectedDownDiameter/2,
					selectedDownDiameter, selectedDownDiameter);
			return r;
		}
		
		public void draw(Graphics2D g){
			if(visible){
				if(selected){
					drawSelectedCircleSVG(g, pointCircle);
				}else{
					drawUnselectedCircleSVG(g, pointCircle);
				}
				
				if(markerTop != null){
					drawConnectorSVG(g, markerTop.getPoint(), pointCircle);
				}
			}
		}
		
		private void drawSelectedCircleSVG(Graphics2D g2d, Point center) {
			int x2 = center.x - selectedDownDiameter/2;
			int y2 = center.y - selectedDownDiameter/2;
			g2d.setColor(c2);
			g2d.fillOval(x2, y2, selectedDownDiameter, selectedDownDiameter);

			int x1 = center.x - circleDiameter/2;
			int y1 = center.y - circleDiameter/2;
			g2d.setColor(c1);
			g2d.fillOval(x1, y1, circleDiameter, circleDiameter);
			
			int x3 = center.x - selectedUpDiameter/2;
			int y3 = center.y - selectedUpDiameter/2;
			g2d.setColor(c2);
			g2d.fillOval(x3, y3, selectedUpDiameter, selectedUpDiameter);
		}
		
		private void drawUnselectedCircleSVG(Graphics2D g2d, Point center) {
			int x1 = center.x - circleDiameter/2;
			int y1 = center.y - circleDiameter/2;
			g2d.setColor(c1);
			g2d.fillOval(x1, y1, circleDiameter, circleDiameter);
		}
		
		private void drawConnectorSVG(Graphics2D g2d, Point p1, Point p2) {
			g2d.setStroke(new BasicStroke(5));
			g2d.setColor(c1);
			g2d.drawLine(p1.x, p1.y + selectedUpDiameter, p2.x, p2.y - selectedUpDiameter);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Delivery d = control.getModel().getMainDelivery();
		if(d!= null){
			if(markerStep1.getRectangle().contains(e.getPoint())){
				d.setActualStep(Delivery.STEP1);
				control.colSideBarBlue();
			}
			if(markerStep2.getRectangle().contains(e.getPoint())){
				d.setActualStep(Delivery.STEP2);
				control.colSideBarBlue();
			}
			if(markerStep3.getRectangle().contains(e.getPoint())){
				d.setActualStep(Delivery.STEP3);
				control.colSideBarBlue();
			}
			if(markerStep4.getRectangle().contains(e.getPoint())){
				d.setActualStep(Delivery.STEP4);
				control.colSideBarBlue();
			}
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setData(Model model){
		if(model.getMainDelivery()!= null){
			switch (model.getMainDelivery().getActualStep()) {
			case Delivery.STEP1:
				markerStep1.setSelected(true);
				
				markerStep2.setSelected(false);
				markerStep3.setSelected(false);
				markerStep4.setSelected(false);
				break;
	
			case Delivery.STEP2:
				markerStep2.setSelected(true);
				
				markerStep1.setSelected(false);
				markerStep3.setSelected(false);
				markerStep4.setSelected(false);
				break;
				
			case Delivery.STEP3:
				markerStep3.setSelected(true);
				
				markerStep1.setSelected(false);
				markerStep2.setSelected(false);
				markerStep4.setSelected(false);
				break;
				
			case Delivery.STEP4:
				markerStep4.setSelected(true);
				
				markerStep1.setSelected(false);
				markerStep2.setSelected(false);
				markerStep3.setSelected(false);
				break;
			default:
				break;
			}
			
			if (model.getMainDelivery().getTarget() == Delivery.THALES) {
				markerStep4.setVisible(false);
			}else{
				markerStep4.setVisible(true);
			}
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Model model = (Model)arg0;
		setData(model);
		repaint();
	}
}
