package org.com.collector.prime;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class PrimeCollector implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> {

    public boolean isPrime(List<Integer> primes, int candidate)
    {
        int candidateRoot=(int)Math.sqrt((double)candidate);
        return takeWhile(primes,i->i<=candidateRoot)
                .stream().noneMatch(p->candidate%p==0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p)
    {
        int i=0;
        for(A item:list)
        {
            if(!p.test(item))
            {
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return ()-> new HashMap<Boolean, List<Integer>>(){
            {
                put(true,new ArrayList<Integer>());
                put(true,new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> acc,Integer prm)->{
            acc.get(isPrime(acc.get(true),prm)).add(prm);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> m1,Map<Boolean, List<Integer>> m2)->{
            m1.get(true).addAll(m2.get(true));
            m2.get(false).addAll(m2.get(false));
            return m1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }
}

