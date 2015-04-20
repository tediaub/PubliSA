package view.frame.mainFrame.panel;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Model;
import controller.IFrameController;

@SuppressWarnings("serial")
public abstract class PanelObserver<T> extends JPanel implements Observer {
	
	protected T control;
	
	public PanelObserver(T control){
		this.control = control;
		((IFrameController) control).getModel().addObserver(this);
	}
	
	protected abstract void update(Model model);

	@Override
	public void update(Observable o, Object arg) {
		update((Model) o);
	}		
}