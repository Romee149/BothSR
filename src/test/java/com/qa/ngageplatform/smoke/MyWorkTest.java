package com.qa.ngageplatform.smoke;

import com.qa.ngageplatform.base.BaseTest;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;

import java.util.*;

import org.testng.annotations.Test;

public class MyWorkTest extends BaseTest {

	@Test(description = "Verify Default and User Navigated Pagination Info and Searched Customer Name", priority = 1)
	public void verifyPaginationInfoAndCustomerNameForWorkItemList_MW001_MW002() {

		String customerName = "MyTest" + new CommonUtil().getCurrentDateTime();
		String expectedPaginationInfoText = "Showing 1 - 10 of";
		String expectedUniqueSearchResultPaginationInfoText = "Showing 1 - 1 of 1";

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MyTest", customerName);
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		String actualPaginationInfoText = workItemsScreen.getPaginationInfo();

		// ****** Verification of Pagination Info MW001
		AssertionUtil.verifyContainsText(actualPaginationInfoText, expectedPaginationInfoText,
				"Verification of Pagination Info");

		String actualCustomerName = workItemsScreen.searchAndFetchCustomerNameOfFirstAvailableRow(customerName);

		// ****** Verification of Customer Name
		AssertionUtil.verifyEqual(actualCustomerName, customerName,
				"Verification of Customer Name in Work Item List should be according to Search Parameter");

		String paginationInfoTextForSearchResult = workItemsScreen.getPaginationInfo();

		// ****** Verification of Pagination Text for unique search result
		AssertionUtil.verifyEqual(paginationInfoTextForSearchResult, expectedUniqueSearchResultPaginationInfoText,
				"Verification of Pagination Info Text for Search results");

		workItemsScreen.resetSearchPanel();
		String paginationInfoTextAfterReset = workItemsScreen.getPaginationInfo();

		// ****** Verification of Pagination Text before and after Resetting Search
		// Panel MW002
		AssertionUtil.verifyEqual(actualPaginationInfoText, paginationInfoTextAfterReset,
				"Verification of Pagination Info Text Before and After Reset Of Search Panel");

		workItemsScreen.goToPage("3");
		String paginationInfoTextForSpecificPage = workItemsScreen.getPaginationInfo();

		// ****** Verification of Pagination for specific page MW006
		AssertionUtil.verifyContainsText(paginationInfoTextForSpecificPage, "Showing 21 - 30 of",
				"Verification of Pagination Info for specific page");

	}

//MW003
	@Test(description = "Verify Search Result for Process Due Date Start Range", priority = 1)
	public void verifyProcessDueDateStartRange_MW003() {

		String dateTimeFormat = "MM-dd-yyyy hh:mm:ss a";
		CommonUtil commonUtil = new CommonUtil();
		String expectedProcessDueDateStartRange = commonUtil.getDateTime(0, dateTimeFormat);
		String customerName = "MyTest" + commonUtil.getCurrentDateTime();

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MyTest", customerName);
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		workItemsScreen.searchProcessDueDateByStartRange(expectedProcessDueDateStartRange);
		workItemsScreen.sortProcessDueDateColumnInAscOrder();
		String processDueDateValue = workItemsScreen.getFirstAvailableProcessDueDateData();

		boolean isActualProcessDueDateGreater = commonUtil.verifyFirstDateIsGreater(processDueDateValue,
				expectedProcessDueDateStartRange, "MM-dd-yyyy hh:mm:ss a");

		AssertionUtil.verifyEqual(isActualProcessDueDateGreater, true,
				"Verification of \'Process Due Date\' start date should be lesser than Process Due Date Column Value");

	}

// MW004
	@Test(description = "Verify Search Result for Process Due Date End Range", priority = 1)
	public void verifyProcessDueDateEndRange_MW004() {

		String dateTimeFormat = "MM-dd-yyyy hh:mm:ss a";
		CommonUtil commonUtil = new CommonUtil();
		String expectedProcessDueDateEndRange = commonUtil.getDateTime(0, dateTimeFormat);
		String customerName = "MyTest" + commonUtil.getCurrentDateTime();

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MyTest", customerName);
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();

		workItemsScreen.searchProcessDueDateByEndRange(expectedProcessDueDateEndRange);
		workItemsScreen.sortProcessDueDateColumnInDescOrder();
		String processDueDateValue = workItemsScreen.getFirstAvailableProcessDueDateData();

		boolean isActualProcessDueDateLesser = commonUtil.verifyFirstDateIsGreater(expectedProcessDueDateEndRange,
				processDueDateValue, dateTimeFormat);

		AssertionUtil.verifyEqual(isActualProcessDueDateLesser, true,
				"Verification of \'Process Due Date\' end date should be greater than Process Due Date Column Value");

	}

//MW005
	@Test(description = "Verify Search Result for Process Due Date Start Range and End Range", priority = 1)
	public void verifyProcessDueDateBetweenStartAndEndRange_MW005_MW006() {

		CommonUtil commonUtil = new CommonUtil();
		String dateTimeFormat = "MM-dd-yyyy hh:mm:ss a";
		String expectedProcessDueDateStartRange = commonUtil.getDateTime(0, dateTimeFormat);
		String expectedProcessDueDateEndRange = commonUtil.getDateTime(7, dateTimeFormat);
		String customerName = "MyTest" + commonUtil.getCurrentDateTime();

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MyTest", customerName);
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();

		workItemsScreen.searchProcessDueDate(expectedProcessDueDateStartRange, expectedProcessDueDateEndRange);
		workItemsScreen.sortProcessDueDateColumnInAscOrder();
		String processDueDateValue = workItemsScreen.getFirstAvailableProcessDueDateData();
		boolean isActualProcessDueDateGreater = commonUtil.verifyFirstDateIsGreater(processDueDateValue,
				expectedProcessDueDateStartRange, dateTimeFormat);
		AssertionUtil.verifyEqual(isActualProcessDueDateGreater, true,
				"Verification of \'Process Due Date\' start date should be lesser than Process Due Date Column Value");

		workItemsScreen.sortProcessDueDateColumnInDescOrder();
		processDueDateValue = workItemsScreen.getFirstAvailableProcessDueDateData();
		boolean isActualProcessDueDateLesser = commonUtil.verifyFirstDateIsGreater(expectedProcessDueDateEndRange,
				processDueDateValue, dateTimeFormat);
		AssertionUtil.verifyEqual(isActualProcessDueDateLesser, true,
				"Verification of \'Process Due Date\' end date should be greater than Process Due Date Column Value");

	}

//MW008
	@Test(description = "Verify ActivityA should have Foldering Configuration option and Verify Default Content of Foldering Configuration Screen", priority = 1)
	public void verifyRightClickOptionForActivityAWithFolderingConfigurationScreenContent() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.expandClosureAction();
		myWorkScreen.rightClickOnActivityALink();

