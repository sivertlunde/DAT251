package no.hvl.dat251.backend;

import no.hvl.dat251.backend.model.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

public class DataFromFirestoreToDerbyTest {
	List<ProductDirectory> firebaseproducts = new ArrayList<>();
	private ProductDirectory dir1;
	private ProductDirectory dir2;
	private ProductDirectory dir3;
	
	
	
	@BeforeEach
	public void setup() {
		dir1 = new ProductDirectory("eple","id1");
		firebaseproducts.add(dir1);
		dir2 = new ProductDirectory("agurk", "id2");
		firebaseproducts.add(dir2);
		dir3 = new ProductDirectory("egg", "id3");
		firebaseproducts.add(dir3);
		
		
	}
	
	@Test
	public void fromListToString() {
		String result = "";
		if(!firebaseproducts.isEmpty()) {
		
			for(ProductDirectory dir : firebaseproducts) {
				result = result + "( '" + dir.getId() + "', '" + dir.getName() +"')";	
			}
			
		}
		
		
		
		
		
		
	}
	
}
