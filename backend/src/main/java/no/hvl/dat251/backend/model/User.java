package no.hvl.dat251.backend.model;

import java.util.List;

public class User {
	private String id;
	private String email;
	private String password;
	private String address;
	private ShoppingList previousLists;
	private List<Product> favorites;
	private List<Product> blackList;
	
	public User() {
		
	}
	
	public User(String id, String email, String password, String address) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ShoppingList getPreviousLists() {
		return previousLists;
	}

	public void setPreviousLists(ShoppingList previousLists) {
		this.previousLists = previousLists;
	}

	public List<Product> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Product> favorites) {
		this.favorites = favorites;
	}

	public List<Product> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<Product> blackList) {
		this.blackList = blackList;
	}
}
