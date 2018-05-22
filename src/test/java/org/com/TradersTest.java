package org.com;

import org.com.trader.Trader;
import org.com.trader.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * java8实战P98
 */
public class TradersTest {

    List<Transaction> transactions;
    @Before
    public void setup()
    {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    /**
     * 找出2011年的所有交易并按交易额从小到大排序
    * */
    public void testf1()
    {
        transactions.stream().filter(transaction -> transaction.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
    }

    /**
     * 交易员都在哪些不同的城市工作过？
     */
    @Test
    public void testf2()
    {
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序。
     */
    @Test
    public void testf3()
    {
        transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader()
                .getCity())).map(Transaction::getTrader).
                distinct().sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }

    @Test
    public void testf4()
    {
        String collect = transactions.stream().map(Transaction::getTrader).distinct()
                .map(Trader::getName)
                .sorted()
                //.reduce("",(n1,n2)->n1+n2);
                .collect(joining());
        System.out.println(collect);
    }
    @Test
    public void testf5()
    {
        boolean anyMatch = transactions.stream().map(Transaction::getTrader)
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        if(anyMatch)
            System.out.println("finded");
        else
            System.out.println("non");
    }

    @Test
    public void testf6()
    {
        Optional<Integer> reduce = transactions.stream().filter(transaction -> "Milan".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue).reduce(Integer::sum);
        System.out.println("sum:"+reduce.get());
    }

    @Test
    public void testf7()
    {
        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println(reduce.get());
    }
    @Test
    public void testf8()
    {
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

    }

    @Test
    public void testGroupMy()
    {
        Map<Integer, List<Transaction>> collect = transactions.stream().collect(groupingBy(Transaction::getYear));

    }
}
