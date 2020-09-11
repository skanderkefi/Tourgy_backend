package com.tourgy.services;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;


@Service
public class UtilitiesImpl implements Utilities{

	@Override
	public String generateAleatoireString()
	{
			int length = 10;
		    boolean useLetters = true;
		    boolean useNumbers = false;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		    return generatedString;
		 
	}
}
