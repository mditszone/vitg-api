package com.vitg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AwsSNSConfig {

	/*
	 * @Value("${aws.access.key}") String access_key;
	 */
    @Value("${cloud.aws.credentials.access-key}")
    String access_key;

    @Value("${cloud.aws.credentials.secret-key}")
    String secret_key;

    @Value("${cloud.aws.region.static}")
    String region;

    
    @Bean
    @Primary
    public AmazonSNSClient amazonSNSClient() {
    	
    	System.out.println(access_key);
     	System.out.println(secret_key);
     	System.out.println(region);
    	
    	BasicAWSCredentials credentials = new BasicAWSCredentials(access_key, secret_key);
    	
    	
    	return (AmazonSNSClient )AmazonSNSClientBuilder.standard()
    			.withRegion(region)
    			.withCredentials(new AWSStaticCredentialsProvider(credentials))
    			.build();
    	
    }
}
