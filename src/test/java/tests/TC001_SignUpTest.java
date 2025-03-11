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
        sheetname = "SignUpPageTest";
        testName = "SignUpTest";
        testDescription = "Testing the signup functionality with positive and negative cases";
        testAuthor = "Sathya";
        testCategory = "Smoke Testing";
    }

    @Test(dataProvider = "excelRead")
    public void signUpTest(String firstName, String lastName, String email, String password, String expectedResult) {
        HomePage homePage = new HomePage(driver);

        // Navigate to Sign Up Page
        SignUpPage signUpPage = homePage.clickSignUp();

        // Fill Signup Form
        signUpPage.enterFirstName(firstName)
                  .enterLastName(lastName)
                  .enterEmail(email)
                  .enterPassword(password)
                  .clickSubmit();

        // Verify Signup Result
        SignUpResult signUpResult = new SignUpResult(driver);
        signUpResult.verifySuccessfulSignup();
    }
}
