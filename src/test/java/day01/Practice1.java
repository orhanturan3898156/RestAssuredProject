package day01;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

//Hamcrest is another Assertion Library.

public class Practice1 {

    @Test
    public void test1() {
        Response response = get("http://54.174.216.245:8000/api/hello");
        //getting statusCode.
        System.out.println("status code of response: " + response.statusCode());

        System.out.println("response.getStatusLine() = " + response.getStatusLine());

        System.out.println("response getStatusCode() =" + response.getStatusCode());

        System.out.println("Getting the value of the Date header : " + response.header("Date"));
        System.out.println("Getting the value of the Date header : " + response.getHeader("Date"));

        System.out.println("Content-Length header: " + response.getHeader("Content-Length"));

        System.out.println(response.contentType());
        System.out.println(response.getContentType());


    }

    @DisplayName("Testing / hello endpoint")
    @Test
    public void testHello() {

        Response response = get("http://3.226.201.68:8000/api/hello");
        assertEquals(200, response.statusCode());
        //below all same.just slight difference.
        assertEquals("text/plain;charset=UTF-8", response.getContentType());
        assertEquals("text/plain;charset=UTF-8", response.header("Content-Type"));
        assertEquals("text/plain;charset=UTF-8", response.getHeader("Content-Type"));
        assertEquals("17", response.getHeader("Content-Length"));


    }

    @DisplayName("Testing Hello Body")
    @Test
    public void testingHelloResponseBody() {

        Response response = get("http://54.174.216.245:8000/api/hello");

        System.out.println(response.body().asString());//we can use either this or below one.both do same.
        System.out.println(response.asString());
        String helloBody = response.asString();

        assertEquals("Hello from Sparta", response.body().asString());
        assertEquals("Hello from Sparta", response.asString());

        assertEquals(17, helloBody.length());


    }


    @DisplayName("Printing the response body using method")
    @Test
    public void printingBody() {
        Response response = get("http://54.174.216.245:8000/api/hello");
        //it is a printing method prettyPrint().
        System.out.println(response.prettyPrint());

        System.out.println("=====================");

        //it print out the entire response
        System.out.println(response.prettyPeek());

        System.out.println("=====================");
        int statusCode = response.prettyPeek().statusCode();
        System.out.println("status code: "+statusCode);



    }


}

