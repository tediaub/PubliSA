package model.saveLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Delivery;

import org.jdom.Element;
import org.jdom.filter.Filter;
import org.jdom.input.SAXBuilder;

import view.frame.dialog.DialogFlat;
import controller.OpeningController;

public class XmlLoader{
	   
	static org.jdom.Document document;
	static Element racine;
	
	static String xml = System.getProperty("user.dir")+File.separator+ "PubliSA.xml";
	
	public XmlLoader(String path){
		
		//On crée une instance de SAXBuilder
	    SAXBuilder sxb = new SAXBuilder();
	    try
	    {
	  	   	document = sxb.build(new File(path));
	  	   	racine = document.getRootElement();
	    }
	    catch(Exception e){
	    	new DialogFlat().showDialog("Fichier XML",
					"Erreur fichier XML",
					DialogFlat.ERROR_OPERATION,
					DialogFlat.ERROR_ICON);
	    	return;
	    }
	}
	
	public void loadUser(String user, OpeningController control){
		control.createUser(user);
		loadDeliveries(user, control);
		loadMail(user, control);
		loadParameters(user, control);
	}
	
	private void loadMail(String user, OpeningController control) {
		control.setMail(control.getModel().getUser().getMails().get(0),
				racine.getChild(user).getChild("Mail").getChild("AKKA-Ubik").getChildText("Correspondant"),
				racine.getChild(user).getChild("Mail").getChild("AKKA-Ubik").getChildText("Objet"),
				racine.getChild(user).getChild("Mail").getChild("AKKA-Ubik").getChildText("Message"));
		control.setMail(control.getModel().getUser().getMails().get(1),
				racine.getChild(user).getChild("Mail").getChild("AKKA-Thales").getChildText("Correspondant"),
				racine.getChild(user).getChild("Mail").getChild("AKKA-Thales").getChildText("Objet"),
				racine.getChild(user).getChild("Mail").getChild("AKKA-Thales").getChildText("Message"));
		control.setMail(control.getModel().getUser().getMails().get(2),
				racine.getChild(user).getChild("Mail").getChild("SOPRA").getChildText("Correspondant"),
				racine.getChild(user).getChild("Mail").getChild("SOPRA").getChildText("Objet"),
				racine.getChild(user).getChild("Mail").getChild("SOPRA").getChildText("Message"));
		control.setMail(control.getModel().getUser().getMails().get(3),
				racine.getChild(user).getChild("Mail").getChild("THALES").getChildText("Correspondant"),
				racine.getChild(user).getChild("Mail").getChild("THALES").getChildText("Objet"),
				racine.getChild(user).getChild("Mail").getChild("THALES").getChildText("Message"));
	}

	private void loadParameters(String user, OpeningController control) {
		control.getModel().getUser().setPathExe(racine.getChild(user).getChild("FichierExt").getChildText("FichierEXE"));
		control.getModel().getUser().setPathWord(racine.getChild(user).getChild("FichierExt").getChildText("FichierDOC"));
		control.getModel().getUser().setDeleteFinishDelivery(Boolean.parseBoolean(racine.getChild(user).getChildText("GestLiv")));
	}

	private void loadDeliveries(String user, OpeningController control){
		ArrayList<Element> array = getDeliveries(user);
		for (int i = 0; i < array.size(); i++) {
			String name = array.get(i).getChildText("Nom");
			String cible = array.get(i).getChildText("Cible");
			int target;
			if(cible == "Thales"){
				target = Delivery.THALES;
			}else{
				target = Delivery.UBIK;
			}
			
			Delivery del = control.getModel().createDelivery(name, target);
			
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
			
			//Computer
			del.setComputer(array.get(i).getChild("Calculateur").getChildText("Type"));
			del.setStandard(array.get(i).getChild("Calculateur").getChildText("Standard"));
			
			//OGC
			del.setPathOGC(array.get(i).getChildText("AdresseOGC"));
		}
	}
	
	@SuppressWarnings({ "serial", "unchecked" })
	private static ArrayList<Element> getDeliveries(String UTI){
		
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
	
	public ArrayList<String> getUsers(){
		ArrayList<String> UTI = new ArrayList<String>();
		Iterator<?> i = racine.getChildren().iterator();
	    while(i.hasNext())
	    {
	       Element courant = (Element)i.next();
	       if(!courant.getName().contentEquals("MultiUTI") 
	    		   && !courant.getName().contentEquals("FirstUti")
	    		   && !courant.getName().contentEquals("Langue")
	    		   && !courant.getName().contentEquals("Color")){
	    	   UTI.add(courant.getName());
	       }
		}
		return UTI;
	}
}
