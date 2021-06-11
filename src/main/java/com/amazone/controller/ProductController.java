package com.amazone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.IdNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.ProductDetails;
import com.amazone.model.UserDetails;
import com.amazone.service.AdminDetailsService;
import com.amazone.service.ProductDetailsService;
import com.amazone.service.UserDetailsService;

@RestController
@RequestMapping("/amazone-api")
public class ProductController {
	
	@Autowired
	UserDetailsService userServices;
	
	@GetMapping("/user-login/{username}/{password}")
	ResponseEntity<Boolean> validateUser(@PathVariable("username")String username, @PathVariable("password")String password) throws UserNotFoundException {
		boolean checkDetails = userServices.validateUser(username, password);
		return ResponseEntity.ok(checkDetails);
	}
	
	@PostMapping("/user-register")
	ResponseEntity<String> registerUser(@RequestBody UserDetails user) throws UserAlreadyExistException {
		System.out.println(user);
		userServices.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Registered");
	}
	
	@Autowired
	AdminDetailsService adminService;
	
	@GetMapping("/admin-login/{username}/{password}")
	ResponseEntity<Boolean> validateAdmin(@PathVariable("username")String username, @PathVariable("password")String password) throws UserNotFoundException {
		boolean checkDetails = adminService.validateAdmin(username, password);
		return ResponseEntity.ok(checkDetails);
	}
	
	@Autowired
	ProductDetailsService productService;
	
	@GetMapping("/products")
	List<ProductDetails> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/products")
	void addProduct(@RequestBody ProductDetails productDetails) {
		productService.addProduct(productDetails);
	}
	
	@PutMapping("/products")
	void updateProduct(@RequestBody ProductDetails productDetails) throws IdNotFoundException {
		productService.updateProduct(productDetails);
	}

	@DeleteMapping("/products/{proid}")
	void deleteProduct(@PathVariable("proid") int proid) throws IdNotFoundException {
		productService.deleteProduct(proid);
	}
	
	@GetMapping("/products-by-id/{proid}")
	ProductDetails getProductById(@PathVariable("proid") int proid) throws IdNotFoundException {
		return productService.getProductById(proid);
	}

	@GetMapping("/products-by-brand/{brand}")
	List<ProductDetails> getProductByBrand(@PathVariable("brand") String brand) throws BrandNotFoundException {
		return productService.getProductByBrand(brand);
	}

	@GetMapping("/products-by-category/{category}")
	List<ProductDetails> getBookByCategory(@PathVariable("category") String category) throws CategoryNotFoundException {
		return productService.getProductByCategory(category);
	}

	@GetMapping("/products-by-choice/{choice}")
	List<ProductDetails> getByCatOrTitleOrAuth(@PathVariable("choice") String choice) throws ProductNotFoundException {
		return productService.getProductByNameOrBrandOrCategory(choice);
	}
	
	@GetMapping("/products-by-priceless/{price}")
	List<ProductDetails> getProductLessThanPrice(@PathVariable("price") double price) throws ProductNotFoundException {
		return productService.getProductLessThanPrice(price);
	}
	
	@GetMapping("/products-by-pricemore/{price}")
	List<ProductDetails> getProductMoreThanPrice(@PathVariable("price") double price) throws ProductNotFoundException {
		return productService.getProductMoreThanPrice(price);
	}
	
}
