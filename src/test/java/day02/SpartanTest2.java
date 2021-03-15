package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class SpartanTest2 {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://52.73.110.253:8000";
        RestAssured.basePath = "/api";

    }

    @DisplayName("Get 1 Spartan")
    @Test
    public void testSingleSpartan() {


        // I want to log the request I sent so I see what is the uRL , methods and so on
        given()
                // .log().all().
                .auth().basic("admin", "admin")
                .log().uri().
                when()
                .get("/spartans/19").
//                .prettyPeek().
        then()
                .log().all()
//                .log().body()
//                .log().ifValidationFails()
                .statusCode(is(200))
                .body("name", is("Bunnie"))

        ;


    }


    @DisplayName("Testing Hello ")
    @Test
    public void test2() {

        given()
                .accept(ContentType.TEXT)
                .when().get("/hello")
                .then()
                .statusCode(is(200))
                .body(equalTo("Hello from Sparta"));

    }


    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans() {

        String spartanURL = "http://54.174.216.245:8000/api/spartans";


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

        given().
                //.baseUri("http://54.146.68.229:8000")  // alternative way of doing it
                //.basePath("/api")
                // .accept("application/json").  //or accept(ContentType.JSON).
                        when()
                .get("/spartans").
                then()
                .statusCode(is(200))
                //.contentType("application/json;charset=UTF-8")
                //.contentType( is("application/json;charset=UTF-8")  )
                //easiest way is below.
                .contentType(ContentType.JSON);

    }


}
