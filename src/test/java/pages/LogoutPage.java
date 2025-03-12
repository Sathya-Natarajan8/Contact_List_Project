package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class LogoutPage extends ProjectSpecificationMethods {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLogoutButtonVisible() {
        try {
            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage logout() {
        try {
            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout")));
            logoutButton.click();

            wait.until(ExpectedConditions.urlContains("login"));

            System.out.println("Logout Successful, redirected to Login Page.");
        } catch (Exception e) {
            System.out.println("Logout Failed: " + e.getMessage());
        }
        return new LoginPage(driver);
    }
}

