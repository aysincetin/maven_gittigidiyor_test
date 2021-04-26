package com.maven_testinium;
import java.util.List;
import java.util.Random;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMain extends BaseTest{

	private static final Logger logger = LogManager.getLogger(TestMain.class);
	static String product_price, productBasket_price;
	
	@Test
    public void OpenSite(){ 
		WebSite webSite=new WebSite(driver); //open "gitti.gidiyor.com"
        driver.get(webSite.site());  
        driver.manage().window().maximize();
        
        // Homepage control
        try {
            Assert.assertEquals("GittiGidiyor - Türkiye'nin Öncü Alýþveriþ Sitesi",driver.getTitle()); 
        } catch (Exception exception){
            logger.error("Ups! wrong page."); // unsuccessful logged
        }

        logger.info("Login to the homepage."); //successful logged
    }	
	
	//Login system and control
	@Test
	public void loginSite() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div[1]/div[3]/div/div[1]/div/div[2]")).click();
		driver.get("https://www.gittigidiyor.com/uye-girisi"); 
		
		//Member login page control
		try {
			String URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "https://www.gittigidiyor.com/uye-girisi");
        } catch (Exception exception){
            logger.error("Login page did not open!");
        }

        logger.info("You are on the member login page"); 
	}
	
	//Member information control
    @Test
    public void memberLogin() throws InterruptedException {
    	//username info
    	WebElement userName = driver.findElement(By.id("L-UserNameField"));
		userName.sendKeys("aysincetinn@gmail.com");
		Thread.sleep(2000);
		//password info
		WebElement password = driver.findElement(By.id("L-PasswordField"));
		password.sendKeys("test123");
		
		WebElement loginButton = driver.findElement(By.id("gg-login-enter"));
		loginButton.click();
        logger.info("You are logged in"); 
       
    }
    
    //Search option control with "bilgisayar" keyword
    @Test
    public void searchComputer() throws InterruptedException {
    	WebElement searchProduct = driver.findElement(By.name("k"));
		searchProduct.sendKeys("bilgisayar");
		searchProduct.sendKeys(Keys.ENTER);
		logger.info("The word has been written in the search bar");
    }
	
    //Second page control  
    @Test
    public void secondPage() throws InterruptedException {
    	//scrolling to the end of page 
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, 9000)");
		//second page				
		driver.get("https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
		
		try {
			String URL = driver.getCurrentUrl();
			Assert.assertEquals(URL, "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
        } catch (Exception exception){
            logger.error("Second page did not open!");
        }

        logger.info("You are on the second page"); 
		
    }
    // Select random product
    @Test
    public void selectProduct() throws InterruptedException {
	    List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"best-match-right\"]/div[3]/div[2]/ul"));
	    Random rand = new Random();
	    int list= rand.nextInt(options.size());
	    options.get(list).click();
	    logger.info("Random product selected");
	    
	    try {
	    	 WebElement lowprice = driver.findElement(By.xpath("//*[@id=\"sp-price-lowPrice\"]"));
	    	 product_price= lowprice.getText();
	    
	    }catch(Exception e){
	    	WebElement highprice = driver.findElement(By.xpath("//*[@id=\"sp-price-highPrice\"]"));
	   	 	product_price= highprice.getText();
	    }
    }
  
    
   //Add to cart process
    @Test
    public void toBasket() throws InterruptedException {
    	WebElement addBasket= driver.findElement(By.id("add-to-basket"));
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", addBasket);;
	    Thread.sleep(2000); 
	    logger.info("Product has been added to cart");
	    
	    
	    try {
	    	 WebElement lowprice = driver.findElement(By.xpath("//*[@id=\"sp-price-lowPrice\"]"));
	    	 productBasket_price= lowprice.getText();
	    
	    }catch(Exception e){
	    	WebElement highprice = driver.findElement(By.xpath("//*[@id=\"sp-price-highPrice\"]"));
	   	 	productBasket_price= highprice.getText();
	    }
	  
	
	    
    }

   //Product accuracy comparison 
    @Test
    public void toCartAccuracy() throws InterruptedException {
    	driver.get("https://www.gittigidiyor.com/sepetim");
    	Assert.assertEquals(product_price,productBasket_price);
    	// logger.info(product_price+ " " + productBasket_price); // price control
    	logger.info("The product price is equal to the price of the basket");
    }
 
    
   // Increase amount 
    @Test 
    public void toProductAdd() throws InterruptedException {
    	
    	Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div[1]/div[2]/div[2]/div[1]/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[2]/select")));
    	dropdown.selectByValue("2");
    	Thread.sleep(500); 
    	logger.info("Amount increased");
    }

  // Delete Product
    @Test
    public void toProductDelete() throws InterruptedException { 
    	driver.get("https://www.gittigidiyor.com/sepetim");
    	Thread.sleep(500); 
    	driver.findElement(By.className("btn-update-item")).click();
    	//empty basket control
    	Thread.sleep(500); 
    	driver.get("https://www.gittigidiyor.com/sepetim");
    	WebElement emptyBasket = driver.findElement(By.xpath("//*[@id=\"empty-cart-container\"]/div[1]/div[1]/div/div[2]/h2"));
    	Assert.assertEquals("Sepetinizde ürün bulunmamaktadýr.",emptyBasket.getText());
    	logger.info("Your basket is empty and test is success");
    }  
}
