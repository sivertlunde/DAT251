package no.hvl.dat251.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "productDirectory")
public class ProductDirectory {
	@Id
	private String id;
	private String name;
	
	public ProductDirectory() {
		
	}
	
	public ProductDirectory(String id, String name) {
		this.id=id;
		this.name = name;
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
