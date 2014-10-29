package outils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Lanceur {

	public Lanceur(String adresse){
		 try {
			Desktop.getDesktop().open(new File(adresse));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
