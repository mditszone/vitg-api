package com.vitg.serviceimpl;

import java.util.HashMap;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vitg.service.AwsSesService;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.PublishRequest;

@Service
public class AwsSesServiceImpl implements AwsSesService {

	@Autowired
	private AmazonSNSClient amazonSNSClient;

	public void sendSMSMessage(String message, String phonenumber) {

		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
		smsAttributes.put("AWS.SNS.SMS.SenderID",
				new MessageAttributeValue().withStringValue("MDITS").withDataType("String"));
		smsAttributes.put("AWS.SNS.SMS.SMSType",
				new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
		PublishRequest request = new PublishRequest();

		System.out.println("publish message");
		System.out.println(message);
		System.out.println(phonenumber);


		request.withMessage(message) 
		.withPhoneNumber(phonenumber)
		.withMessageAttributes(smsAttributes); 
		PublishResult result = amazonSNSClient.publish(request);

	}

}
