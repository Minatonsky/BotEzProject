package apiTest;

import io.restassured.response.Response;
import org.junit.Test;
import restSteps.MainRestSteps;

public class TestRestAssured {

    MainRestSteps mainRestSteps = new MainRestSteps();

    @Test
    public void getCursDetails() {

        Response response = mainRestSteps.getRequestToPrivatApiAndVerifyStatusCode();

        mainRestSteps.getResponseBody(response);

    }
}
