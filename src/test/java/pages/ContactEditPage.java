package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class ContactEditPage extends ProjectSpecificationMethods {

	WebDriver driver;

	@FindBy(id = "firstName")
	WebElement firstNameField;

	@FindBy(id = "lastName")
	WebElement lastNameField;

	@FindBy(id = "email")
	WebElement emailField;

	@FindBy(id = "phone")
	WebElement phoneField;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement saveButton;

	@FindBy(xpath = "//span[@id='error']")
	WebElement errorMessage;

	public ContactEditPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ContactEditPage editLastName(String lastName) {
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
		return this;
	}

	public ContactEditPage editEmail(String email) {
		emailField.clear();
		emailField.sendKeys(email);
		return this;
	}

	public ContactEditPage editPhone(String phone) {
		phoneField.clear();
		phoneField.sendKeys(phone);
		return this;
	}

	public ContactListPage saveContact() {
		saveButton.click();
		return new ContactListPage(driver);
	}

	public boolean isErrorDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
	}

	public ContactEditPage editFirstName(String newFirstName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).clear();
		firstNameField.sendKeys(newFirstName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('readonly')", firstNameField);

		firstNameField.clear();
		firstNameField.sendKeys(newFirstName);

		return this;
	}

}
