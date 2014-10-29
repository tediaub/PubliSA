package outils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import utilisateur.Livraison;
import utilisateur.Utilisateur;

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

	/**
	 * Constructor for CreationMail.
	 * @param etape3 
	 * @param livraison int
	 */
	public CreationMail(){
		erreur = false;
		int livraison = Livraison.getEtape();
		switch(livraison){
		case 1 : 
			txDCR = Etape1.getDCR();
			standard = Etape1.getCalculateur();
			tabDCR = txDCR.split(",");
			break;
		case 3 :
			txDCR = Etape3.getDCR();
			standard = Etape3.getCalculateur();
			tabDCR = txDCR.split(",");
			break;
		}
		
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
		switch (Livraison.getEtape()){
		case 1:
			if(Livraison.getCible().contentEquals("Ubik")){
				correspondant = Utilisateur.getMesAkkaUbik().get(0);
				objet = Utilisateur.getMesAkkaUbik().get(1);
				message = Utilisateur.getMesAkkaUbik().get(2);
			}
			else if(Livraison.getCible().contentEquals("Thales")){
				correspondant = Utilisateur.getMesAkkaThales().get(0);
				objet = Utilisateur.getMesAkkaThales().get(1);
				message = Utilisateur.getMesAkkaThales().get(2);
			}
			break;
		case 3:
			if(Livraison.getCible().contentEquals("Ubik")){
				correspondant = Utilisateur.getMesSopra().get(0);
				objet = Utilisateur.getMesSopra().get(1);
				message = Utilisateur.getMesSopra().get(2);
			}
			else if(Livraison.getCible().contentEquals("Thales")){
				correspondant = Utilisateur.getMesThales().get(0);
				objet = Utilisateur.getMesThales().get(1);
				message = Utilisateur.getMesThales().get(2);
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
