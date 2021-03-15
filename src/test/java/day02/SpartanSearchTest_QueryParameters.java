package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanSearchTest_QueryParameters {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://52.73.110.253:8000";
        RestAssured.basePath = "/api";


    }


    @DisplayName("Testing/spartans/search_Endpoint")
    @Test
    public void testSearch() {

        given().
                log().all()
                .auth().basic("admin","admin")
                .queryParam("gender","Male")
                .queryParam("nameContains","ea").

             when()
                .get("spartans/search")
                .then()
                .log().all()
                .statusCode(200)
                .body("numberOfElements",is(3));


    }


}
