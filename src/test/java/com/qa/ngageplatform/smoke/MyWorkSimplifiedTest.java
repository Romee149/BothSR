package com.qa.ngageplatform.smoke;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;

import org.testng.annotations.Test;

public class MyWorkSimplifiedTest extends BaseTest {

	@Test(description = "Verify activity text, search result, reset, SearchHeadBar process Due Date Start and End", priority = 1)
	public void verifyCloserActionActivityADetails_MWS001_To_MWS006() {

		String customerName = "MWSTest" + new CommonUtil().getCurrentDateTime();
		String value = "Closure Action - Activity A";
		boolean verifysearchheadhidden;
		CommonUtil commonUtil = new CommonUtil();
		String dateTimeFormat = "MM-dd-yyyy hh:mm:ss a";
		String expectedProcessDueDateStartRange = commonUtil.getDateTime(0, dateTimeFormat);
		String expectedProcessDueDateEndRange = commonUtil.getDateTime(7, dateTimeFormat);

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MWSTestCustomerDetails",
				customerName);
		myworksimPage = mainPage.clickOnMyWorkSimplifiedTab();

		AssertionUtil.verifyEqual(myworksimPage.isSearchHeadDisplay(), true,
				"Verification of MyWorkSimplified Page open");
		
		workItemsScreen = myworksimPage.selectCloserActionActivityA(value);
		
		String actualActivityText = myworksimPage.getActivityText();
		String expectedsubActivityText = "Activity A";
		AssertionUtil.verifyContainsText(actualActivityText, expectedsubActivityText,
				"Verification of Activities displayed sub text");
		myworksimPage.doubleClickonDocIdHeader();
		String SearchDocID = workItemsScreen.searchandFetchDocIDFirstAvailableRow();

		String expectedRecordInfo = "Showing 1 - 1 of 1";
		AssertionUtil.verifyEqual(myworksimPage.verifyRecordNumber1(), expectedRecordInfo,
				"Verification of Record Info Text for Search results");
		
		String expectedDocId = workItemsScreen.getFristDocIDAfterSearch();
		AssertionUtil.verifyEqual(SearchDocID, expectedDocId, "Verification of Doc Id for Search results");

		workItemsScreen.enterProcessDueStartAndEndDate();
		boolean searchidfieldvalue = workItemsScreen.verifySearchInputFieldBlank();
		AssertionUtil.verifyEqual(searchidfieldvalue, true,
				"Verification of doc id,process dur date start and search ProcessDue Date end  should be empty after reset.");

		verifysearchheadhidden = myworksimPage.verifySearchSectionHidden();
		AssertionUtil.verifyEqual(verifysearchheadhidden, true, "Verification search header bar should be hidden.");
		workItemsScreen.searchProcessDueDateByStartRange(expectedProcessDueDateStartRange);
		workItemsScreen.clickOnSearchHeaderBar();
		workItemsScreen.clickOnSearchButton();

		AssertionUtil.verifyEqual(myworksimPage.verifyProcessDueDateGreater(), true,
				"Verification of \'Process Due Date\' should be greater than Doc Create Date Column Value");

		verifysearchheadhidden = myworksimPage.verifySearchSectionHidden();
		AssertionUtil.verifyEqual(verifysearchheadhidden, true, "Verification search header bar should be hidden.");//

		workItemsScreen.searchProcessDueDateByEndRange(expectedProcessDueDateEndRange);
		workItemsScreen.clickOnSearchHeaderBar();
		workItemsScreen.clickOnSearchButton();

		AssertionUtil.verifyEqual(myworksimPage.verifyProcessDueDateGreater(), true,
				"Verification of \'Process Due Date\' should be greater than Doc Create Date Column Value");

		verifysearchheadhidden = myworksimPage.verifySearchSectionHidden();
		AssertionUtil.verifyEqual(verifysearchheadhidden, true, "Verification search header bar should be hidden.");

		workItemsScreen.searchProcessDueDate(expectedProcessDueDateStartRange, expectedProcessDueDateEndRange);
		workItemsScreen.clickOnSearchHeaderBar();
		workItemsScreen.clickOnSearchButton();

		AssertionUtil.verifyEqual(myworksimPage.verifyProcessDueDateGreater(), true,
				"Verification of \'Process Due Date\' should be greater than Doc Create Date Column Value");

