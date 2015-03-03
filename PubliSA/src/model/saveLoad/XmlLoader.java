package model.saveLoad;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import model.Delivery;
import model.Model;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.filter.Filter;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlLoader{
	   
	static org.jdom.Document document;
	static Element racine;
	
	static String xml = System.getProperty("user.dir")+File.separator+ "PubliSA.xml";
	
	public static Model loadModel(){
		
		//On crée une instance de SAXBuilder
	    SAXBuilder sxb = new SAXBuilder();
	    try
	    {
	  	   	document = sxb.build(new File(xml));
	  	   	racine = document.getRootElement();
	    }
	    catch(Exception e){
	    	JOptionPane.showMessageDialog(null, "Impossible de lancer PubliSA.\r\nVeuillez placer le fichier PubliSA.xml dans le même répertoire que PubliSA.exe","Erreur XML",JOptionPane.ERROR_MESSAGE);
			return null;
	    }
		
		Model model = new Model();
		loadUser(model);
		loadDelivery(model);
		return model;
	}
	
	public static void loadUser(Model model){
		ArrayList<Element> array = getUTI();
		if(array.size()==1){
			model.createUser(array.get(0).getText());
		}
	}
	
	public static void loadDelivery(Model model){
		ArrayList<Element> array = getLivraison(model.getUser().getName());
		for (int i = 0; i < array.size(); i++) {
			String name = array.get(i).getChildText("Nom");
			String cible = array.get(i).getChildText("Cible");
			int target;
			if(cible == "Thales"){
				target = Delivery.THALES;
			}else{
				target = Delivery.UBIK;
			}
			
			Delivery del = model.createDelivery(name, target);
			
			//STEP
			int step = Integer.parseInt(array.get(i).getChildText("Etape"));
			switch (step) {
			case Delivery.STEP1:
				del.setActualStep(Delivery.STEP1);
				break;
			case Delivery.STEP2:
				del.setActualStep(Delivery.STEP2);
				break;
			case Delivery.STEP3:
				del.setActualStep(Delivery.STEP3);
				break;
			case Delivery.STEP4:
				del.setActualStep(Delivery.STEP4);
				break;

			default:
				break;
			}
			
			//DCR
			del.setDCR(array.get(i).getChildText("DCR"));
		}
	}
	
	public static ArrayList<Element> getUTI(){
		ArrayList<Element> UTI = new ArrayList<Element>();
		Iterator<?> i = racine.getChildren().iterator();
	    while(i.hasNext())
	    {
	       Element courant = (Element)i.next();
	       if(!courant.getName().contentEquals("MultiUTI") 
	    		   && !courant.getName().contentEquals("FirstUti")
	    		   && !courant.getName().contentEquals("Langue")
	    		   && !courant.getName().contentEquals("Color")){
	    	   UTI.add(courant);
	       }
		}
		return UTI;
	}
	
	
	
	
	
	///////// To delete
	
	
	
	
	public static Color[] getColor(){
		
		int b1 = 0,r1 = 0,v1 = 0;
		int b2 = 0,r2 = 0,v2 = 0;
		int b3 = 0,r3 = 0,v3 = 0;
		try{
			b1 = Integer.parseInt(racine.getChild("Color").getChild("Color1").getAttributeValue("Blue"));
			r1 = Integer.parseInt(racine.getChild("Color").getChild("Color1").getAttributeValue("Red"));
			v1 = Integer.parseInt(racine.getChild("Color").getChild("Color1").getAttributeValue("Green"));
			                                                                  
			b2 = Integer.parseInt(racine.getChild("Color").getChild("Color2").getAttributeValue("Blue"));
			r2 = Integer.parseInt(racine.getChild("Color").getChild("Color2").getAttributeValue("Red"));
			v2 = Integer.parseInt(racine.getChild("Color").getChild("Color2").getAttributeValue("Green"));
			                                                                  
			b3 = Integer.parseInt(racine.getChild("Color").getChild("Color3").getAttributeValue("Blue"));
			r3 = Integer.parseInt(racine.getChild("Color").getChild("Color3").getAttributeValue("Red"));
			v3 = Integer.parseInt(racine.getChild("Color").getChild("Color3").getAttributeValue("Green"));
			
		}catch(Exception e){
			Element color = new Element("Color");
			racine.addContent(color);
			
			Element color1 = new Element("Color1");
			Element color2 = new Element("Color2");
			Element color3 = new Element("Color3");
			color.addContent(color1);
			color.addContent(color2);
			color.addContent(color3);
			
			Attribute bleu1 = new Attribute("Blue", "");
			Attribute rouge1 = new Attribute("Red", "");
			Attribute vert1 = new Attribute("Green", "");
			color1.setAttribute(bleu1);
			color1.setAttribute(rouge1);
			color1.setAttribute(vert1);

			Attribute bleu2 = new Attribute("Blue", "");
			Attribute rouge2 = new Attribute("Red", "");
			Attribute vert2 = new Attribute("Green", "");
			color2.setAttribute(bleu2);
			color2.setAttribute(rouge2);
			color2.setAttribute(vert2);
			
			Attribute bleu3 = new Attribute("Blue", "");
			Attribute rouge3 = new Attribute("Red", "");
			Attribute vert3 = new Attribute("Green", "");
			color3.setAttribute(bleu3);
			color3.setAttribute(rouge3);
			color3.setAttribute(vert3);
			
			Color[] couleur = new Color[3];
			couleur[0] = new Color(99, 130, 191);
			couleur[1] = new Color(194, 215, 234);
			couleur[2] = new Color(r3, v3, b3);
			
			XmlLoader.setColor(couleur);
		}
		
		Color[] couleur = new Color[3];
		couleur[0] = new Color(r1, v1, b1);
		couleur[1] = new Color(r2, v2, b2);
		couleur[2] = new Color(r3, v3, b3);
		
		return couleur;
	}
	
	public static void setColor(Color[] couleur){
		
		if(couleur[0] != null){
			racine.getChild("Color").getChild("Color1").getAttribute("Blue").setValue(Integer.toString(couleur[0].getBlue()));
			racine.getChild("Color").getChild("Color1").getAttribute("Red").setValue(Integer.toString(couleur[0].getRed()));
			racine.getChild("Color").getChild("Color1").getAttribute("Green").setValue(Integer.toString(couleur[0].getGreen()));
		}
		if(couleur[1] != null){
			racine.getChild("Color").getChild("Color2").getAttribute("Blue").setValue(Integer.toString(couleur[1].getBlue()));
			racine.getChild("Color").getChild("Color2").getAttribute("Red").setValue(Integer.toString(couleur[1].getRed()));
			racine.getChild("Color").getChild("Color2").getAttribute("Green").setValue(Integer.toString(couleur[1].getGreen()));
		}
		if(couleur[2] != null){
			racine.getChild("Color").getChild("Color3").getAttribute("Blue").setValue(Integer.toString(couleur[2].getBlue()));
			racine.getChild("Color").getChild("Color3").getAttribute("Red").setValue(Integer.toString(couleur[2].getRed()));
			racine.getChild("Color").getChild("Color3").getAttribute("Green").setValue(Integer.toString(couleur[2].getGreen()));
		}
		try {
			enregistreFichier();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getLangue(){
		try{
			racine.getChild("Langue").getText();
		}catch(Exception e){
			Element langue = new Element("Langue");
			racine.addContent(langue);
			
			XmlLoader.setLangue("FRANCAIS");
		}
		
		return racine.getChild("Langue").getText();
	}
	
	public static String getFirstUti(){
		return racine.getChild("FirstUti").getText();
	}
	
	public static void setFirtUti(String nom){
		racine.getChild("FirstUti").setText(nom);
	}
	
	public static boolean getModeUTI(){
		String texte = racine.getChild("MultiUTI").getText();
		return texte.contentEquals("True");
	}
	
	
	
	@SuppressWarnings({ "serial", "unchecked" })
	public static ArrayList<Element> getLivraison(String UTI){
		
		ArrayList<Element> liv = new ArrayList<Element>();
		
		Filter filtre = new Filter()
		   {
		      public boolean matches(Object ob)
		      {
		         if(!(ob instanceof Element)){return false;}

		         Element element = (Element)ob;
		         if(element.getName().indexOf("L")==0){
		        	 return true;
		         }
		         return false;
		      }
		   };
		   
		List<Element> listLiv = racine.getChild(UTI).getContent(filtre);
	    liv.addAll(listLiv);

	    return liv;
	}
	
	public static void newUTI(String nom){
		
		Element utilisateur = new Element(nom);
		racine.addContent(utilisateur);		
		
		Element FichierExt = new Element("FichierExt");
		Element FichierDOC = new Element("FichierDOC");
		Element FichierEXE = new Element("FichierEXE");
		
		utilisateur.addContent(FichierExt);
		FichierExt.addContent(FichierDOC);
		FichierExt.addContent(FichierEXE);
		
		Element Mail = new Element("Mail");
		Element AKKAUbik = new Element("AKKA-Ubik");
		Element AKKAThales = new Element("AKKA-Thales");
		Element SOPRA = new Element("SOPRA");
		Element THALES = new Element("THALES");
		Element Correspondant1 = new Element("Correspondant");
		Element Objet1 = new Element("Objet");
		Element Message1 = new Element("Message");
		Element Correspondant2 = new Element("Correspondant");
		Element Objet2 = new Element("Objet");
		Element Message2 = new Element("Message");
		Element Correspondant3 = new Element("Correspondant");
		Element Objet3 = new Element("Objet");
		Element Message3 = new Element("Message");
		Element Correspondant4 = new Element("Correspondant");
		Element Objet4 = new Element("Objet");
		Element Message4 = new Element("Message");
		
		utilisateur.addContent(Mail);
		
		Mail.addContent(AKKAUbik);
		AKKAUbik.addContent(Correspondant1);
		AKKAUbik.addContent(Objet1);
		AKKAUbik.addContent(Message1);
		
		Mail.addContent(AKKAThales);
		AKKAThales.addContent(Correspondant2);
		AKKAThales.addContent(Objet2);
		AKKAThales.addContent(Message2);
		
		Mail.addContent(SOPRA);
		SOPRA.addContent(Correspondant3);
		SOPRA.addContent(Objet3);
		SOPRA.addContent(Message3);
		
		Mail.addContent(THALES);
		THALES.addContent(Correspondant4);
		THALES.addContent(Objet4);
		THALES.addContent(Message4);
		
		Element GestLiv = new Element("GestLiv");
		utilisateur.addContent(GestLiv);
	}
	
	public static void newLivraison(String UTI, String nom){
		
		Element livraison = new Element("L" + nom);
		racine.getChild(UTI).addContent(livraison);
		
		Element Nom = new Element("Nom");
		Element Cible = new Element("Cible");
		Element Etape = new Element("Etape");
		Element DCR = new Element("DCR");
		Element Calculateur = new Element("Calculateur");
		Element Type = new Element("Type");
		Element Standard = new Element("Standard");
		Element AdresseOGC = new Element("AdresseOGC");
		
		livraison.addContent(Nom);
		livraison.addContent(Cible);
		livraison.addContent(Etape);
		livraison.addContent(DCR);
		livraison.addContent(Calculateur);
		livraison.addContent(AdresseOGC);
		
		Calculateur.addContent(Type);
		Calculateur.addContent(Standard);
	}
	
	public static String getNomLiv(String UTI, String Liv){
		return racine.getChild(UTI).getChild(Liv).getChild("Nom").getText();
	}
	public static String getCibleLiv(String UTI, String Liv){
		return racine.getChild(UTI).getChild(Liv).getChild("Cible").getText();
	}
	public static String getEtapeLiv(String UTI, String Liv){
		return racine.getChild(UTI).getChild(Liv).getChild("Etape").getText();
	}
	public static String getDCRLiv(String UTI, String Liv){
		return racine.getChild(UTI).getChild(Liv).getChild("DCR").getText();
	}
	public static String getCalculateurLiv(String UTI, String Liv){
		return racine.getChild(UTI).getChild(Liv).getChild("Calculateur").getChild("Type").getText();
	}
	public static String getStandardCalLiv(String UTI, String Liv){
		return racine.getChild(UTI).getChild(Liv).getChild("Calculateur").getChild("Standard").getText();
	}
	public static String getOGCLiv(String UTI, String Liv){
		return racine.getChild(UTI).getChild(Liv).getChild("AdresseOGC").getText();
	}
	
	public static boolean getDeleteDeleveryFinish(String UTI){
		String texte = racine.getChild(UTI).getChild("GestLiv").getText();
		return texte.contentEquals("True");
	}
	
	public static String getDOC(String UTI){
		return racine.getChild(UTI).getChild("FichierExt").getChild("FichierDOC").getText();
	}
	
	public static String getEXE(String UTI){
		return racine.getChild(UTI).getChild("FichierExt").getChild("FichierEXE").getText();
	}
	
	public static ArrayList<String> getMesAkkaUbik(String UTI){
		ArrayList<String> eMail = new ArrayList<String>();
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("AKKA-Ubik").getChild("Correspondant").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("AKKA-Ubik").getChild("Objet").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("AKKA-Ubik").getChild("Message").getText());
		return eMail;
	}
	
	public static ArrayList<String> getMesAkkaThales(String UTI){
		ArrayList<String> eMail = new ArrayList<String>();
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("AKKA-Thales").getChild("Correspondant").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("AKKA-Thales").getChild("Objet").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("AKKA-Thales").getChild("Message").getText());
		return eMail;
	}
	
	public static ArrayList<String> getMesSopra(String UTI){
		ArrayList<String> eMail = new ArrayList<String>();
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("SOPRA").getChild("Correspondant").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("SOPRA").getChild("Objet").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("SOPRA").getChild("Message").getText());
		return eMail;
	}
	
	public static ArrayList<String> getMesThales(String UTI){
		ArrayList<String> eMail = new ArrayList<String>();
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("THALES").getChild("Correspondant").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("THALES").getChild("Objet").getText());
		eMail.add(racine.getChild(UTI).getChild("Mail").getChild("THALES").getChild("Message").getText());
		return eMail;
	}
	public static void enregistreFichier() throws Exception
    {
		 XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		 sortie.output(document, new FileOutputStream(xml));
    }
	
	public static void setLangue(String langue){
		racine.getChild("Langue").setText(langue);
		try {
			enregistreFichier();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setModeUTI(boolean mode){
		if (mode){racine.getChild("MultiUTI").setText("True");}
		if (!mode){racine.getChild("MultiUTI").setText("False");}
	}
	
	public static void setNomLiv(String UTI, String Liv, String Nom){
		racine.getChild(UTI).getChild(Liv).getChild("Nom").setText(Nom);
	}
	public static void setCibleLiv(String UTI, String Liv, String Cible){
		racine.getChild(UTI).getChild(Liv).getChild("Cible").setText(Cible);
	}
	public static void setEtapeLiv(String UTI, String Liv, String Etape){
		racine.getChild(UTI).getChild(Liv).getChild("Etape").setText(Etape);
	}
	public static void setDCRLiv(String UTI, String Liv, String DCR){
		racine.getChild(UTI).getChild(Liv).getChild("DCR").setText(DCR);
	}
	public static void setCalculateurLiv(String UTI, String Liv, String Calculateur){
		racine.getChild(UTI).getChild(Liv).getChild("Calculateur").getChild("Type").setText(Calculateur);
	}
	public static void setStandardCalLiv(String UTI, String Liv, String Standard){
		racine.getChild(UTI).getChild(Liv).getChild("Calculateur").getChild("Standard").setText(Standard);
	}
	public static void setOGCLiv(String UTI, String Liv, String OGC){
		racine.getChild(UTI).getChild(Liv).getChild("AdresseOGC").setText(OGC);
	}
	
	public static void setGestLiv(String UTI, boolean gestLiv){
		if (gestLiv){racine.getChild(UTI).getChild("GestLiv").setText("True");}
		if (!gestLiv){racine.getChild(UTI).getChild("GestLiv").setText("False");}
	}
	
	public static void setDOC(String UTI, String DOC){
		racine.getChild(UTI).getChild("FichierExt").getChild("FichierDOC").setText(DOC);
	}
	
	public static void setEXE(String UTI, String EXE){
		racine.getChild(UTI).getChild("FichierExt").getChild("FichierEXE").setText(EXE);
	}
	
	public static void setMesAkkaUbik(String UTI, String correspondant, String objet, String message){
		racine.getChild(UTI).getChild("Mail").getChild("AKKA-Ubik").getChild("Correspondant").setText(correspondant);
		racine.getChild(UTI).getChild("Mail").getChild("AKKA-Ubik").getChild("Objet").setText(objet);
		racine.getChild(UTI).getChild("Mail").getChild("AKKA-Ubik").getChild("Message").setText(message);
	}
	
	public static void setMesAkkaThales(String UTI, String correspondant, String objet, String message){
		racine.getChild(UTI).getChild("Mail").getChild("AKKA-Thales").getChild("Correspondant").setText(correspondant);
		racine.getChild(UTI).getChild("Mail").getChild("AKKA-Thales").getChild("Objet").setText(objet);
		racine.getChild(UTI).getChild("Mail").getChild("AKKA-Thales").getChild("Message").setText(message);
	}
	
	public static void setMesSopra(String UTI, String correspondant, String objet, String message){
		racine.getChild(UTI).getChild("Mail").getChild("SOPRA").getChild("Correspondant").setText(correspondant);
		racine.getChild(UTI).getChild("Mail").getChild("SOPRA").getChild("Objet").setText(objet);
		racine.getChild(UTI).getChild("Mail").getChild("SOPRA").getChild("Message").setText(message);
	}
	
	public static void setMesThales(String UTI, String correspondant, String objet, String message){
		racine.getChild(UTI).getChild("Mail").getChild("THALES").getChild("Correspondant").setText(correspondant);
		racine.getChild(UTI).getChild("Mail").getChild("THALES").getChild("Objet").setText(objet);
		racine.getChild(UTI).getChild("Mail").getChild("THALES").getChild("Message").setText(message);
	}
	
	public static void supprLiv(String Uti, String Liv){
		racine.getChild(Uti).removeChild(Liv);
		try {
			enregistreFichier();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void supprUti(String Uti) {
		racine.removeChild(Uti);
		try {
			enregistreFichier();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}