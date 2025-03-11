package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class UtilsClass {

    public static WebDriver driver;
    public String sheetname;
    public String testName, testDescription, testAuthor, testCategory;

    /** Launches the browser and navigates to the URL */
    public void browserLaunch(String browser, String url) {
        System.out.println("Launching browser: " + browser);

        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else {
            System.out.println("Invalid browser! Defaulting to Chrome.");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(url);
        System.out.println("Opened URL: " + url);
    }

    /** Closes the browser session */
    public void closeBrowser() {
        if (driver != null) {
            driver.quit(); // Ensures all browser instances are closed
            System.out.println("Browser closed successfully.");
        }
    }

    /** Reads Excel file and returns data as a 2D String array */
    public static String[][] readExcel(String sheetname) throws IOException {
        String filePath = System.getProperty("C:\\Users\\sathy\\eclipse-workspace\\Contact_List_Project\\src\\test\\resources\\ContactListPOMProject.xlsx");
        FileInputStream file = new FileInputStream(new File(filePath));
        XSSFWorkbook book = new XSSFWorkbook(file);
        XSSFSheet sheet = book.getSheet(sheetname);

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        System.out.println("Row count: " + rowCount);
        System.out.println("Column count: " + columnCount);

        String[][] data = new String[rowCount][columnCount];

        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);

            for (int j = 0; j < columnCount; j++) {
                XSSFCell cell = row.getCell(j);

                if (cell != null) {
                    data[i - 1][j] = cell.toString(); // Handle different cell types
                } else {
                    data[i - 1][j] = ""; // Assign empty string for null cells
                }
            }
        }

        book.close();
        file.close();
        return data;
    }

    /** Captures a screenshot and saves it in the 'snap' folder */
    public static String screenshot(String name) throws IOException {
        String path = System.getProperty("C:\\Users\\sathy\\eclipse-workspace\\Contact_List_Project\\snap" + name + ".png");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(path);
        FileUtils.copyFile(src, dest);
        return path;
    }
}