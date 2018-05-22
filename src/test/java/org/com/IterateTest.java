package org.com;

import org.junit.Test;

import java.util.function.IntToDoubleFunction;
import java.util.stream.Stream;

public class IterateTest {
    @Test
    public void testIterate()
    {
        Function<Integer,Integer> f = (Integer a) -> a + 1;
        Stream.iterate(new int[]{0,1},n-> new int[]{n[1],n[0]+n[1]})
                .limit(10)
                .forEach(t->System.out.println("("+t[0]+","+t[1]+")"));

    }
}
