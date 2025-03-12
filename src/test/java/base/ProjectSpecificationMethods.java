package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.UtilsClass;

public class ProjectSpecificationMethods extends UtilsClass {

	@BeforeMethod
	@Parameters({ "browser" })
	public void lauchBrowser(@Optional("Chrome") String browser) {
		browserLaunch(browser, "https://thinking-tester-contact-list.herokuapp.com");

	}

	@DataProvider(name = "excelRead")
	public String[][] excelRead() throws IOException {
		return readExcel(sheetname);
	}

	@AfterMethod
	public void close() {
		try {
			if (driver != null && ((RemoteWebDriver) driver).getSessionId() != null) {
				if (isAlertPresent()) {
					driver.switchTo().alert().accept();
				}

				driver.quit();
				System.out.println("Browser closed successfully.");
			} else {
				System.out.println("Driver is already closed or session is null.");
			}
		} catch (Exception e) {
			System.out.println("Error while closing browser: " + e.getMessage());
		}
	}

	public boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
