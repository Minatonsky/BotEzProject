package webTest.facebookTest;

import org.junit.Test;
import parentWebTest.ParentTest;

import static libs.Utils.waitABit;

public class CreateFacebookPost extends ParentTest {
    String login = "minatonski@gmail.com";
    String pass = "44B/HY7v>`zStn[&";
    String postText = "My first post";

    @Test
    public void createFacebookPost(){
        facebookPage.openPage();
        facebookPage.enterLogin(login);
        facebookPage.enterPass(pass);
        facebookPage.clickLoginButton();
        waitABit(5);
        facebookPage.clickOnAddPostInput();
        facebookPage.enterTextOnPostField(postText);
        facebookPage.clickOnPostSubmitButton();
        waitABit(10);
    }
}
