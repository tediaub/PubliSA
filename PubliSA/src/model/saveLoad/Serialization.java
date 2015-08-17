package model.saveLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Model;

public class Serialization {

	static String path = System.getProperty("user.dir")+File.separator;
	
	public static Model loadModel(File dataFile){
		Model model = null;
		try {
			FileInputStream file = new FileInputStream(dataFile);
			model = (Model)extracted(file).readObject();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidClassException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	private static ObjectInputStream extracted(FileInputStream file)
			throws IOException {
		return new ObjectInputStream(file);
	}
	
	public static boolean saveModel(Model model, File dataFile){
		ObjectOutputStream out = null;
		Boolean b = true;
		try {
			FileOutputStream fichier = new FileOutputStream(dataFile);
			out = new ObjectOutputStream(fichier);
			
			out.writeObject(model);
			
		} catch (IOException e) {
			b = false;
			e.printStackTrace();
		}finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			}catch (final IOException ex) {
					ex.printStackTrace();
			}
		}
		return b;
	}
}
