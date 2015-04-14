package view.language;

import java.util.ResourceBundle;

/**
 * Classe g�rant l'internationnalisation
 * @author ZEGGWAGH_L
 *
 */

public class LanguageSelector  {
	
	private final static String DICTIONNAIRES ="PubliSA";

		
	// La locale utilis�e (la langue actuellement utilis�e)
	private static ELanguage currentLanguage=ELanguage.FRANCAIS;

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
	 * Cette fonction sert � changer de langue
	 * @param ELanguage la nouvelle langue
	 **/
	public static void setLangue(ELanguage langue)
	{
		currentLanguage=langue;
		
		// Ressource locale
		messages = ResourceBundle.getBundle(DICTIONNAIRES,currentLanguage.getLocale());
	}
	
	/**
	 *Retourne la langue actuelle
	 *@return la langue actuelle
	 **/
	public static ELanguage getCurrentLanguage()
	{
		return currentLanguage;
	}
}
