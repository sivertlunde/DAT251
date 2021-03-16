package no.hvl.dat251.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingList {
	private String id;
	private LocalDateTime dato;
	private List<Map<Product, Integer>> produkter;
	
	public ShoppingList() {
		
	}
	
	public ShoppingList(String id, LocalDateTime dato, List<Map<Product, Integer>> produkter) {
		this.id = id;
		this.dato = dato;
		this.produkter = new ArrayList<Map<Product, Integer>>();
	}
	
	public void leggTil(Product produkt) {
		
	}
	
	public Product slett(Product produkt) {
		return null;
	}
	
	public double sumMin() {
		return 0;
	}

	public void genererHandleliste() {
		
	}
	
	public void genererPdf() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getDato() {
		return dato;
	}

	public void setDato(LocalDateTime dato) {
		this.dato = dato;
	}

	public List<Map<Product, Integer>> getProdukter() {
		return produkter;
	}

	public void setProdukter(List<Map<Product, Integer>> produkter) {
		this.produkter = produkter;
	}
}
