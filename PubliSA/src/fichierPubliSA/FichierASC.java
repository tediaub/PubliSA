package fichierPubliSA;

import java.io.File;

import langue.GestLangue;
import langue.IHM;

import utilisateur.Livraison;


/**
 */
@SuppressWarnings("serial")
public class FichierASC extends File{
	
	private String nom;
	private String adresse;
	private String repertoire;
	
	private String livre;
	private String chapitre;
	private String planche;
	private String variante;
	private String issue;
	
	private String NomTheorique;
	private String messageErreur = "";
	private String messageErrOriginal;
	private String DCR;

	
	private boolean isPresent;
	private boolean exception;

	
	/**
	 * Constructor for FichierPOS.
	 * @param adresse String
	 */
	public FichierASC(String adresse) {
		super(adresse);
		this.isPresent = false;
		this.adresse = adresse;
		this.nom = super.getName();
		this.repertoire = super.getParentFile().getPath();
		
		if(nom.substring(nom.indexOf(".")+1, nom.length()).toUpperCase().contains("ASC")){
			livre = new String(nom.substring(1,3));
			chapitre = new String(nom.substring(4,6));
			planche = new String(nom.substring(7,10));
			
			String nomFin;
			nomFin = new String(nom.substring(11, nom.length()));
			variante = new String(nom.substring(11, nomFin.indexOf("I")+11));
			issue = new String(nom.substring(nomFin.indexOf("I")+12, nom.indexOf(".")));
			
	        String message = new String();
	        char car = ' ';
	        try{
        	if (Integer.parseInt(livre)> 9 && Integer.parseInt(livre) < 36){
	        	car = (char)(55+Integer.parseInt(livre));
	        	livre = "0" + car; 
	        	message = "Livre";
	        	exception = true;
			}
			
			if (Integer.parseInt(chapitre) > 9 && Integer.parseInt(chapitre) < 36){      
			    if (car != ' '){
			      message = message + ", ";
			    }
				car = (char)(55+Integer.parseInt(chapitre));
				chapitre = "0" + car;
				message = message + "Chapitre";
				exception = true;
			}

			if (Integer.parseInt(planche) > 9 && Integer.parseInt(planche) < 36){       
			    if (car != ' '){
			      message = message + ", ";
				}
				car = (char)(55+Integer.parseInt(planche));
				planche = "00" + car;
				message = message + "Planche";
				exception = true;
			}
	        }catch(NumberFormatException e){
	        	NomTheorique = "L" + livre + "C" + chapitre + "P" + planche + "V" + variante + "I" + issue + ".ASC";
	        	messageErrOriginal = GestLangue.getInstance().getLocalizedText(IHM.MES_ERR_ORIGINAL1.getLabel()) + message + " " +GestLangue.getInstance().getLocalizedText(IHM.MES_ERR_ORIGINAL2.getLabel())+ " " + NomTheorique;
				return;
	        }
			NomTheorique = "L" + livre + "C" + chapitre + "P" + planche + "V" + variante + "I" + issue + ".ASC";
			messageErrOriginal = GestLangue.getInstance().getLocalizedText(IHM.MES_ERR_ORIGINAL1.getLabel()) + message + " " +GestLangue.getInstance().getLocalizedText(IHM.MES_ERR_ORIGINAL2.getLabel())+ " " + NomTheorique;
        }
	}
	
	/**
	 * Method changeAdresse.
	 * @return String
	 */
	public String changeAdresse(){
		boolean changement = false;
		
		if (nom.substring(nom.indexOf(".")+1, nom.length()).contains(";")){
	        nom = nom.substring(0, nom.indexOf(";"));
	        adresse = repertoire + File.separator + nom;
	        changement = true;
	    }
		if(nom.substring(nom.indexOf(".")+1, nom.length()).toUpperCase().contains("ASC")){
			//Vérification des erreurs de nom des planches
			if(nom.substring(nom.indexOf(".")+1, nom.length()).contentEquals("asc")){
				nom = nom.substring(0, nom.lastIndexOf(".")) + ".ASC";
				adresse = repertoire + File.separator + nom;
				changement = true;
			}
			issue = new String(nom.substring(nom.lastIndexOf("I"), nom.lastIndexOf(".")));
			if (issue.length() == 2){
				issue = "I0" + issue.charAt(1);
				nom = nom.substring(0, nom.lastIndexOf("I")) + issue + ".ASC";
				adresse = repertoire + File.separator + nom;
		        changement = true;
			}
			
		}
		if(changement){
			this.renameTo(new File(adresse));
		}
		return adresse;
	}
	
	/**
	 * Method getNom.
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Method getRepertoire.
	 * @return String
	 */
	public String getRepertoire(){
		return this.repertoire;
	}
	
	/**
	 * Method getLivre.
	 * @return String
	 */
	public String getLivre(){
		return this.livre;
	}
	
	/**
	 * Method getChapitre.
	 * @return String
	 */
	public String getChapitre(){
		return this.chapitre;
	}
	
	/**
	 * Method getPlanche.
	 * @return String
	 */
	public String getPlanche(){
		return this.planche;
	}
	
	/**
	 * Method getVariante.
	 * @return String
	 */
	public String getVariante(){
		return this.variante;
	}
	
	/**
	 * Method getIssue.
	 * @return String
	 */
	public String getIssue(){
		return this.issue;
	}
	
	/**
	 * Method getMessage.
	 * @return String
	 */
	public String getMessage(){
		return this.messageErreur;
	}
	
	/**
	 * Method setMessage.
	 * @param indice int
	 */
	public void setMessage(int indice){
		if (indice == 0){
			messageErreur = GestLangue.getInstance().getLocalizedText(IHM.MES_ERR0.getLabel());
		}
		if (indice == 1){
			messageErreur = GestLangue.getInstance().getLocalizedText(IHM.MES_ERR1.getLabel()) + messageErrOriginal;
		}
		if (indice == 2){
			messageErreur = GestLangue.getInstance().getLocalizedText(IHM.MES_ERR2.getLabel());
		}
	}
	
	/**
	 * Method getNomTheorique.
	 * @return String
	 */
	public String getNomTheorique(){
		return this.NomTheorique;
	}
	
	/**
	 * Method getException.
	 * @return boolean
	 */
	public boolean getException(){
		return this.exception;
	}
	
	/**
	 * Method getPresent.
	 * @return boolean
	 */
	public boolean getPresent(){
		return isPresent;
	}
	
	public void isPresent(){
		isPresent = true;
		
		File repertoire = new File(this.getParentFile().getPath() + File.separator + Livraison.getNom());
		if(!repertoire.exists()){repertoire.mkdir();}
		renameTo(new File(this.getParentFile().getPath() + File.separator + Livraison.getNom() + File.separator + this.getName()));
	}
	
	/**
	 * Method getDCR.
	 * @return String
	 */
	public String getDCR(){
		return DCR;
	}

	/**
	 * Method setDCR.
	 * @param DCR String
	 */
	public void setDCR(String DCR){
		this.DCR = DCR;
	}
}
