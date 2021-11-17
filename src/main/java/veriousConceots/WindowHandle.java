package veriousConceots;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandle {
	WebDriver driver;
	
	@Test
	public void init() {
		
		System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.yahoo.com/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		System.out.println(driver.getTitle());
		
		String handle1 = driver.getWindowHandle();
		//System.out.println(handle1);
		
		driver.findElement(By.xpath("//*[@id=\"ybar-sbq\"]")).sendKeys("xpath");

		driver.findElement(By.xpath("//*[@id=\"ybar-search\"]")).click();
		//System.out.println(driver.getTitle());
		

		String handle2 = driver.getWindowHandle();
		System.out.println(handle2);
		
		driver.findElement(By.partialLinkText("XPath Tutorial")).click();
		String handle3 = driver.getWindowHandle();
		Set<String> handle4 = driver.getWindowHandles();
		
		
		for(String i :handle4) {
			System.out.println(i);
		
			driver.switchTo().window(i);
			
		}
		
		System.out.println(driver.getTitle());
		driver.switchTo().window(handle1);
		System.out.println(driver.getTitle());
		driver.close();
}
	
}
