package no.hvl.dat251.backend.model;

import java.util.*;

public class Product {
	private String id;
	private String navn;
	private Map<String, Double> butikkPris;
	private double vekt;
	private String kategori;
	
	public Product() {
		
	}
	
	public Product(String id, String navn, Map<String, Double> butikkPris, double vekt, String kategori) {
		this.id = id;
		this.navn = navn;
		butikkPris = new HashMap<String, Double>();
		this.vekt = vekt;
		this.kategori = kategori;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Map<String, Double> getButikkPris() {
		return butikkPris;
	}

	public void setButikkPris(Map<String, Double> butikkPris) {
		this.butikkPris = butikkPris;
	}

	public double getVekt() {
		return vekt;
	}

	public void setVekt(double vekt) {
		this.vekt = vekt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

}
