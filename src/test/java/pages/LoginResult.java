package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ProjectSpecificationMethods;

public class LoginResult extends ProjectSpecificationMethods {

    WebDriver driver;

    @FindBy(xpath = "///h1[text()='Contact List']")
    WebElement contactListHeader;

    @FindBy(xpath = "//span[@id='error']")
    WebElement loginErrorMessage;

    public LoginResult(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifySuccessfulLogin() {
        String actualHeader = contactListHeader.getText();
        Assert.assertEquals(actualHeader, "Contact List", "Login failed! User not redirected to Contact List.");
        System.out.println("Login Successful: User is on the Contact List page.");
    }

    public void verifyLoginFailure() {
        Assert.assertTrue(loginErrorMessage.isDisplayed(), "Login error message not displayed for invalid credentials.");
        System.out.println("Login Failed: Incorrect email or password.");
    }

    public boolean isContactListDisplayed() {
        return driver.getCurrentUrl().contains("contact-list");
    }
}

