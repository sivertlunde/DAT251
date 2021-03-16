package no.hvl.dat251.backend.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.hvl.dat251.backend.helperclasses.DerbyConnectionSetup;
import no.hvl.dat251.backend.model.ProduktDirectory;
import no.hvl.dat251.backend.repository.ProduktDirectoryRepository;

@RestController
public class ProduktDirectoryController {

	@Autowired
	ProduktDirectoryRepository directoryRepository;
	public String url ="jdbc:derby:derbydb";
	
	
	@GetMapping("/productDirectories")
	public ResponseEntity<List<ProduktDirectory>> getAllProducts ()  {
		
		//DerbyConnectionSetup dbsetup = new DerbyConnectionSetup(url);
		//Connection conn = dbsetup.setupConnection(url);
		try {
			List<ProduktDirectory> dirs = new ArrayList<ProduktDirectory>();
			
			directoryRepository.findAll().forEach(dirs::add);
			

			if (dirs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(dirs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
