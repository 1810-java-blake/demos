package com.revature.launcher;

import com.revature.aws.SnsUtil;
import com.revature.aws.SqsUtil;

public class Launcher {
	public static void main(String[] args) {
//		SnsUtil snsUtil = new SnsUtil();
//		snsUtil.publish("Testing From Java");
		SqsUtil sqsUtil = new SqsUtil();
		sqsUtil.poll();
	}
}
