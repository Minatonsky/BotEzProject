package pages;

import libs.ActionsWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    ActionsWithWebElements actionsWithWebElements;
    JavascriptExecutor js = (JavascriptExecutor) webDriver;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        actionsWithWebElements = new ActionsWithWebElements(webDriver);
    }
    public void openPage(String url) {
        try {
            webDriver.get(url);
        } catch (Exception e) {
            logger.info(e);
            logger.error("Our page doesn't opened");
        }
    }



}
