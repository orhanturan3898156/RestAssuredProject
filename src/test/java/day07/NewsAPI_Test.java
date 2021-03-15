package day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NewsAPI_Test {


    @DisplayName("News API get all News Author if the Source id is not full")
    @Test
    public void testNews() {
        String apiToken = "42bb42f550eb432a90d48201b33380e5";



        ///GET http://newsapi.org/v2/top-headlines?country=us
        /*
             ?-Question mark means to use query param
             if you see " : " >>it's pathParam.
         */
        Response response = given()
                .baseUri("http://newsapi.org")
                .basePath("/v2")
                .header("Authorization", "Bearer " + apiToken)//make sure to space
                .queryParam("country", "us")
                .log().all()
                .when()
                .get("/top-headlines");

        JsonPath jp=response.jsonPath();

        List<String> allAuthor=jp.getList("articles.author");
        System.out.println(allAuthor);

        allAuthor.forEach(eachAuthor-> System.out.println(eachAuthor)); //or use for each loop

        List<String> allAuthorFiltered=jp.getList("articles.findAll{it.source.id !=null}.author");
        //System.out.println(allAuthorFiltered);

        for (String author:allAuthorFiltered){
            System.out.println("autor: "+author);
        }





    }


}
