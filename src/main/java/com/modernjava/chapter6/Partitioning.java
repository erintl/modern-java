package com.modernjava.chapter6;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import com.modernjava.chapter4.Dish;

public class Partitioning {
  
  public static void main(String[] args) {
    System.out.println("Dishes partitioned by vegetarian: " + partitionByVegetarian());
    System.out.println("\nVegetarian dishes partitioned by type:" + vegetarianDishesByType());
    System.out.println("\nMost caloric partitioned by vegetarian: " + mostCaloricPartitionedByVegetarian());
    System.out.println("\nIs 13 prime?: " + isPrime(13));
    System.out.println("\nPartitioning primes: " + partitionPrimes(25));
  }

  private static Map<Boolean, List<String>> partitionByVegetarian() {
    return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
      mapping(Dish::name, toList())));
  }

  private static Map<Boolean, Map<Dish.Type, List<String>>> vegetarianDishesByType() {
    return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
      groupingBy(Dish::type, mapping(Dish::name, toList()))));
  }

  private static Map<Boolean, String> mostCaloricPartitionedByVegetarian() {
    return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
      collectingAndThen(maxBy(Comparator.comparingInt(Dish::calories)),d -> d.get().name())));
  }

  private static boolean isPrime(int candidate) {
    int candidateRoot = (int)Math.sqrt(candidate);
    return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
  }

  private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
    return IntStream.rangeClosed(2, n)
      .boxed()
      .collect(partitioningBy(Partitioning::isPrime));
  }
}
