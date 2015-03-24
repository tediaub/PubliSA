package view.panel;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.ControllerFrame;
import model.Model;

@SuppressWarnings("serial")
public abstract class PanelObserver extends JPanel implements Observer {
	
	protected ControllerFrame control;
	
	protected abstract void update(Model model);

	@Override
	public void update(Observable o, Object arg) {
		update((Model) o);
	}		
}
