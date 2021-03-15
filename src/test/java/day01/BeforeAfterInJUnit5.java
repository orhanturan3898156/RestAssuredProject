package day01;

import org.junit.jupiter.api.*;

public class BeforeAfterInJUnit5 {


    @BeforeAll
    public static void setUp() {
        System.out.println("This runs before All"); //this is like BeforeClass, but has to be static, not instance.

    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Running before each test"); //like Before Method
    }

    @Test
    public void test1() {
        System.out.println("test1 is running");
    }

    @Disabled  //same as ignore
    @AfterEach
    public void afterEachTest() {
        System.out.println("Running after each test"); //like Before Method
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("this runs all the way the end"); //This is like After Class.
    }


}
