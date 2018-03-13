package org.com;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Apple {
    String color=null;
    Integer wight=0;

    public Apple(String color, Integer wight) {
        this.color = color;
        this.wight = wight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWight() {
        return wight;
    }

    public void setWight(Integer wight) {
        this.wight = wight;
    }
    public static void prettyPrintApple(List<Apple> inventory,Function<String,Apple> formt)
    {
        for (Apple apple:inventory)
        {
            String out=formt.accept(apple);
            System.out.println(out);
        }
    }
    public static <T>List<T> prettyPrintApple(List<T> inventory,Predicate<T> formt)
    {
        ArrayList<T> list = new ArrayList<>();
        for (T apple:inventory)
        {
            if(formt.test(apple))
                list.add(apple);



        }
        return list;
    }
}
