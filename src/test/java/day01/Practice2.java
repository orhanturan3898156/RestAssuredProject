package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Practice2 {
    //hamcrest library is a assertion library
    //it is more human readable
    //most importatnyly RestAssusurred use hamcrest matcher when we chain multiple rrest assured methods

    @Test
    public void test1() {

        int num1 = 5;
        int num2 = 4;

        //hamcrest library use the asserThat method for all assertions
        //is(some value)
        //equalTo(some value) or is(equalTo(someValue))
        assertThat(num1 + num2, is(9)); //more like human readable
        assertThat(num1 + num2, equalTo(9));

        //not(value)
        //is (not(some value))
        //not(equalTo(some vAlue))
        assertThat(num1 + num2, not(11));
        assertThat(num1 + num2, is(not(11)));

        String firstName = "Orhan ";
        String lastName = "Turan";

        assertThat(firstName + lastName, is("Orhan Turan"));
        assertThat(firstName + lastName, equalTo("Orhan Turan"));
        assertThat(firstName + lastName, is(equalTo("Orhan Turan")));

        assertThat(firstName, equalToIgnoringCase("orhan "));

        assertThat(firstName, equalToCompressingWhiteSpace("Orhan"));


    }

    @DisplayName("Support for Collection")
    @Test
    public void test2() {

        List<Integer> numbList= Arrays.asList(11,44,3,55,88,5);

       assertThat(numbList,hasSize(6));
       assertThat(numbList,hasItem(11));
      // assertThat(numbList,contains(11,44,55));
        assertThat(numbList,  containsInAnyOrder(11, 44, 55,3,88,5 )   );





    }


}
