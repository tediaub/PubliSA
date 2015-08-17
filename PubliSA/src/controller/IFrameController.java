package controller;

import java.awt.Point;

import model.Model;

public interface IFrameController {

	void iconifiedFrame();

	boolean maximizedFrame();

	void closeFrame();
	
	void closeAll();
	
	void resizeFrame(int x, int y);

	void setFrameLocation(int x, int y);

	Point getMouseOnFrame(int xOnScreen, int yOnScreen);
	
	boolean save();
	
	Model getModel();

	void openDelivery(String obj);
}
