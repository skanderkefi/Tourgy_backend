package com.tourgy.services;

import java.io.FileNotFoundException; 
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.CreateFolderErrorException;

public interface UploadBusiness {
	public String uploadFile(MultipartFile file) throws CreateFolderErrorException, FileNotFoundException, IOException, DbxException; 


}
