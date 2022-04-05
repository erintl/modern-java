package com.modernjava.chapter5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
  
  public static void main(String[] args) {
    // Stream.of
    System.out.println("Stream.of:");
    Stream<String> stringStream = Stream.of("Modern", "Java", "In", "Action");
    stringStream.map(String::toUpperCase).forEach(System.out::println);

    // Stream.empty
    System.out.println("\nStream.empty:");
    Stream<String> emptyStream = Stream.empty();
    emptyStream.forEach(System.out::println);

    // Arrays.stream
    System.out.println("\nArrays.stream:");
    int[] numbers = {2, 3, 5, 7, 11, 13};
    System.out.println("The sum is: " + Arrays.stream(numbers).sum());

    // File stream
    System.out.println("\nFile streams");
    long uniqueWords = 0;
    try (Stream<String> lines = Files.lines(Path.of("src/main/resources/data2.txt"),
      Charset.defaultCharset())) {
      uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
        .distinct()
        .count();
    } catch(IOException e) {
      System.out.println(e);
    }
    System.out.println("Unique word count: " + uniqueWords);

    // Stream.iterate
    System.out.println("\nStream.iterate example:");
    Stream.iterate(0, n -> n + 2)
      .limit(10)
      .forEach(System.out::println);

    // Fibonacci with iterate
    System.out.println("\nFibonacci 1:");
    Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
      .limit(20)
      .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

    System.out.println("\nFibonacci 2:");
    Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
      .limit(20)
      .map(t -> t[0])
      .forEach(System.out::println);

    System.out.println("\nFibonacci Test 1:");
    System.out.println(fib1(7));

    // Stream.iterate Java 9
    System.out.println("\nStream.iterate Java 9 example:");
    IntStream.iterate(0, n -> n < 100, n -> n + 4)
      .forEach(System.out::println);

    // Generate examples
    System.out.println("\nRandom generation:");
    Stream.generate(Math::random)
      .limit(5)
      .forEach(System.out::println);

    System.out.println("\nOnes generation:");
    Stream.generate(() -> 1)
      .limit(5)
      .forEach(System.out::println);

    System.out.println("\nGenerate supplier example:");
    IntStream.generate(new IntSupplier() {
      @Override
      public int getAsInt() {
        return 2;
      }
    }).limit(5).forEach(System.out::println);

    System.out.println("\n Fibonacci using Stream.generate:");
    IntSupplier fib = new IntSupplier() {
      private int previous = 0;
      private int current = 1;

      @Override
      public int getAsInt() {
        int nextValue = this.previous + this.current;
        int oldPrevious = this.previous;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
      }
    };
    IntStream.generate(fib).limit(10).forEach(System.out::println);
  }

  public static int fib1(int n) {
    if (n <= 1) {
      return n;
    }
    return fib1(n - 1) + fib1(n - 2);
  }
}
