package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.junit.Test;
import restSteps.MainRestSteps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

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
        Reader reader = Files.newBufferedReader(Paths.get("user.json"));
        TypeDTO[] myTypes = gson.fromJson(reader, TypeDTO[].class);
        System.out.println(gson.toJson(myTypes));
//        System.out.println(ItemDTO.class.name);



    }
    class TypeDTO
    {
        ArrayList<ItemDTO> files;
    }
    class ItemDTO
    {
        String name;
    }
}
