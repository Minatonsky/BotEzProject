package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;
import restSteps.MainRestSteps;


public class TestRestAssured {

    MainRestSteps mainRestSteps = new MainRestSteps();
    Gson gson = new Gson();
    JSONObject requestParams = new JSONObject();


    public  PostModelDTO[] getFacebookPost() {

        RequestSpecification request = mainRestSteps.setUrlForFacebook();
        Response response = request.get();
        String json = mainRestSteps.getResponseBody(response).asString();
        PostModelDTO[] myTypes = gson.fromJson(json, PostModelDTO[].class);

        return myTypes;

    }

    @Test
    public void postScenarioExecutingResult(String postId, String socialName, String publicationState, String message){
        RequestSpecification request = mainRestSteps.setUrlForScenarioExecutingResult();
        requestParams.put("post_id", postId);
        requestParams.put("social_name", socialName);
        requestParams.put("publication_state", publicationState);
        requestParams.put("message", message);
        request.body(requestParams.toMap());
        Response response = request.post();
        mainRestSteps.getResponseBody(response);
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
