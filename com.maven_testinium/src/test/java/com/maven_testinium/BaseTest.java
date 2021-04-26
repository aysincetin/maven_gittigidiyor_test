package com.maven_testinium;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


	public class BaseTest {
	    protected static WebDriver driver;
	    public WebElement element(By Locator){
	        return driver.findElement(Locator);
	    }
	    
	    @BeforeClass
	    public static void before(){
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aysin\\Desktop\\chromedriver.exe");
	        driver=new ChromeDriver();
	    }
	    @AfterClass
	    public static void after() throws InterruptedException{
	        Thread.sleep(2000);
	    }

	    public WebDriver getDriver(){
	        return driver;
	    }
	}
