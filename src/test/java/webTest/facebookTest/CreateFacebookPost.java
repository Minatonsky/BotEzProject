package webTest.facebookTest;

import apiTest.TestRestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parentWebTest.ParentTest;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static libs.Utils.waitABit;

public class CreateFacebookPost extends ParentTest {

    TestRestAssured testRestAssured = new TestRestAssured();
    TestRestAssured.PostModelDTO[] myTypes = testRestAssured.getFacebookPost();


    private String login;
    private String pass;
    private String title;
    private String body;
    private String imageUri;
    private String imageName;
    private String postId;
    private String socialName;
    private String publicationState = "";
    private String message = "";


    @Before
    public void getDataFromAPI(){

        if (!myTypes[0].post.status.isEmpty()) {
            this.login = myTypes[0].user_socials[0].login;

            this.pass = myTypes[0].user_socials[0].password;

            this.title = myTypes[0].post.title;

            this.body = myTypes[0].post.body;

            this.postId = String.valueOf(myTypes[0].post.id);

            this.socialName = myTypes[0].user_socials[0].social_name;

        } else Assert.fail();

    }


    @Test
    public void createFacebookPost() throws IOException {

        try {

            facebookPage.openPage();

            facebookPage.enterLogin(login);
            facebookPage.enterPass(pass);
            facebookPage.clickLoginButton();

            if (facebookPage.isLoginPassErrorDisplayed()) {
                this.publicationState = "VIOLATION";
                this.message = "The username or password is incorrect";
                Assert.fail();
            }

            waitABit(5);
            facebookPage.clickOnSharePostButton();
            facebookPage.enterTextOnPostField(body);

            for (int i=0; i<myTypes[0].files.length; i++){

                this.imageName = myTypes[0].files[i].name;

                this.imageUri = myTypes[0].files[i].uri;

                URL url = new URL("https://app.socialsched.com" + imageUri);
                BufferedImage img = ImageIO.read(url);
                File file = new File(imageName + ".jpg");
                ImageIO.write(img, "jpg", file);

                facebookPage.addImageOnPost("C:\\BotEzProject\\" + imageName + ".jpg");
                waitABit(5);
            }


            facebookPage.clickOnPostSubmitButton();
            this.publicationState = "SENT";
            this.message = "Post published successfully";

        } catch (Error e){
            if (publicationState.isEmpty()) {
                this.publicationState = "FAIL";
                this.message = "Post failed " + e;
            }

        } finally {
            if (publicationState.isEmpty()){
                this.publicationState = "FAIL";
                this.message = "Post failed";
            }

            testRestAssured.postScenarioExecutingResult(postId, socialName, publicationState, message);
        }

    }


}
