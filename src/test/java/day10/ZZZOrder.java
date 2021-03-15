package day10;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DB_Utility;

import java.util.Map;

public class ZZZOrder {

    /*
LibraryAppReusingTheSpecification
LibraryAppReusingTheSpecification_Shorter
User>>POJO

we added lombok dependncy in pom.xml. you add dependecy and plugin
lombook is used for shorting the getter,setter,toString .....

Category-lombokTest
Countries

GetTestDataFromSpartanDatabase



     */
    public static void main(String[] args) {
        String s="200";
//Converting String into int using Integer.parseInt()
        int i=Integer.parseInt(s);
//Printing value of i
        System.out.println(i+1);


      String a="20";

      int b=Integer.parseInt(a);
        System.out.println(b+1);


        int num=25;
        String str=Integer.toString(num);
        System.out.println(str+1);


    }

    @DisplayName("Testing GET /Spartans/{id} by getting the RANDOM id from DB")
    @Test
    public void testDataFromDB_RandomLy(){

        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC" ;
        DB_Utility.runQuery( myQuery ) ;
        // we want to get the rowNum for below method randomly from 1 till the last row count
        // so I can always get different data for my test
        // so first I need to get the row count so I can set the max of my random number
        int rowCount = DB_Utility.getRowCount();
        // get a random number from 1 to rowCount value
        int randomRowNum = new Faker().number().numberBetween(1, rowCount) ;
        Map<String, String> randomRowMap = DB_Utility.getRowMap(randomRowNum);
        System.out.println("CURRENT ID IS "+ randomRowNum);
        System.out.println("CURRENT ROW DATA IS "+ randomRowMap);



    }


}
