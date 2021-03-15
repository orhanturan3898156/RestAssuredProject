

import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Self {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://3.226.201.68";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @Test
    public void test1() {

        Map<String, Object> body1 = new LinkedHashMap<>();
        body1.put("gender","Male");
        body1.put("name", "Kamil");
        body1.put("phone","1234567890");



        given()
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("/spartans")
                .then()
                .statusCode(is(201));


    }


}
