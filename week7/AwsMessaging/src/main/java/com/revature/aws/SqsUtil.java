package com.revature.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

public class SqsUtil {
	private BasicAWSCredentials awsCreds = new BasicAWSCredentials(System.getenv("MESSAGING_ACCESS_KEY"), System.getenv("MESSAGING_SECRET_ACCESS_KEY"));
	private AmazonSQS sqsClient = AmazonSQSClient
										.builder()
										.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
										.withRegion(System.getenv("MESSAGING_REGION"))
										.build();
	private final String QUEUE_URL = System.getenv("MESSAGING_QUEUE_URL");
	
	public ReceiveMessageResult poll() {
		ReceiveMessageRequest request = new ReceiveMessageRequest(QUEUE_URL);
		request.setVisibilityTimeout(15);
		ReceiveMessageResult message = sqsClient.receiveMessage(request);
		System.out.println(message.getMessages());
		deleteMessage(message.getMessages().get(0).getReceiptHandle());
		return message;
	}
	
	public void deleteMessage(String receiptHandle) {
		DeleteMessageRequest deleteRequest = new DeleteMessageRequest(QUEUE_URL, receiptHandle);
		sqsClient.deleteMessage(deleteRequest);
	}
}
