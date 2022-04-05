package com.modernjava.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import com.modernjava.chapter4.Dish;

public class NumericStreams {
  
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
    Arrays.stream(numbers.toArray())
        .forEach(System.out::println);

    // IntStream examples
    int menuCalories = Dish.menu.stream().mapToInt(Dish::calories).sum();
    System.out.printf("%nTotal calories in menu: %d%n", menuCalories);

    System.out.println("\nCalorie example:");
    IntStream intStream = Dish.menu.stream().mapToInt(Dish::calories);
    Stream<Integer> stream = intStream.boxed();
    stream.forEach(System.out::println);

    // OptionalInt example
    System.out.println("\nOptionalInt example:");
    OptionalInt maxCalories = Dish.menu.stream()
      .mapToInt(Dish::calories)
      .max();
    int max = maxCalories.orElse(1);
    System.out.printf("The max calories on the menu is: %d%n", max);

    // Numeric ranges
    System.out.println("Numeric ranges:");
    System.out.println(IntStream.range(1, 100).filter(n -> n % 2 == 0).count());
    System.out.println(IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0).count());

    // Pythagorean triples
    System.out.println("Pythagorean Triples");
    Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
            .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
            .mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));
    pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

  }
}
