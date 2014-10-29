package affichage;

import java.awt.Graphics; 
import java.awt.Image;
import java.net.URL;

import javax.swing.JPanel; 

@SuppressWarnings("serial")
public class AfficheImage extends JPanel 
{ 
	public URL image = FenetrePrincipale.class.getResource("/image/fonds/FondPrincipal.png");
	Image eau;

	AfficheImage() 
	{ 
		eau = getToolkit().getImage(image); 
	} 

	public void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g); 
		g.drawImage(eau, 0, 0, getWidth(), getHeight(), this);
	} 
}