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

import com.amazone.exception.IdNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.AdminDetails;
import com.amazone.model.ProductDetails;
import com.amazone.service.AdminDetailsService;
import com.amazone.service.ProductDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/amazone-api/admin")
public class AdminController {

	@Autowired
	ProductDetailsService productService;
	
	@Autowired
	AdminDetailsService adminService;
	
	// checking admin login
	@GetMapping("/login/{username}/{password}")
	@ApiOperation(value = "Admin Login Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<Boolean> validateAdmin(@PathVariable("username")String username, @PathVariable("password")String password) throws UserNotFoundException {
		boolean checkDetails = adminService.validateAdmin(username, password);
		return ResponseEntity.ok(checkDetails);
	}
	
	// register user
	@PostMapping("/register")
	@ApiOperation(value = "Register User Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<String> registerUser(@RequestBody AdminDetails admin) throws UserAlreadyExistException {
		adminService.registerAdmin(admin);
		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Registered");
	}
	
	// add products on admin side
	@PostMapping("/products")
	@ApiOperation(value = "Add Product Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<String> addProduct(@RequestBody ProductDetails product) throws IdNotFoundException {
		productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product Added");
	}
	
	// update products on admin side
	@PutMapping("/products")
	@ApiOperation(value = "Update Product Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<String> updateProduct(@RequestBody ProductDetails Product) throws IdNotFoundException {
		productService.updateProduct(Product);
		return ResponseEntity.accepted().body("Updated");
	}
	
	// delete products on admin side
	@DeleteMapping("/products/{productId}")
	@ApiOperation(value = "Delete Product Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")int productId) throws IdNotFoundException {
		productService.deleteProduct(productId);
		return ResponseEntity.accepted().body("Deleted");
	}
	
	// show all products on admin side
	@GetMapping("/products")
	@ApiOperation(value = "Show All Products Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<ProductDetails>> findAllProducts() {
		List<ProductDetails> productList = productService.getAllProducts();
		return ResponseEntity.ok(productList);
	}
	
	// show all products by ID on admin side
	@GetMapping("/product-by-id/{productId}")
	@ApiOperation(value = "Get Product By Id Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<ProductDetails> findProductById(@PathVariable("productId")int productId) throws IdNotFoundException {
		ProductDetails product = productService.getProductById(productId);
		return ResponseEntity.ok(product);
	}
}
