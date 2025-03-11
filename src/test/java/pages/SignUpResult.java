package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class SignUpResult extends ProjectSpecificationMethods {



    @FindBy(xpath = "//h1[contains(text(),'Add User')]")
    WebElement addUserHeader;

	public SignUpResult(WebDriver driver) {
		UtilsClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

    public void verifySuccessfulSignup() {
        String pageHeader = addUserHeader.getText();
        Assert.assertEquals(pageHeader, "Add User", "Signup failed! The page did not redirect properly.");
    }
}

