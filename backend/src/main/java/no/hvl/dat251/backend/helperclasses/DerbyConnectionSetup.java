package no.hvl.dat251.backend.helperclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.client.am.SqlException;

public class DerbyConnectionSetup {

	public String url ="jdbc:derby:derbydb";
			
	public DerbyConnectionSetup(String url) {
		this.url = url;
	}		
	
	public Connection setupConnection(String url) throws SqlException {
		System.setProperty("derby.system.home", "./Derby");
		Connection con = null;
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
}
