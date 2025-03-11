package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class SignUpPage extends ProjectSpecificationMethods {

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement email;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement password;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submitButton;

    @FindBy(xpath = "//button[text()='Cancel']")
    WebElement cancelButton;

    public SignUpPage(WebDriver driver) {
        UtilsClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage enterFirstName(String fName) {
        firstName.sendKeys(fName);
        return this;
    }

    public SignUpPage enterLastName(String lName) {
        lastName.sendKeys(lName);
        return this;
    }

    public SignUpPage enterEmail(String emailAddress) {
        email.sendKeys(emailAddress);
        return this;
    }

    public SignUpPage enterPassword(String pwd) {
        password.sendKeys(pwd);
        return this;
    }

    public SignUpResult clickSubmit() {
        submitButton.click();
        return new SignUpResult(driver);
    }

    public void clickCancel() {
        cancelButton.click();
    }
}