		// Verification of Right Click Options for Activity A resides under Closure
		// Action option
		boolean isRefreshOptionExists = myWorkScreen.verifyExistenceOfValueInRightClickOptions("Refresh");
		AssertionUtil.verifyEqual(isRefreshOptionExists, true,
				"Verification of \'Refresh Option\' on right click on ActivityA under Closure Action");

		boolean isFolderingConfigurationOptionExists = myWorkScreen
				.verifyExistenceOfValueInRightClickOptions("Foldering Configuration");
		AssertionUtil.verifyEqual(isFolderingConfigurationOptionExists, true,
				"Verification of \'Foldering Configuration Option\' on right click on ActivityA under Closure Action");

		// Verification of default dropdown values and label values on Foldering
		// Configuration screen
		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration();

		AssertionUtil.verifyEqual(folderConfigurationScreen.getAssignedFieldLevel1DropdownDefaultSelectedValue(),
				"Customer Name", "Verification of Default Selected value for \'AssignedField Level1\' dropdown");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getAssignedFieldLevel2DropdownDefaultSelectedValue(),
				"Select a Field", "Verification of Default Selected value for \'AssignedField Level2\' dropdown");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getAssignedFieldLevel3DropdownDefaultSelectedValue(),
				"Select a Field", "Verification of Default Selected value for \'AssignedField Level3\' dropdown");

		AssertionUtil.verifyEqual(folderConfigurationScreen.getSortingFieldLevel1DropdownDefaultSelectedValue(), "None",
				"Verification of Default Selected value for \'SortingField Level1\' dropdown");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getSortingFieldLevel2DropdownDefaultSelectedValue(), "None",
				"Verification of Default Selected value for \'SortingField Level2\' dropdown");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getSortingFieldLevel3DropdownDefaultSelectedValue(), "None",
				"Verification of Default Selected value for \'SortingField Level3\' dropdown");

		AssertionUtil.verifyEqual(folderConfigurationScreen.getProcessValue(), "Closure Action",
				"Verification of folder Configuration Process Value is 'Closure Action' is successfull");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getActivityValue(), "Activity A",
				"Verification of folder Configuration Activity Value is 'Activity A' is successfull");
		AssertionUtil.verifyEqual(folderConfigurationScreen.getSearchClassIDValue(), "100012",
				"Verification of folder Configuration Search Class ID is '100012' is successfull ");

	}

