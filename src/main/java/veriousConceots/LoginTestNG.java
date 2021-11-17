package veriousConceots;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTestNG {

 WebDriver driver;
 String browser;
 String url = null;

@BeforeClass
public void readConfig() {
	
	Properties prop = new Properties();
	
	try {
		
	InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
	prop.load(input);
     browser = prop.getProperty("browser");
     url = prop.getProperty("url");
	
	}
	
	catch(IOException e ){
	
	e.printStackTrace();
	
}
}
	@BeforeMethod
	public void init() {
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
				
				driver = new ChromeDriver();
				
			}
			else if (browser.equalsIgnoreCase( "firefox")){System.setProperty("webdriver.gecko.driver","C:\\Users\\Sadeq\\eclipse-workspace2021\\2021sd\\Session5\\drivers\\geckodriver.exe");
			
			driver = new FirefoxDriver();
			}
		
		
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test(priority=1)
	public void login() {
	Assert.assertEquals(driver.getTitle(), "Login - iBilling", "wrong page");
	
	WebElement user_element = driver.findElement(By.xpath("//input[@id='username']"));
	WebElement password_element = driver.findElement(By.xpath("//input[@id='password']"));
	WebElement login_button_element = driver.findElement(By.xpath("//button[@name='login']"));

	
	user_element.clear();
	user_element.sendKeys("demo@techfios.com");

	password_element.sendKeys("abc123");
	
	login_button_element.click();
	
	System.out.println(driver.getTitle());
	
	
	Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "wrong page");
	}
	
	@Test (priority=2)
	public void addCustomerTest() throws InterruptedException {

		// Element Library
		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		By DASHBOARD_BUTTON = By.xpath("//span[contains(text(), 'Dashboard')]");
		By CUSTOMERS_BUTTON = By.xpath("//span[contains(text(), 'Customers')]");
		By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(), 'Add Customer')]");
		By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(text(),'Add Contact')]");
		By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
		By COMPANY_NAME_FIELD = By.xpath("//*[@id=\'cid\']");
		By EMAIL_FIELD = By.xpath("//input[@id='email']");
		By PHONE_FIELD = By.xpath("//input[@id='phone']");
		By ADDRESS_FIELD = By.xpath("//input[@id='address']");
		By CITY_FIELD = By.xpath("//input[@id='city']");
		By STATE_REGION_FIELD = By.xpath("//input[@id='state']");
		By ZIP_FIELD = By.xpath("//input[@id='zip']");
		By SUBMIT_BUTTON = By.xpath("//button[@class='btn btn-primary']");
		By LIST_CONTACTS_BUTTON = By.xpath("//a[contains(text(),'List Contacts')]");	
		
		//loginData
		String loginId = "demo@techfios.com";
		String password = "abc123";
		
		//Test Data
			
		String fullName= "Test Spring";
		String companyName = "Techfios";
		String emailAddress = "@gmail.com";
		String phoneNumber = "2316564564";
		
		//Perform Login In
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "wrong page");
		
				driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
				driver.findElement(PASSWORD_FIELD).sendKeys(password);
				driver.findElement(SIGNIN_BUTTON).click();
				
				Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "wrong page");	
			
				driver.findElement(CUSTOMERS_BUTTON).click();
				//wait
				waitForElement (driver,5,ADD_CUSTOMER_BUTTON);
				
				driver.findElement(ADD_CUSTOMER_BUTTON).click();
				
				
				
				Random rnd = new Random();
				int rand =rnd.nextInt(5);
				driver.findElement(FULL_NAME_FIELD).sendKeys(fullName+rand);
				
				waitForElement(driver,5,FULL_NAME_FIELD);
				
				dropDown(COMPANY_NAME_FIELD,companyName);
				
				
				
				waitForElement (driver,5,ADD_CUSTOMER_BUTTON);
	}
	
	public void dropDown(By locater,String selescted) {
		Select sel = new Select(driver.findElement(locater));
		sel.selectByVisibleText(selescted);
	}

	public void waitForElement(WebDriver driver, int timeInSeconds , By locater) {
		WebDriverWait wait = new WebDriverWait(driver,timeInSeconds);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
		
		
		
		
		
	}

	//@AfterMethod
	public void tareDown() {
	
		driver.getClass();
		driver.quit();
	}
}
