package day03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class practice1 {


    @BeforeAll //Before All in Junit5 must be static
    public static void setUp(){

        baseURI="http://54.174.216.245";
        port=8000;
        basePath="/api";


    }


    @DisplayName("Simple Test")
    @Test
    public void test1(){

        given()
                .log().all()
                .queryParam("gender","Female")
                .when()
                .get("/spartans/search")
                .then()
                .statusCode(is(200));

    }





















}
