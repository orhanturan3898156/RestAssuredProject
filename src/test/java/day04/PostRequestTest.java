package day04;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://54.174.216.245";
        port = 8000;
        RestAssured.basePath = "/api";

    }


    @DisplayName("Post request with String as body")
    @Test
    public void testPostWithStringBody() {

        // lets try to get random names rather than same ADAM each time
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        System.out.println("randomName = " + randomName);

        String bodyString = "{\n" +
                "  \"name\"  : \"" + randomName + "\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);


    }

    @DisplayName("Posting with json File")
    @Test
    public void testPostWithExternalFile() {

        File file1 = new File("spartan.json");


        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("Maria"))
        ;


    }

    @DisplayName("Posting with Map Object as body")
    @Test
    public void testPostWithMapAsBody() {

        //To be able to this style we need to add a dependcy called jackson or Gson.
        Map<String, Object> bodyMap = new HashMap<>();

        bodyMap.put("name", "Vincent");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", "1234554321")
        ;

        given().
                contentType(ContentType.JSON)
                .body(bodyMap)
                .log().all()
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
        ;


    }


}
