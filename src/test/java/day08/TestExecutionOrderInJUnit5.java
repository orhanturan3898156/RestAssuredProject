package day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//we have to use this anotation in the class level to make sure it works as we want.
public class TestExecutionOrderInJUnit5 {

    @Test
    @Order(1)
    public void testB(){

    }

    @Test
    @Order(2)
    public void testA(){

    }

    @Test
    @Order(3)
    public void testZ(){

    }

    @Test
    @Order(4)
    public void testK(){

    }


}
