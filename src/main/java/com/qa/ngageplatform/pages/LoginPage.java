package com.qa.ngageplatform.pages;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.ngageplatform.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to Login Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// ****************** Locators ****************** //
	private By userNameTextField = By.id("Login1_UserName");
	private By passwordTextField = By.id("Login1_Password");
	private By loginButton = By.id("Login1_LoginImageButton");
	private By changePWLink = By.id("LnkBtnChangePassword");
	private By forgotPWLink = By.id("LnkBtn");
	private By logoImg = By.xpath("//*[@id='imgEPMLogo']");
	private By loginBox = By.xpath("//div[@class='loginrightpanel']");
	private By backToLoginLink = By.xpath("//tr[@id='trBackToLogin']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	/**
	 * This method is used to enter UserName
	 *
	 * @param username Username value for Login
	 * @return This will return the Object of LoginPage class
	 */
	public LoginPage enterUserName(String username) {
		eleUtil.doSendKeys(this.userNameTextField, username);
		return this;

	}

	/**
	 * This method is used to enter Password
	 *
	 * @param password Password value for Login
	 * @return This will return the Object of LoginPage class
	 */
	public LoginPage enterPassword(String password) {
		eleUtil.doSendKeys(this.passwordTextField, password);
		return this;
	}

	/**
	 * This method is used to click on Login button
	 *
	 * @return This will return the Object of LoginPage class
	 */
	public LoginPage clickLoginButton() {
		new JavaScriptUtil(this.driver).clickElementByJS(eleUtil.getElement(this.loginButton));
		return this;
	}

	/**
	 * This method is used to enter the credentials on Login Page
	 *
	 * @param userName Username value for Login
	 * @param password Password value for Login
	 * @return This will return the Object of MainPage class
	 */
	public MainPage doLogin(String userName, String password) {
		this.enterUserName(userName);
		this.enterPassword(password);
		this.clickLoginButton();
		if (eleUtil.isElementExist(this.loginButton)) {
			this.enterUserName(userName);
			this.enterPassword(password);
			this.clickLoginButton();
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));

		}
		return new MainPage(this.driver);
	}

	/**
	 * This method is used to validate submit button has Login value
	 * 
	 * @return This will return result in boolean format
	 */
	public boolean validateLoginButton() {
		boolean login = eleUtil.isDisplay(this.loginButton);
		if (login == true) {
			ExtentReportListener.test.get().log(Status.INFO, "Login button is displayed");
		}

		String loginBtnValue = eleUtil.getAttributeValue(this.loginButton, "value");
		if (loginBtnValue.contentEquals("Login")) {
			ExtentReportListener.test.get().log(Status.INFO, "Submit button's attribute	 value is 'Login'");
			return true;
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Submit button's attribute value is not 'Login'");
			return false;
		}
	}

	/**
	 * This method is used to verify Change Password link is displayed in loginPage
	 * 
	 * @return This will return result in boolean format
	 */
	public boolean vefificationOfChangePasswordIsDisplayed() {
		boolean login = eleUtil.isDisplay(this.changePWLink);
		String changePasswordText = eleUtil.doGetText(this.changePWLink);
		if (login == true) {
			ExtentReportListener.test.get().log(Status.INFO, "Change Password Link is displayed");
			{
				if (changePasswordText.contentEquals("Change Password")) {
					ExtentReportListener.test.get().log(Status.INFO, "Change Password link text value is correct");
					return true;
				} else {
					ExtentReportListener.test.get().log(Status.FAIL,
							"Change Password link text value is not displayed correctly");
					return false;
				}
			}
		} else {
			return false;
		}

	}

	/**
	 * This method is used to verify Forgot Password link is displayed in loginPage
	 * 
	 * * @return This will return result in boolean format
	 */
	public boolean vefificationOfForgotPasswordIsDisplayed() {
		boolean login = eleUtil.isDisplay(this.forgotPWLink);
		String forgotPasswordText = eleUtil.doGetText(this.forgotPWLink);
		if (login == true) {
			ExtentReportListener.test.get().log(Status.INFO, "Forgot Password Link is displayed");
			{
				if (forgotPasswordText.contentEquals("Forgot Password?")) {
					ExtentReportListener.test.get().log(Status.INFO, "Forgot Password link text value is correct");
					return true;
				} else {
					ExtentReportListener.test.get().log(Status.FAIL,
							"Forgot Password link text value is not displayed correctly");
					return false;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * This method is used to Verify correct image logo is loaded
	 * 
	 * * @return This will return result in boolean format
	 */

	public boolean validateLogoImage() {
		boolean imgLogo = eleUtil.isElementExist(this.logoImg);
		String imgLogoSrcValue = eleUtil.getAttributeValue(this.logoImg, "src");
		if (imgLogo == true & imgLogoSrcValue.contains("Ngage_banner1_sav.png")) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * This method is used to get login box width
	 * 
	 * * @return This will return loginBoxWidth in integer format
	 */

	public int getLoginBoxWidth() {
		WebElement loginBoxEle = driver.findElement(this.loginBox);
		Rectangle loginBoxRec = loginBoxEle.getRect(); 
		int loginBoxWidth= loginBoxRec.getWidth();
		return loginBoxWidth;

}
	/**
	 * This method is used to get login box height
	 * 
	 * * @return This will return loginBoxHeight in integer format
	 */

	public int getLoginPageWindowWidth() {
		Dimension winDimension = driver.manage().window().getSize();
		int winWidth = winDimension.getWidth();
		System.out.println(winWidth);
		return winWidth;

}
	
	/**
	 * This method is used to verify login box width after resize is same
	 * 
	 * * @return This will return true or false in boolean format
	 */
	public boolean verifyWindowWidthAfterResize() {
		driver.manage().window().maximize();
		int beforeResizeWindowWidth =this.getLoginPageWindowWidth();
		this.resizeWindow();
		int afterResizeWindowWidth =this.getLoginPageWindowWidth();
		if (beforeResizeWindowWidth>afterResizeWindowWidth ) {
		return true;}
		else {return false;}
	}
	
	/**
	 * This method is used to verify login box height and width is equal
	 * 
	 * * @return This will return true or false in boolean format
	 */
	public void resizeWindow() {
		Dimension d = new Dimension(1024, 768);
		driver.manage().window().setSize(d);
	}
	
	/**
	 * This method is used to verify login box width after resize is same
	 * 
	 * * @return This will return true or false in boolean format
	 */
	public boolean verifyLoginBoxWidthAfterResize() {
		int beforeResizeLoginBoxWidth =this.getLoginBoxWidth();
		this.resizeWindow();
		int afterResizeLoginBoxWidth =this.getLoginBoxWidth();
		if (beforeResizeLoginBoxWidth==afterResizeLoginBoxWidth ) {
		return true;}
		else {return false;}
	}
	
	/**
	 * This method is used to verify Back to login link is present
	 * 
	 * * @return This will return true or false in boolean format
	 */
	public boolean verifyBackToLoginLink() {
		eleUtil.doClick(this.forgotPWLink);
		String backToLogin = eleUtil.doGetText(this.backToLoginLink);
		if (backToLogin.contentEquals("Back to Login") && eleUtil.isDisplay(this.backToLoginLink)) {
			ExtentReportListener.test.get().log(Status.INFO, "Back to Login link text value is correct");
			return true;
		} else {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Back to Login link text value is not displayed correctly");
			return false;
		}
		
		
	}
		
}
