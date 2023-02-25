package com.qa.ngageplatform.smoke;

import com.qa.ngageplatform.base.BaseTest;

import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;

import java.util.*;

import org.testng.annotations.Test;

public class WMITest extends BaseTest {

	@Test(description = "Verify Reference Object InlineNew document content", priority = 1)
	public void verifyReferenceObjectInlineNewDocumentContent_WMI2_WMI_3_WMI_4() {

		// String expectedBMIntValue = new CommonUtil().getCurrentDateTime();
		// expectedBMIntValue = expectedBMIntValue.substring(expectedBMIntValue.length()
		// - 2);

		String expectedBMIntValue = "11";
		String expectedBMStringValue = "test" + expectedBMIntValue;

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		newDocScreen = mainPage.createNewDocumentAndSwitchToDocIDNewPage("Reference Object Feature",
				"Reference Object InlineNew");

		AssertionUtil.verifyEqual(newDocScreen.verifyExistenceOfWMIHarnessTabs("1) InlineNew"), true,
				"Verification of existence of 'InlineNew' tab");

		AssertionUtil.verifyEqual(
				newDocScreen.verifyExistenceOfWMIHarnessTabs("2)InlineNew with drag-drop functionality"), true,
				"Verification of existence of 'InlineNew with drag-drop functionality' tab");

		AssertionUtil.verifyEqual(
				newDocScreen.verifyExistenceOfWMIHarnessTabs("7)InlineNew with multiple doctype list"), true,
				"Verification of existence of 'InlineNew with multiple doctype list' tab");

		AssertionUtil.verifyEqual(
				newDocScreen.verifyExistenceOfWMIHarnessTabs("3)NewDoc open in dialogue with drag-drop functionality"),
				true, "Verification of existence of 'NewDoc open in dialogue with drag-drop functionality' tab");

		AssertionUtil.verifyEqual(
				newDocScreen.verifyExistenceOfWMIHarnessTabs(
						"4)InlineNew with required content and drag-drop functionality"),
				true, "Verification of existence of 'InlineNew with required content and drag-drop functionality' tab");

		AssertionUtil.verifyEqual(
				newDocScreen.verifyExistenceOfWMIHarnessTabs(
						"5)NewDoc open in dialogue with required content and drag-drop functionality"),
				true,
				"Verification of existence of 'NewDoc open in dialogue with required content and drag-drop functionality' tab");

		AssertionUtil.verifyEqual(
				newDocScreen.verifyExistenceOfWMIHarnessTabs(
						"6)InlineNew with required content and hidecontentupload=true"),
				true, "Verification of existence of 'InlineNew with required content and hidecontentupload=true' tab");

		String oldDocId = newDocScreen.getFirstAvailableDocIdValueInInlineNewTab();
		newDocScreen.createDocInInlineNewTab(expectedBMIntValue);
		String actualBMIntValue = newDocScreen.getFirstAvailableBMIntValueInInlineNewTab();
		String newDocId = newDocScreen.getFirstAvailableDocIdValueInInlineNewTab();

		AssertionUtil.verifyGreaterThan(Integer.parseInt(newDocId), Integer.parseInt(oldDocId),
				"Verification of newly created Doc Id is greater than older Doc Id");

		AssertionUtil.verifyEqual(actualBMIntValue, expectedBMIntValue, "Verification of BM Integer value");

		newDocScreen.clickOnInlineNewWithDragDropFunctionalityTab();

		oldDocId = newDocScreen.getFirstAvailableDocIdValueInlineNewWithDragDropFunctionalityTab();
		newDocScreen.createDocInInlineNewWithDragDropFunctionalityTab(expectedBMStringValue, "uploadPDFfile.exe");
		newDocId = newDocScreen.getFirstAvailableDocIdValueInlineNewWithDragDropFunctionalityTab();
		String actualBMStringValue = newDocScreen.getFirstAvailableBMStringValueInlineNewWithDragDropFunctionalityTab();

		AssertionUtil.verifyGreaterThan(Integer.parseInt(newDocId), Integer.parseInt(oldDocId),
				"Verification of newly created Doc Id is greater than older Doc Id");

		AssertionUtil.verifyEqual(actualBMStringValue, expectedBMStringValue, "Verification of BM Integer value");

		// AssertionUtil.verifyEqual(newDocScreen.verifyPDFIconForFirstRow(), true,
		// "Verification of BM Integer value");

	}

