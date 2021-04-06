package no.hvl.dat251.backend;

import no.hvl.dat251.backend.model.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.format.datetime.DateFormatter;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class DomainModelTests {
	private Store butikk;
	private User bruker;
	private ShoppingList handleliste;
	private Product produkt;
	
	@BeforeEach
	public void setup() {
		ArrayList<Map<Product, Integer>> produkter = new ArrayList<>();
		Map<Product, Integer> produktVare = new HashMap<>();
		Map<String, Double> produktButikkOgPris = new HashMap<>();
		produktButikkOgPris.put("Spar", 15.99);
		produktVare.put(produkt, 1);
		produkter.add(produktVare);
		LocalDateTime date = LocalDateTime.now();
		
		butikk = new Store("Spar", "https://spar.no/");
//		bruker = new User("123", "test@gmail.com", "passord123", "testvegen 123");
		produkt = new Product("1", "Agurk", produktButikkOgPris, 1.75, "Gronnsaker");
		handleliste = new ShoppingList("1", date, produkter);
	}
	
	@Test
	public void getHandleliste() {
		
	}
}
