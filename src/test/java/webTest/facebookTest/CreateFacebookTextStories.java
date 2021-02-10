package webTest.facebookTest;

import org.junit.Test;
import parentWebTest.ParentTest;

import static libs.Utils.waitABit;

public class CreateFacebookTextStories extends ParentTest {

    @Test
    public void createFacebookTextStories(){
        facebookPage.openPage();
        facebookPage.enterLogin("minatonski@gmail.com");
        facebookPage.enterPass("44B/HY7v>`zStn[&");
        facebookPage.clickLoginButton();
        waitABit(5);
        facebookPage.goToCreateStories();
        facebookPage.clickOnCreateTextStory();
        facebookPage.enterTextInTextArea("My first stories");
        facebookPage.clickOnSherStoryButton();
        waitABit(20);
    }
}
