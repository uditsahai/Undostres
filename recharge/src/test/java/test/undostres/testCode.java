package test.undostres;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Resources.utilityClass;

public class testCode extends utilityClass {
	
	public WebDriver driver;
	public Properties properties;
	
	
	@Test
	public void rechargePage() throws IOException
	{
		driver= browserDetails();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://undostres.com.mx/");
		WebElement operator=driver.findElement(By.xpath("//input[@name='operator'][@suggest='mobile-operator']"));
		operator.click();
		WebElement operatorname=driver.findElement(By.linkText("Telcel"));
		operatorname.click();
		WebElement number=driver.findElement(By.xpath("//input[@name='mobile'][@oninput='checkMob(this)']"));
		number.sendKeys("5523261151");
		WebElement amount=driver.findElement(By.xpath("//input[@name='amount'][@suggest='mobile-operator_amount']"));
		amount.click();
		WebElement rechargeamount =driver.findElement(By.xpath("//li[@data-show='$10 (Recarga Saldo)']"));
		rechargeamount.click();
		WebElement following=driver.findElement(By.cssSelector("button.button.buttonRecharge"));
		following.click();		
	}
	
	@Test(dependsOnMethods = "rechargePage")
	public void paymentDetails()
	{
		WebElement card=driver.findElement(By.cssSelector("a[class*='select-card']"));
		card.click();
		WebElement cardname=driver.findElement(By.xpath("(//input[@name='cardname'])[2]"));
		cardname.sendKeys("Test");
		//driver.findElement(By.xpath("(\"//*[@id=\\\"cardnumberunique\\\"]\""));
		WebElement cardnumber=driver.findElement(By.cssSelector("*[id='cardnumberunique']"));
		cardnumber.sendKeys("4111111111111111");
		WebElement expirymonth=driver.findElement(By.xpath("(//input[@name='expmonth'])[2]"));
		expirymonth.sendKeys("11");
		WebElement expiryyear=driver.findElement(By.xpath("(//input[@name='expyear'])[2]"));
		expiryyear.sendKeys("2025");
		WebElement cvv=driver.findElement(By.xpath("(//input[@name='cvvno'])[2]"));
		cvv.sendKeys("111");
		WebElement email=driver.findElement(By.xpath("//input[@class='form-control email']"));
		email.sendKeys("test@test.com");
		WebElement button=driver.findElement(By.xpath("//button[@name='formsubmit']"));
		button.click();
	}
	
	
	@Test(dependsOnMethods = "paymentDetails")
	public void userlogin()
	{
		WebDriverWait wait=new WebDriverWait(driver,60);
		WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='email'])[1]")));
		email.click();
		email.sendKeys("automationexcersise@test.com");
		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='password'])[1]")));
		password.click();
		password.sendKeys("123456");
		WebElement frame = driver.findElement(By.cssSelector("iframe[name^=a]"));
		driver.switchTo().frame(frame);
		WebElement captcha = driver.findElement(By.cssSelector("div.recaptcha-checkbox-border"));
		captcha.click();
		driver.switchTo().parentFrame();
		WebElement access=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='loginBtn']")));
		//WebElement access = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#loginBtn")));
		access.click();	
	}
	
	@Test(dependsOnMethods = "userlogin")
	public void readMessage()
	{
		//WebDriverWait wait=new WebDriverWait(driver,60);
		//WebElement like=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"u_0_1\"]/div/button/span")));
		//like.click();
		//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//WebElement text=driver.findElement(By.linkText("Why did my payment fail? "));
		//String txt=text.getText();
		//System.out.println(txt);
		WebDriverWait wait=new WebDriverWait(driver,60);
		WebElement message=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainBlueContainer\"]/div/div[1]/div[1]/div/div/div/div[1]")));
		String msg=message.getText();
		System.out.println("msg");
			
		}
	
}
