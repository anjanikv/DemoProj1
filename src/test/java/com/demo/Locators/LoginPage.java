package com.demo.Locators;

import org.openqa.selenium.By;

public class LoginPage {
	
	public static By signInLink = By.xpath("//a[@href='login.php']");
	public static By userField = By.id("login");
	public static By passwordField = By.id("password");
	public static By signInButton = By.id("btn_login");

}
