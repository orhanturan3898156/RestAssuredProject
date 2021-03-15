package day02;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://52.73.110.253:8000";
        RestAssured.basePath = "/api";

    }

    @DisplayName("Testing/spartans/{id}")
    @Test
    public void testSingleSpartan(){

        given()
                .log().all()
                .auth().basic("admin","admin")
                .pathParam("id",37)
        .when()
                .get("/spartans/{id}")
        .then()
                .statusCode(200);


    }



    @DisplayName("Testing2/spartans/{id}")
    @Test
    public void testSingleSpartan2(){

       given()
               .log().all().
       when()
               .get("/spartans/{id}",33)
               .then()
               .statusCode(is(200));


    }

    @DisplayName("Testing/spartans/{id} body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all()
                .pathParam("id",66)
                .when()
                .get("spartans/{id}")
                .then()
                .log().all()
                .statusCode(is(200))
        .body("id",is(66))
        .body("name",is("Quentin"))
        .body("gender",is("Male"))
        ;




    }


}
