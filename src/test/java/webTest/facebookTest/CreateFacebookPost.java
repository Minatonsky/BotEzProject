package webTest.facebookTest;

import apiTest.TestRestAssured;
import org.junit.Test;
import parentWebTest.ParentTest;

import static libs.Utils.waitABit;

public class CreateFacebookPost extends ParentTest {

    TestRestAssured testRestAssured = new TestRestAssured();

    @Test
    public void createFacebookPost(){

        TestRestAssured.PostModelDTO[] myTypes = testRestAssured.getFacebookPost();
        String login = myTypes[0].user_socials[0].login;
        String pass = myTypes[0].user_socials[0].password;
        String title = myTypes[0].post.title;
        String image = myTypes[0].files[0].uri;

        facebookPage.openPage();
        facebookPage.enterLogin(login);
        facebookPage.enterPass(pass);
        facebookPage.clickLoginButton();
        waitABit(5);
        facebookPage.clickOnSharePostButton();
        facebookPage.enterTextOnPostField(title);
        facebookPage.clickOnPostSubmitButton();
        waitABit(10);
    }
}
