package no.hvl.dat251.backend.model;

import java.util.List;

public class User {
	private String id;
	private String email;
	private String passord;
	private String addresse;
	private ShoppingList tidligereLister;
	private List<Product> favoritter;
	private List<Product> svarteliste;
	
	public User() {
		
	}
	
	public User(String id, String email, String passord, String addresse) {
		this.id = id;
		this.email = email;
		this.passord = passord;
		this.addresse = addresse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public ShoppingList getTidligereLister() {
		return tidligereLister;
	}

	public void setTidligereLister(ShoppingList tidligereLister) {
		this.tidligereLister = tidligereLister;
	}

	public List<Product> getFavoritter() {
		return favoritter;
	}

	public void setFavoritter(List<Product> favoritter) {
		this.favoritter = favoritter;
	}

	public List<Product> getSvarteliste() {
		return svarteliste;
	}

	public void setSvarteliste(List<Product> svarteliste) {
		this.svarteliste = svarteliste;
	}	
}
