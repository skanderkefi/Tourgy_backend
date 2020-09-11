package com.tourgy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourgy.exceptions.ResourceNotFoundException;
import com.tourgy.model.Guide;
import com.tourgy.repository.GuideRepo;
import com.tourgy.services.GuideService;

@RestController
@RequestMapping("/Edit")
public class EditController {
	
	@Autowired
	private GuideService guideServices;
	@Autowired
	GuideRepo guideRepo;

	
	@CrossOrigin
	@PutMapping("/accept")
	public void acceptGuide(@Valid @RequestBody Guide guide) throws ResourceNotFoundException {

		Guide acceptedguide = guideRepo.findByEmail(guide.getEmail());
		acceptedguide.setStatus("ACCEPTED");
		guideServices.editGuide(acceptedguide.getId(), acceptedguide);
		String message = "Hi "+acceptedguide.getFirstName()+", you're accepted ! You're welcome";
		guideServices.sendMail(acceptedguide.getEmail(), message);
			
	}
	@CrossOrigin
	@PutMapping("/refuse")
	public  void refuseGuide(@Valid @RequestBody Guide guide) throws ResourceNotFoundException{

		Guide refusedguide = guideRepo.findByEmail(guide.getEmail());
		refusedguide.setStatus("REFUSE");
		guideServices.editGuide(refusedguide.getId(), refusedguide);
		String message = "Hi "+refusedguide.getFirstName()+", you're accepted ! Sorry and good luck";
		guideServices.sendMail(refusedguide.getEmail(), message);
		
			
	}
}
