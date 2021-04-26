package com.maven_testinium;
import org.openqa.selenium.WebDriver;

public class WebSite extends Base{
    public WebSite(WebDriver driver) {
        super(driver);
    }

    public String site(){
        return "https://www.gittigidiyor.com/";
    }
}