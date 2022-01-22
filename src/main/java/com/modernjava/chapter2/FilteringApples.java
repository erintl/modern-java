package com.modernjava.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilteringApples {
  public static void main(String[] args) {
    appleFilteringExamples();
    appleSortingExamples();
  }

  public static void appleFilteringExamples() {
    List<Apple> inventory =  Arrays.asList(
      new Apple(80, Color.GREEN),
      new Apple(155, Color.GREEN),
      new Apple(120, Color.RED));
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    System.out.println("Pretty print apples 1:");
    prettyPrintApples(inventory, new FancyAppleFormatter());
    System.out.println("\nPretty print apple 2:");
    prettyPrintApples(inventory, new SimpleAppleFormatter());

    System.out.println("\nFilter green apples 1:");
    List<Apple> greenApples = filterGreenApples(inventory);
    System.out.println(greenApples);

    System.out.println("\nFilter green apples 2:");
    List<Apple> greenApples2 = filterApplesByColor(inventory, Color.GREEN);
    System.out.println(greenApples2);

    System.out.println("\nFilter green apples 3:");
    List<Apple> greenApples3 = filterApples(inventory, new GreenApplePredicate());
    System.out.println(greenApples3);

    System.out.println("\nFilter red apples 1:");
    List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
    System.out.println(redApples);


    System.out.println("\nFilter red apples 2:");
    List<Apple> redApples2 = filterApples(inventory, new ApplePredicate() {
      @Override
      public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor());
      }
    });
    System.out.println(redApples2);

    System.out.println("\nFilter red apples 3:");
    List<Apple> redApples3 = filterApples(inventory, (Apple apple) -> 
    Color.RED.equals(apple.getColor()));
    System.out.println(redApples3);

    System.out.println("\nFilter red apples 4:");
    List<Apple> redApples4 = filter(inventory, (Apple apple) ->
    Color.RED.equals(apple.getColor()));
    System.out.println(redApples4);


    System.out.println("\nFilter red apples 5:");
    List<Apple> redApple5 = filter(inventory, new Predicate<Apple>() {
      @Override
      public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor());
      }
    });
    System.out.println(redApple5);


    System.out.println("\nFilter red apples 6:");
    List<Apple> redApples6 = filter(inventory, FilteringApples::isRed);
    System.out.println(redApples6);


    System.out.println("\nFilter apples by weight 1:");
    List<Apple> heavyApples = filterApplesByWeight(inventory, 150);
    System.out.println(heavyApples);

    System.out.println("\nFilter apples by weight 2:");
    List<Apple> heavyApples2 = filterApples(inventory, new HeavyApplePredicate());
    System.out.println(heavyApples2);

    System.out.println("\nFilter even numbers:");
    List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
    System.out.println(evenNumbers);

    System.out.println("\nFilter even numbers 2:");
    List<Integer> evenNumbers2 = filter(numbers, FilteringApples::isEven);
    System.out.println(evenNumbers2);
  }

  public static void appleSortingExamples() {
    List<Apple> inventory =  Arrays.asList(
      new Apple(80, Color.GREEN),
      new Apple(155, Color.GREEN),
      new Apple(120, Color.RED));

    List<Apple> inventory2 =  Arrays.asList(
      new Apple(80, Color.GREEN),
      new Apple(155, Color.GREEN),
      new Apple(120, Color.RED));
    
    System.out.println("\nSort inventory: ");
    inventory.sort(new Comparator<Apple>() {
      @Override
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
      }
    });
    System.out.println(inventory);

    inventory2.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    System.out.println(inventory2);
  }

  public static List<Apple> filterGreenApples(List<Apple> apples) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : apples) {
      if (Color.GREEN.equals(apple.getColor())) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByColor(List<Apple> apples, Color color) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : apples) {
      if (apple.getColor().equals(color)) {
        result.add(apple);
      }
    } 
    return result;
  }

  public static List<Apple> filterApplesByWeight(List<Apple> apples, int weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : apples) {
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApples(List<Apple> apples, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : apples) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> result = new ArrayList<>();
    for (T e : list) {
      if (p.test(e)) {
        result.add(e);
      }
    }
    return result;
  }

  public static void prettyPrintApples(List<Apple> apples, AppleFormatter formatter) {
    apples.forEach(a -> System.out.println(formatter.accept(a)));
  }

  public static boolean isRed(Apple a) {
    return Color.RED.equals(a.getColor());
  }

  public static boolean isEven(Integer i) {
    return i % 2 == 0;
  }
}