//mw012 desc left
	@Test(description = "Verify Sorting of Customer Name by Selecting Sorting criteria from Foldering Configuration screen", priority = 1)
	public void verifySortingByFolderingConfigurationScreenSelection_MW012() {

		CommonUtil commonUtil = new CommonUtil();
		List<String> defaultCustomerNameList = new ArrayList<>();
		List<String> sortedCustomerNameList = new ArrayList<>();
		List<String> expectedDefaultCustomerNameList = new ArrayList<>();
		List<String> expectedSortedCustomerNameList_Asc = new ArrayList<>();
		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.expandClosureAction();
		myWorkScreen.expandActivityA();
		expectedDefaultCustomerNameList = myWorkScreen.getActivityACustomerNameList();
		expectedSortedCustomerNameList_Asc = myWorkScreen.getActivityACustomerNameList();
		commonUtil.customizeSorting(expectedSortedCustomerNameList_Asc);
		myWorkScreen.rightClickOnActivityALink();
		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration();
		folderConfigurationScreen.selectValueForSortingFieldLevel1Dropdown("Asc by Field");
		folderConfigurationScreen.clickOnSaveButton();
		folderConfigurationScreen.clickOnCloseButton();
		sortedCustomerNameList = myWorkScreen.getActivityACustomerNameList();
		// ****** Verification of customer list with asc sorting order
		AssertionUtil.verifyEqual(sortedCustomerNameList, expectedSortedCustomerNameList_Asc,
				"Verification of sorting of ActivityA Customer Name List in ascending order");
		myWorkScreen.rightClickOnActivityALink();
		folderConfigurationScreen = myWorkScreen.clickOnFolderingConfiguration();
		folderConfigurationScreen.clickOnRestoreDefaultButton();
		folderConfigurationScreen.clickOnSaveButton();
		folderConfigurationScreen.clickOnCloseButton();
		defaultCustomerNameList = myWorkScreen.getActivityACustomerNameList();
		// ****** Verification of customer list with default order [after clicking on
		// restore button]
		AssertionUtil.verifyEqual(defaultCustomerNameList, expectedDefaultCustomerNameList,
				"Verification of default sorting of ActivityA Customer Name List");
	}

//MW008 MW009
	@Test(description = "Verify Right Click options for ReloadOnPostback link", priority = 1)
	public void verifyRightClickOptionForReloadOnPostback_MW008_MW009() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.expandReloadOnPostback();
		myWorkScreen.rightClickOnActivity1Link();

		// Verification of Right Click Options for Activity A resides under Closure
		// Action option
		boolean isRefreshOptionExists = myWorkScreen.verifyExistenceOfValueInRightClickOptions("Refresh");
		AssertionUtil.verifyEqual(isRefreshOptionExists, true,
				"Verification of \'Refresh Option\' on right click on Activity1 under ReloadOnPostback");

		boolean isFolderingConfigurationOptionExists = myWorkScreen
				.verifyExistenceOfValueInRightClickOptions("Foldering Configuration");
		AssertionUtil.verifyEqual(isFolderingConfigurationOptionExists, false,
				"Verification of \'Foldering Configuration\' should not be display when right click on Activity1 under ReloadOnPostback");

	}

