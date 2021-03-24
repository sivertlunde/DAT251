package no.hvl.dat251.backend.model;

import java.util.*;

public class Product {
	private String id;
	private String name;
	private Map<String, Double> storePrice;
	private double weight;
	private String category;
	
	public Product() {
		
	}
	
	public Product(String id, String name, Map<String, Double> storePrice, double weight, String category) {
		this.id = id;
		this.name = name;
		this.storePrice = storePrice;
		this.weight = weight;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Double> getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Map<String, Double> storePrice) {
		this.storePrice = storePrice;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
