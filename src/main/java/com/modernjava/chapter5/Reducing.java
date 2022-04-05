package com.modernjava.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.modernjava.chapter4.Dish;

public class Reducing {
  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

    System.out.println("Sum example 1:");
    int sum1 = numbers.stream().reduce(0, (a, b) -> a + b);
    System.out.println(sum1);
    
    System.out.println("\nSum example 2:");
    int sum2 = numbers.stream().reduce(0, Integer::sum);
    System.out.println(sum2);


    System.out.println("\nMaximum example:");
    int max = numbers.stream().reduce(0, Integer::max);
    System.out.println(max);

    System.out.println("\nMinimum example:");
    Optional<Integer> min = numbers.stream().reduce(Integer::min);
    min.ifPresent(System.out::println);

    System.out.println("\nQuiz 5.3 example 1:");
    int dishCount = Dish.menu.stream().map(d -> 1).reduce(0, Integer::sum);
    System.out.printf("dish count: %d%n", dishCount);

    System.out.println("\nQuiz 5.3 example 2:");
    long dishCount2 = Dish.menu.stream().count();
    System.out.printf("dish count: %d%n", dishCount2);

    System.out.println("\nMenu calories example:");
    int calories = Dish.menu.stream().map(Dish::calories).reduce(0, Integer::sum);
    System.out.println(calories);
  }
}
