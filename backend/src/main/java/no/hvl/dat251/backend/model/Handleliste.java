package no.hvl.dat251.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Handleliste {
	private String id;
	private LocalDateTime dato;
	private List<Map<Produkt, Integer>> produkter;
	
	public Handleliste() {
		
	}
	
	public Handleliste(String id, LocalDateTime dato, List<Map<Produkt, Integer>> produkter) {
		this.id = id;
		this.dato = dato;
		this.produkter = new ArrayList<Map<Produkt, Integer>>();
	}
	
	public void leggTil(Produkt produkt) {
		
	}
	
	public Produkt slett(Produkt produkt) {
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

	public List<Map<Produkt, Integer>> getProdukter() {
		return produkter;
	}

	public void setProdukter(List<Map<Produkt, Integer>> produkter) {
		this.produkter = produkter;
	}
}
