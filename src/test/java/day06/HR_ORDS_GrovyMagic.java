package day06;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class HR_ORDS_GrovyMagic {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.174.216.245";
        RestAssured.port = 1000;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("Testing  the/employees endpoint")
    @Test
    public void testEmployees() {

        Response response = get("/employees").prettyPeek();

        JsonPath jp = response.jsonPath();
        /*
        List<Integer> list=jp.getList("items.employee_id");
        System.out.println(list);
         */

        System.out.println("All Ids" + jp.getList("items.employee_id"));
        System.out.println("First Id" + jp.getInt("items[0].employee_id"));
        System.out.println("Last Id" + jp.getInt("items[-1].employee_id"));


         //we use grovy thing.
        System.out.println("from the first till the fifth" + jp.getList("items[0..4].employee_id"));

        System.out.println("Last name from 10 to 15"+jp.getList("items[10..15].last_name"));

        //get the employee first_name with employee id of 105
        //find and find all where you can specify the creteria to restrict the result
        //find method will return single value that fall into the creteria  compared to findAll will return a list
        //find{it.employee_id=105}

        String result=jp.getString("items.find{it.employee_id==105}.last_name");
        System.out.println("result = " + result);

        int salary=jp.getInt("items.find{it.email=='LDEHAAN'}.salary");
        System.out.println("salary = " + salary);


        List<String> richPeople=jp.getList("items.findAll{it.salary>15000}.first_name");
        System.out.println("richPeople = " + richPeople);

        List<String> allPhones=jp.getList("items.findAll{it.department_id==90}.phone_number");
        System.out.println(allPhones);

        //max,min find out the max salary, and min

        int maxSalary=jp.getInt("items.max{it.salary}.salary");
        System.out.println("maxSalary = " + maxSalary);
        String maxSalaryName=jp.getString("items.max{it.salary}.first_name");
        System.out.println("maxSalaryName = " + maxSalaryName);


    }

}
