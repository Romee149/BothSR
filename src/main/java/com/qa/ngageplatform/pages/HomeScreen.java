package com.qa.ngageplatform.pages;

import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;

import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
/**
 * This Class is used to provide Object Repo and Actions related to Home Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class HomeScreen {

    private WebDriver driver;
    private ElementUtil eleUtil;

    // ****************** Locators ****************** //
    private By recentDocumentsPageIframe = By.id("iframe_103");
    private By recentDocumentLink = By.xpath("//*[@id='westContent']//*[@id='RECENT_anchor']");
    private By favoriteDocumentLink = By.id("FAVORITE");
    private By docID = By.xpath("//th[@id='tblMyDocumentsResults_DocId']");
    private By docIdData = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_DocId'])[1]");
    private By docTitleData = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_DocTitle'])[1]");
    private By docLastActionData = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_LastAction'])[1]");
    private By recentDocResultInfo = By.xpath("//div[@class='ui-paging-info']");

    public HomeScreen(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
        eleUtil.switchToDefaultContent();
    }

    
    /**
     * This method is used to switch to Home Screen frame
     *
     * @return This will return the Object of HomeScreen class
     */
    public HomeScreen switchToPageFrame() {
        eleUtil.switchToFrame(this.recentDocumentsPageIframe, 20);
        return this;
    }


   
    /**
     * This method is used to click on Recent Document Link
     *
     * @return This will return the Object of HomeScreen class
     */
    @Step("Click On Recent Document Link")
    public HomeScreen clickRecentDocumentLink() {
        try {
            eleUtil.clickElementWhenReady(this.recentDocumentLink, 3);
            ExtentReportListener.test.get().log(Status.INFO, "Clicked on \"Recent Documents\" link successfully");

        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \"Recent Documents\" link");
            Assert.fail(e.getMessage());
        }
        return this;
    }


  
/**
     * This method is used to get grid result
     *
     * @return This will return the count of grid value
     */
    @Step("Fetching Grid Results")
    public int getGridResult() {
        eleUtil.wait(2);
        eleUtil.switchToFrame(this.recentDocumentsPageIframe, 20);
        eleUtil.waitForElementPresence(this.recentDocResultInfo, 50);
        String textInfo = eleUtil.doGetText(recentDocResultInfo);
        String resultCount = textInfo.substring(textInfo.length() - 3);
        int cnt = Integer.parseInt(resultCount.trim());
        eleUtil.switchToDefaultContent();
        return cnt;
    }
    /**
     * This method is used to get grid result
     *
     * @return This will return the count of grid value
     */
    @Step("Validate Grid Results")
    public boolean validateGridResult() {
        if (getGridResult() >= 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method is used to click on Favourites Document Link
     *
     * @return This will return the Object of HomeScreen class
     */
    @Step("Click On Favourite Document Link")
    public HomeScreen clickFavouritesDocumentLink() {
        try {
            eleUtil.clickElementWhenReady(this.favoriteDocumentLink, 10);
            ExtentReportListener.test.get().log(Status.INFO, "Clicked on \"Favourites Documents\" link successfully");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \"Favourites Documents\" link");
            Assert.fail(e.getMessage());
        }
        return this;
    }


    /**
     * This method is used to click on DocID Column Header
     *
     * @return This will return the Object of HomeScreen class
     */
    @Step("Click On Doc ID column header")
    public HomeScreen clickOnDocIDColumnHeader() {
        this.switchToPageFrame();
        eleUtil.waitForElementToBeClickable(this.docID, 10);
        eleUtil.doDoubleClick(this.docID);
        eleUtil.switchToDefaultContent();
        return this;
    }

    /**
     * This method is used to get First Available DocID value
     *
     * @return This will return the first available DocID value in String Format
     */
    @Step("Get First Available DocID")
    public String getFirstAvailableDocID() {
        this.switchToPageFrame();
        String docID = eleUtil.doGetText(this.docIdData);
        eleUtil.switchToDefaultContent();
        return docID;
    }

    /**
     * This method is used to get First Available DocType value
     *
     * @return This will return the first available DocType value in String Format
     */
    @Step("Get First Available DocType")
    public String getFirstAvailableDocType() {
        eleUtil.wait(2);
        this.switchToPageFrame();
        String docType = eleUtil.doGetText(this.docTitleData);
        eleUtil.switchToDefaultContent();
        return docType;
    }


    /**
     * This method is used to get First Available DocLastAction value
     *
     * @return This will return the first available DocLastAction value in String Format
     */
    @Step("Get First Available DocLast Action")
    public String getFirstAvailableDocLastAction() {
        eleUtil.wait(2);
        this.switchToPageFrame();
        String docLastAction = eleUtil.doGetText(this.docLastActionData);
        eleUtil.switchToDefaultContent();
        return docLastAction;
    }

    /**
     * This method is used to get First Available DocLastAction value in Numeric Format
     *
     * @return This will return the first available DocLastAction value in int Format
     */
    public int getFirstAvailableDocIDInNumericFormat() {
        return new CommonUtil().convertStringToNumber(this.getFirstAvailableDocID());
    }

    /**
     * This method is used to click on First Available Doc Type
     *
     * @return This will return the Object of NewDocPage class
     */
    @Step("Click First Available Doc Type")
    public NewDocPage clickFirstAvailableDocType() {
        this.switchToPageFrame();
        try
        {
            eleUtil.waitForTitleContains("DocTitle",1);
           
        }
        catch (Exception e) {};
        eleUtil.waitForElementToBeClickable(this.docTitleData, 20);
        eleUtil.doClick(this.docTitleData);
        eleUtil.switchToDefaultContent();
        return new NewDocPage(this.driver);
    }

    
    

    // ************************************************//
    // ************ Complete Actions/Flows ************//
    // ************************************************//

    /**
     * This method is used Sort DocID Column in Descending order
     *
     * @return This will return the Object of HomeScreen class
     */
    @Step("Sort Doc Id in descending order")
    public HomeScreen sortDocIDInDescOrder() {
        this.clickOnDocIDColumnHeader();
        return this;
    }

    /**
     * This method is used to get Latest Doc ID by sort the DocID in Desc Order and Fetch the first value
     *
     * @return This will return the Latest Doc ID Value in int Format
     */
    @Step("Get Latest Doc ID")
    public int getLatestDocID() {
        this.sortDocIDInDescOrder();
        eleUtil.wait(2);
        return this.getFirstAvailableDocIDInNumericFormat();
    }

    /**
     * This method is used Sort DocID Column in Descending order for Recent Documents List
     *
     * @return This will return the Object of HomeScreen class
     */
    @Step("Sort Document List by Doc ID")
    public HomeScreen sortDocumentListByDocID() {
        this.clickRecentDocumentLink();
        this.sortDocIDInDescOrder();
        return this;
    }
    
    /**
     * This method is used to get First Available Doc Type Text
     *
     * @return This will return the Object of NewDocPage class
     */
    @Step("Get First Available Doc Type Value")
    public String getFirstDocTypeValue() {
        eleUtil.wait(2);
        this.switchToPageFrame();
        String value= eleUtil.doGetText(this.docTitleData);        	
        eleUtil.switchToDefaultContent();
        return value ;
              }
    
    /**
     * This method is used to click on First Available Doc Type
     *
     * @return This will return the Object of NewDocPage class
     */
    public NewDocPage getNewDocPage() {
        eleUtil.wait(2);
        this.switchToPageFrame();
        eleUtil.switchToDefaultContent();
        return new NewDocPage(this.driver);
    }

}
