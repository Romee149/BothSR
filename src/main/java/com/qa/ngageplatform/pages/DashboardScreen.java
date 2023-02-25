package com.qa.ngageplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to Dashboard Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class DashboardScreen {

    private WebDriver driver;
    private ElementUtil eleUtil;

    // ****************** Locators ****************** //
    private By assignmentButton = By.xpath("//a[@data-key='ASIGNEE']");
    private By activityEventStateButton = By.xpath("//a[@data-key='ACTEVENTSTATE']");    	
    private By newVsInProcessButton = By.xpath("//a[@data-key='NEWVSINPROC']");
    private By activityDueButton = By.xpath("//label[@for='AssignedDueType']/span");
    private By processDueButton = By.xpath("//label[@for='ProcessDueType']/span");
    private By assignmentDashboardGraphBar = By.xpath("(//*[local-name()='g' and contains(@class,'nv-bar positive')]//*[local-name()='rect' and @width>'200'])[1]");    
    private By workItemTableRows = By.xpath("//table[@id='MyWorkSearchResultsTable']//tr");
    private By dashboardVerticalGraphBar = By.xpath("(//*[local-name()='rect' and @class='nv-bar positive' and @height>'20'])[1]");
    
    private By mainIFrame = By.id("iframe_105");
    private By dashboardIFrame = By.xpath("//iframe[@class='tabpanel-iframe' and contains(@src,'ActivityDashboard')]");
    private By assignmentIFrame = By.id("ASIGNEE_iframe");
    private By activityEventStateIFrame = By.id("ACTEVENTSTATE_iframe");
    private By newVsInProcessIFrame = By.id("NEWVSINPROC_iframe");
    private By workItemIFrame = By.id("iframeBAMActivityWorkItems");
    
    
    

    public DashboardScreen(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    
    /**
     * This method is used to Switch to Dashboard Screen Main frame
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen switchToMainFrame() {
        eleUtil.waitForElementPresence(this.mainIFrame, 20);
        eleUtil.switchToFrame(this.mainIFrame, 20);
        return this;
    }
    

    /**
     * This method is used to Switch to Dashboard Screen's Dashboard frame
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen switchToDashboardFrame() {
    	this.switchToMainFrame();
        eleUtil.waitForElementPresence(this.dashboardIFrame, 20);
        eleUtil.switchToFrame(this.dashboardIFrame, 20);
        return this;
    }
    
    
    /**
     * This method is used to Switch to Dashboard Screen Assignment frame
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen switchToAssignmentFrame() {
    	this.switchToDashboardFrame();
        eleUtil.waitForElementPresence(this.assignmentIFrame, 20);
        eleUtil.switchToFrame(this.assignmentIFrame, 20);
        return this;
    }
    
    
    /**
     * This method is used to Switch to Dashboard Screen activity Event State frame
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen switchToActivityEventStateFrame() {
    	this.switchToDashboardFrame();
        eleUtil.waitForElementPresence(this.activityEventStateIFrame, 20);
        eleUtil.switchToFrame(this.activityEventStateIFrame, 20);
        return this;
    }
    
    /**
     * This method is used to Switch to Dashboard Screen NewVsInprocess frame
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen switchToNewVsInprocessFrame() {
    	this.switchToDashboardFrame();
        eleUtil.waitForElementPresence(this.newVsInProcessIFrame, 20);
        eleUtil.switchToFrame(this.newVsInProcessIFrame, 20);
        return this;
    }
   

    /**
     * This method is used to Switch to Dashboard Screen WorkItem frame
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen switchToWorkItemFrame() {
        eleUtil.waitForElementPresence(this.workItemIFrame, 20);
        eleUtil.switchToFrame(this.workItemIFrame, 20);
        return this;
    }

   
    /**
     * This method is used to get the Activity button label
     *
     * @return This will return the Activity button label in String Format
     */
    public String getActivityDueButtonLabel() {
        String buttonLabel = null;
        try {
            this.switchToAssignmentFrame();
            eleUtil.waitForElementPresence(this.activityDueButton, 20);
            buttonLabel = eleUtil.doGetText(this.activityDueButton);
            ExtentReportListener.test.get().log(Status.INFO,
                    "Getting Activity Due button label successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while getting Activity Due button label");
            Assert.fail(e.getMessage());
        }
        return buttonLabel;
    }
    
    
    /**
     * This method is used to get the Process Due button label
     *
     * @return This will return the Process Due button label in String Format
     */
    public String getProcessDueButtonLabel() {
        String buttonLabel = null;
        try {
            this.switchToAssignmentFrame();
            eleUtil.waitForElementPresence(this.processDueButton, 20);
            buttonLabel = eleUtil.doGetText(this.processDueButton);
            ExtentReportListener.test.get().log(Status.INFO,
                    "Getting Process Due button label successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while getting Process Due button label");
            Assert.fail(e.getMessage());
        }
        return buttonLabel;
    }
    
    


    /**
     * This method is used to click on Assignment Tab
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen clickOnAssignmentTab() {
        try {
            this.switchToDashboardFrame();
            eleUtil.doClick(this.assignmentButton);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Assignment\' tab successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Assignment\' tab");
            Assert.fail(e.getMessage());
        }
        return this;
    }
    
    
    
    /**
     * This method is used to click on Activity Event State Tab
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen clickOnActivityEventStateTab() {
        try {
            this.switchToDashboardFrame();
            eleUtil.doClick(this.activityEventStateButton);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Activity Event State\' tab successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Activity Event State\' tab");
            Assert.fail(e.getMessage());
        }
        return this;
    }
    
    /**
     * This method is used to click on NewVsInprocess Tab
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen clickOnNewVsInprocessTab() {
        try {
            this.switchToDashboardFrame();
            eleUtil.doClick(this.newVsInProcessButton);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'NewVsInProcess\' tab successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'NewVsInProcess\' tab");
            Assert.fail(e.getMessage());
        }
        return this;
    }
    
    /**
     * This method is used to click on Assignment Dashboard Graph Bar
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen clickOnAssignmentDashboardGraphBar() {
        try {
            this.switchToAssignmentFrame();        
            eleUtil.doClick(this.assignmentDashboardGraphBar);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Assignment Dashboard Graph Bar\' successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Assignment Dashboard Graph Bar\'");
            Assert.fail(e.getMessage());
        }
        return this;
    }

   
    /**
     * This method is used to click on Activity Event State Dashboard Graph Bar
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen clickOnActivityEventStateVerticalDashboardGraphBar() {
        try {
            this.switchToActivityEventStateFrame();
            eleUtil.waitForElementPresence(this.dashboardVerticalGraphBar, 120);
            eleUtil.doClick(this.dashboardVerticalGraphBar);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Event State Dashboard Graph Bar\' successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Event State Dashboard Graph Bar\'");
            Assert.fail(e.getMessage());
        }
        return this;
    }
    
    
    /**
     * This method is used to click on NewVsInprocess Dashboard Graph Bar
     *
     * @return This will return the Object of DashboardScreen class
     */
    public DashboardScreen clickOnNewVsInprocessVerticalDashboardGraphBar() {
        try {
            this.switchToNewVsInprocessFrame();
            eleUtil.waitForElementPresence(this.dashboardVerticalGraphBar, 120);
            eleUtil.doClick(this.dashboardVerticalGraphBar);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Event State Dashboard Graph Bar\' successfully");
            eleUtil.switchToDefaultContent();
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Event State Dashboard Graph Bar\'");
            Assert.fail(e.getMessage());
        }
        return this;
    }
    
    
    /**
     * This method is used to get the Assignment Screen WorkItem Table Row Count
     *
     * @return This will return the WorkItem Table Row Count in Int Format
     */
    public int getAssignmentWorkItemTableRowCount() {
    	eleUtil.wait(5);
    	this.switchToAssignmentFrame();
        this.switchToWorkItemFrame(); 
        int rowCount = this.getWorkItemTableRowCount();
        eleUtil.switchToDefaultContent();
        return rowCount;
    }
    
    
    
    
    /**
     * This method is used to get the Activity Event Screen WorkItem Table Row Count
     *
     * @return This will return the WorkItem Table Row Count in Int Format
     */
    public int getActivityEventWorkItemTableRowCount() {
    	eleUtil.wait(5);
    	this.switchToActivityEventStateFrame();
        this.switchToWorkItemFrame(); 
        int rowCount = this.getWorkItemTableRowCount();
        eleUtil.switchToDefaultContent();
        return rowCount;
    }
    
    
    /**
     * This method is used to get the New Vs Inprocess Screen WorkItem Table Row Count
     *
     * @return This will return the WorkItem Table Row Count in Int Format
     */
    public int getActivityNewVsInProcessItemTableRowCount() {
    	eleUtil.wait(5);
    	this.switchToNewVsInprocessFrame();
        this.switchToWorkItemFrame(); 
        int rowCount = this.getWorkItemTableRowCount();
        eleUtil.switchToDefaultContent();
        return rowCount;
    }
    
    /**
     * This method is used to get the WorkItem Table Row Count
     *
     * @return This will return the WorkItem Table Row Count in Int Format
     */
    public int getWorkItemTableRowCount() {
        int rowCount = 0;
        try {        	        
            rowCount = eleUtil.getElements(this.workItemTableRows).size();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Getting WorkItem Table Row count successfully");            
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while getting WorkItem Table Row count");
            Assert.fail(e.getMessage());
        }
        return rowCount;
    }
    
}
