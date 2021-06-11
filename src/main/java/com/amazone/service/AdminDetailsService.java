package com.amazone.service;

import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.AdminDetails;

public interface AdminDetailsService {

	public boolean validateAdmin(String username, String password) throws UserNotFoundException;
	public void registerAdmin(AdminDetails admindetails) throws UserAlreadyExistException;
	
}
