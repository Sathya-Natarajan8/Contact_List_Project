package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilsClass {

	public static WebDriver driver;
	public String sheetname;
	public String testName, testDescription, testAuthor, testCategory;

	public void browserLaunch(String browser, String url) {

		if (browser.contentEquals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void closeBrowser() {

		driver.close();
	}

	public static String[][] readExcel(String sheetname) throws IOException {
		String filePath = "C:\\\\Users\\sathy\\eclipse-workspace\\Contact_List_Project\\src\\test\\resources\\ContactListPOMProject.xlsx";
		FileInputStream file = new FileInputStream(new File(filePath));
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheet(sheetname);

		int rowCount = sheet.getPhysicalNumberOfRows();
		int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

		String[][] data = new String[rowCount - 1][columnCount];

		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				XSSFCell cell = row.getCell(j);
				if (cell != null) {
					data[i - 1][j] = formatter.formatCellValue(cell);
				} else {
					data[i - 1][j] = "";
				}
			}
		}

		book.close();
		file.close();
		return data;
	}

	public static String screenshot(String name) {
		if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
			System.out.println("WebDriver session is null. Skipping screenshot.");
			return "";
		}

		try {
			String path = "C:\\Users\\sathy\\eclipse-workspace\\Contact_List_Project\\snap\\" + name + ".png";
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(path);
			FileUtils.copyFile(src, dest);
			return path;
		} catch (Exception e) {
			System.out.println("Error while capturing screenshot: " + e.getMessage());
			return "";
		}
	}

	public static void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitForAlertAndAccept() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	public static void waitForPageLoad() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
