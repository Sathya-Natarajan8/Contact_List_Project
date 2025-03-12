package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ContactListPage;
import pages.LoginPage;
import pages.LogoutPage;

public class TC007_LogoutTest extends ProjectSpecificationMethods {

	@Test
	public void verifyLogoutFunctionality() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("sathyanatrajan8@gmail.com", "Arun8819");
		ContactListPage contactListPage = new ContactListPage(driver);
		LogoutPage logoutPage = contactListPage.goToLogoutPage();
		Assert.assertTrue(logoutPage.isLogoutButtonVisible(), "Logout button is not visible!");
		LoginPage redirectedLoginPage = logoutPage.logout();
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Logout failed, not redirected to login page!");
	}
}