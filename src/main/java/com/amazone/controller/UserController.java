package com.amazone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.IdNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.CartDetails;
import com.amazone.model.ProductDetails;
import com.amazone.model.UserDetails;
import com.amazone.service.CartDetailsService;
import com.amazone.service.ProductDetailsService;
import com.amazone.service.UserDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/amazone-api/user")
public class UserController {
	
	@Autowired
	UserDetailsService userServices;
	
	@Autowired
	ProductDetailsService productServices;
	
	@Autowired
	CartDetailsService cartServices;
	
	// checking user login
	@GetMapping("/login/{username}/{password}")
	@ApiOperation(value = "Login Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<Boolean> validateUser(@PathVariable("username")String username, @PathVariable("password")String password) throws UserNotFoundException {
		boolean checkDetails = userServices.validateUser(username, password);
		return ResponseEntity.ok(checkDetails);
	}
	
	// register user
	@PostMapping("/register")
	@ApiOperation(value = "Register User Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<String> registerUser(@RequestBody UserDetails user) throws UserAlreadyExistException {
		userServices.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Registered");
	}
	
	// show all products on user side
	@GetMapping("/products")
	@ApiOperation(value = "Show All Products Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<ProductDetails>> findAllProducts() {
		List<ProductDetails> productList = userServices.viewAllProducts();
		return ResponseEntity.ok(productList);
	}
	
	// get product by ID on user side
	@GetMapping("/product-by-id/{productid}")
	@ApiOperation(value = "Get Product By Id Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<ProductDetails> findBookById(@PathVariable("productid")int productid) throws IdNotFoundException {
		ProductDetails product = productServices.getProductById(productid);
		return ResponseEntity.ok(product);
	}
	
	// get product by brand on user side
	@GetMapping("/products-by-brand/{brand}")
	@ApiOperation(value = "Get Products By Brand Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<ProductDetails>> findBookByBrand(@PathVariable("brand")String brand) throws BrandNotFoundException {
		List<ProductDetails> productList =  userServices.ViewProductByBrand(brand);
		return ResponseEntity.ok(productList);
	}
	
	// get product by category on user side
	@GetMapping("/products-by-category/{category}")
	@ApiOperation(value = "Get Products By Category Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<ProductDetails>> findProductByCategory(@PathVariable("category")String category) throws CategoryNotFoundException {
		List<ProductDetails> productList = userServices.viewProductByCategory(category);
		return ResponseEntity.ok(productList);
	}
	
	// get product by search on user side
	@GetMapping("/products-by-choice/{choice}")
	@ApiOperation(value = "Get Products By Choice Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<ProductDetails>> getByCategoryOrTitleOrAuth(@PathVariable("choice")String choice) throws CategoryNotFoundException {
		List<ProductDetails> productList = userServices.ViewProductByNameOrBrandOrCategory(choice);
		return ResponseEntity.ok(productList);
	}
	
	// cart functionality
	
	@GetMapping("/cart")
	@ApiOperation(value = "Show All Products in Cart Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<CartDetails>> findAllCartProducts() {
		List<CartDetails> cartList = cartServices.allCartItems();
		return ResponseEntity.ok(cartList);
	}
	
	@PostMapping("/cart")
	@ApiOperation(value = "Add Product in Cart Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<String> addProductInCart(@RequestBody CartDetails cart) throws IdNotFoundException {
		cartServices.addProductToCart(cart);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product Added Inside Cart");
	}
	
	@DeleteMapping("/cart/{productId}")
	@ApiOperation(value = "Delete Product From Cart Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")int productId) throws IdNotFoundException {
		System.out.println(productId);
		cartServices.deleteProductFromCart(productId);
		return ResponseEntity.accepted().body("Removed From Cart");
	}
	
}
