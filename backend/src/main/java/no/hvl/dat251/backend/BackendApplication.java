package no.hvl.dat251.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import no.hvl.dat251.backend.model.ProductDirectory;
import no.hvl.dat251.backend.repository.ProductDirectoryRepository;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
	}

	@Bean
	public CommandLineRunner demo(ProductDirectoryRepository pr) {
		return (args) -> {
			// save a few entities
			
			ProductDirectory item = new ProductDirectory("Sorrel - Fresh", "04lqa16zHxKbUXXTeyG9");
			
			pr.save(item);
			
			System.out.println(pr.findAll());
			
			

		};
	}
}

