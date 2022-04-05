package com.modernjava.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.modernjava.chapter4.Dish;

public class Finding {
  
  public static void main(String[] args) {
    if (isVegetarianFriendlyMenu()) {
      System.out.println("The menu is (somewhat) vegetarian friendly!!");
    }
    System.out.printf("The menu is healthy: %B%n", isHealthyMenu());
    System.out.printf("The menu is healthy: %B%n", isHealthyMenu2());

    Optional<Dish> vegetarianDish = findVegetarianDish();
    vegetarianDish.ifPresent(d -> System.out.println(d.name()));

    Optional<Integer> firstNumber = findFirstSquareDivisibleByThree();
    firstNumber.ifPresent(System.out::println);
  }

  private static boolean isVegetarianFriendlyMenu() {
    return Dish.menu.stream().anyMatch(Dish::isVegetarian);
  }

  private static boolean isHealthyMenu() {
    return Dish.menu.stream().allMatch(d -> d.calories() < 1000);
  }

  private static boolean isHealthyMenu2() {
    return Dish.menu.stream().noneMatch(d -> d.calories() >= 1000);
  }

  private static Optional<Dish> findVegetarianDish() {
    return Dish.menu.stream().filter(Dish::isVegetarian).findAny();
  }

  private static Optional<Integer> findFirstSquareDivisibleByThree() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    return numbers.stream()
      .map(n -> n * n)
      .filter(n -> n % 3 == 0)
      .findFirst();
  }
}
