package com.modernjava.chapter5;

import static java.util.stream.Collectors.toList;
import java.util.Arrays;
import java.util.List;
import com.modernjava.chapter4.Dish;


public class Mapping {

  public static void main(String[] args) {
    System.out.println("Dish names:");
    List<String> dishNames = Dish.menu.stream()
      .map(Dish::name)
      .collect(toList());
    dishNames.forEach(System.out::println);

    System.out.println("\nWord lengths");
    List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
    List<Integer> wordLengths = words.stream()
      .map(String::length)
      .collect(toList());
    wordLengths.forEach(System.out::println);  

    System.out.println("\nUnique characters flatmap example:");
    List<String> uniqueCharacters = words.stream()
      .flatMap((String word) -> Arrays.stream(word.split("")))
      .distinct()
      .collect(toList());
    uniqueCharacters.forEach(System.out::println);

    System.out.println("\nSquare numbers:");
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> squaredNumbers = numbers.stream()
      .map(n -> n * n)
      .collect(toList());
    squaredNumbers.forEach(System.out::println);

    System.out.println("\nNumber pairs:");
    List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> numbers2 = Arrays.asList(6, 7 ,8);
    List<int[]> pairs = numbers1.stream()
      .flatMap(i -> numbers2.stream()
                            .map(j -> new int[]{i , j}))
      .collect(toList());
    pairs.forEach(Mapping::printPair);

    System.out.println("\nNumber pairs 3:");
    List<int[]> pairs3 = numbers1.stream()
      .flatMap(i -> numbers2.stream()
                            .map(j -> new int[]{i, j}))
      .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
      .collect(toList());
    pairs3.forEach(Mapping::printPair);
  }

  private static void printPair(int[] pair) {
    System.out.printf("%d %d%n", pair[0], pair[1]);
  }
}
