package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;


public class utilityClass {

	public WebDriver driver;
	public Properties properties;
	
	
	public WebDriver browserDetails() throws IOException
	{
		Properties properties=new Properties();
		FileInputStream FIS=new FileInputStream("C:\\Users\\Udit\\Desktop\\Undostres\\Undostres_1\\und\\recharge\\utility.properties");
		properties.load(FIS);
		String Browser=properties.getProperty("browserName");
		if(Browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.IE.driver","IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}
	
	@AfterClass
	public void closeBrowser() 
	{
		driver.quit(); 
		
	}
			
}
