package com.vitg.serviceimpl;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.vitg.dto.FileUploadDTO;
import com.vitg.entity.Course;
import com.vitg.entity.SubCourse;
import com.vitg.entity.SubTopic;
import com.vitg.entity.SubTopicConcept;
import com.vitg.entity.Topic;
import com.vitg.repository.SubTopicConceptRepository;
import com.vitg.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	@Autowired
	private SubTopicConceptRepository subTopicConceptRepository;

	@Override
	public void uploadFiles(MultipartFile file,String path,String fileCategory) {
		try {

			String fileName = file.getOriginalFilename();
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());

			s3client.putObject( bucketName + path, fileName, file.getInputStream(), metadata);
		} catch(IOException ioe) {
			logger.error("IOException: " + ioe.getMessage());
		} catch (AmazonServiceException ase) {
			logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			throw ase;
		} catch (AmazonClientException ace) {
			logger.info("Caught an AmazonClientException: ");
			logger.info("Error Message: " + ace.getMessage());
			throw ace;
		}
	}

	//Get All Files
	public List<FileUploadDTO> listFiles(int id, String fileCategory ) {

		SubTopicConcept subTopicConcept = subTopicConceptRepository.findById(id);
		SubTopic subTopic = subTopicConcept.getSubTopic();
		Topic topic = subTopic.getTopic();
		SubCourse subCourse = topic.getSubCourse();
		Course course = subCourse.getCourse();

		StringBuilder path = new StringBuilder();

		path.append("material" +  "/");
		path.append(course.getName()+"/");
		path.append(subCourse.getName()+"/");
		path.append(topic.getName()+"/");
		path.append(subTopic.getName()+"/");
		path.append(subTopicConcept.getId());
		path.append("/");
		path.append(fileCategory);
		System.out.println(bucketName+path);
		String finalPath=path.toString();

		if (!s3client.doesBucketExistV2(bucketName)) {
			System.out.println(bucketName+path);
			return null;
		}

		final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(finalPath);
		ListObjectsV2Result result;
		result = s3client.listObjectsV2(req);
		List<FileUploadDTO> filesList=result.getObjectSummaries().stream()
				.map(file -> new FileUploadDTO(file.getKey(), file.getSize(), file.getETag()))
				.collect(Collectors.toList());


		return filesList;
	}


	//	//Get All Files
	//	public List<FileUploadDTO> getFiles(int id) {
	//
	//		SubTopicConcept subTopicConcept = subTopicConceptRepository.findById(id);
	//		SubTopic subTopic = subTopicConcept.getSubTopic();
	//		Topic topic = subTopic.getTopic();
	//		SubCourse subCourse = topic.getSubCourse();
	//		Course course = subCourse.getCourse();
	//
	//		StringBuilder path = new StringBuilder();
	//
	//		path.append("material" +  "/");
	//		path.append(course.getName()+"/");
	//		path.append(subCourse.getName()+"/");
	//		path.append(topic.getName()+"/");
	//		path.append(subTopic.getName()+"/");
	//		path.append(subTopicConcept.getId()); 
	//		//path.append("/");
	//
	//		String finalPath = path.toString();
	//		String bucketFinal=bucketName.concat(finalPath);
	//		final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(finalPath);
	//		ListObjectsV2Result result;
	//		do {               
	//			result = s3client.listObjectsV2(req);
	//
	//			for (S3ObjectSummary objectSummary : 
	//				result.getObjectSummaries()) {
	//				System.out.println(" - " + objectSummary.getKey() + "  " +
	//						"(size = " + objectSummary.getSize() + 
	//						")");
	//			}  
	//			System.out.println("Next Continuation Token : " + result.getNextContinuationToken());
	//			req.setContinuationToken(result.getNextContinuationToken());
	//		} while(result.isTruncated() == true ); 
	//		return null;
	//	}


	//	//Get All Files
	//	public S3ObjectInputStream getFiles(int id) {
	//
	//		SubTopicConcept subTopicConcept = subTopicConceptRepository.findById(id);
	//		SubTopic subTopic = subTopicConcept.getSubTopic();
	//		Topic topic = subTopic.getTopic();
	//		SubCourse subCourse = topic.getSubCourse();
	//		Course course = subCourse.getCourse();
	//
	//		StringBuilder path = new StringBuilder();
	//
	//		path.append("material" +  "/");
	//		path.append(course.getName()+"/");
	//		path.append(subCourse.getName()+"/");
	//		path.append(topic.getName()+"/");
	//		path.append(subTopic.getName()+"/");
	//		path.append(subTopicConcept.getId()); 
	//		path.append("/");
	//		String finalPath = path.toString();
	//		System.out.println(finalPath);
	//		//final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
	//		try {
	//			S3Object obj = s3client.getObject(bucketName, finalPath + "Angular Commands.xlsx");
	//			System.out.println(obj);
	//			S3ObjectInputStream inputStream = obj.getObjectContent();
	//			System.out.println(inputStream);
	//
	//			return inputStream;
	//		} catch (AmazonServiceException e) {
	//			System.err.println(e.getErrorMessage());
	//			System.exit(1);
	//			throw e;
	//		}
	//	}


	//Download File
	public ByteArrayOutputStream downloadFile(String fileName) {
		try {
			S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, fileName));
			InputStream inputStream = s3object.getObjectContent();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[4096];
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				outputStream.write(buffer, 0, len);
			}

			return outputStream;
		} catch (IOException ioException) {
			logger.error("IOException: " + ioException.getMessage());
		} catch (AmazonServiceException serviceException) {
			logger.info("AmazonServiceException Message: " + serviceException.getMessage());
			throw serviceException;
		} catch (AmazonClientException clientException) {
			logger.info("AmazonClientException Message: " + clientException.getMessage());
			throw clientException;
		}

		return null;
	}

	//Delete File
	public String deleteFile(final String fileName) {
		s3client.deleteObject(bucketName, fileName);
		System.out.println(bucketName);
		System.out.println(fileName);
		return "Deleted File: " + fileName;
	}

}
