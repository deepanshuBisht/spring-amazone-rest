package com.amazone.service;

import java.util.List;

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.ProductDetails;
import com.amazone.model.UserDetails;

public interface UserDetailsService {

	public boolean validateUser(String userName,String password) throws UserNotFoundException;
	public void registerUser(UserDetails userdetails) throws UserAlreadyExistException;
	public int addMoney(int amount,String userid);
	public int checkBalance(String userid);
	public int updateWalletBalance(String userid,int amount);
	public int generateBill(int...ProdIds);
	
	List<ProductDetails> viewAllProducts();
	List<ProductDetails> viewProductByCategory(String category) throws CategoryNotFoundException;
	List<ProductDetails> ViewProductByPrice(int choice) throws ProductNotFoundException;
	List<ProductDetails> ViewProductByBrand(String brand) throws BrandNotFoundException;
	List<ProductDetails> ViewProductByNameOrBrandOrCategory(String choice) throws CategoryNotFoundException;
	
}
