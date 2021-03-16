package no.hvl.dat251.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import no.hvl.dat251.backend.model.ProduktDirectory;
import no.hvl.dat251.backend.repository.ProduktDirectoryRepository;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
	}

	@Bean
	public CommandLineRunner demo(ProduktDirectoryRepository pr) {
		return (args) -> {
			// save a few entities
			
			ProduktDirectory item = new ProduktDirectory("banan", "idher");
			
			pr.save(item);
			
			System.out.println(pr.findAll());
			
			

		};
	}
}

