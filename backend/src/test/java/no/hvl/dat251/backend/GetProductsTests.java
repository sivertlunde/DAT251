package no.hvl.dat251.backend;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import no.hvl.dat251.backend.controller.ProductController;
import no.hvl.dat251.backend.firestore.FirebaseInitializer;
import no.hvl.dat251.backend.firestore.FirestoreConfig;
import no.hvl.dat251.backend.firestore.FirestoreUtil;
import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class GetProductsTests {

	 @Mock private ProductDirectoryRepository mockedPDRepo;
	 @Mock private FirestoreUtil mockedFirestoreUtil;
	 @Mock private FirestoreConfig mockedConfig;
	 @Mock private CollectionReference mockedCR;
	 @Mock private DocumentReference dr1;
	 @Mock private DocumentReference dr2;
	 @Mock private DocumentReference dr3;
	 
	 private ProductController pc;
	 private ProductDirectory pd1;
	 private ProductDirectory pd2;
	 private ProductDirectory pd3;
	 private List<ProductDirectory> pdList;
	 @Mock private ApiFuture<List<DocumentSnapshot>> mockedFuture;
	 private List<DocumentSnapshot> snapshots;
	 @Mock private DocumentSnapshot ds1;
	 @Mock private DocumentSnapshot ds2;
	 @Mock private DocumentSnapshot ds3;
	 private Map<String, Object> documentMap1;
	 private Map<String, Object> documentMap2;
	 private Map<String, Object> documentMap3;
	 private Map<String, Double> prices1;
	 private Map<String, Double> prices2;
	 private Map<String, Double> prices3;
	 
	 @BeforeEach
	 public void setup() {
		 pc = new ProductController(mockedFirestoreUtil, mockedPDRepo, mockedConfig);
	 }
	 
	 /*
	  * These tests have been commented out as the methods used have been extracted to another class
	  */
//	 @Test
//	 public void controllerMakesDocumentReferencesFromDB() throws IOException {
//		 pdList = new ArrayList<>();
//		 pd1 = new ProductDirectory("", "");
//		 pd2 = new ProductDirectory("", "");
//		 pd3 = new ProductDirectory("", "");
//		 pdList.add(pd1);
//		 pdList.add(pd2);
//		 pdList.add(pd3);
//		 when(dr1.getId()).thenReturn("1");
//		 when(dr2.getId()).thenReturn("2");
//		 when(dr3.getId()).thenReturn("3");
//		 when(mockedConfig.getDb()).thenReturn(mockedFirestore);
//		 when(mockedPDRepo.findByNameContainingIgnoreCase(any(String.class))).thenReturn(pdList);
//		 when(mockedFirestore.collection(any(String.class))).thenReturn(mockedCR);
//		 when(mockedCR.document(any(String.class))).thenReturn(dr1, dr2, dr3);
//		 DocumentReference[] actualDRTable = pc.makeDocumentReferences("");
//		 assertEquals("1", actualDRTable[0].getId());
//		 assertEquals("2", actualDRTable[1].getId());
//		 assertEquals("3", actualDRTable[2].getId());
//	 }
//	 
//	 @Test
//	 public void controllerMakesListOfProductsBasedOnDocumentSnapshotsReturnedFromFirestore() throws InterruptedException, ExecutionException {
//		 snapshots = new ArrayList<>();
//		 snapshots.add(ds1);
//		 snapshots.add(ds2);
//		 snapshots.add(ds3);
//		 documentMap1 = new HashMap<>();
//		 documentMap2 = new HashMap<>();
//		 documentMap3 = new HashMap<>();
//		 prices1 = new HashMap<>();
//		 prices2 = new HashMap<>();
//		 prices3 = new HashMap<>();
//		 prices1.put("meny", 40.3);
//		 prices1.put("spar", 47.3);
//		 prices2.put("meny", 30.3);
//		 prices2.put("spar", 37.3);
//		 prices3.put("meny", 20.3);
//		 prices3.put("spar", 27.3);
//		 documentMap1.put("prices", prices1);
//		 documentMap1.put("name", new String("product1"));
//		 documentMap2.put("prices", prices2);
//		 documentMap2.put("name", new String("product2"));
//		 documentMap3.put("prices", prices3);
//		 documentMap3.put("name", new String("product3"));
//		 when(mockedConfig.getDb()).thenReturn(mockedFirestore);
//		 when(mockedFirestore.getAll(any(DocumentReference[].class))).thenReturn(mockedFuture);
//		 when(mockedFuture.get()).thenReturn(snapshots);
//		 when(ds1.getData()).thenReturn(documentMap1);
//		 when(ds1.getId()).thenReturn("1");
//		 when(ds2.getData()).thenReturn(documentMap2);
//		 when(ds2.getId()).thenReturn("2");
//		 when(ds3.getData()).thenReturn(documentMap3);
//		 when(ds3.getId()).thenReturn("3");
//		 List<Product> products = pc.getProducts("name");
//		 assertEquals("product3", products.get(2).getName());
//	 }
}
