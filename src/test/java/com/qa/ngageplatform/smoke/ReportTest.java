package com.qa.ngageplatform.smoke;

import org.testng.annotations.Test;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.pages.ReportPage;
import com.qa.ngageplatform.utils.AssertionUtil;

public class ReportTest extends BaseTest {

	@Test(description = "Verify Line gragh is displayed for Security Management User listing", priority = 1)
	public void verifyUserlistingReportGraghExist_Report001() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		ReportPage reportPage = mainPage.clickOnReportTab();
		reportPage.goToUserListingPage();

		AssertionUtil.verifyGreaterThan(reportPage.getUserListinglineGraphCount(), 0,
				"Verification of LineGraph line Count in User Listing Report");
	}

	@Test(description = "Verify Line gragh is displayed for Security Management User listing", priority = 2)
	public void verifyAllWorkItemErrorReportGraghExist_Report005() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		ReportPage reportPage = mainPage.clickOnReportTab();
		reportPage.goToAllWorkItemErrorPage();
		
		AssertionUtil.verifyGreaterThan(reportPage.getWorkItemErrorlineGraphCount(), 0,
				"Verification of LineGraph line Count in All Work Item in Error");
	}

	@Test(description = "Verify Line gragh is displayed for Security Management User listing for Updated result Created By", priority = 3)
	public void verifyUserlistingUpdatedResultReportGraghExist_Report_006() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		ReportPage reportPage = mainPage.clickOnReportTab();
		reportPage.goToUserListingPage();

		AssertionUtil.verifyGreaterThan(reportPage.getUserListingUdatedResultCreatedBy(), 0,
				"Verification of LineGraph line Count in All Work Item in Error");
	}

	@Test(description = "Verify result for Security Management User group listing", priority = 4)
	public void verifyUserGrouplistingReport_007() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		ReportPage reportPage = mainPage.clickOnReportTab();
		reportPage.goToUserGroupListingPage();

		AssertionUtil.verifyContainsText(reportPage.verifyUpdateResultByUserIdForUserGroupListing(), "100,002",
				"Verification of User Id is present in the result is successful");
	}
}
