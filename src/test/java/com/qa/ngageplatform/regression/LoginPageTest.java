package com.qa.ngageplatform.regression;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.utils.AssertionUtil;

import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest
//Branding of Feature Module of RegressionSuite
{
	@Test(description = "Verify login page links and verify links' text and logo are displayed correctly", priority = 1)
	public void verificationOfLoginPage_BM001_To_BM004() {

		AssertionUtil.verifyEqual(loginPage.validateLoginButton(), true,
				"Verification of Login button's link and text 'Login' is displayed successfully");
		AssertionUtil.verifyEqual(loginPage.vefificationOfForgotPasswordIsDisplayed(), true,
				"Verification of Change Password link and text displayed is successfully");
		AssertionUtil.verifyEqual(loginPage.vefificationOfChangePasswordIsDisplayed(), true,
				"Verification of Change Password link and text displayed is successfully");
		AssertionUtil.verifyEqual(loginPage.validateLogoImage(), true,
				"Verification of Correct logo image is loaded successfully");

	}

	@Test(description = "Verify login page margin", priority = 2)
	public void verificationOfLoginPage_Login002() {

		AssertionUtil.verifyEqual(loginPage.validateLoginButton(), true,
				"Verification of Login button's link and text 'Login' is displayed successfully");
		AssertionUtil.verifyEqual(loginPage.verifyLoginBoxWidthAfterResize(), true,
				"Verification of Login box width after resize is equal succesfully");
		AssertionUtil.verifyEqual(loginPage.verifyWindowWidthAfterResize(), true,
				"Verification of Window width after resize is smaller succesfully");
		}

	@Test(description = "Verify back to login link", priority = 3)
	public void verificationOfForgotPwdBackToLogin_Login003() {
		loginPage.enterUserName(prop.getProperty("username"));
		AssertionUtil.verifyEqual(loginPage.verifyBackToLoginLink(), true,
				"Verification of Back to Login link text is displayed successfully");

	}
}
