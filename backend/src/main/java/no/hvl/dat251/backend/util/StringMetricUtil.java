package no.hvl.dat251.backend.util;

import org.springframework.stereotype.Service;

import info.debatty.java.stringsimilarity.Levenshtein;

@Service
public class StringMetricUtil {
		
	public Levenshtein getLevenshtein() {
		return new Levenshtein();
	}
	
}
