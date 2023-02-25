package com.qa.ngageplatform.regression;

import org.testng.annotations.Test;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;

public class MyWorkSimplifiedTest extends BaseTest {

	@Test(description = "Regression Testcases for My Work Simplified Module", priority = 1)
	public void verifyCloserActionActivityA_CorresponanceGeneration_MWS001_To_MWS006() {
		String customerName = "MWSTest" + new CommonUtil().getCurrentDateTime();
		String value = "Closure Action - Activity A";

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MWSTestCustomerDetails",
				//customerName);
		myworksimPage = mainPage.clickOnMyWorkSimplifiedTab();

		AssertionUtil.verifyEqual(myworksimPage.isSearchHeadDisplay() & myworksimPage.isActivitiesTextDisplay(), true,
				"Verification of MyWorkSimplified Page is open");

		AssertionUtil.verifyEqual(myworksimPage.isSearchAndResetBtnDisplayed(), true,
				"Verification of Search and Reset buttons are displayed successfully");

		workItemsScreen = myworksimPage.selectCloserActionActivityA(value);
		String actualActivityText = myworksimPage.getActivityText();
		String expectedsubActivityText = "Activity A";
		AssertionUtil.verifyContainsText(actualActivityText, expectedsubActivityText,
				"Verification of Activities displayed sub text " + expectedsubActivityText + " successfully");
		AssertionUtil.verifyEqual(myworksimPage.getNextItemDisplayed(), true, "Verification of Get Next is displayed");

		boolean resultForAscOrder = myworksimPage.verifyRecordSortedAscOrder();
		AssertionUtil.verifyEqual(resultForAscOrder, true, "Verification of records sorted in ascending order is successfull.");
		boolean resultForDscOrder = myworksimPage.verifyRecordSortedDscOrder();
		AssertionUtil.verifyEqual(resultForDscOrder, true, "Verification of records sorted in descending order is successfull.");
		
		AssertionUtil.verifyEqual(myworksimPage.verificationOfSetLayout(), true, "Verification of columns are switched after set layout successfully");
		AssertionUtil.verifyEqual(myworksimPage.verificationOfResetLayout(), true, "Verification of columns are switched in default position after reset successfully");

		AssertionUtil.verifyEqual(myworksimPage.selectCorrespondenceGenerationAndVerifyGetNextItemNotDisplayed(), true,
				"Verification of 'Correspondence Generation' page is displayed");

		myworksimPage.verificationOfPageSummary();
	}

}
