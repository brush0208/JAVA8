package org.com;

public interface Function<E,T> {
    E accept(T t);
}
