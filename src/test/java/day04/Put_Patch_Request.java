package day04;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Put_Patch_Request {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://54.174.216.245";
        port = 8000;
        RestAssured.basePath = "/api";

    }


    @DisplayName("Put Request body as a Map")
    @Test
    public void testPutRequestWithMap() {

        // put request to update spartan with id 421
        // name : put with map  , gender : Male , phone : 1231231234
        // status code 204
        // how do I actually know it's updated since it does not have body in request
        // we can make another get request right after this and assert the body
        // getting random name
        String randomName = new Faker().name().firstName();

        Map<String, Object> updatedBody = new LinkedHashMap<>();
        updatedBody.put("name", randomName);
        updatedBody.put("gender", "Male");
        updatedBody.put("phone", 8745124312L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(updatedBody). // this is how we do it with the map
                when()
                .put("/spartans/{id}", 142).
                then()
                .log().all()
                .statusCode(is(204))
        ;


    }


    @DisplayName("Put Request body as a POJO")
    @Test
    public void testPutRequestWithPojo() {

        // put request to update spartan with id 421
        // name : put with map  , gender : Male , phone : 1231231234
        // status code 204
        // how do I actually know it's updated since it does not have body in request
        // we can make another get request right after this and assert the body
        // getting random name
        String randomName = new Faker().name().firstName();

        // This is how we can provide POJO instead
        Spartan sp1 = new Spartan( randomName , "Female" , 1231231231L ) ;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                //.body(updatedBody). // this is how we do it with the map
                .body(sp1).
                when()
                .put("/spartans/{id}",142).
                then()
                .log().all()
                .statusCode( is(204) )
        ;


    }


    @DisplayName("Patch Request")
    @Test
    public void testPatchPartialUpdate(){

        String randomName=new Faker().name().firstName();

        Map<String, Object> patchBodyMap=new HashMap<>();
        patchBodyMap.put("name",randomName);

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(patchBodyMap)
                .when()
                .patch("/spartans/{id}",96)
                .then()
                .log().all()
                .statusCode(is(204))
                ;


    }



}
