package com.modernjava.chapter2;

public class FancyAppleFormatter implements AppleFormatter {
  
  @Override
  public String accept(Apple apple) {
    String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
    return String.format("A %s %s apple", characteristic, apple.getColor());
  }
}
