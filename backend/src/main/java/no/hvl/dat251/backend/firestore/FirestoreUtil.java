package no.hvl.dat251.backend.firestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@Service
public class FirestoreUtil {

	@Autowired
	Firestore firestore;
	
	public List<QueryDocumentSnapshot> getAllProducts() {
		List<QueryDocumentSnapshot> result = new ArrayList<>();
		ApiFuture<QuerySnapshot> future = firestore.collection("products").get();
		try {
			result = future.get().getDocuments();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
