package com.tourgy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourgy.model.Admin;
import com.tourgy.model.Guide;
import com.tourgy.model.Login;
import com.tourgy.repository.AdminRepo;

@Service
public class AdminService {
	
	@Autowired
	AdminRepo adminrepo;
	
	public Admin login(Login login) {
		return adminrepo.findByEmail(login.getEmail());
	}

}
