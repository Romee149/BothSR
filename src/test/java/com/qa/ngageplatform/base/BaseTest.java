package com.qa.ngageplatform.base;

import java.util.Properties;

import com.qa.ngageplatform.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.qa.ngageplatform.factory.DriverFactory;

@Listeners({com.qa.ngageplatform.listeners.ExtentReportListener.class,com.qa.ngageplatform.listeners.TestAllureListener.class})


/**
 * This Class is used to manage all pages objects
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class BaseTest {

	DriverFactory df;
	ExtentTest extentLogger;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected MainPage mainPage;
	protected MyWorkSimplifiedPage myworksimPage;
	protected HomeScreen homeScreen;
	protected MyWorkScreen myWorkScreen;
	protected WorkItemsScreen workItemsScreen;
	protected DashboardScreen dashboardScreen;
	protected NewDocPage newDocScreen;
	protected FolderingConfigurationScreen folderConfigurationScreen;
	protected RepositoryPage repositoryPage;
	protected MultipageViewer multiPageScreen;
	SoftAssert softAssert;

	@BeforeMethod
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
