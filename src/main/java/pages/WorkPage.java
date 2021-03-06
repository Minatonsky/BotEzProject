package pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileReader;
import java.io.IOException;


public class WorkPage extends ParentPage {

    static String jPath = "src/data.json";

    private String url;
    private String action;
    private String typeLocator;
    private String data;
    private String locator;


    public WorkPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void handleWithChildJSONObject() throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(jPath));
        JSONObject jsonObject = (JSONObject) obj;

        for (JSONObject jsonChildObject : (Iterable<JSONObject>) jsonObject.values()) {

            if (jsonChildObject.containsKey("url")) {
                this.url = ((String) jsonChildObject.get("url"));
            } else this.url = "";

            if (jsonChildObject.containsKey("action")) {
                this.action = ((String) jsonChildObject.get("action"));
            }else this.action = "";

            if (jsonChildObject.containsKey("typeLocator")) {
                this.typeLocator = ((String) jsonChildObject.get("typeLocator"));
            }else this.typeLocator = "";

            if (jsonChildObject.containsKey("locator")) {
                this.locator = ((String) jsonChildObject.get("locator"));
            }else this.locator = "";

            if (jsonChildObject.containsKey("data")) {
                this.data = ((String) jsonChildObject.get("data"));
            }else this.data = "";

            if (!url.isEmpty()) {
                openPage(url);
            }
            if (!action.isEmpty()) {
                doAction(action, typeLocator, locator, data);
            }

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

