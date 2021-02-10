package webTest.facebookTest;

import org.junit.Test;
import parentWebTest.ParentTest;

import static libs.Utils.waitABit;

public class CreateFacebookPhotoStories extends ParentTest {

    @Test
    public void createFacebookTextStories(){
        facebookPage.openPage();
        facebookPage.enterLogin("minatonski@gmail.com");
        facebookPage.enterPass("44B/HY7v>`zStn[&");
        facebookPage.clickLoginButton();
        waitABit(5);
        facebookPage.goToCreateStories();
        facebookPage.addPhoto();
        facebookPage.clickOnSherStoryButton();
        waitABit(20);
    }
}
