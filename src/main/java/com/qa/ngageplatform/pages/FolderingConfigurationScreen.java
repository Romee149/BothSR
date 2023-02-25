package com.qa.ngageplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to Foldering Configuration Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class FolderingConfigurationScreen {

    private WebDriver driver;
    private ElementUtil eleUtil;


    // ************ Locators ************ //
    private By assignedFieldLevel1Dropdown = By.xpath("//*[@id='drpFieldLevel1']");
    private By assignedFieldLevel2Dropdown = By.xpath("//*[@id='drpFieldLevel2']");
    private By assignedFieldLevel3Dropdown = By.xpath("//*[@id='drpFieldLevel3']");
    private By sortingFieldLevel1Dropdown = By.xpath("//*[@id='dropSortingLevel1']");
    private By sortingFieldLevel2Dropdown = By.xpath("//*[@id='dropSortingLevel2']");
    private By sortingFieldLevel3Dropdown = By.xpath("//*[@id='dropSortingLevel3']");
    private By restoreDefaultButton = By.xpath("//*[@class='ui-dialog-buttonset']//button[text()='Restore Default']");
    private By saveButton = By.xpath("//*[@class='ui-dialog-buttonset']//button[text()='Save']");
    private By closeButton = By.xpath("//*[@class='ui-dialog-buttonset']//button[text()='Close']");
    private By processValue = By.xpath("//*[@id='lblProcessName']");
    private By activityValue = By.xpath("//*[@id='lblSearchClassQ']");
    private By searchClassIDValue = By.xpath("//*[@id='lblSearchClassID']");
    private By frame = By.id("iframe_folderingConfiguration");


    public FolderingConfigurationScreen(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    /**
     * This method is used to switch to Foldering Configuration Screen frame
     *
     * @return This will return the Object of FolderingConfigurationScreen class
     */
    public FolderingConfigurationScreen switchToPageFrame() {
        eleUtil.switchToFrameIfExists(this.frame);
        return this;
    }

    /**
     * This method is used to fetch the Assigned Field's Level 1 Dropdown Default Value
     *
     * @return This will return the Default Value of Assigned Field's Level 1 Dropdown in String Format
     */
    public String getAssignedFieldLevel1DropdownDefaultSelectedValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.getDefaultSelectedValueForDropdown(this.assignedFieldLevel1Dropdown);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching default selected value for \'Assigned Field Level 1\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while fetching default selected value for \'Assigned Field Level 1\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }

    /**
     * This method is used to fetch the Assigned Field's Level 2 Dropdown Default Value
     *
     * @return This will return the Default Value of Assigned Field's Level 2 Dropdown in String Format
     */
    public String getAssignedFieldLevel2DropdownDefaultSelectedValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.getDefaultSelectedValueForDropdown(this.assignedFieldLevel2Dropdown);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching default selected value for \'Assigned Field Level 2\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while fetching default selected value for \'Assigned Field Level 2\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }

    /**
     * This method is used to fetch the Assigned Field's Level 3 Dropdown Default Value
     *
     * @return This will return the Default Value of Assigned Field's Level 3 Dropdown in String Format
     */
    public String getAssignedFieldLevel3DropdownDefaultSelectedValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.getDefaultSelectedValueForDropdown(this.assignedFieldLevel3Dropdown);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching default selected value for \'Assigned Field Level 3\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while fetching default selected value for \'Assigned Field Level 3\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }

    /**
     * This method is used to fetch the Sorting Field's Level 1 Dropdown Default Value
     *
     * @return This will return the Default Value of Sorting Field's Level 1 Dropdown in String Format
     */
    public String getSortingFieldLevel1DropdownDefaultSelectedValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.getDefaultSelectedValueForDropdown(this.sortingFieldLevel1Dropdown);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching default selected value for \'Sorting Field Level 1\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while fetching default selected value for \'Sorting Field Level 1\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }


    /**
     * This method is used to fetch the Sorting Field's Level 2 Dropdown Default Value
     *
     * @return This will return the Default Value of Sorting Field's Level 2 Dropdown in String Format
     */
    public String getSortingFieldLevel2DropdownDefaultSelectedValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.getDefaultSelectedValueForDropdown(this.sortingFieldLevel2Dropdown);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching default selected value for \'Sorting Field Level 2\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while fetching default selected value for \'Sorting Field Level 2\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }


    /**
     * This method is used to fetch the Sorting Field's Level 3 Dropdown Default Value
     *
     * @return This will return the Default Value of Sorting Field's Level 3 Dropdown in String Format
     */
    public String getSortingFieldLevel3DropdownDefaultSelectedValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.getDefaultSelectedValueForDropdown(this.sortingFieldLevel3Dropdown);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching default selected value for \'Sorting Field Level 3\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while fetching default selected value for \'Sorting Field Level 3\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }

    /**
     * This method is used to fetch the Process Value
     *
     * @return This will return the Process Label Value in String Format
     */
    public String getProcessValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.doGetText(this.processValue).trim();
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO, "Fetching value of Process");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching value of Process");
            Assert.fail(e.getMessage());
        }
        return value;
    }


    /**
     * This method is used to fetch the Activity Value
     *
     * @return This will return the Activity Label Value in String Format
     */
    public String getActivityValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.doGetText(this.activityValue).trim();
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO, "Fetching value of Activity");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching value of Activity");
            Assert.fail(e.getMessage());
        }
        return value;
    }

    /**
     * This method is used to fetch the Search Class ID Value
     *
     * @return This will return the Search Class ID Label Value in String Format
     */
    public String getSearchClassIDValue() {
        String value = null;
        try {
            this.switchToPageFrame();
            value = eleUtil.doGetText(this.searchClassIDValue).trim();
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO, "Fetching value of SearchClassID");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching value of SearchClassID");
            Assert.fail(e.getMessage());
        }
        return value;
    }

    /**
     * This method is used to click on Restore Default button
     *
     * @return This will return the Object of FolderingConfigurationScreen class
     */
    public FolderingConfigurationScreen clickOnRestoreDefaultButton() {
        try {
        	eleUtil.waitForElementPresence(this.restoreDefaultButton, 40);
            eleUtil.doClick(this.restoreDefaultButton);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking On Restore Default button");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on Restore Default button");
            Assert.fail(e.getMessage());
        }
        return this;
    }

    /**
     * This method is used to click on Save button
     *
     * @return This will return the Object of FolderingConfigurationScreen class
     */
    public FolderingConfigurationScreen clickOnSaveButton() {
        try {
            eleUtil.doClick(this.saveButton);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking On Save button");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on Save button");
            Assert.fail(e.getMessage());
        }
        return this;
    }

    /**
     * This method is used to click on Close button
     *
     * @return This will return the Object of FolderingConfigurationScreen class
     */
    public FolderingConfigurationScreen clickOnCloseButton() {
        try {
            eleUtil.doClick(this.closeButton);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO, "Clicking On Close button");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on Close button");
            Assert.fail(e.getMessage());
        }
        return this;
    }

     /**
     * This method is used select value from Sorting Field Level 1 Drop down
     *
     * @param value Drop down value which we need to select
     * @return This will return the Object of FolderingConfigurationScreen class
     */
    public String selectValueForSortingFieldLevel1Dropdown(String value) {
        try {
            this.switchToPageFrame();
            eleUtil.doDropDownSelectByVisibleText(this.sortingFieldLevel1Dropdown, value);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Selecting value " + value + " in \'Sorting Field Level 1\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while selecting value " + value + " in \'Sorting Field Level 1\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }
    
    /**
     * This method is used select value from Assigned Field Level 1 Drop down
     *
     * @param value Drop down value which we need to select
     * @return This will return the Object of FolderingConfigurationScreen class
     */
    public String selectValueForAssignedFieldLevel1Dropdown(String value) {
        try {
            this.switchToPageFrame();
            eleUtil.doDropDownSelectByVisibleText(this.assignedFieldLevel1Dropdown, value);
            eleUtil.switchToDefaultContent();
            ExtentReportListener.test.get().log(Status.INFO,
                    "Selecting value " + value + " in \'Sorting Field Level 1\' dropdown");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed while selecting value " + value + " in \'Sorting Field Level 1\' dropdown");
            Assert.fail(e.getMessage());
        }
        return value;
    }
    
    /**
     * This method is used to click on Save and then Close button
     *
     * @return This will return the Object of FolderingConfigurationScreen class
     */
    public FolderingConfigurationScreen clickOnSaveAndCloseButton() {
    	this.clickOnSaveButton();
    	this.clickOnCloseButton();
		return this;
    	
    }
    
    /**
	 * This method is used to get MyWorkScreen Object
	 *
	 * @return This will return the Object of MyWorkScreen class Object
	 */
	public MyWorkScreen getMyWorkScreenPage() {
		return new MyWorkScreen(this.driver);
	}

}
