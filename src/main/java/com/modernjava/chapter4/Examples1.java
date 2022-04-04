package com.modernjava.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Examples1 {

  public static void main(String[] args) {
    java7LowCalorieDishes();
    java8LowCalorieDishes();
    printHighCalorieDishes(Dish.menu);

    List<String> names = Dish.menu.stream()
      .filter(dish -> {
        System.out.println("filtering:" + dish.name());
        return dish.calories() > 300;
      })
      .map(dish -> {
        System.out.println("mapping:" + dish.name());
        return dish.name();
      })
      .limit(3)
      .collect(Collectors.toList());
  }

  public static void java7LowCalorieDishes() {
    List<Dish> lowCalorieDishes = new ArrayList<>();
    for (Dish dish: Dish.menu) {
      if (dish.calories() < 400) {
        lowCalorieDishes.add(dish);
      }
    }

    Collections.sort(lowCalorieDishes, new Comparator<Dish>() {
      @Override
      public int compare(Dish dish1, Dish dish2) {
        return Integer.compare(dish1.calories(), dish2.calories());
      };
    });

    List<String> lowCalorieDishNames = new ArrayList<>();
    for (Dish dish: lowCalorieDishes) {
      lowCalorieDishNames.add(dish.name());
    }
    System.out.println(lowCalorieDishNames);
  }

  public static void java8LowCalorieDishes() {
    List<String> lowCalorieDishNames = Dish.menu.parallelStream()
      .filter(d -> d.calories() < 400)
      .sorted(Comparator.comparing(Dish::calories))
      .map(Dish::name)
      .collect(Collectors.toList());
      System.out.println(lowCalorieDishNames);
  }

  public static void printHighCalorieDishes(List<Dish> dishes) {
    List<String> threeHighCaloricDishNames = dishes.stream()
      .filter(dish -> dish.calories() > 300)
      .map(Dish::name)
      .limit(3)
      .collect(Collectors.toList());

    System.out.println(threeHighCaloricDishNames);
  }
}
