package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class HomePage extends ProjectSpecificationMethods {

    @FindBy(xpath = "//button[@id='signup']")
    WebElement signUpLink;
    
    @FindBy(xpath = "//button[@id='submit']")
    WebElement loginLink;
    
    @FindBy(id = "add-contact")
    WebElement addContactForm;

   
    public HomePage(WebDriver driver) {
        UtilsClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage clickSignUp() {
        signUpLink.click();
        return new SignUpPage(driver);
    }
    public LoginPage clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }
    public boolean isLoginLinkVisible() {
        return loginLink.isDisplayed();
    }
        
        public ContactListPage navigateToContacts() {
        	addContactForm.click();
            return new ContactListPage(driver);
        }
 
        public ContactAdditionPage navigateToAddContact() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            try {
 
                WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-contact")));
                WebElement addContactButton = form.findElement(By.tagName("button"));
                addContactButton.click();
            } catch (Exception e) {
                System.out.println("Button not found, trying JavaScript Click...");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", addContactForm);
            }

            return new ContactAdditionPage(driver);
        }


}

