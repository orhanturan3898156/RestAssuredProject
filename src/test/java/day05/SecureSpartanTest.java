package day05;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SecureSpartanTest {

    /*
    //54.160.106.84
    //100.24.235.129
    /23.23.75.140
     */

    @BeforeAll
    public static void setUp() {
        baseURI = "http://100.24.235.129";
        port = 8000;
        basePath = "/api";

    }

    @DisplayName("Test GET/spartan/{id} endpoint")
    @Test
    public void testSingleSpartanSecured() {

        given()
                .log().all()
                .pathParam("id", 174)
                .when()
                .get("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(is(401))
        ;


    }

    @Test
    public void testSingleSpartanSecuredwithCredentials() {

        int newID=createRandomSpartan();

        given()
                .log().ifValidationFails()//if it fails it will log
                .auth().basic("admin", "admin")
                .pathParam("id", newID)
                .when()
                .get("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(is(200))
        ;


    }


    public static int createRandomSpartan() {

        String name = new Faker().name().firstName();
        String gender = new Faker().demographic().sex();
        long phone = new Faker().number().numberBetween(100000000, 9999999999L);

        Spartan sp = new Spartan(name, gender, phone);

        Response response = given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(sp)
                .when()
                .post("spartans")
                .prettyPeek();

        return response.jsonPath().getInt("data.id");


    }


}
