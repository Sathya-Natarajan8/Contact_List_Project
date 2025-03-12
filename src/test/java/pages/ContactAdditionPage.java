package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class ContactAdditionPage extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(id = "firstName")
	WebElement firstNameField;

	@FindBy(id = "lastName")
	WebElement lastNameField;

	@FindBy(id = "email")
	WebElement emailField;

	@FindBy(id = "phone")
	WebElement phoneField;

	@FindBy(id = "birthDate")
	WebElement birthDateField;

	@FindBy(id = "address")
	WebElement addressField;

	@FindBy(xpath = "//table[@id='myTable']//th[contains(text(),'City')]")
	WebElement cityStatePostalField;

	@FindBy(id = "country")
	WebElement countryField;

	@FindBy(xpath = "//button[contains(@onclick, 'addContact')]")
	WebElement addContactButton;

	@FindBy(xpath = "//span[@id='error']")
	WebElement errorMessage;

	public ContactAdditionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ContactAdditionPage enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
		return this;
	}

	public ContactAdditionPage enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
		return this;
	}

	public ContactAdditionPage enterEmail(String email) {
		emailField.sendKeys(email);
		return this;
	}

	public ContactAdditionPage enterPhone(String phone) {
		phoneField.sendKeys(phone);
		return this;
	}

	/** Enters Birth Date */
	public ContactAdditionPage enterBirthDate(String birthDate) {
		birthDateField.sendKeys(birthDate);
		return this;
	}

	public ContactAdditionPage enteraddress(String address) {
		addressField.sendKeys(address);
		return this;
	}

	public ContactAdditionPage entercityStatePostal(String cityStatePostal) {
		cityStatePostalField.sendKeys(cityStatePostal);
		return this;
	}

	public ContactAdditionPage entercountry(String country) {
		countryField.sendKeys(country);
		return this;
	}

	public ContactListPage clickAddContact() {
		addContactButton.click();
		return new ContactListPage(driver);
	}

	public boolean isErrorDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
	}
}
