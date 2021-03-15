package day03;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanSearchExtractData {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://3.226.201.68:8000";
        RestAssured.basePath = "/api";

    }

    @Test
    public void test1() {


        Response response =
                given().log().all()
                        .queryParam("gender", "Female")
                        .when().get("/spartans/search")
                        .prettyPeek();


        JsonPath jp = response.jsonPath();
        int numberOfFemaleSpartans = jp.getInt("numberOfElements");
        System.out.println("numberOfElements: " + numberOfFemaleSpartans);

        List<Integer> numList = jp.getList("content.id"); //with this method we can get names and other info as well.
        System.out.println(numList);

        List<String> nameList=jp.getList("content.name");
        System.out.println(nameList);

        //List<Map<String, Object>> listMap=jp.getList("content");
        //System.out.println(listMap);


    }


}
