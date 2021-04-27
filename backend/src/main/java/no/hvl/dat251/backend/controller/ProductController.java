package no.hvl.dat251.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import no.hvl.dat251.backend.firestore.FirebaseInitializer;
import no.hvl.dat251.backend.firestore.FirestoreConfig;
import no.hvl.dat251.backend.firestore.FirestoreUtil;
import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;

@RestController
public class ProductController {
	
	
	@Autowired
	FirestoreConfig config;
	
	FirestoreUtil firestoreUtil;
	
	@Autowired
	ProductDirectoryRepository pdRepo;
		
	public ProductController(FirestoreUtil firestore, ProductDirectoryRepository pdRepo, FirestoreConfig config) {
		this.firestoreUtil = firestore;
		this.pdRepo = pdRepo;
		this.config = config;
	}

	@GetMapping("/api/products/{name}")
    public List<Product> getProducts(@PathVariable("name") String name) {
		List<Product> productList = new ArrayList<>();
		try {
//			firestore = config.getDb();
			List<ProductDirectory> pdList = pdRepo.findByNameContainingIgnoreCase(name);
	        ApiFuture<List<DocumentSnapshot>> productSnapshots = firestoreUtil.getAll(pdList);
	        List<DocumentSnapshot> snapshotList = productSnapshots.get();
			for (DocumentSnapshot ds : snapshotList) {
	        	Map<String, Object> documentMap = ds.getData();
	        	Map<String, Double> prices = firestoreUtil.getPriceMap(documentMap.get("prices"));
	        	Product p = new Product(ds.getId(), documentMap.get("name").toString(), prices, 0.0, "");
	        	productList.add(p);
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return productList;
    }
	
	
}
