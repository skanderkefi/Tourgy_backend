package com.tourgy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourgy.model.Admin;
import com.tourgy.model.Guide;
import com.tourgy.model.Login;
import com.tourgy.services.AdminService;
import com.tourgy.services.GuideService;

@RestController
@RequestMapping("/Login")
public class LoginController {

	@Autowired
	private GuideService guideServices;
	@Autowired
	private AdminService adminServices;
	
	
	
	@PostMapping("/connectguide")
	public  ResponseEntity<Guide> verifyGuide(@Valid @RequestBody Login login) {
		Guide guide = guideServices.login(login);
		if(guide!=null) {
		if(guide.getMdp().equals(login.getMdp())) {
			if(guide.getStatus().equals("ACCEPTED")) {
			return new ResponseEntity<>(guide, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} }else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);}
	}
	
	@PostMapping("/connectadmin")
	public  ResponseEntity<Admin> verifyAdmin(@Valid @RequestBody Login login) {
		Admin admin = adminServices.login(login);
		if(admin!=null) {
		if(admin.getMdp().equals(login.getMdp())) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} }else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);}
	}
}
