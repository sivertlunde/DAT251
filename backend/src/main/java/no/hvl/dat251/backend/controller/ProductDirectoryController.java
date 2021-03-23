package no.hvl.dat251.backend.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import no.hvl.dat251.backend.firestore.FirestoreUtil;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;

@RestController
public class ProductDirectoryController {
	
	FirestoreUtil firestoreutil;
	
	public ProductDirectoryController(FirestoreUtil firestoreutil) {
		this.firestoreutil = firestoreutil;
	}

	@Autowired
	ProductDirectoryRepository directoryRepository;
	public String url ="jdbc:derby:derbydb";
	
	
	@GetMapping("/api/productDirectories")
	public ResponseEntity<List<ProductDirectory>> getAllProducts ()  {
		
		//DerbyConnectionSetup dbsetup = new DerbyConnectionSetup(url);
		//Connection conn = dbsetup.setupConnection(url);
		try {
			List<ProductDirectory> dirs = new ArrayList<ProductDirectory>();
			
			directoryRepository.findAll().forEach(dirs::add);
			

			if (dirs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(dirs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public String sqlStmtFromData(List<ProductDirectory> firebaseproducts) {
		// TODO Auto-generated method stub
		String result ="";
		if(!firebaseproducts.isEmpty()) {
			for(int i =0; i<firebaseproducts.size(); i++ ) {
				if(i==firebaseproducts.size()-1) {
					result = result + "( '" + firebaseproducts.get(i).getId() + "', '" + firebaseproducts.get(i).getName() +"');";
				}else {
					result = result + "( '" + firebaseproducts.get(i).getId() + "', '" + firebaseproducts.get(i).getName() +"'),";	
				}
			}
			
		}
		
		return result;
		
	}


	public List<ProductDirectory> dataToList(List<QueryDocumentSnapshot> documents) {
		// TODO Auto-generated method stub
		List<ProductDirectory> pdlist = new ArrayList<>();
		for(QueryDocumentSnapshot document : documents) {
			pdlist.add(new ProductDirectory(document.getId(), document.get("name").toString()));
		}
		
		return pdlist;
	}
	
	
	
	
}
