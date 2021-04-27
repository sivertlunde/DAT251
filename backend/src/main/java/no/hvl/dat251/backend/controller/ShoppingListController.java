package no.hvl.dat251.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import info.debatty.java.stringsimilarity.Levenshtein;
import no.hvl.dat251.backend.firestore.FirestoreUtil;
import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;
import no.hvl.dat251.backend.util.StringMetricUtil;

@RestController
public class ShoppingListController {
	
	@Autowired
	ProductDirectoryRepository pdRepo;
	
	FirestoreUtil firestoreUtil;
	
	@Autowired
	StringMetricUtil smUtil;

	public ShoppingListController(ProductDirectoryRepository DirectoryRepo, StringMetricUtil stringMetricUtil, FirestoreUtil firestore) {
		pdRepo = DirectoryRepo;
		smUtil = stringMetricUtil;
		this.firestoreUtil = firestore;
	}
	
	//@GetMapping("/api/shoppinglist")
	@PostMapping
	@RequestMapping (value = "/api/shoppinglist", method = RequestMethod.POST, consumes = "text/plain")
    public List<Product> getProductsFromRawText(@RequestBody String list) {
		List<Product> productList = new ArrayList<>();
		List<String> inputText = convertRawTextToStrings(list);
		Map<String, List<ProductDirectory>> documentIdMap = getDocumentIds(inputText);
		Map<String, Map<ProductDirectory, Double>> productSimilarityMap = calculateSimilarityScore(documentIdMap);
		ApiFuture<List<DocumentSnapshot>> productSnapshots = getProductsFromFirestore(productSimilarityMap);
		try {
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

	public List<String> convertRawTextToStrings(String rawText) {
		String[] stringArray = rawText.split("\r?\n");
		List<String> strings = new ArrayList<>();
		for (int i = 0; i < stringArray.length; i++) {
			strings.add(stringArray[i].trim());
		}
		return strings;
	}

	public Map<String, List<ProductDirectory>> getDocumentIds(List<String> strings) {
		Map<String, List<ProductDirectory>> pdMap = new HashMap<>();
		for (String item : strings) {
			pdMap.put(item, pdRepo.findByNameContainingIgnoreCase(item));
		}
		return pdMap;
	}

	public Map<String, Map<ProductDirectory, Double>> calculateSimilarityScore(Map<String, List<ProductDirectory>> docRefs) {
		Levenshtein l = smUtil.getLevenshtein();
		Map<String, Map<ProductDirectory, Double>> productSimilarities = new HashMap<>();
		Set<String> keys = docRefs.keySet();
		for (String key : keys) {
			Map<ProductDirectory, Double> similarityScore = new HashMap<>();
			List<ProductDirectory> products = docRefs.get(key);
			for (ProductDirectory product : products) {
				similarityScore.put(product, l.distance(key, product.getName()));
			}
			productSimilarities.put(key, similarityScore);
		}
		return productSimilarities;
	}

	public ApiFuture<List<DocumentSnapshot>> getProductsFromFirestore(Map<String, Map<ProductDirectory, Double>> similarityList) {
		List<ProductDirectory> pdList = new ArrayList<>();
		Set<String> keys = similarityList.keySet();
		for (String key : keys) {
			Map<ProductDirectory, Double> similarityScores = similarityList.get(key);
			ProductDirectory pd = getPdWithLowestSimilarity(similarityScores);
			if (pd != null) {
				pdList.add(pd);
			}
		}
		return firestoreUtil.getAll(pdList);
	}
	
	private ProductDirectory getPdWithLowestSimilarity(Map<ProductDirectory, Double> similarityScores) {
		Set<ProductDirectory> pdSet = similarityScores.keySet();
		if (pdSet.isEmpty()) {
			return null;
		}
		ProductDirectory mostAlike = pdSet.iterator().next();
		for (ProductDirectory pd : pdSet) {
			if (similarityScores.get(pd) < similarityScores.get(mostAlike)) {
				mostAlike = pd;
			}
		}
		return mostAlike;
	}
	
	

}
