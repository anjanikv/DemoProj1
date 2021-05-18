package com.demo.suite1.tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = 4;

	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			System.out.println("RetryAnalyzer >>>> "+counter);
			return true;
		}
		return false;
	}
}