//MW013
	@Test(description = "Verify Default Dashboard Screen Content for Process Link", priority = 1)
	public void verifyProcessesDashboardPageContent_MW013() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnProcesses();

		// Verification of PieChart Section Header
		AssertionUtil.verifyEqual(myWorkScreen.getPieChartSectionHeader(), "All Processes - Summary",
				"Verification of \'PieChart Section Header\' in Process Dashboard");

		// Verification of PieChart Slice Count
		AssertionUtil.verifyGreaterThan(myWorkScreen.getPieChartSliceCount(), 0,
				"Verification of \'PieChart Slice Count\' in Process Dashboard");

		// Verification of Bar Graph Section Header
		AssertionUtil.verifyEqual(myWorkScreen.getBarGraphSectionHeader(), "All Processes - SLA Status View",
				"Verification of \'Bar Graph Section Header\' in Process Dashboard");

		// Verification of Bar Graph Count
		AssertionUtil.verifyGreaterThan(myWorkScreen.getBarGraphRectangularBarCount(), 0,
				"Verification of \'BarGraph Bar Count\' in Process Dashboard");

		// Verification of Table Section Row Count
		AssertionUtil.verifyGreaterThan(myWorkScreen.getDashboardTableSectionRowCount(), 0,
				"Verification of \'Table Section Row Count\' in Process Dashboard");

		// Verification of Process Summary Section
		AssertionUtil.verifyEqual(myWorkScreen.getShowOnTimeWorkItemsLinkText(), "Show On Time Work Items",
				"Verification of \'Link1\' in Process Summary Section in Process Dashboard");
		AssertionUtil.verifyEqual(myWorkScreen.getShowDueSoonWorkItemsLinkText(), "Show Due Soon Work Items",
				"Verification of \'Link2\' in Process Summary Section in Process Dashboard");
		AssertionUtil.verifyEqual(myWorkScreen.getShowOverdueWorkItemsLinkText(), "Show Overdue Work Items",
				"Verification of \'Link3\' in Process Summary Section in Process Dashboard");
		AssertionUtil.verifyEqual(myWorkScreen.getShowAllWorkItemsLinkText(), "Show All Work Items",
				"Verification of \'Link4\' in Process Summary Section in Process Dashboard");

	}

