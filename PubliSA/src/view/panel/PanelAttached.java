package view.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.Model;
import view.guiComponents.SeparatorFlat;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.ControllerFrame;

@SuppressWarnings("serial")
public class PanelAttached extends PanelObserver {

	private JLabel textInfo;

	public PanelAttached(ControllerFrame control) {
		super(control);
		
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20mm"),
				ColumnSpec.decode("10mm"),
				ColumnSpec.decode("default:grow")},
			new RowSpec[] {
				RowSpec.decode("5mm"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("7mm")}));
		
		JLabel lblAprsLaCration = new JLabel(GestLangue.getInstance().getLocalizedText(IHM.APRES_MAIL.getLabel()));
		textInfo = new JLabel();
		
		add(lblAprsLaCration, "2, 4, 2, 1");
		lblAprsLaCration.setFont(new Font("Dialog", Font.BOLD, 12));
		add(textInfo, "3, 6");
		
		update(control.getModel());
	}

	@Override
	protected void update(Model model) {
		if(model.getMainDelivery().getTarget() == Delivery.UBIK){
			textInfo.setText(GestLangue.getInstance().getLocalizedText(IHM.A_JOINDRE_UBIK.getLabel()));
		}
		else if(model.getMainDelivery().getTarget() == Delivery.THALES){
			textInfo.setText(GestLangue.getInstance().getLocalizedText(IHM.A_JOINDRE_THALES.getLabel()));
		}
	}

}
