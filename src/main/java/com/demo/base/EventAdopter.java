package com.demo.base;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class EventAdopter extends BaseTest{

	public boolean click(By locator) {
		boolean result = false;
		try {
		if(isElementClickable(locator)) {
			driver.findElement(locator).click();
			result = true;
		}else {
			result = false;
		}
		}catch(Exception e) {
			result = false;
		}finally {
			if(result) {
				ATUReports.add("Click on "+locator, LogAs.PASSED, null);
			}else {
				ATUReports.add("Click on "+locator, LogAs.FAILED, new CaptureScreen(
                        ScreenshotOf.DESKTOP));
			}
		}
		return result;
	}
	
	public boolean isElementClickable(By locator) {
		boolean result = false;
		try {
		WebElement we = wait.until(ExpectedConditions.elementToBeClickable(locator));
		if(we.isEnabled()) {
			result = true;
		}else {
			result = false;
		}
		}catch(Exception e) {
			result = false;
		}finally {
			if(result) {
				ATUReports.add("is Element Clickable on "+locator, LogAs.PASSED, null);
			}else {
				ATUReports.add("is Element Clickable on "+locator, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				//assertTrue(false,"is Element Clickable failed on "+locator);
			}
		}
		return result;
	}
	
	public boolean isElementDisplayed(By locator) {
		boolean result = false;
		try {
		WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		if(we.isEnabled()) {
			result = true;
		}else {
			result = false;
		}
		}catch(Exception e) {
			result = false;
		}finally {
			if(result) {
				ATUReports.add("is Element Displayed on "+locator, LogAs.PASSED, null);
			}else {
				ATUReports.add("is Element Displayed on "+locator, LogAs.FAILED, new CaptureScreen(
                        ScreenshotOf.BROWSER_PAGE));
				//assertTrue(false,"is Element Displayed failed on "+locator);
			}
		}
		return result;
	}
	
	public boolean typeNoAppend(By locator, String data) {
		boolean result = false;
		try {
		if(isElementClickable(locator)) {
			driver.findElement(locator).sendKeys(data);
			result = true;
		}else {
			result = false;
		}
		}catch(Exception e) {
			result = false;
		}finally {
			if(result) {
				ATUReports.add("type Not Append on "+locator, data, LogAs.PASSED, null);
			}else {
				ATUReports.add("type Not Append on  on "+locator, data , LogAs.FAILED, new CaptureScreen(
                        ScreenshotOf.BROWSER_PAGE));
			}
		}
		return result;
	}
}
