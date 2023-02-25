package com.qa.ngageplatform.regression;

import org.testng.annotations.Test;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.pages.ReportPage;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;

public class RepositoryComplaintsTemplateTest extends BaseTest {

	String templateUpdatedName = "Template Name updated";
	String templateUpdatedText = "Updated " + new CommonUtil().getCurrentDateTime("MM-dd-yyyy");
	String templateName = "TemplateTest";
	String textDetails = "Complaint Template Test " + new CommonUtil().getCurrentDateTime("MM-dd-yyyy");

	@Test(description = "Verify Complaint Template of MyWork module result for Complaints Templates Doc", priority = 1)
	public void verifyComplaintTemplateActivities_ED001_To_ED004() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		workItemsScreen = myWorkScreen.clickOnComplaintTemplate();
		int cntBeforeCreate = workItemsScreen.getActivityCount();
		mainPage.createNewDocumentWithtemplateNameAndtemplateBodyText("Complaints Templates", "Complaint Template",
				templateName, textDetails);
		int cntAfterCreate = workItemsScreen.getActivityCount();
		int expectedCount = cntBeforeCreate + 1;
		AssertionUtil.verifyEqual(cntAfterCreate, expectedCount, "Verify document count is increased by one.");

		String latestTemplateName = workItemsScreen.getLatestTemplateName();
		String latestTemplateText = workItemsScreen.getLatestTemplateText();
		AssertionUtil.verifyEqual(latestTemplateName, templateName,
				"Verification of Template Name is saved successfully");
		AssertionUtil.verifyEqual(latestTemplateText, textDetails,
				"Verification of Template Text are saved successfully");
		String Windowname = workItemsScreen.switchToComplaintTeplateDocWindow(1);
		workItemsScreen.getNewDocListPage().updateTemplateName(templateUpdatedName, templateUpdatedText, Windowname);

		String updateTemplateName = workItemsScreen.getLatestTemplateName();
		String updateTemplateText = workItemsScreen.getLatestTemplateText();
		AssertionUtil.verifyEqual(updateTemplateName, templateUpdatedName,
				"Verification of Template Name is updated successfully");
		AssertionUtil.verifyEqual(updateTemplateText, templateUpdatedText,
				"Verification of Template Text are updated successfully");

		mainPage.createNewDocumentWithtemplateNameAndtemplateBodyText("Complaints Templates", "Complaint Template",
				templateName, textDetails);
		int cntGridResult = workItemsScreen.getActivityCount();
		AssertionUtil.verifyGreaterThan(cntGridResult, 1, "Verification of grid result grater than 1 is successful");
		String newTemplateName = workItemsScreen.getLatestTemplateName();
		workItemsScreen.sendSearchTemplateName(newTemplateName);
		workItemsScreen.verifyTempleIdSorted();

		workItemsScreen.verifyDeleteTemplate();

	}

	@Test(description = "Verify Complaint Template of Repository module result for Complaints Templates Doc", priority = 2)
	public void verifyComplaintTemplateActivitiesOnReposritry_ED005_To_ED007() {
		String templateNameForRepo = templateName + "ForRepository";
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithtemplateNameAndtemplateBodyText("Complaints Templates", "Complaint Template",
				templateNameForRepo, textDetails);
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.clickOnComplaintTemplate();
		repositoryPage.clickOnSearch();
		int cntAfterSearch = repositoryPage.getActivityCount();
		AssertionUtil.verifyGreaterThan(cntAfterSearch, 0, templateNameForRepo);
		String latestTemplateName = repositoryPage.getLatestTemplateName();
		String latestTemplateText = repositoryPage.getLatestTemplateText();
		AssertionUtil.verifyEqual(latestTemplateName, templateNameForRepo,
				"Verification of Template Name is saved successfully");
		AssertionUtil.verifyEqual(latestTemplateText, textDetails,
				"Verification of Template Text are saved successfully");

		String Windowname = repositoryPage.switchToComplaintTeplateDocWindow(1);
		repositoryPage.getNewDocListPage().updateTemplateName(templateUpdatedName, templateUpdatedText, Windowname);

		String updateTemplateName = repositoryPage.getLatestTemplateName();
		String updateTemplateText = repositoryPage.getLatestTemplateText();
		AssertionUtil.verifyEqual(updateTemplateName, templateUpdatedName,
				"Verification of Template Name is updated successfully");
		AssertionUtil.verifyEqual(updateTemplateText, templateUpdatedText,
				"Verification of Template Text are updated successfully");

		mainPage.createNewDocumentWithtemplateNameAndtemplateBodyText("Complaints Templates", "Complaint Template",
				templateNameForRepo, textDetails);
		int cntGridResult = repositoryPage.getActivityCount();
		String newTemplateName = repositoryPage.getLatestTemplateName();
		AssertionUtil.verifyGreaterThan(cntGridResult, 1, "Verification of grid result grater than 1 is successful");
		AssertionUtil.verifyEqual(newTemplateName, templateNameForRepo,
				"Verification of new Template Name is saved successfully");
	}

	@Test(description = "Verify Complaint Template of Repository deleted for Complaints Templates Doc", priority = 3)
	public void verifyComplaintTemplateActivitiesOnReposritry_ED005_To_ED008() {
		String templateNameForRepo = templateName + "ForRepository";
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithtemplateNameAndtemplateBodyText("Complaints Templates", "Complaint Template",
				templateNameForRepo, textDetails);
		repositoryPage = mainPage.clickOnRepositoryTab();
		repositoryPage.clickOnComplaintTemplate();
		repositoryPage.clickOnSearch();
		repositoryPage.verifyDeleteTemplate();
	}
}