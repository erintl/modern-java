package com.modernjava.chapter9.strategy;

@FunctionalInterface
public interface ValidationStrategy {
  boolean execute(String s);
}
