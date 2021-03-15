package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


// we can also give a new in the class level.
@DisplayName("Testing Zip Code API")
public class ZipCodeTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://www.zippopotam.us";
        RestAssured.basePath = "/us";

    }


    @Test
    @DisplayName("Zip to City")
    public void testZipToCity() {

        given()
                .pathParam("zip", 32810).
                log().uri().
                when()
                .get("/{zip}")
                .then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
        .body("country",is("United States"))
        .body("places[0].state",is("Florida"))
        .body("places[0].'place name'",is("Orlando"))
        .body("'post code'",is("32810"))

        ;


    }


    @DisplayName("City to Zip")
    @Test
    public void testCityToZip(){


        given()
                //.pathParam("state","VA")         this one way. look below comment out
                //.pathParam("city","Fairfax")
                .log().all().
                when()
               // .get("/{state}/{city}")
                .get("/{state}/{city}","VA","Fairfax")
                .then()
                .log().all()
                .statusCode(is(200))
        .body("'country abbreviation'",is("US"))
        .body("places[-1].latitude",is("38.8458"))

        ;


    }


}
