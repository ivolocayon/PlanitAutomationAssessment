import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCase4 {	
	
	@Test
    public void  testcase4() throws InterruptedException {
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
        
        //Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
        WebElement stuffedFrogBuyBtn = driver.findElement(By.xpath("//h4[text()='Stuffed Frog']//following::a[contains(text(),'Buy')][1]"));
        WebElement fluffyBunnyBuyBtn = driver.findElement(By.xpath("//h4[text()='Fluffy Bunny']//following::a[contains(text(),'Buy')][1]"));
        WebElement valentineBearBuyBtn = driver.findElement(By.xpath("//h4[text()='Valentine Bear']//following::a[contains(text(),'Buy')][1]"));
        WebElement cartCountTxt = driver.findElement(By.xpath("//span[@class='cart-count ng-binding']"));
        WebElement cartMenu = driver.findElement(By.xpath("(//a[contains(@href,'cart')])[1]"));
        int stuffedFrogCount = 2;
        int fluffyBunnnyCount = 5; 
        int valentineBunnnyCount = 3;       
        for(int ctr = 0 ; ctr < stuffedFrogCount ; ctr++) {
        	stuffedFrogBuyBtn.click();
        }      
        for(int ctr2 = 0 ; ctr2 < fluffyBunnnyCount ; ctr2++) {
        	fluffyBunnyBuyBtn.click();
        }    
        for(int ctr3 = 0 ; ctr3 < valentineBunnnyCount ; ctr3++) {
        	valentineBearBuyBtn.click();
        }     
        String cartCount = cartCountTxt.getText();
        if(cartCount.equals("10")) {
        	System.out.println("[PASSED] Products were added Successfully with quantity of:" + cartCount );
        }
        else {
        	System.out.println("[FAILED] Products were not added Successfully");
        }
      
        //Click the cart menu
        cartMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[(text()='Check Out') or text()='Checkout'])[last()]")));
        
        //Verify Price for each Product
        String stuffedFrogPrice = "10.99";
        String fluffyBunnyPrice = "9.99";
        String valentineBearPrice = "14.99";
        String stuffedFrogPrice2 = "10.99";
        String fluffyBunnyPrice2 = "8.99";
        String valentineBearPrice2 = "13.99";
        WebElement stuffedFrogDisplayPrice = driver.findElement(By.xpath("//td[contains(text(),'Stuffed Frog')]//following::td[contains(text(),'$')][1]"));
        WebElement fluffyBunnyDisplayPrice = driver.findElement(By.xpath("//td[contains(text(),'Fluffy Bunny')]//following::td[contains(text(),'$')][1]"));
        WebElement valentineBearDisplayPrice = driver.findElement(By.xpath("//td[contains(text(),'Valentine Bear')]//following::td[contains(text(),'$')][1]"));
        //get value of displayed price
        String sfPrice = stuffedFrogDisplayPrice.getText();
        String fbPrice = fluffyBunnyDisplayPrice.getText();
        String vbPrice = valentineBearDisplayPrice.getText();
        //get only the numbers
        String sfPriceNum = sfPrice.replaceAll("[^0-9.]", "");
        String fbPriceNum = fbPrice.replaceAll("[^0-9.]", "");
        String vbPriceNum = vbPrice.replaceAll("[^0-9.]", ""); 
        //compare values from the gathered numbers to the accepted value
        if(stuffedFrogPrice.equals(sfPriceNum) || stuffedFrogPrice2.equals(sfPriceNum)) {
        	System.out.println("[PASSED] Price for Stuffed Frog is Correct:" + sfPriceNum);
        }
        else {
        	System.out.println("[FAILED] Price for Stuffed Frog is Incorrect");
        }
        
        if(fluffyBunnyPrice.equals(fbPriceNum) || fluffyBunnyPrice2.equals(fbPriceNum)) {
        	System.out.println("[PASSED] Price for Fluffy Bunny is Correct:" + fbPriceNum);
        }
        else {
        	System.out.println("[FAILED] Price for Fluffy Bunny is Incorrect");
        }
        
        if(valentineBearPrice.equals(vbPriceNum) || valentineBearPrice2.equals(vbPriceNum)) {
        	System.out.println("[PASSED] Price for Valentine Bunny is Correct:" + vbPriceNum);
        }
        else {
        	System.out.println("[FAILED] Price for Valentine Bunny is Incorrect");
        }
        
        //Verify that each product's Sub Total = product price * quantity
        WebElement stuffedFrogSubTotalPrice = driver.findElement(By.xpath("//td[contains(text(),'Stuffed Frog')]//following::td[contains(text(),'$')][2]"));
        WebElement fluffyBunnySubTotalPrice = driver.findElement(By.xpath("//td[contains(text(),'Fluffy Bunny')]//following::td[contains(text(),'$')][2]"));
        WebElement valentineBearSubTotalPrice = driver.findElement(By.xpath("//td[contains(text(),'Valentine Bear')]//following::td[contains(text(),'$')][2]"));
        //get value of subtotal price
        String sfSubtotal = stuffedFrogSubTotalPrice.getText();
        String fbSubtotal = fluffyBunnySubTotalPrice.getText();
        String vbSubtotal = valentineBearSubTotalPrice.getText();
        //get only the numbers for subtotal
        String sfSubtotalNum = sfSubtotal.replaceAll("[^0-9.]", "");
        String fbSubtotalNum = fbSubtotal.replaceAll("[^0-9.]", "");
        String vbSubtotalNum = vbSubtotal.replaceAll("[^0-9.]", "");
        //convert the gathered number to double
        double sfPriceValue = Double.parseDouble(sfPriceNum);
        double fbPriceValue = Double.parseDouble(fbPriceNum);
        double vbPriceValue = Double.parseDouble(vbPriceNum);
        double sfSubtotalValue = Double.parseDouble(sfSubtotalNum);
        double fbSubtotalValue = Double.parseDouble(fbSubtotalNum);
        double vbSubtotalValue = Double.parseDouble(vbSubtotalNum);
        //compute for product price * quantity
        double sfComputedsubtotalPrice = stuffedFrogCount * sfPriceValue;
        double fbComputedsubtotalPrice = fluffyBunnnyCount * fbPriceValue;
        double vbComputedsubtotalPrice = valentineBunnnyCount * vbPriceValue; 
        //compare computed value to displayed subtotal
        if(sfComputedsubtotalPrice == sfSubtotalValue) {
        	System.out.println("[PASSED] Subtotal for Stuffed Frog is Correct:" + sfSubtotalValue);
        }
        else {
        	System.out.println("[FAILED] Subtotal for Stuffed Frog is Incorrect");
        }
        if(fbComputedsubtotalPrice == fbSubtotalValue) {
        	System.out.println("[PASSED] Subtotal for Fluffy Bunny is Correct:" + fbSubtotalValue);
        }
        else {
        	System.out.println("[FAILED] Subtotal for Fluffy Bunny is Incorrect");
        }
        if(vbComputedsubtotalPrice == vbSubtotalValue) {
        	System.out.println("[PASSED] Subtotal for Fluffy Bunny is Correct:" + vbSubtotalValue);
        }
        else {
        	System.out.println("[FAILED] Subtotal for Fluffy Bunny is Incorrect");
        }
        
        //Verify that total = sum(sub totals)
        //compute for total price using the gathered subtotals
        double sumSubtotal = sfSubtotalValue + fbSubtotalValue + vbSubtotalValue;
        //declare xpath for total price
        WebElement totalPriceTxt = driver.findElement(By.xpath("//strong[contains(text(),'Total')]"));
        //get total price value
        String totalPrice = totalPriceTxt.getText();
        //get only the numbers for total
        String totalPriceNum = totalPrice.replaceAll("[^0-9.]", "");
        //comvert total price num to double
        double totalPriceValue = Double.parseDouble(totalPriceNum);
        //compare computed value of total price to displayed total
        if(sumSubtotal == totalPriceValue) {
        	System.out.println("[PASSED] Total for the Cart is Correct:" + totalPriceValue);
        }
        else {
        	System.out.println("[FAILED] Total for the Cart is Incorrect");
        }
        Thread.sleep(3000);

        //close Google Chrome
        driver.close();
       
    }
}
