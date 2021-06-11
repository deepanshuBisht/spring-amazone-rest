package com.amazone.service;

import java.util.List;

import com.amazone.exception.IdNotFoundException;
import com.amazone.model.CartDetails;

public interface CartDetailsService {

	public void addProductToCart(CartDetails product);
	public void deleteProductFromCart(int proId) throws IdNotFoundException;
	
	List<CartDetails> allCartItems();
	
}
