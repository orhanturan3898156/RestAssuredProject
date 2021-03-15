package day03;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class ExtractingDataFromResponseBody {


    @BeforeAll
    public static void init(){

        baseURI="http://www.omdbapi.com";
    }

    @DisplayName("Getting Movie Info")
    @Test
    public void test1(){

        //baseURI="http://www.omdbapi.com/"; or you can write it down below  54.174.216.245

       Response response= given()
               .log().all()
              //  .baseUri("http://www.omdbapi.com")
                .queryParam("apikey", "d3aea025")
                .queryParam("t", "Boss Baby")
                .queryParam("plot", "full")
                .when()
                .get();


       response.prettyPrint();
        System.out.println(response.statusCode());

        String title=response.path("Title"); //this path method help us to see whatever you want to see in the body
        //difference with body is ---body("Title","The Boss Baby")>>> this makes assertions.
        System.out.println(title);

        String year=response.path("Year");
        String actors=response.path("Actors");


        System.out.println("title = " + title);
        System.out.println("year = " + year);
        System.out.println("actors = " + actors);

        String firstRatingSrc=response.path("Ratings[0].Source");
        System.out.println("firstRatingSrc = " + firstRatingSrc);


    }




}
