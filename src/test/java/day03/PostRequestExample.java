package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostRequestExample {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://54.174.216.245";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @Test
    public void test1() {

        //first thing in POST is we need to tell what kind of data we are sending

        String myBodyData = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Tirsik\",\n" +
                "  \"phone\": 1234554321\n" +
                "}";

        System.out.println("myBodyData" + myBodyData);


        given().contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all()
                .when()
                .post("/spartans")
                .then()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Tirsik"))


        ;


    }

    @DisplayName("Practice Extracting Data")
    @Test
    public void postRequesExtractingData() {

        String myBodyData = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"California\",\n" +
                "  \"phone\": 1234554321\n" +
                "}";


        Response response = given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all()
                .when()
                .post("/spartans")
                .prettyPeek();


        System.out.println("data.id" + response.path("data.id"));
        System.out.println("data.name" + response.path("data.name"));


        JsonPath jp = response.jsonPath();

        System.out.println("phone:" + jp.getString("phone"));
        System.out.println("name: " + jp.getString("name"));


    }


}
