package day05;

import POJO.Region;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_Test {

    @BeforeAll
    public static void setUp() {

        baseURI = "http://54.174.216.245";
        port = 1000;
        basePath = "/ords/hr";


    }

    @Test
    public void testRegion() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("region_id", 1)
                .when()
                .get("/regions/{region_id}")
                .prettyPeek();

        Region r1 = response.as(Region.class); //there is region_id,region_name and links when you get it.
        // but we nly want to get region_id and region_name
        // we added REgionPJO to -- @JsonIgnoreProperties(ignoreUnknown = true)
        System.out.println("r1 = " + r1);


    }


    @DisplayName("Testing the /regions endpoint")
    @Test
    public void testRegionJsonArraytoPojoList() {

        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .when()
                .get("/regions");

        JsonPath jp=response.jsonPath();

        System.out.println("First region name: "+jp.getString("items[0].region_name"));
        System.out.println("Last region name: "+jp.getString("items[-1].region_name"));


        System.out.println("First region name: "+jp.getInt("items[0].region_id"));
        System.out.println("Last region name: "+jp.getInt("items[-1].region_id"));


        List<Region> regionList = jp.getList("items", Region.class) ;

        System.out.println("regionList = " + regionList);

    }


}
