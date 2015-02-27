package model;

import java.util.ArrayList;
import java.util.Observable;

import model.svg.DocSVG;

import org.w3c.dom.Document;

public class Model extends Observable{
	
	private User user = null;
	
	private Document docArianeSVG = null;
	
	private Delivery mainDelivery = null;
	private ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
	
	public Model(){
		createUser("Teddy");
	}
	
	public void createUser(String name){
		user = new User(name);
		notice();
	}
	
	public User getUser(){
		return user;
	}
	
	public void setMainDelivery(Delivery delivery){
		if(mainDelivery != null){
			mainDelivery.setMain(false);
		}
		mainDelivery = delivery;
	}
	
	public Delivery getMainDelivery(){
		return mainDelivery;
	}
	
	public void createDelivery(String deliveryName, int target){
		Delivery delivery = new Delivery(deliveryName, target);
		delivery.setActualStep(Delivery.STEP1);
		setMainDelivery(delivery);
		deliveries.add(delivery);
		notice();
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
	
	private void notice(){
		setChanged();
		notifyObservers();
	}
}
