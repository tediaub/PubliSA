package outils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import model.Delivery;
import model.Model;
import model.User;
import etape.Etape1;
import etape.Etape3;

/**
 * 
 * @author Teddy AUBERT
 *
 */
public class CreationMail {
	
	
	private String[] tabDCR;
	private static String txDCR;
	private String DCRMax = "0";
	private String correspondant = "";
	private String objet = "";
	private String message = "";
	private String standard = "";
	
	private boolean erreur;
	private String computer;

	/**
	 * Constructor for CreationMail.
	 * @param etape3 
	 * @param livraison int
	 */
	public CreationMail(Model model){
		erreur = false;
		
		switch(model.getMainDelivery().getActualStep()){
		case Delivery.STEP1 : 
			txDCR = Etape1.getDCR();
			standard = Etape1.getCalculateur();
			tabDCR = txDCR.split(",");
			break;
		case Delivery.STEP3 :
			txDCR = Etape3.getDCR();
			standard = Etape3.getCalculateur();
			tabDCR = txDCR.split(",");
			break;
		}
		
		txDCR = model.getMainDelivery().getDCR();
		standard = model.getMainDelivery().getStandard();
		computer = model.getMainDelivery().getComputer();
		
		tabDCR = txDCR.split(",");
		
		if(txDCR.isEmpty()){
			JOptionPane.showMessageDialog(null, "Erreur DCR.\r\nAucune DCR n'a été rentrée.", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
			erreur = true;
			return;
		}
		try{
			for (int i =0; i<tabDCR.length;i++){
				if(Integer.parseInt(DCRMax)<Integer.parseInt(tabDCR[i])){
					DCRMax = tabDCR[i];
				}
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Erreur DCR.\r\nVérifier que : \r\n- la/les DCR sont des nombres\r\n- il n'y a pas d'espace\r\n- le séparateur est bien une virgule(,)", "Erreur DCR", JOptionPane.ERROR_MESSAGE);
			erreur = true;
			return;
		}
		

		//récupération des données message
		switch (model.getMainDelivery().getActualStep()){
		case Delivery.STEP1:
			if(model.getMainDelivery().getTarge == Delivery.UBIK){
				correspondant = User.getMesAkkaUbik().get(0);
				objet = User.getMesAkkaUbik().get(1);
				message = User.getMesAkkaUbik().get(2);
			}
			else if(Delivery.getCible().contentEquals("Thales")){
				correspondant = User.getMesAkkaThales().get(0);
				objet = User.getMesAkkaThales().get(1);
				message = User.getMesAkkaThales().get(2);
			}
			break;
		case Delivery.STEP3:
			if(Delivery.getCible().contentEquals("Ubik")){
				correspondant = User.getMesSopra().get(0);
				objet = User.getMesSopra().get(1);
				message = User.getMesSopra().get(2);
			}
			else if(Delivery.getCible().contentEquals("Thales")){
				correspondant = User.getMesThales().get(0);
				objet = User.getMesThales().get(1);
				message = User.getMesThales().get(2);
			}
			break;
		}

	    String mesAjout = new String(" " + DCRMax);
	    StringTokenizer s = new StringTokenizer(message,"#");
	    message = "";
	    while (s.hasMoreTokens()) {
	       if(s.countTokens()!= 1){
	    	   message = message + s.nextToken() + mesAjout;
	       }
	       else{
	          message = message + s.nextToken();
	       }
	    }
	    
	    ///////////////////////////
	    for (int i =0; i<tabDCR.length;i++){
			if (i != 0){mesAjout = mesAjout + ", " + tabDCR[i];}
			else{mesAjout = "MOD " + tabDCR[i];}
		}
	    
	    s = new StringTokenizer(message,"*");
	    message = "";
	    while (s.hasMoreTokens()) {
	       if(s.countTokens()!= 1){
	    	   message = message + s.nextToken() + mesAjout;
	       }
	       else{
	    	   message = message + s.nextToken();
	       }
	    }
	    
	    ////////////////////////
	    for (int i =0; i<tabDCR.length;i++){
			if (i != 0){mesAjout = mesAjout + ", " + tabDCR[i];}
			else{mesAjout = "MOD " + tabDCR[i];}
		}
	    mesAjout = mesAjout + " (standard : " + standard +")"; 
	    s = new StringTokenizer(message,"@");
	    message = "";
	    while (s.hasMoreTokens()) {
	       if(s.countTokens()!= 1){
	    	   message = message + s.nextToken() + mesAjout;
	       }
	       else{
	    	   message = message + s.nextToken();
	       }
	    }
	    
	    for (int i =0; i<tabDCR.length;i++){
			if (i != 0){mesAjout = mesAjout + ", " + tabDCR[i];}
			else{mesAjout = " MOD " + tabDCR[i];}
		}
	    objet = objet + mesAjout + " (standard : " + standard+")";
	    if(Desktop.isDesktopSupported()){		
	    	if(Desktop.getDesktop().isSupported(Desktop.Action.MAIL)){
	    		try {
					 Desktop.getDesktop().mail(new URI("mailto", correspondant + "?subject=" + objet +"&body=" + message, null));
				 } catch (IOException e1) {
					 e1.printStackTrace();
				 } catch (URISyntaxException e1) {
					 e1.printStackTrace();
				 }
	    	}
	    }
	    
	}
	
	public boolean getErreur(){
		return erreur;
	}
}
