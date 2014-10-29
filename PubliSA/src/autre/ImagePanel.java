package autre;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author Teddy AUBERT
 *
 * @version $Revision: 1.0 $
 */

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image img;

	/**
	 * Constructeur de ImagPanel
	 * @param img String
	 */
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	/**
	 * Constructeur de ImagPanel
	 * @param img Image
	 */
	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	/**
	 * Méthode paintComponent.
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}

