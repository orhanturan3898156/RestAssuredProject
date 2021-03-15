package day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GOT_Homework {


    @DisplayName("Getting All Members of House Stark")
    @Test
    public void testingGOT(){
        Response response=given()
                .baseUri("https://api.got.show")
                .basePath("/api/book")
                .when()
                .get("/characters")
                ;

        List<String> houseStarList=response.jsonPath().getList("findAll{it.house=='House Stark'}.name");
        System.out.println(houseStarList);
        System.out.println(houseStarList.size());//76
        assertThat(houseStarList,hasSize(76));
        assertThat(houseStarList,hasItem("Eddard Stark"));
        assertThat(houseStarList,hasItems("Robb Stark","Lyanna Stark","Arya Stark"));



    }
}
