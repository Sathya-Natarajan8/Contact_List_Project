package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class LoginPage extends ProjectSpecificationMethods{
	WebDriver driver;

	@FindBy(xpath = "//input[@id='email']")
	WebElement emailField;

	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordField;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement loginButton;

	@FindBy(xpath = "//span[@id='error']")
	WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage enterEmail(String email) {
		emailField.sendKeys(email);
		return this;
	}

	public LoginPage enterPassword(String password) {
		passwordField.sendKeys(password);
		return this;
	}

	public LoginResult clickLogin() {
		loginButton.click();
		return new LoginResult(driver);
	}

	public boolean isLoginButtonVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
	}

	public boolean isLoginErrorDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
	}

	public HomePage login(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickLogin();
		return new HomePage(driver);
	}

}
