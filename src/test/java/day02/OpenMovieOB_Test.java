package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class OpenMovieOB_Test {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://www.omdbapi.com/";


    }

    @DisplayName("Test Movie API")
    @Test
    public void test() {
         /*
             ?-Question mark means to use query param
             if you see " : " >>it's pathParam.
         */

        given()
                .queryParam("apikey", "d3aea025")
                .queryParam("t", "Boss Baby")
                .queryParam("plot", "full")
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
        .body("Ratings[0].Source",is("Internet Movie Database"))

                ;


    }
}
