package no.hvl.dat251.backend;

import no.hvl.dat251.backend.controller.ProductDirectoryController;
import no.hvl.dat251.backend.firestore.FirestoreConfig;
import no.hvl.dat251.backend.firestore.FirestoreUtil;
import no.hvl.dat251.backend.model.*;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class DataFromFirestoreToDerbyTest {
	private List<ProductDirectory> firebaseproducts = new ArrayList<>();
	private List<QueryDocumentSnapshot> documentList = new ArrayList<>();
	@Mock private QueryDocumentSnapshot doc1;
	@Mock private QueryDocumentSnapshot doc2;
	@Mock private QueryDocumentSnapshot doc3;
	private ProductDirectory dir1;
	private ProductDirectory dir2;
	private ProductDirectory dir3;
	@Mock private FirestoreUtil firestoreutil;
	@Mock private FirestoreConfig config;
	@Mock private ProductDirectoryRepository repo;
	
	private ProductDirectoryController pdc;
	
	
	
	@BeforeEach
	public void setup() {
		dir1 = new ProductDirectory("id1", "eple");
		firebaseproducts.add(dir1);
		dir2 = new ProductDirectory("id2","agurk");
		firebaseproducts.add(dir2);
		dir3 = new ProductDirectory("id3","egg");
		firebaseproducts.add(dir3);
		documentList.add(doc1);
		documentList.add(doc2);
		documentList.add(doc3);
		pdc=  new ProductDirectoryController(firestoreutil, config, repo);
		
	}
	
	
//	@Test
//	public void fromListToString() {
//		when(doc1.getId()).thenReturn("id1");
//		when(doc2.getId()).thenReturn("id2");
//		when(doc3.getId()).thenReturn("id3");
//		when(doc1.get("name")).thenReturn("eple");
//		when(doc2.get("name")).thenReturn("agurk");
//		when(doc3.get("name")).thenReturn("egg");
//		String result = pdc.sqlStmtFromData(pdc.dataToList(documentList));
//		String expected = "( 'id1', 'eple'),( 'id2', 'agurk'),( 'id3', 'egg');";
//		assertEquals(expected, result);
//		
//	}
	
	@Test
	public void turnFirebaseDataIntoListOfProductDirectories() {
		when(firestoreutil.getAllProducts()).thenReturn(documentList);
		when(doc1.getId()).thenReturn("id1");
		when(doc2.getId()).thenReturn("id2");
		when(doc3.getId()).thenReturn("id3");
		when(doc1.get("name")).thenReturn("eple");
		when(doc2.get("name")).thenReturn("agurk");
		when(doc3.get("name")).thenReturn("egg");
		List<ProductDirectory> pd = pdc.dataToList(firestoreutil.getAllProducts());
		
		assertEquals( "id1",pd.get(0).getId());
		assertEquals( "eple",pd.get(0).getName());
		assertEquals("id2",pd.get(1).getId());
		assertEquals("agurk",pd.get(1).getName());
		assertEquals("id3",pd.get(2).getId());
		assertEquals( "egg",pd.get(2).getName());
		
	}
	
	@Test
	public void UpdateDatabaseWithNewProductDirectories() {
		when(doc1.getId()).thenReturn("id1");
		when(doc2.getId()).thenReturn("id2");
		when(doc3.getId()).thenReturn("id3");
		when(doc1.get("name")).thenReturn("eple");
		when(doc2.get("name")).thenReturn("agurk");
		when(doc3.get("name")).thenReturn("egg");
		when(firestoreutil.getAllProducts()).thenReturn(documentList);
		pdc.replaceAll();
		verify(repo, times(documentList.size())).save(any(ProductDirectory.class));
	}
	
	@Test
	public void deleteAllProductsInDatabase() {
		pdc.deleteAllProducts();
		verify(repo, times(1)).deleteAll();
	}
	
	
	
}
