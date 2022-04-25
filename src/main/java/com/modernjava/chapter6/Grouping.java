package com.modernjava.chapter6;

import static java.util.stream.Collectors.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import com.modernjava.chapter4.Dish;

public class Grouping {

  public enum CaloricLevel {
    DIET,
    NORMAL,
    FAT
  }

  public static void main(String[] args) {
    System.out.println("Dishes grouped by type: " + groupDishesByType());
    System.out.println("\nDishes grouped by caloric level: " + groupDishesByCaloricLevel());
    System.out.println("\nDishes grouped by type and: " + groupCaloricDishesByType());
    System.out.println("\nDish names grouped by type: " + groupDishNamesByType());
    System.out.println("\nDish tags grouped by type: " + groupDishesByTagType());
    System.out.println("\nDishes grouped by type and caloric level: " + groupDishesByTypeAndCaloricLevel());
    System.out.println("\nCount dishes in groups: " + countDishesInGroups());
    System.out.println("\nMost caloric dishes by type (optional): " + mostCaloricDishesByTypeOptional());
    System.out.println("\nMost caloric dishes by type: " + mostCaloricDishesByType());
    System.out.println("\nTotal calories by dish type: " + getTotalCaloriesByType());
    System.out.println("\nCaloric levels by dish types: " + getCaloricLevelsByType());
    System.out.println("\nCaloric levels by dish types (hash set): " + getCaloricLevelsByTypeHash());
  }

  private static Map<Dish.Type, List<Dish>> groupDishesByType() {
    return Dish.menu.stream().collect(groupingBy(Dish::type));
  }

  private static Map<CaloricLevel, List<String>> groupDishesByCaloricLevel() {
    return Dish.menu.stream().collect(
      groupingBy(dish -> {
        if (dish.calories() <= 400) {
          return CaloricLevel.DIET;
        } else if (dish.calories() <= 700){
          return CaloricLevel.NORMAL;
        } else {
          return CaloricLevel.FAT;
        }
      }, mapping(Dish::name, toList()))
    );
  }

  private static Map<Dish.Type, List<String>> groupCaloricDishesByType() {
    return Dish.menu.stream().collect(groupingBy(Dish::type,
      filtering(dish -> dish.calories() > 500, mapping(Dish::name, toList()))));
  }

  private static Map<Dish.Type, List<String>> groupDishNamesByType() {
    return Dish.menu.stream().collect(groupingBy(Dish::type, mapping(Dish::name, toList())));
  }

  private static Map<Dish.Type, Set<String>> groupDishesByTagType() {
    return Dish.menu.stream().collect(groupingBy(Dish::type,
      flatMapping(dish -> Dish.dishTags.get(dish.name()).stream(), toSet())));
  }

  private static Map<Dish.Type, Map<CaloricLevel, List<String>>> groupDishesByTypeAndCaloricLevel() {
    return Dish.menu.stream().collect(
      groupingBy(Dish::type,
        groupingBy(dish -> {
          if (dish.calories() <= 400) {
            return CaloricLevel.DIET;
          } else if (dish.calories() <= 700) {
            return CaloricLevel.NORMAL;
          } else {
            return CaloricLevel.FAT;
          }
        }, mapping(Dish::name, toList()))
      ));
  }

  private static Map<Dish.Type, Long> countDishesInGroups() {
    return Dish.menu.stream().collect(groupingBy(Dish::type, counting()));
  }

  private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByTypeOptional() {
    return Dish.menu.stream().collect(groupingBy(Dish::type,
      maxBy(Comparator.comparingInt(Dish::calories))));
  }

  private static Map<Dish.Type, String> mostCaloricDishesByType() {
    return Dish.menu.stream().collect(
      groupingBy(Dish::type, collectingAndThen(
        maxBy(Comparator.comparingInt(Dish::calories)),
        d -> d.get().name())));
  }

  private static Map<Dish.Type, Integer> getTotalCaloriesByType() {
    return Dish.menu.stream().collect(groupingBy(Dish::type, summingInt(Dish::calories)));
  }

  private static Map<Dish.Type, Set<CaloricLevel>> getCaloricLevelsByType() {
    return Dish.menu.stream().collect(groupingBy(Dish::type, mapping(dish -> {
        if (dish.calories() <= 400) {
          return CaloricLevel.DIET;
        } else if (dish.calories() <= 700) {
          return CaloricLevel.NORMAL;
        } else {
          return CaloricLevel.FAT;
        }
    }, toSet())));
  }

  private static Map<Dish.Type, Set<CaloricLevel>> getCaloricLevelsByTypeHash() {
    return Dish.menu.stream().collect(groupingBy(Dish::type, mapping(dish -> {
        if (dish.calories() <= 400) {
          return CaloricLevel.DIET;
        } else if (dish.calories() <= 700) {
          return CaloricLevel.NORMAL;
        } else {
          return CaloricLevel.FAT;
        }
    }, toCollection(HashSet::new))));
  }
}
