import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCase3 {	
	
	@Test
    public void  testcase3() throws InterruptedException {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,30);

		//Test URLs
        String testUrl = "http://jupiter.cloud.planittesting.com"; //1
        String testUrl2 = "http://jupiter2.cloud.planittesting.com"; //2
        int urlChoice = 1; //change depending on the URL you wanted to use
      
        // launch Google Chrome and direct it to the Base URL
        driver.manage().window().maximize();
        switch (urlChoice) {
        case 1 :
        	 driver.get(testUrl);
        	 break;
        case 2 :
       	 driver.get(testUrl2);
       	 break;
        default:
        	System.out.println("Not a valid choice for URL");
        	driver.close();
       	 break;
        }
      
       
        //From the home page go to shop page
        WebElement shopTab = driver.findElement(By.xpath("(//a[contains(@href,'shop')])[1]"));
        shopTab.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Buy')])[1]")));
        
        //Click buy button 2 times on "Funny Cow" and Click buy button 1 time on "Fluffy Bunny"
        WebElement funnyCowBuyBtn = driver.findElement(By.xpath("//h4[text()='Funny Cow']//following::a[contains(text(),'Buy')][1]"));
        WebElement fluffyBunnyBuyBtn = driver.findElement(By.xpath("//h4[text()='Fluffy Bunny']//following::a[contains(text(),'Buy')][1]"));
        WebElement cartCountTxt = driver.findElement(By.xpath("//span[@class='cart-count ng-binding']"));
        WebElement cartMenu = driver.findElement(By.xpath("(//a[contains(@href,'cart')])[1]"));
        funnyCowBuyBtn.click();
        funnyCowBuyBtn.click();
        fluffyBunnyBuyBtn.click();  
        String cartCount = cartCountTxt.getText();
        if(cartCount.equals("3")) {
        	System.out.println("[PASSED] Products were added Successfully with quantity of:" + cartCount );
        }
        else {
        	System.out.println("[FAILED] Products were not added Successfully");
        }
        
        //Click the cart menu\
        cartMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[(text()='Check Out') or text()='Checkout'])[last()]")));
        
        //Verify the items are in the cart
        WebElement funnyCowLineItem = driver.findElement(By.xpath("//*[contains(text(),'Funny Cow')]"));
        WebElement fluffyBunnyLineItem = driver.findElement(By.xpath("//*[contains(text(),'Fluffy Bunny')]"));
        if(urlChoice == 1) {
        WebElement funnyCowQty = driver.findElement(By.xpath("//*[contains(text(),'Funny Cow')]//following::input[@name='quantity'][1]"));
        WebElement fluffyBunnyQty = driver.findElement(By.xpath("//*[contains(text(),'Fluffy Bunny')]//following::input[@name='quantity'][1]"));
        String funnyCowCount = funnyCowQty.getAttribute("value");
        String fluffyBunnyCount = fluffyBunnyQty.getAttribute("value");
        
	        if(funnyCowLineItem.isDisplayed()) {
	        	System.out.println("[PASSED] Funny Cow is present in the cart with quantity of:" + funnyCowCount);
	        }
	        else {
	        	System.out.println("[FAILED] funny Cow is not present in the cart");
	        }
	        
	        if(fluffyBunnyLineItem.isDisplayed()) {
	        	System.out.println("[PASSED] Fluffy Bunny is present in the cart with quantity of:" + fluffyBunnyCount);
	        }
	        else {
	        	System.out.println("[FAILED] Fluffy Bunny is not present in the cart");
	        }
	        Thread.sleep(3000);
        }
        
        else if(urlChoice == 2) {
        WebElement funnyCowQty = driver.findElement(By.xpath("//*[contains(text(),'Funny Cow')]//preceding::input[@name='quantity'][1]"));
        WebElement fluffyBunnyQty = driver.findElement(By.xpath("//*[contains(text(),'Fluffy Bunny')]//preceding::input[@name='quantity'][1]"));
        String funnyCowCount = funnyCowQty.getAttribute("value");
        String fluffyBunnyCount = fluffyBunnyQty.getAttribute("value");
        
	        if(funnyCowLineItem.isDisplayed()) {
	        	System.out.println("[PASSED] Funny Cow is present in the cart with quantity of:" + funnyCowCount);
	        }
	        else {
	        	System.out.println("[FAILED] funny Cow is not present in the cart");
	        }
	        
	        if(fluffyBunnyLineItem.isDisplayed()) {
	        	System.out.println("[PASSED] Fluffy Bunny is present in the cart with quantity of:" + fluffyBunnyCount);
	        }
	        else {
	        	System.out.println("[FAILED] Fluffy Bunny is not present in the cart");
	        }
	        Thread.sleep(3000);
        }
      
        //close Google Chrome
        driver.close();
       
    }
}