//MW014
	@Test(description = "Verify Default Dashboard Screen Content for Closure Action Link", priority = 1)
	public void verifyClosureActionDashboardPageContent_MW014() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnClosureAction();

		// Verification of PieChart Section Header
		AssertionUtil.verifyEqual(myWorkScreen.getPieChartSectionHeader(), "Closure Action - Summary",
				"Verification of \'PieChart Section Header\' in Closure Action Dashboard");

		// Verification of PieChart Slice Count
		AssertionUtil.verifyGreaterThan(myWorkScreen.getPieChartSliceCount(), 0,
				"Verification of \'PieChart Slice Count\' in Closure Action Dashboard");

		// Verification of Bar Graph Section Header
		AssertionUtil.verifyEqual(myWorkScreen.getBarGraphSectionHeader(), "Closure Action - SLA Status View",
				"Verification of \'Bar Graph Section Header\' in Closure Action Dashboard");

		// Verification of Bar Graph Count
		AssertionUtil.verifyGreaterThan(myWorkScreen.getBarGraphRectangularBarCount(), 0,
				"Verification of \'BarGraph Bar Count\' in Closure Action Dashboard");

		// Verification of Table Section Row Count
		AssertionUtil.verifyGreaterThan(myWorkScreen.getDashboardTableSectionRowCount(), 0,
				"Verification of \'Table Section Row Count\' in Closure Action Dashboard");

		// Verification of Process Summary Section
		AssertionUtil.verifyEqual(myWorkScreen.getShowOnTimeWorkItemsLinkText(), "Show On Time Work Items",
				"Verification of \'Link1\' in Process Summary Section in Closure Action Dashboard");
		AssertionUtil.verifyEqual(myWorkScreen.getShowDueSoonWorkItemsLinkText(), "Show Due Soon Work Items",
				"Verification of \'Link2\' in Process Summary Section in Closure Action Dashboard");
		AssertionUtil.verifyEqual(myWorkScreen.getShowOverdueWorkItemsLinkText(), "Show Overdue Work Items",
				"Verification of \'Link3\' in Process Summary Section in Closure Action Dashboard");
		AssertionUtil.verifyEqual(myWorkScreen.getShowAllWorkItemsLinkText(), "Show All Work Items",
				"Verification of \'Link4\' in Process Summary Section in Closure Action Dashboard");

	}

	// MW015
	@Test(description = "Verify ActivityA Dashboard Bar Graph And Work Item Table on Assignment Tab Screen", priority = 1)
	public void verifyActivityADashboardBarGraphAndWorkItemTableOnAssignmentScreen_MW015() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnClosureAction();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		dashboardScreen = workItemsScreen.clickOnDashboardTab();
		dashboardScreen.clickOnAssignmentTab();

		// Verification of Activity Due button label
		AssertionUtil.verifyEqual(dashboardScreen.getActivityDueButtonLabel(), "Activity Due",
				"Verification of \'Activity Due\' button label in Assignment Section on ActivityA Dashboard screen");

		// Verification of Process Due button label
		AssertionUtil.verifyEqual(dashboardScreen.getProcessDueButtonLabel(), "Process Due",
				"Verification of \'Process Due\' button label in Assignment Section on ActivityA Dashboard screen");

		int initialRowCount = dashboardScreen.getAssignmentWorkItemTableRowCount();

		dashboardScreen.clickOnAssignmentDashboardGraphBar();

		int updatedRowCount = dashboardScreen.getAssignmentWorkItemTableRowCount();

		// Verification of Process Due button label
		AssertionUtil.verifyGreaterThan(updatedRowCount, initialRowCount,
				"Verification of \'WorkItem\' table should be display when click on Dashboard Graph Bar in ActivityA Dashboard screen");

	}

	@Test(description = "Verify ActivityA Dashboard Bar Graph And Work Item Table on Activity Event State Tab Screen", priority = 1)
	public void verifyActivityADashboardBarGraphAndWorkItemTableOnEventScreen() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnClosureAction();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		dashboardScreen = workItemsScreen.clickOnDashboardTab();
		dashboardScreen.clickOnActivityEventStateTab();

		int initialRowCount = dashboardScreen.getActivityEventWorkItemTableRowCount();

		dashboardScreen.clickOnActivityEventStateVerticalDashboardGraphBar();

		int updatedRowCount = dashboardScreen.getActivityEventWorkItemTableRowCount();

		// Verification of Process Due button label
		AssertionUtil.verifyGreaterThan(updatedRowCount, initialRowCount,
				"Verification of \'WorkItem\' table should be display when click on Dashboard Vertical Graph Bar in ActivityA Dashboard Activity Event State Tab screen");

	}

	@Test(description = "Verify ActivityA Dashboard Bar Graph And Work Item Table on NewVsInprocess Tab Screen", priority = 1)
	public void verifyActivityADashboardBarGraphAndWorkItemTableOnNewVsInprocessScreen() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnClosureAction();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		dashboardScreen = workItemsScreen.clickOnDashboardTab();
		dashboardScreen.clickOnNewVsInprocessTab();

		int initialRowCount = dashboardScreen.getActivityNewVsInProcessItemTableRowCount();

		dashboardScreen.clickOnNewVsInprocessVerticalDashboardGraphBar();

		int updatedRowCount = dashboardScreen.getActivityNewVsInProcessItemTableRowCount();

		// Verification of Process Due button label
		AssertionUtil.verifyGreaterThan(updatedRowCount, initialRowCount,
				"Verification of \'WorkItem\' table should be display when click on Dashboard Graph Bar in ActivityA Dashboard NewVsInprocess Tab screen");

	}

	@Test(description = "Verify Lock Icon on different action", priority = 1)
	public void verifyLockIcon() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MyTest", "MyTest1");
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnClosureAction();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		workItemsScreen.sortDocIDInDescOrder();

		// Verification of Show Assigned Only Checkbox
		AssertionUtil.verifyEqual(workItemsScreen.getSelectionStatusForShowAssignedOnlyCheckbox(), false,
				"Verification of \'Show Assigned Only\' checkbox default selection status");

		// Verification of First Row Checkbox
		AssertionUtil.verifyEqual(workItemsScreen.getSelectionStatusForFirstRowCheckbox(), false,
				"Verification of \'First Row\' checkbox default selection status");

		String tableCustomerNameValue = workItemsScreen.getFirstAvailableCustomerNameData();
		boolean isLockIconExists = workItemsScreen.getExistenceStatusForFirstRowLockIcon();

		// Verification of default existence status for Lock Icon
		AssertionUtil.verifyEqual(isLockIconExists, false,
				"Verification of \'Lock Icon\' should be display by default");

		newDocScreen = workItemsScreen.clickOnFirstAvailableCustomerName();

		newDocScreen.switchToDocIDNewPageScreen().clickOnCustomerInformationLink();

		String customerNameTextFieldValue = newDocScreen.getCustomerNameFieldValue();

		// Verification of CustomerName Text Field value with Table's CustomerName value
		AssertionUtil.verifyEqual(customerNameTextFieldValue, tableCustomerNameValue,
				"Verification of CustomerName textfield value with Customer Name row value");

		workItemsScreen = newDocScreen.switchToWorkItemsScreen();

		isLockIconExists = workItemsScreen.getExistenceStatusForFirstRowLockIcon();

		// Verification of existence status for Lock Icon when user work on the doc
		AssertionUtil.verifyEqual(isLockIconExists, true,
				"Verification of \'Lock Icon\' should display when user works on Work item");

		workItemsScreen.navigateAndClickToUnlockWorkItemLink();

		// Verification of Message on Work Item Actions Dialog
		AssertionUtil.verifyEqual(workItemsScreen.getWorkItemActionsDialogMessage(),
				"Work item(s) unlocked successfully",
				"Verification of Message when click on Unlock Work item on Work Item Actions Dialog");

		workItemsScreen.clickOnCloseButtonOnWorkItemActionDialog();

		isLockIconExists = workItemsScreen.getExistenceStatusForFirstRowLockIcon();

		// Verification of Lock Icon should not display when Work item(s) unlocked
		AssertionUtil.verifyEqual(isLockIconExists, false,
				"Verification of \'Lock Icon\' should not display when Work item(s) unlocked");

	}

	@Test(description = "Verify Assigned Work Item with message", priority = 1)
	public void verifyAssignedWorkItem() {

		String customerName = "MyTest" + new CommonUtil().getCurrentDateTime();

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		mainPage.createNewDocumentWithCustomerDetails("Closure Action", "Closure Action", "MyTest", customerName);
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnClosureAction();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityALink();
		workItemsScreen.sortDocIDInDescOrder();

		String customerNameValue = workItemsScreen.getFirstAvailableCustomerNameData();

		workItemsScreen.clickOnFirstRowCheckbox();

		workItemsScreen.navigateAndClickToAssignWorkItemLink();

		workItemsScreen.selectValueFromSelectDropdownOnAssignWorkItemDialog("Users");
		workItemsScreen.selectValueFromAssignToListDropdownOnAssignWorkItemDialog("Automation user");
		workItemsScreen.clickOnAssignWorkItemsButtonOnAssignWorkItemDialog();

		// Verification of Message on Work Item Actions Dialog
		AssertionUtil.verifyEqual(workItemsScreen.getWorkItemActionsDialogMessage(),
				"Successfully Re-assigned the WorkItems to - Automation user",
				"Verification of Message when click on Assign Work item on Work Item Actions Dialog");

		workItemsScreen.clickOnCloseButtonOnWorkItemActionDialog();
		workItemsScreen.clickOnShowAssignedOnlyCheckbox();

		workItemsScreen.sortDocIDInDescOrder();
		String assignedCustomerNameValue = workItemsScreen.getFirstAvailableCustomerNameData();

		// Verification of Assigned Work Item
		AssertionUtil.verifyEqual(assignedCustomerNameValue, customerNameValue,
				"Verification of Assigned Work Item should be display when selects Show Assigned Only Checkbox");

	}

	@Test(description = "Verify Route to another ActivityC Test", priority = 1)
	public void verifyRouteActivityC_MW010_MW11() {

		mainPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myWorkScreen = mainPage.clickOnMyWorkTab();
		myWorkScreen.clickOnClosureAction();
		myWorkScreen.expandClosureAction();
		workItemsScreen = myWorkScreen.clickOnActivityCLink();
		int BeforeActivityCountC = workItemsScreen.getActivityCount();
		myWorkScreen.clickOnActivityALink();
		String beforeActivityACustomerName = workItemsScreen.getFirstAvailableCustomerNameData();
		int BeforeActivityCountA = workItemsScreen.getActivityA_Count();
		workItemsScreen.activityAAction();
		myWorkScreen.clickOnActivityCLink();
		int AfterActivityCountC = workItemsScreen.getActivityCount();
		myWorkScreen.clickOnActivityALink();
		int AfterActivityCountA = workItemsScreen.getActivityA_Count();
		int ActivityCCount = BeforeActivityCountC + 1;
		int ActivityACount = BeforeActivityCountA - 1;
		AssertionUtil.verifyEqual(ActivityCCount, AfterActivityCountC,
			"Verified Count of Activty C is increased by 1 after Action transfer is performed ");
		
		AssertionUtil.verifyEqual(ActivityACount, AfterActivityCountA,
				"Verified Count of Activty A is decreased by 1 after Action transfer is performed ");
		myWorkScreen.clickOnActivityALink();
		
		String afterActivityACustomerName = workItemsScreen.getFirstAvailableCustomerNameData();
		AssertionUtil.verifyNotEqual(beforeActivityACustomerName, afterActivityACustomerName,
				"Verified after transfer Closure Action Activity from Activity A to Activity C, Customer Name is not present");
	}

}
