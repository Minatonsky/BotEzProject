package parentWebTest;


import com.sun.javafx.collections.MappingChange;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

import pages.FacebookPage;
import pages.WorkPage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import static java.util.concurrent.TimeUnit.SECONDS;

public class ParentTest {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    protected WorkPage workPage;
    protected FacebookPage facebookPage;

    String browser; // System.getProperty("browser");
    Map<String, Object> prefs = new HashMap<String, Object>();

    @Before
    public void setUp(){
        initDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, SECONDS);
        workPage = new WorkPage(webDriver);
        facebookPage = new FacebookPage(webDriver);
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Chrome closed");
    }

    private void initDriver(String browserName) {
        if ( browserName == null || browserName.equals("chrome")) {

            logger.info("Chrome will be started");
            File file = new File("./src/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", prefs);
            webDriver = new ChromeDriver(options);
            logger.info("Chrome is started");
        }else if ("ChromeHeadless".equals(browserName)){
            logger.info("ChromeHeadless will be started");
            File file = new File("./src/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            webDriver = new ChromeDriver(options);
            logger.info("ChromeHeadless is started");

        } else if ("remote".equals(browser)){
            logger.info("Remote Driver will be started");
            try {
                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        else {
            logger.error("Can`t init driver");
            Assert.fail("Can`t init driver");
        }
    }
    protected void checkAC(String message, boolean actual, boolean expected){
        if (actual != expected){
            logger.error("AC failed: " + message);
        }
        Assert.assertEquals(message,expected,actual);
        logger.info("AC passed");
    }
}
