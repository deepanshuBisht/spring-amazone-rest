package com.amazone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.ProductDetails;
import com.amazone.model.UserDetails;
import com.amazone.repository.ProductDetailsRepository;
import com.amazone.repository.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userRepository;
	
	@Autowired
	ProductDetailsRepository productService;
	
	@Override
	public boolean validateUser(String userName, String password) throws UserNotFoundException{
		boolean result = userRepository.existsByUserIdAndPassword(userName, password);
		if(!result)
			throw new UserNotFoundException("User Not Found");
		return result;
	}

	@Override
	public void registerUser(UserDetails userdetails) throws UserAlreadyExistException {
		userRepository.save(userdetails);
	}

	@Override
	public int addMoney(int amount, String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkBalance(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateWalletBalance(String userid, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateBill(int... ProdIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductDetails> viewAllProducts() {
		return productService.findAll(); 
	}

	@Override
	public List<ProductDetails> viewProductByCategory(String category) throws CategoryNotFoundException{
		List<ProductDetails> productList = productService.findByCategory(category);
		if(productList.isEmpty())
			throw new CategoryNotFoundException("Category Not Found");
		return productList;
	}

	@Override
	public List<ProductDetails> ViewProductByPrice(int choice) throws ProductNotFoundException {
		List<ProductDetails> productList = productService.findByPrice(choice);
		if(productList.isEmpty())
			throw new ProductNotFoundException("Products Not Available");
		return productList;
	}

	@Override
	public List<ProductDetails> ViewProductByBrand(String brand) throws BrandNotFoundException{
		List<ProductDetails> productList =  productService.findByBrand(brand);
		if(productList.isEmpty())
			throw new BrandNotFoundException("Brand Not Found");
		return productList;
	}

	@Override
	public List<ProductDetails> ViewProductByNameOrBrandOrCategory(String choice) throws CategoryNotFoundException{
		List<ProductDetails> productList = productService.findProductByNameOrBrandOrCategory("%"+choice+"%");
		if(productList.isEmpty())
			throw new CategoryNotFoundException("Category Not Found");
		return productList;
	}

}
