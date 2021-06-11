package com.amazone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.IdNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.model.ProductDetails;
import com.amazone.repository.ProductDetailsRepository;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

	@Autowired
	ProductDetailsRepository productRepository;
	
	@Override
	public void addProduct(ProductDetails product) {
		productRepository.save(product);
	}

	@Override
	public void updateProduct(ProductDetails product) throws IdNotFoundException {
		try {
			productRepository.save(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IdNotFoundException("Invalid Id for update../database error");
		}
	}

	@Override
	public void deleteProduct(int proId) throws IdNotFoundException {
		try {
			productRepository.deleteById(proId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IdNotFoundException("Id not found for delete.. /database error");
		}
	}

	@Override
	public ProductDetails getProductById(int proId) throws IdNotFoundException {
		Optional<ProductDetails> product = productRepository.findById(proId);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new IdNotFoundException("Id not found for get /database error");
		}
	}

	@Override
	public List<ProductDetails> getAllProducts() {
		System.out.println("Inside getAllProducts Service");
		return productRepository.findAll();
	}

	@Override
	public List<ProductDetails> getProductByBrand(String brand) throws BrandNotFoundException {
		System.out.println("Inside getProductByBrand Service");
		List<ProductDetails> products = productRepository.findByBrand(brand);
		if (products.isEmpty()) {
			throw new BrandNotFoundException("Brand not found.. /database error");
		}
		return products;
	}

	@Override
	public List<ProductDetails> getProductByCategory(String category) throws CategoryNotFoundException {
		System.out.println("Inside getProductByCategory Service");
		List<ProductDetails> products = productRepository.findByCategory(category);
		if (products.isEmpty()) {
			throw new CategoryNotFoundException("Category not found.. /database error");
		}
		return products;
	}

	@Override
	public List<ProductDetails> getProductByNameOrBrandOrCategory(String choice) throws ProductNotFoundException {
		String lchoice = "%" + choice + "%";
		List<ProductDetails> productList = productRepository.findProductByNameOrBrandOrCategory(lchoice);
		if (productList.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		return productList;
	}

	@Override
	public List<ProductDetails> getProductLessThanPrice(double price) throws ProductNotFoundException {
		List<ProductDetails> productList = productRepository.findProductLessThanPrice(price);
		if (productList.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		return productList;
	}

	@Override
	public List<ProductDetails> getProductMoreThanPrice(double price) throws ProductNotFoundException {
		List<ProductDetails> productList = productRepository.findProductMoreThanPrice(price);
		if (productList.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		return productList;
	}

}
