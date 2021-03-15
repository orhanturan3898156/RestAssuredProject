package day01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    public void calculator() {
        System.out.println("Hello World");
        assertEquals(9, 5 + 4);
        assertEquals(10, 5 + 4);

    }

    //we add a display name instead of method name.
    @DisplayName("I am the testing name")
    @Test
    public void nameTest() {
        String name = "Sedat";
        String lastname = "Tore";

        assertEquals("Sedat Tore",name+" "+lastname);

    }


}
