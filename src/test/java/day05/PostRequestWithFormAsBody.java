package day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestWithFormAsBody {

    //posting in library app
    // body is not json , it's x-www-urlencoded-form-data

    //http://library1.cybertekschool.com/rest/v1/login
    // baseURI  is http://library1.cybertekschool.com
    // basePath is /rest/v1
    // we are working on POST /login

    // Post body , type x-www-urlencoded-form-data
    //email :    librarian69@library
    //password : KNPXrm3S

    // in URLs if you do not see port , because it's omitted because it's so common
    //  http --->> 80
    //  https --->> 443
    //  anything other than above 2 ports need to be explicitly set in the URL
    // for example spartan app use port 8000 -->> yourip:8000

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";

    }

    @DisplayName("Post login request Test")
    @Test
    public void testingLoginEndPoint() {

        given()
                .log().all()
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S")
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(is(200))
                .body("token", is(notNullValue()));


    }


    @Test
    public static String loginAndGetToken(String username, String password) {

        String token = "";
        Response response = given()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password", password)
                .when()
                .post("/login");

        token = response.jsonPath().getString("token");


        return token;


    }


    @DisplayName("Testing out hte utility")
    @Test
    public void runningUtiliyMethod(){

        System.out.println(loginAndGetToken("librarian69@library", "KNPXrm3S"));

    }

}
