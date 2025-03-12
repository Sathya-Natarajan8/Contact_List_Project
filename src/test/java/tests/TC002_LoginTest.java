package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.LoginPage;
import pages.LoginResult;

public class TC002_LoginTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "LoginTest";
		testName = "LoginTest";
		testDescription = "Testing login functionality with valid and invalid credentials";
		testAuthor = "Sathya";
		testCategory = "Smoke Testing";
	}

	@Test(dataProvider = "excelRead")
	public void loginTest(String email, String password, String expectedOutcome) {
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = homePage.clickLogin();
		LoginResult contactListPage = loginPage.enterEmail(email).enterPassword(password).clickLogin();

		if (expectedOutcome.equalsIgnoreCase("Success")) {
			AssertJUnit.assertTrue("Login failed with valid credentials!", contactListPage.isContactListDisplayed());
		} else if (expectedOutcome.equalsIgnoreCase("Fail")) {
			AssertJUnit.assertTrue("Error message not displayed for invalid login!", loginPage.isLoginErrorDisplayed());
		}
	}
}
