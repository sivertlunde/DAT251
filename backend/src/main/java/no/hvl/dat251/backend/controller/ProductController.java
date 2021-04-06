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
import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;

@RestController
public class ProductController {
	
	
	@Autowired
	FirestoreConfig config;
	
	Firestore firestore;
	
	@Autowired
	ProductDirectoryRepository pdRepo;
		
	public ProductController(Firestore firestore, ProductDirectoryRepository pdRepo, FirestoreConfig config) {
		this.firestore = firestore;
		this.pdRepo = pdRepo;
		this.config = config;
	}

	@GetMapping("/api/products/{name}")
    public List<Product> getProducts(@PathVariable("name") String name) {
		List<Product> productList = new ArrayList<>();
		try {
			firestore = config.getDb();
	        ApiFuture<List<DocumentSnapshot>> productSnapshots = firestore.getAll(makeDocumentReferences(name));
	        List<DocumentSnapshot> snapshotList = productSnapshots.get();
			for (DocumentSnapshot ds : snapshotList) {
	        	Map<String, Object> documentMap = ds.getData();
	        	Map<String, Double> prices = getPriceMap(documentMap.get("prices"));
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
		List<ProductDirectory> pdList = pdRepo.findByNameContainingIgnoreCase(string);
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
	
	private Map<String, Double> getPriceMap(Object map) {
		Map<String, Object> objectMap = (Map<String, Object>) map;
		Map<String, Double> priceMap = new HashMap<String, Double>();
		Set<String> keys = objectMap.keySet();
		for (String key : keys) {
			Object priceObject = objectMap.get(key);
			double price = 0.0;
			if(priceObject instanceof Long) {
				price = (double)(long)priceObject;
			} else if (priceObject instanceof Double) {
				price = (double)priceObject;
			}
			if (price == 0.0) {
				priceMap.put(key, null);
			} else {
				priceMap.put(key, price);
			}
		}
		return priceMap;
	}
}
