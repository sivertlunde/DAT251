package no.hvl.dat251.backend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import no.hvl.dat251.backend.model.Product;
import no.hvl.dat251.backend.model.ProductDirectory;

public interface ProductDirectoryRepository extends CrudRepository<ProductDirectory, Long> {

	/*
	 *@Modifying
		@Query(
		value= "CREATE TABLE IF NOT EXISTS 'products'(id VARCHAR(500), name VARCHAR(500)); INSERT INTO 'products' VALUES :statement",
		nativeQuery= true) 
	 */
	
	//void addAll(@Param("statement") String statement);
	
}
