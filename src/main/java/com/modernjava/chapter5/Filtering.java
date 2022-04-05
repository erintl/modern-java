package com.modernjava.chapter5;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import com.modernjava.chapter4.Dish;

public class Filtering {

  public static void main(String[] args) {
    // Filtering with a predicate
    System.out.println("Filtering with a predicate:");
    List<Dish> vegetarianDishes =
      Dish.menu.stream()
      .filter(Dish::isVegetarian)
      .collect(toList());
    vegetarianDishes.forEach(System.out::println);

    // Filtering unique elements
    System.out.println("\nFiltering unique elements:");
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    numbers.stream()
      .filter(i -> i % 2 == 0)
      .distinct()
      .forEach(System.out::println);

    // Special Menu - pre-sorted ascending by number of calories
    List<Dish> specialMenu = Arrays.asList(
      new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
      new Dish("prawns", false, 300, Dish.Type.FISH),
      new Dish("rice", true, 350, Dish.Type.OTHER),
      new Dish("chicken", false, 400, Dish.Type.MEAT),
      new Dish("french fries", true, 530, Dish.Type.OTHER)
    );

    // Slicing a stream examples
    System.out.println("\nFiltered sorted menu:");
    List<Dish> filteredMenu = specialMenu.stream()
      .filter(dish -> dish.calories() < 320)
      .collect(toList());
    filteredMenu.forEach(System.out::println);

    System.out.println("\nSorted menu sliced with takeWhile():");
    List<Dish> slicedMenu1 = specialMenu.stream()
      .takeWhile(dish -> dish.calories() < 320)
      .collect(toList());
    slicedMenu1.forEach(System.out::println);

    System.out.println("\nSorted menu sliced with dropWhile():");
    List<Dish> slicedMenu2 = specialMenu.stream()
      .dropWhile(dish -> dish.calories() < 320)
      .collect(toList());
    slicedMenu2.forEach(System.out::println);

    System.out.println("\nTruncating a stream:");
    List<Dish> truncatedMenu = specialMenu.stream()
      .filter(d -> d.calories() > 299)
      .limit(3)
      .collect(toList());
    truncatedMenu.forEach(System.out::println);

    System.out.println("\nQuiz 5.1:");
    List<Dish> meatFilteredMenu = Dish.menu.stream()
      .filter(d -> d.type().equals(Dish.Type.MEAT))
      .limit(2)
      .collect(toList());
    meatFilteredMenu.forEach(System.out::println);
  }
}
