package com.revature.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SnsUtil {
	private BasicAWSCredentials awsCreds = new BasicAWSCredentials(System.getenv("MESSAGING_ACCESS_KEY"), System.getenv("MESSAGING_SECRET_ACCESS_KEY"));
	private AmazonSNS snsClient = AmazonSNSClient
										.builder()
										.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
										.withRegion(System.getenv("MESSAGING_REGION"))
										.build();
	
	public void publish(String message) {
		PublishRequest pubRequest = new PublishRequest(System.getenv("MESSAGING_TOPIC_ARN"), message);
		PublishResult pubResult = snsClient.publish(pubRequest);
		System.out.println("published");
	}
}
