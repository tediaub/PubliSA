package model;

import java.io.File;
import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Observable;

import model.saveLoad.Serialization;

public class Model extends Observable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user = null;
	
	private Delivery mainDelivery = null;
	private ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
	
	public User createUser(String name){
		user = new User(name, this);
		notice();
		return user;
	}

	public User getUser(){
		return user;
	}
	
	public Delivery createDelivery(){
		return createDelivery("", Delivery.UBIK); 
	}
	
	public Delivery createDelivery(String deliveryName, int target){
		Delivery delivery = new Delivery(deliveryName, target, this);
		setMainDelivery(delivery);
		deliveries.add(delivery);
		
		notice();
		
		return delivery; 
	}
	
	public void changeMainDelivery(Delivery delivery){
		setMainDelivery(delivery);
		
		notice();
	}
	
	public void setMainDelivery(Delivery delivery){
		if(mainDelivery != null){
			mainDelivery.setMain(false);
		}
		mainDelivery = delivery;
		delivery.setMain(true);
	}
	
	public Delivery getMainDelivery(){
		return mainDelivery;
	}
	
	public Delivery getDelivery(String deliveryName){
		for (int i = 0; i < deliveries.size(); i++) {
			if(deliveries.get(i).getName() == deliveryName){
				return deliveries.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Delivery> getDeliveries(){
		return deliveries;
	}
	
	public void notice() {
		setChanged();
		notifyObservers();
	}

	public void clear() {
		deliveries.clear();
		mainDelivery = null;
	}

	public void deleteDelivery(Delivery delivery) {
		deliveries.remove(delivery);
		notice();
	}

	public static Model create() {
		File datafile = getDataFile();
		if(datafile != null && datafile.exists()){
			return Serialization.loadModel(datafile);
		}
		return new Model();
	}
	
	public boolean save(){
		File datafile = getDataFile();
		if(datafile != null && datafile.exists()){
			return Serialization.saveModel(this, getDataFile());
		}
		return false;
	}
	
	private static File getDataFile(){
		if(new File("data").exists()){
			return new File("data");
		}
		return null;
	}
}
