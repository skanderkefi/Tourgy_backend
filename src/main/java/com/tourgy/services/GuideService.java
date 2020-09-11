package com.tourgy.services;

import org.springframework.beans.factory.annotation.Autowired;   

import org.springframework.stereotype.Service;

import com.tourgy.exceptions.ResourceNotFoundException;
import com.tourgy.model.Guide;
import com.tourgy.model.Login;
import com.tourgy.repository.GuideRepo;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class GuideService {

	@Autowired
	GuideRepo guideRepo;
	@Autowired
    protected JavaMailSender javaMailSender;

	
	public boolean exist(String address) {
		Guide guide=guideRepo.findByEmail(address);
		if(guide==null) {
			return false;
		}else {
			return true;
		}
	}
	public List<Guide> findAllGuides() {
		return guideRepo.findAll();
	}
	
	public Guide login(Login login) {
		return guideRepo.findByEmail(login.getEmail());
	}
	
	public void createGuide(Guide guide) {
		guideRepo.save(guide);
	}
	
	public Guide findGuideById(Long id) throws ResourceNotFoundException {
		return guideRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guide not found for this id :: " + id));
	}
	
public Guide editGuide(Long id, Guide editGuide) throws ResourceNotFoundException {
		
		Guide guide = guideRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guide not found for this id :: " + id));

		guide.setLastName(editGuide.getLastName());
		guide.setFirstName(editGuide.getFirstName());
		guide.setMdp(editGuide.getMdp());
		guide.setStatus(editGuide.getStatus());

		final Guide updatedGuide = guideRepo.save(guide);
		return updatedGuide;
	}

public void deleteGuide(Long id) throws ResourceNotFoundException {
	Guide guide = guideRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Guide not found for this id :: " + id));

	guideRepo.delete(guide);
}

public void sendMail(String adress, String message) {

    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(adress);

    msg.setSubject("Tourgy");
    msg.setText(message);

    javaMailSender.send(msg);

}


	
	
}
