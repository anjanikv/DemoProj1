package com.demo.suite1.tests;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListners.class)			

public class Test1 {

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testSample(ITestContext  test1) {
		System.out.println("from test### "+test1.getSuite());
		assertTrue(false, "test failed");
	}
}
