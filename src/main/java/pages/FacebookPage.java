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

    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[text()='Create a Text Story']")
    private WebElement createTextStory;

    @FindBy(xpath = ".//*[text()='Create a Photo Story']")
    private WebElement createPhotoStory;

    @FindBy(xpath = ".//input[@accept='image/*,image/heif,image/heic']")
    private WebElement addPhotoInput;

    @FindBy(xpath = ".//textarea")
    private WebElement textArea;

    @FindBy(xpath = ".//*[text()='Share to Story']")
    private WebElement shareStoryButton;

    @FindBy(xpath = "//*[@id=\"mount_0_0\"]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div[2]/div/div/div[3]/div/div[2]/div/div/div/div[1]/div/div[1]/span")
    private WebElement addPostInput;

    @FindBy(xpath = "//*[@id=\"mount_0_0\"]/div/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div/div[2]/div")
    private WebElement postTextField;

    @FindBy(xpath = ".//*[text()='Опублікувати']")
    private WebElement postSubmitButton;

    @FindBy(xpath = "//*[text()=\"Створити допис\"]")
    private WebElement test;

    public void enterLogin(String login){
        actionsWithWebElements.enterTextToElement(emailInput, login);
    }
    public void enterPass(String pass){
        actionsWithWebElements.enterTextToElement(passInput, pass);
    }
    public void clickLoginButton(){
        actionsWithWebElements.clickOnElement(loginButton);
    }

    public void goToCreateStories(){
        super.openPage("https://www.facebook.com/stories/create");
    }

    public void clickOnCreateTextStory(){
        actionsWithWebElements.clickOnElement(createTextStory);
    }
    public void clickOnCreatePhotoStory(){
        actionsWithWebElements.clickOnElement(createPhotoStory);
    }

    public void enterTextInTextArea(String text){
        actionsWithWebElements.enterTextToElement(textArea, text);
    }
    public void clickOnSherStoryButton(){
        actionsWithWebElements.clickOnElement(shareStoryButton);
    }

    public void addPhoto(){
        actionsWithWebElements.addFileByJs(addPhotoInput, "src/test.jpg");
    }

    public void clickOnAddPostInput(){
        actionsWithWebElements.clickOnElement(addPostInput);
    }

    public void enterTextOnPostField(String text){
        actionsWithWebElements.enterTextToElement(postTextField, text);
    }

    public void clickOnPostSubmitButton(){
        actionsWithWebElements.clickOnElement(postSubmitButton);
    }



}
