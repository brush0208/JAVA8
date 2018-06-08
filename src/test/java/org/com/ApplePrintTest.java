package org.com;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

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


        ApplePrintFormtColor applePrintFormtColor = new ApplePrintFormtColor();
        Apple.prettyPrintApple(inventory,applePrintFormtColor::accept);
    }

    @Test
    @Ignore
    public void testLambda()
    {
        List<Apple> result=Apple.prettyPrintApple(inventory,(Apple apple)-> "red".equals(apple.getColor()));

        result.sort(comparing(Apple::getWight).reversed());
        Function<Apple, String> getColor = Apple::getColor;
        Apple.prettyPrintApple(result,new ApplePrintFormtColor());
        Predicate<Apple> readApple=apple->"red".equals(getColor);
        readApple.and(a -> a.getWight()>150);
        inventory.sort(comparing(Apple::getWight).reversed().thenComparing(Apple::getColor));
        //inventory.sort();
        List<String[]> collect = inventory.stream().map(Apple::getColor).map(w -> w.split("")).collect(toList());

        OptionalInt max = inventory.stream().mapToInt(Apple::getWight).max();
        max.orElse(100);
        IntStream.range(1,100);
        inventory.iterator();
    }

    @Test
    public void testStream()
    {
       inventory.stream().filter(apple -> apple.getWight()>100).forEach(System.out::println);
    }
}
