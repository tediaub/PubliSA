package utilisateur;

import java.util.ArrayList;

import XML.XML;

public class Utilisateur {
	
	private static String nom;
	
	private static String EXE;
	private static String DOC;
	
	private static ArrayList<String> AkkaUbik = new ArrayList<String>();
	private static ArrayList<String> AkkaThales = new ArrayList<String>();
	private static ArrayList<String> Sopra = new ArrayList<String>();
	private static ArrayList<String> Thales = new ArrayList<String>();
	
	private static boolean gestLiv;
	
	
	@SuppressWarnings("static-access")
	public Utilisateur(String nom){
		this.nom = nom;
		
		EXE = XML.getEXE(nom);
		DOC = XML.getDOC(nom);
		
		AkkaUbik = XML.getMesAkkaUbik(nom);
		AkkaThales = XML.getMesAkkaThales(nom);
		Sopra = XML.getMesSopra(nom);
		Thales = XML.getMesThales(nom);
		
		gestLiv = XML.getGestLiv(nom);
	}
	
	public static void refresh(){		
		EXE = XML.getEXE(nom);
		DOC = XML.getDOC(nom);
		
		AkkaUbik = XML.getMesAkkaUbik(nom);
		AkkaThales = XML.getMesAkkaThales(nom);
		Sopra = XML.getMesSopra(nom);
		Thales = XML.getMesThales(nom);
		
		gestLiv = XML.getGestLiv(nom);
	}
	
	public static String getNom(){
		return nom;
	}
	
	public static String getExe(){
		return EXE;
	}
	
	public static String getDoc(){
		return DOC;
	}
	
	public static ArrayList<String> getMesAkkaUbik(){
		return AkkaUbik;
	}
	
	public static ArrayList<String> getMesAkkaThales(){
		return AkkaThales;
	}
	
	public static ArrayList<String> getMesSopra(){
		return Sopra;
	}
	
	public static ArrayList<String> getMesThales(){
		return Thales;
	}
	
	public static boolean getGestLiv(){
		return gestLiv;
	}
}
