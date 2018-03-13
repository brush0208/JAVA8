package org.com;

public class ApplePrintFormtColor implements Function<String,Apple> {

    public String accept(Apple apple) {
        return apple.getColor()+" "+apple.getWight()+'g';
    }
}
