package com.vitg.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vitg.service.FileUploadService;
import com.vitg.serviceimpl.FileUploadServiceImpl;

import springfox.documentation.service.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/fileUpload")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;

	@Autowired
	FileUploadServiceImpl fileUploadServiceImpl;
	
	@CrossOrigin
	
	//Upload File
	@PostMapping()
	public ResponseEntity<String> uploadMultipartFile(@RequestParam("file") MultipartFile file) 
	{
		System.out.println("inside upload multipart file");
		String fileName =file.getOriginalFilename();
		fileUploadService.uploadFile(fileName, file);
		return new ResponseEntity<>(fileName, HttpStatus.OK);
	}
	
//	@PostMapping("/upload")
//	  public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files) {
//	    String message = "";
//	    try {
//	      List<String> fileNames = new ArrayList<>();
//
//	      Arrays.asList(files).stream().forEach(file -> {
//	    	  fileUploadService.save(file);
//	        fileNames.add(file.getOriginalFilename());
//	      });
//
//	      message = "Uploaded the files successfully: " + fileNames;
//	      return new ResponseEntity<>(message, HttpStatus.CREATED);
//	    } catch (Exception e) {
//	      message = "Fail to upload files!";
//	      return new ResponseEntity<>(message, HttpStatus.CREATED);
//	    }
//	  }

	
	//Get All Files
	@GetMapping("/list/files")
    public ResponseEntity<List<String>> getListOfFiles() {
        return new ResponseEntity<>(fileUploadServiceImpl.listFiles(), HttpStatus.OK);
    }

	//Delete File
	@GetMapping(value = "/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable("fileName") String fileName) {
        return new ResponseEntity<>(fileUploadServiceImpl.deleteFile(fileName), HttpStatus.OK);
    }
	//Download File
	@GetMapping(value = "/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        ByteArrayOutputStream downloadInputStream = fileUploadServiceImpl.downloadFile(filename);

        return ResponseEntity.ok()
                .contentType(contentType(filename))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(downloadInputStream.toByteArray());
    }
    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}