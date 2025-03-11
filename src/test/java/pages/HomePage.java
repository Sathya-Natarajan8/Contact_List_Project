package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class HomePage extends ProjectSpecificationMethods {

    @FindBy(xpath = "//a[contains(text(),'Sign up')]")
    WebElement signUpLink;

   
    public HomePage(WebDriver driver) {
        UtilsClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /** Clicks on the Sign Up link and navigates to the Sign Up Page */
    public SignUpPage clickSignUp() {
        signUpLink.click();
        return new SignUpPage(driver);
    }

}
