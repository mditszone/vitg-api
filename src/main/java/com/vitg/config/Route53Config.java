package com.vitg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.PropertiesFileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.route53.AmazonRoute53;
import com.amazonaws.services.route53.AmazonRoute53Client;
import com.amazonaws.services.route53.AmazonRoute53ClientBuilder;
import com.amazonaws.services.route53.model.ChangeResourceRecordSetsRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class Route53Config {
	@Value("${cloud.aws.credentials.access-key}")
	private String awsId;
 
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsKey;
	
	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public AmazonRoute53 Route53() {
		
	BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
	
		AmazonRoute53 route53 = AmazonRoute53ClientBuilder.standard().withRegion(Regions.fromName(region)).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

        
		
		return route53;
	}
}
