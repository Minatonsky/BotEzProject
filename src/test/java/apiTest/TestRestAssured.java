package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import restSteps.MainRestSteps;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TestRestAssured {

    MainRestSteps mainRestSteps = new MainRestSteps();
    static String jPath = "src/data2.json";

    @Test
    public void getFacebookPost() {

        RequestSpecification request = mainRestSteps.setUrlForTest();
        Response response = request.get();
        mainRestSteps.getResponseBodyPrettyPrint(response);

    }


    @Test
    public void testParsJson() throws IOException{

        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(jPath));
        PostModelDTO[] myTypes = gson.fromJson(reader, PostModelDTO[].class);

        int a = 1;
//        System.out.println(ItemDTO.class.name);



    }

    class PostModelDTO
    {
        PostDTO post;
        UserDTO user;
        ArrayList<PostTypeDTO> post_type;
        ArrayList<FileDTO> files;
        ArrayList<UserSocialsDTO> user_socials;
    }

    class PostDTO
    {
        int id;
        String status;
        String title;
        String body;
        String hashtag;
        String website;
        String date_publication_start;
    }

    class UserDTO
    {
        String email;
    }

    class PostTypeDTO
    {
        String social_name;
        String post_type;
    }

    class FileDTO
    {
        String name;
        String uri;
    }

    class UserSocialsDTO
    {
        String social_name;
        String login;
        String password;
    }
}
