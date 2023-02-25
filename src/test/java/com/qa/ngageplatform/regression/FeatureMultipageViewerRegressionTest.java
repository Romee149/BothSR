package com.qa.ngageplatform.regression;

import java.awt.AWTException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.pages.NewDocPage;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.PDFUtil;

import net.sourceforge.tess4j.TesseractException;

public class FeatureMultipageViewerRegressionTest extends BaseTest {

	String classValueForThumbnailOpen = "ui-layout-toggler ui-layout-toggler-west ui-layout-toggler-open ui-layout-toggler-west-open";
	String validationValueForThumbnailOpen = null;
	String imageText = null;
	int thumbnailPageNumber=0 ;

	@Test(description = "Verify menus are displayed in newly created WMI Menu Doc", priority = 1)
	public void verifyVisibilityOfPDFDocumentMenus_MV001() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verificationOfMenusIsDisplayed();
		String pageInfo = newDocScreen.getUploadedDocPageInfo();
		AssertionUtil.verifyEqual(pageInfo, "Page 1/8", "Verification of Uploaded PDF Page count is successful");
	}

	@Test(description = "Verify Thumbnail grid is open and Thumbnail Text for 2 and 4", priority = 2)
	public void verifyThumbnailGridAndThumbnailText_MV002() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		validationValueForThumbnailOpen = multiPageScreen.verifyThumbnailIsOpen();
		AssertionUtil.verifyEqual(validationValueForThumbnailOpen, classValueForThumbnailOpen,"Verification of Thumbnail Grid is open successfully");
		newDocScreen.getUploadedDocPageInfo();
		imageText = multiPageScreen.getTextFromThumbnail(thumbnailPageNumber+2);
		AssertionUtil.verifyContainsText(imageText, "Q", "Verification of thumbnail 2 text is successful");
		newDocScreen.getUploadedDocPageInfo();
		imageText = multiPageScreen.getTextFromThumbnail(thumbnailPageNumber+4);
		AssertionUtil.verifyContainsText(imageText, "S", "Verification of thumbnail 4 text is successful");

	}

	@Test(description = "Verify Thumbnail grid and Thumbnail Text for 1 and 2 after drag and drop", priority = 3, enabled = false)
	public void verifyThumbnailGridAndThumbnailText_MV003() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.validateNewDocumentExist();
		newDocScreen = mainPage.getHomeDocumentsListPage().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		String validationValueForThumbnailOpen = multiPageScreen.verifyThumbnailIsOpen();
		AssertionUtil.verifyEqual(validationValueForThumbnailOpen, classValueForThumbnailOpen,
				"Verification of Thumbnail Grid is open successfully");
		multiPageScreen.dragThumbnail1OnThumbmail2();
		imageText = multiPageScreen.getTextFromThumbnail(thumbnailPageNumber+2); 
		AssertionUtil.verifyEqual(imageText, "1 page",
				"Verification of page 2 contain page 1 text after drag thumbnail 1 on thumbnail2 is successful");
		imageText = multiPageScreen.getTextFromThumbnail(1);
		AssertionUtil.verifyEqual(imageText, "2 page",
				"Verification of page 1 contain page 2 text after drag thumbnail 1 on thumbnail2 is successful");
	}

	@Test(description = "Verify splitters for left close, right close, reset and expand", priority = 4)
	public void verifySplitters_MV004_To_MV007() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.validateNewDocumentExist();
		newDocScreen = mainPage.getHomeDocumentsListPage().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.validationLeftclosersplitter();
		multiPageScreen.validateRightCloserSplitter();
		multiPageScreen.validateRightResetArrowSplitter();
		multiPageScreen.validateRightExpandArrowSplitter();

	}

	@Test(description = "Verify next and previous buttons functionalty", priority = 5)
	public void verifyNextBtnAndPreviousBtn_MV008_MV009() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.clickOnNextBtn();
		String pageInfoN = newDocScreen.getUploadedDocPageInfo();
		AssertionUtil.verifyEqual(pageInfoN, "Page 2/8",
				"Verification of Uploaded PDF Page info after click on next button");
		imageText = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(imageText, "Q",
				"Verification of page 2 contains text after upload file is successful");
		String actualPageInfo = multiPageScreen.getPageInfoAfterInsertingPageNumber("8");
		AssertionUtil.verifyEqual(actualPageInfo, "Page 8/8",
				"Verification of Uploaded PDF Page info after inserting page number 8");
		multiPageScreen.clickOnPreviousBtn();
		String actualPageInfoP = newDocScreen.getUploadedDocPageInfo();
		AssertionUtil.verifyEqual(actualPageInfoP, "Page 7/8",
				"Verification of Uploaded PDF Page info after click on previous button");
		imageText = multiPageScreen.getTextFromThumbnail(7);
		AssertionUtil.verifyContainsText(imageText, "V",
				"Verification of page 7 contains text after upload file is successful");
	}

	@Test(description = "Verify first and last button fuctionality", priority = 6)
	public void verifyThumbnailPageInfo_MV010_MV011() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();

		String actualPageInfo = multiPageScreen.getPageInfoAfterInsertingPageNumber("8");
		AssertionUtil.verifyEqual(actualPageInfo, "Page 8/8",
				"Verification of Uploaded PDF Page info after inserting page number 8");
		imageText = multiPageScreen.getTextFromThumbnail(8);
		AssertionUtil.verifyContainsText(imageText, "W", "Verification of Uploaded PDF Page count");

		multiPageScreen.clickOnFirstPageBtn();
		String actualPageInfoFirst = newDocScreen.getUploadedDocPageInfo();
		AssertionUtil.verifyEqual(actualPageInfoFirst, "Page 1/8",
				"Verification of Uploaded PDF Page info after click on first button");
		imageText = multiPageScreen.getTextFromThumbnail(1);
		AssertionUtil.verifyContainsText(imageText, "P", "Verification of Uploaded PDF Page count");

		multiPageScreen.clickOnLastPageBtn();
		String actualPageInfoLast = newDocScreen.getUploadedDocPageInfo();
		AssertionUtil.verifyEqual(actualPageInfoLast, "Page 8/8", "Verification of Uploaded PDF Page count");
		imageText = multiPageScreen.getTextFromThumbnail(8);
		AssertionUtil.verifyContainsText(imageText, "W", "Verification of Uploaded PDF Page count");

	}

	@Test(description = "Verify page info after inserting page number", priority = 7)
	public void verifyThumbnailPageInfo_MV012_MV016() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();

		String actualPageInfo = multiPageScreen.getPageInfoAfterInsertingPageNumber("6");
		AssertionUtil.verifyEqual(actualPageInfo, "Page 6/8",
				"Verification of Uploaded PDF Page info after inserting page number 6");
		String imageText = multiPageScreen.getTextFromThumbnail(6);
		AssertionUtil.verifyContainsText(imageText, "U",
				"Verification of page 7 contains text after upload file is successful");

		String actualPageInfoPage8 = multiPageScreen.getPageInfoAfterClickOnThumbnail(8);
		AssertionUtil.verifyEqual(actualPageInfoPage8, "Page 8/8", "Verification of Uploaded PDF Page count");
	}

	@Test(description = "Verify alert pop up message and select all pages link", priority = 8)
	public void verifyThumbnailPageInfo_MV017_MV018() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.validateNewDocumentExist();
		newDocScreen = mainPage.getHomeDocumentsListPage().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		String validationValueForThumbnailOpen = multiPageScreen.verifyThumbnailIsOpen();
		AssertionUtil.verifyEqual(validationValueForThumbnailOpen, classValueForThumbnailOpen,
				"Verification of Thumbnail Grid is open successfully");
		String alertMessage = "Page number can not be greater than document pages count.";
		String actualalertMessage = multiPageScreen.getErrorMessage();
		AssertionUtil.verifyEqual(actualalertMessage, alertMessage,
				"Verification of Error message on popup is displayed successfully");
		multiPageScreen.verifyAllThumbnailPagesAreSelected();
	}

	@Test(description = "Verify uploaded file's pages info by inserting after page", priority = 9)
	public void verifyUploadedFileInfo_MV019() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verifyThumbnailIsOpen();
		String pageInfoAfterClickOnPage2ABeforeUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage2ABeforeUpload, "Page 2/8",
				"Verification of page info before uploading file for page2 is successfull");

		multiPageScreen.uploadedPagesByInsertingAfterPage("AB.pdf");
		String pageInfoAfterClickOnPage2AfterUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage2AfterUpload, "Page 2/10",
				"Verification of page info after uploading file using inserting after page for page2 is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(imageText, "Q",
				"Verification of page 2 contains text after upload file is successful");

		String pageInfoAfterClickOnPage3AfterUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(3);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage3AfterUpload, "Page 3/10",
				"Verification of page info after uploading file using inserting after page for page3 is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(3);
		AssertionUtil.verifyContainsText(imageText, "A",
				"Verification of page 3 contains text after upload file is successful");

		String pageInfoAfterClickOnPage4AfterUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(4);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage4AfterUpload, "Page 4/10",
				"Verification of page info after uploading file using inserting after page for page4 is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(4);
		AssertionUtil.verifyContainsText(imageText, "B",
				"Verification of page 4 contains text after upload file is successful");
	}

	@Test(description = "Verify uploaded file's pages info by inserting before page", priority = 10)
	public void verifyUploadedFileInfo_MV020() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verifyThumbnailIsOpen();

		String pageInfoBeforeUploadFile = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoBeforeUploadFile, "Page 2/8",
				"Verification of page info before uploading file for page2 is successfull");

		multiPageScreen.uploadedPagesByInsertingBeforePage("AB.pdf");
		String pageInfoAfterClickOnPage2AfterUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage2AfterUpload, "Page 2/10",
				"Verification of page info after uploading file using inserting before page for page2 is successfull");

		String actualTextPage2 = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(actualTextPage2, "A",
				"Verification of page 2 contains text after upload file is successful");

		String pageInfoAfterClickOnPage3AfterUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(3);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage3AfterUpload, "Page 3/10",
				"Verification of page info after uploading file using inserting before page for page3 is successfull\"");
		String actualTextPage3 = multiPageScreen.getTextFromThumbnail(3);
		AssertionUtil.verifyContainsText(actualTextPage3, "B",
				"Verification of page 3 contains text after upload file is successful");

		String pageInfoAfterClickOnPage4AfterUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(4);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage4AfterUpload, "Page 4/10",
				"Verification of page info after uploading file using inserting before page for page4 is successfull");
		String actualTextPage4 = multiPageScreen.getTextFromThumbnail(4);
		AssertionUtil.verifyContainsText(actualTextPage4, "Q",
				"Verification of page 4 contains text after upload file is successful");
	}

	@Test(description = "Verify uploaded file's pages info by replace page", priority = 11)
	public void verifyReplacedPageInfo_MV021_MV022() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verifyThumbnailIsOpen();
		String pageInfoAfterClickOnPage2ABeforeUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage2ABeforeUpload, "Page 2/8",
				"Verification of page info before uploading file for page2 is successfull");

		multiPageScreen.replacePageByUploadFile("Replace.pdf");
		String pageInfoAfterClickOnPage2AfterUpload = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage2AfterUpload, "Page 2/8",
				"Verification of page info after uploading file using inserting after page for page2 is successfull");
		String actualText1 = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(actualText1, "Rep",
				"Verification of page 2 contains text 'Rep' after replace page is successful");
		multiPageScreen.replacePageByUploadFile("AB.pdf");
		String pageInfoAfterClickOnPage2AfterReplace = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage2AfterReplace, "Page 2/9",
				"Verification of page info after uploading file using inserting after page for page2 is successfull");

		String actualTextPage2 = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(actualTextPage2, "A",
				"Verification of page 2 contains text 'A' after replace page is successful");
		String pageInfoAfterClickOnPage3AfterReplace = multiPageScreen.getPageInfoAfterClickOnThumbnail(3);
		AssertionUtil.verifyContainsText(pageInfoAfterClickOnPage3AfterReplace, "Page 3/9",
				"Verification of page info after uploading file using inserting after page for page2 is successfull");
		String actualTextPage3 = multiPageScreen.getTextFromThumbnail(3);
		AssertionUtil.verifyContainsText(actualTextPage3, "B",
				"Verification of page 2 contains text 'B' after replace page is successful");
	}

	@Test(description = "Verify Thumbnail grid delete message, page info before and after delete page", priority = 12)
	public void verifyThumbnailDeleteOptions_MV0023() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		String validationValueForThumbnailOpen = multiPageScreen.verifyThumbnailIsOpen();
		AssertionUtil.verifyEqual(validationValueForThumbnailOpen, classValueForThumbnailOpen,
				"Verification of Thumbnail Grid is open successfully");
		String pageInfoBeforeDelete = multiPageScreen.getPageInfoAfterClickOnThumbnail(3);
		AssertionUtil.verifyContainsText(pageInfoBeforeDelete, "Page 3/8",
				"Verification of page info before delete for page3 is successfull");
		multiPageScreen.deleteThumbnailPage(3);
		String pInfo = multiPageScreen.getPageInfoAfterDeleteThumbnailPage();
		AssertionUtil.verifyEqual(pInfo, "Page 3/7", "Verification of page info after delete page 2 is successful.");
		imageText = multiPageScreen.getTextFromThumbnail(3);
		AssertionUtil.verifyContainsText(imageText, "S", "Verification of thumbnail 2 text is successful");

	}

	@Test(description = "Verify rotate menu and text after rotate", priority = 13)
	public void verifyRotateMenu_MV025() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verifyThumbnailIsOpen();
		String pageInfoBeforeRotate = multiPageScreen.getPageInfoAfterClickOnThumbnail(6);
		AssertionUtil.verifyContainsText(pageInfoBeforeRotate, "Page 6/8",
				"Verification of page info before rotate for page6 is successfull");
		multiPageScreen.clickOnRotate180Link();
		String pageInfoAfterRotate = multiPageScreen.getPageInfoAfterClickOnThumbnail(6);
		AssertionUtil.verifyContainsText(pageInfoAfterRotate, "Page 6/8",
				"Verification of page info before rotate for page6 is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(6);
		AssertionUtil.verifyContainsText(imageText, "fl", "Verification of thumbnail 2 text is successful");

	}

	@Test(description = "Verify multipages deleted and verify delete message", priority = 14)
	public void verifyMultipleThumbnailDelete_MV024() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verifyThumbnailIsOpen();
		multiPageScreen.deleteMultiThumbnailPages();
		String pageInfoAfterRotate = multiPageScreen.getPageInfoAfterClickOnThumbnail(1);
		AssertionUtil.verifyContainsText(pageInfoAfterRotate, "Page 1/5",
				"Verification of page info after delete 3 page is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(1);
		AssertionUtil.verifyContainsText(imageText, "S", "Verification of thumbnail 2 text is successful");
	}

	@Test(description = "Verify page info for Thumbnail 2 and 3 and total count of Thumbnail", priority = 15)
	public void verifyPageInfoAndTotalCount_MV026_MV027() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		int toatalPageCount = multiPageScreen.getTotalThumbnailCount();
		AssertionUtil.verifyEqual(toatalPageCount, 8, "Verification of total Thumbnail count is successful");
		String pageInfoForThumbnail2 = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoForThumbnail2, "Page 2/8",
				"Verification of page info before delete for page2 is successfull");
		String pageInfoForThumbnail3 = multiPageScreen.getPageInfoAfterClickOnThumbnail(3);
		AssertionUtil.verifyContainsText(pageInfoForThumbnail3, "Page 3/8",
				"Verification of page info before delete for page3 is successfull");
	}

	@Test(description = "Verify Export>PDF without annotations and default options of dialog box and ", priority = 16)
	public void verifyExportPdfNoAnnotations_MV032() {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.validateNewDocumentExist();
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		String titleValue = multiPageScreen.getDialogTitleForExportPdfForWithoutAnnotations();
		AssertionUtil.verifyContainsText(titleValue, "Export PDF Without Annotations",
				"Verification of dialog title is displayed successfully");
		boolean result = multiPageScreen.validateDialogCorrectOptionsSelectedForPDFWithoutAnnotation();
		AssertionUtil.verifyEqual(result, true,
				"Verification of correct options for PDF without annotation doc are selected successfully");
	}

	@Test(description = "Verify Export>Split to Pdf document and default options of dialog box and "
			+ "verify text of newly created doc ", priority = 17, enabled = true)
	public void verifyExportSplitToPdf_MV034() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		String titlePDF = multiPageScreen.getDialogBoxTitleForOptionPDF();
		AssertionUtil.verifyContainsText(titlePDF, "Split To PDF",
				"Verification of dialog title for Split to PDF option is displayed successfully");
		multiPageScreen.clickOnRefreshInRecentDoc();
		AssertionUtil.verifyEqual(multiPageScreen.verifyPDFLogoIsDisplayed(), true,
				"Verification of PDF icon displayed is successful.");
		multiPageScreen.clickOnFirstDoc();
		newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verifyThumbnailIsOpen();
		String pageInfoForThumbnail1 = multiPageScreen.getPageInfoAfterClickOnThumbnail(1);
		AssertionUtil.verifyContainsText(pageInfoForThumbnail1, "Page 1/2",
				"Verification of page info for page1 after creating new split to PDF document is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(1);

		AssertionUtil.verifyContainsText(imageText, "Q",
				"Verification of thumbnail 1 text for newly created is successfull");
		String pageInfoForThumbnail2 = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoForThumbnail2, "Page 2/2",
				"Verification of page info for page2 after creating new split to PDF document is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(imageText, "S",
				"Verification of thumbnail 1 text for newly created " + "split to pdf document is successful");
		String actualFieldText = multiPageScreen.getTextFromWMIMenuDoc();
		String expectedValue = "Automation Script " + new CommonUtil().getCurrentDateTime("ddMMyyyy");
		AssertionUtil.verifyContainsText(actualFieldText, expectedValue,
				"Verification of field text after creating doc by" + "export split to PDF is successful");

	}

	@Test(description = "Verify Export>Split to TIFF document and default options of dialog box and "
			+ "verify text of newly created doc ", priority = 18, enabled = true)
	public void verifyExportSplitToTIFF_MV035() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		String titleTIFF = multiPageScreen.getDialogBoxTitleForOptionTIFF();
		AssertionUtil.verifyContainsText(titleTIFF, "Split To TIFF",
				"Verification of dialog title for Split to TIFF option is displayed successfully");
		multiPageScreen.clickOnRefreshInRecentDoc();
		AssertionUtil.verifyEqual(multiPageScreen.verifyTIFFLogoIsDisplayed(), true,
				"Verification of TIFF icon displayed is successful.");
		multiPageScreen.clickOnFirstDoc();
		newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.verifyThumbnailIsOpen();
		String pageInfoForThumbnail1 = multiPageScreen.getPageInfoAfterClickOnThumbnail(1);
		AssertionUtil.verifyContainsText(pageInfoForThumbnail1, "Page 1/2",
				"Verification of page info for page1 after creating new split to TIFF document is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(1);
		AssertionUtil.verifyContainsText(imageText, "Q",
				"Verification of thumbnail 1 text for newly created is successful");
		String pageInfoForThumbnail2 = multiPageScreen.getPageInfoAfterClickOnThumbnail(2);
		AssertionUtil.verifyContainsText(pageInfoForThumbnail2, "Page 2/2",
				"Verification of page info for page2 after creating new split to TIFF document is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(imageText, "S",
				"Verification of thumbnail 1 text for newly created split to TIFF document is successful");
		String actualFieldText = multiPageScreen.getTextFromWMIMenuDoc();
		String expectedValue = "Automation Script " + new CommonUtil().getCurrentDateTime("ddMMyyyy");
		AssertionUtil.verifyContainsText(actualFieldText, expectedValue,
				"Verification of field text after creating doc by export split to TIFF is successful");
	}

	@Test(description = "Verify clear, add and veiw clipboard links, clip board document's thumbnails' text"
			+ "& verify alert and confirmation messages", priority = 19)
	public void verifyClipboard_MV038_MV041() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.addMultiPageslToClipboardAfterClrClkBoardAndSwitchToClipboardWindow();
		String pageInfoForClipboardPage1 = multiPageScreen.getPageInfoForClipBoardWindowDoc(1);
		AssertionUtil.verifyContainsText(pageInfoForClipboardPage1, "Page 1/2","Verification of page info for page1 of clipboard document is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(1);
		AssertionUtil.verifyContainsText(imageText, "Q","Verification of thumbnail 1 text of clipboard document is successful");
		multiPageScreen.switchToClipboardWindow();
		String pageInfoForClipboardPage2 = multiPageScreen.getPageInfoForClipBoardWindowDoc(2);
		AssertionUtil.verifyContainsText(pageInfoForClipboardPage2, "Page 2/2",
				"Verification of page info for page2 of clipboard document is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(2);
		AssertionUtil.verifyContainsText(imageText, "S",
				"Verification of thumbnail 2 text of clipboard document is successful");
		multiPageScreen.switchToNewDocWindow();
		String confirmationActualText = multiPageScreen.clickOnClearClipboardAndGetConfirmationDialog();
		String confirmationExpectedText = "Are you sure you want to Clear ClipBoard?";
		AssertionUtil.verifyEqual(confirmationActualText, confirmationExpectedText,
				"Verification of message in confirmation for clear clipboard is successful.");

		String alertActualText = multiPageScreen.clickOnViewClipboardAndGetAlertDialog();
		String alertExpectedText = "Clipboard is empty";
		AssertionUtil.verifyEqual(alertActualText, alertExpectedText,
				"Verification of alert message in alert box for view clipboard is successful.");
	}
	
	@Test(description = "Verify delete option for clipboard document's thumbnail and after delete verify page info and text"
			+ "& verify alert messages for delete", priority = 22)
	public void verifyClipboard_MV46_MV47() throws IOException, AWTException, TesseractException {
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.addMultiPageslToClipboardAfterClrClkBoardAndSwitchToClipboardWindow();
		
		String deleteAlertActualText = multiPageScreen.getAlertForDeleteClipboardPage();
		String deleteAlertExpectedText = "Are you sure you want to mark page(s) as Remove?";
		AssertionUtil.verifyEqual(deleteAlertActualText, deleteAlertExpectedText,
				"Verification of alert message for delete clipboard page is successful.");
		multiPageScreen.saveClipboard();
		String pageInfoForClipboardPage = multiPageScreen.getPageInfoForClipBoardWindowDoc(1);
		AssertionUtil.verifyContainsText(pageInfoForClipboardPage, "Page 1/1",
				"Verification of page info for second page of clipboard document after delete first page is successfull");
		imageText = multiPageScreen.getTextFromThumbnail(1);
		AssertionUtil.verifyContainsText(imageText, "S",
				"Verification of thumbnail text of clipboard document is successful");

		multiPageScreen.switchToNewDocWindow();
		multiPageScreen.doClickOnViewClipboardAfterAdd();
		String pageInfoAfterSaveAndViewClipboard = multiPageScreen.getPageInfoForClipBoardWindowDoc(1);
		AssertionUtil.verifyContainsText(pageInfoAfterSaveAndViewClipboard, "Page 1/1",
				"Verification of page info after save clipboard document and switch to WMI window and then click on view clipboard is successfull");
	
	}
	@Test(description = "Verify toggle slidder by visiblity of thumbnail", priority = 20, enabled = false)
	public void verifyDragDropClipboardPages_MV42() throws IOException, AWTException, TesseractException{
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.validateNewDocumentExist();
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.addMultiPageslToClipboardAfterClrClkBoardAndSwitchToClipboardWindow();
		boolean visibleThumbnails = multiPageScreen.verifyClipboardThumbnailIsVisible();
		AssertionUtil.verifyEqual(visibleThumbnails, true, "Verification of thumbnails are displayed successfully");
		multiPageScreen.dragClipboardThumbnail1OnThumbmail2();
		
		}
	
	@Test(description = "Verify toggle slidder by visiblity of thumbnail", priority = 21)
	public void verifyToggleSlidderFunctionality_MV43(){
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.validateNewDocumentExist();
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.addMultiPageslToClipboardAfterClrClkBoardAndSwitchToClipboardWindow();
		boolean visibleThumbnails = multiPageScreen.verifyClipboardThumbnailIsVisible();
		AssertionUtil.verifyEqual(visibleThumbnails, true, "Verification of thumbnails are displayed successfully");
		
		String classValueOfSplitter = multiPageScreen.getClassAttrubuteValueOfToggleSlidder();
		AssertionUtil.verifyContainsText(classValueOfSplitter, "layout-toggler-west-close", "Verification of thumbnails are not displayed successfully after click on toggle splitter");
}
	@Test(description = "Verify splitter by dragging to left to right", enabled=false, priority = 23)
	public void verifySlidderFunctionality_MV45(){
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.validateNewDocumentExist();
		newDocScreen = mainPage.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		multiPageScreen = newDocScreen.switchToMultipageViewerClass();
		multiPageScreen.addMultiPageslToClipboardAfterClrClkBoardAndSwitchToClipboardWindow();
		multiPageScreen.dragSplitterFromLeftToRight();
//will work later
}
	}