		verifysearchheadhidden = myworksimPage.verifySearchSectionHidden();
		AssertionUtil.verifyEqual(verifysearchheadhidden, true, "Verification search header bar should be hidden.");
	}

	@Test(description = "Verify Recent Document Grid result, verify latest closure action document info in details ", priority = 2)
	public void verifyWindowInfoAndCount_MWS007() throws InterruptedException {
		String customerName = "MWSTest";
		String customerDetails = "MWSTestCustomerDetails";
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", customerDetails,
				customerName);
		homeScreen = mainPage.getHomeDocumentsListPage();
		homeScreen.clickRecentDocumentLink();

		boolean value = homeScreen.validateGridResult();
		AssertionUtil.verifyEqual(value, true,
				"Verification of at least of 1 record in grid result of Recent Document page");

		int latestDocId = homeScreen.getLatestDocID();
		myworksimPage = mainPage.clickOnMyWorkSimplifiedTab();

			AssertionUtil.verifyEqual(myworksimPage.isSearchHeadDisplay(), true,
				"Verification of MyWorkSimplified Page open");

		workItemsScreen = myworksimPage.selectCloserActionActivityA("Closure Action - Activity A");
		workItemsScreen.clickOnSearchHeaderBar();
		myworksimPage.enterRecentDocIDAndSearch(String.valueOf(latestDocId));

		String expectedRecordInfo = "Showing 1 - 1 of 1";
		AssertionUtil.verifyEqual(myworksimPage.verifyRecordNumber1(), expectedRecordInfo,
				"Verification of Record Info Text for Search results");

		myworksimPage.clickOnAllIconSelectCheckbox();
		myworksimPage.clickOnOpenAllSelectedIcon();
		myworksimPage.clickOnCustomerInformation();

		AssertionUtil.verifyContainsText(myworksimPage.getCustomerNameInfo(), customerName,
				"Verified that customer name is correct");
		AssertionUtil.verifyContainsText(myworksimPage.getCustomerDetailInfo(), customerDetails,
				"Verified that customer detail info is correct");
	}

	@Test(description = "Verify the page count information", priority = 3)
	public void verifyPageCount_MWS008() throws InterruptedException {
		String customerName = "MWSTest" + new CommonUtil().getCurrentDateTime();
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MWSTestCustomer",
				customerName);
		myworksimPage = mainPage.clickOnMyWorkSimplifiedTab();

		AssertionUtil.verifyEqual(myworksimPage.isSearchHeadDisplay(), true,
				"Verification of MyWorkSimplified Page open");
		myworksimPage.selectCloserActionActivityA("Closure Action - Activity A");
		myworksimPage.enterPageNumberAndPressEnterKey();

		String expectedPInfo = "Showing 21 - 30";
		AssertionUtil.verifyContainsText(myworksimPage.getPageInfo(), expectedPInfo,
				"Verification Page no is successful");
	}

	@Test(description = "Verify records are sorted as Ascending and Descending order", priority = 4)
	public void verifyRecordSortedAscAndDsc_MWS009() throws InterruptedException {

		String customerName = "MWSTest" + new CommonUtil().getCurrentDateTime();
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MWSTestCustomer",
				customerName);
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MWSTestCustomer",
				customerName);
		myworksimPage = mainPage.clickOnMyWorkSimplifiedTab();

		AssertionUtil.verifyEqual(myworksimPage.isSearchHeadDisplay(), true,
				"Verification of MyWorkSimplified Page open");
		myworksimPage.selectCloserActionActivityA("Closure Action - Activity A");

		myworksimPage.verifyRecordSortedAscOrder();
		myworksimPage.verifyRecordSortedDscOrder();
	}

	@Test(description = "Verify GetNext item displayed for Closure activity but not displayed for Corresponse Generation", priority = 5)
	public void verifyGetNextIsDisplayesOrNot_MWS010() throws InterruptedException {
		String customerName = "MWSTest";
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); 
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MWSTestCustomer",
				customerName);

		mainPage.createNewDocumentWithCustomerNameAndBodyText("Correspondence", "Correspondence", "MWSFirstName",
				"MWSLastName", "Body");
		myworksimPage = mainPage.clickOnMyWorkSimplifiedTab();
		AssertionUtil.verifyEqual(myworksimPage.isSearchHeadDisplay(), true,
				"Verification of MyWorkSimplified Page open");
		myworksimPage.selectCloserActionActivityA("Closure Action - Activity A");

		AssertionUtil.verifyEqual(myworksimPage.getNextItemDisplayed(), true, "Verification of Get Next is displayed");
		AssertionUtil.verifyEqual(myworksimPage.selectCorrespondenceGenerationAndVerifyGetNextItemNotDisplayed(), true,
				"Verification of Get Next is displayed");
	}
}