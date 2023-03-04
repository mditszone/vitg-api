package com.vitg.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.vitg.config.CustomPropertyConfig;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceJavaMailSender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

    private CustomPropertyConfig customPropertyConfig;

    public MailConfig(final CustomPropertyConfig customPropertyConfig) {
        this.customPropertyConfig = customPropertyConfig;
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {

        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        customPropertyConfig.awsAccessKey, customPropertyConfig.awsSecretKey)))
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }

    @Bean
    public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
    }
}