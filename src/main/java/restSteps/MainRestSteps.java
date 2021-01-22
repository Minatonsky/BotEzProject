package restSteps;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class MainRestSteps {
    final private String baseUrlPrivat = "http://api.privatbank.ua";

    public RequestSpecification setBaseUrlForPrivatApi() {
        RestAssured.baseURI = baseUrlPrivat + "/p24api/pubinfo";
        return RestAssured.given().queryParam("json").queryParam("exchange").queryParam("coursid", 5);
    }

    public Response getRequestToPrivatApi() {
        return setBaseUrlForPrivatApi().request(Method.GET);
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
    public Response getRequestToPrivatApiAndVerifyStatusCode() {
        Response response = getRequestToPrivatApi();
        checkResponseCode(response, 200);
        return response;
    }

    public String getValueForKeyFromResponseAsJsonObject(Response response, String key) {
        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String value = jsonPathEvaluator.get(key);
        if (value == null) {
            Assert.fail("There is no key '" + key + "' in Json response ");
        }
        return value;
    }




}
