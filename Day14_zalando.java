package seleniumPractiseSessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day14_zalando {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.Chrome.driver", "./drivers/Chromedriver.exe");
		
		ChromeOptions option=new ChromeOptions();
		option.addArguments("disable Notifications");
		ChromeDriver driver=new ChromeDriver(option);
		
		WebDriverWait wait=new WebDriverWait(driver,30);
		JavascriptExecutor  js=(JavascriptExecutor) driver;  //page scrolldown
        js.executeScript("window.scrollBy(0,1000)");
		driver.manage().window().maximize();
		
		
		//1) Go to https://www.zalando.com/
		driver.get("https://www.zalando.com/");
		
		//2) Get the Alert text and print it
		Thread.sleep(2000);
		Alert alerttext = driver.switchTo().alert();
		 String textmsg = alerttext.getText();
		 System.out.println("the alert message is:"+textmsg);
		
		
		//3) Close the Alert box and click on Zalando.uk
		 Thread.sleep(2000);
		 alerttext.accept();
		 driver.findElementByXPath("//a[text()='Zalando.uk']").click();
		
		//4) Click Women--> Clothing and click Coat 
		 Thread.sleep(2000);
		 driver.findElementByXPath("(//span[text()='Women'])[1]").click();
		 Thread.sleep(1000);
		 driver.findElementByXPath("(//span[text()='Clothing'])[1]").click();
		 Thread.sleep(2000);
		 driver.findElementByXPath("(//a[text()='Coats'])[3]").click();
		 Thread.sleep(3000);
		 driver.findElementByXPath("//button[@id='uc-btn-accept-banner']").click();
		
		//5) Choose Material as cotton (100%) and Length as thigh-length
		 Thread.sleep(2000);
		 driver.findElementByXPath("//span[text()='Material']").click();
		 driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		 driver.findElementByXPath("//button[text()='Save']").click();
		 
		 Thread.sleep(3000);
		 driver.findElementByXPath("//span[text()='Length']").click();
		 driver.findElementByXPath("//span[text()='thigh-length']").click();
		 driver.findElementByXPath("//button[text()='Save']").click();
		 Thread.sleep(3000);
		 
		//6) Click on Q/S designed by MANTEL - Parka coat
		driver.findElementByXPath("//div[text()='Q/S designed by']").click();
		Thread.sleep(3000);
		
		//7) Check the availability for Color as Olive and Size as 'M'
		driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//button[@id='picker-trigger']").click();
		driver.findElementByXPath("//span[text()='M']").click();
	    Thread.sleep(3000);
		//driver.findElementByXPath("//button[@class='_75ibAw HWFFx9 c2gWar xeY27O A95iT1 pDVUjz AHAcbe ElcxMs D41Vul _4H5FbR _4huXmv']").click();
		
		
		//driver.findElementByXPath("//span[text()='Remind me']").click();
		
		
		//8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
		Thread.sleep(3000);
		driver.findElementByXPath("//h2[text()='Out of stock']").isDisplayed();
		
		//System.out.println("olive clr is out of stock");
		driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
		driver.findElementByXPath("//button[@id='picker-trigger']").click();
		driver.findElementByXPath("//span[text()='M']").click();
		
		
		//9) Add to bag only if Standard Delivery is free
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='Add to bag']").click();
		
		//10) Mouse over on Your Bag and Click on "Go to Bag"
		Thread.sleep(3000);
		WebElement Bag = driver.findElementByXPath("//span[text()='Your bag']");
		Actions GotoBag=new Actions(driver);
		GotoBag.moveToElement(Bag).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Go to bag']").click();
		
		//11) Capture the Estimated Deliver Date and print
		String deliverydate = driver.findElementByXPath("//div[@data-id='delivery-estimation']").getText();
		System.out.println("estimated date is"+deliverydate);
		
		//12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
		Thread.sleep(1000);
		WebElement returns = driver.findElementByXPath("//a[text()='Free delivery & returns*']");
		Actions freedelivery=new Actions(driver);
		freedelivery.moveToElement(returns).perform();
		
		String tooltipmsg = driver.findElementByXPath("//a[text()='Free delivery & returns*']//parent::span").getAttribute("title");
		System.out.println("name of the title is "+tooltipmsg);
		
		//13) Click on Start chat in the Start chat and go to the new window
		driver.findElementByXPath("//a[text()='Free delivery & returns*']").click();
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='Start chat']/parent::button").click();

		Set<String> window = driver.getWindowHandles();
		List<String> wndList = new ArrayList<String>(window);
		driver.switchTo().window(wndList.get(1));
		Thread.sleep(5000);
		
		//14) Enter you first name and a dummy email and click Start Chat
		driver.findElementByXPath("//input[@placeholder='Your First Name*']").sendKeys("nyni");
		driver.findElementByXPath("//input[@placeholder='Your Email*']").sendKeys("nynik@gmail.com");
		driver.findElementByXPath("//span[text()='Start Chat']").click();
		Thread.sleep(3000);
		
		//15) Type Hi, click Send and print thr reply message and close the chat window.
		driver.findElementByXPath("//textarea[@id='liveAgentChatTextArea']").sendKeys("Hi", Keys.ENTER);
		String rplyMsg = driver.findElementByXPath("(//span[@class='messageText'])[4]").getText();
		System.out.println("Chat Msg:" + rplyMsg);
		driver.quit();
		

	}

	

}
