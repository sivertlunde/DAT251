package no.hvl.dat251.backend.model;

public class Store {
	private String name;
	private String url;
	
	public Store() {
		
	}
	
	public Store(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
