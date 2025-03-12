package tests;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.LoginPage;
import pages.ContactAdditionPage;
import pages.ContactListPage;

public class TC003_AddContactTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "AddContactTest";
		testName = "AddContactTest";
		testDescription = "Testing contact addition functionality with various scenarios";
		testAuthor = "Sathya";
		testCategory = "Functional Testing";
	}

	@Test(priority = 1, dataProvider = "excelRead")
	public void testAddContact() {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login("sathyanatrajan8@gmail.com", "Arun8819");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-contact")));

		ContactAdditionPage contactPage = homePage.navigateToAddContact();
	}

	@Test(priority = 2, dataProvider = "excelRead")
	public void addContactTest(String firstName, String lastName, String email, String phone, String birthDate,
			String address, String cityStatePostal, String country, String expectedOutcome) {
		HomePage homePage = new HomePage(driver);
		ContactAdditionPage contactAdditionPage = homePage.navigateToAddContact();

		contactAdditionPage.enterFirstName(firstName).enterLastName(lastName).enterEmail(email).enterPhone(phone)
				.enterBirthDate(birthDate).enteraddress(address).entercityStatePostal(cityStatePostal)
				.entercountry(country).clickAddContact();

		ContactListPage contactListPage = new ContactListPage(driver);

		if (expectedOutcome.equalsIgnoreCase("Success")) {
			AssertJUnit.assertTrue("Contact was not added!", contactListPage.isContactListDisplayed());
		} else if (expectedOutcome.equalsIgnoreCase("Fail")) {
			AssertJUnit.assertTrue("Error message not displayed for invalid input!",
					contactAdditionPage.isErrorDisplayed());
		}
	}
}
