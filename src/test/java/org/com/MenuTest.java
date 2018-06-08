package org.com;

import org.com.dash.Dash;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class MenuTest {

    public List<Dash> menu= Arrays.asList(
            new Dash("pork",false,800,Dash.Type.MEAT),
            new Dash("beef",false,700,Dash.Type.MEAT),
            new Dash("chicken",false,400,Dash.Type.MEAT),
            new Dash("french fries",true,530,Dash.Type.OTHER),
            new Dash("rice",true,350,Dash.Type.OTHER),
            new Dash("season fruit",true,120,Dash.Type.OTHER),
            new Dash("pizza",true,500,Dash.Type.OTHER),
            new Dash("prawns",false,300,Dash.Type.FISH),
            new Dash("salmon",false,450,Dash.Type.FISH)
            );
    @Before
    public void setUp()
    {

    }
    @Test
    public void testFilter()
    {
        menu.stream().filter(d->d.getCalories()>300)
        .limit(3)
        .collect(toMap(Dash::getName,Function.identity()));
    }
    @Test
    public void testMapAndFaltMap()
    {
        String[] words={"hello","world"};
        Stream<String> stream = Arrays.stream(words);
        Stream<String[]> stream1 = stream.map(word -> word.split(""));
        Stream<Stream<String>> streamStream = stream1.map(Arrays::stream);
        Stream<String> stringStream = stream1.flatMap(Arrays::stream);

        List<Stream<String>> streamList = Arrays.asList(words).stream().
                    map(str -> str.split("")).
                      map(str -> Arrays.stream(str)).
                 collect(Collectors.toList());
       List<String> collect = stream.map(word -> word.split(""))
                .flatMap(Arrays::stream).distinct().collect(toList());
        System.out.println(streamList);

    }
    @Test
    public void testMap()
    {
        Arrays.asList("brush","ss");
        Integer[] num={1,2,3};
        Integer[] num2={4,5};
        Arrays.stream(num).flatMap(
                a->Arrays.stream(num2).filter(b->(a+b)%3==0).map(
                b->Arrays.asList(a,b))
        ).collect(toList()).forEach(System.out::println);

    }
    @Test
    public void testReduce()
    {
      //  Integer reduce = menu.stream().map(dash -> 1).reduce(0, Integer::sum);
        //System.out.println(reduce);
        int h=100;
        //Predicate
        h^=(h>>>20)^(h>>>12);
        System.out.println("h:"+h);
        int x= h^(h>>>7)^(h>>>4);
        System.out.println("x:"+x);

    }

    @Test
    public void testGroupBy()
    {
        menu.stream().collect(groupingBy(Dash::getName));
    }
    @Test
    public void testCollectingAndThen()
    {
        Map<Dash.Type, Dash> collect = menu.stream().collect(groupingBy(Dash::getType
                , collectingAndThen(maxBy(Comparator.comparingInt(Dash::getCalories)), Optional::get)));
        System.out.println(collect);
    }
}
