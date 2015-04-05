package langue;

import java.util.ResourceBundle;

/**
 * Classe gérant l'internationnalisation
 * @author ZEGGWAGH_L
 *
 */

public class GestLangue  {
	
	private final static String DICTIONNAIRES ="PubliSA";

		
	// La locale utilisée (la langue actuellement utilisée)
	private static Langue currentLanguage=Langue.FRANCAIS;

	// Ressource locale
	private static ResourceBundle messages = ResourceBundle.getBundle(
			DICTIONNAIRES,currentLanguage.getLocale());	 
	
	/**
	 * Renvoi le texte correspond dans la langue choisi
	 * @param text texte que l'on veut
	 **/
	public static String getLocalizedText(String text)
	{
		return messages.getString(text);
	}
	
	/**
	 * Cette fonction sert à changer de langue
	 * @param Langue la nouvelle langue
	 **/
	public static void setLangue(Langue langue)
	{
		currentLanguage=langue;
		
		// Ressource locale
		messages = ResourceBundle.getBundle(DICTIONNAIRES,currentLanguage.getLocale());
	}
	
	/**
	 *Retourne la langue actuelle
	 *@return la langue actuelle
	 **/
	public static Langue getCurrentLanguage()
	{
		return currentLanguage;
	}
}
