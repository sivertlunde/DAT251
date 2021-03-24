package no.hvl.dat251.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingList {
	private String id;
	private LocalDateTime date;
	private List<Map<Product, Integer>> products;
	
	public ShoppingList() {
		
	}
	
	public ShoppingList(String id, LocalDateTime date, List<Map<Product, Integer>> products) {
		this.id = id;
		this.date = date;
		this.products = new ArrayList<Map<Product, Integer>>();
	}
	
	public void add(Product product) {
		
	}
	
	public Product delete(Product product) {
		return null;
	}
	
	public double sumMin() {
		return 0;
	}

	public void generateShoppingList() {
		
	}
	
	public void generatePdf() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<Map<Product, Integer>> getProducts() {
		return products;
	}

	public void setProducts(List<Map<Product, Integer>> products) {
		this.products = products;
	}
}
