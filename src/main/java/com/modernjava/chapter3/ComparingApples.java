package com.modernjava.chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.modernjava.chapter2.Apple;
import com.modernjava.chapter2.AppleComparator;
import com.modernjava.chapter2.Color;

public class ComparingApples {

  public static void main(String[] args) {
    // Passing code
    List<Apple> inventory1 = getInventory();
    inventory1.sort(new AppleComparator());
    System.out.println(inventory1);

    // Anonymous class
    List<Apple> inventory2 = getInventory();
    inventory2.sort(new Comparator<Apple>() {
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
      }
    });
    System.out.println(inventory2);

    // Lambda
    List<Apple> inventory3 = getInventory();
    inventory3.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
    System.out.println(inventory3);

    // Comparing helper
    Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
    List<Apple> inventory4 = getInventory();
    inventory4.sort(c);
    System.out.println(inventory4);

    List<Apple> inventory5 = getInventory();
    inventory5.sort(Comparator.comparing(Apple::getWeight));
    System.out.println(inventory5);

    List<Apple> inventory6 = getInventory();
    inventory6.sort(Comparator.comparing(Apple::getWeight).reversed());
    System.out.println(inventory6);

    List<Apple> inventory7 = getCountryInventory();
    inventory7.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getCountry));
    System.out.println(inventory7);
  }

  public static List<Apple> getInventory() {
    return Arrays.asList(
      new Apple(155, Color.GREEN),
      new Apple(80, Color.GREEN),
      new Apple(120, Color.RED));
  }

  public static List<Apple> getCountryInventory() {
    return Arrays.asList(
      new Apple(20, Color.GREEN, "United States"),
      new Apple(20, Color.RED, "England"),
      new Apple(20, Color.GREEN, "Canada")
    );
  }
}
