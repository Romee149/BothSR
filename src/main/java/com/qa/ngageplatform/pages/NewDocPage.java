package com.qa.ngageplatform.pages;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.RescaleOp;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.debugger.ui.ImageUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.github.dockerjava.api.model.Image;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;
import com.qa.ngageplatform.utils.JavaScriptUtil;
import com.qa.ngageplatform.utils.PDFUtil;
//import com.asprise.util.oct.OCR;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * This Class is used to provide Object Repo and Actions related to NewDoc Page
 * Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class NewDocPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	private CommonUtil commonUtil;

	// ****************** Locators ****************** //
	private By actionLink = By.id("actionsMenu_Submenu_1");
	private By saveLink = By.id("actionsMenu_Submenu_2");
	private By customerDetailsTextField = By.xpath("//input[contains(@id,'Customer_details')]");
	private By customerNameTextField = By.xpath("(//input[contains(@id,'Customer_Name')])[1]");
	private By customerFirstNameTextField = By.xpath("(//input[@class='inputclass BO_Textbox'])[1]");
	private By customerLastNameTextField = By.xpath("(//input[@class='inputclass BO_Textbox'])[2]");
	private By BodyTextField = By.xpath("//*[@id='tinymce']");
	private By stringFieldTextField = By.xpath("//input[contains(@id,'Key_BM_String')]");
	private By masterObjectDropDown = By.xpath("//select[contains(@id,'BM_String_WL')]");
	private By inputDateField = By.xpath("//input[contains(@id,'Key_BM_Date')]");
	private By chooseFileButton = By.xpath("//input[@type='file' and contains(@id,'fileUpload')]");
	private By docIDNewPageIframe = By.xpath("(//*[@id='ContentPlaceHolder1_iPage'])[1]");
	private By createNewItemsPageIframe = By.xpath("(//*[@id='ContentPlaceHolder1_iPage'])[last()]");
	private By mainContainerIframe = By.id("mainContainerFrame");
	private By WMIIframe = By.xpath("(//*[contains(@id,'ifrmOpenWMI')])[1]");
	private By favoritesLink = By.xpath("//*[contains(text(),'Favorites')]");
	private By addToFavoritesLink = By.xpath("//*[contains(text(),'Add to Favorites')]");
	private By removeFromFavoritesLink = By.xpath("//*[contains(text(),'Remove from Favorites')]");
	private By infoMessage = By.id("messageLabel");
	private By uploadedDocumentPageIframe = By.id("westContainerFrame");
	private By uploadedDocumentPageInfo = By
			.xpath("//*[contains(@id,'EPMMultiPageViewer1') and contains(@id,'PageNum')]");
	private By customerInformationButton = By
			.xpath("//span[@class='ui-button-text' and contains(text(),'Customer Information')]");
	private By WMIHarnessTabs = By
			.xpath("(//*[contains(@id,'TabContainer') and contains(@class,'tab_header')])[2]//a/span");

	private By saveButton_MainPage = By.xpath("//a[@role='menuitem']//*[contains(text(),'Save')]");
	private By firstDocIdColumnValue_InlineNewTab = By.xpath(
			"((//table[contains(@id,'TabRef_1')]//table[contains(@class,'GVGrid')]//tr[contains(@class,'GV') and not(@class='GVHeader')])//td[7])[1]");
	private By newButton_InlineNewTab = By.xpath("//span[@class='ui-button-text' and text()='New']");
	private By BMIntegerTextField_InlineNewTab = By
			.xpath("//*[contains(@id,'TabRef_1')]//input[contains(@id,'BM_Int')]");
	private By saveButton_InlineNewTab = By
			.xpath("//*[contains(@id,'TabRef_1')]//input[@type='submit' and @value='Save']");
	private By firstBMIntColumnValue_InlineNewTab = By.xpath(
			"((//table[contains(@id,'TabRef_1')]//table[contains(@class,'GVGrid')]//tr[contains(@class,'GV') and not(@class='GVHeader')])//td[15])[1]");

	private By InlineNewWithDragDropFunctionalityTab = By
			.xpath("//span[contains(@id,'TabRef_2')]//a[contains(@id,'TabRef_2')]/span");

	private By newButton_InlineNewWithDragDropFunctionalityTab = By
			.xpath("//*[contains(@id,'TabRef_2')]//span[@class='ui-button-text' and text()='New']");
	private By multipageViewerOption_InlineNewWithDragDropFunctionalityTab = By
			.xpath("//a[@id='ui-id-2' and text()='MultipageViewer with drag and drop']");
	private By BMStringTextField_InlineNewWithDragDropFunctionalityTab = By
			.xpath("(//*[contains(@id,'TabRef_2')]//input[contains(@id,'BM_String')])[1]");
	private By firstDocIdColumnValue_InlineNewWithDragDropFunctionalityTab = By.xpath(
			"((//table[contains(@id,'TabRef_2')]//table[@class='GVGrid']//tr[contains(@class,'GV') and not(@class='GVHeader')])//td[7])[1]");
	private By firstBMStringColumnValue_InlineNewWithDragDropFunctionalityTab = By.xpath(
			"((//table[contains(@id,'TabRef_2')]//table[@class='GVGrid']//tr[contains(@class,'GV') and not(@class='GVHeader')])//td[12])[1]");
	private By dropFileHereButton_InlineNewWithDragDropFunctionalityTab = By
			.xpath("//*[contains(@id,'TabRef_2')]//*[contains(text(),'Drop file here')]");
	private By saveButton_InlineNewWithDragDropFunctionalityTab = By
			.xpath("//*[contains(@id,'TabRef_2')]//input[@type='submit' and @value='Save']");
	private By firstRowDocIcon_InlineNewWithDragDropFunctionalityTab = By.xpath(
			"((//table[contains(@id,'TabRef_2')]//table[contains(@class,'GVGrid')]//tr[contains(@class,'GV') and not(@class='GVHeader')])//td[5])[1]//img");

	private By informationMessageText = By.xpath("//span[@class='FieldVal']");
	private By newLinkDefaultTab = By.xpath("//a/span[text()='New link(Default)']");
	private By categoryMenuTab = By.xpath("//a/span[text()='CategoryMenu']");
	private By integerFieldTextField = By.xpath("//*[text()='Integer Field']/following-sibling::input");
	private By saveButton = By.xpath("//*[@class='ui-button-text' and contains(text(),'Save')]");
	private By closeWindowButton = By.xpath("//*[@class='ui-button-text' and contains(text(),'Close Window')]");

	private By newLink_CategoryMenuTab = By
			.xpath("//table[contains(@id,'TabRef_5')]//span[@class='ui-button-text' and text()='New']");
	private By othersLink_CategoryMenuTab = By.xpath("//table[contains(@id,'TabRef_5')]//a[text()='Others']");
	private By moreLink_CategoryMenuTab = By.xpath("//table[contains(@id,'TabRef_5')]//a[contains(text(),'More')]");
	private By renderAllFieldsTypeLink_CategoryMenuTab = By
			.xpath("//table[contains(@id,'TabRef_5')]//a[contains(text(),'Render All Field Types')]");
	private By informationMessageText_CategoryMenuTab = By.xpath("(//span[@class='FieldVal'])[last()]");

	private By headerTitleText = By.xpath("//*[@class='headerTitle']/span");

	private By integerFieldLabel = By.xpath("//span[@class='FieldLabel' and contains(@id,'BM_Int')]");
	private By stringFieldLabel = By.xpath("(//span[@class='FieldLabel' and contains(@id,'BM_String')])[1]");
	private By stringFieldWithLookupLabel = By.xpath("//span[@class='FieldLabel' and contains(@id,'BM_String_WL')]");
	private By stringFieldWithLookupDropdown = By.xpath("//select[contains(@id,'BM_String_WL')]");
	private By currencyTextfield = By.xpath("//input[@class='inputclass' and contains(@id,'BM_Currency')]");
	private By bodyframe = By.xpath("//*[@id='eform_mcb67676_phBO_3_BO_eidmKey_Template_Text_ifr']");
	private By saveDocLink = By.linkText("Save");
	private By saveDocLinkOfCorrespondance = By.xpath("//span[@class='ui-button-text' and contains(text(),'Save')]");
	private By closeDocLink = By.linkText("Close");
	private By deletelink = By.linkText("Delete");
	private By bodyTextField = By.xpath("//*[@id='tinymce']");
	private By infoDeleteMessage = By.xpath("(//span[contains(text(),'Are you sure?')])[2]");
	private By yesButton = By.xpath("(//input[@name='btnNo']/preceding-sibling::input[@name='ctl25'])[2]");
	private By templateNameTextField = By.xpath("//input[@name='eform_mcb67676$phBO_3_BO$eidmKey_Template_Name']");
	private By selectTemplate = By.xpath("//select[contains(@id,'Template')]");
	
	public NewDocPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		commonUtil = new CommonUtil();
	}

	/**
	 * This method is used to Switch to NewDocPage Screen frame
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage switchToPageFrame() {
		eleUtil.switchToFrameIfExists(this.mainContainerIframe);
		eleUtil.switchToFrame(this.docIDNewPageIframe, 10);
		return this;
	}

	/**
	 * This method is used to Switch to MainContainer frame on NewDocPage
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage switchToMainContainerFrame() {
		eleUtil.switchToFrameIfExists(this.mainContainerIframe);
		return this;
	}

	/**
	 * This method is used to Switch to Uploaded Document(Child) frame on NewDocPage
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage switchToUploadedDocumentFrame() {
		eleUtil.switchToFrameIfExists(this.uploadedDocumentPageIframe);
		return this;
	}

	/**
	 * This method is used to Switch to WMI(Child) frame on NewDocPage
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage switchToWMIFrame() {
		// eleUtil.switchToFrameIfExists(this.WMIIframe);
		eleUtil.switchToFrame(this.WMIIframe);
		return this;
	}

	/**
	 * This method is used to Switch to NewDocPage Screen frame
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage switchToCreateNewItemsPageFrame() {
		eleUtil.switchToFrame(this.createNewItemsPageIframe, 10);
		return this;
	}

	/**
	 * This method is used to get the Info Message on NewDocPage screen
	 *
	 * @return This will return the value of Info Message in String Format
	 */
	public String getInfoMessage() {
		this.switchToPageFrame();
		String value = eleUtil.doGetText(this.infoMessage);
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to click on Action Link and then Save link on NewDocPage
	 * screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage navigateAndClickToSaveLink() {
		this.switchToPageFrame();
		eleUtil.moveToElementAndClick(this.actionLink, this.saveLink);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Action Link and then AddToFavourites link on
	 * NewDocPage screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage navigateAndClickOnAddToFavouritesLink() {
		eleUtil.waitForElementPresence(this.mainContainerIframe, 20);
		this.switchToMainContainerFrame();
		eleUtil.waitForElementPresence(this.favoritesLink, 20);
		eleUtil.moveToElementAndClick(this.favoritesLink, this.addToFavoritesLink);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Favourites Link and then Remove From
	 * Favourites link on NewDocPage screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage navigateAndClickOnRemoveFromFavouritesLink() {
		eleUtil.waitForElementPresence(this.mainContainerIframe, 20);
		this.switchToMainContainerFrame();
		eleUtil.waitForElementPresence(this.favoritesLink, 20);
		eleUtil.moveToElementAndClick(this.favoritesLink, this.removeFromFavoritesLink);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter Customer Details textfield
	 *
	 * @param customerDetails Value which need to provide in Customer Details
	 *                        textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterCustomerDetails(String customerDetails) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.customerDetailsTextField, customerDetails);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter Customer Name textfield
	 *
	 * @param customerName Value which need to provide in Customer Name textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterCustomerName(String customerName) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.customerNameTextField, customerName);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter Customer First Name textfield
	 *
	 * @param customerFirstName Value which need to provide in Customer First Name
	 *                          textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterCustomerFirstName(String customerFirstName) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.customerFirstNameTextField, customerFirstName);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter Customer last Name textfield
	 *
	 * @param customerFirstName Value which need to provide in Customer Last Name
	 *                          textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterCustomerLasttName(String customerLastName) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.customerLastNameTextField, customerLastName);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter Body text textfield
	 * 
	 * @param Body Value which we need to provide Body textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterBodyText(String bodyText) {
		this.switchToPageFrame();
		eleUtil.switchToFrame(this.bodyframe, 2);
		eleUtil.doSendKeys(this.BodyTextField, bodyText);
		eleUtil.switchToDefaultContent();
		return this;
	}

 	/** 
	 * This method is used to enter String textfield
	 *
	 * @param stringFieldValue Value which need to provide in StringField textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterStringField(String stringFieldValue) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.stringFieldTextField, stringFieldValue);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to upload File by taking File by name from
	 * ./resources/Documents
	 *
	 * @param fileName FileName which need to upload
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage uploadFile(String fileName) {
		String filePath = commonUtil.getResourceDocumentPath(fileName);
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.chooseFileButton, filePath);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get existed StringField value
	 *
	 * @return This will return the populated StringField value in String Format
	 */
	public String returnStringFieldValue() {
		eleUtil.wait(5);
		// eleUtil.waitForElementPresence(this.uploadedDocumentPageIframe, 20);
		this.switchToPageFrame();
		eleUtil.wait(5);
		eleUtil.waitForElementPresence(this.stringFieldTextField, 20);
		String value = eleUtil.getAttributeValue(this.stringFieldTextField, "value");
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to switch to DocIdNew Page Screen and get existed
	 * StringField value
	 *
	 * @return This will return the populated StringField value in String Format
	 */
	public String getStringFieldValue() {
		eleUtil.wait(5);
		this.switchToDocIDNewPageScreen();
		return this.returnStringFieldValue();
	}

	/**
	 * This method is used to switch to Main Page Screen
	 *
	 * @return This will return the Object of MainPage class
	 */
	public MainPage switchToMainPageScreen() {
		Set<String> allWindows = this.driver.getWindowHandles();
		eleUtil.switchToWindow((allWindows.toArray(new String[allWindows.size()]))[0]);
		return new MainPage(this.driver);
	}

	/**
	 * This method is used to switch to Main Page's WorkItems Page Screen
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen switchToWorkItemsScreen() {
		Set<String> allWindows = this.driver.getWindowHandles();
		eleUtil.switchToWindow((allWindows.toArray(new String[allWindows.size()]))[0]);
		return new WorkItemsScreen(this.driver);
	}

	/**
	 * This method is used to get Uploaded Document PDF page info
	 *
	 * @return This will return the Uploaded Document PDF page info in String Format
	 */
	public String getUploadedDocPageInfo() {
		this.switchToUploadedDocumentFrame();
		eleUtil.waitForElementPresence(this.uploadedDocumentPageInfo, 30);
		String value = eleUtil.doGetText(this.uploadedDocumentPageInfo);
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to switch to New Doc Page Screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage switchToDocIDNewPageScreen() {
		eleUtil.switchToWindow(1);
		eleUtil.waitMethod();
		return this;
	}

	/**
	 * This method is used to click on Save Link screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickToSaveLink() {
		eleUtil.switchToFrameIfExists(this.mainContainerIframe);
		eleUtil.switchToFrameIfExists(this.docIDNewPageIframe);
		eleUtil.doClick(this.saveDocLink);
		eleUtil.switchToDefaultContent();
		return this;
	}
	
	public NewDocPage clickToSaveLinkOfCorrespondenceDoc() {
		this.switchToPageFrame();
		eleUtil.doClick(this.saveDocLinkOfCorrespondance);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get existed CustomerNameTextField value
	 *
	 * @return This will return the populated CustomerNameTextField value in String
	 *         Format
	 */
	public String getCustomerNameFieldValue() {
		this.switchToPageFrame();
		String value = eleUtil.getAttributeValue(this.customerNameTextField, "value");
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to click on Customer Information Link on NewDocPage
	 * screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnCustomerInformationLink() {
		eleUtil.switchToDefaultContent();
		eleUtil.waitForElementToBeClickable(this.customerInformationButton,10);
		eleUtil.doClick(this.customerInformationButton);
		return this;
	}

	/**
	 * This method is used get the names of all WMI Harness Tabs
	 *
	 * @return This will return the list of WMI Harness Tabs list in List<String>
	 *         format
	 */
	public List<String> getWMIHarnessTabs() {
		this.switchToPageFrame();
		List<WebElement> allTabs = eleUtil.getElements(this.WMIHarnessTabs);
		List<String> allTabNames = new ArrayList<String>();
		for (WebElement ele : allTabs) {
			allTabNames.add(eleUtil.doGetText(ele).trim());
		}
		eleUtil.switchToDefaultContent();
		return allTabNames;
	}

	/**
	 * This method is used to verify the existence of WMI Harness Tab
	 * 
	 * @param tabName tabName which needs to check whether exists or not
	 * @return This will return the list of WMI Harness Tabs list in List<String>
	 *         format
	 */
	public boolean verifyExistenceOfWMIHarnessTabs(String tabName) {
		return this.getWMIHarnessTabs().contains(tabName);
	}

	/**
	 * This method is used to click on Page Save button
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnSaveButton() {
		this.switchToPageFrame();
		eleUtil.doClick(this.saveButton_MainPage);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get the first available DocId from the table in Inline
	 * New Tab
	 * 
	 * @return This will return the first available DocId in String format
	 */
	public String getFirstAvailableDocIdValueInInlineNewTab() {
		this.switchToPageFrame();
		String value = eleUtil.doGetText(this.firstDocIdColumnValue_InlineNewTab);
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to click on New button in Inline New Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnNewButtonInInlineNewTab() {
		this.switchToPageFrame();
		eleUtil.doClick(this.newButton_InlineNewTab);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter the value in BM Integer textfield in Inline New
	 * Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterValueInBMIntegerTextFieldInInlineNewTab(String value) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.BMIntegerTextField_InlineNewTab, value);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Save button in Inline New Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnSaveButtonInInlineNewTab() {
		this.switchToPageFrame();
		eleUtil.doClick(this.saveButton_InlineNewTab);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get the first available BMInt column value in Inline
	 * New Tab
	 * 
	 * @return This will return the first available BMInt in String format
	 */
	public String getFirstAvailableBMIntValueInInlineNewTab() {
		this.switchToPageFrame();
		String value = eleUtil.doGetText(this.firstBMIntColumnValue_InlineNewTab);
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to click on Inline New With Drag Drop Functionality Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnInlineNewWithDragDropFunctionalityTab() {
		this.switchToPageFrame();
		eleUtil.doClick(this.InlineNewWithDragDropFunctionalityTab);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on New Link and then Multipage Viewer With
	 * DragAndDrop link on Inline New With Drag Drop Functionality Tab
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage navigateAndClickToMultipageViewerWithDragAndDropLinkInInlineNewWithDragDropFunctionalityTab() {
		this.switchToPageFrame();
		eleUtil.moveToElementAndClick(this.newButton_InlineNewWithDragDropFunctionalityTab,
				this.multipageViewerOption_InlineNewWithDragDropFunctionalityTab);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get the first available BMString column value in
	 * InlineNew With DragDrop Functionality Tab
	 * 
	 * @return This will return the first available BMString in String format
	 */
	public String getFirstAvailableBMStringValueInlineNewWithDragDropFunctionalityTab() {
		this.switchToPageFrame();
		String value = eleUtil.doGetText(this.firstBMStringColumnValue_InlineNewWithDragDropFunctionalityTab);
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to get the first available DocId column value in
	 * InlineNew With DragDrop Functionality Tab
	 * 
	 * @return This will return the first available DocId in String format
	 */
	public String getFirstAvailableDocIdValueInlineNewWithDragDropFunctionalityTab() {
		this.switchToPageFrame();
		String value = eleUtil.doGetText(this.firstDocIdColumnValue_InlineNewWithDragDropFunctionalityTab);
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to enter the value in BM String textfield in Inline New
	 * With Drag Drop Functionality Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterValueInBMStringTextFieldInInlineNewWithDragDropFunctionalityTab(String value) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.BMStringTextField_InlineNewWithDragDropFunctionalityTab, value);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to upload File in Inline New With DragDrop Functionality
	 * Tab by taking File by name from ./resources/Documents
	 *
	 * @param fileName FileName which need to upload
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage uploadFileInInlineNewWithDragDropFunctionalityTab(String fileName) {
		String filePath = commonUtil.getResourceDocumentPath(fileName);
		this.switchToPageFrame();
		eleUtil.doClick(this.dropFileHereButton_InlineNewWithDragDropFunctionalityTab);
		try {
			Runtime.getRuntime().exec(filePath);
			eleUtil.wait(15);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Save button in in Inline New With DragDrop
	 * Functionality Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnSaveButtonInInlineNewWithDragDropFunctionalityTab() {
		this.switchToPageFrame();
		jsUtil.clickElementByJS(eleUtil.getElement(this.saveButton_InlineNewWithDragDropFunctionalityTab));
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to create document in Inline New tab
	 * 
	 *
	 * @param BMIntValue value which need to be enter in Integer textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage createDocInInlineNewTab(String BMIntValue) {
		this.clickOnSaveButton();
		this.clickOnNewButtonInInlineNewTab();
		this.enterValueInBMIntegerTextFieldInInlineNewTab(BMIntValue);
		this.clickOnSaveButtonInInlineNewTab();
		eleUtil.wait(5);
		return this;
	}

	/**
	 * This method is used to create document in Inline New tab
	 * 
	 *
	 * @param BMIntValue value which need to be enter in Integer textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage createDocInInlineNewWithDragDropFunctionalityTab(String BMStringValue, String fileName) {
		this.navigateAndClickToMultipageViewerWithDragAndDropLinkInInlineNewWithDragDropFunctionalityTab();
		this.enterValueInBMStringTextFieldInInlineNewWithDragDropFunctionalityTab(BMStringValue);
		eleUtil.wait(5);
		this.uploadFileInInlineNewWithDragDropFunctionalityTab(fileName);
		this.clickOnSaveButtonInInlineNewWithDragDropFunctionalityTab();
		eleUtil.wait(5);
		return this;
	}

	/**
	 * This method is used to create document in Inline New tab
	 * 
	 *
	 * 
	 * @return This will return the existence status of PDF Icon
	 */
	public boolean verifyPDFIconForFirstRow() {
		this.switchToPageFrame();
		boolean iconDetail = eleUtil
				.getAttributeValue(this.firstRowDocIcon_InlineNewWithDragDropFunctionalityTab, "src").contains("PDF");
		eleUtil.switchToDefaultContent();
		return iconDetail;
	}

	/**
	 * This method is used to get Information Message value
	 * 
	 *
	 * 
	 * @return This will return the Information Message text
	 */
	public String getInformationMessageText() {
		this.switchToPageFrame();
		String message = eleUtil.doGetText(this.informationMessageText);
		eleUtil.switchToDefaultContent();
		return message;
	}

	/**
	 * This method is used to click on New Link Default Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnNewLinkDefaultTab() {
		this.switchToPageFrame();
		eleUtil.doClick(this.newLinkDefaultTab);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on New Link Default Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnCategoryMenuTab() {
		this.switchToPageFrame();
		eleUtil.doClick(this.categoryMenuTab);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter value in Integer Field textfield
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterIntegerFieldTextField(String value) {
		this.switchToPageFrame();
		this.switchToWMIFrame();
		this.switchToCreateNewItemsPageFrame();
		eleUtil.doSendKeys(this.integerFieldTextField, value);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on New Link Default Tab
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnCreateNewItemScreenSaveButton() {
		this.switchToPageFrame();
		this.switchToWMIFrame();
		this.switchToCreateNewItemsPageFrame();
		eleUtil.doClick(this.saveButton);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Close Window button
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnCloseWindowButton() {
		this.switchToPageFrame();
		this.switchToWMIFrame();
		eleUtil.doClick(this.closeWindowButton);
		eleUtil.switchToDefaultContent();
		return this;
	}
	
	public NewDocPage clickOnCloseWindowBtnOfCorrespondenceDoc() {
		this.switchToMainContainerFrame();
		eleUtil.doClick(this.closeWindowButton);
		return this;
	}

	/**
	 * This method is used to click on Close Window button
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnCloseWindowButtonOnNewDocPage() {
		eleUtil.doClick(this.closeWindowButton);
		return this;
	}

	/**
	 * This method is used to get Input Field value
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public String getIntegerFieldValue() {
		eleUtil.wait(10);
		this.switchToPageFrame();
		String value = eleUtil.getAttributeValue(this.integerFieldTextField, "value");
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to click on Action Link and then Render All Fields Type
	 * link on NewDocPage screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage navigateAndClickToRenderAllFieldsTypeLink() {
		this.switchToPageFrame();
		eleUtil.wait(5);
		eleUtil.moveToElementAndClick(this.newLink_CategoryMenuTab, this.othersLink_CategoryMenuTab,
				this.moreLink_CategoryMenuTab, this.renderAllFieldsTypeLink_CategoryMenuTab);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get Information Message value for CategoryMenuTab
	 * 
	 *
	 * 
	 * @return This will return the Information Message text
	 */
	public String getInformationMessageTextForCategoryMenuTab() {
		this.switchToPageFrame();
		this.switchToWMIFrame();
		this.switchToCreateNewItemsPageFrame();
		String message = eleUtil.doGetText(this.informationMessageText_CategoryMenuTab);
		eleUtil.switchToDefaultContent();
		return message;
	}

	/**
	 * This method is used to get Header Title Text
	 * 
	 *
	 * 
	 * @return This will return the Header Title text
	 */
	public String getHeaderTitleText() {
		this.switchToPageFrame();
		String message = eleUtil.doGetText(this.headerTitleText);
		eleUtil.switchToDefaultContent();
		return message;
	}

	/**
	 * This method is used to get Integer Field Label
	 * 
	 *
	 * 
	 * @return This will return the Integer Field Label text
	 */
	public String getIntegerFieldLabel() {
		this.switchToPageFrame();
		String message = eleUtil.doGetText(this.integerFieldLabel);
		eleUtil.switchToDefaultContent();
		return message;
	}

	/**
	 * This method is used to get String Field Label
	 * 
	 *
	 * 
	 * @return This will return the String Field Label text
	 */
	public String getStringFieldLabel() {
		this.switchToPageFrame();
		String message = eleUtil.doGetText(this.stringFieldLabel);
		eleUtil.switchToDefaultContent();
		return message;
	}

	/**
	 * This method is used to get String Field With Lookup Label
	 * 
	 *
	 * 
	 * @return This will return the String Field With Lookup Label text
	 */
	public String getStringFieldWithLookupLabel() {
		this.switchToPageFrame();
		String message = eleUtil.doGetText(this.stringFieldWithLookupLabel);
		eleUtil.switchToDefaultContent();
		return message;
	}

	/**
	 * This method is used to verify the Existence of value in String Field With
	 * Lookup Dropdown
	 *
	 * @param value Value which we need to check in String Field With Lookup
	 *              Dropdown
	 * @return This will return the true if value is exist in String Field With
	 *         Lookup Dropdown
	 */
	public boolean verifyExistenceOfValueInStringFieldWithLookupDropdown(String value) {
		this.switchToPageFrame();
		if (eleUtil.getAllOptionsForDropdown(this.stringFieldWithLookupDropdown).contains(value)) {
			eleUtil.switchToDefaultContent();
			return true;
		}
		eleUtil.switchToDefaultContent();
		return false;
	}

	/**
	 * This method is used to enter value in Currency Field
	 * 
	 * @return This will return object of class NewDocPage
	 */
	public NewDocPage enterValueInCurrencyField(String value) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.currencyTextfield, value);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to enter String template Name
	 *
	 * @param templateName Value which need to provide in Template Name textfield
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterTemplateName(String templateName) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.templateNameTextField, templateName);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to update String template Name and Text
	 *
	 * @param templateName Value which need to provide in Template Name textfield
	 * @param templateText Value which need to provide in Template textfield
	 * @param windowName   Value which need to provide to switch that window
	 * @return This will return WorkItemsScreen class object
	 */
	public WorkItemsScreen updateTemplateName(String templateName, String templateText, String windowName) {
		this.switchToPageFrame();
		eleUtil.doClear(this.templateNameTextField);
		eleUtil.doSendKeys(this.templateNameTextField, templateName);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		eleUtil.switchToFrame(this.bodyframe, 2);
		eleUtil.doClear(this.bodyTextField);
		eleUtil.doSendKeys(this.bodyTextField, templateText);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		this.clickToSaveLink();
		eleUtil.doClick(this.closeDocLink);
		eleUtil.switchToWindow(windowName);
		driver.switchTo().defaultContent();
		return new WorkItemsScreen(driver);
	}

	/**
	 * This method is used to delete Template Document
	 *
	 * @param currentWindowName Value in String need to provide to switch that
	 *                          window
	 */
	public void deleteTemplateDoc(String currentWindowName) {
		this.switchToDocIDNewPageScreen();
		eleUtil.waitForElementPresence(this.docIDNewPageIframe, 20);
		this.switchToPageFrame();
		eleUtil.doClick(this.deletelink);
		eleUtil.waitForElementPresence(this.infoDeleteMessage, 20);
		try {
			String deleteMessage = eleUtil.doGetText(this.infoDeleteMessage);
			if (deleteMessage.contains("You are about to delete this document! Are you sure?")) {
				ExtentReportListener.test.get().log(Status.PASS,
						"Verification of message on delete prompt is Successful");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.doClick(this.yesButton);
		driver.switchTo().defaultContent();
		eleUtil.switchToWindow(currentWindowName);
	}

	/**
	 * This method is used to switch to Home Page Screen
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	public HomeScreen switchToHomePageScreen() {
		eleUtil.switchToWindow(0);
		return new HomeScreen(this.driver);
	}
	
	/**
	 * This method is used to switch to MultipageViewer class and
	 * switch to New doc page screen
	 *
	 * @return This will return the Object of MultipageViewer class
	 */
	public MultipageViewer switchToMultipageViewerClass() {
		this.switchToDocIDNewPageScreen();
		return new MultipageViewer(this.driver);
	}
	
	/**
	 * This method is used to select Template option
	 *
	 * @param docClass     value for Doc Class dropdown
	 * @param documentType value for Document Type dropdown
	 * @return This will return the Object of MainPage class
	 */
	public NewDocPage selectTemplateOption(String templateOption) {
		try {
			this.switchToPageFrame();
			eleUtil.doDropDownSelectByVisibleText(this.selectTemplate, templateOption);
			eleUtil.switchToDefaultContent();
			ExtentReportListener.test.get().log(Status.PASS, "Template Option selected successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while selected Template option");
			Assert.fail(e.getMessage());
		}
		return this;
	}
	
	/** 
	 * This method is used to enter current date in date field
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage enterCurrentDate() {
		String dateFormat = "MM-dd-yyyy";
		String currentDate = commonUtil.getCurrentDateTime(dateFormat);
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.inputDateField, currentDate);
		eleUtil.switchToDefaultContent();
		return this;
	}
	
	/**
	 * This method is used to select value from Master Object dropdown Control in
	 * Event For Required Field Doc
	 *
	 * @param value for Master Object dropdown
	 * @return This will return the Object of NewDocPage Class
	 */
	public NewDocPage selectMasterObject(String value) {
		try {
			this.switchToPageFrame();
			eleUtil.doDropDownSelectByVisibleText(this.masterObjectDropDown, value);
			eleUtil.switchToDefaultContent();
			eleUtil.wait(2);
			ExtentReportListener.test.get().log(Status.INFO, "Selected Doc Type " + value + " successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while selecting Doc Type" + value + "");
			Assert.fail(e.getMessage());
		}
		return this;
	}
}