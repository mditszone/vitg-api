package com.vitg.serviceimpl;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.vitg.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	private final Path root = Paths.get("uploads");
	
//	@Override
//	  public void save(MultipartFile file) {
//	    try {
//	      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
//	    } catch (Exception e) {
//	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
//	    }
//	  }
	
	@Override
	public void uploadFile(String fileName, MultipartFile file) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());

			//			StringBuffer sb= new StringBuffer();
			//			sb.append(bucketName);
			//			sb.append("java");
			//			sb.append("corejava");
			//			sb.append("oops");
			//			sb.append("inheritance");
			//			sb.append("trainerppt");
			//			System.out.println(sb);
			//			String s= sb.toString();
			//			s3client.putObject(s, fileName, file.getInputStream(), metadata);

			s3client.putObject(bucketName, fileName, file.getInputStream(), metadata);
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
	
	//Delete File
	public String deleteFile(final String fileName) {
		s3client.deleteObject(bucketName, fileName);
        return "Deleted File: " + fileName;
    }
	
	//Get All Files
	public List<String> listFiles() {
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);
		List<String> fileNames = new ArrayList<>();
		ObjectListing objects = s3client.listObjects(listObjectsRequest);
		while (true) {
			List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries();
			if (objectSummaries.size() < 1) {
				break;
			}
			for (S3ObjectSummary item : objectSummaries) {
				if (!item.getKey().endsWith("/"))
					fileNames.add(item.getKey());
			}
			objects = s3client.listNextBatchOfObjects(objects);
		}
		return fileNames;
	}
	
	//Download File
	public ByteArrayOutputStream downloadFile(String fileName) {
        try {
            S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, fileName));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return outputStream;
        } catch (IOException ioException) {
            logger.error("IOException: " + ioException.getMessage());
        } catch (AmazonServiceException serviceException) {
            logger.info("AmazonServiceException Message:    " + serviceException.getMessage());
            throw serviceException;
        } catch (AmazonClientException clientException) {
            logger.info("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }

        return null;
    }

	
}
