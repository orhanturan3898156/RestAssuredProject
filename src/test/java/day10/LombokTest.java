package day10;

import POJO.Category;
import POJO.Countries;
import org.junit.jupiter.api.Test;

public class LombokTest {

    @Test
    public void test(){
        Category c1=new Category("12","ABC");
        System.out.println(c1);


        Countries ar=new Countries("AR","Argentina",2);
        System.out.println("ar = " + ar);

    }


}
