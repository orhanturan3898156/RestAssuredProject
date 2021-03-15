package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredMethodChaining {

    @DisplayName("First Attempt for chaining")
    @Test
    public void chainingMethodsTest1() {

        /*
        given(): some more infomation you want to provide other than url.like header,query,path variable,body
        when() you send a request GET,POST,PUt PATCH,DELETE
        then(): validate something

         */

        //if you remove when ,it will still works.but it is better to use for readibility.

         /*
             ?-Question mark means to use query param
             if you see " : " >>it's pathParam.
         */

        when().get("http://3.226.201.68:8000/api/hello")//sending request
                .then()//assertions starts here
                // .statusCode(200).//asserting status code or  below one can be used
                .statusCode(is(200)).
                header("Content-Length", equalTo("17"));//asserttion header is 17


    }

    @DisplayName("Using Hamcrest matcher for assertion")
    @Test
    public void testingWithMatcher() {


        when().
                get("http://54.174.216.245:8000/api/hello").   // sending a request to this url
                prettyPeek().
                then().                         // asssrtions start here
                statusCode(is(200)).        // asserting status code is 200
                header("Content-Length", equalTo("17")).
                header("Content-Type", is("text/plain;charset=UTF-8")).
                body(is("Hello from Sparta"));
        ;  // asserting header is "17"
        ;

    }

    @DisplayName("Testing GET /api/Spartans endpoint")
    @Test
    public void testAllSpartans() {


        given().  // add all your request specific information like header, query param, path var, body
                header("Accept", "application/xml").
                when().
                get("http://54.174.216.245:8000/api/spartans").
                prettyPeek().
                then().
                statusCode(is(200))
                ;

    }


}
