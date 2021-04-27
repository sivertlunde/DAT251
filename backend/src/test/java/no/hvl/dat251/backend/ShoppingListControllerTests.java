package no.hvl.dat251.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

import info.debatty.java.stringsimilarity.Levenshtein;
import no.hvl.dat251.backend.controller.ShoppingListController;
import no.hvl.dat251.backend.firestore.FirestoreUtil;
import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;
import no.hvl.dat251.backend.util.StringMetricUtil;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class ShoppingListControllerTests {
	
	@Mock private ProductDirectoryRepository mockedPDRepo;
	@Mock private StringMetricUtil mockedSMUtil;
	@Mock private Levenshtein mockedLevenshtein;
	@Mock private FirestoreUtil mockedFirestore;
	@Mock private DocumentReference dr1;
	@Mock private DocumentReference dr2;
	@Mock private DocumentReference dr3;
	
	private ShoppingListController slc;
	private String rawText;
	private ProductDirectory pd1 = new ProductDirectory("1", "Milk skimmed");
	private ProductDirectory pd2 = new ProductDirectory("2", "20pc toilet paper");
	private ProductDirectory pd3 = new ProductDirectory("3", "organic vanilla beans");
	private ProductDirectory pd4 = new ProductDirectory("4", "500g green grapes");
	private List<ProductDirectory> pdList1 = new ArrayList<>();
	private List<ProductDirectory> pdList2 = new ArrayList<>();
	private List<ProductDirectory> pdList3 = new ArrayList<>();
	private List<ProductDirectory> pdList4 = new ArrayList<>();

	
	@BeforeEach
	public void setup() {
		slc = new ShoppingListController(mockedPDRepo, mockedSMUtil, mockedFirestore);
		rawText = "Milk\n"
				+ "Toilet paper \n"
				+ "vanilla beans\n"
				+ "grapes";
		pdList1.add(pd1);
		pdList2.add(pd2);
		pdList3.add(pd3);
		pdList4.add(pd4);
	}

	@Test
	public void controllerTakesRawTextAndTranslatesToIndividualStringsWithNoLeadingOrTrailingSpaces() {
		List<String> strings = slc.convertRawTextToStrings(rawText);
		assertEquals("Toilet paper", strings.get(1));
		assertEquals("grapes", strings.get(3));
	}
	
	@Test
	public void controllerQueriesProductDirectoryForFirestoreDocumentIds() {
		List<String> strings = slc.convertRawTextToStrings(rawText);
		Map<String, List<ProductDirectory>> docRefs = slc.getDocumentIds(strings);
		verify(mockedPDRepo, times(strings.size())).findByNameContainingIgnoreCase(any(String.class));
	}
	
	@Test
	public void controllerFiltersProductDirectoriesBasedOnStringSimilarity() {
		when(mockedSMUtil.getLevenshtein()).thenReturn(new Levenshtein());
		when(mockedPDRepo.findByNameContainingIgnoreCase(any(String.class))).thenReturn(pdList1, pdList2, pdList3, pdList4);
//		when(mockedLevenshtein.distance("Milk", "Milk skimmed")).thenReturn(0.5);
//		when(mockedLevenshtein.distance("Toilet paper", any(String.class))).thenReturn(0.5);
//		when(mockedLevenshtein.distance("vanilla beans", any(String.class))).thenReturn(0.5);
//		when(mockedLevenshtein.distance("grapes", any(String.class))).thenReturn(0.5);
		List<String> strings = slc.convertRawTextToStrings(rawText);
		Map<String, List<ProductDirectory>> docRefs = slc.getDocumentIds(strings);
		Set<String> keys = docRefs.keySet();
		int pdCount = 0;
		for (String key : keys) {
			pdCount += docRefs.get(key).size();
		}
		Map<String, Map<ProductDirectory, Double>> stringSimilarity = slc.calculateSimilarityScore(docRefs);
		assertNotNull(stringSimilarity);
		System.out.println(stringSimilarity.toString());
	}
	
//	@Test
//	public void controllerGetsProductWithLowestSimilarityScoreFromFirestore() {
//		List<String> strings = slc.convertRawTextToStrings(rawText);
//		Map<String, List<ProductDirectory>> docRefs = slc.getDocumentIds(strings);
//		Map<String, Map<ProductDirectory, Double>> similarityList = slc.calculateSimilarityScore(docRefs);
//		List<Product> shoppingList = slc.getProductsFromFirestore(similarityList);
//	}
}
