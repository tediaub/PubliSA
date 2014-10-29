package utilisateur;

import XML.XML;

public class Livraison {
	
	private static String Uti;
	
	private static String nom;
	private static String cible;
	
	private static int etape = 0;
	private static int etapeMAX = 0;
	
	private static String dcr;
	private static String calculateur;
	private static String standard;
	
	private static String adresseOGC;
	
	
	@SuppressWarnings("static-access")
	public Livraison(String Uti, String nom){
		this.nom = nom;
		this.Uti = Uti;
		etapeMAX = 0;
		
		cible = XML.getCibleLiv(Uti, "L" + nom);
		setEtape(Integer.parseInt(XML.getEtapeLiv(Uti, "L" + nom)));
		
		dcr = XML.getDCRLiv(Uti, "L" + nom);
		calculateur = XML.getCalculateurLiv(Uti, "L" + nom);
		standard = XML.getStandardCalLiv(Uti, "L" + nom);
		
		adresseOGC = XML.getOGCLiv(Uti, "L" + nom);
	}
	
	public static void refresh(){
		cible = XML.getCibleLiv(Uti, "L" + nom);
		setEtape(Integer.parseInt(XML.getEtapeLiv(Uti, "L" + nom)));
		
		dcr = XML.getDCRLiv(Uti, "L" + nom);
		calculateur = XML.getCalculateurLiv(Uti, "L" + nom);
		standard = XML.getStandardCalLiv(Uti, "L" + nom);
		
		adresseOGC = XML.getOGCLiv(Uti, "L" + nom);
	}
	
	public static void enregistreLiv(){
		XML.setCibleLiv(Uti, "L" + nom, cible);
		XML.setEtapeLiv(Uti, "L" + nom, Integer.toString(etape));
		XML.setDCRLiv(Uti, "L" + nom, dcr);
		XML.setCalculateurLiv(Uti, "L" + nom, calculateur);
		XML.setStandardCalLiv(Uti, "L" + nom, standard);
		XML.setOGCLiv(Uti, "L" + nom, adresseOGC);
		
		try {
			XML.enregistreFichier();
			Utilisateur.refresh();
			refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getNom(){
		return nom;
	}
	
	public static String getCible(){
		return cible;
	}
	
	public static int getEtape(){
		return etape;
	}
	
	public static int getEtapeMAX(){
		return etapeMAX;
	}
	
	public static void setEtape(int etap) {
		if(etap>etapeMAX){
			etapeMAX = etap;
		}
		etape = etap;
	}
	
	public static String getDCR(){
		return dcr;
	}
	public static void setDCR(String DCR) {
		dcr = DCR;
	}
	
	public static String getCalculateur(){
		return calculateur;
	}
	public static void setCalculateur(String calculo) {
		calculateur = calculo;
	}
	
	public static String getStandard(){
		return standard;
	}
	public static void setStandard(String stand) {
		standard = stand;
	}
	
	public static String getOGC(){
		return adresseOGC;
	}
	public static void setOGC(String OGC){
		adresseOGC = OGC;
	}


}
