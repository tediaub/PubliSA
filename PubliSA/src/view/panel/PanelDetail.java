package view.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.guiComponents.SeparatorFlat;
import view.language.ELabelUI;
import view.language.LanguageSelector;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class PanelDetail extends JPanel {

	String name = LanguageSelector.getLocalizedText(ELabelUI.DETAIL.getLabel());
	
	public PanelDetail(){
		setOpaque(false);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("15px"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				RowSpec.decode("25px"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("25px"),
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("10px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTitle = new JLabel(name);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setForeground(new Color(0, 119, 175));
		add(lblTitle, "6, 1, right, default");
		
		JLabel lblNewLabel = new JLabel("Application");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 119, 175));
		add(lblNewLabel, "2, 3, 3, 1");
		
		SeparatorFlat separator = new SeparatorFlat();
		add(separator, "2, 4, 6, 1");
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(PanelDetail.class.getResource("/logo/logoPubliSA4.png")));
		add(lblNewLabel_1, "4, 6, 1, 7");
		
		JLabel lblNewLabel_3 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.VERSION.getLabel()));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_3, "6, 6");
		
		JLabel lblNewLabel_4 = new JLabel("Soci\u00E9t\u00E9 : Aeroconseil");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_4, "6, 8");
		
		JLabel lblNewLabel_5 = new JLabel("Derni\u00E8re mise \u00E0 jour : " + LanguageSelector.getLocalizedText(ELabelUI.MISE_JOUR.getLabel()));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_5, "6, 10");
		
		JLabel lblNewLabel_6 = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.CONTACT.getLabel()));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_6, "6, 12");
		
		JLabel label = new JLabel(LanguageSelector.getLocalizedText(ELabelUI.SUIVI_VERS.getLabel()));
		label.setForeground(new Color(0, 119, 175));
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		add(label, "2, 14, 3, 1");
		
		SeparatorFlat separatorFlat = new SeparatorFlat();
		add(separatorFlat, "2, 15, 6, 1");
		
		JEditorPane textPane = new JEditorPane();
		textPane.setBorder(null);
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		textPane.setText(
			"<html>" + 
				"<style type=\"text/css\">"+
			    	"body {background-color:#FFFFFF;font-family:Arial; font-size:11pt;}" +
	        		".tr1 {background-color:#EEEEEE}" +
	        		".tr2 {background-color:#FFFFFF}" +
	        		".tdChamp {text-align: right; width:1000px; }" +
	        		".tdTitre {width:65px;}" +
	        	"</style>"+ "Description des versions :<br>" +
	        	"<body>" +
	        		"<form id='form' action='#'>" +
                    	"<table>" +
                        	"<tr>" +
		                        "<td>" +
		                        	"<table>" +
				                        "<tr class='tr1'>" +
					                        "<td class='tdTitre'>" + LanguageSelector.getLocalizedText(ELabelUI.V4_0.getLabel()) + "</td>" +
			                    			"<td class='tdChamp'>" + LanguageSelector.getLocalizedText(ELabelUI.TEXT_V4_0.getLabel()) + "</td>" +
		                        		"</tr>" +
		                        		"<tr class='tr2'>" +
			                        		"<td class='tdTitre'>" + LanguageSelector.getLocalizedText(ELabelUI.V3_0.getLabel()) + "</td>" +
			                    			"<td class='tdChamp'>" + LanguageSelector.getLocalizedText(ELabelUI.TEXT_V3_0.getLabel()) + "</td>" +
			                    		"</tr>" +
		                        		"<tr class='tr1'>" +
			                        		"<td class='tdTitre'>" + LanguageSelector.getLocalizedText(ELabelUI.V2_0.getLabel()) + "</td>" +
			                    			"<td class='tdChamp'> " + LanguageSelector.getLocalizedText(ELabelUI.TEXT_V2_0.getLabel()) + "</td>" +
		                    			"</tr>" +
		                    			"<tr class='tr2'>" +
			                    			"<td class='tdTitre'>" + LanguageSelector.getLocalizedText(ELabelUI.V1_0.getLabel()) + "</td>" +
		                        			"<td class='tdChamp'> " + LanguageSelector.getLocalizedText(ELabelUI.TEXT_V1_0.getLabel()) + "</td>" +
		                    			"</tr>" +
		                    		"</table>" +
		                    	"</td>"+
		                    "</tr>"+
		                "</table>"+
		            "</form>"+
		        "</body>"+
			"</html>");
		add(textPane, "2, 17, 6, 1, fill, fill");
	}
	
	public String getName(){
		return name;
	}
}