package com.qa.ngageplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.ElementUtil;

import io.qameta.allure.Step;

public class ReportPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By securityManagementLink = By.xpath("//a[contains(text(), 'Security Management')]");
	private By userListingLink = By.xpath("//a[@id='GROUP_CATEGORY_REPORT_38_anchor']");
	private By lineChart = By.xpath("//*[local-name()='svg' and @class='highcharts-root']");
	private By systemManagementLink = By.xpath("//a[contains(text(), 'System Management')]");
	private By systemHealthLink = By.xpath("//a[contains(text(), 'System Health')]");
	private By allWorkItemsErrorLink = By.xpath("//a[contains(text(), 'All Work Items in Error')]");
	private By reportUserListingFrame = By.id("GROUP_CATEGORY_REPORT_38_iframe");
	private By reportAllWorkItemErrorFrame = By.id("GROUP_CATEGORY_REPORT_1002_iframe");
	private By reportFrame = By.id("iframe_108");
	private By userGroupListingLink = By.xpath("//a[@id='GROUP_CATEGORY_REPORT_50_anchor']");
	private By reportUserGroupListingFrame = By.id("GROUP_CATEGORY_REPORT_50_iframe");
	private By showFilters =By.xpath("//a[contains(text(),'Show Filters')]");
	private By updateResult =By.xpath("//a[contains(text(),'Update Results')]");	
	private By filterField = By.xpath("//*[@id='ORept_Explorer_Filters_Column']");
	private By filterFieldGroupListing = By.xpath("(//*[@id='ORept_Explorer_Filters_Column'])");
	private By operatorGroupListing = By.xpath("(//*[@id='ORept_Explorer_Filters_Operator'])");
	private By valueGroupListing = By.xpath("(//input[@id='ORept_Explorer_Filters_Edit1'])");
	private By reportResultId = By.xpath("//tr[@class='ReportItem']//td[1]");

	public ReportPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to land User Listing Page from Report to Security
	 * Management links
	 *
	 * @return This will return the Object of Report class Object
	 */
	@Step("Landing on User Listing page")
	public ReportPage goToUserListingPage() {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(this.securityManagementLink);
		action.doubleClick(element).perform();
		eleUtil.waitForElementPresence(this.userListingLink, 50);
		eleUtil.doClick(this.userListingLink);
		eleUtil.waitForElementPresence(this.reportFrame, 50);
		return this;
	}

	/**
	 * This method is used to land All Work Item in Error from Report to Sytem
	 * Management to Sytem Health links
	 *
	 * @return This will return the Object of Report class Object
	 */
	@Step("Landing on All WorkItem Error Page")
	public ReportPage goToAllWorkItemErrorPage() {
		eleUtil.doClick(this.systemManagementLink);
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(this.systemHealthLink);
		action.doubleClick(element).perform();
		eleUtil.waitForElementPresence(this.allWorkItemsErrorLink, 50);
		eleUtil.doClick(this.allWorkItemsErrorLink);
		eleUtil.waitForElementPresence(this.reportFrame, 50);
		return this;
	}

	/**
	 * This method is used to get line Graph's line count for user listing report
	 *
	 * @return This will return the line Graph's line Count in int Format for user
	 *         listing report
	 */
	@Step("Get user listing line graph count")
	public int getUserListinglineGraphCount() {
		int count = 0;
		try {
			eleUtil.switchToFrameIfExists(this.reportFrame, 40);
			eleUtil.switchToFrame(this.reportUserListingFrame, 50);
			eleUtil.waitForElementPresence(this.lineChart, 50);
			count = eleUtil.getAllOptionsOfElement(this.lineChart).size();
			ExtentReportListener.test.get().log(Status.INFO, "Fetching line Graph line Count successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching line Graph line Count");
			Assert.fail(e.getMessage());
		}
		return count;
	}

	/**
	 * This method is used to get line Graph's line count for All Work Item in Error
	 * report
	 *
	 * @return This will return the line Graph's line Count in int Format for All
	 *         Work Item in Error
	 */
	@Step("Get Work Item Error line graph count")
	public int getWorkItemErrorlineGraphCount() {
		int count = 0;
		try {
			eleUtil.switchToFrameIfExists(this.reportFrame, 40);
			eleUtil.switchToFrame(this.reportAllWorkItemErrorFrame, 50);
			eleUtil.waitForElementPresence(this.lineChart, 50);
			count = eleUtil.getAllOptionsOfElement(this.lineChart).size();
			ExtentReportListener.test.get().log(Status.INFO, "Fetching line Graph line Count successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching line Graph line Count");
			Assert.fail(e.getMessage());
		}
		return count;
	}
	
	/**
	 * This method is used to land User Group Listing Page from Report to Security
	 * Management links
	 *
	 * @return This will return the Object of Report class Object
	 */
	@Step("Landinig on User Group Listing Page")
	public ReportPage goToUserGroupListingPage() {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(this.securityManagementLink);
		action.doubleClick(element).perform();
		eleUtil.waitForElementPresence(this.userGroupListingLink, 50);
		eleUtil.doClick(this.userGroupListingLink);
		eleUtil.waitForElementPresence(this.reportFrame, 50);
		return this;
	}

	/**
	 * This method is used to get Graph's line after updated result by Created By
	 * report
	 *
	 * @return This will return the line Graph's line Count in int Format 
	 */
	@Step("Get User Listing Udated Result Created By")
	public int getUserListingUdatedResultCreatedBy() {
		int count = 0;
		try {
			eleUtil.switchToFrameIfExists(this.reportFrame, 20);
			eleUtil.switchToFrame(this.reportUserListingFrame, 40);
			eleUtil.doClick(this.showFilters);
			eleUtil.doDropDownSelectByVisibleText(this.filterField, "Created By");
			eleUtil.doClick(this.updateResult);
			eleUtil.waitForElementPresence(this.lineChart, 40);
			count = eleUtil.getAllOptionsOfElement(this.lineChart).size();
			ExtentReportListener.test.get().log(Status.PASS, "Fetching line Graph line Count successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching line Graph line Count");
			Assert.fail(e.getMessage());
		}
		return count;
	}
	/**
	 * This method is used to get user group listing update result by selecting
	 * filter field>User ID and operator>Equals and input user id as input text
	 *
	 * @return This will return result in string of user id info from output result
	 */
	@Step("Verify update result by user Id for user group listing")
	public String verifyUpdateResultByUserIdForUserGroupListing() {
			eleUtil.switchToFrameIfExists(this.reportFrame, 20);
			eleUtil.switchToFrame(this.reportUserGroupListingFrame, 40);
			
			eleUtil.doDropDownSelectByVisibleText(this.filterFieldGroupListing, "User ID");
			eleUtil.doDropDownSelectByVisibleText(this.operatorGroupListing, "Equals");
			eleUtil.doSendKeys(this.valueGroupListing, "100002");
			eleUtil.doClick(this.updateResult);
			eleUtil.wait(2);
			eleUtil.waitForElementPresence(this.reportResultId, 20);
			String result = eleUtil.doGetText(this.reportResultId);
			eleUtil.switchToDefaultContent();
			return result;
	}
}