package com.file.example.fileuploadmicroservice.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.example.fileuploadmicroservice.services.FileUploadServices;

@RestController
public class FileController {
	
	@Autowired
	private FileUploadServices fileUploadservices;
	
	
	
	private  final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	
	 @RequestMapping(value="/file/upload", method= RequestMethod.POST)
	 public ResponseEntity<?> uploadFile(
	            @RequestParam("file") MultipartFile uploadfile) {
		 System.out.println("entering into file upload");
		 LOG.debug(" file upload!");
		 if (uploadfile.isEmpty()) {
	            return new ResponseEntity("please select a file!", HttpStatus.OK);
	        }
		 
		 
		 try {
			 fileUploadservices.saveUploadedFiles(uploadfile);
		} catch (IOException e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		 
		 return new ResponseEntity("Successfully uploaded - " +
	                uploadfile.getOriginalFilename(), HttpStatus.OK);
		 
		 
	 }
	 
	 
	 
}
