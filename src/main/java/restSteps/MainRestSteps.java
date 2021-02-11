package restSteps;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

public class MainRestSteps {
    final private String baseUrl = "https://socialsched.com.itex.agency/robot/posts/view";
    String stringToken = "UjJEMkBzb2NpYWxzaGVkLmxvY3xBcnRvby1EZXRvb19DLTNQTzo=";


    public RequestSpecification setBaseUrlWithToken(String addToUrl, String stringToken) {
        RestAssured.baseURI = baseUrl + addToUrl;
        return RestAssured.given().accept("application/json").contentType("application/json").header("Authorization", "Bearer "+ stringToken);
    }

    public RequestSpecification testTest(String addToUrl, String stringToken) {
        RestAssured.baseURI = baseUrl + addToUrl;
        return RestAssured.given().accept("application/json").contentType("application/json").param("socials[]");
    }

    public RequestSpecification setUrlForTest() {
        RestAssured.baseURI = baseUrl;
        return RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Basic "+ stringToken)
                .param("socials", "facebook")
                .param("limit", "1");
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




}
