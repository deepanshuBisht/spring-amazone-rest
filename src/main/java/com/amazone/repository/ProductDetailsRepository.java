package com.amazone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amazone.model.ProductDetails;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer>{
	
	List<ProductDetails> findByBrand(String brand);
	List<ProductDetails> findByCategory(String category);
	List<ProductDetails> findByPrice(double price);
	
	@Query("from ProductDetails p where p.name like :choice Or p.brand like :choice Or p.category like :choice")
	List<ProductDetails> findProductByNameOrBrandOrCategory(String choice);
	@Query("from ProductDetails p where p.price < :price")
	List<ProductDetails> findProductLessThanPrice(double price);
	@Query("from ProductDetails p where p.price > :price")
	List<ProductDetails> findProductMoreThanPrice(double price);
	

}
