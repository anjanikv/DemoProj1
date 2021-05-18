package com.demo.suite1.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.Locators.HomeScreen;
import com.demo.Locators.LoginPage;
import com.demo.base.EventAdopter;

public class LoginTest extends EventAdopter{
	

	@Test(dataProvider ="testParameterData")
	 public void testLogin(String user, String password)throws Exception
	{
		setAuthorInfoForReports();
		setIndexPageDescription();
		
		click(LoginPage.signInLink);
		typeNoAppend(LoginPage.userField,user);
		typeNoAppend(LoginPage.passwordField,password);
		click(LoginPage.signInButton);
		isElementDisplayed(HomeScreen.userDetails);
		click(HomeScreen.signOut);
		isElementDisplayed(LoginPage.signInLink);
	}
	
	 @DataProvider(name="testParameterData")
	    public Object[][] getDataFromDataprovider(){
	    return new Object[][] 
	    	{
	            { "guest", "test" },
	            { "guest", "guest" }
	        };

	    }
}
