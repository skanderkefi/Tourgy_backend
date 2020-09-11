package com.tourgy.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.tourgy.model.Admin;


public interface AdminRepo extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);

}
