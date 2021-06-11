package com.amazone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.AdminDetails;
import com.amazone.repository.AdminDetailsRepository;

@Service
public class AdminDetailsServiceImpl implements AdminDetailsService {

	@Autowired
	AdminDetailsRepository adminRepository;
	
	@Override
	public boolean validateAdmin(String username, String password) throws UserNotFoundException {
		boolean result = adminRepository.existsByAdminIdAndPassword(username, password);
		if(!result)
			throw new UserNotFoundException("Admin Not Found");
		return true;
	}

	@Override
	public void registerAdmin(AdminDetails admindetails) throws UserAlreadyExistException {
		adminRepository.save(admindetails);
		
	}

}
