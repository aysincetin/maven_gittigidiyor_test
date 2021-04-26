package com.maven_testinium;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	WebDriverWait wait;
    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 2000);
        PageFactory.initElements(driver, this);
    }
}
