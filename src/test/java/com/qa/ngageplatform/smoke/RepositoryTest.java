package com.qa.ngageplatform.smoke;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;

public class RepositoryTest extends BaseTest {

	CommonUtil commonUtil = new CommonUtil();
	String dateFormat = "MM-dd-yyyy";
	String dateTimeFormat = "MM-dd-yyyy hh:mm:ss a";
	String dateValue = commonUtil.getDateTime(0, dateTimeFormat);
	String dateTimeValue = commonUtil.getDateTime(20, dateTimeFormat);
	String Order = null;

	@Test(description = "Verify Repository search result for Business Model Doc", priority = 1)
	public void verifySearchResultForBusinessModel_Repo001_Repo002() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
//Repo001		
		mainPage.createNewDocumentWithMasterObject("Master Object Feature", "Render All Field Types", dateValue,
				dateTimeValue);
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.selectRepoSearch("Business Model", "Business Model");
		repositoryPage.clickRepoSearchBtn();
		boolean value = repositoryPage.validateSearchResultRecord();
		AssertionUtil.verifyEqual(value, true,
				"Verification of at least of 1 record is present in grid search result of Repository page");
//Repo002
		boolean searchfieldvalue = repositoryPage.verifySearchInputFieldBlank();
		AssertionUtil.verifyEqual(searchfieldvalue, true,
				"Verification of doc id, start Currency value and End Currency Value  should be empty after click on Clear button.");
	}

	@Test(description = "Verify Repository search result for Date Time Doc", priority = 2)
	public void verifySearchResultForDateTimeDoc_Repo003_To_Repo008() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
//Repo003 		
		mainPage.createNewDateTimeDoc("Date Date Time DC", "Date DateTime DT", commonUtil.getDateTime(0, "MM-dd-yyyy"),
				commonUtil.getDateTime(10, "MM-dd-yyyy"), dateTimeValue);
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.selectRepoSearch("Date n Date time EDM", "Date n Date time search class");

		boolean validateRangeResult = repositoryPage.validateDateRangeForStartAndEndDate();
		AssertionUtil.verifyEqual(validateRangeResult, true,
				"Verification of search result range are with start and end date");

//Repo004
		boolean validateRageForStart = repositoryPage.validateRangeForStartDate();
		AssertionUtil.verifyEqual(validateRageForStart, true,
				"Verification of search result range date are greater than start range date");
//Repo005
		boolean validateRangeForEnd = repositoryPage.validateRangeEndDate();
		AssertionUtil.verifyEqual(validateRangeForEnd, true,
				"Verification of search result range date are greater than start range date");

//Repo006		
		String Description = "Automation" + commonUtil.getDateTime(0, dateFormat);
		String SearchDescription = repositoryPage.validateSearchDescription(Description);
		AssertionUtil.verifyEqual(SearchDescription, Description,
				"Verification of Search Description after save the search result as a Search Description");
//Repo007		
		String recordCount = repositoryPage.getRecordCountInSavedSearch();
		repositoryPage.clickOnRecallButton();
		String afterRecallRecordCount = repositoryPage.getRecordCountInSavedSearch();
		AssertionUtil.verifyEqual(recordCount, afterRecallRecordCount,
				"Validate record count should be same after recall button.");
//Repo008
		repositoryPage.clickOnDelete();
		String searchDescriptionAfterDelete = repositoryPage.validateDeleteValue();
		AssertionUtil.verifyNotEqual(Description, searchDescriptionAfterDelete,
				"Verify after delete click the saved search description is not present");
		String afterdeletecount = repositoryPage.getRecordCountInSavedSearch();
		AssertionUtil.verifyNotEqual(afterdeletecount, afterRecallRecordCount,
				"Verify the page record count change after delete the latest saved search description");
	}

//Repo009 and Repo010
	@Test(description = "Verify Repository search result for Colsure Doc", priority = 3)
	public void verifySearchResultForClosureDoc_Repo009_To_Repo010() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "RepoTestDetails",
				"RepoTest");
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.selectRepoSearch("Closure", "Closure");
		repositoryPage.clickRepoSearchBtn();
		AssertionUtil.verifyEqual(repositoryPage.verifySearchResultDisplayed(), true,
				"Verification of search result is displayed");
		repositoryPage.enterPageNumberAndPressEnterKey();
		String expectedPInfo = "Showing 21 - 30";
		AssertionUtil.verifyContainsText(repositoryPage.getPageInfo(), expectedPInfo,
				"Verification of page count info contain text 'Showing 21 - 30' is successful");
		Order = "Dsc";
		repositoryPage.verifyRecordSortedDscOrder(Order);
		Order = "Asc";
		repositoryPage.verifyRecordSortedDscOrder(Order);
	}

//Repo011
	@Test(description = "Verify Repository search result for 'Business Model", priority = 4)
	public void verifySearchResultForBusinessModelDoc_Repo011() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.selectRepoSearch("Business Model", "Business Model");
		repositoryPage.clickRepoSearchBtn();
		String expectedPInfo = "Showing 1 - 12";
		AssertionUtil.verifyContainsText(repositoryPage.getPageInfo(), expectedPInfo,
				"Verification of Number of records per page is 12 is successful");
		String resultCount = repositoryPage.getPageInfo().substring(repositoryPage.getPageInfo().length() - 3);
		int cnt = Integer.parseInt(resultCount.trim());
		if (cnt <= 500)
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of total number of returned records is less than or equal to 500 is successful");
		else
			ExtentReportListener.test.get().log(Status.FAIL,
					"Verification of total number of returned records is less than or equal to 500 is successful");
	}

//Repo012
	@Test(description = "Verify Repository search result for 'WMI Menu", priority = 5)
	public void verifySearchResultForWMIMenu_Repo012() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		for (int i = 0; i <= 2; i++) {
			mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyRepositoryTest",
					"A3_103.pdf");
		}
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.selectRepoSearch("WMI Menu", "WMI Menu");
		repositoryPage.clickRepoSearchBtn();
		String expectedPInfo = "Showing 1 - 15";
		AssertionUtil.verifyContainsText(repositoryPage.getPageInfo(), expectedPInfo,
				"Verification of Number of records per page is 15 is successful");
		String resultCount = repositoryPage.getPageInfo().substring(repositoryPage.getPageInfo().length() - 3);
		int cnt = Integer.parseInt(resultCount.trim());
		if (cnt <= 250)
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of total number of returned records is less than or equal to 250 is successful");
		else
			ExtentReportListener.test.get().log(Status.FAIL,
					"Verification of total number of returned records is less than or equal to 250 is successful");
	}

	@Test(description = "Verify Set and Reset layout by column drag and drop", priority = 6)
	public void verifyCloser_Repo013() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.validateLayout();
		repositoryPage.verificationOfSetAndResetLayout();
	}

	@Test(description = "Verify multi value for int and String field types after creating new doc ", priority = 7)
	public void verifyMultivalue_Repo014() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithMasterObject("Master Object Feature", "Render All Multivalue Field Types");
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.selectRepoSearch("Business Model", "Business Model");
		//Assertion will be added after fixing the configuration by Amol	
	}
}
