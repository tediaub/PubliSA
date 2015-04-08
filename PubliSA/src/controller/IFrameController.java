package controller;

import java.awt.Point;

public interface IFrameController {

	void iconifiedFrame();

	void maximizedFrame();

	void closeFrame();
	
	void closeAll();
	
	void resizeFrame(int x, int y);

	void setFrameLocation(int x, int y);

	Point getMouseOnFrame(int xOnScreen, int yOnScreen);
	
	void save();

}
