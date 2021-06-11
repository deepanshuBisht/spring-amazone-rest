package com.amazone.service;

import java.util.List;

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.IdNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.model.ProductDetails;

public interface ProductDetailsService {

	public void addProduct(ProductDetails product);
	public void updateProduct(ProductDetails product) throws IdNotFoundException;
	public void deleteProduct(int proId) throws IdNotFoundException;
	ProductDetails getProductById(int proId) throws IdNotFoundException;
	
	List<ProductDetails> getAllProducts();
	List<ProductDetails> getProductByBrand(String brand) throws BrandNotFoundException;
	List<ProductDetails> getProductByCategory(String category) throws CategoryNotFoundException;
	List<ProductDetails> getProductByNameOrBrandOrCategory(String choice) throws ProductNotFoundException;
	
	List<ProductDetails> getProductLessThanPrice(double price) throws ProductNotFoundException;
	List<ProductDetails> getProductMoreThanPrice(double price) throws ProductNotFoundException;
	
}
