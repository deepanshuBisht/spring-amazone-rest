package com.amazone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazone.model.AdminDetails;

@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetails, Integer>{
	
	boolean existsByAdminIdAndPassword(String name,String password);
	

}
