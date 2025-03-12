package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.SignUpPage;
import pages.SignUpResult;

public class TC001_SignUpTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "SignUpTest";
		testName = "SignUpTest";
		testDescription = "Testing the signup functionality with positive and negative cases";
		testAuthor = "Sathya";
		testCategory = "Smoke Testing";
	}

	@Test(dataProvider = "excelRead")
	public void signUpTest(String firstName, String lastName, String email, String password) {
		HomePage homePage = new HomePage(driver);

		SignUpPage signUpPage = homePage.clickSignUp();

		signUpPage.enterFirstName(firstName).enterLastName(lastName).enterEmail(email).enterPassword(password)
				.clickSubmit();

		SignUpResult signUpResult = new SignUpResult(driver);
		signUpResult.verifySuccessfulSignup();
	}
}
