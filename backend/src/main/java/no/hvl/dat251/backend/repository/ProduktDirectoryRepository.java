package no.hvl.dat251.backend.repository;

import org.springframework.data.repository.CrudRepository;

import no.hvl.dat251.backend.model.Produkt;
import no.hvl.dat251.backend.model.ProduktDirectory;

public interface ProduktDirectoryRepository extends CrudRepository<ProduktDirectory, Long> {

}
