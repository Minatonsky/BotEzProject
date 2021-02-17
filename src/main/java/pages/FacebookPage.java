package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FacebookPage extends ParentPage{


    public FacebookPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        super.openPage("https://www.facebook.com/");
    }

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "pass")
    private WebElement passInput;

    @FindBy(xpath = ".//*[@class='_9ay7']")
    private WebElement loginPassError;

    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[@class='rq0escxv datstx6m k4urcfbm a8c37x1j']//.//*[@class='notranslate _5rpu' and @role='textbox']")
    private WebElement postTextField;

    @FindBy(xpath = ".//*[@class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql rrkovp55 a8c37x1j keod5gw0 nxhoafnm aigsh9s9 d3f4x2em fe6kdd0r mau55g9w c8b282yb iv3no6db jq4qci2q a3bd9o3v lrazzd5p bwm1u5wc']//.//*[@class='a8c37x1j ni8dbmo4 stjgntxs l9j0dhe7 ltmttdrg g0qnabr5']")
    private WebElement postSubmitButton;

    @FindBy(xpath = ".//*[@class='m9osqain a5q79mjw jm1wdb64 k4urcfbm']//.//*[@class='a8c37x1j ni8dbmo4 stjgntxs l9j0dhe7']")
    private WebElement sharePostButton;

    @FindBy(xpath = ".//*[@class='dwxx2s2f dicw6rsg kady6ibp rs0gx3tq']/.//input[@type='file']")
    private WebElement addPictureToPostButton;


    public void enterLogin(String login){
        actionsWithWebElements.enterTextToElement(emailInput, login);
    }
    public void enterPass(String pass){
        actionsWithWebElements.enterTextToElement(passInput, pass);
    }

    public void clickLoginButton(){
        actionsWithWebElements.clickOnElement(loginButton);
    }

    public void enterTextOnPostField(String text){
        if (!text.isEmpty()) {
            actionsWithWebElements.enterTextToElement(postTextField, text);
        }
    }

    public void clickOnPostSubmitButton(){
        actionsWithWebElements.clickOnElement(postSubmitButton);
    }

    public void clickOnSharePostButton(){
        actionsWithWebElements.clickOnElement(sharePostButton);
    }

    public void addImageOnPost(String pathFile){
        actionsWithWebElements.addFileByJs(addPictureToPostButton, pathFile);
    }

    public boolean isLoginPassErrorDisplayed(){
        return actionsWithWebElements.isElementDisplay(loginPassError);
    }


}
