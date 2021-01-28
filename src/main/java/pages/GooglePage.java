package pages;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


public class GooglePage extends ParentPage {

    static String json = "{ \"url\": \"google.com\", \"work\": \"test\" }";
    static String jPath = "C:\\BotEzProject\\src\\data.json";


    public GooglePage(WebDriver webDriver) {
        super(webDriver);
    }



    public void handleJSONObject2() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(jPath));
        JSONObject jsonObject = (JSONObject) obj;
        for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            System.out.println(jsonObject.get(key));
        }
    }

    public void handleJSONObject() throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(jPath));
        JSONObject jsonObject = (JSONObject) obj;
        Iterator<JSONObject> iterator = jsonObject.values().iterator();
        webDriver.get("google.com");


        while (iterator.hasNext()) {
            JSONObject  jsonChildObject = iterator.next();

            String id = (String) jsonChildObject.get("locator");
            System.out.println(id);
            String nameUrl = (String) jsonChildObject.get("url");
            String action = (String) jsonChildObject.get("action");
            String typeLocator = (String) jsonChildObject.get("typeLocator");
            String text = (String) jsonChildObject.get("data");
            String locator = (String) jsonChildObject.get("locator");
            System.out.println(locator);

            if (!nameUrl.isEmpty()){
                System.out.println(nameUrl);
                webDriver.get(nameUrl);
            }
            if (!action.isEmpty()){
                doAction(action, typeLocator, locator, text);
            }

        }
    }




    public void extractData_JSON() {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(jPath)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            String nameUrl = (String) jsonObject.get("url");
            String action = (String) jsonObject.get("action");
            String typeLocator = (String) jsonObject.get("typeLocator");
            String text = (String) jsonObject.get("data");
            String locator = (String) jsonObject.get("locator");

            if (!nameUrl.isEmpty()){
                openPage(nameUrl);
            } else logger.info("Url is failed");
            if (!action.isEmpty()){
                doAction(action, typeLocator, locator, text);
            } else logger.info("Action is failed");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
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

    public void openPage(String url) {
        try {
            webDriver.get(url);
        } catch (Exception e) {
            logger.error("Our page doesn't opened");
        }
    }
}
