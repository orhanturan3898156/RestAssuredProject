package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanTest {


    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans() {

        String spartanURL = "http://54.174.216.245:8000/api/spartans";

        RestAssured.baseURI = "http://54.174.216.245:8000";
        RestAssured.basePath = "/api";

        given().
                header("Accept", "application/json");

        when().
                get("/spartans")   //get(baseURI+basePath) //or get(spartanURL)
                .then()
                .statusCode(200);


    }

    @DisplayName("Get All Spartan 2")
    @Test
    public void testAllSpartans2() {


        // send the same request specifiying the accept header is application/json
        // use baseuri basepath , check status code 200 , contentType header is json

        given()
                .baseUri("http://54.174.216.245:8000")  // alternative way of doing it
                .basePath("/api")
                .accept("application/json").  //or accept(ContentType.JSON).
                when()
                .get("/spartans").
                then()
                .statusCode( is(200) )
                //.contentType("application/json;charset=UTF-8")
                //.contentType( is("application/json;charset=UTF-8")  )
                //easiest way is below.
                .contentType( ContentType.JSON )  ;

    }


}
