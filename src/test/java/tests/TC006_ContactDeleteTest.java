package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import base.ProjectSpecificationMethods;
import pages.ContactListPage;
import pages.HomePage;
import pages.LoginPage;

public class TC006_ContactDeleteTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "ContactDeleteTest";
		testName = "Contact Delete Test";
		testDescription = "Testing contact deletion functionality";
		testAuthor = "Sathya";
		testCategory = "Functional Testing";
	}

	@Test(dataProvider = "excelRead")
	public void verifyDeleteContact(String firstName, String lastName, String email, String phone) {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login("sathyanatrajan8@gmail.com", "Arun8819");
		ContactListPage contactListPage = new ContactListPage(driver);

		Assert.assertTrue(contactListPage.isContactDisplayed(firstName, lastName), "Contact does not exist!");

		contactListPage.deleteContact(firstName, lastName);

		Assert.assertFalse(contactListPage.isContactDisplayed(firstName, lastName), "Contact was not deleted!");
	}
}