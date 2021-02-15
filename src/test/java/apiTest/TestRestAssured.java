package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restSteps.MainRestSteps;


public class TestRestAssured {

    MainRestSteps mainRestSteps = new MainRestSteps();
    Gson gson = new Gson();


    public  PostModelDTO[] getFacebookPost() {

        RequestSpecification request = mainRestSteps.setUrlForFacebook();
        Response response = request.get();
        String json = mainRestSteps.getResponseBody(response).asString();
        PostModelDTO[] myTypes = gson.fromJson(json, PostModelDTO[].class);

        return myTypes;

    }


    public class PostModelDTO
    {
        public PostDTO post;
        public UserDTO user;
        public PostTypeDTO[] post_type;
        public FileDTO[] files;
        public UserSocialsDTO[] user_socials;
    }

    public class PostDTO
    {
        public int id;
        public String status;
        public String title;
        public String body;
        public String hashtag;
        public String website;
        public String date_publication_start;
    }

    public class UserDTO
    {
        public String email;
    }

    public class PostTypeDTO
    {
        public String social_name;
        public String post_type;
    }

    public class FileDTO
    {
        public String name;
        public String uri;
    }

    public class UserSocialsDTO
    {
        public String social_name;
        public String login;
        public String password;
    }
}
