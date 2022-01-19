package com.modernjava.chapter2;

public class SimpleAppleFormatter implements AppleFormatter {
  
  @Override
  public String accept(Apple apple) {
    return String.format("An apple of %d g", apple.getWeight());
  }
}
