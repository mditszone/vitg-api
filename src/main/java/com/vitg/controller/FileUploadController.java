package com.vitg.controller;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.vitg.dto.FileUploadDTO;
import com.vitg.dto.StaffDTO;
import com.vitg.entity.Course;
import com.vitg.entity.SubCourse;
import com.vitg.entity.SubTopic;
import com.vitg.entity.SubTopicConcept;
import com.vitg.entity.Topic;
import com.vitg.repository.CourseRepository;
import com.vitg.repository.SubCourseRepository;
import com.vitg.repository.SubTopicConceptRepository;
import com.vitg.repository.SubTopicRepository;
import com.vitg.repository.TopicRepository;
import com.vitg.service.FileUploadService;
import com.vitg.serviceimpl.FileUploadServiceImpl;

import springfox.documentation.service.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;

	@Autowired
	FileUploadServiceImpl fileUploadServiceImpl;

	@Autowired
	private SubTopicConceptRepository subTopicConceptRepository;


	@PostMapping("/{id}")
	public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files,@PathVariable(value = "id") int id,@RequestParam(value = "fileCategory") String fileCategory) {
		String message = "";
		try {
			List<String> fileNames = new ArrayList<>();

			SubTopicConcept subTopicConcept = subTopicConceptRepository.findById(id);
			SubTopic subTopic = subTopicConcept.getSubTopic();
			Topic topic = subTopic.getTopic();
			SubCourse subCourse = topic.getSubCourse();
			Course course = subCourse.getCourse();

			StringBuilder path = new StringBuilder();

			path.append("/material" +  "/");
			path.append(course.getName()+"/");
			path.append(subCourse.getName()+"/");
			path.append(topic.getName()+"/");
			path.append(subTopic.getName()+"/");
			path.append(subTopicConcept.getId()+"/");
			path.append(fileCategory);

			System.out.println(path);

			Arrays.asList(files).stream().forEach(file -> {
				fileUploadService.uploadFiles(file,path.toString(),fileCategory);
				fileNames.add(file.getOriginalFilename());
			});
			message = "Uploaded files successfully: " + fileNames;
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "Fail to upload files!";
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		}
	}


	//Get All Files
	@GetMapping("/getAllFiles/{id}")
	public ResponseEntity<List<FileUploadDTO>> getListOfFiles(@PathVariable(value = "id") int id,@RequestParam(value="fileCategory") String fileCategory) {
		System.out.println(fileCategory);

		return new ResponseEntity<>(fileUploadServiceImpl.listFiles(id,fileCategory), HttpStatus.OK);
	}

	//		//Get All Files
	//		@GetMapping("/getAllFiles/{id}")
	//		public ResponseEntity<List<String>> getListOfFiles(@PathVariable(value = "id") int id) {
	//			return new ResponseEntity<>(fileUploadServiceImpl.getFiles(id), HttpStatus.OK);
	//		}

	//	//Get All Files
	//	@GetMapping("/getAllFiles/{id}")
	//	public ResponseEntity<S3ObjectInputStream> getListOfFiles(@PathVariable(value = "id") int id) {
	//		return new ResponseEntity<>(fileUploadServiceImpl.getFiles(id), HttpStatus.OK);
	//	}


	//Download File
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) {
		
		ByteArrayOutputStream downloadInputStream = fileUploadServiceImpl.downloadFile(fileName);
		return ResponseEntity.ok()
				.contentType(contentType(fileName))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(downloadInputStream.toByteArray());
	}
	private MediaType contentType(String fileName) {
		String[] fileArrSplit = fileName.split("\\.");
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

	//Delete File
	@GetMapping(value = "/delete")
	public ResponseEntity<String> deleteFile(@RequestParam("fileName") String fileName) {
		System.out.println("TEST");
		return new ResponseEntity<>(fileUploadServiceImpl.deleteFile(fileName), HttpStatus.OK);
	}
}