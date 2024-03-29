package com.modernjava.chapter2;

public class HeavyApplePredicate implements ApplePredicate {
  @Override
  public boolean test(Apple apple) {
    return apple.getWeight() > 150;
  }
}
