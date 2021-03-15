package day04;

import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class PostRequestWithPOJO {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://54.174.216.245";
        port = 8000;
        RestAssured.basePath = "/api";

    }

    @Test
    public void testPostWithPojo() {

        Spartan spartan1 = new Spartan("Irina", "Female", 1231231231);

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(spartan1)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
        ;


    }


}
