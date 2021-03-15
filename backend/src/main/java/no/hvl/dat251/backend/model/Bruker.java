package no.hvl.dat251.backend.model;

import java.util.List;

public class Bruker {
	private String id;
	private String email;
	private String passord;
	private String addresse;
	private Handleliste tidligereLister;
	private List<Produkt> favoritter;
	private List<Produkt> svarteliste;
	
	public Bruker(String id, String email, String passord, String addresse) {
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

	public Handleliste getTidligereLister() {
		return tidligereLister;
	}

	public void setTidligereLister(Handleliste tidligereLister) {
		this.tidligereLister = tidligereLister;
	}

	public List<Produkt> getFavoritter() {
		return favoritter;
	}

	public void setFavoritter(List<Produkt> favoritter) {
		this.favoritter = favoritter;
	}

	public List<Produkt> getSvarteliste() {
		return svarteliste;
	}

	public void setSvarteliste(List<Produkt> svarteliste) {
		this.svarteliste = svarteliste;
	}	
}
