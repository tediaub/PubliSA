package langue;

import java.util.Locale;

/**
 * Enumeration des langues du programme
 * @author ZEGGWAGH_L
 *
 */

public enum Langue {
	ANGLAIS("ANGLAIS",new Locale("en")),
	FRANCAIS("FRANCAIS",new Locale("fr"));

	protected String label;
	protected Locale locale;

	/** Constructeur */
	Langue(String pLabel,Locale locale) {
		this.label = pLabel;
		this.locale=locale;
	}

	public String getLabel() {
		return this.label;
	}

	public Locale getLocale() {
		return this.locale;
	}
}
