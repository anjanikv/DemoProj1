package com.demo.base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.demo.support.Configurator;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.utils.Utils;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
    MethodListener.class })

public class BaseTest  {
	
	public static String projRoot = System.getProperty("user.dir");
	public static Configurator configProps = new Configurator(
			projRoot+File.separator+"src"
	+File.separator+"test"+File.separator
	+"resources"+File.separator+"config.properties");
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public BaseTest() {
		 System.setProperty("atu.reporter.config", projRoot+File.separator+"atu.properties");
	}

	public void setAuthorInfoForReports() {
         ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),
                      "1.0");
	 }

	public void setIndexPageDescription() {
         ATUReports.indexPageDescription = "My Project Description <br/> <b><div class=\"logo\">\r\n" + 
         		"  <a href=\"https://www.anuko.com/lp/tt_1.htm\" target=\"_blank\"><img alt=\"Anuko Time Tracker\" width=\"300\" height=\"43\" ></a>\r\n" + 
         		"</div></b>";
	}
	
//	private void afterInvocation(IInvokedMethod itm, ITestResult itr) {
//		if(itr.) {
//			
//		}
//
//	}

	/**
	 * Initializing browser requirements
	 * reading configuration details from the configuration file
	 * 
	 */
	@BeforeSuite(alwaysRun = true)
	public static void setupSuite() throws Throwable {
		String browser;
		long implicitWait = Long.parseLong(configProps.getProperty("implicitWait"));
		long explicitWait = Long.parseLong(configProps.getProperty("explicitWait"));
		long pageLoadTimeOut = Long.parseLong(configProps.getProperty("pageLoadTimeOut"));
		browser = configProps.getProperty("browser");
			if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				File file = new File(projRoot+File.separator+"Drivers"
						+File.separator+"IEDriverServer_old.exe");
				System.setProperty("webdriver.ie.driver",
						file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				//driver.manage().deleteAllCookies();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						projRoot+File.separator+"Drivers"+
				File.separator+"chromedriver.exe");
				driver = new ChromeDriver();
			} 
		try {
			ATUReports.setWebDriver(driver);
			driver.get(configProps.getProperty("baseUrl"));
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(pageLoadTimeOut, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, explicitWait);
		} catch (Exception e1) {
			System.out.println("exception == "+ e1);
		}
		

	}

	/**
	 * De-Initializing and closing all the connections
	 * 
	 * @throws Throwable
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Throwable {
		driver.quit();
	}

	@BeforeMethod(alwaysRun = true)
	public void reportHeader() throws Throwable {
		driver.manage().deleteAllCookies();
	}
	
}

