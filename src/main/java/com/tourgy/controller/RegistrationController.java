package com.tourgy.controller;
import java.io.FileNotFoundException;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.tourgy.model.Guide;
import com.tourgy.model.Login;
import com.tourgy.services.GuideService;
import com.tourgy.services.UploadBusinessImpl;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private GuideService guideServices;
	@Autowired
	private UploadBusinessImpl upload;
	private static String fileLink;
	

	
	@CrossOrigin
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws CreateFolderErrorException, FileNotFoundException, IOException, DbxException {
		System.out.println(file.getOriginalFilename());
		String newfileLink=upload.uploadFile(file);
		System.out.println(newfileLink);
		RegistrationController registrationController = new RegistrationController();
		registrationController.setFile(newfileLink);
	}
	private void setFile(String newfileLink) {
		this.fileLink=newfileLink;
	}
	
	
	

	@CrossOrigin
	@PostMapping("/guide")
	public  ResponseEntity<Guide> createGuide(@Valid @RequestBody Guide guide) {

		if(!guideServices.exist(guide.getEmail())) {
			String message = "Hi "+guide.getFirstName()+", you'll receive your result in an email";
			guideServices.sendMail(guide.getEmail(),message);
			//guide.setLink(fileLink);
			guide.setStatus("WAITING");
			guideServices.createGuide(guide);
			
		    return new ResponseEntity<Guide>(guide, HttpStatus.CREATED);
		}else {
		    return new ResponseEntity<Guide>(guide, HttpStatus.FOUND);

		}
		
	}
	
	@CrossOrigin
	@GetMapping("/show")
	public List<Guide> getAllGuides() {
		List<Guide> listeEnAttente = new ArrayList<>();
		List<Guide> toutlesguides = guideServices.findAllGuides();
		for (Guide guide : toutlesguides) {
			if (guide.getStatus().equals("WAITING")){
				listeEnAttente.add(guide);
			}
		}
		return listeEnAttente;
	}
	
	
	
}
