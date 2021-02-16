package webTest.facebookTest;

import apiTest.TestRestAssured;
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


    private String login;
    private String pass;
    private String title;
    private String image;
    private String fileName;
    private String postId;
    private String socialName;
    private String publicationState;
    private String message;


    @Test
    public void createFacebookPost() throws IOException {
        try {
            getDataFromAPI();
            writeImageFile();

            facebookPage.openPage();
            facebookPage.enterLogin(login);
            facebookPage.enterPass(pass);
            facebookPage.clickLoginButton();
            waitABit(5);
            facebookPage.clickOnSharePostButton();
            facebookPage.enterTextOnPostField(title);
            facebookPage.addImageOnPost("C:\\BotEzProject\\" + fileName + ".jpg");
//            facebookPage.clickOnPostSubmitButton();
            this.publicationState = "SENT";
            this.message = "Post published";


        } finally {
            if (publicationState.isEmpty()){
                this.publicationState = "FAIL";
                this.message = "Post failed";
            }
            testRestAssured.postScenarioExecutingResult(postId, socialName, publicationState, message);
            int f = 1;
        }

    }
    public void getDataFromAPI(){

        TestRestAssured.PostModelDTO[] myTypes = testRestAssured.getFacebookPost();

        if (!myTypes[0].user_socials[0].login.isEmpty()) {
            this.login = myTypes[0].user_socials[0].login;
        }

        if (!myTypes[0].user_socials[0].password.isEmpty()) {
            this.pass = myTypes[0].user_socials[0].password;
        }

        if (!myTypes[0].post.title.isEmpty()) {
            this.title = myTypes[0].post.title;
        }

        if (!myTypes[0].files[0].uri.isEmpty()) {
            this.image = myTypes[0].files[0].uri;
        }

        if (!myTypes[0].files[0].name.isEmpty()) {
            this.fileName = myTypes[0].files[0].name;
        }

        if (!myTypes[0].files[0].name.isEmpty()) {
            this.postId = String.valueOf(myTypes[0].post.id);
        }
        this.socialName = myTypes[0].user_socials[0].social_name;

    }

    public void writeImageFile() throws IOException {
        URL url = new URL("https://app.socialsched.com" + image);
        BufferedImage img = ImageIO.read(url);
        File file = new File(fileName + ".jpg");
        ImageIO.write(img, "jpg", file);
    }
}
