package view.guiComponents.svg;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import model.Delivery;
import model.Model;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanSVG extends JSVGCanvas implements Observer{
	private Document doc;
	String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
	
	public PanSVG(ControllerFrame control){
		setDoubleBufferedRendering(true);
		if(control.getModel().getMainDelivery()!= null){
			DocSVG doc = new DocSVG(control.getModel().getMainDelivery());
			setDocument(doc.getDoc());
		}
		setBackground(new Color(0, 119, 175));
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Model model = (Model)arg0;
		if(model.getMainDelivery()!= null){
			DocSVG doc = new DocSVG(model.getMainDelivery());
			setDocument(doc.getDoc());
		}
	}
	
	private class DocSVG {
		
		MarkerSVG markerStep1;
		MarkerSVG markerStep2;
		MarkerSVG markerStep3;
		MarkerSVG markerStep4;
		
		Point pointStep1 = new Point(25, 25);
		Point pointStep2 = new Point(25, 75);
		Point pointStep3 = new Point(25, 125);
		Point pointStep4 = new Point(25, 175);
	
	
		public DocSVG(Delivery delivery) {
			
			DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
			doc = impl.createDocument(svgNS, "svg", null);
	
			// Get the root element (the 'svg' element).
			Element svgRoot = doc.getDocumentElement();
	
			// Set the width and height attributes on the root 'svg' element.
			svgRoot.setAttributeNS(null, "width", "50");
			svgRoot.setAttributeNS(null, "height", "200");
			
			boolean actStep1= false, actStep2 = false, actStep3 = false, actStep4 = false;
			switch (delivery.getActualStep()) {
			case Delivery.STEP1:
				actStep1 = true;
				break;
	
			case Delivery.STEP2:
				actStep2 = true;
				break;
				
			case Delivery.STEP3:
				actStep3 = true;
				break;
				
			case Delivery.STEP4:
				actStep4 = true;
				break;
			default:
				break;
			}
			
			markerStep1 = new MarkerSVG(pointStep1, null, actStep1);
			markerStep2 = new MarkerSVG(pointStep2, markerStep1, actStep2);
			markerStep3 = new MarkerSVG(pointStep3, markerStep2, actStep3);
			
			markerStep1.draw(svgRoot);
			markerStep2.draw(svgRoot);
			markerStep3.draw(svgRoot);
			
			if(delivery.getTarget() == Delivery.UBIK){
				markerStep4 = new MarkerSVG(pointStep4, markerStep3, actStep4);
				
				markerStep4.draw(svgRoot);
			}
		}
	
		public Document getDoc(){
			return doc;
		}
	}
	
	private class MarkerSVG{
		
		private Point pointCircle;
		
		private MarkerSVG markerTop = null;
		
		private boolean selected = false;		
		
		public MarkerSVG(Point pointCircle, MarkerSVG markerTop, boolean selected){
			this.pointCircle = pointCircle;
			this.markerTop = markerTop;
			this.selected = selected;
		}
		
		public Point getPoint(){
			return pointCircle;
		}
		
		public void draw(Element rootSVG){
			if(selected){
				drawSelectedCircleSVG(rootSVG, pointCircle);
			}else{
				drawUnselectedCircleSVG(rootSVG, pointCircle);
			}
			
			if(markerTop != null){
				drawConnectorSVG(rootSVG, markerTop.getPoint(), pointCircle);
			}
		}
		
		private void drawSelectedCircleSVG(Element svgRoot, Point center) {
			
			String x = Integer.toString(center.x);
			String y = Integer.toString(center.y);
			
			Element circleBottom = doc.createElementNS(svgNS, "circle");
			circleBottom.setAttributeNS(null, "cx", x);
			circleBottom.setAttributeNS(null, "cy", y);
			circleBottom.setAttributeNS(null, "r", "12");
			circleBottom.setAttributeNS(null, "fill", "#87b5ff");
			svgRoot.appendChild(circleBottom);
			
			Element circleMain = doc.createElementNS(svgNS, "circle");
			circleMain.setAttributeNS(null, "cx", x);
			circleMain.setAttributeNS(null, "cy", y);
			circleMain.setAttributeNS(null, "r", "8");
			circleMain.setAttributeNS(null, "fill", "#003f71");
			svgRoot.appendChild(circleMain);
			
			Element circleTop = doc.createElementNS(svgNS, "circle");
			circleTop.setAttributeNS(null, "cx", x);
			circleTop.setAttributeNS(null, "cy", y);
			circleTop.setAttributeNS(null, "r", "3");
			circleTop.setAttributeNS(null, "fill", "#87b5ff");
			svgRoot.appendChild(circleTop);
		}
		
		private void drawUnselectedCircleSVG(Element svgRoot, Point center) {
			
			String x = Integer.toString(center.x);
			String y = Integer.toString(center.y);
			
			Element circleMain = doc.createElementNS(svgNS, "circle");
			circleMain.setAttributeNS(null, "cx", x);
			circleMain.setAttributeNS(null, "cy", y);
			circleMain.setAttributeNS(null, "r", "8");
			circleMain.setAttributeNS(null, "fill", "#003f71");
			svgRoot.appendChild(circleMain);
		}
		
		private void drawConnectorSVG(Element svgRoot, Point p1, Point p2) {
			
			String xO = Integer.toString(p1.x);
			String yO = Integer.toString(p1.y + 4) ;
			
			String xN = Integer.toString(p2.x);
			String yN = Integer.toString(p2.y - 4) ;
			
			Element connector = doc.createElementNS(svgNS, "line");
			connector.setAttributeNS(null, "x1", xO);
			connector.setAttributeNS(null, "x2", xN);
			connector.setAttributeNS(null, "y1", yO);
			connector.setAttributeNS(null, "y2", yN);
			connector.setAttributeNS(null, "stroke", "#003f71");
			connector.setAttributeNS(null, "stroke-width", "5");
			svgRoot.appendChild(connector);
		}
	}
}