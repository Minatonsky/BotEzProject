package restSteps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class MainRestSteps {
    final private String baseUrl = "https://app.socialsched.com";
    String stringToken = "UjJEMkBzb2NpYWxzaGVkLmxvY3xBcnRvby1EZXRvb19DLTNQTzo=";



    public RequestSpecification setUrlForFacebook() {
        RestAssured.baseURI = baseUrl + "/robot/posts/view";
        return RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Basic "+ stringToken)
                .param("socials", "facebook")
                .param("limit", "1");
    }

    public RequestSpecification setUrlForScenarioExecutingResult(){
        RestAssured.baseURI = baseUrl + "/robot/posts/record-posting-post";
        return RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Basic "+ stringToken);
    }


    public void checkResponseCode(Response response, int expectedStatusCode) {
        // Get the status code from the Response. In case of
        // a successfull interaction with the web service, we
        // should get a status code of 200.
        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals("Correct status code returned", statusCode /*actual value*/, expectedStatusCode /*expected value*/);
    }
    public ResponseBody getResponseBody(Response response) {
        // Now let us print the body of the message to see what response
        // we have recieved from the server
        ResponseBody responseBody = response.getBody();
        System.out.println("Response Body is =>  " + responseBody.asString());
        return responseBody;
    }


    public ResponseBody getResponseBodyPrettyPrint(Response response) {
        ResponseBody responseBody = response.getBody();
        responseBody.prettyPrint();
        return responseBody;
    }

    public static JsonPath getJsonPath(Response response) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js;
    }




}
