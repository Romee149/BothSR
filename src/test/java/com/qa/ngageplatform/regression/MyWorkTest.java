package com.qa.ngageplatform.regression;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.enums.PaginationButton;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;

public class MyWorkTest extends BaseTest {
	CommonUtil commonUtil = new CommonUtil();
	
	@Test(description = "Verify Expand Button of Closue Action in My Work Module", priority = 1)
	public void verifyClosureActionExpandBtn_MW001() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandClosureAction();
		AssertionUtil.verifyEqual(myWorkScreen.getAreaExpanedValueOfClosureAction(), "true","Verification of Closure Action Expand button is Successful after open");
		myWorkScreen.expandClosureAction();
		AssertionUtil.verifyEqual(myWorkScreen.getAreaExpanedValueOfClosureAction(), "false","Verification of Closure Action Expand button is Successful after close");
	}
	
	@Test(description = "Verify Foldering Configuration option not present, Refresh option present of My Work>ReloadOnPostback>Activity1", priority = 2)
	public void verifyClosureActionExpandBtn_MW002() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandReloadOnPostback().rightClickOnActivity1Link();
	}

	@Test(description = "Verify Sorting of Loan Applicant Name by Selecting Sorting criteria Asc by Field "
			+ "from Foldering Configuration screen and verify Loan Application Expand Link Button is not displayed after Restore Default", priority = 3)
	public void verifySortingAscAndRestoreDefaultByFolderingConfigurationScreenSelection_MW003() {

		List<String> expectedSortedLoanApplicantNameList = new ArrayList<>();
		List<String> actualSortedLoanApplicantNameList_Asc = new ArrayList<>();
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandLoanInteractive().rightClickOnLoanApplication();
		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration();
		folderConfigurationScreen.selectValueForAssignedFieldLevel1Dropdown("First Name");
		folderConfigurationScreen.selectValueForSortingFieldLevel1Dropdown("Asc by Field");
		folderConfigurationScreen.clickOnSaveAndCloseButton();
		myWorkScreen = folderConfigurationScreen.getMyWorkScreenPage().clickOnLoanApplicationExpandLinkButton();

		actualSortedLoanApplicantNameList_Asc = myWorkScreen.getLoanApplicantNameList();
		expectedSortedLoanApplicantNameList = myWorkScreen.sortLoanApplicantInAscOrder();
		// ****** Verification of customer list with asc sorting order
		AssertionUtil.verifyEqual(actualSortedLoanApplicantNameList_Asc, expectedSortedLoanApplicantNameList,
				"Verification of sorting of Loan Applicant's Name List in ascending order is successfull");
		myWorkScreen.rightClickOnLoanApplication();
		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration();
		folderConfigurationScreen.clickOnRestoreDefaultButton().clickOnSaveAndCloseButton().getMyWorkScreenPage();
		AssertionUtil.verifyEqual(myWorkScreen.validateLoanApplicationExpandLink(), false,
				"Verification of Loan Application expand button is not displayed"
						+ "after folder configuration set in Restore Default is successful");

	}

	@Test(description = "Verify Foldering Configuration option for Restore Default option", priority = 4)
	public void verifyFolderingConfigurationScreenContentForRestoreDefault_MW004() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandLoanInteractive().rightClickOnLoanApplication();

		boolean isFolderingConfigurationOptionExists = myWorkScreen
				.verifyExistenceOfValueInRightClickOptions("Foldering Configuration");
		AssertionUtil.verifyEqual(isFolderingConfigurationOptionExists, true,
				"Verification of \'Foldering Configuration Option\' on right click on Loan Appliction under Loan Interactive link");

		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration().clickOnRestoreDefaultButton();

		AssertionUtil.verifyEqual(folderConfigurationScreen.getAssignedFieldLevel1DropdownDefaultSelectedValue(),
				"Select a Field",
				"Verification of Default Selected value for \'AssignedField Level1\' dropdown is 'Select a Field'");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getAssignedFieldLevel2DropdownDefaultSelectedValue(),
				"Select a Field",
				"Verification of Default Selected value for \'AssignedField Level2\' dropdown is 'Select a Field'");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getAssignedFieldLevel3DropdownDefaultSelectedValue(),
				"Select a Field",
				"Verification of Default Selected value for \'AssignedField Level3\' dropdown is 'Select a Field'");

		AssertionUtil.verifyEqual(folderConfigurationScreen.getSortingFieldLevel1DropdownDefaultSelectedValue(), "None",
				"Verification of Default Selected value for \'SortingField Level1\' dropdown is 'None'");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getSortingFieldLevel2DropdownDefaultSelectedValue(), "None",
				"Verification of Default Selected value for \'SortingField Level2\' dropdown is 'None'");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getSortingFieldLevel3DropdownDefaultSelectedValue(), "None",
				"Verification of Default Selected value for \'SortingField Level3\' dropdown is 'None'");

		AssertionUtil.verifyEqual(folderConfigurationScreen.getProcessValue(), "Loan Interactive",
				"Verification of folder Configuration Process Value is 'Loan Interactive' is successfull");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getActivityValue(), "Loan Application",
				"Verification of folder Configuration Activity Value is 'Loan Application' is successful");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getSearchClassIDValue(), "100009",
				"Verification of folder Configuration Search Class ID is '100009' is successfull");

	}

	@Test(description = "Verify Correspondence under Correspondence Generation has no Folder Configuration option after creating new Correspondence doc", priority = 5)
	public void verifyFolderingOptionOfCorrespondance_MW005() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerNameAndTemplate("Correspondence", "Correspondence", "MWFirstName",
				"MWLastName", "Template1");
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnCorrespondenceGenerationExpandLink().rightClickOnCorrespondenceLink();
		boolean isFolderingConfigurationOptionExists = myWorkScreen
				.verifyExistenceOfValueInRightClickOptions("Foldering Configuration");
		AssertionUtil.verifyEqual(isFolderingConfigurationOptionExists, false,
				"Verification of \'Foldering Configuration\' should not be display "
						+ "when right click on Correspondence under Correspondence Generation");
	}

	@Test(description = "Verify Sorting of Loan Applicant Name by Selecting Sorting criteria Dsc by Field "
			+ "from Foldering Configuration screen and verify Loan Application Expand Link Button is not displayed after Restore Default", priority = 6)
	public void verifySortingDscAndRestoreDefaultByFolderingConfigScreenSelection_MW006_MW007() {

		List<String> actualSortedLoanApplicantNameList_Dsc = new ArrayList<>();
		List<String> expectedLoanApplicationNameList_Dsc = new ArrayList<>();
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandLoanInteractive().rightClickOnLoanApplication();
		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration();
		folderConfigurationScreen.selectValueForAssignedFieldLevel1Dropdown("First Name");
		folderConfigurationScreen.selectValueForSortingFieldLevel1Dropdown("Desc by Field");
		folderConfigurationScreen.clickOnSaveAndCloseButton();
		myWorkScreen = folderConfigurationScreen.getMyWorkScreenPage().clickOnLoanApplicationExpandLinkButton();

		actualSortedLoanApplicantNameList_Dsc = myWorkScreen.getLoanApplicantNameList();
		myWorkScreen.sortLoanApplicantInAscOrder();
		expectedLoanApplicationNameList_Dsc = myWorkScreen.getLoanApplicantNameListInDsc();
		// ****** Verification of customer list with descending sorting order
		AssertionUtil.verifyEqual(actualSortedLoanApplicantNameList_Dsc, expectedLoanApplicationNameList_Dsc,
				"Verification of sorting of Loan Applicant's Name List in descending order is successfull");
		myWorkScreen.rightClickOnLoanApplication();
		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration().clickOnRestoreDefaultButton();
		folderConfigurationScreen.clickOnSaveAndCloseButton().getMyWorkScreenPage();
		AssertionUtil.verifyEqual(myWorkScreen.validateLoanApplicationExpandLink(), false,
				"Verification of Loan Application expand button is not displayed"
						+ "after folder configuration set in Restore Default is successful");
	}

	@Test(description = "Verify Set Layout of loan application of My Work window by "
			+ "drag and drop column exchnge", priority = 7)
	public void verifySetResetLayoutForLoanApplication_MW008() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandLoanInteractive();
		workItemsScreen = myWorkScreen.clickOnLoanApplication();
		workItemsScreen.clickOnResetLayout();
		AssertionUtil.verifyEqual(workItemsScreen.verificationOfSetLayout("Doc ID", "Process Instance ID"), true,
				"Verification of DocId and Process InstanceID columns are switched after drag and drop successfully");
		myWorkScreen = workItemsScreen.getMyWorkScreenPage();
		myWorkScreen.expandClosureAction().clickOnActivityALink();
		myWorkScreen.expandClosureAction().clickOnLoanApplication().clickOnRefreshIcon();
		
		AssertionUtil.verifyEqual(workItemsScreen.verificationOfAfterSetLayout(), true,
				"Verification of DocId and Process InstanceID columns' position remain same for set layout saved successfully");
	}

	@Test(description = "Verify Reset and Set Layout of loan application of My Work window by "
			+ "drag 'Doc ID' and drop on 'Doc Create Date' column exchnge", priority = 8)
	public void verifySetResetLayoutForLoanApllication_MW009() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandLoanInteractive();
		workItemsScreen = myWorkScreen.clickOnLoanApplication();
		workItemsScreen.clickOnResetLayout();
		AssertionUtil.verifyEqual(workItemsScreen.verificationOfSetLayout("Doc ID", "Doc Create Date"), true,
				"Verification of 'DocId' and 'Doc Create Date columns' are switched after set layout successfully");
		AssertionUtil.verifyEqual(workItemsScreen.verificationOfResetLayout(), true,
				"Verification of 'DocId' and 'Doc Create Date columns' are switch to default position after reset successfully");

	}

	@Test(description = "Verify Newly created Doc Id of Closure Action (Activity A of My work)"
			+ " is greater than old Doc Id", priority = 9)
	public void verifyNewlyCreatedDocIdGreaterThanOldDocId_MW011() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		int oldDocId = workItemsScreen.getLatestDocIdForMWActivityA();
		workItemsScreen.getMainPage().createNewDocumentWithCustomerDetails("Closure Action", "Closure Action",
				"MWTest");
		myWorkScreen = mainPage.reloadMainPage().clickOnMyWorkTab().expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		int newDocId = workItemsScreen.getLatestDocIdForMWActivityA();

		AssertionUtil.verifyGreaterThan(newDocId, oldDocId,
				"Verification of Newly created DocID for closure Action>ActivityA of My Work module is Greater than old DocID successfully");
	}

	@Test(description = "Verify Docments Asc and Dsc order of My Work Closure Action-Activity A by clicking on DocId", priority = 10)
	public void verifyCloserActionActivityASelectedOption_MW013() {
		List<Integer> actualUIList = new ArrayList<Integer>();
		List<Integer> expectedList = new ArrayList<Integer>();
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink().clickOnRefreshIcon();
		actualUIList = workItemsScreen.getSortedDocIDListInAscOrder();
		expectedList = commonUtil.copyList(actualUIList, expectedList, true);
		expectedList = commonUtil.sortListInAscOrder(expectedList);
		AssertionUtil.verifyEqual(actualUIList, expectedList,
				"Verification of documents are in Ascending order of Closure Action>ActivityA of My Work module");
		actualUIList = workItemsScreen.getSortedDocIDListInDescOrder();
		expectedList = commonUtil.copyList(actualUIList, expectedList, true);
		expectedList = commonUtil.sortListInDescOrder(expectedList);
		AssertionUtil.verifyEqual(actualUIList, expectedList,
				"Verification of documents are in Descending order of Closure Action>ActivityA of My Work module");
	}

	@Test(description = "Verify Docments of My Work Closure Action-Activity A are selected "
			+ "by Checkbox icons in Page 1 and not selected in page 2", priority = 11)
	public void verifyCloserActionActivityASelectedOption_MW012_14() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink().clickOnRefreshIcon();
		int actualpageNumberForFirstPage = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(actualpageNumberForFirstPage, 1, "Verification of first page number 1 is successful");

		workItemsScreen.clickOnSelectedAllCheckBox();

		AssertionUtil.verifyEqual(workItemsScreen.getAllCheckedRowsCount(), workItemsScreen.getAllRowsCount(),
				"Verification of all documents in first page are selected successfully after click on all select checkbox");

		workItemsScreen.clickOnTopPageNextBtn();

		AssertionUtil.verifyEqual(workItemsScreen.getAllUncheckedRowsCount(), workItemsScreen.getAllRowsCount(),
				"Verification of any documents in 2nd page are not selected successfully");

		int actualCurrentPageNumber = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(actualCurrentPageNumber, 2, "Verification of second page number 2 is successful");
	}

	@Test(description = "Verify Page No by clicking page next and page next icons for My Work>Closure Action>Activity A", priority = 12)
	public void verifyCloserActionActivityAPageNoAndPagePrev_NextIcon_MW015_16_17() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink().clickOnRefreshIcon().clickOnTopPageNextBtn()
				.clickOnTopPageNextBtn();
		int actualCurrentPageNumber = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(actualCurrentPageNumber, 3,
				"Verification of third page number 3 is successful after click on Page Next button two times.");
		workItemsScreen.clickOnTopPagePreviousBtn();
		actualCurrentPageNumber = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(actualCurrentPageNumber, 2,
				"Verification of third page number 2 is successful after click on Page Previous T button.");
		workItemsScreen.clickOnTopPageNextBtn();
		actualCurrentPageNumber = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(actualCurrentPageNumber, 3, "Verification of third page number 3 is successful "
				+ "after click on Page Next T button while current page no. was 2");
		workItemsScreen.clickOnTopPageStartTBtn();

		actualCurrentPageNumber = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(actualCurrentPageNumber, 1, "Verification of Start page number 1 is successful "
				+ "after click on Page start T button while current page no. was 3");

		boolean isButtonEnabled = workItemsScreen.getButtonEnableState(PaginationButton.FIRST_PAGE);
		AssertionUtil.verifyEqual(isButtonEnabled, false, "Verification of Top Page Start T Button is not clickable "
				+ "while current page number 1 is successful");

		isButtonEnabled = workItemsScreen.getButtonEnableState(PaginationButton.PREVIOUS_PAGE);
		AssertionUtil.verifyEqual(isButtonEnabled, false, "Verification of Top Page Previous T Button is not clickable "
				+ "while current page number 1 is successful");

		int totalPageNumberFromPageInfoBar = workItemsScreen.getTotalPageNumberFromPageBar();
		workItemsScreen.clickOnTopPageEndBtn();
		int totalPageNumberFromPageInputBox = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(totalPageNumberFromPageInfoBar, totalPageNumberFromPageInputBox,
				"Verification of Total Page number in page input box "
						+ "and middle of page bar info is same after click on last page T button is successfull");

		isButtonEnabled = workItemsScreen.getButtonEnableState(PaginationButton.NEXT_PAGE);
		AssertionUtil.verifyEqual(isButtonEnabled, false, "Verification of Top Page Next T Button is not clickable "
				+ "while current page is end page is successful");

		isButtonEnabled = workItemsScreen.getButtonEnableState(PaginationButton.LAST_PAGE);
		AssertionUtil.verifyEqual(isButtonEnabled, false, "Verification of Top Page End T Button is not clickable "
				+ "while current page is the last page is successful");

	}

	@Test(description = "Verify Page No by clicking page next and page next icons for My Work>Closure Action>Activity A", priority = 13)
	public void verifyCloserActionActivityAPageNoAndPagePrev_NextIcon_MW018_To_19() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab().expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		int totalNumberOfClosureAction = workItemsScreen.getTotalActivityNumberFromPageBar();

		for (int i = totalNumberOfClosureAction; i <= 35; i++) {
			workItemsScreen.getMainPage().createNewDocumentWithCustomerDetails("Closure Action", "Closure Action",
					"MWTest");
		}
		int actualCurrentPageNumber = workItemsScreen.getPageNoFromPageInputBox();
		AssertionUtil.verifyEqual(actualCurrentPageNumber, 1, "Verification of first page number 1 is successful");
		workItemsScreen.enterPageNoInPageInputBox("2");
		String actualCurrentPageInfo = workItemsScreen.getPaginationInfo();
		AssertionUtil.verifyContainsText(actualCurrentPageInfo, "Showing 11 - 20",
				"Verification of correct current page number 2 info is showing successfully");
		int actualNumberOfRecord = workItemsScreen.getNumberOfRecordByDocId();
		AssertionUtil.verifyEqual(actualNumberOfRecord, 10,
				"verification of table grid contains 10 records is successful");

		workItemsScreen.enterPageNoInPageInputBox("999");
		actualCurrentPageInfo = workItemsScreen.getPaginationInfo();
		AssertionUtil.verifyContainsText(actualCurrentPageInfo, "No records to view",
				"Verification of correct current page number 999 info is showing successfully");
		actualNumberOfRecord = workItemsScreen.getNumberOfRecordByDocId();
		AssertionUtil.verifyEqual(actualNumberOfRecord, 0,
				"verification of table grid contains no record is successful");
	}
	
	@Test(description = "Create Doc of Event for Required Field and then verify tree folder is correct in My Work Module", priority = 14)
	public void verifyTreeFolderForEventRequiredField_MW020_MW21() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithMasterObjectStringFieldAndDate("Event for Req Fld", "Event for req field",
				"Value 1", "MWRegressionTest");
		myWorkScreen = mainPage.clickOnMyWorkTab();
		
	}
}
