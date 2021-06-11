package com.amazone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.IdNotFoundException;
import com.amazone.model.CartDetails;
import com.amazone.repository.CartDetailsRepository;

@Service
public class CartDetailsServiceImpl implements CartDetailsService {

	@Autowired
	CartDetailsRepository cartRepository;
	
	@Override
	public void addProductToCart(CartDetails product) {
		cartRepository.save(product);

	}

	@Override
	public void deleteProductFromCart(int proId) throws IdNotFoundException {
		try {
			cartRepository.deleteById(proId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IdNotFoundException("Id not found for delete.. /database error");
		}

	}

	@Override
	public List<CartDetails> allCartItems() {
		System.out.println("Inside getAllCartProducts Service");
		return cartRepository.findAll();
	}

}
