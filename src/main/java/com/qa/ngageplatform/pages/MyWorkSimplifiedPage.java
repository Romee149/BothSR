package com.qa.ngageplatform.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;

public class MyWorkSimplifiedPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	CommonUtil comUtil = new CommonUtil();
	WorkItemsScreen workItemsScreen;
	private By activityIframe = By.id("iframe_110");
	private By sDropdown = By.xpath("//select[@id='ddActivities']");
	private By cActivityA = By.xpath("//select[@id='ddActivities']/child::option[@value='100027']");
	private By docIdColumnHead = By.xpath("//div/parent::th[@title='Doc ID']");
	private By docIdBeforeSwitch = By.xpath("//*[@id='SimplifiedWorkflowSearchResultsTable']/tbody/tr[2]/td[7]");
	private By docIdAfterSwitch = By.xpath("//*[@id='SimplifiedWorkflowSearchResultsTable']/tbody/tr[2]/td[8]");
	private By docIdData1st = By.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID'])[1]");
	private By docIdData2nd = By.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID'])[2]");
	private By headSearch = By.xpath("//h3[@id='ui-id-1']");
	private By paginationInfoLabel = By.xpath(
			"//td[contains(@id,'SimplifiedWorkflowSearchResultsTable_toppager_right')]//*[@class='ui-paging-info']");
	private By inputDocID = By.xpath("//input[@id='-100']");
	private By searchDoc = By.xpath("//input[@value='Search']");

	private By pageNoInfo = By.xpath("//div[@class='ui-paging-info']");
	private By docID = By.xpath("//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID']");
	private By docStartDate = By
			.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc Create Date'])[1]");
	private By processDueDate = By
			.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Process Due Date'])[1]");
	private By docIdHead = By.xpath("//div[contains(text(),'Doc ID')]");
	private By docIdWithAscOrder = By.xpath("//th[contains(@id,'Doc ID')]//span[@sort='desc' and contains(@class,'ui-state-disabled')]");
	private By docIdWithDescOrder = By.xpath("//th[contains(@id,'Doc ID')]//span[@sort='asc' and contains(@class,'ui-state-disabled')]");
	private By selectAllIcon = By.id("cb_SimplifiedWorkflowSearchResultsTable");
	private By openAllSelectedIcon = By.xpath("//span[@class='ui-icon ui-icon-folder-open']");

	private By customerInformation = By.xpath("//span[contains(text(),'Customer Information')]/parent::a");
	private By newWindowIframe = By.xpath("//iframe[@id='ContentPlaceHolder1_iPage']");
	private By customerNameInfo = By.xpath("//*[@name='eform_mcb67676$phBO_3_BO$eidmKey_Customer_Name']");
	private By customerDetailsInfo = By.xpath("//input[@name='eform_mcb67676$phBO_3_BO$eidmKey_Customer_details']");

	private By inputPageNumber = By.xpath("//input[@class='ui-pg-input']");
	private By getNextItem = By.xpath("//input[@name='btnGetNext']");

	private By processDueDateColumnHeader = By
			.xpath("//th[@id='SimplifiedWorkflowSearchResultsTable_Process Due Date']");
	private By activityText = By.xpath("//td[contains(text(),'Activities')]");
	private By resetBtn = By.xpath("//input[@value='Reset']");
	private By customerNameHead = By.xpath("//*[@id='SimplifiedWorkflowSearchResultsTable']/tbody/tr[1]/td[8]");
	private By resetLayoutIcon = By.xpath("//*[@id='SimplifiedWorkflowSearchResultsTable_btnResetLayout']");
	private By setLayoutIcon = By.xpath("//*[@id='SimplifiedWorkflowSearchResultsTable_btnSetLayout']");
	private By refreshIcon = By.xpath("//span[@class='ui-icon ui-icon-refresh']");

	public MyWorkSimplifiedPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		eleUtil.switchToDefaultContent();

	}

	/**
	 * This method is used to Switch to MyWorkSimplified Screen frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage switchToPageFrame() {
		eleUtil.switchToFrameIfExists(this.activityIframe, 20);
		return this;
	}

	/**
	 * This method is used to sendKeys for MyWorkSimplified Screen frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage doSendKeysMWS(By InputLocator, String value) {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(InputLocator, 10);
		eleUtil.doClear(InputLocator);
		eleUtil.doSendKeys(InputLocator, value);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to verify Search Head Bar is displayed in
	 * MyWorkSimplified Screen frame
	 * 
	 * @return This will return true if Search Head Bar is displayed
	 */
	public boolean isSearchHeadDisplay() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.headSearch, 50);
		boolean searchDisplay = eleUtil.isDisplay(this.headSearch);
		eleUtil.switchToDefaultContent();
		return searchDisplay;
	}

	/**
	 * This method is used to select Activity Closure Action - Activity A from Drop
	 * down
	 *
	 * @return This will return the Object of WorkItemScreen class Object
	 */
	public WorkItemsScreen selectCloserActionActivityA(String value) {
		try {
			this.switchToPageFrame();
			eleUtil.doSelectDropDownValue(this.sDropdown, value);
			eleUtil.switchToDefaultContent();
			eleUtil.wait(5);
			ExtentReportListener.test.get().log(Status.INFO, "Selected Activities \"" + value + "\" successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while selecting Activities \"" + value + "\"");
			Assert.fail(e.getMessage());
		}
		return new WorkItemsScreen(this.driver);

	}

	/**
	 * This method is used to Get Current selected Activity
	 *
	 * @return This will return the selected activity text
	 */
	public String getActivityText() {
		String activityInfo = null;
		try {

			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.cActivityA, 50);
			activityInfo = eleUtil.doGetText(this.cActivityA);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching activities Info \'" + activityInfo + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching activities Info");
			Assert.fail(e.getMessage());
		}
		return activityInfo;
	}

	/**
	 * This method is used to click on SearchHeadBar for MyWorkSimplified Screen
	 * frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage clickOnHeadSearch() {
		eleUtil.doClick(this.headSearch);
		return this;
	}

	/**
	 * This method is used to double click on DocID Header for MyWorkSimplified
	 * Screen frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage doubleClickonDocIdHeader() {
		Actions action = new Actions(driver);
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.docIdColumnHead, 50);
		WebElement element = driver.findElement(this.docIdColumnHead);
		action.doubleClick(element).perform();
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get first DocID from search result
	 *
	 * @return This will return the first DocID
	 */
	public String getFirstDocID() {
		String docID = eleUtil.doGetText(this.docIdData1st);
		return docID;
	}

	/**
	 * This method is used to enter DocID value to be searched & click on search
	 * button
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage enterDocIDAndSearch() {
		workItemsScreen.clickOnSearchHeaderBar();
		eleUtil.doSendKeys(this.inputDocID, getFirstDocID());
		eleUtil.doClick(this.searchDoc);
		return this;
	}

	/**
	 * This method is used to verify Search Result grid contains 1 record only
	 *
	 * @return This will return the page Information text
	 */
	public String verifyRecordNumber1() {
		String pageInfo = null;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(pageNoInfo, 10);
			eleUtil.wait(2);
			pageInfo = eleUtil.doGetText(this.pageNoInfo);
			ExtentReportListener.test.get().log(Status.INFO, "Fetching page Info \'" + pageInfo + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching page Info");
			Assert.fail(e.getMessage());
		}
		return pageInfo;
	}

	/**
	 * This method is used to get Doc Id from search result page
	 *
	 * @return This will return the Doc Id of search result
	 */
	public String getResultDocId() {
		String actualResultDocId = eleUtil.doGetText(this.docID);
		return actualResultDocId;
	}

	/**
	 * This method is used to click on Process Due Date Column Header
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnProcessDueDateColumnHeader() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.processDueDateColumnHeader, 20);
		eleUtil.doClick(this.processDueDateColumnHeader);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to sort record by descending order
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage sortDocIdDescending() {
		this.switchToPageFrame();
		eleUtil.doClick(this.refreshIcon);
		eleUtil.wait(2);
		eleUtil.doDoubleClick(docIdHead);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to verify Records in table are more than start date
	 *
	 * @return This will return true if Process Due Date Start and End both are
	 *         greater than the created date of doc
	 */
	public boolean verifyProcessDueDateGreater() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.docStartDate, 10);
		String docSDate = eleUtil.doGetText(docStartDate);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		String pDueDate = eleUtil.doGetText(processDueDate);
		eleUtil.switchToDefaultContent();
		boolean verifydate = comUtil.verifyFirstDateIsGreater(pDueDate, docSDate, "MM-dd-yyyy hh:mm:ss a");
		return verifydate;
	}

	/**
	 * This method is used to enter Recent Doc ID and Search
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage enterRecentDocIDAndSearch(String latestdocId) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.inputDocID, latestdocId);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		eleUtil.doClick(this.searchDoc);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click On Select All Icon
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnAllIconSelectCheckbox() {
		this.switchToPageFrame();
		eleUtil.doClick(selectAllIcon);
		eleUtil.switchToDefaultContent();

		return this;
	}

	/**
	 * This method is used to click on Open All Selected Icon
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnOpenAllSelectedIcon() {
		this.switchToPageFrame();
		eleUtil.doClick(openAllSelectedIcon);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Customer Information tab after land the new
	 * window
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnCustomerInformation() {
		eleUtil.switchToWindow(1);
		eleUtil.waitForElementPresence(this.customerInformation, 60);
		eleUtil.doClick(this.customerInformation);
		eleUtil.wait(5);
		return this;
	}

	/**
	 * This method is used to get customer name info after switch child window
	 *
	 * @return This will return the customer name
	 */
	public String getCustomerNameInfo() {
		eleUtil.switchToFrameIfExists(this.newWindowIframe);
		eleUtil.waitForElementPresence(this.customerNameInfo, 50);
		String customerNInfo = driver.findElement(this.customerNameInfo).getAttribute("value");
		return customerNInfo;

	}

	/**
	 * This method is used to verify search section is hidden
	 *
	 * @return This will return true if search result is hidden
	 */
	public Boolean verifySearchSectionHidden() {
		Boolean result = false;

		try {
			this.switchToPageFrame();
			String ariaExpandCondition = eleUtil.getAttributeValue(this.headSearch, "aria-expanded");
			if (ariaExpandCondition.contains("false")) {
				result = true;
			}
			ExtentReportListener.test.get().log(Status.INFO,
					"Search section is hidden \'" + result + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Search section is not hidden");
			Assert.fail(e.getMessage());
		}

		return result;

	}

	/**
	 * This method is used to get customer details information
	 *
	 * @return This will return customer details info
	 */
	public String getCustomerDetailInfo() {
		eleUtil.waitForElementPresence(this.customerDetailsInfo, 50);
		String customerDInfo = driver.findElement(this.customerDetailsInfo).getAttribute("value");
		return customerDInfo;
	}

	/**
	 * This method is used to put value 3 in input feild of page and press enter
	 *
	 * @return This will return the object of 3 page
	 */
	public void enterPageNumberAndPressEnterKey() {
		this.doSendKeysMWS(inputPageNumber, "3");
		eleUtil.pressEnterKey();
		eleUtil.waitMethod();
	}

	/**
	 * This method is used to page information in text
	 *
	 * @return This will return the page info
	 */
	public String getPageInfo() {
		this.switchToPageFrame();
		String pInfo = eleUtil.doGetText(this.pageNoInfo);
		eleUtil.switchToDefaultContent();
		return pInfo;
	}

	/**
	 * This method is used to verify record sorted by Ascending order
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public boolean verifyRecordSortedAscOrder() {
		int counter =0;
		this.switchToPageFrame();
		eleUtil.doClick(this.refreshIcon);
		eleUtil.waitForElementToBeClickable(this.docIdHead, 50);
		while(!eleUtil.isElementExist(docIdWithAscOrder) && counter<3) {
			eleUtil.doClick(docIdHead);
			counter++;
		}
		eleUtil.wait(1);
		String firstDocId = eleUtil.doGetText(this.docIdData1st);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.docIdData2nd, 60);
		String secondDocId = eleUtil.doGetText(this.docIdData2nd);
		eleUtil.switchToDefaultContent();
		int firstDocIdNum = comUtil.convertStringToNumber(firstDocId);
		int secondDocIdNum = comUtil.convertStringToNumber(secondDocId);
		if (secondDocIdNum > firstDocIdNum) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method is used to verify record sorted by descending order
	 *
	 * @return This will return result in boolean format
	 */
	public boolean verifyRecordSortedDscOrder() {
		this.sortDocIdDescending();
		eleUtil.wait(2);
		this.switchToPageFrame();
		String firstDocId = eleUtil.doGetText(this.docIdData1st);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		String secondDocId = eleUtil.doGetText(this.docIdData2nd);
		eleUtil.switchToDefaultContent();
		int firstDocIdNum = comUtil.convertStringToNumber(firstDocId);
		int secondDocIdNum = comUtil.convertStringToNumber(secondDocId);
		if (firstDocIdNum > secondDocIdNum) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to check 'Get Next Item' is exist or not for closure
	 * Action activity option
	 *
	 * @return This will return the true if 'Get Next Item' is displayed
	 */
	public boolean getNextItemDisplayed() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.getNextItem, 50);
		boolean nextItemDisplay = eleUtil.isDisplay(this.getNextItem);
		eleUtil.switchToDefaultContent();
		if (nextItemDisplay = true) {
			System.out.println("Get Next Item is displayed");
			ExtentReportListener.test.get().log(Status.PASS, "Verification of Get Next Item is displayed successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of Get Next Item is not displayed.");
		}

		return nextItemDisplay;
	}

	/**
	 * This method is used to check 'Get Next Item' is exist or not for Corresponse
	 * option
	 *
	 * @return This will return the true if 'Get Next Item' is not displayed
	 */
	public boolean selectCorrespondenceGenerationAndVerifyGetNextItemNotDisplayed() {
		String correspond = "Correspondence Generation - Correspondence";
		boolean flag = false;
		this.switchToPageFrame();
		eleUtil.doSelectDropDownValue(this.sDropdown, correspond);
		eleUtil.waitForElementToBeClickable(this.paginationInfoLabel, 50);
		eleUtil.switchToDefaultContent();
		if (driver.getPageSource().contains("Get Next Item") == false) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * This method is used to verify Activities Text is displayed in
	 * MyWorkSimplified Screen frame
	 * 
	 * @return This will return true if Activities Text is displayed
	 */
	public boolean isActivitiesTextDisplay() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.activityText, 50);
		boolean activitiesDisplay = eleUtil.isDisplay(this.activityText);
		eleUtil.switchToDefaultContent();
		return activitiesDisplay;
	}

	/**
	 * This method is used to verify search and reset buttons are displayed in
	 * MyWorkSimplified Screen frame
	 * 
	 * @return This will return true if search and reset buttons are displayed
	 */
	public boolean isSearchAndResetBtnDisplayed() {
		boolean result = false;
		this.switchToPageFrame();
		this.clickOnHeadSearch();
		boolean search = eleUtil.isDisplay(this.searchDoc);
		boolean reset = eleUtil.isDisplay(this.resetBtn);
		if (search & reset == true)
			result = true;
		eleUtil.switchToDefaultContent();
		return result;

	}

	/**
	 * This method is used to Get No. of Activities in total
	 *
	 * @return This will return total number of activity count in String
	 */
	public String getNumberOfActiviesInTotal() {
		this.switchToPageFrame();
		String pageInfo = eleUtil.doGetText(this.paginationInfoLabel);
		String[] arrOfStr = pageInfo.split("of");
		String lastDigitC = arrOfStr[1].trim();
		eleUtil.switchToDefaultContent();
		return lastDigitC;
	}

	/**
	 * This method is used to Get No. of rows count showing in page
	 *
	 * @return This will return number of rows count in integer showing in page
	 */
	public int getNumberOfActiviesInPage() {
		this.switchToPageFrame();
		String pageInfo = eleUtil.doGetText(this.paginationInfoLabel);
		String arrOfStr[] = pageInfo.split("of");
		String firstPart = arrOfStr[0];
		String arOfStr[] = firstPart.split("-");
		String lastDigitC = arOfStr[1];
		int rowsCount = Integer.parseInt(lastDigitC.trim());
		eleUtil.switchToDefaultContent();
		return rowsCount;
	}

	/**
	 * This method is used to verify Set Layout Icons work properly
	 *
	 * @return This will return result in boolean format
	 */
	public boolean verificationOfSetLayout() {
		this.switchToPageFrame();
		eleUtil.doClick(this.resetLayoutIcon);
		eleUtil.wait(3);
		eleUtil.waitForElementPresence(this.docIdBeforeSwitch, 50);
		String getDocIdBefore = eleUtil.doGetText(this.docIdBeforeSwitch);
		eleUtil.dragAndDrop(this.docIdColumnHead, this.customerNameHead);
		eleUtil.doClick(this.setLayoutIcon);
		eleUtil.wait(3);
		String getDocIdAfter = eleUtil.doGetText(this.docIdAfterSwitch);
		if (getDocIdBefore.contentEquals(getDocIdAfter)) {
			return true;
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of columns are not switched");
			return false;
		}}
	
	/**
	 * This method is used to verify Reset Layout Icons work properly
	 *
	 * @return This will return result in boolean format
	 */
	public boolean verificationOfResetLayout() {
		String getDocIdBefore = eleUtil.doGetText(this.docIdAfterSwitch);
		eleUtil.doClick(this.resetLayoutIcon);
		eleUtil.wait(3);
		String getDocIdAfterReset = eleUtil.doGetText(this.docIdBeforeSwitch);
		if (getDocIdBefore.contentEquals(getDocIdAfterReset)) {
			ExtentReportListener.test.get().log(Status.INFO, "Verification of column positon are reset successfully");
			eleUtil.switchToDefaultContent();
			return true;
		} else {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Verification of column positon are not reset successfully");
			return false;
		}
		
	}
	
	public void verificationOfPageSummary() {
		String rowsCount = String.valueOf(getNumberOfActiviesInPage());
		this.switchToPageFrame();
		String pageSummary = eleUtil.doGetText(this.paginationInfoLabel);
		System.out.println(rowsCount);
		String expPageSummary1 = "Showing 1 - 999";
		String expPageSummary2 = "Showing 1 - " + rowsCount + " of " + this.getNumberOfActiviesInTotal();
		if (this.getNumberOfActiviesInPage() > 999) {
			AssertionUtil.verifyContainsText(pageSummary, expPageSummary1,
					"Verification of page summary contains 'Showing 1 - 999' is successfully");
		} else {
			AssertionUtil.verifyEqual(pageSummary, expPageSummary2,
					"Verification of page summary is successfull when row counts less than 999");
		}
		eleUtil.switchToDefaultContent();
	}

}
