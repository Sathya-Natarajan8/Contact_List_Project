package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.ContactListPage;
import pages.ContactEditPage;
import pages.LoginPage;

public class TC005_ContactEditTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "ContactEditTest";
		testName = "Contact Edit Test";
		testDescription = "Testing contact editing functionality";
		testAuthor = "Sathya";
		testCategory = "Functional Testing";
	}

	@Test(priority = 1)
	public void verifyContactEditFunctionality() {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login("sathyanatrajan8@gmail.com", "Arun8819");

		ContactListPage contactListPage = new ContactListPage(driver);

		ContactEditPage contactEditPage = contactListPage.clickContact("Alex", "Brown");

		contactEditPage.editFirstName("Alexander").editLastName("Brown").editEmail("alex.brown@example.com")
				.editPhone("+91 9988776655").saveContact();

		Assert.assertTrue(
				contactListPage.isContactDisplayed("Alexander", "Brown", "alex.brown@example.com", "+91 9988776655"),
				"Edited contact details are not displayed correctly!");
	}

	@Test(priority = 2)
	public void verifyEmptyFieldsRestriction() {
		ContactListPage contactListPage = new ContactListPage(driver);

		ContactEditPage contactEditPage = contactListPage.clickContact("Alexander", "Brown");

		contactEditPage.editFirstName("").editEmail("").saveContact();

		Assert.assertTrue(contactEditPage.isErrorDisplayed(), "Error message not displayed for empty fields!");
	}
}
