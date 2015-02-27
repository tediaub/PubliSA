package model;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
	
	private User user = null;
	
	private Delivery mainDelivery = null;
	private ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
	
	public Model(){
		createUser("Teddy");
	}
	
	public void createUser(String name){
		user = new User(name, this);
		notice();
	}

	public User getUser(){
		return user;
	}
	
	public void createDelivery(String deliveryName, int target){
		Delivery delivery = new Delivery(deliveryName, target, this);
		setMainDelivery(delivery);
		deliveries.add(delivery);
		
		setChanged();
		notifyObservers();
	}
	
	public void setMainDelivery(Delivery delivery){
		if(mainDelivery != null){
			mainDelivery.setMain(false);
		}
		delivery.setMain(true);
		mainDelivery = delivery;
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
}
