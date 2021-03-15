package day08;

import groovy.transform.ToString;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiDB_Practice {


    @BeforeAll
    public static void init() {

        baseURI = "http://100.25.192.231"; //this one doesn't reqire password
        port = 8000;
        basePath = "/api";

        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url")
                                    , ConfigurationReader.getProperty("spartan1.database.username")
                                    , ConfigurationReader.getProperty("spartan1.database.password"));


    }

    @DisplayName("Testing out my DB Connection")
    @Test
    public void testDB() {
        DB_Utility.runQuery("SELECT * FROM SPARTANS") ;
        //DB_Utility.displayAllData();

        // run this query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query) ;
        DB_Utility.displayAllData();



        //rowcount

        int expectedResult=DB_Utility.getRowCount();
        System.out.println("expectedResult = " + expectedResult);

    }


    @DisplayName("Testing /spartans/search Endpoint and Validate against DB")
    @Test
    public void testSearch() {


        Response response = given()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "a")
                .log().all()
                .when()
                .get("/spartans/search")
                .prettyPeek();

        int resultCount = response.jsonPath().getInt("numberOfElements");
        System.out.println("resultCount = " + resultCount);
        // run this query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query);
        int expectedResult=DB_Utility.getRowCount();
        assertEquals(expectedResult,resultCount);


    }


    @DisplayName("Testing /spartans/search Endpoint and Validate against DB")
    @Test
    public void testSearchVerfyAllID() {


        Response response = given()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "a")
                .log().all()
                .when()
                .get("/spartans/search")
                .prettyPeek();

        List<String> IDListfromResponse=response.jsonPath().getList("content.id",String.class);
        //reason we add String.class ,we want to compare two list and check if their content are same


        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query);

        List<String> idListFromDB=DB_Utility.getColumnDataAsList(1);
        assertEquals(IDListfromResponse.size(),idListFromDB.size());

        //let's compare two list have same content
        assertEquals(idListFromDB,IDListfromResponse);



    }




    @AfterAll
    public static void destroy() {
        RestAssured.reset();
    }


}
