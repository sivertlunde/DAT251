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
	List<PorduktDirectory> firebaseproducts = new ArrayList<>();
	private ProductDirectory dir;
	
	
	
	@BeforeEach
	public void setup() {
		Map<String,String> product = new HashMap<String,String>();
		product.put("eple", "id1");
		firebaseproducts.add(product);
		product.put("agurk", "id2");
		firebaseproducts.add(product);
		product.put("egg", "id3");
		firebaseproducts.add(product);
		
		
	}
	
	@Test
	public void fromListToString() {
		String result;
		
		for(Map<String,String> map : firebaseproducts) {
			result = result + "( '" + map. + "', '" + tab.toString() +"')");
			
		}
		
		
	}
	
}
