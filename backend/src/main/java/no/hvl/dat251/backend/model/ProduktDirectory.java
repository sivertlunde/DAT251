package no.hvl.dat251.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "productDirectories")
public class ProduktDirectory {
	@Id
	private String id;
	private String name;
	
	public ProduktDirectory() {
		
	}
	
	public ProduktDirectory(String name, String id) {
		this.name = name;
		this.id=id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