	@Test(description = "Verify New Link Default tab components", priority = 1)
	public void VerifyNewLinkDefaultTab_WMI5_WMI6() {

		// String expectedIntegerFieldValue = new CommonUtil().getCurrentDateTime();
		// expectedIntegerFieldValue =
		// expectedIntegerFieldValue.substring(expectedIntegerFieldValue.length() - 2);

		String expectedIntegerFieldValue = "12";
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		newDocScreen = mainPage.createNewDocumentAndSwitchToDocIDNewPage("Reference Object Feature",
				"Reference Object Import");

		AssertionUtil.verifyEqual(newDocScreen.getInformationMessageText(),
				"This WMI imparts Reference Object Import feature", "Verification of Information Message value");

		newDocScreen.clickOnNewLinkDefaultTab();

		newDocScreen.clickOnNewButtonInInlineNewTab();
		newDocScreen.enterIntegerFieldTextField(expectedIntegerFieldValue);
		newDocScreen.clickOnCreateNewItemScreenSaveButton();
		newDocScreen.clickOnCloseWindowButton();
		newDocScreen.clickOnCloseWindowButtonOnNewDocPage();

		mainPage = newDocScreen.switchToMainPageScreen();
		mainPage.closeCreateNewItemPopup();

		homeScreen = mainPage.switchToHomePageScreen();
		homeScreen.sortDocumentListByDocID();
		newDocScreen = homeScreen.clickFirstAvailableDocType();
		newDocScreen.switchToDocIDNewPageScreen();
		String actualIntegerFieldValue = newDocScreen.getIntegerFieldValue();

		AssertionUtil.verifyEqual(actualIntegerFieldValue, expectedIntegerFieldValue,
				"Verification of Integer Field value for newly created document");

	}

	@Test(description = "Verify Category Menu Tab components", priority = 1)
	public void verifyCategoryMenuTab_WMI7() {

		String expectedIntegerFieldValue = new CommonUtil().getCurrentDateTime();
		expectedIntegerFieldValue = expectedIntegerFieldValue.substring(expectedIntegerFieldValue.length() - 2);

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		newDocScreen = mainPage.createNewDocumentAndSwitchToDocIDNewPage("Reference Object Feature",
				"Reference Object Import");

		newDocScreen.clickOnCategoryMenuTab();

		newDocScreen.navigateAndClickToRenderAllFieldsTypeLink();

		AssertionUtil.verifyEqual(newDocScreen.getInformationMessageTextForCategoryMenuTab(),
				"This WMI imparts - Renders all basic type of single value fields",
				"Verification of Information Message value");

		newDocScreen.enterIntegerFieldTextField(expectedIntegerFieldValue);
		newDocScreen.clickOnCreateNewItemScreenSaveButton();
		newDocScreen.clickOnCloseWindowButton();
		newDocScreen.clickOnCloseWindowButtonOnNewDocPage();

		mainPage = newDocScreen.switchToMainPageScreen();
		mainPage.closeCreateNewItemPopup();

		homeScreen = mainPage.switchToHomePageScreen();
		homeScreen.sortDocumentListByDocID();
		newDocScreen = homeScreen.clickFirstAvailableDocType();
		newDocScreen.switchToDocIDNewPageScreen();
		String actualIntegerFieldValue = newDocScreen.getIntegerFieldValue();

		AssertionUtil.verifyEqual(actualIntegerFieldValue, expectedIntegerFieldValue,
				"Verification of Integer Field value for newly created document");

	}

	@Test(description = "Verify Master Object Feature Work Item components", priority = 1)
	public void verifyMasterObjectFeatureWorkItemComponents_WMI7() {

		String expectedIntegerFieldValue = new CommonUtil().getCurrentDateTime();
		expectedIntegerFieldValue = expectedIntegerFieldValue.substring(expectedIntegerFieldValue.length() - 2);

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		newDocScreen = mainPage.createNewDocumentAndSwitchToDocIDNewPage("Master Object Feature",
				"Render All Field Types");

		AssertionUtil.verifyEqual(newDocScreen.getInformationMessageText(),
				"This WMI imparts - Renders all basic type of single value fields",
				"Verification of Information Message value");

		AssertionUtil.verifyEqual(newDocScreen.getHeaderTitleText(), "Business Model View - Render All Field Types",
				"Verification of Header Title value");

		AssertionUtil.verifyEqual(newDocScreen.getIntegerFieldLabel(), "Integer Field",
				"Verification of Integer Field Label value");

		AssertionUtil.verifyEqual(newDocScreen.getStringFieldLabel(),
				"String Field (with onfocussetcursorattheend attribute)", "Verification of String Field Label value");

		AssertionUtil.verifyEqual(newDocScreen.getStringFieldWithLookupLabel(), "String Field With Lookup",
				"Verification of String Field With Lookup Label value");

		AssertionUtil.verifyEqual(newDocScreen.verifyExistenceOfValueInStringFieldWithLookupDropdown("Value 1"), true,
				"Verification of existence of String Field With Lookup Dropdown value");

		AssertionUtil.verifyEqual(newDocScreen.verifyExistenceOfValueInStringFieldWithLookupDropdown("Value 2"), true,
				"Verification of existence of String Field With Lookup Dropdown value");

		AssertionUtil.verifyEqual(newDocScreen.verifyExistenceOfValueInStringFieldWithLookupDropdown("Value 3"), true,
				"Verification of existence of String Field With Lookup Dropdown value");

		AssertionUtil.verifyEqual(newDocScreen.verifyExistenceOfValueInStringFieldWithLookupDropdown("Value 4"), true,
				"Verification of existence of String Field With Lookup Dropdown value");

		AssertionUtil.verifyEqual(newDocScreen.verifyExistenceOfValueInStringFieldWithLookupDropdown("Value 5"), true,
				"Verification of existence of String Field With Lookup Dropdown value");

	}

}
