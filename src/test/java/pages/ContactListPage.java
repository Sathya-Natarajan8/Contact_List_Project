package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.ProjectSpecificationMethods;

public class ContactListPage extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(xpath = "//h1[text()='Contact List']")
	WebElement contactListHeader;

	@FindBy(xpath = "//table[@class='contactTable']")
	WebElement contactTable;

	@FindBy(xpath = "//table[@class='contactTable']//tr")
	List<WebElement> contactRows;

	public ContactListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isContactListDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(contactListHeader));
		return contactListHeader.isDisplayed();
	}

	public boolean isContactDisplayed(String firstName, String lastName, String email, String phone) {
		System.out.println("Checking Contact: " + firstName + " " + lastName + " | " + email + " | " + phone);

		for (WebElement row : contactRows) {
			String rowText = row.getText();
			System.out.println("Row Found: " + rowText);

			if (rowText.contains(firstName) && rowText.contains(lastName) && rowText.contains(email)
					&& rowText.contains(phone)) {
				return true;
			}
		}
		System.out.println("Contact NOT found in table!");
		return false;
	}

	public List<String> getLastNames() {
		List<String> lastNames = new ArrayList<>();
		for (WebElement row : contactRows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			if (columns.size() > 1) {
				lastNames.add(columns.get(1).getText().trim());
			}
		}
		System.out.println("Last Names Found: " + lastNames);
		return lastNames;
	}

	public boolean isContactListSorted() {
		List<String> lastNames = getLastNames();
		List<String> sortedLastNames = new ArrayList<>(lastNames);
		sortedLastNames.sort(String::compareToIgnoreCase);
		return lastNames.equals(sortedLastNames);
	}

	public boolean isPhoneNumberFormattedCorrectly() {
		for (WebElement row : contactRows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			if (columns.size() > 3) {
				String phoneNumber = columns.get(3).getText().trim();
				System.out.println("Checking Phone Number: " + phoneNumber);

				if (!phoneNumber.matches("\\+\\d{1,3}\\s\\d+")) {
					return false;
				}
			}
		}
		return true;
	}

	// Click a Contact to Open Edit Page
	public ContactEditPage clickContact(String firstName, String lastName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> matchingContacts = driver
				.findElements(By.xpath("//table[@class='contactTable']//td[contains(text(),'" + firstName
						+ "') and contains(text(),'" + lastName + "')]"));

		if (matchingContacts.isEmpty()) {
			System.out.println("No Contact Found: " + firstName + " " + lastName);
			return null;
		}

		System.out.println("Clicking Contact: " + firstName + " " + lastName);
		wait.until(ExpectedConditions.elementToBeClickable(matchingContacts.get(0))).click();

		return new ContactEditPage(driver);
	}

	public void deleteContact(String firstName, String lastName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement deleteButton = driver.findElement(By.xpath("//tr[td[contains(text(),'" + firstName
					+ "')] and td[contains(text(),'" + lastName + "')]]//button[contains(text(),'Delete')]"));

			deleteButton.click();

			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();

			System.out.println("Contact Deleted Successfully: " + firstName + " " + lastName);
		} catch (NoSuchElementException e) {
			System.out.println("Contact Not Found: " + firstName + " " + lastName);
		}
	}

	public boolean isContactDisplayed(String firstName, String lastName) {
		try {

			return driver.findElement(By.xpath(
					"//tr[td[contains(text(),'" + firstName + "')]]//td[contains(text(),'" + lastName + "')]")) != null;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public LogoutPage goToLogoutPage() {
		return new LogoutPage(driver);
	}
}
