package no.hvl.dat251.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import no.hvl.dat251.backend.model.ProductDirectory;

public interface ProductDirectoryRepository extends CrudRepository<ProductDirectory, Long> {

	List<ProductDirectory> findByNameContaining(String name);

}
