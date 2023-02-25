package com.qa.ngageplatform.smoke;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.Constants;

import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

	// HOME_001,HOME_002,HOME_003,
	@Test(description = "Verify Newly Created DocID, Document Title and Last Action value", priority = 1)
	public void verifyRecentDocumentList_HOME_001_HOME_002_HOME_003_HOME007_HOME009() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		homeScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID();
		int oldDocId = homeScreen.sortDocumentListByDocID().getLatestDocID();

		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MyTest");

		homeScreen = mainPage.reloadMainPage().getHomeDocumentsListPage().sortDocumentListByDocID();
		int newDocId = homeScreen.getLatestDocID();

		// ****** Verification of Newly created DocID should be greater than Existed
		// DocID
		AssertionUtil.verifyGreaterThan(newDocId, oldDocId,
				"Verification of Newly created DocID should be Greater than old DocID");

		// ****** Verification of Document Title in Recent Document for Newly created
		// DocID
		String latestDocType = homeScreen.getFirstAvailableDocType();
		AssertionUtil.verifyEqual(latestDocType, "Closure Action - Closure Action", "Verification of Document Title");

		// ****** Verification of Last Action in Recent Document for Newly created DocID
		// String latestAction = homeScreen.getFirstAvailableDocLastAction();
		// AssertionUtil.verifyEqual(latestAction, "Created", "Verification of Last
		// Action column value");

	}

	// HOME_004,HOME_005,HOME_006,
	@Test(description = "Verify Add To Favorites, Remove To Favorites functionality with provided StringField value for Multipage viewer Doc", priority = 1)
	public void VerifyFavouritesDocList_HOME_004_HOME_005_HOME_006() {

		String expectedStringFieldValue = "MyTest";

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("Multipage Viewer", "Multipage Viewer", expectedStringFieldValue);
		homeScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID();
		newDocScreen = homeScreen.clickFirstAvailableDocType();
		String actualStringFieldValue = newDocScreen.getStringFieldValue();

		// ****** Verification of Provided String Field for Newly created DocID
		AssertionUtil.verifyEqual(actualStringFieldValue, expectedStringFieldValue,
				"Verification of Updated String Field Value");

		// ****** Verification of Add To Favourites Message
		newDocScreen.navigateAndClickOnAddToFavouritesLink();
		AssertionUtil.verifyEqual(newDocScreen.getInfoMessage(), Constants.ADD_TO_FAVOURITES_INFO_MESSAGE,
				"Verification of \"Add To Favorites\" Info Message");

		// ****** Verification of Newly Created Doc ID in Favourites list
		homeScreen = newDocScreen.switchToMainPageScreen().reloadMainPage().switchToHomePageScreen();
		int expectedDocId = homeScreen.clickRecentDocumentLink().getLatestDocID();
		homeScreen.clickFavouritesDocumentLink();
		int actualDocId = homeScreen.getLatestDocID();
		AssertionUtil.verifyEqual(actualDocId, expectedDocId, "Verification of \"Doc Id\" in favourite documents");

		// ****** Verification of Remove From Favourites Message
		newDocScreen = homeScreen.clickFirstAvailableDocType();
		newDocScreen.switchToDocIDNewPageScreen();
		newDocScreen.navigateAndClickOnRemoveFromFavouritesLink();
		AssertionUtil.verifyEqual(newDocScreen.getInfoMessage(), Constants.REMOVE_FROM_FAVOURITES_INFO_MESSAGE,
				"Verification of \"Remove from Favorites\" Info Message");

		// ****** Verification of Removal of DocID From Favourites List
		homeScreen = newDocScreen.switchToMainPageScreen().switchToHomePageScreen();
		homeScreen.clickFavouritesDocumentLink();
		homeScreen.sortDocIDInDescOrder();
		int currentDocId = homeScreen.getLatestDocID();
		AssertionUtil.verifyNotEqual(currentDocId, actualDocId, "Verify \"Doc Id\" Removed from favourite documents");
	}

	@Test(description = "Verify Pagination Info value of Uploaded Document", priority = 1)
	public void verifyUploadedPDFDocument_HOME008() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "A3_103.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		// homeDocListTable.clickFirstAvailableDocType();
		newDocScreen.switchToDocIDNewPageScreen();
		String actualPageCount = newDocScreen.getUploadedDocPageInfo();

		// ****** Verification of Uploaded PDF Page count
		AssertionUtil.verifyEqual(actualPageCount, "Page 1/100", "Verification of Uploaded PDF Page count");
	}

}
