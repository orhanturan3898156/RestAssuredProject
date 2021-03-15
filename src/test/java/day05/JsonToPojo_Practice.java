package day05;

import POJO.Spartan2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class JsonToPojo_Practice {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://54.174.216.245";
        port = 8000;
        basePath = "/api";

    }

    @DisplayName("Json to Pojo GET /spartans/{id} ")
    @Test
    public void testSpartanJsonToSpartanObject() {

        int newID = SecureSpartanTest.createRandomSpartan();

        Response response = given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id", newID).
                        when()
                .get("/spartans/{id}")
                .prettyPeek();
        // as method from response
        // accept a class type to define what is the type object you are trying to store the response as
        //  Spartan2 class we created has all the fields that match the json fields from the response
        // So it will map all the json fields to the java fields and return Spartan2 POJO Object
        // in a simple word turn below json into Java object
        /**
         * {
         *     "id": 261,
         *     "name": "Elma",
         *     "gender": "Male",
         *     "phone": 9999999998
         * }
         *
         * into  new Spartan2(261,"Elma","Male",9999999998L) Java Object
         * so we can work with the data using java object directly
         */
        Spartan2 sp = response.as(Spartan2.class);
        // above line is almost as if you are doing below line
//        Spartan2 sp = new Spartan2(261,"Elma","Male",9999999998L)
        System.out.println("sp = " + sp);

    }

    @DisplayName("Search Request and save 1st Result as Spartan2 Pojo")
    @Test
    public void gettingNestedJsonAsPojo() {

        Response response = given()
                .log().all()
                .auth().basic("admin", "admin")
                .queryParam("gender", "Male").
                        when()
                .get("/spartans/search");
        // .prettyPeek();

        System.out.println("response.statusCode() = " + response.statusCode());

        JsonPath jp = response.jsonPath();

        System.out.println("First ID: " + jp.getInt("content[0].id"));
        System.out.println("First Name: " + jp.getString("content[0].name"));

        Spartan2 firstMaleSpartan = jp.getObject("content[0]", Spartan2.class);//this will return all info about first object.look into below
        System.out.println(firstMaleSpartan);

        //System.out.println("firstMaleSpartan = " + firstMaleSpartan);
        System.out.println("The Spartan id from POJO is " + firstMaleSpartan.getId());
        System.out.println("The Spartan name from POJO is " + firstMaleSpartan.getName());
        System.out.println("The Spartan gender from POJO is " + firstMaleSpartan.getGender());
        System.out.println("The Spartan phone from POJO is " + firstMaleSpartan.getPhone());


    }

    @DisplayName("Save the json array as List<Spartan>")
    @Test
    public void testJsonArrayToListOFPojo() {

        Response response = given()
                .auth().basic("admin", "admin")
                .queryParam("gender", "Female")
                .when()
                .get("/spartans/search");


        JsonPath jp = response.jsonPath();

        List<Integer> ids = jp.getList("content.id");
        System.out.println(ids);

        List<String> names = jp.getList("content.name");
        System.out.println(names);


        List<Spartan2> spartans2List = jp.getList("content", Spartan2.class);
        System.out.println(spartans2List);
/*
       for (Spartan2 each:spartans2List){
           System.out.println(each);    1st way
       }

 */

        spartans2List.forEach(each -> System.out.println(each));



    }


}
