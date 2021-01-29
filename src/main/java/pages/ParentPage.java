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
    public WebElement findWebElement(String typeLocator, String locator){
        switch (typeLocator){
            case "name": return webDriver.findElement(By.name(locator));
            case "id": return webDriver.findElement(By.id(locator));
            default: return webDriver.findElement(By.xpath(locator));
        }
    }
    public void doAction(String action, String typeLocator, String locator, String text){
        switch (action){
            case "enterTextToElement": actionsWithWebElements.enterTextToElement(findWebElement(typeLocator, locator), text);
                break;
            case "sendEnterKey": actionsWithWebElements.sendEnterKey(findWebElement(typeLocator, locator));
                break;
            default: logger.info("Invalid action");
        }
    }



}
