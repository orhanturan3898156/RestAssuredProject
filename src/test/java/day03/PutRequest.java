package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutRequest {


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
                "  \"name\": \"Tirsikkkkk\",\n" +
                "  \"phone\": 1234554321\n" +
                "}";


        given().contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all()
                .when()
                .put("/spartans/{id}", 167)
                .then()
                .statusCode(204);


    }


    @Test
    public void delete() {

        when().delete("spartans/{id}", 168)
                .then()
                .statusCode(is(204))
                
        ;


    }

}
