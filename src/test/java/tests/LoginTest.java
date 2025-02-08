package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import resources.Base;
import pageobjects.LoginPage;
import pageobjects.AccountPage;

public class LoginTest extends Base {
	
	public WebDriver driver;
	Logger log;
	
	
	@Test(dataProvider="getLoginData")
	public void login (String email, String password, String expectedStatus) throws IOException, InterruptedException {
		
		
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		//log.debug("Clicked on My Account dropdown");
		landingPage.loginOption().click();
		log.debug("Clicked on login option");
		
		Thread.sleep(3000);
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.emailAddressfield().sendKeys(email);
		log.debug("Email addressed got entered");
		loginPage.passwordfield().sendKeys(password);
		log.debug("Password got entered");
		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");
		
		AccountPage accountPage = new AccountPage(driver);
		
		String actualResult,expectedResult = "Successful";
		
		try {
			if(accountPage.editAccountInfoLink().isDisplayed());{
				log.debug("User got logged in");
				actualResult = "Successful";
			}
			System.out.println("Inside try");
		}catch(Exception e) {
			log.debug("User didn't log in");
			actualResult = "Failure";
			System.out.println("Inside catch");
		}
		
		if(actualResult.equals(expectedResult)) {
			
			log.info("Login Test Passed");
		}else {
			log.error("Login Test Failed");
		}
		
		//Assert.assertEquals(actualResult, expectedStatus);
	
		
		
	}
	
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}
	
	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("Browser got closed");
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = {{"anurag19578@gmail.com","Anurag@123","Successful"}};
		return data;
	}
	
 
}
