package com.modernjava.chapter2;

@FunctionalInterface
public interface Predicate<T> {
  boolean test(T t);
}
