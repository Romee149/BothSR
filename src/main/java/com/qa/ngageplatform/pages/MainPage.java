package com.qa.ngageplatform.pages;

import java.util.Set;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to Main Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class MainPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// ****************** Locators ****************** //
	private By docNameDropdown = By.id("DDLDocClasses");
	private By docTypeDropdown = By.id("DDLDocTypes");
	private By newButton = By.id("btnGlobalNew");
	private By okButton = By.id("btnsave");
	private By createNewIFrame = By.id("ifrmCreateNew");
	private By createNewItemPopupCloseButton = By
			.xpath("//button[contains(@class,'ui-dialog-titlebar-close') and @title='Close']");
	private By myWorkLink = By.xpath("//a[text()='My Work']");
	private By myWorkSimplifiedLink = By.xpath("//a[text()='My Work Simplified']");
	private By reportsLink = By.xpath("//a[text()='Reports']");
	private By repositoryLink = By.xpath("//a[text()='Repository']");
	private By integerInputfiled = By.xpath("//input[contains(@id,'eidmKey_BM_Int')]");
	private By masterObjectIFrame = By.id("ContentPlaceHolder1_iPage");
	private By stringInputField = By.xpath("//input[@id='eform_mcb67676_phBO_3_BO_eidmKey_BM_String']");
	private By selectValueField = By.xpath("//select[@id='eform_mcb67676_phBO_3_BO_eidmKey_BM_String_WL']");
	private By currencyInputField = By.xpath("//input[contains(@name,'BM_Currency')]");
	private By dateInputField = By.xpath("//input[@name='eform_mcb67676$phBO_3_BO$eidmKey_BM_Date']");
	private By dateTimeInputField = By.xpath("//input[contains(@name,'BM_DateTime')]");
	private By floatInputField = By.xpath("//input[contains(@name,'BM_Float')]");
	private By smallIntInputField = By.xpath("//input[contains(@name,'Small_Int')]");
	private By bmDateInputField = By.id("eform_mcb67676_FieldKey_BM_Date");
	private By dateRangeInputField = By.id("eform_mcb67676_FieldKey_Date_range");
	private By dateTimeField = By.id("eform_mcb67676_FieldKey_BM_DateTime");
	private By dateframe = By.id("['divpropcontrols']/table");
	private By newDocIFrame = By.id("ContentPlaceHolder1_iPage");
	private By intInputForMutiValue = By.xpath("//input[contains(@id,'EPM_BM_Int_MV')]");
	private By stringInputForMutiValue = By.xpath("//input[contains(@id,'EPM_BM_String_MV') and @tabindex='2']");
	private By intAddForMutiValue = By.xpath("//input[contains(@id, 'btnAdd_BM_Int_MV') and @type='submit']");
	private By saveBtnForCorrespondence = By.xpath("//span[contains(text(), 'Save')]");
	private By closeBtnForCorrespondence = By.xpath("//span[contains(text(), 'Close Window')]");
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to get HomeScreen Page Object
	 *
	 * @return This will return the Object of HomePage class Object
	 */
	public HomeScreen getHomeDocumentsListPage() {
		return new HomeScreen(this.driver);
	}

	/**
	 * This method is used to click on New button
	 *
	 * @return This will return the Object of MainPage class
	 */
	public MainPage clickNewButton() {
		try {
			eleUtil.doClick(this.newButton);
			ExtentReportListener.test.get().log(Status.INFO, "Clicked on \'New\' button successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'New\' button");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to select value from DocClass dropdown in CreateNewItem
	 * popup
	 *
	 * @param docClass value for DocClass dropdown
	 * @return This will return the Object of MainPage class
	 */
	public MainPage selectDocClass(String docClass) {
		try {
			eleUtil.switchToFrame(this.createNewIFrame);
			eleUtil.doDropDownSelectByVisibleText(this.docNameDropdown, docClass);
			eleUtil.switchToDefaultContent();
			ExtentReportListener.test.get().log(Status.INFO, "Selected Doc Class \"" + docClass + "\" successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while selecting Doc Class \"" + docClass + "\"");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to select value from Document Type dropdown in
	 * CreateNewItem popup
	 *
	 * @param documentType value for Document Type dropdown
	 * @return This will return the Object of MainPage class
	 */
	public MainPage selectDocType(String documentType) {
		try {
			eleUtil.switchToFrame(this.createNewIFrame);
			eleUtil.doDropDownSelectByVisibleText(this.docTypeDropdown, documentType);
			eleUtil.switchToDefaultContent();
			ExtentReportListener.test.get().log(Status.INFO, "Selected Doc Type \"" + documentType + "\" successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while selecting Doc Type \"" + documentType + "\"");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on OK button in CreateNewItem popup
	 *
	 * @return This will return the Object of MainPage class
	 */
	public MainPage clickOkButton() {
		try {
			eleUtil.switchToFrame(this.createNewIFrame);
			eleUtil.waitForElementPresence(this.okButton, 20);
			eleUtil.doClick(this.okButton);
			eleUtil.switchToDefaultContent();
			ExtentReportListener.test.get().log(Status.INFO, "Clicked on \"OK\" button successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \"OK\" button");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to close CreateNewItem popup
	 *
	 * @return This will return the Object of MainPage class
	 */
	public MainPage closeCreateNewItemPopup() {
		try {
			eleUtil.doClick(this.createNewItemPopupCloseButton);
			ExtentReportListener.test.get().log(Status.INFO, "Closed \"Create New Item\" Popup successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while closing \"Create New Item\" Popup");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	// ************************************************ //
	// ************** Navigation Actions ************** //
	// ************************************************ //

	/**
	 * This method is used to click on MyWork Tab
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen clickOnMyWorkTab() {
		try {
			eleUtil.waitForElementPresence(this.myWorkLink, 20);
			eleUtil.doClick(this.myWorkLink);
			ExtentReportListener.test.get().log(Status.INFO, "Clicked on \'My Work\' tab link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'My Work\' tab link");
			Assert.fail(e.getMessage());
		}
		return new MyWorkScreen(driver);
	}

	/**
	 * This method is used to switch to DocIDNew Page Screen
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage switchToDocIDNewPageScreen() {
		try {
			Set<String> allWindows = this.driver.getWindowHandles();
			eleUtil.switchToWindow((allWindows.toArray(new String[allWindows.size()]))[1]);
			ExtentReportListener.test.get().log(Status.INFO, "Switching to \"DocID-New\" Screen successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while switching to \"DocID-New\" Screen");
			Assert.fail(e.getMessage());
		}
		return new NewDocPage(this.driver);
	}

	// ************************************************//
	// ************ Complete Actions/Flows ************//
	// ************************************************//

	/**
	 * This method is used to Create New Item
	 *
	 * @param docClass     value for Doc Class dropdown
	 * @param documentType value for Document Type dropdown
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewItem(String docClass, String documentType) {
		try {
			this.clickNewButton();
			this.selectDocClass(docClass);
			this.selectDocType(documentType);
			this.clickOkButton();
			ExtentReportListener.test.get().log(Status.PASS, "New Item created successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while creating New Item");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to Create New Item with Customer Details
	 *
	 * @param docClass        value for Doc Class dropdown
	 * @param docType         value for Document Type dropdown
	 * @param customerDetails value to enter in Customer Details textfield
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithCustomerDetails(String docClass, String docType, String customerDetails) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.enterCustomerDetails(customerDetails);
		docIdNewPage.navigateAndClickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	/**
	 * This method is used to Create New Item with Customer Details and Customer
	 * Name
	 *
	 * @param docClass        value for Doc Class dropdown
	 * @param docType         value for Document Type dropdown
	 * @param customerDetails value to enter in Customer Details textfield
	 * @param customerName    value to enter in Customer Name textfield
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithCustomerDetails(String docClass, String docType, String customerDetails,
			String customerName) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.enterCustomerDetails(customerDetails);
		docIdNewPage.enterCustomerName(customerName);
		docIdNewPage.navigateAndClickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	/**
	 * This method is used to Create New Item with stringField Value
	 *
	 * @param docClass         value for Doc Class dropdown
	 * @param docType          value for Document Type dropdown
	 * @param stringFieldValue value to enter in StringField textfield
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithStringField(String docClass, String docType, String stringFieldValue) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.enterStringField(stringFieldValue);
		docIdNewPage.navigateAndClickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	/**
	 * This method is used to Create New Item with stringField Value and Upload File
	 * path value
	 *
	 * @param docClass         value for Doc Class dropdown
	 * @param docType          value for Document Type dropdown
	 * @param stringFieldValue value to enter in StringField textfield
	 * @param uploadFileName   FilePath to enter in Upload File textfield
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithStringField(String docClass, String docType, String stringFieldValue,
			String uploadFileName) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.enterStringField(stringFieldValue);
		docIdNewPage.uploadFile(uploadFileName);
		docIdNewPage.navigateAndClickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	/**
	 * This method is used to Create New Item and switch to Doc ID New Screen
	 *
	 * @param docClass value for Doc Class dropdown
	 * @param docType  value for Document Type dropdown
	 * 
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage createNewDocumentAndSwitchToDocIDNewPage(String docClass, String docType) {
		this.createNewItem(docClass, docType);
		return this.switchToDocIDNewPageScreen();
	}

	/**
	 * This method is used to switch to Main Page's Home Page Screen
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	public HomeScreen switchToHomePageScreen() {
		return new HomeScreen(this.driver);
	}

	/**
	 * This method is used to switch to Main Page's Home Page Screen
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	public MainPage reloadMainPage() {
		eleUtil.refreshPage();
		eleUtil.wait(5);
		return this;
	}

	/**
	 * Created by Romee This method is used to Create New Item with Customer First
	 * Name, Last Name and Body Text
	 *
	 * @param docClass          value for Doc Class dropdown
	 * @param docType           value for Document Type dropdown
	 * @param customerFirstName value to enter in Customer First Name textfield
	 * @param customerLastName  value to enter in Customer Last Name textfield
	 * @param BodyText          value to enter in BodyText textfield
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithCustomerNameAndBodyText(String docClass, String docType,
		String customerFirstName, String customerLastName, String bodyText) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.enterCustomerFirstName(customerFirstName);
		docIdNewPage.enterCustomerLasttName(customerLastName);
		docIdNewPage.enterBodyText(bodyText);
		docIdNewPage.clickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	/**
	 * This method is used to click on MyWorkSimplified Tab
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnMyWorkSimplifiedTab() {
		try {
			eleUtil.waitForElementPresence(myWorkSimplifiedLink, 50);
			eleUtil.doClick(this.myWorkSimplifiedLink);
			eleUtil.wait(5);
			ExtentReportListener.test.get().log(Status.INFO, "Clicked on \'My Work Simplified\' tab link successfully");

		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'My Work Simplified\' tab link");
			Assert.fail(e.getMessage());
		}
		return new MyWorkSimplifiedPage(driver);
	}

	/**
	 * This method is used to click on Report Tab
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public ReportPage clickOnReportTab() {
		try {
			eleUtil.waitForElementPresence(this.reportsLink, 50);
			eleUtil.doClick(this.reportsLink);
			eleUtil.wait(5);
			ExtentReportListener.test.get().log(Status.INFO, "Clicked on \'Report\' tab link successfully");

		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Report\' tab link");
			Assert.fail(e.getMessage());
		}
		return new ReportPage(driver);
	}

	/**
	 * This method is used to click on Repository Tab
	 *
	 * @return This will return the Object of RepositoryPage class
	 */
	public RepositoryPage clickOnRepositoryTab() {
		try {
			eleUtil.waitForElementPresence(this.repositoryLink, 50);
			eleUtil.doClick(this.repositoryLink);
			eleUtil.wait(5);
			ExtentReportListener.test.get().log(Status.PASS, "Clicked on \'Repository\' tab link successfully");

		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Repository\' tab link");
			Assert.fail(e.getMessage());
		}
		return new RepositoryPage(driver);
	}

	/**
	 * This method is used to Create New Item for Master Object Feature
	 *
	 * @param docClass     value for Doc Class dropdown
	 * @param docType      value for Document Type dropdown
	 * @param date         date to enter Date Input field
	 * @param customerName date and time to enter Date time input field
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithMasterObject(String docClass, String docType, String date, String dateTime) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		eleUtil.switchToFrameIfExists(this.masterObjectIFrame);

		eleUtil.doSendKeys(this.integerInputfiled, "111");
		eleUtil.doSendKeys(this.stringInputField, "RepositoryTest");
		eleUtil.doSendKeys(this.floatInputField, "100.123");
		eleUtil.doSendKeys(this.currencyInputField, "$500");
		eleUtil.doSendKeys(this.smallIntInputField, "$500");
		eleUtil.doSendKeys(this.dateInputField, date);
		eleUtil.doSendKeys(this.dateTimeInputField, dateTime);
		docIdNewPage.clickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.switchToHomePageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	/**
	 * This method is used to Create New Item with BM Date, Date Range and BM
	 * DateTime Name
	 *
	 * @param docClass   value for Doc Class dropdown
	 * @param docType    value for Document Type dropdown
	 * @param bmDate     date to enter in BM Date field
	 * @param dateRange  date to enter in date range date field
	 * @param bmDateTime date to enter in date range date field
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDateTimeDoc(String docClass, String docType, String bmDate, String dateRange,
			String bmDateTime) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.switchToDocIDNewPageScreen();
		eleUtil.switchToFrameIfExists(this.newDocIFrame);
		eleUtil.switchToFrameIfExists(this.dateframe);
		eleUtil.doSendKeys(this.bmDateInputField, bmDate);
		eleUtil.doSendKeys(this.dateRangeInputField, dateRange);
		eleUtil.doSendKeys(this.dateTimeField, bmDateTime);
		docIdNewPage.clickToSaveLink();
		docIdNewPage.switchToHomePageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	/**
	 * This method is used to Create New Item with Customer First Name, Last Name
	 * and Body Text
	 *
	 * @param docClass     value for Doc Class dropdown
	 * @param docType      value for Document Type dropdown
	 * @param templateName value to enter in Template Name textfield
	 * @param BodyText     value to enter in BodyText textfield
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithtemplateNameAndtemplateBodyText(String docClass, String docType,
			String templateName, String bodyText) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.enterTemplateName(templateName);
		docIdNewPage.enterBodyText(bodyText);
		docIdNewPage.switchToPageFrame();
		docIdNewPage.clickToSaveLink();
		docIdNewPage.switchToHomePageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

	public MainPage validateNewDocumentExist() {
		NewDocPage newDocScreen = this.getHomeDocumentsListPage().sortDocumentListByDocID().getNewDocPage();
		String getTtile = this.getHomeDocumentsListPage().getFirstDocTypeValue();
		if (!getTtile.contains("WMI Menu BOV Vertical")) {
			this.createNewDocumentWithStringField("WMI Menu", "WMI Menu BOV Vertical", "MyTest", "P8.pdf");
			newDocScreen = this.getHomeDocumentsListPage().sortDocumentListByDocID().clickFirstAvailableDocType();
		}

		return this;

	}

	/**
	 * This method is used to Create New Item for Master Object Feature
	 *
	 * @param docClass value for Doc Class dropdown
	 * @param docType  value for Document Type dropdown
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithMasterObject(String docClass, String docType) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		eleUtil.switchToFrameIfExists(this.masterObjectIFrame);
		eleUtil.doSendKeys(this.intInputForMutiValue, "123");
		eleUtil.doClick(this.intAddForMutiValue);
		eleUtil.doSendKeys(this.intInputForMutiValue, "4566");
		eleUtil.doSendKeys(this.stringInputForMutiValue, "test");
		docIdNewPage.clickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.switchToHomePageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}
	
	/**
	 * This method is used to Create New Item with Customer First
	 * Name, Last Name and Template
	 *
	 * @param docClass          value for Doc Class dropdown
	 * @param docType           value for Document Type dropdown
	 * @param customerFirstName value to enter in Customer First Name textfield
	 * @param customerLastName  value to enter in Customer Last Name textfield
	 * @param selectTemplate    value for Select Template dropdown
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithCustomerNameAndTemplate(String docClass, String docType,
			String customerFirstName, String customerLastName, String selectTemplate) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.enterCustomerFirstName(customerFirstName);
		docIdNewPage.enterCustomerLasttName(customerLastName);
		docIdNewPage.selectTemplateOption(selectTemplate);
		docIdNewPage.clickToSaveLink();
		eleUtil.switchToWindow(1);
		eleUtil.wait(5);
		docIdNewPage.clickToSaveLinkOfCorrespondenceDoc();
		docIdNewPage.clickOnCloseWindowBtnOfCorrespondenceDoc();
		eleUtil.switchToWindow(0);
		this.closeCreateNewItemPopup();
		return this;
	}
	
	/**
	 * This method is used to Create New Item with Customer First
	 * Name, Last Name and Body Text
	 *
	 * @param docClass          value for Doc Class dropdown
	 * @param docType           value for Document Type dropdown
	 * @param customerFirstName value to enter in Customer First Name textfield
	 * @param customerLastName  value to enter in Customer Last Name textfield
	 * @param BodyText          value to enter in BodyText textfield
	 * @return This will return the Object of MainPage class
	 */
	public MainPage createNewDocumentWithMasterObjectStringFieldAndDate(String docClass, String docType,
		String masterObjectValue, String stringFieldValue) {
		this.createNewItem(docClass, docType);
		NewDocPage docIdNewPage = this.switchToDocIDNewPageScreen();
		docIdNewPage.selectMasterObject(masterObjectValue);
		docIdNewPage.enterStringField(stringFieldValue);
		docIdNewPage.enterCurrentDate();
		docIdNewPage.navigateAndClickToSaveLink();
		docIdNewPage.switchToMainPageScreen();
		this.closeCreateNewItemPopup();
		return this;
	}

}
