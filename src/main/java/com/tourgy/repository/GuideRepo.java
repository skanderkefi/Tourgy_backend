package com.tourgy.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.tourgy.model.Guide;



@Repository
public interface GuideRepo extends JpaRepository<Guide, Long>{

	Guide findByEmail(String email);
}