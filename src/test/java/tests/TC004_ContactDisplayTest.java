package tests;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.ContactAdditionPage;
import pages.ContactListPage;
import pages.LoginPage;

public class TC004_ContactDisplayTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "ContactDisplayTest";
		testName = "Contact Display Test";
		testDescription = "Testing if contacts are displayed correctly and sorted alphabetically";
		testAuthor = "Sathya";
		testCategory = "UI Testing";
	}

	@Test(priority = 1)
	public void verifyContactDetailsDisplay() {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login("sathyanatrajan8@gmail.com", "Arun8819");

		ContactAdditionPage contactAdditionPage = homePage.navigateToAddContact();
		contactAdditionPage.enterFirstName("Alex").enterLastName("Brown").enterEmail("alex@example.com")
				.enterPhone("+91 9876543210").clickAddContact();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='contactTable']")));

		ContactListPage contactListPage = new ContactListPage(driver);

		Assert.assertTrue(contactListPage.isContactDisplayed("Alex", "Brown", "alex@example.com", "+91 9876543210"),
				"Contact details are not displayed correctly!");
	}

	@Test(priority = 2)
	public void verifyContactSortingByLastName() {
		ContactListPage contactListPage = new ContactListPage(driver);

		Assert.assertTrue(contactListPage.isContactListSorted(),
				"Contacts are not sorted alphabetically by last name!");
	}

	@Test(priority = 3)
	public void verifyPhoneNumberFormatting() {
		ContactListPage contactListPage = new ContactListPage(driver);

		Assert.assertTrue(contactListPage.isPhoneNumberFormattedCorrectly(),
				"Phone numbers are not formatted correctly!");
	}
}
