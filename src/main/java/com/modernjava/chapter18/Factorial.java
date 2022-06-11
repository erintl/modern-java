package com.modernjava.chapter18;

import java.util.stream.LongStream;

public class Factorial {
  
  public static void main(String[] args) {
    System.out.println("------ Factorial Demonstration ------");
    System.out.printf("Iterative: %d%n", factorialIterative(4));
    System.out.printf("Recursive: %d%n", factorialRecursive(4));
    System.out.printf("Stream: %d%n:", factorialStream(4));
  }

  public static long factorialIterative(long n) {
    long result = 1;
    for (int i = 1; i <=n; i++) {
      result = result * i;
    }
    return result;
  }

  public static long factorialRecursive(long n) {
    return n == 1 ? 1 : n * factorialRecursive(n - 1);
  }

  public static long factorialStream(long n) {
    return LongStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
  }
}
