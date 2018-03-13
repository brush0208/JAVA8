package org.com;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class ApplePrintTest {

    List<Apple> inventory=null;
    @Before
    public void setUp()
    {
            inventory=new ArrayList<Apple>(10);
            inventory.add(new Apple("red",100));
            inventory.add(new Apple("red",120));
            inventory.add(new Apple("green",221));
            inventory.add(new Apple("green",20));
    }
    @Test
    public void testPrintColor()
    {


        Apple.prettyPrintApple(inventory,new ApplePrintFormtColor());
    }

    @Test
    public void testLambda()
    {
        List<Apple> result=Apple.prettyPrintApple(inventory,(Apple apple)-> "red".equals(apple.getColor()));

        result.sort(comparing(Apple::getWight).reversed());
        Apple.prettyPrintApple(result,new ApplePrintFormtColor());
        inventory.sort(comparing(Apple::getWight));
    }
}
