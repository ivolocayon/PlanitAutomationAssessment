import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCase2 {	
	
	@Test(invocationCount = 5)
    public void  testcase2() throws InterruptedException {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,30);

		//Test URLs
        String testUrl = "http://jupiter.cloud.planittesting.com";
        String testUrl2 = "http://jupiter2.cloud.planittesting.com";
      
        // launch Google Chrome and direct it to the Base URL
        driver.manage().window().maximize();
        driver.get(testUrl);
       
        //From the home page go to contact page
        WebElement contactTab = driver.findElement(By.xpath("//a[contains(@href,'contact')]"));
        contactTab.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Forename')]//following::input[1]")));
        
    	//Populate mandatory fields
        System.out.println("Populating required fields..");
    	WebElement forenameFld = driver.findElement(By.xpath("//label[contains(text(),'Forename')]//following::input[1]"));
    	WebElement emailFld = driver.findElement(By.xpath("//label[contains(text(),'Email')]//following::input[1]"));
    	WebElement messageFld = driver.findElement(By.xpath("//label[contains(text(),'Message')]//following::textarea[1]"));
    	forenameFld.sendKeys("John");
    	emailFld.sendKeys("john@test.com");
    	messageFld.sendKeys("This is a sample message");
     	
        //click submit button
        WebElement submitBtn = driver.findElement(By.xpath("//a[text()='Submit']"));
        submitBtn.click();
        
        //Validate successful submission message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert alert-success')]")));
        WebElement successMsg = driver.findElement(By.xpath("//div[contains(@class,'alert alert-success')]"));
        if(successMsg.isDisplayed()) {
        	System.out.println("[PASSED] Success Message is Displayed");
        }
        else {
        	System.out.println("[PASSED] Success Message is NOT Displayed");
        }
    	Thread.sleep(3000);
    	
        //close Google Chrome
        driver.close();
       
    }
}
