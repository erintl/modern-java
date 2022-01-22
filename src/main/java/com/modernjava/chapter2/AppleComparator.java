package com.modernjava.chapter2;

import java.util.Comparator;

/**
 * Compares Apples by weight
 */
public class AppleComparator implements Comparator<Apple> {
  @Override
  public int compare(Apple a1, Apple a2) {
    return a1.getWeight().compareTo(a2.getWeight());
  }
}
