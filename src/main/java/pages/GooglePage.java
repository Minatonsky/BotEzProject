package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends ParentPage {
    String url = "https://www.google.com/";

    String typeLocator = "name";
    String locator = "q";

    String action = "enterText";
    String text = "some text";
    String key = "url";

    public GooglePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void mainScript(){
        switch (key){
            case "url": openPage(url);
                break;
            case "work": doAction(action, text, typeLocator);
                break;
        }
    }

    public WebElement findWebElement(String typeLocator){
        switch (typeLocator){
            case "name": return webDriver.findElement(By.name(locator));
            case "id": return webDriver.findElement(By.id(locator));
            default: return webDriver.findElement(By.xpath(locator));
        }
    }
    public void doAction(String action, String text, String typeLocator){
        switch (action){
            case "enterText": actionsWithWebElements.enterTextToElement(findWebElement(typeLocator), text);
                break;
            case "sendEnterKey": actionsWithWebElements.sendEnterKey(findWebElement(typeLocator));
                break;
            default: logger.info("Invalid action");
        }
    }

    public void openPage(String url) {
        try {
            webDriver.get(url);
        } catch (Exception e) {
            logger.error("Our doesn't opened");
        }
    }
}
