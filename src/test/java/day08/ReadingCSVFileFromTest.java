package day08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingCSVFileFromTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void simpleRead(int num1, int num2) {
        System.out.println("num1: "+ num1);
        System.out.println("num2: "+ num2);

    }

    @ParameterizedTest(name = "iteration {index} -> {0}+{1}={2}")//this gives a name to your test.
    // index gives number.row 1,row 2 ,row 3 .....
    @CsvFileSource(resources = "/numbers.csv", numLinesToSkip = 1)
    public void testAddition(int n1, int n2,int result) {

        assertEquals(result,n1+n2,"WRONG RESULT");

    }




}
