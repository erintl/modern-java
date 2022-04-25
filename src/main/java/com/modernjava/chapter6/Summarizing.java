package com.modernjava.chapter6;

import static java.util.stream.Collectors.*;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.function.BinaryOperator;
import com.modernjava.chapter4.Dish;

public class Summarizing {

  public static void main(String[] args) {
    long howManyDishes1 = Dish.menu.stream().collect(counting());
    long howManyDishes2 = Dish.menu.stream().count();
    System.out.println("Dishes count 1: " + howManyDishes1);
    System.out.println("Dishes count 2: " + howManyDishes2);

    Comparator<Dish> dishCalorieComparator = Comparator.comparingInt(Dish::calories);
    Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(maxBy(dishCalorieComparator));
    mostCalorieDish.ifPresent(value -> System.out.println("The most calorie dish is: " + value));

  
    System.out.println("\nNumber of dishes: " + howManyDishes());
    System.out.println("Most caloric dish 1: " + findMostCaloricDish1());
    System.out.println("Most caloric dish 2: " + findMostCaloricDish2());
    System.out.println("Total calories in menu 1: " + calculateTotalCalories1());
    System.out.println("Total calories in menu 2: " + calculateTotalCalories2());
    System.out.println("Average calories in menu: " + calculateAverageCalories());
    System.out.println("Menu statistics: " + calculateMenuStatistics());
    System.out.println("Short menu: " + getShortMenu());
    System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
  }

  private static long howManyDishes() {
    return Dish.menu.stream().collect(counting());
  }
  
  private static Dish findMostCaloricDish1() {
    return Dish.menu.stream().collect(reducing((d1, d2) -> d1.calories() > d2.calories() ? d1 : d2)).get();
  }

  private static Dish findMostCaloricDish2() {
    Comparator<Dish> dishCalorieComparator = Comparator.comparingInt(Dish::calories);
    BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCalorieComparator);
    return Dish.menu.stream().collect(reducing(moreCaloricOf)).get();
  }

  private static int calculateTotalCalories1() {
    return Dish.menu.stream().collect(summingInt(Dish::calories));
  }

  private static int calculateTotalCalories2() {
    return Dish.menu.stream().collect(reducing(0, Dish::calories, (i, j) -> i + j));
  }

  private static double calculateAverageCalories() {
    return Dish.menu.stream().collect(averagingInt(Dish::calories));
  }

  private static IntSummaryStatistics calculateMenuStatistics() {
    return Dish.menu.stream().collect(summarizingInt(Dish::calories));
  }

  private static String getShortMenu() {
    return Dish.menu.stream().map(Dish::name).collect(joining());
  }

  private static String getShortMenuCommaSeparated() {
    return Dish.menu.stream().map(Dish::name).collect(joining(", "));
  }
}
