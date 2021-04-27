package no.hvl.dat251.backend.firestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import no.hvl.dat251.backend.model.ProductDirectory;

@Component
public class FirestoreUtil {

	@Autowired
	Firestore firestore;
	
	@Autowired
	FirebaseInitializer init;
	
	@Autowired
	FirestoreConfig config;
	
	public List<QueryDocumentSnapshot> getAllProducts() {
		List<QueryDocumentSnapshot> result = new ArrayList<>();
		firestore = config.getDb();
		ApiFuture<QuerySnapshot> future = firestore.collection("products").get();
		try {
			result = future.get().getDocuments();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public DocumentReference[] makeDocumentReferences(List<ProductDirectory> pdList) {
		List<DocumentReference> drList = new ArrayList<>();
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

	public ApiFuture<List<DocumentSnapshot>> getAll(List<ProductDirectory> pdList) {
		DocumentReference[] drTable = makeDocumentReferences(pdList);
		firestore = config.getDb();
		return firestore.getAll(drTable);
	}
	
	public Map<String, Double> getPriceMap(Object map) {
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
