package com.smoke.demo;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class SmokeTest {

	private WebDriver driver;
	//public String baseUrl;
	PageBuilder searchPage;
	ExtentReports report;
	ExtentTest test;
	
	@Parameters("browserType")
	 @BeforeClass
		 public void beforeClass(String browser) {
				if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", "/home/anita/Desktop/geckodriver/geckodriver");
					driver = new FirefoxDriver();
				} 
				else if (browser.equalsIgnoreCase("chrome")) {
					
					System.setProperty("webdriver.chrome.driver", "/home/anita/Desktop/chromedriver99/chromedriver");
					driver = new ChromeDriver();
				}

			//System.setProperty("webdriver.gecko.driver", "/home/anita/Desktop/geckodriver/geckodriver");
			//driver = new FirefoxDriver();
			searchPage = new PageBuilder(driver);
			String baseUrl = "http://ah-test.abhapp.com/login";
			report = new ExtentReports("/home/anita/Desktop/smokeTestAH.html");
			test = report.startTest("Agile Hats Smoke Test");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(baseUrl);
			test.log(LogStatus.INFO, "Web application opened");
			
	  }
	
	
	  @Test(groups={"Precondition"})
	  public void bLogin() {
	
			searchPage.setEmail("testabh1234u@gmail.com");
			test.log(LogStatus.INFO, "Set email");
			searchPage.setPassword("matejsvoboda");
			test.log(LogStatus.INFO, "Set password");
			searchPage.slickLoginButton();
			test.log(LogStatus.INFO, "Clicked on login Button");
	  }
  @Test(dependsOnMethods={"bLogin"},groups={"Smoke Test"})
  public void aSmokeTests() throws InterruptedException {
	
		searchPage.clickProjectsLink();
		test.log(LogStatus.INFO, "Clicked on Project Link");
		searchPage.clickCreateProjectButton();
		test.log(LogStatus.INFO, "Clicked on Create Project Button");
		searchPage.clickFunding();
		test.log(LogStatus.INFO, "Clicked on Project Funding Link");
		searchPage.setProjectName("Project Name Maven Test");
		test.log(LogStatus.INFO, "Set Project Name");
		searchPage.setWhy("This is why text...");
		test.log(LogStatus.INFO, "Set Project Why text");
		searchPage.setWhat("This is what text...");
		test.log(LogStatus.INFO, "Set Project What text");
		searchPage.setHow("This is how...");
		test.log(LogStatus.INFO, "Set Project How text");
		searchPage.clickNextButton1();
		test.log(LogStatus.INFO, "Clicked on Next Button");
		searchPage.verifyAddPeople("");
		searchPage.clickNextButton2();
		test.log(LogStatus.INFO, "Clicked on Next Button");
		searchPage.verifyAddResponsibility("");
		searchPage.clickNexButton3();
		test.log(LogStatus.INFO, "Clicked on Next Button");
		searchPage.clickGoToProjectButton();
		test.log(LogStatus.INFO, "Clicked on Go to Project Button");
		searchPage.projectVerification();
		test.log(LogStatus.PASS, "Verified New Project Created");
  }
 
  @AfterTest
  public void afterTest() {
	    driver.quit();
		report.endTest(test);
		report.flush();
  }

}
