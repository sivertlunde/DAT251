package no.hvl.dat251.backend.repository;

import org.springframework.data.repository.CrudRepository;

import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;

public interface ProductDirectoryRepository extends CrudRepository<ProductDirectory, Long> {

}
