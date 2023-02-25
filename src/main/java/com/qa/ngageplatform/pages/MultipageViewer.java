package com.qa.ngageplatform.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class MultipageViewer {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private CommonUtil commonUtil;
	private NewDocPage newDocPage;

	private By mainContainerIframe = By.id("mainContainerFrame");
	private By uploadedDocumentPageIframe = By.id("westContainerFrame");
	private By pageLink = By.xpath("//li[@id='PAGE']");
	private By zoomLink = By.xpath("//li[@id='ZOOM']");
	private By mouseToolLink = By.xpath("//li[@id='CHANGEMOUSETOOL']");
	private By rotateLink = By.xpath("	//li[@id='ROTATE']");
	private By annotationsLink = By.xpath("//li[@id='ANNOTATIONS']");
	private By imageProcessingLink = By.xpath("//li[@id='IMAGE_PROCESSING']");
	private By clipboardLink = By.xpath("//li[@id='CLIPBOARD']");
	private By clearClipboardLink = By.id("CLEAR_FROM_CLIPBOARD");
	private By clearAlertMessage = By.id("ShowWebConfirmDialog");
	private By clearAlertTitle = By.xpath("//div[@id='ShowWebConfirmDialog']/preceding-sibling::div");
	private By addClipboardLink = By.id("ADD_TO_CLIPBOARD");
	private By viewClipboardLink = By.id("VIEW_CLIPBOARD");
	private By viewClipboardAlert = By.id("dialog");
	private By alertOkBtnForViewClipboard = By.xpath("(//*[contains(text(),'Ok')])[2]");
	private By alertError = By
			.xpath("//div[@id='ShowWebConfirmDialog']//div[@class='text-align-center position-relative']");
	private By alertOkBtn = By.xpath("//*[contains(text(),'Ok')]");
	private By deleteBtnForthumbnail = By.xpath("//*[@id='ui-id-9']");

	private By leftCloseArrow = By.xpath("//div[@id='dvMultiPageViewerwest-toggler']");
	private By rightCloseArrowOpen = By
			.xpath("//span[@class='content content-open']//div[@class='westToggler' and @title='Close']");
	private By rightExpandArrowOpen = By
			.xpath("//span[@class='content content-open']//div[@class='westExpand' and @title='Expand to full size']");
	private By rightResetArrowClose = By.xpath(
			"//span[@class='content content-closed']//div[@class='westReset' and @title='Reset size to default']");
	private By businessModelView = By.xpath("//span[contains(text(),'Business Model View')]");
	private By mainContainer = By.xpath("//div[@id='mainContent_WMM']");
	private By westContainer = By.xpath("//div[@id='dvMultiPageViewercenter']");
	private By thumbnailContainer = By.xpath("//div[@id='dvMultiPageViewerwest']");
	private By containerIFrame = By.xpath("//iframe[@id='ContentPlaceHolder1_iPage']");
	private By pageInputBox = By.xpath("//input[@id='EPMMultiPageViewer1_txtGoToPage']");
	private By nextPageBtn = By.xpath("//img[@id='GoNextPage']");
	private By previousPageBtn = By.xpath("//img[@id='GoPreviousPage']");
	private By firstPageBtn = By.xpath("//img[@id='GoFirstPage']");
	private By lastPageBtn = By.xpath("//img[@id='GoLastPage']");
	private By dialogBox = By.id("dialog");
	private By dialogOkBtn = By.xpath("//div[@class='ui-dialog-buttonset']");
	private By selectAllLink = By.xpath("//li[@id='SELECT_ALL']");
	private By insertAfterLink = By.xpath("//li[@id='INSERT_AFTER']");
	private By insertBeforeLink = By.xpath("//li[@id='INSERT_BEFORE']");
	private By chooseFileForUpload = By.xpath("//input[@id='EPMMultiPageViewer1_FileUpload']");
	private By clipboardPageInfo = By.id("EPMMultiPageViewer1_lblPageNum");
	private By okBtnForFileUpload = By.xpath("(//button[contains(text(),'Ok')])[1]");
	private By replaceLink = By.xpath("//li[@id='REPLACE']");
	private By rotate180Link = By.xpath("//li[@id='ROTATE180']");
	private By thumbnail1 = By.xpath("//*[@id='0']");
	private By thumbnail2 = By.xpath("//*[@id='1']");
	private By thumbnail3 = By.xpath("//*[@id='2']");
	private By thumbnail4 = By.xpath("//*[@id='3']");
	private By exportLink = By.id("EXPORT");
	private By splitToPDFLink = By.id("SPLIT_TO_PDF");
	private By splitToTIFFLink = By.id("SPLIT_TO_TIFF");
	private By dialogTitle = By.xpath("//span[@id='ui-id-113']");
	private By typeOptionPDF = By.id("EPMMultiPageViewer1_rblType_0");
	private By typeOptionTIFF = By.id("EPMMultiPageViewer1_rblType_1");
	private By annOptionNo = By.id("EPMMultiPageViewer1_rblAnnotation_1");
	private By pageOptionSelected = By.id("EPMMultiPageViewer1_rblPage_1");
	private By docClass = By.id("EPMMultiPageViewer1_DDLDocClasses");
	private By docType = By.id("EPMMultiPageViewer1_DDLDocTypes");
	private By indexDocument = By.id("EPMMultiPageViewer1_chkIndexDocument");
	private By okBtn = By.xpath("//*[contains(text(),'Ok')]");
	private By inputFieldValue = By.xpath("//input[contains(@id,'Key_BM_String')]");
	private By closeLink = By.xpath("//*[contains(text(), 'Close')]");
	private By refreshIcon = By.xpath("//td[@id='tblMyDocumentsResults_btnRefresh']");
	private By pdfNoAnnotationLink = By.id("PDF_NO_ANN");
	private By annOptionYes = By.id("EPMMultiPageViewer1_rblAnnotation_0");
	private By pageOptionAll = By.id("EPMMultiPageViewer1_rblPage_0");
	private By pageOptionPages = By.id("EPMMultiPageViewer1_rblPage_2");
	private By standardActionLink = By.xpath("//li[@id='actionsMenu_Submenu_1']//a//span[text()='Standard Actions']");
	private By saveLink = By.id("ui-id-1");
	private By recentDocumentsPageIframe = By.id("iframe_103");
	private By imgLogo = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_ObjectType']//img)[1]");
	private By docLastActionData = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_LastAction'])[1]");
	private By clipboardPageLink = By.id("PAGE");
	private By deleteClipboardPageLink = By.id("DELETE");
	private By clipboardToggle = By.id("dvMultiPageViewerwest-toggler");
	private By clipboardSaveLink = By.id("SAVE_TO_CLIPBOARD");
	private By clipboardSplitter = By.id("dvMultiPageViewerwest-resizer");

	public MultipageViewer(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		commonUtil = new CommonUtil();
		newDocPage = new NewDocPage(driver);
	}

	/**
	 * This method is used to delete Thumbnail
	 *
	 * @param pageNumber value in Integer which one will be deleted
	 * @throws AWTException
	 */
	public void deleteThumbnailPage(int pageNumber) throws AWTException {
		int actualPageNumber = pageNumber - 1;
		By thumbnailPage = By.xpath("//*[@id='" + actualPageNumber + "']");
		By thumbnailPage1 = By.xpath("//*[@id='2']");

		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		eleUtil.doClick(thumbnailPage);
		eleUtil.doClick(thumbnailPage1);
		eleUtil.doClick(this.pageLink);
		eleUtil.doClick(this.deleteBtnForthumbnail);
		try {
			String deleteMessage = eleUtil.doGetText(this.alertError);
			if (deleteMessage.contains("Are you sure you want to mark page(s) as Delete?")) {
				ExtentReportListener.test.get().log(Status.PASS,
						"Verification of Page delete message is displayed successfully");
				eleUtil.doDoubleClick(this.alertOkBtn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to get PageInfo after delete ThumbnailPage
	 *
	 * @return This will return page info in string
	 */
	public String getPageInfoAfterDeleteThumbnailPage() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		if (eleUtil.isDisplay(this.alertOkBtn)) {
			eleUtil.doClick(this.alertOkBtn);
			eleUtil.switchToDefaultContent();
			return newDocPage.getUploadedDocPageInfo();
		} else {
			return newDocPage.getUploadedDocPageInfo();
		}

	}

	/**
	 * This method is used to verify all menus are displayed in WMI Menu doc
	 *
	 * @return This will return none
	 */
	public void verificationOfMenusIsDisplayed() {

		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 60);
		boolean pageMenuDisplay = eleUtil.isDisplay(this.pageLink);
		boolean exportMenuDisplay = eleUtil.isDisplay(this.exportLink);
		boolean zoomMenuDisplay = eleUtil.isDisplay(this.zoomLink);
		boolean mouseToolMenuDisplay = eleUtil.isDisplay(this.mouseToolLink);
		boolean rotateMenuDisplay = eleUtil.isDisplay(this.rotateLink);
		boolean annotationsMenuDisplay = eleUtil.isDisplay(this.annotationsLink);
		boolean imageProcessingMenuDisplay = eleUtil.isDisplay(this.imageProcessingLink);
		boolean clipboardMenuDisplay = eleUtil.isDisplay(this.clipboardLink);

		eleUtil.switchToDefaultContent();
		if (pageMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of Page Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Page Menu is not displayed.");
		}
		if (exportMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of Export Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Export Menu is not displayed.");
		}
		if (zoomMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of Zoom Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Zoom Menu is not displayed.");
		}
		if (mouseToolMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of Mouse Tool Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Mouse Tool Menu is not displayed.");
		}
		if (rotateMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of Rotate Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Rotate Menu is not displayed.");
		}
		if (annotationsMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of Annotations Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Annotations Menu is not displayed.");
		}
		if (imageProcessingMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of Image Processing Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Image Processing Menu is not displayed.");
		}
		if (clipboardMenuDisplay == true) {
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of Clipboard Menu is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Clipboard Menu is not displayed.");
		}
	}

	/**
	 * This method is used to get class attribute value by putting condition of
	 * style attribute and click on left close arrow
	 *
	 * @return This will return class attribute value of left close arrow of
	 *         splitter
	 */
	public String verifyThumbnailIsOpen() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		String styleCondition = null;
		styleCondition = eleUtil.getAttributeValue(this.thumbnailContainer, "style");
		if (styleCondition.contains("none")) {
			eleUtil.doClick(this.leftCloseArrow);
		}
		String actualValue = eleUtil.getAttributeValue(this.leftCloseArrow, "class");
		eleUtil.switchToDefaultContent();
		return actualValue;
	}

	/**
	 * This method is used to validate West Container by clicking on Left closer
	 * splitter
	 *
	 * @return none
	 */
	public void validationLeftclosersplitter() {
		String thumnailOpenVal = "ui-layout-toggler-west-open";
		if (!verifyThumbnailIsOpen().contains(thumnailOpenVal)) {
			eleUtil.doClick(this.leftCloseArrow);
		}
		int beforeWestConWidth = getWestContainerWidth();
		String thumbnailmessage = null;
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		eleUtil.doClick(this.leftCloseArrow);

		try {
			thumbnailmessage = eleUtil.doGetText(this.leftCloseArrow);
			if (thumbnailmessage.trim().replaceAll("\n", "").contains("Thumb")) {
				ExtentReportListener.test.get().log(Status.PASS,
						"Verification of Thumbnail(s) word is displayed successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.switchToDefaultContent();
		int afterWestConWidth = getWestContainerWidth();
		if (afterWestConWidth > beforeWestConWidth) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of left closer arrow is successful");
		} else {
			AssertionUtil.fail("Verification of left closer arrow is not successful.");
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of left closer arrow is successful.");
		}
	}

	/**
	 * This method is used to validate east Container Business Model view full close
	 * thumbnail page and west container by clicking on Left closer splitter
	 *
	 * @return none
	 */
	public void validateRightCloserSplitter() {
		int totalWidthBefore = getMainContainerWidth() + getWestContainerWidth();
		eleUtil.doClick(this.rightCloseArrowOpen);
		int afterRightCloseArrowWidth = getMainContainerWidth();
		AssertionUtil.verifyGreaterThan(afterRightCloseArrowWidth, totalWidthBefore,
				"Verification of right closer arrow is successful");

		eleUtil.switchToFrame(this.mainContainerIframe, 10);
		eleUtil.switchToFrame(this.containerIFrame, 10);
		if (eleUtil.isDisplay(this.businessModelView)) {
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of Business Model veiw is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Business Model veiw is not displayed.");
		}
	}

	/**
	 * This method is used to reset size to default before clicking on Right closer
	 * splitter
	 *
	 * @return none
	 */
	public void validateRightResetArrowSplitter() {
		eleUtil.switchToDefaultContent();
		eleUtil.doClick(this.rightResetArrowClose);
		int totalWidthBefore = getMainContainerWidth() + getWestContainerWidth();
		int mainContainerWidth = getMainContainerWidth();
		AssertionUtil.verifyGreaterThan(totalWidthBefore, mainContainerWidth, "rightResetArrowClose validation");
		eleUtil.switchToFrame(this.mainContainerIframe, 10);
		eleUtil.switchToFrame(this.containerIFrame, 10);
		if (eleUtil.isDisplay(this.businessModelView)) {
			eleUtil.switchToDefaultContent();
			eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
			if (eleUtil.isDisplay(this.pageLink)) {
				ExtentReportListener.test.get().log(Status.PASS,
						"Verification of Page Link and Business Model Veiw both are displayed successfully");
			}
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Business Model veiw is not displayed.");
		}
	}

	/**
	 * This method is used to expand to full size of thumb nail page by clicking on
	 * right expand arrow of splitter and close the business model view
	 *
	 * @return none
	 */
	public void validateRightExpandArrowSplitter() {
		eleUtil.switchToDefaultContent();
		eleUtil.doClick(this.rightExpandArrowOpen);
		AssertionUtil.verifyGreaterThan(getWestContainerWidth(), getMainContainerWidth(),
				"Verification of right Expand arrow is successful");
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		if (eleUtil.isDisplay(this.pageLink)) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of Page Link is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Page Link is not displayed.");
		}
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to get main Container Width
	 *
	 * @return This will return main Container Width in Integer
	 */
	public int getMainContainerWidth() {
		eleUtil.switchToFrame(this.mainContainerIframe, 10);
		String styleAttributeValue = eleUtil.getAttributeValue(this.mainContainer, "style");
		String widthValueArray[] = styleAttributeValue.split(";");
		String strWidth[] = widthValueArray[4].split(":");
		String strWidthValue = strWidth[1].replace("px", "");
		int mainContainerWidth = Integer.parseInt(strWidthValue.trim());
		eleUtil.switchToDefaultContent();
		return mainContainerWidth;
	}

	/**
	 * This method is used to get West Container Width
	 *
	 * @return This will return West Container Width in Integer
	 */
	public int getWestContainerWidth() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		String styleAttributeValue = eleUtil.getAttributeValue(this.westContainer, "style");
		String widthValueArray[] = styleAttributeValue.split(";");
		String strWidth[] = widthValueArray[4].split(":");
		String strWidthValue = strWidth[1].replace("px", "");
		if (strWidthValue.contains(".")) {
			Float floatVal = Float.valueOf(strWidthValue.trim()).floatValue();
			System.out.println(floatVal);
			int westContainerWidth = Math.round(floatVal);
			eleUtil.switchToDefaultContent();
			return westContainerWidth;
		} else {
			int westContainerWidth = Integer.parseInt(strWidthValue.trim());
			eleUtil.switchToDefaultContent();
			return westContainerWidth;
		}
	}

	/**
	 * This method is used to download Image From Thumb nail using Robot class
	 *
	 * @param imagePath in String -the location where image will be downloaded
	 * @return This will none
	 */
	public void downloadImageFromThumbnail(String imagePath, By element) throws AWTException {
		eleUtil.switchToFrameIfExists(this.uploadedDocumentPageIframe, 5);
		String expectedValue = "ui-layout-toggler ui-layout-toggler-west ui-layout-toggler-open ui-layout-toggler-west-open";
		String actualValue = eleUtil.getAttributeValue(this.leftCloseArrow, "class");
		if (!actualValue.contentEquals(expectedValue)) {
			eleUtil.doClick(this.leftCloseArrow);
		}
		eleUtil.doClick(element);
		eleUtil.doRightClickOnElement(element);
		eleUtil.waitMethod();

		File file = new File(imagePath);
		try {
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
		}

		String path = file.toString();
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_DOWN);
		eleUtil.waitMethod();
		rb.keyPress(KeyEvent.VK_DOWN);
		eleUtil.waitMethod();
		rb.keyPress(KeyEvent.VK_ENTER);
		eleUtil.waitMethod();
		rb.keyRelease(KeyEvent.VK_DOWN);
		rb.keyRelease(KeyEvent.VK_DOWN);
		rb.keyRelease(KeyEvent.VK_ENTER);
		StringSelection string = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(string, null);
		eleUtil.waitMethod();
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		eleUtil.waitMethod();
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_TAB);
		eleUtil.waitMethod();
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_ENTER);
		try {
			if (file.exists()) {
				ExtentReportListener.test.get().log(Status.INFO, "verified file is downloaded.");
			}
		} catch (Exception e) {
		}
		eleUtil.switchToDefaultContent();

	}

	/**
	 * This method is used to get image text by using Java OCR
	 *
	 * @param imagePath in string to save the image
	 * @return This will return image text in string
	 */
	public String getImageText(String imagePath) throws TesseractException, IOException {
		Tesseract tes = new Tesseract();
		tes.setDatapath("./src/test/resources/tessdata");
		tes.setLanguage("eng");
		String imageText = tes.doOCR(new File(imagePath));
		return imageText;
	}

	/**
	 * This method is used to get Text From Thumbnail
	 *
	 * @param page number in integer to get text from it
	 * @return This will return image text in String
	 */
	public String getTextFromThumbnail(int pageNumber) throws IOException, AWTException, TesseractException {

		String filePath = null;
		filePath = new File("./").getAbsolutePath().replace(".", "")
				+ "src\\test\\resources\\Documents\\downloaded.jpg";
		int actualPageNumber = pageNumber - 1;
		By thumbnailPage = By.xpath("//*[@id='" + actualPageNumber + "']");
		eleUtil.waitMethod();
		downloadImageFromThumbnail(filePath, thumbnailPage);
		eleUtil.waitMethod();
		String imageText = getImageText(filePath);
		newDocPage.switchToDocIDNewPageScreen();
		return imageText;
	}

	/**
	 * This method is used to get PageInfo After Inserting PageNumber in page input
	 * box
	 *
	 * @param page number in integer
	 * @return This will return page info in String
	 */
	public String getPageInfoAfterInsertingPageNumber(String pageNo) {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe);
		String expectedValue = "ui-layout-toggler ui-layout-toggler-west ui-layout-toggler-open ui-layout-toggler-west-open";
		String actualValue = eleUtil.getAttributeValue(this.leftCloseArrow, "class");
		if (!actualValue.contentEquals(expectedValue)) {
			eleUtil.doClick(this.leftCloseArrow);
		}
		eleUtil.doSendKeys(this.pageInputBox, pageNo);
		try {
			eleUtil.waitForElementToBeClickable(this.pageInputBox, 3);
		} catch (Exception e) {
		}
		eleUtil.pressEnterKey();
		try {
			eleUtil.waitForElementToBeClickable(this.pageInputBox, 3);
		} catch (Exception e) {
		}
		eleUtil.switchToDefaultContent();
		try {
			eleUtil.waitForElementToBeClickable(this.pageInputBox, 3);
		} catch (Exception e) {
		}
		String result = newDocPage.getUploadedDocPageInfo();
		return result;
	}

	/**
	 * This method is used to get page info after click on a given thumbnail
	 *
	 * @param pageNumber in integer to click on that page
	 * @return This will return page info in String
	 */
	public String getPageInfoAfterClickOnThumbnail(int pageNumber) {
		int actualPageNumber = pageNumber - 1;
		By thumbnailPageLocator = By.xpath("//*[@id='" + actualPageNumber + "']");
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe);
		try {
			eleUtil.waitForElementToBeClickable(thumbnailPageLocator, 3);
		} catch (Exception e) {
		}
		eleUtil.waitMethod();
		eleUtil.doClick(thumbnailPageLocator);
		eleUtil.waitMethod();
		String pageInfo = newDocPage.getUploadedDocPageInfo();
		eleUtil.switchToDefaultContent();
		return pageInfo;
	}

	/**
	 * This method is used to get page count after click on first Thumbnail
	 *
	 * @return This will return total Thumbnail count in integer
	 */
	public int getTotalThumbnailCount() {
		String strDisplay = getPageInfoAfterClickOnThumbnail(1);
		String pageInfo[] = strDisplay.split("/");
		String pageCnt = pageInfo[1].trim();
		int totalCount = Integer.parseInt(pageCnt);
		return totalCount;

	}

	/**
	 * This method is used to validate given thumbnail is deleted
	 * 
	 * @param pageNumber in integer which need to be deleted
	 * @return This will return boolean result true if page is deleted, if not
	 *         return false
	 */
	public boolean verifyDeletedThumbnail(int pageNumber) throws IOException, AWTException, TesseractException {
		String result = newDocPage.getUploadedDocPageInfo();
		String ar[] = result.split(" ");
		String strDisplay[] = ar[1].split("/");
		String pageCnt = strDisplay[1].trim();
		boolean pageFlag = false;
		String filePath = null;
		int pageCount = Integer.parseInt(pageCnt);
		for (int i = 0; i < pageCount; i++) {
			By afterDeletethumbnailPage = By.xpath("//*[@id='" + i + "']");
			filePath = new File("./").getAbsolutePath().replace(".", "")
					+ "src\\test\\resources\\Documents\\downloaded.jpg";
			this.downloadImageFromThumbnail(filePath, afterDeletethumbnailPage);
			String imageText = this.getImageText(filePath);
			String pageText = pageNumber + " " + "page";
			{
				if (imageText.contains(pageText)) {
					pageFlag = true;
				} else {
					pageFlag = false;
				}
			}
		}
		return pageFlag;
	}

