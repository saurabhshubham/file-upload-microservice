package com.file.example.fileuploadmicroservice.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadServices {
	@Value("${file.store.location}")
	 private  String folder_location;

	public void saveUploadedFiles(MultipartFile file) throws IOException {

		File fileDir = new File(folder_location);
		if (!fileDir.exists()) {
			if (fileDir.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		byte[] bytes = file.getBytes();
		Path path = Paths.get(folder_location + file.getOriginalFilename());
		Files.write(path, bytes);
	}
}


