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
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

import no.hvl.dat251.backend.controller.ProductController;
import no.hvl.dat251.backend.firestore.FirebaseInitializer;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class GetProductsTests {

	 @Mock private ProductDirectoryRepository mockedPDRepo;
	 @Mock private Firestore mockedFirestore;
	 @Mock private CollectionReference mockedCR;
	 @Mock private DocumentReference dr1;
	 @Mock private DocumentReference dr2;
	 @Mock private DocumentReference dr3;
	 
	 private ProductController pc;
	 private ProductDirectory pd1;
	 private ProductDirectory pd2;
	 private ProductDirectory pd3;
	 private List<ProductDirectory> pdList;
	 
	 @BeforeEach
	 public void setup() {
		 pc = new ProductController(mockedFirestore, mockedPDRepo);
		 pdList = new ArrayList<>();
		 pd1 = new ProductDirectory("", "");
		 pd2 = new ProductDirectory("", "");
		 pd3 = new ProductDirectory("", "");
		 pdList.add(pd1);
		 pdList.add(pd2);
		 pdList.add(pd3);
	 }
	 
	 @Test
	 public void controllerMakesDocumentReferencesFromDB() throws IOException {
		 when(dr1.getId()).thenReturn("1");
		 when(dr2.getId()).thenReturn("2");
		 when(dr3.getId()).thenReturn("3");
		 when(mockedPDRepo.findByNameContaining(any(String.class))).thenReturn(pdList);
		 when(mockedFirestore.collection(any(String.class))).thenReturn(mockedCR);
		 when(mockedCR.document(any(String.class))).thenReturn(dr1, dr2, dr3);
		 DocumentReference[] actualDRTable = pc.makeDocumentReferences("");
		 assertEquals("1", actualDRTable[0].getId());
		 assertEquals("2", actualDRTable[1].getId());
		 assertEquals("3", actualDRTable[2].getId());
	 }
	 
//	 @Test
//	 public void unnamedTest() {
//		 
//	 }
}
