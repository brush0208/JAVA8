package org.com;

import org.junit.Test;

import java.util.stream.IntStream;

public class IntStreamTest {
    @Test
    public void testInt()
    {
        IntStream.rangeClosed(1,100).filter(n->n%2==0);
    }

    @Test
    public void testGg()
    {
        int a=3;
        IntStream.rangeClosed(1,100)
                .filter(b->Math.sqrt(a*a+b*b)%1==0)
                .boxed()
                .map(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)});
    }

}