//Will work on it later
	/**
	 * This method is used to DragDrop from Thumbnail 1 on Thumbnail2
	 *
	 * @return This will return none
	 */
	public void dragThumbnail1OnThumbmail2() throws AWTException {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.xpath("(//table[@id='ThumbnailGrid']//tr//img)[1]"));
		WebElement target = driver.findElement(By.xpath("((//table[@id='ThumbnailGrid']//tr)[3]//div)[1]"));
		action.dragAndDrop(source, target).build().perform();

	}

	/**
	 * This method is used to click on next page arrow to get next page
	 *
	 * @return This will return the Object of class MultipageViewer
	 */
	public MultipageViewer clickOnNextBtn() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		eleUtil.doClick(this.nextPageBtn);
		eleUtil.switchToDefaultContent();
		return this;

	}

	/**
	 * This method is used to click on previous page arrow to get previous page
	 *
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer clickOnPreviousBtn() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		eleUtil.doClick(this.previousPageBtn);
		eleUtil.switchToDefaultContent();
		return this;

	}

	/**
	 * This method is used to click on first page arrow to get first page
	 *
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer clickOnFirstPageBtn() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		eleUtil.doClick(this.firstPageBtn);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on last page arrow to get last page
	 *
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer clickOnLastPageBtn() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		eleUtil.doClick(this.lastPageBtn);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get Error message when insert page number more than
	 * total page number
	 *
	 * @return This will return error message in String
	 */
	public String getErrorMessage() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe);
		eleUtil.doSendKeys(this.pageInputBox, "10");
		try {
			eleUtil.waitForElementToBeClickable(this.pageInputBox, 3);
		} catch (Exception e) {
		}
		eleUtil.pressEnterKey();
		String alert = eleUtil.doGetText(this.dialogBox);
		eleUtil.doClick(this.dialogOkBtn);
		eleUtil.switchToDefaultContent();
		return alert;

	}

	/**
	 * This method is used to verify all Thumbnail pages are selected after click on
	 * Select All link from page link
	 *
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer verifyAllThumbnailPagesAreSelected() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe);
		eleUtil.doClick(this.pageLink);
		eleUtil.doClick(this.selectAllLink);
		eleUtil.switchToDefaultContent();
		String result = newDocPage.getUploadedDocPageInfo();
		String ar[] = result.split(" ");
		String strDisplay[] = ar[1].split("/");
		String pageCnt = strDisplay[1].trim();
		int pageCount = Integer.parseInt(pageCnt);
		newDocPage.switchToUploadedDocumentFrame();
		String value = null;
		for (int i = 0; i < pageCount; i++) {
			By thumbnailPage = By.xpath("//*[@id='" + i + "']");
			try {
				value = eleUtil.getAttributeValue(thumbnailPage, "aria-selected");
			} catch (Exception e) {
			}
			if (value.equalsIgnoreCase("true")) {
				ExtentReportListener.test.get().log(Status.PASS,
						"Verification of thumbnail page is selected successfully");
			} else {
				AssertionUtil.fail("Verification of thumbnail page is not selected.");
				ExtentReportListener.test.get().log(Status.FAIL, "Verification of thumbnail page is not selected.");
			}
		}
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to upload File using page insert and choose file from
	 * page link by taking File by name from ./src/test/resources/Documents
	 *
	 * @param locator  Locator for insert after page or insert before page
	 * @param fileName FileName which need to upload
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer uploadFileByInsert(By locator, String fileName) {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe);
		eleUtil.doClick(this.pageLink);
		eleUtil.doClick(locator);
		String filePath = commonUtil.getResourceDocumentPath(fileName);
		eleUtil.doSendKeys(this.chooseFileForUpload, filePath);
		eleUtil.waitForElementPresence(this.okBtnForFileUpload, 20);
		eleUtil.doClick(this.okBtnForFileUpload);
		eleUtil.switchToDefaultContent();
		return this;

	}

	/**
	 * This method is used to upload File using insert after page link by taking
	 * File by name from ./src/test/resources/Documents
	 * 
	 * @param fileName FileName which need to upload
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer uploadedPagesByInsertingAfterPage(String fileName) {
		this.uploadFileByInsert(this.insertAfterLink, fileName);
		return this;

	}

	/**
	 * This method is used to upload File using insert before page link by taking
	 * File by name from ./src/test/resources/Documents
	 * 
	 * @param fileName FileName which need to upload
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer uploadedPagesByInsertingBeforePage(String fileName) {
		this.uploadFileByInsert(this.insertBeforeLink, fileName);
		return this;

	}

	/**
	 * This method is used to upload File using page replace link and choose file
	 * from page link by taking File by name from ./src/test/resources/Documents
	 *
	 * @param locator  Locator for replace link
	 * @param fileName FileName which need to upload
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer replacePageByUploadFile(String uploadFileName) {
		this.uploadFileByInsert(this.replaceLink, uploadFileName);
		return this;

	}

	/**
	 * This method is used to delete multiple Thumbnail pages
	 *
	 * @param pageNumber value in Integer which one will be deleted
	 * @throws AWTException
	 */
	public void deleteMultiThumbnailPages() throws AWTException {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		WebElement thumbnailPage_2 = driver.findElement(this.thumbnail2);
		WebElement thumbnailPage_3 = driver.findElement(this.thumbnail3);
		Actions action = new Actions(driver);
		Action serieOfActions = (Action) action.keyDown(Keys.CONTROL).click(thumbnailPage_2).click(thumbnailPage_3)
				.build();
		serieOfActions.perform();
		eleUtil.doClick(this.pageLink);
		eleUtil.doClick(this.deleteBtnForthumbnail);
		try {
			eleUtil.waitForElementPresence(this.alertError, 3);
			String deleteMessage = eleUtil.doGetText(this.alertError);
			if (deleteMessage.contains("Are you sure you want to mark page(s) as Delete?")) {
				ExtentReportListener.test.get().log(Status.PASS,
						"Verification of Page delete message is displayed successfully");
				eleUtil.doDoubleClick(this.alertOkBtn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to click on rotate link and rotate180
	 *
	 * @return This will return none
	 */
	public void clickOnRotate180Link() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe);
		eleUtil.doClick(this.rotateLink);
		eleUtil.doClick(this.rotate180Link);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to get title for Export SplitToPdf without annotation
	 * dialog box
	 *
	 * @return This will return title of dialog box in String
	 */
	public String getDialogTitleForExportPdfForWithoutAnnotations() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		eleUtil.doClick(this.exportLink);
		eleUtil.doClick(this.pdfNoAnnotationLink);
		eleUtil.waitForElementPresence(dialogTitle, 10);
		String title = eleUtil.doGetText(this.dialogTitle);
		return title;

	}

	/**
	 * This method is used to verify correct options for Export SplitToPdf without
	 * annotation are selected
	 *
	 * @return This will return boolean value
	 */
	public boolean validateDialogCorrectOptionsSelectedForPDFWithoutAnnotation() {
		boolean typeOptionPdf = eleUtil.isSelected(this.typeOptionPDF);
		boolean typeOptionTiff = eleUtil.isSelected(this.typeOptionTIFF);
		boolean annOptionNo = eleUtil.isSelected(this.annOptionNo);
		boolean annOptionYes = eleUtil.isSelected(this.annOptionYes);
		boolean pageOtionS = eleUtil.isSelected(this.pageOptionSelected);
		boolean pageOtionA = eleUtil.isSelected(this.pageOptionAll);
		boolean pageOtionP = eleUtil.isCheckboxSelected(this.pageOptionPages);
		eleUtil.doClick(this.okBtn);
		if (typeOptionPdf == true & typeOptionTiff == false & annOptionNo == true & annOptionYes == false
				& pageOtionS == true & pageOtionA == false & pageOtionP == false) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to select multiple thumbnails
	 *
	 */
	public void selectMultiThumbnails() {
		eleUtil.switchToFrame(this.uploadedDocumentPageIframe, 10);
		WebElement thumbnailPage1 = driver.findElement(this.thumbnail1);
		WebElement thumbnailPage2 = driver.findElement(this.thumbnail2);
		WebElement thumbnailPage4 = driver.findElement(this.thumbnail4);
		Actions action = new Actions(driver);
		Action serieOfActions = (Action) action.keyDown(Keys.CONTROL).click(thumbnailPage1).click(thumbnailPage2)
				.click(thumbnailPage4).build();
		serieOfActions.perform();
	}

	/**
	 * This method is used to get title from dialog box from Export> type option
	 * (Split To PDF or Split to TIFF) and validate selected options after split and
	 * then switch to new split doc update field value save> switch to new doc close
	 * it then switch to main recent page
	 * 
	 * @param typeOptionLocator Locator in By Format
	 * @return This will return title of the dialog box in String
	 */
	public String validateDialogBoxForTypeOption(By typeOptionLocator, By splitLinkLocator) {
		selectMultiThumbnails();
		eleUtil.doClick(this.exportLink);
		eleUtil.doClick(splitLinkLocator);
		String title = eleUtil.doGetText(this.dialogTitle);
		boolean typeOption = eleUtil.isSelected(typeOptionLocator);
		boolean annotationOption = eleUtil.isSelected(this.annOptionNo);
		boolean pageOtion = eleUtil.isSelected(this.pageOptionSelected);
		boolean indexDoc = eleUtil.isCheckboxSelected(this.indexDocument);
		eleUtil.doDropDownSelectByVisibleText(this.docClass, "WMI Menu");
		eleUtil.doDropDownSelectByVisibleText(this.docType, "WMI Menu BOV Vertical");

		if (typeOption == true & annotationOption == true & pageOtion == true & indexDoc == true) {
			ExtentReportListener.test.get().log(Status.PASS,
					"Verification of correct options are selected successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of correct options are not selected");
		}
		eleUtil.doClick(this.okBtn);
		try {
			eleUtil.waitForElementToBeClickable(this.inputFieldValue, 5);
		} catch (Exception e) {
		}
		eleUtil.switchToWindow(2);
		String value = "Automation Script " + commonUtil.getCurrentDateTime();
		eleUtil.switchToFrameIfExists(this.containerIFrame);
		eleUtil.doClick(this.inputFieldValue);
		eleUtil.doSendKeys(this.inputFieldValue, value);
		eleUtil.doSendKeys(this.inputFieldValue, value);
		eleUtil.moveToElementAndClick(standardActionLink, saveLink);
		switchToNewDocWindow();
		eleUtil.switchToFrame(this.mainContainerIframe);
		eleUtil.doClick(this.closeLink);
		eleUtil.switchToWindow(0);
		return title;
	}

	/**
	 * This method is used to get field text after creating by export Split to
	 * PDF/Split to TIFF
	 *
	 * @return This will return value in String
	 */
	public String getTextFromWMIMenuDoc() {
		eleUtil.switchToFrameIfExists(this.mainContainerIframe);
		eleUtil.switchToFrameIfExists(this.containerIFrame);
		eleUtil.doClick(this.inputFieldValue);
		String fieldText = eleUtil.getAttributeValue(this.inputFieldValue, "value");
		eleUtil.switchToDefaultContent();
		eleUtil.switchToFrame(this.mainContainerIframe);
		eleUtil.doClick(this.closeLink);
		return fieldText;
	}

	/**
	 * This method is used to verify PDF image is displayed in the Recent document
	 * page after sorting
	 *
	 * @return This will return value in boolean
	 */
	public boolean verifyPDFLogoIsDisplayed() {
		eleUtil.waitForElementPresence(this.imgLogo, 20);
		String pdfSrcValue = eleUtil.getAttributeValue(this.imgLogo, "src");
		if (eleUtil.isDisplay(this.imgLogo)) {
			if (pdfSrcValue.contains("i_g_PDF.gif")) {
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to verify TIFF image is displayed in the Recent document
	 * page after sorting
	 *
	 * @return This will return value in boolean
	 */
	public boolean verifyTIFFLogoIsDisplayed() {
		eleUtil.waitForElementPresence(this.imgLogo, 20);
		String pdfSrcValue = eleUtil.getAttributeValue(this.imgLogo, "src");
		if (eleUtil.isDisplay(this.imgLogo)) {
			if (pdfSrcValue.contains("i_g_TIF.gif")) {
			}
			return true;
		} else {
			return false;

		}
	}

	/**
	 * This method is used to switch to click on first doc of the Recent document
	 * page after sorting MultipageViewer class and switch to New doc page screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnFirstDoc() {
		eleUtil.waitForElementPresence(this.docLastActionData, 20);
		eleUtil.doDoubleClick(this.docLastActionData);
		return new NewDocPage(this.driver);

	}

	/**
	 * This method is used to click on Refresh button
	 *
	 */
	public void clickOnRefreshInRecentDoc() {
		eleUtil.switchToFrame(this.recentDocumentsPageIframe, 20);
		eleUtil.doClick(this.refreshIcon);
	}

	/**
	 * This method is used to get Export SplitToPDF dialog box after creating new
	 * PDF doc split
	 * 
	 * @return This will return titlePDF in String format
	 */
	public String getDialogBoxTitleForOptionPDF() {
		String titlePDF = validateDialogBoxForTypeOption(this.typeOptionPDF, this.splitToPDFLink);
		return titlePDF;
	}

	/**
	 * This method is used to get Export SplitToTIFF dialog box text after creating
	 * new TIFF doc split
	 * 
	 * @return This will return titleTIFF in String format
	 */
	public String getDialogBoxTitleForOptionTIFF() {
		String titleTIFF = validateDialogBoxForTypeOption(this.typeOptionTIFF, this.splitToTIFFLink);
		return titleTIFF;
	}

	/**
	 * This method is used to click on clip board link
	 * 
	 */
	public void doClickOnClipboard() {
		eleUtil.switchToFrameIfExists(this.uploadedDocumentPageIframe, 2);
		eleUtil.doClick(this.clipboardLink);
	}

	/**
	 * This method is used to click on view clip board link
	 * 
	 */
	public void doClickOnViewClipboardAfterAdd() {
		doClickOnClipboard();
		eleUtil.doClick(this.viewClipboardLink);
		switchToClipboardWindow();
	}

	/**
	 * This method is used to click on clear clip board link and ok button
	 * 
	 */
	public void doClickOnClearClipboardAndOkBtn() {
		doClickOnClipboard();
		eleUtil.doClick(this.clipboardLink);
		eleUtil.doClick(this.clearClipboardLink);
		eleUtil.doClick(this.alertOkBtn);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to click on clear clip board link and get text from
	 * confirmation dialog box
	 * 
	 * @return This will return dialogText in String format
	 */
	public String clickOnClearClipboardAndGetConfirmationDialog() {
		doClickOnClipboard();
		eleUtil.doClick(this.clearClipboardLink);
		String dialogText = eleUtil.doGetText(this.clearAlertMessage);
		eleUtil.doClick(this.alertOkBtn);
		eleUtil.switchToDefaultContent();
		return dialogText;
	}

	/**
	 * This method is used to click on add clip board after select multi thumbnails
	 * and switch to new clip board window
	 * 
	 */
	public void addMultiPageslToClipboardAfterClrClkBoardAndSwitchToClipboardWindow() {
		doClickOnClipboard();
		doClickOnClearClipboardAndOkBtn();
		selectMultiThumbnails();
		doClickOnClipboard();
		eleUtil.doClick(this.addClipboardLink);
		eleUtil.switchToDefaultContent();
		doClickOnViewClipboardAfterAdd();
	}

	/**
	 * This method is used to switch to new doc window
	 *
	 */
	public void switchToNewDocWindow() {
		eleUtil.switchToWindow(1);
	}

	/**
	 * This method is used to switch to clip- board page window
	 *
	 */
	public void switchToClipboardWindow() {
		eleUtil.switchToWindow(2);
	}

	/**
	 * This method is used to get page info for selected thumbnail of the clip board
	 * window
	 *
	 * @param pageNo is in Integer format
	 * @return This will return pageInfo in String format
	 */
	public String getPageInfoForClipBoardWindowDoc(int pageNumber) {
		int pageNo = pageNumber - 1;
		By thumbnailPage = By.xpath("//*[@id='" + pageNo + "']");
		eleUtil.doClick(thumbnailPage);
		String pageInfo = eleUtil.doGetText(this.clipboardPageInfo);
		return pageInfo;

	}

	/**
	 * This method is used to click on view clip board link and get text from alert
	 * dialog box after clear clip board
	 * 
	 * @return This will return alertText in String format
	 */
	public String clickOnViewClipboardAndGetAlertDialog() {
		doClickOnClipboard();
		eleUtil.doClick(this.viewClipboardLink);
		String alertTextForViewClipboard = eleUtil.doGetText(this.viewClipboardAlert);
		eleUtil.doClick(this.alertOkBtnForViewClipboard);
		eleUtil.switchToDefaultContent();
		return alertTextForViewClipboard;
	}

	/**
	 * This method is used to get alert text after click on delete link for selected
	 * clipboard thumbnail page
	 *
	 * @return This will return alertTextForDelete in String format
	 */
	public String getAlertForDeleteClipboardPage() {
		eleUtil.doClick(this.clipboardPageLink);
		eleUtil.doClick(this.deleteClipboardPageLink);
		Alert alert = driver.switchTo().alert();
		String alertTextForDelete = alert.getText();
		System.out.print(alertTextForDelete);
		alert.accept();
		return alertTextForDelete;

	}

	/**
	 * This method is used to save clipboard after delete first thumbnail page
	 *
	 * @return This will return none
	 */
	public void saveClipboard() {
		eleUtil.doClick(this.clipboardLink);
		eleUtil.doClick(this.clipboardSaveLink);
	}

	/**
	 * This method is used to verify thumbnails are displayed in clipboard document
	 *
	 * @return This will return boolean true or false
	 */
	public boolean verifyClipboardThumbnailIsVisible() {
		String classValueOfSplitter = eleUtil.getAttributeValue(this.clipboardToggle, "class");
		System.out.print(classValueOfSplitter);
		if (eleUtil.isEnabled(this.thumbnail1) & eleUtil.isEnabled(this.thumbnail2)
				& classValueOfSplitter.contains("layout-toggler-west-open")) {

			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to get class Attribute vale of toggle slider of clipboard
	 * doc after click on it
	 *
	 * @return This will return boolean
	 */
	public String getClassAttrubuteValueOfToggleSlidder() {
		eleUtil.doClick(this.clipboardToggle);
		String classValueOfSplitter = eleUtil.getAttributeValue(this.clipboardToggle, "class");
		return classValueOfSplitter;
	}

	/**
	 * This method is used to drag and drop clipboard's thumbnail1 to thimbnail2
	 *
	 */
	public void dragClipboardThumbnail1OnThumbmail2() {
		eleUtil.dragAndDrop(this.thumbnail1, this.thumbnail2);
	}

	/**
	 * This method is used to drag splitter from left to right
	 *
	 * @return This will return none
	 */
	public void dragSplitterFromLeftToRight() {
		WebElement splitter = driver.findElement(this.clipboardSplitter);
		Actions action = new Actions(driver);
		action.dragAndDropBy(splitter, 680, 688).perform();
	}

}
