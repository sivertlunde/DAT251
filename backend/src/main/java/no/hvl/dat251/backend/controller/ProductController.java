package no.hvl.dat251.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;

@RestController
public class ProductController {
	
	@Autowired
	FirebaseInitializer firebase;
	
	@Autowired
	Firestore firestore;
	
	@Autowired
	ProductDirectoryRepository pdRepo;
		
	public ProductController(FirebaseInitializer firebase, Firestore firestore, ProductDirectoryRepository pdRepo) {
		this.firebase = firebase;
		this.firestore = firestore;
		this.pdRepo = pdRepo;
	}

	@GetMapping("/api/products/{name}")
    public List<Product> getProducts(@PathVariable("name") String name) {
		List<Product> productList = new ArrayList<>();
		try {
	        ApiFuture<List<DocumentSnapshot>> productSnapshots = firestore.getAll(makeDocumentReferences(name));
	        List<DocumentSnapshot> snapshotList = productSnapshots.get();
			for (DocumentSnapshot ds : snapshotList) {
	        	Map<String, Object> documentMap = ds.getData();
	        	Map<String, Double> prices = (Map<String, Double>) documentMap.get("prices");
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

	public DocumentReference[] makeDocumentReferences(String string) {
		List<DocumentReference> drList = new ArrayList<>();
		List<ProductDirectory> pdList = pdRepo.findByNameContaining(string);
		for (ProductDirectory pd : pdList) {
			DocumentReference dr = firestore.collection("products").document(pd.getId());
			drList.add(dr);
		}
		DocumentReference[] drTable = new DocumentReference[drList.size()];
		for (int i = 0; i < drList.size(); i++) {
			drTable[i] = drList.get(i);
		}
		return drTable;
	}
}
